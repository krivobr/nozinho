package br.com.nozinho.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import br.com.nozinho.dao.OficioDAO;
import br.com.nozinho.ejb.dao.impl.GenericDAOImpl;
import br.com.nozinho.model.Oficio;

@Stateless
public class OficioDAOImpl extends GenericDAOImpl<Oficio, Long> implements OficioDAO{

	@Override
	public List<Oficio> findByNumeroLike(String numero) {
		TypedQuery<Oficio> query = getEntityManager().createNamedQuery("Oficio.findByNumeroLike", Oficio.class);
		query.setParameter("numero", numero + "%");
		return query.getResultList();
	}

	@Override
	public Long findMaxId() {
		try{
			TypedQuery<Long> query = getEntityManager().createNamedQuery("Oficio.findMaxId", Long.class);
			return query.getSingleResult() != null ? query.getSingleResult() : 1l;
		}catch(NoResultException e){
			return 1l;
		}
	}

}
