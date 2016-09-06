/**
 * 
 */
package br.com.nozinho.ejb.dao.impl;

import java.lang.reflect.Constructor;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;

/**
 * @author AndreLA
 *
 */
@SuppressWarnings("rawtypes")
public final class ReflectionUtil {

	private static final org.slf4j.Logger LOG = org.slf4j.LoggerFactory.getLogger(ReflectionUtil.class);

	public static <T> T newInstance(Class<T> clazz, Object... params) {
		if (clazz != null) {
			ArrayList<Class> classes = new ArrayList<Class>();
			for (int i = 0; i < params.length; i++) {
				if (params[i] != null && !params[i].toString().equals("")) {
					classes.add(params[i].getClass());
				}
			}
			Constructor<T> cons = null;
			try {
				if (classes.size() > 0) {
					Class[] classArray = new Class[classes.size()];
					cons = clazz.getDeclaredConstructor(classes.toArray(classArray));
					return cons != null ? cons.newInstance(params) : null;
				} else {
					return clazz.newInstance();
				}
			} catch (Exception e) {
				LOG.error("Problema ao instanciar a classe.", e);
			}
		}
		return null;
	}
	
	public static ParameterizedType getParameterizedType(Class<?> clazz) {
		if (clazz.equals(Object.class)) {
			return null;
		}
		if (clazz.getGenericSuperclass() instanceof ParameterizedType) {
			return (ParameterizedType) clazz.getGenericSuperclass();
		}
		return getParameterizedType(clazz.getSuperclass());
	}

}
