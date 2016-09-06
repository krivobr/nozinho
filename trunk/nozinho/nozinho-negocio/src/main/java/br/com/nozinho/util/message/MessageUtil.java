package br.com.nozinho.util.message;

import java.util.ResourceBundle;

public class MessageUtil {

	private static ResourceBundle message = ResourceBundle.getBundle("business_messages");
	
	private MessageUtil(){
		
	}
	
	public static String getMessage(String messageName){
		if(messageName == null){
			throw new IllegalArgumentException("O parâmetro não pode ser nulo.");
		}
		return message.getString(messageName);
	}
	
}
