package br.com.nozinho.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "formacao_intelectual")
public class FormacaoIntelectual extends Entidade {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3833792028757919877L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idformacao_intelectual")
	private Long id;
	
	@Column(name = "nome_formacao_intelectual")
	private String nome;

	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public void setId(Long id) {
		this.id = id;
	}


	@Override
	public Serializable getId() {
		return id;
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
		FormacaoIntelectual other = (FormacaoIntelectual) obj;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "FormacaoIntelectual [id=" + id + ", nome=" + nome + "]";
	}

}
