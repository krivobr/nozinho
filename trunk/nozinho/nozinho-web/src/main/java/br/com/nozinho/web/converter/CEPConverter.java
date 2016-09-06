package br.com.nozinho.web.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter("converter.cepConverter")
public class CEPConverter implements Converter{

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		return value != null ? value.replace(".", "").replace("-", "") : null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		return value != null ? value.toString() : null;
	}

}
