package br.com.nozinho.ejb.service;

import java.util.List;

import javax.ejb.Local;

import br.com.nozinho.model.Notificacao;
import br.com.nozinho.model.TipoNotificacaoEnum;
import br.com.nozinho.model.Usuario;

@Local
public interface NotificacaoService extends GenericService<Notificacao, Long>{

	List<Notificacao> findByUsuarioNotificado(Usuario usuario);
	
	Long findNumNotificacaoByUsuario(Usuario usuario);
	
	Notificacao createNotificacao(Usuario usuarioNotificado, Usuario usuarioNotificador, TipoNotificacaoEnum tipo, Long idObjeto, String complementoMensagem);
	
}
