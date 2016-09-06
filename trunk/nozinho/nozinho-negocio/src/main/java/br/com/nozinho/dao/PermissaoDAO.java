package br.com.nozinho.dao;

import java.util.List;

import javax.ejb.Local;

import br.com.nozinho.ejb.dao.GenericDAO;
import br.com.nozinho.model.Permissao;

@Local
public interface PermissaoDAO extends GenericDAO<Permissao, Long>{
	
	List<Permissao> findPermissoes();

}
