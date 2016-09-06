package br.com.nozinho.dao.framework.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;

public class HttpClient{	
	private String url;
	private String CHARSET = "UTF-8";
	private Proxy proxy;
	private String user;
	private String password;
	
	public HttpClient(String url){
		this.url = url;
	}
	

	public HttpClient(String url,Proxy proxy){
		this.url = url;
		this.proxy = proxy;
	}
	
	public HttpClient(String url, String proxyHost,int proxyPort){
		this(url,new Proxy(Proxy.Type.HTTP, new InetSocketAddress(proxyHost, proxyPort)));
	}
	
	public HttpClient(String url,String charset){
		this.url = url;
		this.CHARSET = charset;
	}
	
	public HttpClient(String url,String charset, Proxy proxy){
		this(url,charset);
		this.proxy = proxy;
	}
	
	public HttpClient(String url,String charset,String proxyHost, int proxyPort){
		this(url,charset,new Proxy(Proxy.Type.HTTP,new InetSocketAddress(proxyHost, proxyPort)));
	}
	
	public String getContent(HTTPMethodEnum method,Map<String,String> parameters) {
		switch(method){
			case POST:
				return getContentByPost(parameters);
			case GET:
				return getContentByGet(parameters);
			default:
				return getContentByGet(parameters);
		}
	}

	
	private String getContentByGet(Map<String,String> parameters){
		try {
			URL urlObject = null;
			if(parameters != null){
				urlObject = new URL(url + convertParameters(parameters));
			}else {
				urlObject = new URL(url);
			}
			URLConnection uc = openConnection(urlObject);
			BufferedReader reader = new BufferedReader(new InputStreamReader(uc.getInputStream(), CHARSET));
			
			StringBuilder builder = new StringBuilder();
			String strTemp = "";
			while (null != (strTemp = reader.readLine())) {
				builder.append(strTemp + "\n\t\t\t");
			}
			return builder.toString();
		} catch(Exception e) {
			return null;
		}
	}
		
	private String convertParameters(Map<String,String> parameters){
		String encodedParameters = "";
		try{
			for(String key : parameters.keySet()){
				if("password".equalsIgnoreCase(key) || "user".equalsIgnoreCase(key)){
					continue;
				}
				String parameter = parameters.get(key);
				encodedParameters += key + "=" + URLEncoder.encode(parameter,CHARSET) + "&";
			}
			encodedParameters = encodedParameters.substring(0, encodedParameters.length() - 1);
			this.user = parameters.get("user");
			this.password = parameters.get("password");
			return encodedParameters;
		}catch(Exception e){
			return null;
		}
	}
	private String getContentByPost(Map<String,String> parameters){
		try{
			URL urlObject = new URL(url);
			HttpURLConnection connection = (HttpURLConnection) openConnection(urlObject);
			connection.setRequestMethod("POST");
			connection.setDoOutput(true);
			
			OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
			if(parameters != null){
				writer.write(convertParameters(parameters));
			}
			writer.flush();
	
			String line;
			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
	
			StringBuilder builder = new StringBuilder();
			while ((line = reader.readLine()) != null) {
				builder.append(line + "\n\t\t\t");
				System.out.println(line);
			}
	
			writer.close();
			return builder.toString();
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;		
	}
	
	private URLConnection openConnection(URL url) throws IOException{
		URLConnection connection = null;
		if(proxy == null){
			connection = url.openConnection();
		}else{
			connection = url.openConnection(proxy);
		}
		if(user != null && password != null){
			String authString = user.concat(":").concat(password);
			byte[] authEncBytes = Base64.encodeBase64(authString.getBytes());
			String authEncString = new String(authEncBytes);
			connection.setRequestProperty("Authorization", "Basic " + authEncString);
		}
		return connection;
	}

}
