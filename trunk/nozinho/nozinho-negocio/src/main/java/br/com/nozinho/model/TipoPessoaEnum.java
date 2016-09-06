package br.com.nozinho.model;

public enum TipoPessoaEnum {
	PESSOA_FISICA("Pessoa Física"), PESSOA_JURIDICA("Pessoa Jurídica");
	
	private String descricao;
	
	private TipoPessoaEnum(String descricao){
		this.descricao = descricao;
	}
	
	public String getDescricao(){
		return this.descricao;
	}
}
