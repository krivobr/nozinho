package br.com.nozinho.dao;

import java.util.List;

import javax.ejb.Local;

@Local
public interface DashboardDAO {
	
	Long getDemandasMenor15Dias();

	Long getDemandasEntre15E30Dias();
	
	Long getDemandasEntre30E60Dias();
	
	Long getDemandasMais60Dias();
	
	Long getTotalDemandasPrazo();
	
	List<Object[]> getNumDemandaMesCurrentYear();
	
	List<Object[]> getNumDemandasTipo();
}
