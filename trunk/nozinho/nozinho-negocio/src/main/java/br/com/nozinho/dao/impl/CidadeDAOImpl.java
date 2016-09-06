package br.com.nozinho.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import br.com.nozinho.dao.CidadeDAO;
import br.com.nozinho.ejb.dao.impl.GenericDAOImpl;
import br.com.nozinho.model.Cidade;

@Stateless
public class CidadeDAOImpl extends GenericDAOImpl<Cidade, Long> implements CidadeDAO{

	public List<Cidade> findByNomeLike(String nome){
		TypedQuery<Cidade> query = getEntityManager().createNamedQuery("Cidade.findByNomeLike", Cidade.class);
		query.setParameter("nome", nome + "%");
		return query.getResultList();
	}
	
}
