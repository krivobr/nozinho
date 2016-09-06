package br.com.nozinho.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "orgao_entidade")
@NamedQueries({
	@NamedQuery(name = "OrgaoEntidade.findByNameLike", query = "SELECT o FROM OrgaoEntidade o WHERE o.nome like :nome")
})
public class OrgaoEntidade extends Entidade{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6145297486147136453L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "idorgao_entidade")
	private Long id;
	
	@Column(name = "nome_orgao_entidade", unique = true)
	private String nome;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
		OrgaoEntidade other = (OrgaoEntidade) obj;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "OrgaoEntidade [id=" + id + ", nome=" + nome + "]";
	}
	
	
}
