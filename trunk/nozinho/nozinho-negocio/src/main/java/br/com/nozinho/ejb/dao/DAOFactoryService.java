/**
 * 
 */
package br.com.nozinho.ejb.dao;

import javax.ejb.Local;

/**
 * @author RodrigoAndrade
 *
 */
@Local
@SuppressWarnings("rawtypes")
public interface DAOFactoryService {

	GenericDAO getDAO(final Object serviceClass);

	GenericDAO getGenericDAO(Class classeEntidade);

}
