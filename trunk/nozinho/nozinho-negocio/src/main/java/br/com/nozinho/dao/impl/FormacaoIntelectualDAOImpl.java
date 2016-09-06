package br.com.nozinho.dao.impl;

import javax.ejb.Stateless;

import br.com.nozinho.dao.FormacaoIntelectualDAO;
import br.com.nozinho.ejb.dao.impl.GenericDAOImpl;
import br.com.nozinho.model.FormacaoIntelectual;

@Stateless
public class FormacaoIntelectualDAOImpl extends GenericDAOImpl<FormacaoIntelectual, Long> implements FormacaoIntelectualDAO {

}
