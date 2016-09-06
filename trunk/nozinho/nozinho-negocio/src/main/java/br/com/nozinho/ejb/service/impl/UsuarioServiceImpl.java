package br.com.nozinho.ejb.service.impl;

import java.util.List;

import javax.ejb.Stateless;

import br.com.nozinho.dao.UsuarioDAO;
import br.com.nozinho.ejb.service.UsuarioService;
import br.com.nozinho.model.Usuario;

@Stateless
public class UsuarioServiceImpl extends GenericServiceImpl<Usuario, Long> implements  UsuarioService{

	@Override
	public Usuario findByLoginAndSenha(String login, String senha) {
		return ((UsuarioDAO)getDAO()).findByLoginAndSenha(login, senha);
	}

	@Override
	public List<Usuario> findAllExceptAdm() {		
		return ((UsuarioDAO)getDAO()).findAllExceptAdm();
	}

	@Override
	public List<Usuario> findByNomeContato(String query) {		
		return ((UsuarioDAO)getDAO()).findByContatoNomeLike(query);
	}

}
