package br.com.nozinho.ejb.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;

import br.com.nozinho.model.Entidade;

public interface GenericDAO<T extends Entidade, C extends Serializable> {

	void remover(T entidade);

	T salvar(T entidade);

	T merge(T entidade);

	List<T> salvarLista(List<T> listaEntidades);

	T salvarClear(T entidade);

	T recuperarPorId(Object id);

	List<T> recuperarTodos();

	void setEntityClass(Class<T> entityClass);

	void setEntityManager(EntityManager entityManager);

	void initTransaction();
	
	void endTransaction();
	
	List<T> executeNamedQuery(String namedQuery, Map<String,String> params);

}