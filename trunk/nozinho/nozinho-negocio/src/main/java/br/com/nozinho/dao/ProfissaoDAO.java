package br.com.nozinho.dao;

import javax.ejb.Local;

import br.com.nozinho.ejb.dao.GenericDAO;
import br.com.nozinho.model.Profissao;

@Local
public interface ProfissaoDAO extends GenericDAO<Profissao, Long>{

}
