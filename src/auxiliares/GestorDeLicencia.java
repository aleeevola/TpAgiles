package auxiliares;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import clases.Clase;
import clases.Licencia;
import clases.Persona;
import clases.Titular;
import interfacesGraficas.FrameImprimirComprobante;
import interfacesGraficas.PanelImprimirLicencia;



public class GestorDeLicencia {

	private GestorBaseDeDatos bd= new GestorBaseDeDatos();
	
	Date fechaActual = new Date();
	
	public GestorDeLicencia() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	/*
	 * Este metodo devueve:
	 * -1 si no se puede cargar la licencia
	 * 0 si se cargo correctamente
	 * 1 para llamar al panel dar de alta y usar el metodo darDeAltaNuevoTitular
	 **/
	public int emitirLicencia(Clase clase, Persona persona) {
		
		//Datos titular
		int dni=persona.getDni();
		Date fecha_de_nacimiento=persona.getFecha_de_nacimiento();
		String nombre=persona.getNombre();
		String apellido=persona.getApellido();
		
		GregorianCalendar CalendarFecha_de_nacimiento = new GregorianCalendar();
		CalendarFecha_de_nacimiento.setTime(fecha_de_nacimiento);
		
		Integer edad=this._calcularEdad(CalendarFecha_de_nacimiento);
		
		if (edad>=17) {
		
		ArrayList<Titular> titulares=bd.getTitular(dni, apellido, nombre);
		
		Date fecha_de_vencimiento = new Date();
		
		if(titulares.isEmpty()) {
			System.out.println("No existe");
			
			if(clase==Clase.A || clase==Clase.B || clase==Clase.F || clase==Clase.G) { 
					
					System.out.println("retorna 1");
					
					return 1;
			} else { 
				/* No existe el titular en la base de datos, por lo que si solicit� licencia C/D/E no cumple el requisito "tener una licencia clase B de al menos un a�o" */
				JOptionPane.showMessageDialog(null, "Debe tener una licencia clase B de al menos un a�o para solicitar clase"+clase, "Error", JOptionPane.OK_OPTION);

				return -1;
				} 
		}
		else {
			System.out.println("existe");
			Titular titular=titulares.get(0);
			if(_tieneLicencia(titular.getLicencias(),clase)) {
				JOptionPane.showMessageDialog(null, "Ya posee una licencia, debe hacer el tramite de renovaci�n", "Error", JOptionPane.OK_OPTION);
				/* Si ya tiene una licencia de la clase solicitada debe hacer el tramite de renovacion de licencia, no emision. */
				return -1;  
			}
			else {

			if(clase==Clase.C || clase==Clase.D || clase==Clase.E){
					if((20<edad && edad<66) && _licenciaB1a�o(titular.getLicencias())) {
						fecha_de_vencimiento=this.calcularVigencia(fecha_de_nacimiento, titular.getLicencias().size());
						
						Licencia licencia = new Licencia();
						licencia.setClase(clase);
						licencia.setNumero_de_copias(0);
						licencia.setFecha_de_vencimiento(fecha_de_vencimiento);
						licencia.setFecha_de_emision(fechaActual);
						
						titular.addLicencia(licencia);
						
						bd.updateTitular(titular);
						
						JOptionPane.showMessageDialog(null, "Licencia asignada con �xito. Su licencia y comprobante se est�n imprimiendo...", "Licencia Emitida", JOptionPane.INFORMATION_MESSAGE);
						
						try {
							this.imprimirLicencia(titular, licencia);
							this.imprimirComprobante(titular, licencia);
						} catch (Exception e) {
							e.printStackTrace();
						}
						
						return 0;
					}else { 
						
						/* Se solicito una clase C/D/E pero no cumple requisitos de edad y/o de licencias anteriores, retorna false */
						JOptionPane.showMessageDialog(null, "Para solicitar una licencia clase "+clase+" debe tener entre 21 y 65 a�os, y tener una licencia B de al menos un a�o.", "Error", JOptionPane.OK_OPTION);
					
						return -1;
					}
				
				
			}else {
				/* Se solicito licencia A/B/F/G/H y tiene al menos 17 a�os --> se emite la licencia */
			
		
			fecha_de_vencimiento=this.calcularVigencia(fecha_de_nacimiento, titular.getLicencias().size());
			
			Licencia licencia = new Licencia();
			licencia.setClase(clase);
			licencia.setNumero_de_copias(0);
			licencia.setFecha_de_vencimiento(fecha_de_vencimiento);
			licencia.setFecha_de_emision(fechaActual);
			
			titular.addLicencia(licencia);
			
			bd.updateTitular(titular);
			
			JOptionPane.showMessageDialog(null, "Licencia asignada con �xito. Su licencia y comprobante se est�n imprimiendo...", "Licencia Emitida", JOptionPane.INFORMATION_MESSAGE);
			
			try {
				this.imprimirLicencia(titular, licencia);
				this.imprimirComprobante(titular, licencia);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return 0;
			}	
		}
		}
		} else { /* Es menor de 17 a�os, no puede obtener ninguna licencia */
			JOptionPane.showMessageDialog(null, "Debe tener al menos 17 a�os para solicitar una licencia.", "Error", JOptionPane.OK_OPTION);
			return -1;
			}
	}
	
	

	private boolean _tieneLicencia(List<Licencia> licencias, Clase clase) {
		for(Licencia l : licencias) {
			if(l.getClase()==clase) {
				return true;
			}
		}
		return false;
	}


	public boolean _licenciaB1a�o(List<Licencia> licencias) {
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

	
	public void darDeAltaNuevoTitular(Clase clase,Titular titular) {
		
		Licencia licencia = new Licencia();
		
		try { 
			
			Date fecha_de_vencimiento=this.calcularVigencia(titular.getFecha_de_nacimiento(), 0);
			licencia.setClase(clase);
			licencia.setNumero_de_copias(0);
			licencia.setFecha_de_vencimiento(fecha_de_vencimiento);
			licencia.setFecha_de_emision(fechaActual);
			
			titular.addLicencia(licencia);
			
			bd.guardarTitular(titular);
	
			String Alerta="El titular "+titular.getApellido()+", "+titular.getNombre()+" fue creado con �xito";
	
			JOptionPane.showMessageDialog(null, Alerta, "Operaci�n Exitosa", JOptionPane.INFORMATION_MESSAGE);
	
			JOptionPane.showMessageDialog(null, "Licencia asignada con �xito. Su licencia y comprobante se est�n imprimiendo...", "Licencia Emitida", JOptionPane.INFORMATION_MESSAGE);
			
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "No se pudo emitir la licencia", "Licencia Emitida", JOptionPane.OK_OPTION);
		}
		
		try {
			this.imprimirLicencia(titular, licencia);
			this.imprimirComprobante(titular, licencia);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void imprimirLicencia(Titular titular, Licencia licencia) throws Exception {
		
		 // Imprime la licencia
		JFrame newFrame = new JFrame();
		PanelImprimirLicencia licenciaImpresa = new PanelImprimirLicencia();
		
		newFrame.setSize(750, 500);
		newFrame.setVisible(true);
		
		licenciaImpresa.cargarImagen(licencia, titular);

		newFrame.setContentPane(licenciaImpresa);
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        newFrame.setLocation(dim.width/2- newFrame.getSize().width/2, dim.height/2- newFrame.getSize().height/2);
	}
	
	public void imprimirComprobante(Titular titular, Licencia licencia) {
		
        // Imprime el comprobante
        FrameImprimirComprobante comprobante = new FrameImprimirComprobante();
        
        Double costoLicencia = this._calcularCostoLicencia(licencia);
        
        comprobante.cargarDatos(titular, licencia, costoLicencia);
        
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        comprobante.setLocation(dim.width/2- comprobante.getSize().width/2, dim.height/2- comprobante.getSize().height/2);
	}
	
	public Double _calcularCostoLicencia(Licencia licencia) {
		
		Double costo;
		
		int anioEmision = licencia.getFecha_de_emision().getYear();
		int anioVencimiento = licencia.getFecha_de_vencimiento().getYear();
		int vigencia = anioVencimiento-anioEmision;
		
		Clase clase = licencia.getClase();
		
		switch(clase) {
		
		case A:
		case B:
		case G:
			if(vigencia == 5) {
				costo = 40.0;
			} else if(vigencia == 4) {
				costo = 30.0;
			} else if(vigencia == 3) {
				costo = 25.0;
			} else {
				costo = 20.0;
			}
			break;
		case C:
			if(vigencia == 5) {
				costo = 47.0;
			} else if(vigencia == 4) {
				costo = 35.0;
			} else if(vigencia == 3) {
				costo = 30.0;
			} else {
				costo = 23.0;
			}
			break;
		case E:
			if(vigencia == 5) {
				costo = 59.0;
			} else if(vigencia == 4) {
				costo = 44.0;
			} else if(vigencia == 3) {
				costo = 39.0;
			} else {
				costo = 29.0;
			}
			break;
		default:
			costo = 0.0;
			break;
		}
		return costo;
	}
}
