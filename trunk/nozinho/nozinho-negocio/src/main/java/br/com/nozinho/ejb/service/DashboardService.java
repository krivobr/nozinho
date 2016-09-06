package br.com.nozinho.ejb.service;

import javax.ejb.Local;

import br.com.nozinho.model.Dashboard;

@Local
public interface DashboardService {

	Dashboard getDashBoard();
	
}
