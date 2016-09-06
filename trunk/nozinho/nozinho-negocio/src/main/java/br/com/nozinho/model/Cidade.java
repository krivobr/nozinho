package br.com.nozinho.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "cidade")
@NamedQueries({
	@NamedQuery(name = "Cidade.findByNomeLike", query = "SELECT c FROM Cidade c WHERE c.nome LIKE :nome")
})
public class Cidade extends Entidade {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9213656727069144054L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idcidade")
	private Long id;
	
	@Column(name = "nome_cidade")
	private String nome;
	
	@ManyToOne(optional=false)
	@JoinColumn(name = "iduf", referencedColumnName="iduf")
	private Uf uf;
	
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

	public Uf getUf() {
		return uf;
	}

	public void setUf(Uf uf) {
		this.uf = uf;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((uf == null) ? 0 : uf.hashCode());
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
		Cidade other = (Cidade) obj;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (uf == null) {
			if (other.uf != null)
				return false;
		} else if (!uf.equals(other.uf))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Cidade [id=" + id + ", nome=" + nome + ", uf=" + uf + "]";
	}
	
}
