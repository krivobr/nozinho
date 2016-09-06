package br.com.nozinho.ejb.service;

import java.util.Date;
import java.util.List;

import javax.ejb.Local;

import br.com.nozinho.exceptions.BusinessException;
import br.com.nozinho.model.EventoAgenda;

@Local
public interface CalendarService {
	
	List<EventoAgenda> findEventosByDate(Date startDate, Date endDate) throws BusinessException;
	
	void insertEvento(EventoAgenda evento) throws BusinessException;
	
	void updateEvento(EventoAgenda evento) throws BusinessException;
	
	void deleteEvento(EventoAgenda evento) throws BusinessException;

}
