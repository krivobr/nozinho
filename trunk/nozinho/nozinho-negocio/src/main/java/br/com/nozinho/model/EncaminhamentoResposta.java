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
@Table(name = "encaminhamento_reposta")
public class EncaminhamentoResposta extends Entidade{

	/**
	 * 
	 */
	private static final long serialVersionUID = 161421213372514247L;

	@Id
	@Column(name = "idencaminhamento_resposta")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "idcontato")
	private Contato ecaminhadoPara;
	
	@Column(name = "data_encaminhamento")
	@Temporal(TemporalType.DATE)
	private Date dataEncaminhamento;
	
	@ManyToOne
	@JoinColumn(name = "idoficio")
	private Oficio oficio;
	
	@Column
	private String email;
	
	@Column
	private String telefone;
	
	@OneToOne(optional = false)
	@JoinColumn(name = "iddemanda")
	private Demanda demanda;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Contato getEcaminhadoPara() {
		return ecaminhadoPara;
	}

	public void setEcaminhadoPara(Contato ecaminhadoPara) {
		this.ecaminhadoPara = ecaminhadoPara;
	}

	public Date getDataEncaminhamento() {
		return dataEncaminhamento;
	}

	public void setDataEncaminhamento(Date dataEncaminhamento) {
		this.dataEncaminhamento = dataEncaminhamento;
	}

	public Oficio getOficio() {
		return oficio;
	}

	public void setOficio(Oficio oficio) {
		this.oficio = oficio;
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

	public Demanda getDemanda() {
		return demanda;
	}

	public void setDemanda(Demanda demanda) {
		this.demanda = demanda;
	}
	
}
