package br.com.nozinho.ejb.service.impl;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;

import br.com.nozinho.dao.NotificacaoDAO;
import br.com.nozinho.ejb.service.NotificacaoService;
import br.com.nozinho.model.Notificacao;
import br.com.nozinho.model.TipoNotificacaoEnum;
import br.com.nozinho.model.Usuario;

@Stateless
public class NotificacaoServiceImpl extends GenericServiceImpl<Notificacao, Long> implements NotificacaoService{

	/**
	 * Traz todas as notificações não lidas ou as últimas cinco últimas lidas.  
	 */
	@Override
	public List<Notificacao> findByUsuarioNotificado(Usuario usuario) {
		List<Notificacao> notificacoes = ((NotificacaoDAO)getDAO()).findByUsuarioNotificado(usuario);
		return notificacoes;
	}

	@Override
	public Long findNumNotificacaoByUsuario(Usuario usuario) {
		return ((NotificacaoDAO)getDAO()).findNumNotificacaoByUsuario(usuario);
	}

	@Override
	public Notificacao createNotificacao(Usuario usuarioNotificado,
			Usuario usuarioNotificador, TipoNotificacaoEnum tipo, Long idDemanda, String complementoMensagem) {
		Notificacao notificacao = new Notificacao();
		notificacao.setDataCriacao(new Date());
		notificacao.setUsuarioNotificado(usuarioNotificado);
		notificacao.setUsuarioNotificador(usuarioNotificador);
		notificacao.setTipoNotificacao(tipo);
		notificacao.setIdObjeto(idDemanda);
		switch(tipo){
			case DEMANDA:
				notificacao.setMensagem("Voçê é reponsável pela demanda " + complementoMensagem);
				break;
			case AGENDA:
				notificacao.setMensagem("Há um novo compromisso na agenda para voçê.");
				break;
		}
		return notificacao;
	}

}
