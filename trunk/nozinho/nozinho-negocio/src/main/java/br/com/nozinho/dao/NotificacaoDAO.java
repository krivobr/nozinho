package br.com.nozinho.dao;

import java.util.List;

import javax.ejb.Local;

import br.com.nozinho.ejb.dao.GenericDAO;
import br.com.nozinho.model.Notificacao;
import br.com.nozinho.model.Usuario;

@Local
public interface NotificacaoDAO extends GenericDAO<Notificacao, Long>{

	List<Notificacao> findByUsuarioNotificado(Usuario usuario);
	
	public Long findNumNotificacaoByUsuario(Usuario usuario);
	
}
