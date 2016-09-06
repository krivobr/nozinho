/**
 * 
 */
package br.com.nozinho.test.util.impl;

import org.junit.BeforeClass;

import br.com.nozinho.test.impl.BaseTestCase;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;

/**
 * @author RodrigoAndrade
 *
 */
public class BaseTestCaseIndicaBR extends BaseTestCase {
	
	@BeforeClass
	public static void beforeClass(){
		FixtureFactoryLoader.loadTemplates("br.com.nozinho.test.util.fixture.loaders");
	}

	@Override
	public String getPersistenceUnitName() {
		return "IndicaBRPU";
	}

}
