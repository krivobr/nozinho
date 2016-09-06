package br.com.nozinho.ejb.service.impl;

import javax.ejb.Stateless;

import br.com.nozinho.ejb.service.RegionalService;
import br.com.nozinho.model.Regional;

@Stateless
public class RegionalServiceImpl extends GenericServiceImpl<Regional, Long> implements RegionalService {

}
