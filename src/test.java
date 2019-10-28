import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import clases.Clase;
import clases.Grupo_sanguineo;
import clases.Licencia;
import clases.Titular;
import clases.UsuarioAdministrador;

public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		GestorBaseDeDatos bd=new GestorBaseDeDatos();
		
		Titular t=new Titular();
		t.setApellido("Vola");
		t.setNombre("Alejandro");
		t.setDireccion("Norte");
		t.setDni(40450769);
		t.setSangre(Grupo_sanguineo.A_NEGATIVO);
		t.setDonante_de_organos(true);
		
		Date fn = new GregorianCalendar(1997, Calendar.FEBRUARY, 3).getTime();	
		t.setFecha_de_nacimiento(fn);
		
		UsuarioAdministrador u=new UsuarioAdministrador();
		u.setUsuario("aleeevola");
		//ATENCION    ahre
		//Si el usuario no esta cargado en la BD antes hay que agregarlo con el comando de abajo
		//int oo=bd.guardarUsuarioAdministrador(u);
		
		Licencia l1 = new Licencia();
		l1.setClase(Clase.D);
		l1.setFecha_de_vencimiento(fn);
		l1.setNumero_de_copias(3);
		l1.setEmitidoPor(u);
		
		t.addLicencia(l1);
		
		//int o=bd.guardarTitular(t);
	}

}
