import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import clases.Licencia;

public class testVigencia {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		GestorDeLicencia gl=new GestorDeLicencia();
		Date fechaNacimiento = new Date();
		Date fev = new Date();
		ArrayList<Licencia> historialLicencias= new ArrayList();
		
		
		System.out.println("----- menor 21 sin licencias-----");
		fechaNacimiento = new GregorianCalendar(2001, Calendar.JULY, 17).getTime();
		System.out.println("Fecha nacimiento: "+fechaNacimiento.toString());
		
		fev=gl.calcularVigencia(fechaNacimiento, historialLicencias);
		System.out.println("Fecha vencimiento:"+fev.toString());
		
		System.out.println("----- menor 21 con licencias-----");
		fechaNacimiento = new GregorianCalendar(2001, Calendar.JULY, 17).getTime();
		System.out.println("Fecha nacimiento: "+fechaNacimiento.toString());
		
		historialLicencias.add(new Licencia());
		
		fev=gl.calcularVigencia(fechaNacimiento, historialLicencias);
		System.out.println("Fecha vencimiento:"+fev.toString());
		
		
		System.out.println("----- mayor 21 -----");
		fechaNacimiento = new GregorianCalendar(1997, Calendar.JUNE, 7).getTime();
		System.out.println("Fecha nacimiento: "+fechaNacimiento.toString());
		
		fev=gl.calcularVigencia(fechaNacimiento, historialLicencias);
		System.out.println("Fecha vencimiento:"+fev.toString());
		
		System.out.println("----- 60 anios -----");
		fechaNacimiento = new GregorianCalendar(1959, Calendar.NOVEMBER, 1).getTime();
		System.out.println("Fecha nacimiento: "+fechaNacimiento.toString());
		
		fev=gl.calcularVigencia(fechaNacimiento, historialLicencias);
		System.out.println("Fecha vencimiento:"+fev.toString());
		
		System.out.println("----- 70 anios -----");
		fechaNacimiento = new GregorianCalendar(1949, Calendar.NOVEMBER, 1).getTime();
		System.out.println("Fecha nacimiento: "+fechaNacimiento.toString());
		
		fev=gl.calcularVigencia(fechaNacimiento, historialLicencias);
		System.out.println("Fecha vencimiento:"+fev.toString());
		
		System.out.println("----- +70 anios -----");
		fechaNacimiento = new GregorianCalendar(1900, Calendar.NOVEMBER, 1).getTime();
		System.out.println("Fecha nacimiento: "+fechaNacimiento.toString());
		
		fev=gl.calcularVigencia(fechaNacimiento, historialLicencias);
		System.out.println("Fecha vencimiento:"+fev.toString());
	}

}
