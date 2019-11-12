package auxiliares;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import clases.Clase;
import clases.Licencia;
import clases.Titular;
import interfacesGraficas.PanelDarAltaTitular;
import interfacesGraficas.PanelEmitirLicencia;

public class GestorDeLicencia {

	public GestorDeLicencia() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Boolean emitirLicencia(Clase clase,int dni, Date fecha_de_nacimiento, String nombre, String apellido) {
		GestorBaseDeDatos bd= new GestorBaseDeDatos();
		
		Date fechaActual = new Date();
		GregorianCalendar CalendarFecha_de_nacimiento = new GregorianCalendar();
		CalendarFecha_de_nacimiento.setTime(fecha_de_nacimiento);
		
		Integer edad=this._calcularEdad(CalendarFecha_de_nacimiento);
		
		if (edad>=17) {
		
		
		ArrayList<Titular> titulares=bd.getTitular(dni, apellido, nombre);
		
		Date fecha_de_vencimiento = new Date();
		
		if(titulares.isEmpty()) {
			System.out.println("No existe");
			
			if(clase==Clase.A || clase==Clase.B || clase==Clase.G) { //ACA NO HAY QUE AGREGAR TAMBIEN LA CLASE F??
					fecha_de_vencimiento=this.calcularVigencia(fecha_de_nacimiento, 0);
					
					Licencia licencia = new Licencia();
					licencia.setClase(clase);
					licencia.setNumero_de_copias(0);
					licencia.setFecha_de_vencimiento(fecha_de_vencimiento);
					licencia.setFecha_de_emision(fechaActual);
					
					/*
					 * Aca debe llamar a H009-T01  para crear un titular y pasarle la licencia
					 */
					Titular titular = darDeAltaTitular(clase,dni,fecha_de_nacimiento,nombre,apellido);
					/*
					 * El metodo anterior debe devolver un objeto titular y 
					 * aca abajo asignarle la licencia y guardarlo*/
					titular.addLicencia(licencia);
					bd.guardarTitular(titular);
					
					return true;
			} else { 
				/* No existe el titular en la base de datos, por lo que si solicitó licencia C/D/E no cumple el requisito "tener una licencia clase B de al menos un año" */
				//IMPRIMIR MENSAJE DE ERROR
				return false;
				} 
			
			
		}
		else {
			System.out.println("existe");
			Titular titular=titulares.get(0);

			if(clase==Clase.C || clase==Clase.D || clase==Clase.E){
					if((20<edad && edad<66) && _licenciaB1año(titular.getLicencias())) {
						fecha_de_vencimiento=this.calcularVigencia(fecha_de_nacimiento, titular.getLicencias().size());
						
						Licencia licencia = new Licencia();
						licencia.setClase(clase);
						licencia.setNumero_de_copias(0);
						licencia.setFecha_de_vencimiento(fecha_de_vencimiento);
						licencia.setFecha_de_emision(fechaActual);
						
						titular.addLicencia(licencia);
						
						bd.updateTitular(titular);
						
						return true;
					}else { 
						
						/* Se solicito una clase C/D/E pero no cumple requisitos de edad y/o de licencias anteriores, retorna false */
						//IMPRIMIR MENSAJE DE ERROR 
						return false;
					}
				
				
			}else {
				/* Se solicito licencia A/B/F/G/H y tiene al menos 17 años --> se emite la licencia */
			
		
			fecha_de_vencimiento=this.calcularVigencia(fecha_de_nacimiento, titular.getLicencias().size());
			
			Licencia licencia = new Licencia();
			licencia.setClase(clase);
			licencia.setNumero_de_copias(0);
			licencia.setFecha_de_vencimiento(fecha_de_vencimiento);
			licencia.setFecha_de_emision(fechaActual);
			
			titular.addLicencia(licencia);
			
			bd.updateTitular(titular);
			
			return true;
			}	
		}
		} else { /* Es menor de 17 años, no puede obtener ninguna licencia */
			return false;
			//IMPRIMIR MENSAJE DE ERROR
			}
	}
	
	public Titular darDeAltaTitular(Clase clase,int dni, Date fecha_de_nacimiento, String nombre, String apellido) {
		/*
		 * Este metodo crea el panel de dar de alta el titular 
		 * y debe devolver un objeto titular
		 * Pero creo que solo se puede con JDialog
		 */
		
		JFrame frame = new JFrame();

		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setSize(800, 800);

		
		//PanelDarAltaTitular altaTitular = new PanelDarAltaTitular(clase,dni, fecha_de_nacimiento, nombre, apellido);
		//frame.setContentPane(altaTitular);
		return null;
	}

	public boolean _licenciaB1año(List<Licencia> licencias) {
		if(licencias.isEmpty()) { //NO SE SI ESTA VALIDACION ESTA BIEN, PORQUE SI EXISTE EL TITULAR NUNCA DEBERIA ESTAR VACIA LA LISTA PERO NO SE
			return false;
		}else {
			Date fechaMin = new Date();
			fechaMin.setYear(((new GregorianCalendar().get(Calendar.YEAR)) - 1901));
			
			for(Licencia l : licencias) {
				if(l.getClase()==Clase.B && (l.getFecha_de_emision().before(fechaMin))) {
					return true;
				}
			}
		}
		return false;
	}

	public Date calcularVigencia(Date fechaNacimiento, int cantLicencias) {

		Date fechaVencimiento;
		GregorianCalendar fechaNacimientoCalendar = new GregorianCalendar();
		fechaNacimientoCalendar.setTime(fechaNacimiento);
		Integer anioActual = (new GregorianCalendar()).get(Calendar.YEAR);

		Integer vigencia = 0;
		Integer edad = _calcularEdad(fechaNacimientoCalendar);

		if (edad < 21) {
			if (cantLicencias==0) {
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
