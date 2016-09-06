package br.com.nozinho.dao;

import javax.ejb.Local;

import br.com.nozinho.ejb.dao.GenericDAO;
import br.com.nozinho.model.TipoAtendimento;

/**
 * @author RodrigoAndrade
 *
 */
@Local
public interface TipoAtendimentoDAO extends GenericDAO<TipoAtendimento, Long>{

}
