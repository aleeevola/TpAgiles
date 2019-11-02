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
				 * Menores de 21 años: si obtienen por primera vez, vigencia 1 año.
				 */
				vigencia = 1;

			} else {
				/*
				 * Menores de 21 años: si ya obtuvieron otras licencias, vigencia 3 años.
				 */
				vigencia = 3;

			}
		} // Fin if(edad<21)
		else {
			if (edad <= 46) {
				/*
				 * Hasta 46 años: vigencia 5 años.
				 */
				vigencia = 5;

			} else {
				if (edad <= 60) {
					/*
					 * Hasta 60 años: vigencia 4 años.
					 */
					vigencia = 4;
				} else {
					if (edad <= 70) {
						/*
						 * Hasta 70 años: vigencia 3 años.
						 */
						vigencia = 3;
					} else {
						/*
						 * Mayores de 70 años: vigencia 1 año.
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
		
	 
	        // Cálculo de las diferencias.
	        int anio = fechaActual.get(Calendar.YEAR) - fechaNacimiento.get(Calendar.YEAR);
	        int mes = fechaActual.get(Calendar.MONTH) - fechaNacimiento.get(Calendar.MONTH);
	        int dia = fechaActual.get(Calendar.DAY_OF_MONTH) - fechaNacimiento.get(Calendar.DAY_OF_MONTH);
	 
	        // Hay que comprobar si el día de su cumpleaños es posterior
	        // a la fecha actual, para restar 1 a la diferencia de años,
	        // pues aún no ha sido su cumpleaños.
	 
	        if(mes < 0 // Aún no es el mes de su cumpleaños
	           || (mes==0 && dia < 0)) { // o es el mes pero no ha llegado el día.
	            anio--;
	        }
	        
	        System.out.println("EDAD "+anio);
	        return anio;
	}

}
