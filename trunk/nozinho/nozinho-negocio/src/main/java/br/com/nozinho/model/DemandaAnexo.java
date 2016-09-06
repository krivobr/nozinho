package br.com.nozinho.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name = "demanda_anexo")
public class DemandaAnexo extends Entidade{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2253868388253511102L;
	
	@Id
	@Column(name = "id_demanda_anexo")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "data_anexo")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataAnexo;
	
	@Lob
	@Column(name = "documento_anexo")
	private byte[] documento;
	
	@OneToOne
	@JoinColumn(name = "iddemanda")
	private Demanda demanda;
	
	@ManyToOne
	@JoinColumn(name = "idusuario")
	private Usuario usuario;
	
	public Date getDataAnexo() {
		return dataAnexo;
	}

	public void setDataAnexo(Date dataAnexo) {
		this.dataAnexo = dataAnexo;
	}

	public Demanda getDemanda() {
		return demanda;
	}

	public void setDemanda(Demanda demanda) {
		this.demanda = demanda;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public Serializable getId() {
		return id;
	}

	public byte[] getDocumento() {
		return documento;
	}

	public void setDocumento(byte[] documento) {
		this.documento = documento;
	}
	
}
