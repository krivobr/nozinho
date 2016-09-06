package br.com.nozinho.web.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.context.RequestContext;
import org.primefaces.event.ScheduleEntryMoveEvent;
import org.primefaces.event.ScheduleEntryResizeEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.LazyScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;

import br.com.nozinho.ejb.service.CalendarService;
import br.com.nozinho.ejb.service.ContatoService;
import br.com.nozinho.exceptions.BusinessException;
import br.com.nozinho.model.Contato;
import br.com.nozinho.model.EventoAgenda;
import br.com.nozinho.web.messages.MessagesUtil;

@ManagedBean
@ViewScoped
public class AgendaController implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6237098660209240736L;
	private ScheduleModel eventModel;
	private ScheduleEvent event;
	private boolean diaInteiro;
	private boolean repetir;
	private EventoAgenda evento;
	private Map<String, EventoAgenda> mapEventos = new HashMap<>();
	private static final String REDIRECT_TO = "agenda.xhtml?redirect=true";
	
	@EJB
	private ContatoService contatoService;
	
	@EJB
	private CalendarService calendarService;

	@PostConstruct
	public void init(){
		eventModel = new LazyScheduleModel(){
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void loadEvents(Date start, Date end){
				List<EventoAgenda> eventos = new ArrayList<>();
				try {
					eventos = calendarService.findEventosByDate(start, end);
				} catch (BusinessException e) {
					e.printStackTrace();
					MessagesUtil.showErrorMessage(e.getMessage());
				}
				for(EventoAgenda evento : eventos){
					ScheduleEvent scheduleEvent = new DefaultScheduleEvent(evento.getTitulo(), evento.getDataInicio(), evento.getDataTermino(), evento.isDiaTodo());
					eventModel.addEvent(scheduleEvent);
					scheduleEvent.setId(evento.getId());
					mapEventos.put(evento.getId(), evento);
				}
				
			}
		};
		evento = new EventoAgenda();
		Calendar startDate = Calendar.getInstance();
		Calendar endDate = Calendar.getInstance();
		startDate.set(Calendar.DATE, Calendar.getInstance().getActualMinimum(Calendar.DAY_OF_MONTH));
		endDate.set(Calendar.DATE, Calendar.getInstance().getActualMaximum(Calendar.DAY_OF_MONTH));
		List<EventoAgenda> eventos = new ArrayList<>();
		try {
			eventos = calendarService.findEventosByDate(startDate.getTime(), endDate.getTime());
		} catch (BusinessException e) {
			e.printStackTrace();
			MessagesUtil.showErrorMessage(e.getMessage());
		}
		for(EventoAgenda evento : eventos){
			ScheduleEvent scheduleEvent = new DefaultScheduleEvent(evento.getTitulo(), evento.getDataInicio(), evento.getDataTermino(), evento.isDiaTodo());
			eventModel.addEvent(scheduleEvent);
			scheduleEvent.setId(evento.getId());
			mapEventos.put(evento.getId(), evento);
		}

	}
	
	public ScheduleModel getEventModel() {
		return eventModel;
	}
	
	public boolean isDiaInteiro() {
		return diaInteiro;
	}

	public void setDiaInteiro(boolean diaInteiro) {
		this.diaInteiro = diaInteiro;
	}

	public boolean isRepetir() {
		return repetir;
	}

	public void setRepetir(boolean repetir) {
		this.repetir = repetir;
	}	
	
	public EventoAgenda getEvento() {
		return evento;
	}

	public void setEvento(EventoAgenda evento) {
		this.evento = evento;
	}

	//Events
	public void onDateSelect(SelectEvent selectEvent) {
        event = new DefaultScheduleEvent("", (Date) selectEvent.getObject(), (Date) selectEvent.getObject());
        evento = new EventoAgenda();
        evento.setDataInicio(event.getStartDate());
        evento.setDataTermino(event.getEndDate());
    }
	public void onEventSelect(SelectEvent selectEvent) {
        event = (ScheduleEvent) selectEvent.getObject();
        evento = mapEventos.get(event.getId());
    }
	public void onEventMove(ScheduleEntryMoveEvent event) {
		this.event = event.getScheduleEvent();
		try {
			evento = mapEventos.get(this.event.getId());
			evento.setDataInicio(this.event.getStartDate());
			evento.setDataTermino(this.event.getEndDate());
			calendarService.updateEvento(evento);
			MessagesUtil.addSuccessMessage("registro.atualizado");
		} catch (BusinessException e) {
			e.printStackTrace();
			MessagesUtil.showErrorMessage(e.getMessage());
		}
    }
    public void onEventResize(ScheduleEntryResizeEvent event) {
        this.event = event.getScheduleEvent();
 		try {
			evento = mapEventos.get(this.event.getId());
			evento.setDataInicio(this.event.getStartDate());
			evento.setDataTermino(this.event.getEndDate());
			calendarService.updateEvento(evento);
			MessagesUtil.addSuccessMessage("registro.atualizado");
		} catch (BusinessException e) {
			e.printStackTrace();
			MessagesUtil.showErrorMessage(e.getMessage());
		}
    }
    
    //Methods
    public List<Contato> completeConvidados(String query){
    	return contatoService.findByNameLike(query);
    }
    
    public String deleteEntidade(){
    	try {
			calendarService.deleteEvento(evento);
			MessagesUtil.addErrorMessage("registro.apagado");
		} catch (BusinessException e) {
			e.printStackTrace();
			MessagesUtil.showErrorMessage(e.getMessage());
		}
		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('processModal').hide()");
		context.update("appForm");
    	return REDIRECT_TO;
    }
    
    public String processaEntidade(){
    	try {
    		if(evento.getId() == null){
				calendarService.insertEvento(evento);
				MessagesUtil.addErrorMessage("registro.salvo");
    		} else {
    			calendarService.updateEvento(evento);
    		}
		} catch (BusinessException e) {
			MessagesUtil.showErrorMessage(e.getMessage());
		}
		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('processModal').hide()");
		context.update("appForm");
    	return REDIRECT_TO;
    }
    
    public List<Integer> getVezes(){
    	List<Integer> listaVezes = new ArrayList<>();
    	for(int i = 1; i <= 30; i++){
    		listaVezes.add(i);
    	}
    	return listaVezes;
    }
}
