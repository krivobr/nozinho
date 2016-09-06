/**
 * Classe que faz a chamada para um serviço rest e retorna o resultado.
 * 
 * @author randrade
 */
package br.com.nozinho.dao.framework.rest;

import java.util.List;
import java.util.Map;

import br.com.nozinho.dao.framework.http.HTTPMethodEnum;
import br.com.nozinho.dao.framework.http.HttpClient;
import br.com.nozinho.util.json.JsonUtil;

public class RestClient {
	
	public static <T> T get(String url, Map<String,String> parameters, Class<T> clazz){
		HttpClient client = createHttpClient(url);		
		return JsonUtil.<T>fromJson(client.getContent(HTTPMethodEnum.GET, parameters), clazz);
	}
	
	public static <T> List<T> getList(String url, Map<String,String> parameters, Class<T> clazz){
		HttpClient client = createHttpClient(url);		
		return JsonUtil.<T>fromListJson(client.getContent(HTTPMethodEnum.GET, parameters), clazz);
	}
	
	private static HttpClient createHttpClient(String url){
		String domain = url.substring(0,url.indexOf("?") - 1);
		if(System.getProperty("proxy.host") != null && !isInScapeList(domain)){
			return new HttpClient(url,System.getProperty("proxy.host"),Integer.parseInt(System.getProperty("proxy.port")));
		}else{
			return new HttpClient(url);
		}
	}
	
	private static boolean isInScapeList(String domain){
		String scapeList = System.getProperty("proxy.scape");
		if(scapeList != null){
			String[] scapeArray = scapeList.split(";");
			for(String scapeDomain : scapeArray){
				if(scapeDomain.contains(domain) || domain.contains(scapeDomain)){
					return true;
				}
			}
		}
		return false;
	}
}
