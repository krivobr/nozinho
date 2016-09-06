/**
 * 
 */
package br.com.nozinho.test.impl;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author RodrigoAndrade
 *
 */
public final class EntityManagerController implements InvocationHandler {

	private static final Logger LOG = LoggerFactory.getLogger(EntityManagerController.class);

	private EntityManagerFactory emf;

	private Object target;

	private Field field;
	
	private String region;

	private EntityManagerController(Object target, EntityManagerFactory emf) {
		this(target, emf, null);
	}
	
	private EntityManagerController(Object target, EntityManagerFactory emf, String region) {
		this.setEmf(emf);
		this.setTarget(target);
		try {
			this.setField(target.getClass().getSuperclass().getDeclaredField("entityManager"));
			this.getField().setAccessible(true);
		} catch (SecurityException e) {
    		LOG.error("Ocorreu um erro: " + e.getMessage());
		} catch (NoSuchFieldException e) {
    		LOG.error("Ocorreu um erro: " + e.getMessage());
		}
		this.setRegion(region);
	}

	public EntityManagerFactory getEmf() {
		return emf;
	}

	public void setEmf(EntityManagerFactory emf) {
		this.emf = emf;
	}

	public Object getTarget() {
		return target;
	}

	public void setTarget(Object target) {
		this.target = target;
	}

	public Field getField() {
		return field;
	}

	public void setField(Field field) {
		this.field = field;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public static Object newInstance(Object target, EntityManagerFactory emf) {
		return Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(),
				target.getClass().getInterfaces(), new EntityManagerController(target,
						emf));
	}
	
	public Object invoke(Object proxy, Method m, Object[] args) {
		EntityManager em = this.getEmf().createEntityManager();
		try {
			this.getField().set(target, em);
			em.getTransaction().begin();
			Object result = m.invoke(this.getTarget(), args);
			em.getTransaction().commit();
			return result;
		} catch (Exception e) {
			em.getTransaction().rollback();
			LOG.error("Ocorreu um erro: " + e.getMessage());
			return null;
		} finally {
			if (this.getRegion() != null) {
				Session session = (Session) em.getDelegate();
	    		LOG.error("Region names: " + Arrays.toString(session.getSessionFactory().getStatistics().getSecondLevelCacheRegionNames()) + " statistics: " + session.getSessionFactory().getStatistics().getSecondLevelCacheStatistics(this.getRegion()));
			}
			em.close();
		}
	}
	
}