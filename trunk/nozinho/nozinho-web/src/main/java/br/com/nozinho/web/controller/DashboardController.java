package br.com.nozinho.web.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.nozinho.ejb.service.DashboardService;
import br.com.nozinho.model.Dashboard;
import br.com.nozinho.util.json.JsonUtil;

@ManagedBean
@ViewScoped
public class DashboardController implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5512316456718350539L;

	@EJB
	private DashboardService service;
	
	private Dashboard entidade;
	
	@PostConstruct
	private void init(){
		setEntidade(service.getDashBoard());
	}		

	public Dashboard getEntidade() {
		return entidade;
	}

	public void setEntidade(Dashboard entidade) {
		this.entidade = entidade;
	}

	public void updateDashBoard(){
		setEntidade(service.getDashBoard());
	}
	
	public String getSerialChart(){
		return JsonUtil.toJson(entidade.getSerialChart());
	}
	
	public String getPieChart(){
		return JsonUtil.toJson(entidade.getPieChart());
	}
}
