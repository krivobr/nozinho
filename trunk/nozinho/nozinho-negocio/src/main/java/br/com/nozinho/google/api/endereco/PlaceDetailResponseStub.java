package br.com.nozinho.google.api.endereco;

public class PlaceDetailResponseStub {
	
	private String[] html_attributions;
	
	private PlaceComponentStub result;
	
	private String status;

	public String[] getHtml_attributions() {
		return html_attributions;
	}

	public void setHtml_attributions(String[] html_attributions) {
		this.html_attributions = html_attributions;
	}

	public PlaceComponentStub getResult() {
		return result;
	}

	public void setResult(PlaceComponentStub result) {
		this.result = result;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
