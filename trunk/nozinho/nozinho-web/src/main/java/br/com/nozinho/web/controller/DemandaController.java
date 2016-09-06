package br.com.nozinho.web.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ComponentSystemEvent;

import org.apache.commons.lang.NotImplementedException;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;

import br.com.nozinho.ejb.service.DemandaService;
import br.com.nozinho.ejb.service.DemandaStatusService;
import br.com.nozinho.ejb.service.GenericService;
import br.com.nozinho.ejb.service.NotificacaoService;
import br.com.nozinho.model.Contato;
import br.com.nozinho.model.Demanda;
import br.com.nozinho.model.DemandaAnexo;
import br.com.nozinho.model.DemandaHistorico;
import br.com.nozinho.model.DemandaStatus;
import br.com.nozinho.model.FormaEncaminhamentoEnum;
import br.com.nozinho.model.Notificacao;
import br.com.nozinho.model.Oficio;
import br.com.nozinho.model.PrioridadeEnum;
import br.com.nozinho.model.SituacaoAtendimentoEnum;
import br.com.nozinho.model.TipoNotificacaoEnum;
import br.com.nozinho.model.Usuario;
import br.com.nozinho.web.callers.ContatoCaller;
import br.com.nozinho.web.callers.OficioCaller;
import br.com.nozinho.web.callers.UsuarioCaller;
import br.com.nozinho.web.framwork.impl.GenericControllerImpl;
import br.com.nozinho.web.messages.MessagesUtil;

@ManagedBean
@ViewScoped
public class DemandaController extends GenericControllerImpl<Demanda, Long> implements OficioCaller, ContatoCaller, UsuarioCaller{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1681537711350453284L;
	
	
	@EJB
	private DemandaService service;
	
	@EJB
	private DemandaStatusService statusService;
	
	@EJB
	private NotificacaoService notificacaoService;
	
	private static final String REDIRECT_TO = "list-demandas.xhtml?redirect=true";
	
	private Map<Long, Boolean> checked = new HashMap<Long, Boolean>();
	
	private boolean evento;
	
	private DemandaHistorico demandaHistorico;
	
	private List<Demanda> filteredObjects;
	
	private Usuario responsavelAntigo;
	
	
	@ManagedProperty(value = "#{loginController}")
	private LoginController loginController;
	
	@PostConstruct
	public void init(){
		this.demandaHistorico = new DemandaHistorico();
		setEntidades(service.findAllFetchContatoInteressado());
	}
	
	@Override
	protected GenericService<Demanda, Long> getService() {
		return service;
	}
	
	public void setLoginController(LoginController loginController){
		this.loginController = loginController;
	}
	
	public String processaEntidade(){
		getEntidade().getEncaminhamentoResposta().setDemanda(getEntidade());
		getEntidade().getRetornoSolicitacao().setDemanda(getEntidade());
		getEntidade().getProvidenciaAdotada().setDemanda(getEntidade());
		demandaHistorico.setDataHistorico(new Date());
		demandaHistorico.setDemanda(getEntidade());
		getEntidade().getHistorico().add(demandaHistorico);
		if(getEntidade().getId() == null){
			getEntidade().setData(new Date());
			Demanda demanda = this.salvar();
			Notificacao notificacao = notificacaoService.createNotificacao(demanda.getUsuarioResponsavel(), loginController.getEntidade(), TipoNotificacaoEnum.DEMANDA, (Long) demanda.getId(), demanda.getNumero());
			notificacaoService.salvar(notificacao);
		} else {
			Demanda demanda = this.merge();
			if(!demanda.getUsuarioResponsavel().equals(responsavelAntigo)){
				Notificacao notificacao = notificacaoService.createNotificacao(demanda.getUsuarioResponsavel(), loginController.getEntidade(), TipoNotificacaoEnum.DEMANDA, (Long) demanda.getId(), demanda.getNumero());
				notificacaoService.salvar(notificacao);
			}
		}
		this.setEntidade(new Demanda());
		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('processModal').hide()");
		context.update("appForm");
		return REDIRECT_TO;
	}
	
	public void prepareUpdate(ActionEvent e){
		
		if(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("idDemanda") != null){
			Long id = new Long(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("idDemanda"));
			setEntidade(service.findByIdFetchHistorico(id));
			responsavelAntigo = getEntidade().getUsuarioResponsavel();
		}
	}
	
