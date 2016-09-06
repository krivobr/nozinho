package br.com.nozinho.web.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

@ManagedBean
@ViewScoped
public class DocumentViewerController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8755142744486876258L;

	private byte[] document;


	public StreamedContent getContent() throws IOException {
		if(document != null){
			InputStream is = new ByteArrayInputStream(document);
			is.mark(0);
			StreamedContent stream = new DefaultStreamedContent(is , "application/pdf");
			stream.getStream().reset();
			return stream;
		} else {
			return null;
		}
	}


	public void setDocument(byte[] document){
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("reportBytes", document);
		this.document = document;
	}
	
}
