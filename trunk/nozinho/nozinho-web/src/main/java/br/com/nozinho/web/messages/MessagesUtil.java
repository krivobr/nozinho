package br.com.nozinho.web.messages;

import java.text.MessageFormat;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;

public class MessagesUtil {
	
	static ResourceBundle messages;

	{
		iniciarMensagens();
	}

	public static boolean existeMensagem() {
		return FacesContext.getCurrentInstance().getMessages().hasNext();
	}

	private static void iniciarMensagens() {
		FacesContext ctx = FacesContext.getCurrentInstance();
		UIViewRoot uiRoot = ctx.getViewRoot();
		Locale locale = uiRoot.getLocale();
		messages = ResourceBundle.getBundle("br.com.nozinho.messages", locale);
	}

	public static String getMessage(String messageName) {
		if (messages == null)
			iniciarMensagens();
		return messages.getString(messageName);
	}

	public static String getMessage(String messageName, Object... parameters) {
		MessageFormat format = new MessageFormat(getMessage(messageName), new Locale("pt", "BR"));
		String result = format.format(parameters);
		return result;
	}


	public static void addSuccessMessageForComponent(String componentId, String messageName, Object... parameters) {
		try {
			String messageText = MessagesUtil.getMessage(messageName, parameters);
			FacesMessage messageObject = new FacesMessage(FacesMessage.SEVERITY_INFO, messageText, messageText);
			FacesContext.getCurrentInstance().addMessage(componentId, messageObject);
		} catch (Exception e) {
			addErrorMessage(messageName);
		}
	}

	public static void addErrorMessageForComponent(String componentId, String messageName, Object... parameters) {
		try {
			String messageText = MessagesUtil.getMessage(messageName, parameters);
			FacesMessage messageObject = new FacesMessage(FacesMessage.SEVERITY_ERROR, messageText, messageText);
			FacesContext.getCurrentInstance().addMessage(componentId, messageObject);
		} catch (Exception e) {
			addErrorMessage(messageName);
		}
	}

	public static void addInfoMessageForComponent(String componentId, String messageName, Object... parameters) {
		try {
			String messagetext = MessagesUtil.getMessage(messageName, parameters);
			FacesMessage messageObject = new FacesMessage(FacesMessage.SEVERITY_INFO, messagetext, messagetext);
			FacesContext.getCurrentInstance().addMessage(componentId, messageObject);
		} catch (Exception e) {
			addErrorMessage(messageName);
		}
	}
	
	public static void addErrorMessages(List<String> messages) {
		for (String message : messages) {
			addErrorMessage(message);
		}
	}

	public static void addErrorMessage(String key) {	
		FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, getMessage(key), getMessage(key));
		FacesContext.getCurrentInstance().addMessage(null, facesMsg);
	}

	public static void addInfoMessage(String key) {
		FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, getMessage(key), getMessage(key));
		FacesContext.getCurrentInstance().addMessage(null, facesMsg);
	}

	public static void addSuccessMessage(String key) {
		FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, getMessage(key), getMessage(key));
		FacesContext.getCurrentInstance().addMessage(null, facesMsg);
	}

	public static void showErrorMessage(String msg) {	
		FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg);
		FacesContext.getCurrentInstance().addMessage(null, facesMsg);
	}

	public static void showInfoMessage(String msg) {
		FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg);
		FacesContext.getCurrentInstance().addMessage(null, facesMsg);
	}

	public static void showSuccessMessage(String msg) {
		FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg);
		FacesContext.getCurrentInstance().addMessage("successInfo", facesMsg);
	}
	
	public static String getRequestParameter(String key) {
		return FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get(key);
	}
}
