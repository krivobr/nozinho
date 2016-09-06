/**
 * 
 */
package br.com.nozinho.dao.impl.factory;

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
public class DAOFactoryNozinhoServiceImpl extends DAOFactoryServiceImpl implements DAOFactoryService {

	private static final String PERSISTENCE_UNIT_NAME = "NozinhoPU";

	@PersistenceContext(unitName = PERSISTENCE_UNIT_NAME)
	private EntityManager entityManager;

	@Override
	public EntityManager getEntityManager() {
		return entityManager;
	}

}
