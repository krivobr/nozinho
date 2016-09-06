package br.com.nozinho.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import br.com.nozinho.dao.NotificacaoDAO;
import br.com.nozinho.ejb.dao.impl.GenericDAOImpl;
import br.com.nozinho.model.Notificacao;
import br.com.nozinho.model.Usuario;

@Stateless
public class NotificacaoDAOImpl extends GenericDAOImpl<Notificacao, Long> implements NotificacaoDAO{

	@Override
	public List<Notificacao> findByUsuarioNotificado(Usuario usuario) {
		TypedQuery<Notificacao> query = getEntityManager().createNamedQuery("Notificacao.findAllOrdered", Notificacao.class);
		query.setParameter("usuario", usuario);
		query.setMaxResults(10);
		return query.getResultList();
	}
	
	public Long findNumNotificacaoByUsuario(Usuario usuario){
		TypedQuery<Long> query = getEntityManager().createNamedQuery("Notificacao.countByUser", Long.class);
		query.setParameter("usuario", usuario);
		return query.getSingleResult();		
	}

}
