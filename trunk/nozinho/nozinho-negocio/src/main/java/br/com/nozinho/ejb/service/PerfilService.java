package br.com.nozinho.ejb.service;

import java.util.List;

import javax.ejb.Local;

import br.com.nozinho.model.Perfil;

@Local
public interface PerfilService extends GenericService<Perfil, Long>{
	
	List<Perfil> findPerfis();
	
	Perfil findByIdFetchPermissao(Long id);

}
