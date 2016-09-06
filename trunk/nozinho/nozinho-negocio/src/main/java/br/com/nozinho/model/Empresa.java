package br.com.nozinho.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "empresa")
public class Empresa extends Entidade{

	/**
	 * 
	 */
	private static final long serialVersionUID = 576138872084767942L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idempresa")
	private Long id;
	
	@Column(name = "nome_empresa")
	private String nome;
	
	@Column(name = "endereco_empresa")
	private String endereco;
	
	@Column(name = "numero_empresa")
	private Long numero;
	
	@Column(name = "complemento_empresa")
	private String complemento;
	
	@Column(name = "bairro_empresa")
	private String bairro;
	
	@Column(name = "cep_empresa")
	private String cep;
	
	@Column(name = "ddd_empresa", length = 2)
	private String dddTelefone;

	@Column(name = "telefone_empresa", length = 9)
	private String telefone;
	
	@Column(name = "status_empresa")
	private boolean status;
	
	@Column(name = "ddd1_empresa", length = 2)
	private String dddCelular;
	
	@Column(name = "celular_empresa", length = 9)
	private String celular;
	
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

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public Long getNumero() {
		return numero;
	}

	public void setNumero(Long numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getDddTelefone() {
		return dddTelefone;
	}

	public void setDddTelefone(String dddTelefone) {
		this.dddTelefone = dddTelefone;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getDddCelular() {
		return dddCelular;
	}

	public void setDddCelular(String dddCelular) {
		this.dddCelular = dddCelular;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
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
		Empresa other = (Empresa) obj;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Empresa [id=" + id + ", nome=" + nome + "]";
	}

}
