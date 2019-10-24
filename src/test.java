import java.util.ArrayList;
import java.util.Date;
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
		t.setApellido("carcox");
		t.setDireccion("aaa");
		t.setDni(45515);
		t.setDondante_de_organos(false);
		
		Date fn=new Date();
		fn.setYear(1997);
		fn.setMonth(6);
		fn.setDate(7);
		
		t.setFecha_de_nacimiento(fn);
		t.setNombre("Bruno");
		t.setSangre(Grupo_sanguineo.A_NEGATIVO);
		
		
		Licencia l1 = new Licencia();
		l1.setClase(Clase.A);
		l1.setFecha_de_vencimiento(fn);
		l1.setNumero_de_copias(0);
		
		Licencia l2 = new Licencia();
		l2.setClase(Clase.F);
		l2.setFecha_de_vencimiento(fn);
		l2.setNumero_de_copias(3);
		
		
		List<Licencia> lista=new ArrayList();
		lista.add(l2);
		lista.add(l1);
		t.setLicencias(lista);
		
		
		int o=bd.guardarTitular(t);
	}

}
