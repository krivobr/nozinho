/**
 * 
 */
package br.com.nozinho.test.util.impl;

import java.util.ArrayList;
import java.util.List;

import br.com.nozinho.ejb.dao.DAOFactoryService;

import br.com.nozinho.test.impl.AbstractTestCase;

/**
 * @author RodrigoAndrade
 *
 */
public abstract class AbstractTestCaseIndicaBR extends AbstractTestCase {

	@Override
	public String getPersistenceUnitName() {
		return "IndicaBRPU";
	}

	@Override
	public String getJtaDataSourceName() {
		return "TestDS";
	}


	@Override
	public List<Class<?>> getEntitiesClasses() {
		List<Class<?>> entitiesClasses = new ArrayList<Class<?>>();
		//entitiesClasses.add(Endereco.class);
		return entitiesClasses;
	}

	@Override
	public List<Class<?>> getServiceImplClasses() {
		List<Class<?>> serviceImplClasses = new ArrayList<Class<?>>();
		//serviceImplClasses.add(EnderecoServiceImpl.class);
		//serviceImplClasses.add(DAOFactoryServiceImpl.class);
		return serviceImplClasses;
	}

	@Override
	public Class<?> getPersistenceUnitRootUrl() {
		return DAOFactoryService.class;
	}

}
