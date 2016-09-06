package br.com.nozinho.ejb.service.impl;

import java.util.Calendar;
import java.util.List;

import javax.ejb.Stateless;

import br.com.nozinho.dao.DemandaDAO;
import br.com.nozinho.ejb.service.DemandaService;
import br.com.nozinho.model.Demanda;

@Stateless
public class DemandaServiceImpl extends GenericServiceImpl<Demanda, Long> implements DemandaService{

	@Override
	public List<Demanda> findAllFetchContatoInteressado() {
		return ((DemandaDAO) getDAO()).findAllFetchContatoInteressado();
	}

	@Override
	public Demanda findByIdFetchHistorico(Long id) {
		return ((DemandaDAO) getDAO()).findByIdFetchHistorico(id);
	}

	@Override
	public String getSugestaoNumeroDemanda() {
		Long numero = ((DemandaDAO) getDAO()).findMaxId();
		Integer ano = Calendar.getInstance().get(Calendar.YEAR);
		return ano.toString() + "/" + numero.toString();
	}

}
