package br.com.nozinho.dao.impl;

import javax.ejb.Stateless;

import br.com.nozinho.dao.ProfissaoDAO;
import br.com.nozinho.ejb.dao.impl.GenericDAOImpl;
import br.com.nozinho.model.Profissao;

@Stateless
public class ProfissaoDAOImpl extends GenericDAOImpl<Profissao, Long> implements ProfissaoDAO {

}
