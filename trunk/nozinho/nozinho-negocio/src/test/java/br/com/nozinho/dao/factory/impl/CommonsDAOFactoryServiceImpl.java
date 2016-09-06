/**
 * 
 */
package br.com.nozinho.dao.factory.impl;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.nozinho.ejb.dao.DAOFactoryService;
import br.com.nozinho.ejb.dao.impl.DAOFactoryServiceImpl;

/**
 * @author AndreLA
 *
 */
@Stateless
public class CommonsDAOFactoryServiceImpl extends DAOFactoryServiceImpl implements DAOFactoryService {

	private static final String PERSISTENCE_UNIT_NAME = "CommonsPU";

	@PersistenceContext(unitName = PERSISTENCE_UNIT_NAME)
	private EntityManager entityManager;

	@Override
	public EntityManager getEntityManager() {
		return entityManager;
	}

}
