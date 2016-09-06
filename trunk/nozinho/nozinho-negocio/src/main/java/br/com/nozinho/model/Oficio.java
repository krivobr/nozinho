package br.com.nozinho.model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(name = "oficio")
@NamedQueries({
	@NamedQuery(name = "Oficio.findByNumeroLike", query = "SELECT o FROM Oficio o WHERE o.numero like :numero"),
	@NamedQuery(name = "Oficio.findMaxId", query = "SELECT (max(o.id) + 1) FROM Oficio o")
})
public class Oficio extends Entidade{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6647156336939544913L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idoficio")
	private Long id;
	
	@Column(name = "numero_oficio", unique = true)
	private String numero;
	
	@Column(name = "oficio_interno")
	private boolean oficioInterno;
	
	@Column(name = "oficio_resposta")
	private boolean oficioResposta;
	
	@Column(name = "data_oficio")
	@Temporal(TemporalType.DATE)
	private Date dataOficio = new Date();
	
	@Column(name = "data_protocolo")
	@Temporal(TemporalType.DATE)
	private Date dataProtocolo;
	
	@Column(name = "data_recebimento")
	@Temporal(TemporalType.DATE)
	private Date dataRecebimento;
	
	@ManyToOne
	@JoinColumn(name = "idorgao_entidade")
	private OrgaoEntidade orgao;
	
	@Lob
	@Column(name = "documento_oficio")
	@Basic(fetch=FetchType.LAZY)
	private byte[] documento;
	
	@OneToOne(optional = true)
	@JoinColumn(name = "idoficio_pai", referencedColumnName = "idoficio")
	private Oficio oficio;
	
	@Transient
	private Integer diasProtocolada;
	
	public boolean isOficioInterno() {
		return oficioInterno;
	}

	public void setOficioInterno(boolean oficioInterno) {
		this.oficioInterno = oficioInterno;
	}		

	public boolean isOficioResposta() {
		return oficioResposta;
	}

	public void setOficioResposta(boolean oficioResposta) {
		this.oficioResposta = oficioResposta;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public Date getDataOficio() {
		return dataOficio;
	}

	public void setDataOficio(Date dataOficio) {
		this.dataOficio = dataOficio;
	}

	public Date getDataProtocolo() {
		return dataProtocolo;
	}

	public void setDataProtocolo(Date dataProtocolo) {
		this.dataProtocolo = dataProtocolo;
	}

	public Date getDataRecebimento() {
		return dataRecebimento;
	}

	public void setDataRecebimento(Date dataRecebimento) {
		this.dataRecebimento = dataRecebimento;
	}

	public OrgaoEntidade getOrgao() {
		return orgao;
	}

	public void setOrgao(OrgaoEntidade orgao) {
		this.orgao = orgao;
	}

	public byte[] getDocumento() {
		return documento;
	}

	public void setDocumento(byte[] documento) {
		this.documento = documento;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public Serializable getId() {
		return id;
	}

	public Oficio getOficio() {
		return oficio;
	}

	public void setOficio(Oficio oficio) {
		this.oficio = oficio;
	}

	public int getDiasProtocolada(){
		if(diasProtocolada == null && getDataRecebimento() == null){
			Calendar hoje = Calendar.getInstance();
			diasProtocolada = (int)((hoje.getTimeInMillis() - getDataProtocolo().getTime()) / (1000 * 60 * 60 * 24));
		}
		return diasProtocolada;
	}
}
