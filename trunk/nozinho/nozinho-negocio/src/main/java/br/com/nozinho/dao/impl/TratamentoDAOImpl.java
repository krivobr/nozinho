package br.com.nozinho.dao.impl;

import javax.ejb.Stateless;

import br.com.nozinho.dao.TratamentoDAO;
import br.com.nozinho.ejb.dao.impl.GenericDAOImpl;
import br.com.nozinho.model.Tratamento;

@Stateless
public class TratamentoDAOImpl extends GenericDAOImpl<Tratamento, Long> implements TratamentoDAO {

}
