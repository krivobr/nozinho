package br.com.nozinho.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import br.com.nozinho.dao.PermissaoDAO;
import br.com.nozinho.ejb.dao.impl.GenericDAOImpl;
import br.com.nozinho.model.Permissao;

@Stateless
public class PermissaoDAOImpl extends GenericDAOImpl<Permissao, Long> implements PermissaoDAO{

	@Override
	public List<Permissao> findPermissoes() {
		TypedQuery<Permissao> query = getEntityManager().createNamedQuery("Permissao.findPermissaoExceptAdm", Permissao.class);		
		return query.getResultList();
	}

}
