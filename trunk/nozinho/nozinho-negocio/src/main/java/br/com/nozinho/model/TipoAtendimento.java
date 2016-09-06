package br.com.nozinho.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tipo_atendimento")
public class TipoAtendimento extends Entidade {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9204295095156454333L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idtipo_atendimento")
	private Long id;
	
	@Column(name = "nome_tipo_atendimento")
	private String nome;
	
	@Column(name = "status")
	private boolean status;
	
	@Override
	public Serializable getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
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
		TipoAtendimento other = (TipoAtendimento) obj;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "TipoAtendimento [id=" + id + ", nome=" + nome + ", status="
				+ status + "]";
	}

}
