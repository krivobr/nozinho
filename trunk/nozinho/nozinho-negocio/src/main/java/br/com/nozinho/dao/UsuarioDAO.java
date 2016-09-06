package br.com.nozinho.dao;

import java.util.List;

import javax.ejb.Local;

import br.com.nozinho.model.Usuario;
import br.com.nozinho.ejb.dao.GenericDAO;

@Local
public interface UsuarioDAO extends GenericDAO<Usuario, Long>{

	Usuario findByLoginAndSenha(String login, String senha);
	
	List<Usuario> findAllExceptAdm();
	
	List<Usuario> findByContatoNomeLike(String query);
	
	
}
