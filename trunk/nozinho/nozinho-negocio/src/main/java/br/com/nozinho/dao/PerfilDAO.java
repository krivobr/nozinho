package br.com.nozinho.dao;

import java.util.List;

import javax.ejb.Local;

import br.com.nozinho.model.Perfil;

import br.com.nozinho.ejb.dao.GenericDAO;

@Local
public interface PerfilDAO extends GenericDAO<Perfil, Long>{
	
	List<Perfil> findPerfis();
	
	Perfil findByIdFetchPermissao(Long id);

}
