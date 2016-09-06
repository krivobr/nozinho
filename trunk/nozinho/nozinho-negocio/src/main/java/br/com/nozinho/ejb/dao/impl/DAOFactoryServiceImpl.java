/**
 * 
 */
package br.com.nozinho.ejb.dao.impl;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;

import org.apache.commons.collections.FastHashMap;

import br.com.nozinho.ejb.dao.GenericDAO;

/**
 * @author AndreLA
 *
 */
@SuppressWarnings({ "unchecked", "rawtypes" })
public abstract class DAOFactoryServiceImpl {

	private static final org.slf4j.Logger LOG = org.slf4j.LoggerFactory.getLogger(DAOFactoryServiceImpl.class);
	private static final String SUFIXE_NAME_SEVICE = "Service";
	private static final String SUFIXE_NAME_DAO = "DAO";
	private static final String PART_PACKAGE_PATH_SERVICE = ".ejb.service.";
	private static final String PART_PACKAGE_PATH_DAO = ".dao.";

	private static FastHashMap mapaDAO = new FastHashMap();

	public abstract EntityManager getEntityManager();

	public final GenericDAO getDAO(final Object serviceClass) {
		return lookupDAO(getDAOName(serviceClass));
	}

	public GenericDAO getGenericDAO(Class classeEntidade) {
		GenericDAO dao = instanciarDAO(GenericDAOImpl.class);
		dao.setEntityClass(classeEntidade);
		return dao;
	}

	/**
	 * Método que busca o nome do DAO.
	 * 
	 * @param serviceObject
	 * @return {@link Class}
	 */
	private static Class getDAOName(final Object serviceObject) {

		String nomeServico = serviceObject.getClass().getName();
		Class classDAO = (Class) mapaDAO.get(nomeServico);

		/*
		 * DAO não foi instânciado ainda
		 */
		if (classDAO == null) {
			String classe = serviceObject.getClass().getName();
			classe = classe.replace(PART_PACKAGE_PATH_SERVICE, PART_PACKAGE_PATH_DAO);
			classe = classe.replace(SUFIXE_NAME_SEVICE, SUFIXE_NAME_DAO);
			try {
				classDAO = Class.forName(classe);
			} catch (ClassNotFoundException e) {
				LOG.error("Classe não encontrada para o serviço " + nomeServico, e);
			}
			mapaDAO.put(nomeServico, classDAO);
		}
		return classDAO;
	}

	private GenericDAO instanciarDAO(Class classeDAO) {
		GenericDAO genericDAO = null;
		genericDAO = (GenericDAO) ReflectionUtil.newInstance(classeDAO);
		genericDAO.setEntityManager(getEntityManager());

		return genericDAO;
	}
	
	private GenericDAO lookupDAO(Class classeDAO) {
		GenericDAO genericDAO = null;
		InitialContext ic;
		
		try {
			ic = new InitialContext();
			//ic.unbind(classeDAO.getSimpleName() + "/local");
			genericDAO = (GenericDAO) ic.lookup(classeDAO.getSimpleName() + "/local");
			genericDAO.setEntityManager(getEntityManager());
		} catch (NamingException e) {
			return instanciarDAO(classeDAO);
		}

		return genericDAO;
	}

}
