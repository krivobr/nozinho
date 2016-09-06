package br.com.nozinho.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import br.com.nozinho.dao.ContatoDAO;
import br.com.nozinho.ejb.dao.impl.GenericDAOImpl;
import br.com.nozinho.model.Contato;
import br.com.nozinho.model.TipoPessoaEnum;

@Stateless
public class ContatoDAOImpl extends GenericDAOImpl<Contato, Long> implements ContatoDAO {

	@Override
	public List<Contato> findByNameLike(String name) {
		TypedQuery<Contato> query = getEntityManager().createNamedQuery("Contato.findByNameLike", Contato.class);
		query.setParameter("nome", name + "%");
		return query.getResultList();
	}
	
	@Override
	public List<Contato> findAllFetchAll(){
		TypedQuery<Contato> query = getEntityManager().createNamedQuery("Contato.findAllFetchAll", Contato.class);
		return query.getResultList();
	}

	@Override
	public Contato findByIdFetchAll(Long id) {
		TypedQuery<Contato> query = getEntityManager().createNamedQuery("Contato.findByIdFetchAll", Contato.class);
		query.setParameter("id", id);
		return query.getSingleResult();
	}

	@Override
	public List<Contato> findByNameLikeAndType(String name,
			TipoPessoaEnum tipoPessoa) {
		TypedQuery<Contato> query = getEntityManager().createNamedQuery("Contato.findByNameLikeAndType", Contato.class);
		query.setParameter("nome", name + "%");
		query.setParameter("tipoPessoa", tipoPessoa);
		return query.getResultList();
	}
	
	
}
