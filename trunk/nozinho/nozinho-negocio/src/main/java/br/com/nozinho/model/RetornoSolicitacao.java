package br.com.nozinho.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "retorno_solicitacao")
public class RetornoSolicitacao extends Entidade{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1781324913540944927L;

	@Id
	@Column(name = "idretorno_solicitacao")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "idorgao_entidade")
	private OrgaoEntidade orgao;
	
	@ManyToOne
	@JoinColumn(name = "idoficio")
	private Oficio oficio;
	
	@Column
	private String email;
	
	@Column
	private String telefone;
	
	@Column(name = "situacao_atendimento")
	@Enumerated(EnumType.ORDINAL)
	private SituacaoAtendimentoEnum situacao;
	
	@OneToOne
	@JoinColumn(name = "iddemanda")
	private Demanda demanda;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public OrgaoEntidade getOrgao() {
		return orgao;
	}

	public void setOrgao(OrgaoEntidade orgao) {
		this.orgao = orgao;
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

	public SituacaoAtendimentoEnum getSituacao() {
		return situacao;
	}

	public void setSituacao(SituacaoAtendimentoEnum situacao) {
		this.situacao = situacao;
	}

	public Demanda getDemanda() {
		return demanda;
	}

	public void setDemanda(Demanda demanda) {
		this.demanda = demanda;
	}

}
