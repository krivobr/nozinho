package br.com.nozinho.ejb.service.impl;

import javax.ejb.Stateless;

import br.com.nozinho.ejb.service.TratamentoService;
import br.com.nozinho.model.Tratamento;

@Stateless
public class TratamentoServiceImpl extends GenericServiceImpl<Tratamento, Long> implements TratamentoService{

}
