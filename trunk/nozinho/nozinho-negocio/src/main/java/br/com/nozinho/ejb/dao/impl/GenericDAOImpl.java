package br.com.nozinho.ejb.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.hibernate.Session;

import br.com.nozinho.ejb.dao.GenericDAO;
import br.com.nozinho.model.Entidade;

@SuppressWarnings({ "unchecked", "unused" })
public class GenericDAOImpl<T extends Entidade, C extends Serializable> implements GenericDAO<T, C> {

	private static final long serialVersionUID = 1L;
	private static final Integer QTD_ENTITY_PERSISTED_PER_FLUSH_CLEAR_ON_SAVE_LIST = 50;
	private EntityManager entityManager;
	private Class<T> entityClass = null;
	private boolean isTransaction = false;

	@Resource
	private SessionContext sessionContext;

	public boolean isTransaction() {
		return isTransaction;
	}

	public void setIsTransaction(boolean isTransaction) {
		this.isTransaction = isTransaction;
	}

	@Override
	public final void remover(T entidade) {
		T entidadeRemover = merge(entidade);
		this.getEntityManager().remove(entidadeRemover);

		if (!this.isTransaction()) {
			this.getEntityManager().flush();
		}
	}

	@Override
	public final T salvar(T entidade) {
		T entidadeSalvar = this.merge(preencheUsuarioLogado(entidade));
		return entidadeSalvar;
	}

	@Override
	public final T merge(T entidade) {
		T entidadeMerge = this.getEntityManager().merge(preencheUsuarioLogado(entidade));

		if (!this.isTransaction()) {
			this.getEntityManager().flush();
		}
		return entidadeMerge;
	}

	@Override
	public final List<T> salvarLista(List<T> listaEntidades) {
		List<T> salvarLista = new ArrayList<T>();
		long iter = 0;

		for (T entidade : listaEntidades) {
			salvarLista.add(salvar(entidade));

			iter++;
			if (!this.isTransaction()
					&& (iter % QTD_ENTITY_PERSISTED_PER_FLUSH_CLEAR_ON_SAVE_LIST) == 0) {
				this.getEntityManager().flush();
				this.getEntityManager().clear();
			}
		}

		if (!this.isTransaction()) {
			this.getEntityManager().flush();
			this.getEntityManager().clear();
		}

		return salvarLista;
	}

	@Override
	public final T salvarClear(T entidade) {
		entidade = salvar(entidade);

		if (!this.isTransaction()) {
			this.getEntityManager().flush();
			this.getEntityManager().clear();
		}

		return entidade;
	}

	@Override
	public final T recuperarPorId(Object id) {
		return this.getEntityManager().find(getEntityClass(), id);
	}

	@Override
	public final List<T> recuperarTodos() {
		List<T> listaEndidade = this.getEntityManager().createQuery("SELECT c FROM "
								+ getEntityClass().getSimpleName() + " c").getResultList();
		return listaEndidade;
	}

	protected final Class<T> getEntityClass() {
		if (entityClass == null) {
			entityClass = (Class<T>) ((ParameterizedType) this.getClass()
					.getGenericSuperclass()).getActualTypeArguments()[0];
		}
		return entityClass;
	}

	public void setEntityClass(Class<T> entityClass) {
		this.entityClass = entityClass;
	}

	public final void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public void initTransaction() {
		this.setIsTransaction(true);
	}

	public void endTransaction() {
		this.getEntityManager().flush();
		this.setIsTransaction(false);
	}

	protected final EntityManager getEntityManager() {
		return entityManager;
	}

	protected Query createQuery(String query) {
		return getEntityManager().createQuery(query);
	}

	protected Query createNamedQuery(String query) {
		return getEntityManager().createNamedQuery(query);
	}

	protected TypedQuery<T> createNamedQuery(String query, Class<T> typeClass) {
		TypedQuery<T> typedQuery = null;
		try {
			typedQuery = getEntityManager().createNamedQuery(query, typeClass);
		} catch (Exception e) {
			System.out.println("Ocorreu um erro ao tentar recuperar a Query: " + query);
		}
		return typedQuery;
	}
	
	protected Session getSession(){
		return (Session) this.getEntityManager().getDelegate();
	}
	
	protected T preencheUsuarioLogado(T entidade) {
		if (entidade instanceof Entidade) {
			if (getEJBContext() != null && getEJBContext().getCallerPrincipal() != null) {
//				entidade.setUsuarioAlteracao(getEJBContext().getCallerPrincipal().getName());
//				if (entidade.getUsuarioInclusao() == null) {
//					entidade.setUsuarioInclusao(entidade.getUsuarioAlteracao());
//				}
			}
		}
		return entidade;
	}
	
    private SessionContext getEJBContext() {
        try {
        	if (sessionContext == null) {
        		InitialContext ic = new InitialContext();
        		this.sessionContext = (SessionContext) ic.lookup("java:comp/EJBContext");
        	}
        	return sessionContext;
        } catch (NamingException ex) {
        	System.out.println("Não foi possível identificar o EJBContext.");
        	return null;
        }
    }

	@Override
	public List<T> executeNamedQuery(String namedQuery,
			Map<String, String> params) {
		TypedQuery<T> query = getEntityManager().createNamedQuery(namedQuery, entityClass);
		for(String key : params.keySet()){
			query.setParameter(key, params.get(key));
		}
		return query.getResultList();
	}	
}