package br.com.nozinho.model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.Version;


/**
 * Classe Entidade Base
 * @author RodrigoAndrade
 */
@MappedSuperclass
@SuppressWarnings("serial")
public abstract class Entidade implements Serializable {

//	@Version
//	private Long version;
//
//	private String usuarioInclusao;
//
//	private String usuarioAlteracao;
//
//	@Temporal(TemporalType.DATE)
//	private Date horarioInclusao;
//
//	@Temporal(TemporalType.DATE)
//	private Date horarioAlteracao;

	@Transient
	public abstract Serializable getId();

//	public Long getVersion() {
//		return version;
//	}
//
//	public void setVersion(Long version) {
//		this.version = version;
//	}
//
//	public String getUsuarioInclusao() {
//		return this.usuarioInclusao;
//	}
//
//	public void setUsuarioInclusao(String usuarioInclusao) {
//		this.usuarioInclusao = usuarioInclusao;
//	}
//
//	public String getUsuarioAlteracao() {
//		return this.usuarioAlteracao;
//	}
//
//	public void setUsuarioAlteracao(String usuarioAlteracao) {
//		this.usuarioAlteracao = usuarioAlteracao;
//	}
//
//	public Date getHorarioInclusao() {
//		return this.horarioInclusao != null ? (Date) new Date(this.horarioInclusao.getTime()).clone() : null;
//	}
//
//	public void setHorarioInclusao(Date horarioInclusao) {
//		this.horarioInclusao = horarioInclusao != null ? (Date) new Date(horarioInclusao.getTime()).clone() : null;
//	}
//
//	public Date getHorarioAlteracao() {
//		return this.horarioAlteracao != null ? (Date) new Date(this.horarioAlteracao.getTime()).clone() : null;
//	}
//
//	public void setHorarioAlteracao(Date horarioAlteracao) {
//		this.horarioAlteracao = horarioAlteracao != null ? (Date) new Date(horarioAlteracao.getTime()).clone() : null;
//	}
//
//	/**
//	 * Insere a Data/Hora atual do sistema.
//	 */
//	@PrePersist
//	public void preencheDadosInsercao() {
//		this.horarioInclusao = Calendar.getInstance().getTime();
//	}
//
//	/**
//	 * Insere a Data/Hora atual do sistema.
//	 */
//	@PreUpdate
//	public void preencheDadosAtualizacao() {
//		this.horarioAlteracao = Calendar.getInstance().getTime();
//	}
//
//	@Override
//	public String toString() {
//		StringBuilder builder = new StringBuilder();
//		builder.append("Entidade [version=");
//		builder.append(version);
//		builder.append(", usuarioInclusao=");
//		builder.append(usuarioInclusao);
//		builder.append(", usuarioAlteracao=");
//		builder.append(usuarioAlteracao);
//		builder.append(", horarioInclusao=");
//		builder.append(horarioInclusao);
//		builder.append(", horarioAlteracao=");
//		builder.append(horarioAlteracao);
//		builder.append("]");
//		return builder.toString();
//	}
//
//	@Override
//	public int hashCode() {
//		final int prime = 31;
//		int result = 1;
//		result = prime * result + ((horarioAlteracao == null) ? 0 : horarioAlteracao.hashCode());
//		result = prime * result + ((horarioInclusao == null) ? 0 : horarioInclusao.hashCode());
//		result = prime * result + ((usuarioAlteracao == null) ? 0 : usuarioAlteracao.hashCode());
//		result = prime * result + ((usuarioInclusao == null) ? 0 : usuarioInclusao.hashCode());
//		result = prime * result + ((version == null) ? 0 : version.hashCode());
//		return result;
//	}
//
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Entidade)) {
			return false;
		}
		return true;
	}

}