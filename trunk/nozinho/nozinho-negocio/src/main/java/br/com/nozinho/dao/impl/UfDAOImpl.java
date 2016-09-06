package br.com.nozinho.dao.impl;

import javax.ejb.Stateless;

import br.com.nozinho.dao.UfDAO;
import br.com.nozinho.ejb.dao.impl.GenericDAOImpl;
import br.com.nozinho.model.Uf;

@Stateless
public class UfDAOImpl extends GenericDAOImpl<Uf, String> implements UfDAO{

}
