package br.com.nozinho.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import br.com.nozinho.dao.DemandaDAO;
import br.com.nozinho.ejb.dao.impl.GenericDAOImpl;
import br.com.nozinho.model.Demanda;

@Stateless
public class DemandaDAOImpl extends GenericDAOImpl<Demanda, Long> implements DemandaDAO{

	@Override
	public List<Demanda> findAllFetchContatoInteressado() {
		Query query = getEntityManager().createNamedQuery("Demanda.findAllFetchContatoInteressado");
		List<Demanda> list = new ArrayList<>();
		for(Object obj : query.getResultList()){
			Object[] objArr = (Object[])obj;
			Demanda demanda = (Demanda)objArr[0];
			list.add(demanda);
		}
		return list;
	}

	@Override
	public Demanda findByIdFetchHistorico(Long id) {
		TypedQuery<Demanda> query = getEntityManager().createNamedQuery("Demanda.findByIdFetchHistorico", Demanda.class);
		query.setParameter("id", id);
		return query.getSingleResult();
	}
	
	@Override
	public Long findMaxId() {
		try{
			TypedQuery<Long> query = getEntityManager().createNamedQuery("Demanda.findMaxId", Long.class);
			return query.getSingleResult() != null ? query.getSingleResult() : 1l;
		}catch(NoResultException e){
			return 1l;
		}
	}

}
