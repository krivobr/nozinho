/**
 * 
 */
package br.com.nozinho.ejb.service.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.ejb.EJB;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import br.com.nozinho.ejb.dao.DAOFactoryService;
import br.com.nozinho.ejb.dao.GenericDAO;
import br.com.nozinho.ejb.dao.impl.ReflectionUtil;
import br.com.nozinho.ejb.service.GenericService;
import br.com.nozinho.model.Entidade;

/**
 * @author AndreLA
 *
 */
@SuppressWarnings("unchecked")
public abstract class GenericServiceImpl<T extends Entidade, C extends Serializable> implements GenericService<T, C> {

	@EJB
	private DAOFactoryService daoFactory;

	protected DAOFactoryService getDaoFactory() {
		return daoFactory;
	}

	protected GenericDAO<T, C> getDAO() {
		return this.getDaoFactory().getDAO(this);
	}

	private GenericDAO<T, C> getGenericDAO() {
		Class<? extends Serializable> classePersistente = null;
		classePersistente = (Class<T>) ReflectionUtil.getParameterizedType(
				getClass()).getActualTypeArguments()[0];
		return this.getDaoFactory().getGenericDAO(classePersistente);
	}

	public T recuperarPorId(Object id) {
		return getGenericDAO().recuperarPorId(id);
	}

	public List<T> recuperarTodos() {
		return getGenericDAO().recuperarTodos();
	}

	public void remover(T entidade) {
		getGenericDAO().remover(entidade);
	}

	public T merge(T entidade) {
		return getGenericDAO().merge(entidade);
	}

	public T salvar(T entidade) {
		return getGenericDAO().salvar(entidade);
	}

	public T salvarClear(T entidade) {
		return getGenericDAO().salvarClear(entidade);
	}

	public List<T> salvarLista(List<T> listaEntidade) {
		return getGenericDAO().salvarLista(listaEntidade);
	}
	
	@SuppressWarnings("unused")
	private void validate(T entity){
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		Set<ConstraintViolation<T>> violations = validator.validate(entity);
		if(!violations.isEmpty()){
			//TODO - Colocar aqui o tratamento das mensagens.
		}
	}

}
