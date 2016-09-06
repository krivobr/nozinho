/**
 * 
 */
package br.com.nozinho.test.impl;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;

/**
 * @author RodrigoAndrade
 *
 */
public abstract class BaseTestCase extends RunDatabaseHSqlDB {
	
	private EntityManagerFactory emf;

	public abstract String getPersistenceUnitName();

	public EntityManagerFactory getEmf() {
		return emf;
	}
	
	public void setEmf(EntityManagerFactory emf) {
		this.emf = emf;
	}

	@Before
	public void init() {
		System.setProperty("log4j.configuration", "log4j.properties");
		this.setEmf(Persistence.createEntityManagerFactory(getPersistenceUnitName()));
	}

	@After
	public void finish() {
		if (this.getEmf() != null) {
			this.getEmf().close();
		}
	}

}
