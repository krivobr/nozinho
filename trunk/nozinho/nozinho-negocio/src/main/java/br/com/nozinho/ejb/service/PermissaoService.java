package br.com.nozinho.ejb.service;

import java.util.List;

import javax.ejb.Local;

import br.com.nozinho.model.Permissao;

@Local
public interface PermissaoService extends GenericService<Permissao, Long>{
	
	List<Permissao> findPermissoes();

}
