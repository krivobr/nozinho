package br.com.nozinho.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Entity;

@Entity
@Table(name = "partido")
@NamedQueries({
	@NamedQuery(name = "Partido.findBySiglaLike", query = "SELECT p FROM Partido p WHERE p.sigla LIKE :sigla")
})
public class Partido extends Entidade{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4953141906055189184L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idpartido")
	private Long id;
	
	@Column(name = "sigla_partido", length = 10)
	private String sigla;
	
	@Column(name = "nome_partido")
	private String nome;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((sigla == null) ? 0 : sigla.hashCode());
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
		Partido other = (Partido) obj;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (sigla == null) {
			if (other.sigla != null)
				return false;
		} else if (!sigla.equals(other.sigla))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Partido [id=" + id + ", sigla=" + sigla + ", nome=" + nome
				+ "]";
	}

}
