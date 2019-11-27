import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import auxiliares.GestorBaseDeDatos;
import clases.Persona;

public class testCargarPersonas {

	public static void main(String[] args) {
		
		//EJEMPLO DE COMO CARGAR PERSONAS
	
		GestorBaseDeDatos bd= new GestorBaseDeDatos();
		
		//mismo dni mayor que 21
		
		Persona p1= new Persona();
		p1.setDni(40450769);
		p1.setApellido("Vola");
		p1.setNombre("Mayor 21");
		p1.setDireccion("Hernandarias 4533");
		Date fn1= new GregorianCalendar(1997, Calendar.JUNE, 7).getTime();	
		p1.setFecha_de_nacimiento(fn1);
		//bd.guardarPersona(p1);
		
		//mismo dni menor que 21
		Persona p3= new Persona();
		p3.setDni(40450769);
		p3.setApellido("Rodriguez");
		p3.setNombre("Menor 21");
		p3.setDireccion("Buenos Aires 2011");
		Date fn= new GregorianCalendar(1999, Calendar.SEPTEMBER, 10).getTime();	
		p3.setFecha_de_nacimiento(fn);
		//bd.guardarPersona(p3);
		
		//MENOR DE 17
		Persona p2= new Persona();
		p2.setDni(46000000);
		p2.setApellido("Perez");
		p2.setNombre("menor 17");
		p2.setDireccion("9 de julio 1720");
		Date fn2= new GregorianCalendar(2003, Calendar.OCTOBER, 20).getTime();	
		p2.setFecha_de_nacimiento(fn2);
		//bd.guardarPersona(p2);
		
		//mayor 65
		Persona p4= new Persona();
		p4.setDni(180549123);
		p4.setApellido("Acosta");
		p4.setNombre("mayor 65");
		p4.setDireccion("Junin 1253");
		Date fn4= new GregorianCalendar(1950, Calendar.MARCH, 24).getTime();	
		p4.setFecha_de_nacimiento(fn4);
		//bd.guardarPersona(p4);
		
		
		//tiene una B CON 1 AÑO
		Persona p5= new Persona();
		p5.setDni(180549123);
		p5.setApellido("Sánchez");
		p5.setNombre("tine 1 b vieja");
		p5.setDireccion("Junin 1253");
		Date fn5= new GregorianCalendar(1997, Calendar.MARCH, 4).getTime();	
		p5.setFecha_de_nacimiento(fn5);
		//bd.guardarPersona(p5);
		
		//tienen vencidas
		Persona p6= new Persona();
		p6.setDni(3);
		p6.setApellido("Fernandez");
		p6.setNombre("tine vencida");
		p6.setDireccion("Junin 1253");
		Date fn6= new GregorianCalendar(1997, Calendar.MARCH, 4).getTime();	
		p6.setFecha_de_nacimiento(fn6);
		//bd.guardarPersona(p6);
		
		//tienen vencidas
		Persona p7= new Persona();
		p7.setDni(4);
		p7.setApellido("Gomez");
		p7.setNombre("tine vencida");
		p7.setDireccion("Junin 1253");
		Date fn7= new GregorianCalendar(1997, Calendar.MARCH, 4).getTime();	
		p7.setFecha_de_nacimiento(fn7);
		//bd.guardarPersona(p7);
	}

}
