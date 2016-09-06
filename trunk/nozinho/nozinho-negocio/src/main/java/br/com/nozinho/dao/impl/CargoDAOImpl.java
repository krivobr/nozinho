package br.com.nozinho.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import br.com.nozinho.dao.CargoDAO;
import br.com.nozinho.ejb.dao.impl.GenericDAOImpl;
import br.com.nozinho.model.Cargo;

@Stateless
public class CargoDAOImpl extends GenericDAOImpl<Cargo, Long> implements CargoDAO{

	@Override
	public List<Cargo> findByNoneLike(String nome) {
		TypedQuery<Cargo> query = getEntityManager().createNamedQuery("Cargo.findByNomeLike", Cargo.class);
		query.setParameter("nome", nome + "%");
		return query.getResultList();
	}

}
