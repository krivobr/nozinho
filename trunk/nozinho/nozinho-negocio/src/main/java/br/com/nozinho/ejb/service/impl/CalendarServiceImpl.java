package br.com.nozinho.ejb.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.apache.commons.io.IOUtils;

import br.com.nozinho.ejb.service.CalendarService;
import br.com.nozinho.ejb.service.ContatoService;
import br.com.nozinho.exceptions.BusinessException;
import br.com.nozinho.model.Contato;
import br.com.nozinho.model.EventoAgenda;
import br.com.nozinho.util.message.MessageUtil;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.Event.Creator;
import com.google.api.services.calendar.model.Event.Organizer;
import com.google.api.services.calendar.model.EventAttendee;
import com.google.api.services.calendar.model.EventDateTime;
import com.google.api.services.calendar.model.Events;

@Stateless
public class CalendarServiceImpl implements CalendarService {
	
	private Calendar service;
	
	@EJB
	private ContatoService contatoService;
	
	private static String CALENDAR_KEY = "primary"; 
	
	public static void main(String[] args) throws GeneralSecurityException, IOException,BusinessException {
		CalendarServiceImpl calendar = new CalendarServiceImpl();
		calendar.init();
		calendar.insertEvento(null);
		calendar.findEventosByDate(null, null);
	}
	
	@PostConstruct
	private void init() throws GeneralSecurityException, IOException{
		InputStream fileStream = getClass().getClassLoader().getResourceAsStream("GabineteNozinho-23deef70ae0b.p12");
		GoogleCredential credentials = new GoogleCredential.Builder()
		.setTransport(GoogleNetHttpTransport.newTrustedTransport())
		.setJsonFactory(new GsonFactory())
		.setServiceAccountId("gabinetenozinho-1362@appspot.gserviceaccount.com")
		.setServiceAccountScopes(Arrays.asList("https://www.googleapis.com/auth/calendar"))
		.setServiceAccountPrivateKeyFromP12File(stream2file(fileStream))
		.build();
		service = new Calendar.Builder(GoogleNetHttpTransport.newTrustedTransport(), new GsonFactory(), credentials)
		.setApplicationName("GoogleCalendar").build();
		
	}
	
	private File stream2file (InputStream in) throws IOException {
        final File tempFile = File.createTempFile("tempFile", ".p12");
        tempFile.deleteOnExit();
        try (FileOutputStream out = new FileOutputStream(tempFile)) {
            IOUtils.copy(in, out);
        }
        return tempFile;
    }
	
	@Override
	public List<EventoAgenda> findEventosByDate(Date startDate, Date finalDate) throws BusinessException{
		List<EventoAgenda> eventos = new ArrayList<>();
		try{
			DateTime initDate = new DateTime(startDate);
			DateTime endDate = new DateTime(finalDate);
			Events events = service.events()
				  .list(CALENDAR_KEY)
				  .setTimeMin(initDate)
				  .setTimeMax(endDate)				  
				  .execute();
			List<Event> items = events.getItems();
			for (Event event : items) {
			  eventos.add(converteEvento(event));
			}
			return eventos;
		}catch(Exception e){
			e.printStackTrace();
			throw new BusinessException(MessageUtil.getMessage("message.calendar.error"));
		}
	}
	
	@Override
	public void insertEvento(EventoAgenda evento) throws BusinessException{
		try{
			Event event = new Event();
			EventDateTime start = new EventDateTime();
			EventDateTime end = new EventDateTime();
			start.setDateTime(new DateTime(evento.isDiaTodo(), evento.getDataInicio().getTime(), -3));
			end.setDateTime(new DateTime(evento.isDiaTodo(), evento.getDataTermino().getTime(), -3));		
			event.setSummary(evento.getTitulo());
			event.setDescription(evento.getDescricao());
			event.setStart(start);
			event.setEnd(end);
			event.setCreator(convertCreator());
			event.setAttendees(convertAttendee(evento.getConvidados()));
			event.setLocation(evento.getLocal());
			event.setOrganizer(createOrganizer());
			service.events().insert(CALENDAR_KEY, event)
						.setSendNotifications(true)
						.execute();
		}catch(Exception e){
			e.printStackTrace();
			throw new BusinessException(MessageUtil.getMessage("message.calendar.error"));
		}
	}
	
	@Override
	public void updateEvento(EventoAgenda evento) throws BusinessException{
		try{
			Event event = new Event();
			EventDateTime start = new EventDateTime();
			EventDateTime end = new EventDateTime();		
			start.setDateTime(new DateTime(evento.isDiaTodo(), evento.getDataInicio().getTime(), -3));
			end.setDateTime(new DateTime(evento.isDiaTodo(), evento.getDataTermino().getTime(), -3));
			event.setId(evento.getId());
			event.setSummary(evento.getTitulo());
			event.setDescription(evento.getDescricao());
			event.setStart(start);
			event.setEnd(end);
			event.setCreator(convertCreator());
			event.setAttendees(convertAttendee(evento.getConvidados()));
			service.events()
						.update(CALENDAR_KEY,event.getId(), event)
						.setSendNotifications(true)
						.execute();
		}catch(Exception e){
			e.printStackTrace();
			throw new BusinessException(MessageUtil.getMessage("message.calendar.error"));
		}
	}
	
	@Override
	public void deleteEvento(EventoAgenda evento) throws BusinessException{
		try{
			service.events()
						.delete(CALENDAR_KEY,evento.getId())
						.setSendNotifications(true)
						.execute();
		}catch(Exception e){
			e.printStackTrace();
			throw new BusinessException(MessageUtil.getMessage("message.calendar.error"));
		}
	}
	
	private Creator convertCreator(){
		Creator creator = new Creator();
		creator.setDisplayName("Rodrigo Andrade");
		creator.setEmail("rodrigo.andrade08@gmail.com");
		return creator;
	}
	
	private List<EventAttendee> convertAttendee(List<Contato> convidados){
		List<EventAttendee> listAttendees = new ArrayList<EventAttendee>();
		if(convidados != null){
			for(Contato convidado : convidados){
				Contato contato = contatoService.findByIdFetchAll((Long) convidado.getId());
				EventAttendee attendee = new EventAttendee();
				attendee.setEmail(contato.getEmail().getNome());
				attendee.setDisplayName(contato.getNome());
				listAttendees.add(attendee);
			}
		}
		return listAttendees;
	}

	private Organizer createOrganizer(){
		Organizer org = new Organizer();
		org.setEmail("roandrad@hotmail.com");
		org.setDisplayName("Rodrigo Andrade Cotes");
		return org;
	}
	
	private EventoAgenda converteEvento(Event event){
		EventoAgenda evento = new EventoAgenda();
		evento.setId(event.getId());
		evento.setTitulo(event.getSummary());
		evento.setLocal(event.getLocation());
		evento.setDescricao(event.getDescription());
		evento.setDiaTodo(event.getStart().getDateTime().isDateOnly());
		evento.setDataInicio(new Date(event.getStart().getDateTime().getValue()));
		evento.setDataTermino(new Date(event.getEnd().getDateTime().getValue()));
		evento.setConvidados(converteConvidados(event.getAttendees()));
		return evento;
	}
	
	private List<Contato> converteConvidados(List<EventAttendee> attendees){
		List<Contato> contatos = new ArrayList<>();
		long id = 1;
		if(attendees != null){
			for(EventAttendee attendee : attendees){
				Contato contato = new Contato();
				contato.setId(id++);
				contato.setNome(attendee.getDisplayName());
//				contato.setEmail(attendee.getEmail());
				contatos.add(contato);
			}
		}
		return contatos;
	}
}
