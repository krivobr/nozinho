package br.com.nozinho.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class EventoAgenda implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6903712594642410309L;

	private String id;
	
	private String titulo;
	
	private Date dataInicio;
	
	private Date dataTermino;
	
	private String local;
	
	private String descricao;
	
	private List<Contato> convidados;
	
	private boolean diaTodo;
	
	public EventoAgenda(){
		convidados = new ArrayList<>();
		dataInicio = Calendar.getInstance().getTime();
		Calendar dataTerminoCalendar = Calendar.getInstance();
		dataTerminoCalendar.add(Calendar.HOUR, 1);
		dataTermino = dataTerminoCalendar.getTime();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataTermino() {
		return dataTermino;
	}

	public void setDataTermino(Date dataTermino) {
		this.dataTermino = dataTermino;
	}

	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public List<Contato> getConvidados() {
		return convidados;
	}

	public void setConvidados(List<Contato> convidados) {
		this.convidados = convidados;
	}

	public boolean isDiaTodo() {
		return diaTodo;
	}

	public void setDiaTodo(boolean diaTodo) {
		this.diaTodo = diaTodo;
	}
		
}
