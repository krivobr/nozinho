package br.com.nozinho.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "notificacao")
@NamedQueries({
	@NamedQuery(name = "Notificacao.findAllOrdered", query = "SELECT n FROM Notificacao n WHERE n.usuarioNotificado = :usuario ORDER BY n.lida,n.dataCriacao DESC"),
	@NamedQuery(name = "Notificacao.countByUser", query = "SELECT COUNT(n) FROM Notificacao n WHERE n.lida = false AND n.usuarioNotificado = :usuario")
})
public class Notificacao extends Entidade{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7131191012433305409L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idnotificacao", unique = true, nullable = false)
	private Long id;
	
	@Column(name = "data_criacao")
	@Temporal(TemporalType.DATE)
	private Date dataCriacao;
	
	@Column
	private boolean lida;
	
	@Column
	private String mensagem;
	
	@ManyToOne
	@JoinColumn(name = "idusuario_notificado", referencedColumnName = "idusuario")
	private Usuario usuarioNotificado;
	
	@ManyToOne
	@JoinColumn(name = "idusuario_notificador", referencedColumnName = "idusuario")
	private Usuario usuarioNotificador;
	
	@Column(name = "tipo_notificacao")
	@Enumerated(EnumType.ORDINAL)
	private TipoNotificacaoEnum tipoNotificacao;
	
	@Column(name = "id_objeto")
	private Long idObjeto;

	@Override
	public Serializable getId() {
		return id;
	}

	public Date getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public boolean isLida() {
		return lida;
	}

	public void setLida(boolean lida) {
		this.lida = lida;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public Usuario getUsuarioNotificado() {
		return usuarioNotificado;
	}

	public void setUsuarioNotificado(Usuario usuarioNotificado) {
		this.usuarioNotificado = usuarioNotificado;
	}

	public Usuario getUsuarioNotificador() {
		return usuarioNotificador;
	}

	public void setUsuarioNotificador(Usuario usuarioNotificador) {
		this.usuarioNotificador = usuarioNotificador;
	}

	public TipoNotificacaoEnum getTipoNotificacao() {
		return tipoNotificacao;
	}

	public void setTipoNotificacao(TipoNotificacaoEnum tipoNotificacao) {
		this.tipoNotificacao = tipoNotificacao;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdObjeto() {
		return idObjeto;
	}

	public void setIdObjeto(Long idObjeto) {
		this.idObjeto = idObjeto;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((dataCriacao == null) ? 0 : dataCriacao.hashCode());
		result = prime * result + (lida ? 1231 : 1237);
		result = prime * result
				+ ((mensagem == null) ? 0 : mensagem.hashCode());
		result = prime * result
				+ ((tipoNotificacao == null) ? 0 : tipoNotificacao.hashCode());
		result = prime
				* result
				+ ((usuarioNotificado == null) ? 0 : usuarioNotificado
						.hashCode());
		result = prime
				* result
				+ ((usuarioNotificador == null) ? 0 : usuarioNotificador
						.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Notificacao other = (Notificacao) obj;
		if (dataCriacao == null) {
			if (other.dataCriacao != null)
				return false;
		} else if (!dataCriacao.equals(other.dataCriacao))
			return false;
		if (lida != other.lida)
			return false;
		if (mensagem == null) {
			if (other.mensagem != null)
				return false;
		} else if (!mensagem.equals(other.mensagem))
			return false;
		if (tipoNotificacao != other.tipoNotificacao)
			return false;
		if (usuarioNotificado == null) {
			if (other.usuarioNotificado != null)
				return false;
		} else if (!usuarioNotificado.equals(other.usuarioNotificado))
			return false;
		if (usuarioNotificador == null) {
			if (other.usuarioNotificador != null)
				return false;
		} else if (!usuarioNotificador.equals(other.usuarioNotificador))
			return false;
		return true;
	}
	
}
