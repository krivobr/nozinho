/**
 * 
 */
package br.com.nozinho.test.impl;

import java.util.List;
import java.util.Properties;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.junit.After;
import org.junit.Before;

import com.googlecode.mycontainer.datasource.DataSourceDeployer;
import com.googlecode.mycontainer.ejb.SessionInterceptorDeployer;
import com.googlecode.mycontainer.ejb.StatelessScannableDeployer;
import com.googlecode.mycontainer.jpa.HibernateJPADeployer;
import com.googlecode.mycontainer.jpa.JPADeployer;
import com.googlecode.mycontainer.jpa.JPAInfoBuilder;
import com.googlecode.mycontainer.jta.MyTransactionManagerDeployer;
import com.googlecode.mycontainer.kernel.ShutdownCommand;
import com.googlecode.mycontainer.kernel.boot.ContainerBuilder;
import com.googlecode.mycontainer.kernel.deploy.ScannerDeployer;

/**
 * @author RodrigoAndrade
 * 
 */
public abstract class AbstractTestCase extends RunDatabaseHSqlDB {

	private static final org.slf4j.Logger LOG = org.slf4j.LoggerFactory.getLogger(AbstractTestCase.class);

	public abstract String getPersistenceUnitName();
	
	public abstract String getJtaDataSourceName();
	
	public abstract List<Class<?>> getEntitiesClasses();
	
	public abstract Class<?> getPersistenceUnitRootUrl();
	
	public abstract List<Class<?>> getServiceImplClasses();

	@Before
	public void boot() throws NamingException {
		InitialContext initialContext;
		ContainerBuilder builder = new ContainerBuilder();

		SessionInterceptorDeployer sessionInterceptorDeployer = builder
				.createDeployer(SessionInterceptorDeployer.class);
		sessionInterceptorDeployer.deploy();

		builder.createDeployer(MyTransactionManagerDeployer.class).setName("TransactionManager").deploy();

		DataSourceDeployer ds = builder
				.createDeployer(DataSourceDeployer.class);
		ds.setName("TestDS");
		ds.setDriver("org.hsqldb.jdbcDriver");
		ds.setUrl("jdbc:hsqldb:mem:.");
		ds.setUser("sa");
		ds.deploy();

		JPADeployer jpa = builder.createDeployer(HibernateJPADeployer.class);
		JPAInfoBuilder info = (JPAInfoBuilder) jpa.getInfo();
		info.setPersistenceUnitName(getPersistenceUnitName());
		info.setJtaDataSourceName(getJtaDataSourceName());
		
		/*
		 * Add Jar Files
		 */
		addJarFiles(info);

		info.setPersistenceUnitRootUrl(getPersistenceUnitRootUrl());
		Properties props = info.getProperties();
		props.setProperty("hibernate.dialect",
				"org.hibernate.dialect.HSQLDialect");
		props.setProperty("hibernate.hbm2ddl.auto", "create-drop");
		props.setProperty("hibernate.show_sql", "true");
		jpa.deploy();

		ScannerDeployer scanner = builder.createDeployer(ScannerDeployer.class);
		scanner.add(new StatelessScannableDeployer());
		/*
		 * Add ServicesImplClasses
		 */
		addScannerServiceImplClasses(scanner);
		

		initialContext = builder.getContext();
		initialContext.lookup("TransactionManager");
		
		scanner.deploy();
	}

	private void addScannerServiceImplClasses(ScannerDeployer scanner) {
		for (Class<?> clazz : getServiceImplClasses()) {
			scanner.scan(clazz);
		} 
	}

	private void addJarFiles(JPAInfoBuilder info) {
		for (Class<?> clazz : getEntitiesClasses()) {
			info.addJarFileUrl(clazz);
		} 
	}

	@After
	public void shutdown() {
		try {
			ShutdownCommand shutdown = new ShutdownCommand();
			shutdown.setContext(new InitialContext());
			shutdown.shutdown();
		} catch (Exception e) {
			LOG.error("Error shutdown", e);
		}
	}

}
