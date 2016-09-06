package br.com.nozinho.ejb.service;

import javax.ejb.Local;

import br.com.nozinho.model.Profissao;

@Local
public interface ProfissaoService extends GenericService<Profissao, Long>{

}
