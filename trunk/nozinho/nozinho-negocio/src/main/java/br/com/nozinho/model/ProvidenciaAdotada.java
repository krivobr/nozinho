package br.com.nozinho.model;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "providencia_adotada")
public class ProvidenciaAdotada extends Entidade {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1185631916751062017L;

	@Id
	@Column(name = "idprovidencia_adotada")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "data_encaminhamento")
	@Temporal(TemporalType.DATE)
	private Date dataEncaminhamento;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "telefone")
	private String telefone;
	
	@ManyToOne
	@JoinColumn(name = "idoficio")
	private Oficio oficio;
	
	@ManyToOne
	@JoinColumn(name = "idcontato")
	private Contato encaminhadoPara;
	
	@OneToOne(optional = false)
	@JoinColumn(name = "iddemanda")
	private Demanda demanda;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDataEncaminhamento() {
		return dataEncaminhamento;
	}

	public void setDataEncaminhamento(Date dataEncaminhamento) {
		this.dataEncaminhamento = dataEncaminhamento;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public Oficio getOficio() {
		return oficio;
	}

	public void setOficio(Oficio oficio) {
		this.oficio = oficio;
	}

	public Contato getEncaminhadoPara() {
		return encaminhadoPara;
	}

	public void setEncaminhadoPara(Contato encaminhadoPara) {
		this.encaminhadoPara = encaminhadoPara;
	}

	public Demanda getDemanda() {
		return demanda;
	}

	public void setDemanda(Demanda demanda) {
		this.demanda = demanda;
	}
	
}
