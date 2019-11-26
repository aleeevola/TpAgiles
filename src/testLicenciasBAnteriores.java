import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import auxiliares.GestorDeLicencia;
import clases.Clase;
import clases.Licencia;

public class testLicenciasBAnteriores {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GestorDeLicencia gl=new GestorDeLicencia();
		Date fechaNacimiento = new Date();
		Date fev = new Date();
		ArrayList<Licencia> historialLicencias= new ArrayList();
		
		Licencia licencia = new Licencia();
		licencia.setClase(Clase.A);
		licencia.setNumero_de_copias(0);
		licencia.setFecha_de_vencimiento(new GregorianCalendar(2020, Calendar.JULY, 17).getTime());
		licencia.setFecha_de_emision(new GregorianCalendar(2019, Calendar.JULY, 17).getTime());
		
		Licencia licencia1 = new Licencia();
		licencia1.setClase(Clase.B);
		licencia1.setNumero_de_copias(0);
		licencia1.setFecha_de_vencimiento(new GregorianCalendar(2020, Calendar.JULY, 17).getTime());
		licencia1.setFecha_de_emision(new GregorianCalendar(2019, Calendar.JULY, 17).getTime());
		
		Licencia licencia2 = new Licencia();
		licencia2.setClase(Clase.B);
		licencia2.setNumero_de_copias(0);
		licencia2.setFecha_de_vencimiento(new GregorianCalendar(2020, Calendar.JULY, 17).getTime());
		licencia2.setFecha_de_emision(new GregorianCalendar(2018, Calendar.NOVEMBER, 17).getTime());
		
		Licencia licencia3 = new Licencia();
		licencia3.setClase(Clase.B);
		licencia3.setNumero_de_copias(0);
		licencia3.setFecha_de_vencimiento(new GregorianCalendar(2020, Calendar.JULY, 17).getTime());
		licencia3.setFecha_de_emision(new GregorianCalendar(2010, Calendar.NOVEMBER, 17).getTime());
		
		Licencia licencia4 = new Licencia();
		licencia4.setClase(Clase.B);
		licencia4.setNumero_de_copias(0);
		licencia4.setFecha_de_vencimiento(new GregorianCalendar(2020, Calendar.JULY, 17).getTime());
		licencia4.setFecha_de_emision(new GregorianCalendar(2018, Calendar.NOVEMBER, 12).getTime());
		
		Licencia licencia5 = new Licencia();
		licencia5.setClase(Clase.B);
		licencia5.setNumero_de_copias(0);
		licencia5.setFecha_de_vencimiento(new GregorianCalendar(2020, Calendar.JULY, 17).getTime());
		licencia5.setFecha_de_emision(new GregorianCalendar(2018, Calendar.NOVEMBER, 13).getTime());
		
		historialLicencias.add(licencia);
		historialLicencias.add(licencia1);
		historialLicencias.add(licencia2);
		historialLicencias.add(licencia3);
		historialLicencias.add(licencia4);
		historialLicencias.add(licencia5);
		
		if(gl._licenciaB1anio(historialLicencias)) System.out.println("DEVOLVIO TRUE");
		

	}

}
