package br.com.nozinho.model;

public enum PrioridadeEnum {
	
	NENHUMA("Nenhuma", 0),
	BAIXA("Baixa", 1),
	NORMAL("Normal", 2),
	ALTA("Alta", 3),
	URGENTE("Urgente", 4),
	IMEDIATA("Imediata", 5);		
	
	private Integer peso;
	private String descricao;
	
	private PrioridadeEnum(String descricao, Integer peso){
		this.descricao = descricao;
		this.peso = peso;
	}
	
	public Integer getPeso(){
		return peso;
	}
	
	public String getDescricao(){
		return descricao;
	}

}