	public void prepareInclude(ActionEvent e){
		setEntidade(new Demanda());
		getEntidade().setNumero(service.getSugestaoNumeroDemanda());
	}
	
	public String confirm(){
		if(checked.size() == 0){
			MessagesUtil.addErrorMessage("message.delete.empty");
			return "";
		}
		List<Demanda> listIterator = new ArrayList<Demanda>(getEntidades());
		for(Demanda e : listIterator){
			if(checked.get(e.getId()) != null && checked.get(e.getId())){
				this.getEntidades().remove(e);
				this.setEntidade(e);
				this.remover();
			}
		}
		checked.clear();
		setEntidade(new Demanda());
		return REDIRECT_TO;
	}

	public Map<Long, Boolean> getChecked() {
		return checked;
	}

	public void setChecked(Map<Long, Boolean> checked) {
		this.checked = checked;
	}	

	public PrioridadeEnum[] getListaPrioridade(){
		return PrioridadeEnum.values();
	}
	
	public List<DemandaStatus> getListaStatus(){
		return statusService.recuperarTodos();
	}

	
	public FormaEncaminhamentoEnum[] getFormasEncaminhamento(){
		return FormaEncaminhamentoEnum.values();
	}

	public boolean isEvento() {
		return evento;
	}

	public void setEvento(boolean evento) {
		this.evento = evento;
	}

	public DemandaHistorico getDemandaHistorico() {
		return demandaHistorico;
	}

	public void setDemandaHistorico(DemandaHistorico demandaHistorico) {
		this.demandaHistorico = demandaHistorico;
	}

	public SituacaoAtendimentoEnum[] getSituacaoAtendimento(){
		return SituacaoAtendimentoEnum.values();
	}

	public void handleFileUpload(FileUploadEvent event){
		if(getEntidade().getDocumento() == null){
			DemandaAnexo anexo = new DemandaAnexo();
			getEntidade().setDocumento(anexo);
		}
		getEntidade().getDocumento().setDataAnexo(new Date());
		getEntidade().getDocumento().setDemanda(getEntidade());
		getEntidade().getDocumento().setUsuario(loginController.getEntidade());
		getEntidade().getDocumento().setDocumento(event.getFile().getContents());
	}
	
	public List<Demanda> getFilteredObjects() {
		return filteredObjects;
	}

	public void setFilteredObjects(List<Demanda> filteredObjects) {
		this.filteredObjects = filteredObjects;
	}

	@Override
	public void setOficio(Oficio oficio) {
		throw new NotImplementedException();
	}

	@Override
	public void setOficio(Oficio oficio, String property) {
		switch(property){
			case "oficio":
				this.getEntidade().setOficio(oficio);
				break;
			case "oficioProvidencia":
				this.getEntidade().getProvidenciaAdotada().setOficio(oficio);
				break;
			case "oficioRetorno":
				this.getEntidade().getRetornoSolicitacao().setOficio(oficio);
				break;
			case "oficioEncaminhamento":
				this.getEntidade().getEncaminhamentoResposta().setOficio(oficio);
				break;
		}
	}

	@Override
	public void setContato(Contato contato) {
		throw new NotImplementedException();		
	}

	@Override
	public void setContato(Contato contato, String property) {
		switch(property){
			case "encaminhadoPor":
				this.getEntidade().setContato(contato);
				break;
			case "beneficiarioAtendimento":
				this.getEntidade().setContatoInteressado(contato);
				break;
			case "encaminhadoPara":
				this.getEntidade().getProvidenciaAdotada().setEncaminhadoPara(contato);
				break;
			case "encaminhadoPara2":
				this.getEntidade().getEncaminhamentoResposta().setEcaminhadoPara(contato);
				break;
		}
		
	}

	@Override
	public void setUsuario(Usuario usuario) {
		this.getEntidade().setUsuarioResponsavel(usuario);
	}

	public void isNotificacao(ComponentSystemEvent event){
		String idNotificacao = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("idDemanda");
		if(idNotificacao != null){
			setEntidade(service.findByIdFetchHistorico(Long.parseLong(idNotificacao)));
			RequestContext context = RequestContext.getCurrentInstance();
			context.execute("PF('processModal').show()");
			context.update("panelModalDemanda");
		}
	}
}
