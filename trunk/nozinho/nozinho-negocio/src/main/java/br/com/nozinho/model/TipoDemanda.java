package br.com.nozinho.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tipo_demanda")
public class TipoDemanda extends Entidade{

	/**
	 * 
	 */
	private static final long serialVersionUID = 9187350858873384452L;
	
	
	public TipoDemanda(){
		super();
	}
	
	public TipoDemanda(Long id){
		this.id = id;
	}
	
	@Id
	@Column(name = "idtipo_demanda")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "nome_tipo_demanda")
	private String nome;
	
	@Column
	private Integer status;		

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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
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
		result = prime * result + ((status == null) ? 0 : status.hashCode());
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
		TipoDemanda other = (TipoDemanda) obj;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "TipoDemanda [id=" + id + ", nome=" + nome + ", status="
				+ status + "]";
	}
	
}
