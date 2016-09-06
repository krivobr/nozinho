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
@Table(name = "contato_endereco")
public class ContatoEndereco extends Entidade{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8053381221856809691L;
	
	@Id
	@Column(name = "idcontato_endereco")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "idcontato")
	private Contato contato;
	
	@ManyToOne
	@JoinColumn(name = "idcidade")
	private Cidade cidade;
	
	@Column
	private String endereco;
	
	@Column
	private String numero;
	
	@Column
	private String complemento;
	
	@Column
	private String bairro;
	
	@Column(length = 8)
	private String cep;
	
	@Column(name = "tipo_contato_endereco")
	@Enumerated(EnumType.ORDINAL)
	private TipoEnderecoEnum tipoEndereco;

	public Contato getContato() {
		return contato;
	}

	public void setContato(Contato contato) {
		this.contato = contato;
	}

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}
	
	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
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
	
	@Override
	public Serializable getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TipoEnderecoEnum getTipoEndereco() {
		return tipoEndereco;
	}

	public void setTipoEndereco(TipoEnderecoEnum tipoEndereco) {
		this.tipoEndereco = tipoEndereco;
	}
	
}
