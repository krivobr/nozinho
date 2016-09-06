package br.com.nozinho.model;

public enum FormaEncaminhamentoEnum {
	
	OFICIO_CORRESPONDENCIA("Ofício/Correspondência"), EMAIL("E-mail"), TELEFONE_CONTATO_INTERNO("Telefone/ Contato pessoal gabinete");
	
	private String descricao;
	
	private FormaEncaminhamentoEnum(String descricao){
		this.descricao = descricao;
	}
	
	public String getDescricao(){
		return this.descricao;
	}

}
