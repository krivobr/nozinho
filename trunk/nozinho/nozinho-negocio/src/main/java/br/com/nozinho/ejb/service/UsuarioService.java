package br.com.nozinho.ejb.service;

import java.util.List;

import javax.ejb.Local;

import br.com.nozinho.model.Usuario;

@Local
public interface UsuarioService extends GenericService<Usuario, Long>{
	
	Usuario findByLoginAndSenha(String login, String senha);
	
	List<Usuario> findAllExceptAdm();
	
	List<Usuario> findByNomeContato(String query);

}
