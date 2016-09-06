package br.com.nozinho.google.api.endereco;


public class PlaceComponentStub {
	
	private AddressComponentsStub[] address_components;
	
	private String adr_address;
	
	private String formatted_address;
	
	private GeometryStub geometry;
	
	private String icon;
	
	private String id;
	
	private String name;
	
	private String place_id;
	
	private String reference;
	
	private String scope;
	
	private String[] types;
	
	private String url;
	
	private String vicinity;

	public AddressComponentsStub[] getAddress_components() {
		return address_components;
	}

	public void setAddress_components(AddressComponentsStub[] address_components) {
		this.address_components = address_components;
	}

	public String getAdr_address() {
		return adr_address;
	}

	public void setAdr_address(String adr_address) {
		this.adr_address = adr_address;
	}

	public String getFormatted_address() {
		return formatted_address;
	}

	public void setFormatted_address(String formatted_address) {
		this.formatted_address = formatted_address;
	}

	public GeometryStub getGeometry() {
		return geometry;
	}

	public void setGeometry(GeometryStub geometry) {
		this.geometry = geometry;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPlace_id() {
		return place_id;
	}

	public void setPlace_id(String place_id) {
		this.place_id = place_id;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public String[] getTypes() {
		return types;
	}

	public void setTypes(String[] types) {
		this.types = types;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getVicinity() {
		return vicinity;
	}

	public void setVicinity(String vicinity) {
		this.vicinity = vicinity;
	}
	
	//Get this properties from address_component

	private String getAddressComponentProperty(String property){
		for(AddressComponentsStub obj : getAddress_components()){
			if(property.equalsIgnoreCase(obj.getTypes()[0])){
				return obj.getLong_name();
			}
		}
		return null;
	}
	
	private String getAddressComponentPropertyShort(String property){
		for(AddressComponentsStub obj : getAddress_components()){
			if(property.equalsIgnoreCase(obj.getTypes()[0])){
				return obj.getShort_name();
			}
		}
		return null;
	}
	
	public String getRua(){
		return getAddressComponentProperty("route");
	}
	
	public String getNumero(){
		return getAddressComponentProperty("street_number");
	}
	
	public String getBairro(){
		return getAddressComponentProperty("sublocality_level_1");
	}
	
	public String getCidade(){
		return getAddressComponentProperty("locality");
	}
	
	public String getUF(){
		return getAddressComponentProperty("administrative_area_level_1");
	}
	
	public String getAbvUF(){
		return getAddressComponentPropertyShort("administrative_area_level_1");
	}
	
	public String getPais(){
		return getAddressComponentProperty("country");		
	}
	
	public String getCep(){
		return getAddressComponentProperty("postal_code");
	}
		
}
