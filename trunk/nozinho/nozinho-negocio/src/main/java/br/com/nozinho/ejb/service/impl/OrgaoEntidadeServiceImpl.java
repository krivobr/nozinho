package br.com.nozinho.ejb.service.impl;

import java.util.List;

import javax.ejb.Stateless;

import br.com.nozinho.dao.OrgaoEntidadeDAO;
import br.com.nozinho.ejb.service.OrgaoEntidadeService;
import br.com.nozinho.model.OrgaoEntidade;

@Stateless
public class OrgaoEntidadeServiceImpl extends GenericServiceImpl<OrgaoEntidade, Long> implements OrgaoEntidadeService {

	@Override
	public List<OrgaoEntidade> findByNameLike(String query) {
		return ((OrgaoEntidadeDAO)getDAO()).findByNameLike(query);
	}

}
