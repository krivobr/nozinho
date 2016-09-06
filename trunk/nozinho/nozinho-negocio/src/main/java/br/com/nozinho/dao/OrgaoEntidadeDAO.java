package br.com.nozinho.dao;

import java.util.List;

import javax.ejb.Local;

import br.com.nozinho.ejb.dao.GenericDAO;
import br.com.nozinho.model.OrgaoEntidade;

@Local
public interface OrgaoEntidadeDAO extends GenericDAO<OrgaoEntidade, Long>{
	
	List<OrgaoEntidade> findByNameLike(String name);

}
