package br.com.nozinho.model;

public enum SexoEnum {
	M("M", "Masculino"),F("F", "Feminino");
	
	private String id;
	private String descricao;
	
	private SexoEnum(String id, String descricao){
		this.id = id;
		this.descricao = descricao;
	}
	
	public String getId(){
		return this.id;
	}
	
	public String getDescricao(){
		return this.descricao;
	}
	
}
