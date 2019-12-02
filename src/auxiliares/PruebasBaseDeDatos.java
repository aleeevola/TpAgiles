package auxiliares;
import org.junit.*;

import clases.Clase;
import clases.Grupo_sanguineo;
import clases.Licencia;
import clases.Persona;
import clases.Titular;
import clases.UsuarioAdministrador;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class PruebasBaseDeDatos {
	@Test
	public void testGuardarYRecuperarTitularConLicencia() {
		GestorBaseDeDatos gestorDB = new GestorBaseDeDatos();
		UsuarioAdministrador user = new UsuarioAdministrador();
		user.setUsuario("aleeevola");
		
		Licencia licencia = new Licencia();
		Date fechaEmision = new GregorianCalendar(2019,05,19).getTime();
		Date fechaVencimiento = new GregorianCalendar(2024,05,19).getTime();
		licencia.setEmitidoPor(user);
		licencia.setClase(Clase.B);
		licencia.setNumero_de_copias(0);
		licencia.setId(1);
		licencia.setFecha_de_emision(fechaEmision);
		licencia.setFecha_de_vencimiento(fechaVencimiento);
		List<Licencia> licencias = new ArrayList<Licencia>();
		licencias.add(licencia);
		
		Titular titular = new Titular();
		titular.setNombre("Franco");
		titular.setApellido("Brechbuhl");
		titular.setDni(39630386);
		titular.setDonante_de_organos(false);
		titular.setDireccion("Cullen 923");
		Date fechaNacimiento = new GregorianCalendar(1997,05,19).getTime();
		titular.setFecha_de_nacimiento(fechaNacimiento);
		titular.setSangre(Grupo_sanguineo.A_POSITIVO);
		titular.setLicencias(licencias);
		
		gestorDB.guardarTitular(titular);
		gestorDB.guardarLicencia(licencia);
		
		List<Titular> titularRetornado = gestorDB.getTitular(39630386);
		List<Licencia> licenciasRetornadas = titularRetornado.get(0).getLicencias();
		Licencia licenciaRetornada = licenciasRetornadas.get(0);
		
		assertEquals(titularRetornado, titular);
		assertEquals(licenciaRetornada, licencia);
	}
	
	@Test
	public void testGuardarYRecuperarPersona() {
		GestorBaseDeDatos gestorDB = new GestorBaseDeDatos();
		
		Persona persona = new Persona();
		persona.setNombre("Gabriel");
		persona.setApellido("Barbosa");
		persona.setDireccion("Belgrano 2011");
		persona.setDni(36976206);
		persona.setId(9);
		
		gestorDB.guardarPersona(persona);
		
		List<Persona> personasBuscadas= gestorDB.getPersonas(36976206);
		Persona personaBuscada = personasBuscadas.get(0);
		assertEquals(persona, personaBuscada);
	}


}
