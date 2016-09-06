/**
 * 
 */
package br.com.nozinho.ejb.service;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Local;

import br.com.nozinho.model.Entidade;

/**
 * @author RodrigoAndrade
 *
 */
@Local
public interface GenericService<T extends Entidade, C extends Serializable> {

	void remover(T entidade);

	T salvar(T entidade);

	T salvarClear(T entidade);
	
	T merge(T entidade);
	
	List<T> salvarLista(List<T> listaEntidades);

	T recuperarPorId(Object id);
	
	List<T> recuperarTodos();

}
