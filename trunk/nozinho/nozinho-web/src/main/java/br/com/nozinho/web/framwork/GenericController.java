/**
 * 
 */
package br.com.nozinho.web.framwork;

import java.io.Serializable;
import java.util.List;

import br.com.nozinho.model.Entidade;



/**
 * @author RodrigoAndrade
 *
 */
public interface GenericController<T extends Entidade, C extends Serializable> extends Serializable{

	T getEntidade();
	
	void setEntidade(T entidade);
	
	List<T> getEntidades();
	
	void setEntidades(List<T> entidades);
	
	T recuperarPorId(Object id);
	
	List<T> recuperarTodos();
	
	void remover();
	
	T merge();
	
	T salvar();
	
	T salvarClear();
	
	List<T> salvarLista();

}
