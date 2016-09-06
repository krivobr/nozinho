package br.com.nozinho.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "contato_forma")
public class ContatoForma extends Entidade{

	/**
	 * 
	 */
	private static final long serialVersionUID = 937443600262701754L;
	
	@Id
	@Column(name = "idcontato_forma")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "idcontato")
	private Contato contato;
	
	@Column(name = "tipo_contato_forma")
	@Enumerated(EnumType.ORDINAL)
	private FormaContatoEnum tipoContato;
	
	@Column(name = "contato_forma")
	private String nome;
	
	public Contato getContato() {
		return contato;
	}

	public void setContato(Contato contato) {
		this.contato = contato;
	}

	public FormaContatoEnum getTipoContato() {
		return tipoContato;
	}

	public void setTipoContato(FormaContatoEnum tipoContato) {
		this.tipoContato = tipoContato;
	}

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
		int result = 1;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result
				+ ((tipoContato == null) ? 0 : tipoContato.hashCode());
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
		ContatoForma other = (ContatoForma) obj;
		if (contato == null) {
			if (other.contato != null)
				return false;
		} else if (!contato.equals(other.contato))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (tipoContato != other.tipoContato)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ContatoForma [id=" + id + ", contato=" + contato
				+ ", tipoContato=" + tipoContato + ", nome=" + nome + "]";
	}
}
