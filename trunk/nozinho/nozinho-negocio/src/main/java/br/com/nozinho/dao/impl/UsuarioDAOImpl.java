package br.com.nozinho.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import br.com.nozinho.dao.UsuarioDAO;
import br.com.nozinho.ejb.dao.impl.GenericDAOImpl;
import br.com.nozinho.model.Usuario;

@Stateless
public class UsuarioDAOImpl extends GenericDAOImpl<Usuario, Long> implements UsuarioDAO{

	@Override
	public Usuario findByLoginAndSenha(String login, String senha) {
		TypedQuery<Usuario> query = getEntityManager().createNamedQuery("Usuario.findByLoginAndSenha", Usuario.class);
		query.setParameter("login", login);
		query.setParameter("senha", senha);
		return query.getSingleResult();
	}

	@Override
	public List<Usuario> findAllExceptAdm() {
		TypedQuery<Usuario> query = getEntityManager().createNamedQuery("Usuario.findAllExceptAdm", Usuario.class);
		return query.getResultList();
	}

	@Override
	public List<Usuario> findByContatoNomeLike(String stringQuery) {
		TypedQuery<Usuario> query = getEntityManager().createNamedQuery("Usuario.findByNomeContatoLike", Usuario.class);
		query.setParameter("nome", stringQuery + "%");
		return query.getResultList();
	}
	
}
