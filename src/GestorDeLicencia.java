import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import clases.Licencia;

public class GestorDeLicencia {

	public GestorDeLicencia() {
		super();
		// TODO Auto-generated constructor stub
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
