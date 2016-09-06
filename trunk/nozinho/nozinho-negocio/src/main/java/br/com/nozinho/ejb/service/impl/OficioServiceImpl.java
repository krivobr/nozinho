package br.com.nozinho.ejb.service.impl;

import java.util.Calendar;
import java.util.List;

import javax.ejb.Stateless;

import br.com.nozinho.dao.OficioDAO;
import br.com.nozinho.ejb.service.OficioService;
import br.com.nozinho.model.Oficio;

@Stateless
public class OficioServiceImpl extends GenericServiceImpl<Oficio, Long> implements OficioService{

	@Override
	public List<Oficio> findByNumeroLike(String numero) {
		return ((OficioDAO)getDAO()).findByNumeroLike(numero);
	}

	@Override
	public String getSugestaoNumeroOficio() {
		Long numero = ((OficioDAO)getDAO()).findMaxId();
		Integer ano = Calendar.getInstance().get(Calendar.YEAR);
		return ano.toString() + "/" + numero.toString();
	}

}
