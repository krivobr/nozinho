package br.com.nozinho.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import br.com.nozinho.model.Perfil;

import br.com.nozinho.dao.PerfilDAO;
import br.com.nozinho.ejb.dao.impl.GenericDAOImpl;

@Stateless
public class PerfilDAOImpl extends GenericDAOImpl<Perfil, Long> implements PerfilDAO{

	@Override
	public List<Perfil> findPerfis() {
		TypedQuery<Perfil> query = getEntityManager().createNamedQuery("Perfil.findPerfisExceptAdm", Perfil.class);
		return query.getResultList();
	}

	@Override
	public Perfil findByIdFetchPermissao(Long id) {
		TypedQuery<Perfil> query = getEntityManager().createNamedQuery("Perfil.findByIdFetchPermissao", Perfil.class);
		query.setParameter("id", id);
		return query.getSingleResult();
	}

}
