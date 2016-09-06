package br.com.nozinho.ejb.service.impl;

import java.util.List;

import javax.ejb.Stateless;

import br.com.nozinho.dao.CargoDAO;
import br.com.nozinho.ejb.service.CargoService;
import br.com.nozinho.model.Cargo;

@Stateless
public class CargoServiceImpl extends GenericServiceImpl<Cargo, Long> implements CargoService {

	@Override
	public List<Cargo> findByNomeLike(String nome) {
		return ((CargoDAO) getDAO()).findByNoneLike(nome);
	}

}
