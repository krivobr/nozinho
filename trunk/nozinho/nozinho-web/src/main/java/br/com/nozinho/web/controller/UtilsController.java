package br.com.nozinho.web.controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.apache.commons.beanutils.PropertyUtils;

/**
 * 
 * @author AndradeR
 * 
 */
@ManagedBean
@RequestScoped
public class UtilsController {

	/**
	 * /** Testa se o managed bean possui a propriedade.
	 * 
	 * @param o
	 * @param propertyName
	 * @return
	 */
	public static boolean hasProperty(Object o, String propertyName) {
		if (o == null || propertyName == null) {
			return false;
		}
		try {
			return PropertyUtils.getPropertyDescriptor(o, propertyName) != null;
		} catch (Exception e) {
			return false;
		}
	}

}
