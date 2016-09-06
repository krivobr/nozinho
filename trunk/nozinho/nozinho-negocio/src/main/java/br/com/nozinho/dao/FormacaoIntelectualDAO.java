package br.com.nozinho.dao;

import javax.ejb.Local;

import br.com.nozinho.ejb.dao.GenericDAO;
import br.com.nozinho.model.FormacaoIntelectual;

@Local
public interface FormacaoIntelectualDAO extends GenericDAO<FormacaoIntelectual, Long> {

}
