package br.com.nozinho.model;

public enum FormaContatoEnum {
	
	EMAIL("Email"), TELEFONE("Telefone"), REDE_SOCIAL("Rede Social"), OUTROS("Outros");
	
	private String descricao;
	
	private FormaContatoEnum(String descricao){
		this.descricao = descricao;
	}

	public String getDescricao(){
		return descricao;
	}
}
