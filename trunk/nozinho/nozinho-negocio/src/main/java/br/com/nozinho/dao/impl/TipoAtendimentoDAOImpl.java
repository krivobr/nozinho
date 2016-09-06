package br.com.nozinho.dao.impl;

import javax.ejb.Stateless;

import br.com.nozinho.dao.TipoAtendimentoDAO;
import br.com.nozinho.ejb.dao.impl.GenericDAOImpl;
import br.com.nozinho.model.TipoAtendimento;

@Stateless
public class TipoAtendimentoDAOImpl extends GenericDAOImpl<TipoAtendimento, Long> implements TipoAtendimentoDAO{

}
