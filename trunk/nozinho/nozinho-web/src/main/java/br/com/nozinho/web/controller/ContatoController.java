package br.com.nozinho.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.primefaces.context.RequestContext;

import br.com.nozinho.ejb.service.ContatoService;
import br.com.nozinho.ejb.service.GenericService;
import br.com.nozinho.model.Contato;
import br.com.nozinho.model.ContatoEndereco;
import br.com.nozinho.model.ContatoForma;
import br.com.nozinho.model.FormacaoIntelectual;
import br.com.nozinho.model.Partido;
import br.com.nozinho.model.Profissao;
import br.com.nozinho.model.SexoEnum;
import br.com.nozinho.model.TipoPessoaEnum;
import br.com.nozinho.web.callers.ContatoCaller;
import br.com.nozinho.web.callers.FormacaoIntelectualCaller;
import br.com.nozinho.web.callers.PartidoCaller;
import br.com.nozinho.web.callers.ProfissaoCaller;
import br.com.nozinho.web.framwork.impl.GenericControllerImpl;
import br.com.nozinho.web.messages.MessagesUtil;

@ManagedBean
@ViewScoped
public class ContatoController extends GenericControllerImpl<Contato, Long> implements PartidoCaller, FormacaoIntelectualCaller, ProfissaoCaller{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1464445554765444060L;

	private static final String REDIRECT_TO = "cadastro-contatos.xhtml?redirect=true";
	
	private Map<Long, Boolean> checked = new HashMap<Long, Boolean>();
	
	private String tipoTelefone = "F";
	
	private ContatoCaller caller;
	
	@EJB
	private ContatoService service;
	
	@ManagedProperty(value = "#{contatoEnderecoController}")
	private ContatoEnderecoController enderecoController;
	
	@ManagedProperty(value = "#{contatoFormaController}")
	private ContatoFormaController formaController;
	
	private String property;
	
	private String update;
	
	public ContatoEnderecoController getEnderecoController() {
		return enderecoController;
	}

	public void setEnderecoController(ContatoEnderecoController enderecoController) {
		this.enderecoController = enderecoController;
	}

	public ContatoFormaController getFormaController() {
		return formaController;
	}

	public void setFormaController(ContatoFormaController formaController) {
		this.formaController = formaController;
	}

	@Override
	protected GenericService<Contato, Long> getService() {
		return service;
	}
	
	public String processaEntidade(){
		saveUpdate();
		this.setEntidade(new Contato());
		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('processModalContato').hide()");
		context.update("list-contatos");
		return REDIRECT_TO;
	}
	
	private Contato saveUpdate(){
		if(getEntidade().getId() == null){
			return this.salvar();
		} else {
			return this.merge();
		}
	}
	
	public String processaEntidadeAtalho(){
		if(property == null){
			caller.setContato(saveUpdate());
		} else {
			caller.setContato(saveUpdate(), property);
		}
		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('processModalContato').hide()");
		if(update != null){
			context.update(update);
		}
		return "";
	}
	
	public void prepareUpdate(ActionEvent e){
		e.getSource();
	}
	
	public void prepareInclude(ActionEvent e){
		setEntidade(new Contato());
	}
	
	public String confirm(){
		if(checked.size() == 0){
			MessagesUtil.addErrorMessage("message.delete.empty");
			return "";
		}
		List<Contato> listIterator = new ArrayList<Contato>(getEntidades());
		for(Contato e : listIterator){
			if(checked.get(e.getId()) != null && checked.get(e.getId())){
				this.getEntidades().remove(e);
				this.setEntidade(e);
				this.remover();
			}
		}
		checked.clear();
		setEntidade(new Contato());
		return REDIRECT_TO;
	}
	
	public String processaFormaContato(){
		ContatoForma contatoForma = formaController.getEntidade();
		contatoForma.setContato(this.getEntidade());
		getEntidade().getContatosForma().add(contatoForma);
		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('contatoModal').hide()");
		return "";
	}
	
	public String processaContatoEndereco(){
		ContatoEndereco endereco = enderecoController.getEntidade();
		endereco.setContato(this.getEntidade());
		getEntidade().getEnderecos().add(endereco);
		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('enderecoModal').hide()");
		return "";
	}
	public void deleteEndereco(ActionEvent e){
		Set<ContatoEndereco> listIteration = new HashSet<ContatoEndereco>(getEntidade().getEnderecos());
		for(ContatoEndereco endereco : listIteration){
			if(endereco.equals(enderecoController.getEntidade())){
				getEntidade().getEnderecos().remove(endereco);
				endereco.setContato(null);
			}
		}
	}
	
	public void deleteContato(ActionEvent e){
		Set<ContatoForma> listIteration = new HashSet<ContatoForma>(getEntidade().getContatosForma());
		for(ContatoForma contato : listIteration){
			if(contato.equals(formaController.getEntidade())){
				getEntidade().getContatosForma().remove(contato);
				contato.setContato(null);
			}
		}
	}

	public Map<Long, Boolean> getChecked() {
		return checked;
	}

	public void setChecked(Map<Long, Boolean> checked) {
		this.checked = checked;
	}	

	public SexoEnum[] getListaSexo(){
		return SexoEnum.values();
	}

	public String getTipoTelefone() {
		return tipoTelefone;
	}

	public void setTipoTelefone(String tipoTelefone) {
		this.tipoTelefone = tipoTelefone;
	}

	@Override
	public List<Contato> getEntidades() {
		if(this.entidades == null){
			this.entidades = service.findAllFetchAll();
		}
		return this.entidades;
	}
	
	public TipoPessoaEnum[] getTiposPessoa(){
		return TipoPessoaEnum.values();
	}
	
	public List<Contato> findContatosByQuery(String query){
		return service.findByNameLike(query);
	}
	
	public List<Contato> findContatosPFByQuery(String query){
		return service.findPFByName(query);
	}
	
	public List<Contato> findContatosPJByQuery(String query){
		return service.findPJByName(query);
	}

	public ContatoCaller getCaller() {
		return caller;
	}

	public void setCaller(ContatoCaller caller) {
		this.caller = caller;
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	public String getUpdate() {
		return update;
	}

	public void setUpdate(String update) {
		this.update = update;
	}

	@Override
	public void setProfissao(Profissao profissao) {
		getEntidade().setProfissao(profissao);
	}

	@Override
	public void setFormacaoIntelectual(FormacaoIntelectual formacaoIntelectual) {
		getEntidade().setFormacaoIntelectual(formacaoIntelectual);
	}

	@Override
	public void setPartido(Partido partido) {
		getEntidade().setPartido(partido);
	}
	
}
