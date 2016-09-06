package br.com.nozinho.exceptions;

import javax.ejb.ApplicationException;

@ApplicationException(rollback=true)
public class BusinessException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6376510788692585006L;
	
	public BusinessException(String message){
		super(message);
	}	
	

}
