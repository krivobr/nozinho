package br.com.nozinho.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import br.com.nozinho.dao.OrgaoEntidadeDAO;
import br.com.nozinho.ejb.dao.impl.GenericDAOImpl;
import br.com.nozinho.model.OrgaoEntidade;

@Stateless
public class OrgaoEntidadeDAOImpl extends GenericDAOImpl<OrgaoEntidade, Long> implements OrgaoEntidadeDAO{

	@Override
	public List<OrgaoEntidade> findByNameLike(String name) {
		TypedQuery<OrgaoEntidade> query = getEntityManager().createNamedQuery("OrgaoEntidade.findByNameLike", OrgaoEntidade.class);
		query.setParameter("nome", name + "%");
		return query.getResultList();
	}

}
