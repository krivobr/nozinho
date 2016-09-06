package br.com.nozinho.ejb.service.impl;

import javax.ejb.Stateless;

import br.com.nozinho.ejb.service.ProfissaoService;
import br.com.nozinho.model.Profissao;

@Stateless
public class ProfissaoServiceImpl extends GenericServiceImpl<Profissao, Long> implements ProfissaoService{

}
