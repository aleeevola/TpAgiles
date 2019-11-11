import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import clases.Clase;
import clases.Licencia;
import clases.Titular;

public class GestorDeLicencia {

	public GestorDeLicencia() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Boolean emitirLicencia(Clase clase,int dni, Date fecha_de_nacimiento, String nombre, String apellido) {
		GestorBaseDeDatos bd= new GestorBaseDeDatos();
		
		GregorianCalendar CalendarFecha_de_nacimiento = new GregorianCalendar();
		CalendarFecha_de_nacimiento.setTime(fecha_de_nacimiento);
		
		Integer edad=this._calcularEdad(CalendarFecha_de_nacimiento);
		
		if (edad>=17) {
		
		ArrayList<Titular> titulares=bd.getTitular(dni, apellido, nombre);
		Date fecha_de_vencimiento = new Date();
		
		if(titulares.isEmpty()) {
			System.out.println("No existe");
			
			if(clase==Clase.A || clase==Clase.B || clase==Clase.G) {
					fecha_de_vencimiento=this.calcularVigencia(fecha_de_nacimiento, null);
					
					Licencia licencia = new Licencia();
					licencia.setClase(clase);
					licencia.setNumero_de_copias(0);
					licencia.setFecha_de_vencimiento(fecha_de_vencimiento);
					
					/*
					 * Aca debe llamar a H009-T01  para crear un titular y pasarle la licencia
					 */
					return true;
			} else return false;
			
			
		}
		else {
			if((clase==Clase.C || clase==Clase.D || clase==Clase.E) && 16<edad && edad<66) {
				
			}
			System.out.println("existe");
			Titular titular=titulares.get(0);
			
			fecha_de_vencimiento=this.calcularVigencia(fecha_de_nacimiento, (ArrayList<Licencia>) titular.getLicencias());
			
			Licencia licencia = new Licencia();
			licencia.setClase(clase);
			licencia.setNumero_de_copias(0);
			licencia.setFecha_de_vencimiento(fecha_de_vencimiento);
			
			titular.addLicencia(licencia);
			
			bd.guardarTitular(titular);
			
		}
		} else return false;
		return null;
	}

	public Date calcularVigencia(Date fechaNacimiento, ArrayList<Licencia> historialLicencias) {

		Date fechaVencimiento;
		GregorianCalendar fechaNacimientoCalendar = new GregorianCalendar();
		fechaNacimientoCalendar.setTime(fechaNacimiento);
		Integer anioActual = (new GregorianCalendar()).get(Calendar.YEAR);

		Integer vigencia = 0;
		Integer edad = _calcularEdad(fechaNacimientoCalendar);

		if (edad < 21) {
			if (historialLicencias.isEmpty()) {
				/*
				 * Menores de 21 a�os: si obtienen por primera vez, vigencia 1 a�o.
				 */
				vigencia = 1;

			} else {
				/*
				 * Menores de 21 a�os: si ya obtuvieron otras licencias, vigencia 3 a�os.
				 */
				vigencia = 3;

			}
		} // Fin if(edad<21)
		else {
			if (edad <= 46) {
				/*
				 * Hasta 46 a�os: vigencia 5 a�os.
				 */
				vigencia = 5;

			} else {
				if (edad <= 60) {
					/*
					 * Hasta 60 a�os: vigencia 4 a�os.
					 */
					vigencia = 4;
				} else {
					if (edad <= 70) {
						/*
						 * Hasta 70 a�os: vigencia 3 a�os.
						 */
						vigencia = 3;
					} else {
						/*
						 * Mayores de 70 a�os: vigencia 1 a�o.
						 */
						vigencia = 1;
					}
				}
			}
		}
		fechaVencimiento = new GregorianCalendar((anioActual + vigencia), fechaNacimientoCalendar.get(Calendar.MONTH),
				fechaNacimientoCalendar.get(Calendar.DAY_OF_MONTH)).getTime();
		return fechaVencimiento;
	}

	private Integer _calcularEdad(GregorianCalendar fechaNacimiento) {
		GregorianCalendar fechaActual = new GregorianCalendar();
		
	 
	        // C�lculo de las diferencias.
	        int anio = fechaActual.get(Calendar.YEAR) - fechaNacimiento.get(Calendar.YEAR);
	        int mes = fechaActual.get(Calendar.MONTH) - fechaNacimiento.get(Calendar.MONTH);
	        int dia = fechaActual.get(Calendar.DAY_OF_MONTH) - fechaNacimiento.get(Calendar.DAY_OF_MONTH);
	 
	        // Hay que comprobar si el d�a de su cumplea�os es posterior
	        // a la fecha actual, para restar 1 a la diferencia de a�os,
	        // pues a�n no ha sido su cumplea�os.
	 
	        if(mes < 0 // A�n no es el mes de su cumplea�os
	           || (mes==0 && dia < 0)) { // o es el mes pero no ha llegado el d�a.
	            anio--;
	        }
	        
	        System.out.println("EDAD "+anio);
	        return anio;
	}

}
