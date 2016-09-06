package br.com.nozinho.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "permissao")
@NamedQueries({
	@NamedQuery(name = "Permissao.findPermissaoExceptAdm", query = "SELECT p FROM Permissao p WHERE p.valor <> 'ADM'")
})
public class Permissao extends Entidade {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3783190241259240384L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idpermissao")
	private Long id;
	
	@Column(name = "nome_permissao")
	private String nome;
	
	@Column(name = "valor_permissao", length = 4)
	private String valor;

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

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((valor == null) ? 0 : valor.hashCode());
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
		Permissao other = (Permissao) obj;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (valor == null) {
			if (other.valor != null)
				return false;
		} else if (!valor.equals(other.valor))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Permissao [id=" + id + ", nome=" + nome + ", valor=" + valor + "]";
	}

}
