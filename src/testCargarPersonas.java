import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import auxiliares.GestorBaseDeDatos;
import clases.Persona;

public class testCargarPersonas {

	public static void main(String[] args) {
		
		//EJEMPLO DE COMO CARGAR PERSONAS
	
		GestorBaseDeDatos bd= new GestorBaseDeDatos();
		
		Persona p1= new Persona();
		p1.setDni(40450769);
		p1.setApellido("Vola");
		p1.setNombre("Alejandro Bruno");
		p1.setDireccion("Norte");
		Date fn1= new GregorianCalendar(1997, Calendar.JUNE, 7).getTime();	
		p1.setFecha_de_nacimiento(fn1);
		//bd.guardarPersona(p1);
		
		Persona p2= new Persona();
		p2.setDni(40450769);
		p2.setApellido("Perez");
		p2.setNombre("Esteban");
		p2.setDireccion("9 de julio 1720");
		Date fn2= new GregorianCalendar(2000, Calendar.OCTOBER, 20).getTime();	
		p2.setFecha_de_nacimiento(fn2);
		//bd.guardarPersona(p2);
		
		Persona p3= new Persona();
		p3.setDni(180549123);
		p3.setApellido("Rodiguez");
		p3.setNombre("Marcos");
		p3.setDireccion("Junin 1253");
		Date fn3= new GregorianCalendar(1978, Calendar.DECEMBER, 24).getTime();	
		p3.setFecha_de_nacimiento(fn3);
		//bd.guardarPersona(p3);
		
	}

}
