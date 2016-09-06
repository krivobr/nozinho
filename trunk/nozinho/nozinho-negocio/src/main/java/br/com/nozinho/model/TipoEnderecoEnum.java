package br.com.nozinho.model;

public enum TipoEnderecoEnum {
	
	PRINCIPAL("Principal"), RESIDENCIAL("Residencial"),OUTROS("Outros");
	
	private String descricao;
	
	private TipoEnderecoEnum(String descricao){
		this.descricao = descricao;
	}
	
	public String getDescricao(){
		return this.descricao;
	}

}
