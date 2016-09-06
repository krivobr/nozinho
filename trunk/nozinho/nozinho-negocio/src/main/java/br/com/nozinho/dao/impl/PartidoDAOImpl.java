package br.com.nozinho.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import br.com.nozinho.dao.PartidoDAO;
import br.com.nozinho.ejb.dao.impl.GenericDAOImpl;
import br.com.nozinho.model.Partido;

@Stateless
public class PartidoDAOImpl extends GenericDAOImpl<Partido, Long> implements PartidoDAO{

	@Override
	public List<Partido> findBySiglaLike(String name) {
		TypedQuery<Partido> query = getEntityManager().createNamedQuery("Partido.findBySiglaLike", Partido.class);
		query.setParameter("nome", name + "%");
		return query.getResultList();
	}

}
