package br.com.nozinho.web.callers;

import br.com.nozinho.model.Contato;

public interface ContatoCaller {

	void setContato(Contato contato);
	
	void setContato(Contato contato, String property);
	
}
