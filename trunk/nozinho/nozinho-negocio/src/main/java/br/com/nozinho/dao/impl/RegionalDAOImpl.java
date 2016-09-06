package br.com.nozinho.dao.impl;

import javax.ejb.Stateless;

import br.com.nozinho.dao.RegionalDAO;
import br.com.nozinho.ejb.dao.impl.GenericDAOImpl;
import br.com.nozinho.model.Regional;

@Stateless
public class RegionalDAOImpl extends GenericDAOImpl<Regional, Long> implements RegionalDAO {

}
