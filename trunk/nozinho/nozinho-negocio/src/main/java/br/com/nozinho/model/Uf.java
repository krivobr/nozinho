package br.com.nozinho.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "uf")
public class Uf extends Entidade {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5219701530862574469L;
	
	@Id
	@Column(name = "iduf", length = 2, nullable=false)
	@NotNull
	@Size(min=2,max=2)
	private String id;

	@Column(name = "nome_estado_uf")
	private String nome;
	
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

	public void setId(String id) {
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
		Uf other = (Uf) obj;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Uf [id=" + id + ", nome=" + nome + "]";
	}
	
}
