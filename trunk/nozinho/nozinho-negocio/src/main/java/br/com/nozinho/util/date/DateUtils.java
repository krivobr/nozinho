package br.com.nozinho.util.date;

public class DateUtils {

	private DateUtils(){
		
	}
	
	public static String getMes(int mes){
		String[] meses = {"","Jan","Fev","Mar","Abr","Mai","Jun","Jul","Ago","Set","Out","Nov","Dez"};
		return meses[mes];
	}
	
}
