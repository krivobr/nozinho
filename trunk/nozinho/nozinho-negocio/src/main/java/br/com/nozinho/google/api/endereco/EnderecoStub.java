package br.com.nozinho.google.api.endereco;

import java.io.Serializable;


public class EnderecoStub implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -844886309094824025L;
	private String id;
	private String description;
	private String reference;
	private TermsStub[] terms;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getReference() {
		return reference;
	}
	public void setReference(String reference) {
		this.reference = reference;
	}
	
	public TermsStub[] getTerms() {
		return terms;
	}
	public void setTerms(TermsStub[] terms) {
		this.terms = terms;
	}
	@Override
	public String toString() {
		return getDescription();
	}
	
	//Getters and setters of the terms
	
	private String getTerm(String offset){
		for(TermsStub term : terms){
			if(offset.equalsIgnoreCase(term.getOffset())){
				return term.getValue();
			}
		}
		return null;
	}
	
	public String getEndereco(){
		return getTerm("0");
	}
	
	public String getBairro(){
		return getTerm("31");
	}
	
	public String getEstado(){
		return getTerm("40");
	}
	
	public String getPais(){
		return getTerm("54");
	}
	
	public String getRua(){
		String endereco = getEndereco();
		if(endereco.indexOf(",") > -1){
			return endereco.split(",")[0];
		}
		return endereco;
	}
}
