package br.com.nozinho.web.controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

@ManagedBean
@RequestScoped
public class MenuController {		
	public String getLink(){
		HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		String url = req.getRequestURL().toString();
		if(url.contains("web")){
			return "../index.xhtml?faces-redirect=true";
		}
		return "";
	}
		
	public String getHomeClass(){
		HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		String url = req.getRequestURL().toString();
		if(!url.contains("web")){
			return "header";
		}
		return "";
	}
}
