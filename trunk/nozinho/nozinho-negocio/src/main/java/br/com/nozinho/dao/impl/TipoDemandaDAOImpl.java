package br.com.nozinho.dao.impl;

import javax.ejb.Stateless;

import br.com.nozinho.dao.TipoDemandaDAO;
import br.com.nozinho.ejb.dao.impl.GenericDAOImpl;
import br.com.nozinho.model.TipoDemanda;

@Stateless
public class TipoDemandaDAOImpl extends GenericDAOImpl<TipoDemanda, Long> implements TipoDemandaDAO{

}
