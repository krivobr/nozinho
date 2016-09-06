package br.com.nozinho.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "demanda_status")
public class DemandaStatus extends Entidade {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8196194283467089078L;

	@Id
	@Column(name = "iddemanda_status")
	private Long id;
	
	@Column(name = "demanda_status")
	private String descricao;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((descricao == null) ? 0 : descricao.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DemandaStatus other = (DemandaStatus) obj;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "StatusDemanda [id=" + id + ", descricao=" + descricao + "]";
	}

}
