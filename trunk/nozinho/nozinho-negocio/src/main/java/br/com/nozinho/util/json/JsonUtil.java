package br.com.nozinho.util.json;

import java.io.Reader;
import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Classe Entidade com JSon methods
 * 
 * @author RodrigoAndrade
 */
public class JsonUtil {
	
	public static String toJson(Object propriedade) {
		Gson gson = new GsonBuilder().create();
		if (propriedade != null) {
			return gson.toJson(propriedade);
		}
		return null;
	}

	public static <T> T fromJson(String jsonString, Class<T> clazz) {
		Gson gson = new GsonBuilder().create();

		if (jsonString != null) {
			return gson.fromJson(jsonString, clazz);
		}
		return null;
	}

	public static <T> List<T> fromListJson(String jsonString, Type listType) {
		Gson gson = new GsonBuilder().create();
		
		if (jsonString != null) {
			List<T> list = gson.fromJson(jsonString, listType);
			return list;
		}
		return null;
	}

	public static <T> List<T> fromListJson(Reader jsonReader, Type listType) {
		Gson gson = new GsonBuilder().create();
		
		if (jsonReader != null) {
			List<T> list = gson.fromJson(jsonReader, listType);
			return list;
		}
		return null;
	}

}