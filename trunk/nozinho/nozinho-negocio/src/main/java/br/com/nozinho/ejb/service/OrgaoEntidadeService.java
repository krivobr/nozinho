package br.com.nozinho.ejb.service;

import java.util.List;

import javax.ejb.Local;

import br.com.nozinho.model.OrgaoEntidade;

@Local
public interface OrgaoEntidadeService extends GenericService<OrgaoEntidade, Long>{

	List<OrgaoEntidade> findByNameLike(String query);
	
}
