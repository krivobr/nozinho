package br.com.nozinho.web.converter;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.nozinho.model.Entidade;

@FacesConverter("entidadeConverter")
public class EntidadeConverter implements Converter {
	
	private static Map<String, Map<Serializable, Object>> mapEntidades = new HashMap<String, Map<Serializable, Object>>();

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if(value != null && value.trim().length() > 0 && value.indexOf("-") > -1){
			String[] arrayValue = value.split("-");
			String className = arrayValue[0];
			String classValue = arrayValue[1];
			return mapEntidades.get(className).get(classValue);
		}else{
			return null;
		}
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object object) {
		if(object != null && object instanceof Entidade){
			Entidade entidade = (Entidade) object;
			if(mapEntidades.get(entidade.getClass().getName()) == null){
				mapEntidades.put(entidade.getClass().getName(), new HashMap<Serializable, Object>());
			}
			mapEntidades.get(entidade.getClass().getName()).put(entidade.getId().toString(), entidade);
			return entidade.getClass().getName() + "-" + entidade.getId().toString();
		}else{
			return null;
		}
	}

}
