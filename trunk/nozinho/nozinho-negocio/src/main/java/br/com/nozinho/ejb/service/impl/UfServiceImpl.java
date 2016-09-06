package br.com.nozinho.ejb.service.impl;

import javax.ejb.Stateless;

import br.com.nozinho.ejb.service.UfService;
import br.com.nozinho.model.Uf;

@Stateless
public class UfServiceImpl extends GenericServiceImpl<Uf, String> implements UfService{

}
