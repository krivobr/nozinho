package br.com.nozinho.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(name = "demanda_historico")
public class DemandaHistorico extends Entidade {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7779363761279757100L;

	@Id
	@Column(name = "iddemanda_historico")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "data_demanda_historico")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataHistorico;
	
	@Column(name = "desc_demanda_historico")
	private String descricao;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "iddemanda")
	private Demanda demanda;
	
	public Date getDataHistorico() {
		return dataHistorico;
	}

	public void setDataHistorico(Date dataHistorico) {
		this.dataHistorico = dataHistorico;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	@Transient
	public Serializable getId() {
		return id;
	}
	
	public Demanda getDemanda() {
		return demanda;
	}

	public void setDemanda(Demanda demanda) {
		this.demanda = demanda;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((descricao == null) ? 0 : descricao.hashCode());
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
		DemandaHistorico other = (DemandaHistorico) obj;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "DemandaHistorico [id=" + id + ", dataHistorico="
				+ dataHistorico + ", descricao=" + descricao + "]";
	}

}
