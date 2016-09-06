package br.com.nozinho.model;

public enum SituacaoAtendimentoEnum {
	
	ABERTA("Aberta"), 
	EM_ANDAMENTO("Em Andamento"), 
	ATENDIDO("Atendido"), 
	NAO_ATENDIDO("Não Atendido"), 
	ATENDIDO_PARCIALMENTE("Atendido Parcialmente");
	
	private String descricao;
	
	private SituacaoAtendimentoEnum(String descricao){
		this.descricao = descricao;
	}
	
	public String getDescricao(){
		return this.descricao;
	}
}
