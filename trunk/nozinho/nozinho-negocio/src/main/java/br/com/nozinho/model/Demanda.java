package br.com.nozinho.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "demanda")
@NamedQueries({
	@NamedQuery(name = "Demanda.findAllFetchContatoInteressado", query = "SELECT d,ci FROM Demanda d INNER JOIN d.contatoInteressado ci JOIN FETCH ci.enderecos"),
	@NamedQuery(name = "Demanda.findByIdFetchHistorico", query = "SELECT DISTINCT d FROM Demanda d JOIN Fetch d.historico WHERE d.id = :id"),
	@NamedQuery(name = "Demanda.findMaxId", query = "SELECT (max(d.id) + 1) FROM Demanda d")
})
public class Demanda extends Entidade{

	/**
	 * 
	 */
	private static final long serialVersionUID = -47752880593741707L;
	
	@Id
	@Column(name = "iddemanda")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "numero_demanda")
	private String numero;
	
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date data;
	
	@Column(name = "data_limite")
	@Temporal(TemporalType.DATE)
	private Date dataLimite;
	
	@Column
	private String assunto;
	
	@Column(columnDefinition="Decimal(10,2)")
	private Double valor;
	
	@Column(columnDefinition="Decimal(10,2)", name = "valor_agendamento")
	private Double valorAgendamento;
	
	@Column(name = "data_encerramento")	
	private Date dataEncerramento;
	
	@ManyToOne
	@JoinColumn(name = "iddemanda_status")
	private DemandaStatus status;	
	
	@ManyToOne
	@JoinColumn(name = "idempresa")
	private Empresa empresa;
	
	@ManyToOne
	@JoinColumn(name = "idcontato_resp", referencedColumnName = "idcontato")
	private Contato contatoResponsavel;
	
	@ManyToOne
	@JoinColumn(name = "idcontato")
	private Contato contato;
	
	@Column(name = "idtipo_atendimento")
	@Enumerated(EnumType.ORDINAL)
	private FormaEncaminhamentoEnum tipoAtendimento = FormaEncaminhamentoEnum.OFICIO_CORRESPONDENCIA;
	
	@ManyToOne
	@JoinColumn(name = "idorgao_entidade")
	private OrgaoEntidade orgaoEntidade;
	
	@ManyToOne
	@JoinColumn(name = "idtipo_demanda")
	private TipoDemanda tipoDemanda = new TipoDemanda(1l);
	
	@OneToMany(mappedBy = "demanda", cascade = CascadeType.ALL)
	private Set<DemandaHistorico> historico;
	
	@ManyToOne
	@JoinColumn(name = "idcontato_interessado", referencedColumnName = "idcontato")
	private Contato contatoInteressado;
	
	@Column(name = "agendamento")
	private boolean agendamento;
	
	@Column(name = "nome_clinica")
	private String nomeClinica;
	
	@Column(name = "situacao_atendimento")
	@Enumerated(EnumType.ORDINAL)
	private SituacaoAtendimentoEnum situacao;
	
	@Column(name = "prioridade_atendimento")
	@Enumerated(EnumType.ORDINAL)
	private PrioridadeEnum prioridade;

	@OneToOne(mappedBy = "demanda", cascade = CascadeType.ALL)
	private ProvidenciaAdotada providenciaAdotada;
	
	@OneToOne(mappedBy = "demanda", cascade = CascadeType.ALL)
	private RetornoSolicitacao retornoSolicitacao;
	
	@OneToOne(mappedBy = "demanda", cascade = CascadeType.ALL)
	private EncaminhamentoResposta encaminhamentoResposta;
	
	@Column(name = "evento")
	private boolean evento;
	
	@Column(name = "nome_evento")
	private String nomeEvento;
	
	@Column(name = "data_evento")
	@Temporal(TemporalType.DATE)
	private Date dataEvento;
	
	@OneToOne(mappedBy = "demanda", cascade = CascadeType.ALL)
	private DemandaAnexo documento;
	
	@ManyToOne
	@JoinColumn(name = "idcargo")
	private Cargo cargoPretendido;
	
	@ManyToOne
	@JoinColumn(name = "idusuario_responsavel", referencedColumnName = "idusuario")
	private Usuario usuarioResponsavel;
	
	@ManyToOne
	@JoinColumn(name = "idoficio")
	private Oficio oficio;
	
	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Date getDataLimite() {
		return dataLimite;
	}

	public void setDataLimite(Date dataLimite) {
		this.dataLimite = dataLimite;
	}

	public String getAssunto() {
		return assunto;
	}

	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}
	
	public Double getValorAgendamento() {
		return valorAgendamento;
	}

	public void setValorAgendamento(Double valorAgendamento) {
		this.valorAgendamento = valorAgendamento;
	}

	public Date getDataEncerramento() {
		return dataEncerramento;
	}

	public void setDataEncerramento(Date dataEncerramento) {
		this.dataEncerramento = dataEncerramento;
	}

	public DemandaStatus getStatus() {
		return status;
	}

	public void setStatus(DemandaStatus status) {
		this.status = status;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public Contato getContatoResponsavel() {
		return contatoResponsavel;
	}

	public void setContatoResponsavel(Contato contatoResponsavel) {
		this.contatoResponsavel = contatoResponsavel;
	}

	public Contato getContato() {
		return contato;
	}

	public void setContato(Contato contato) {
		this.contato = contato;
	}

	public FormaEncaminhamentoEnum getTipoAtendimento() {
		return tipoAtendimento;
	}

	public void setTipoAtendimento(FormaEncaminhamentoEnum tipoAtendimento) {
		this.tipoAtendimento = tipoAtendimento;
	}

	public OrgaoEntidade getOrgaoEntidade() {
		return orgaoEntidade;
	}

	public void setOrgaoEntidade(OrgaoEntidade orgaoEntidade) {
		this.orgaoEntidade = orgaoEntidade;
	}

	public TipoDemanda getTipoDemanda() {
		return tipoDemanda;
	}

	public void setTipoDemanda(TipoDemanda tipoDemanda) {
		this.tipoDemanda = tipoDemanda;
	}

	public Contato getContatoInteressado() {
		return contatoInteressado;
	}

	public void setContatoInteressado(Contato contatoInteressado) {
		this.contatoInteressado = contatoInteressado;
	}	
	
	public Set<DemandaHistorico> getHistorico() {
		if(historico == null){
			historico = new HashSet<DemandaHistorico>();
		}
		return historico;
	}

	public void setHistorico(Set<DemandaHistorico> historico) {
		this.historico = historico;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public Serializable getId() {
		return id;
	}

	public boolean isAgendamento() {
		return agendamento;
	}

	public void setAgendamento(boolean agendamento) {
		this.agendamento = agendamento;
	}

	public String getNomeClinica() {
		return nomeClinica;
	}

	public void setNomeClinica(String nomeClinica) {
		this.nomeClinica = nomeClinica;
	}

	public SituacaoAtendimentoEnum getSituacao() {
		return situacao;
	}

	public void setSituacao(SituacaoAtendimentoEnum situacao) {
		this.situacao = situacao;
	}
	
	public PrioridadeEnum getPrioridade() {
		return prioridade;
	}

	public void setPrioridade(PrioridadeEnum prioridade) {
		this.prioridade = prioridade;
	}

	public ProvidenciaAdotada getProvidenciaAdotada() {
		if(providenciaAdotada == null){
			providenciaAdotada = new ProvidenciaAdotada();
		}
		return providenciaAdotada;
	}

	public void setProvidenciaAdotada(ProvidenciaAdotada providenciaAdotada) {
		this.providenciaAdotada = providenciaAdotada;
	}

	public RetornoSolicitacao getRetornoSolicitacao() {
		if(retornoSolicitacao == null){
			retornoSolicitacao = new RetornoSolicitacao();
		}
		return retornoSolicitacao;
	}

	public void setRetornoSolicitacao(RetornoSolicitacao retornoSolicitacao) {
		this.retornoSolicitacao = retornoSolicitacao;
	}

	public EncaminhamentoResposta getEncaminhamentoResposta() {
		if(encaminhamentoResposta == null){
			encaminhamentoResposta = new EncaminhamentoResposta();
		}
		return encaminhamentoResposta;
	}

	public void setEncaminhamentoResposta(
			EncaminhamentoResposta encaminhamentoResposta) {
		this.encaminhamentoResposta = encaminhamentoResposta;
	}

	public boolean isEvento() {
		return evento;
	}

	public void setEvento(boolean evento) {
		this.evento = evento;
	}

	public String getNomeEvento() {
		return nomeEvento;
	}

	public void setNomeEvento(String nomeEvento) {
		this.nomeEvento = nomeEvento;
	}

	public Date getDataEvento() {
		return dataEvento;
	}

	public void setDataEvento(Date dataEvento) {
		this.dataEvento = dataEvento;
	}

	public DemandaAnexo getDocumento() {
		return documento;
	}

	public void setDocumento(DemandaAnexo documento) {
		this.documento = documento;
	}

	public Cargo getCargoPretendido() {
		return cargoPretendido;
	}

	public void setCargoPretendido(Cargo cargoPretendido) {
		this.cargoPretendido = cargoPretendido;
	}

	public Usuario getUsuarioResponsavel() {
		return usuarioResponsavel;
	}

	public void setUsuarioResponsavel(Usuario usuarioResponsavel) {
		this.usuarioResponsavel = usuarioResponsavel;
	}

	public Oficio getOficio() {
		return oficio;
	}

	public void setOficio(Oficio oficio) {
		this.oficio = oficio;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

}
