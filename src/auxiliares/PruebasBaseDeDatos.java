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
		Date fechaEmision = new GregorianCalendar(2019,5,19).getTime();
		Date fechaVencimiento = new GregorianCalendar(2024,5,19).getTime();
		licencia.setEmitidoPor(user);
		licencia.setClase(Clase.B);
		licencia.setNumero_de_copias(0);
		licencia.setId(1);
		licencia.setFecha_de_emision(fechaEmision);
		licencia.setFecha_de_vencimiento(fechaVencimiento);
		List<Licencia> licencias = new ArrayList<Licencia>();
		licencias.add(licencia);
		gestorDB.guardarLicencia(licencia);
		
		Titular titular = new Titular();
		titular.setNombre("Franco");
		titular.setApellido("Brechbuhl");
		titular.setDni(39630386);
		titular.setDonante_de_organos(false);
		titular.setDireccion("Cullen 923");
		Date fechaNacimiento = new GregorianCalendar(1997,5,19).getTime();
		titular.setFecha_de_nacimiento(fechaNacimiento);
		titular.setSangre(Grupo_sanguineo.A_POSITIVO);
		titular.setLicencias(licencias);
		
		gestorDB.guardarTitular(titular);
		
		List<Titular> titularesRetornados = gestorDB.getTitular(39630386);
		Titular titularRetornado = titularesRetornados.get(0);
		List<Licencia> licenciasRetornadas = titularRetornado.getLicencias();
		Licencia licenciaRetornada = licenciasRetornadas.get(0);
		
		assertEquals(titularRetornado.getApellido(), titular.getApellido());
		assertEquals(titularRetornado.getNombre(), titular.getNombre());
		assertEquals(titularRetornado.getDireccion(), titular.getDireccion());
		assertEquals(titularRetornado.getDni(), titular.getDni());
		assertEquals(titularRetornado.getFecha_de_nacimiento().getYear(), titular.getFecha_de_nacimiento().getYear());
		assertEquals(titularRetornado.getFecha_de_nacimiento().getMonth(), titular.getFecha_de_nacimiento().getMonth());
		assertEquals(titularRetornado.getFecha_de_nacimiento().getDay(), titular.getFecha_de_nacimiento().getDay());
		assertEquals(titularRetornado.getApellido(), titular.getSangre());
		assertFalse(titularRetornado.getDonante_de_organos());
		assertEquals(licenciaRetornada.getClase(), licencia.getClase());
		assertEquals(licenciaRetornada.getFecha_de_emision(), licencia.getFecha_de_emision());
		assertEquals(licenciaRetornada.getFecha_de_vencimiento(), licencia.getFecha_de_vencimiento());
		assertEquals(licenciaRetornada.getNumero_de_copias(), licencia.getNumero_de_copias());
	}
	
	@Test
	public void testGuardarYRecuperarPersona() {
		GestorBaseDeDatos gestorDB = new GestorBaseDeDatos();
		
		Persona persona = new Persona();
		persona.setNombre("Gabriel");
		persona.setApellido("Barbosa");
		persona.setDireccion("Belgrano 2011");
		persona.setDni(36976206);
		
		gestorDB.guardarPersona(persona);
		
		List<Persona> personasBuscadas= gestorDB.getPersonas(36976206);
		Persona personaBuscada = personasBuscadas.get(0);
		assertEquals(persona.getNombre(), personaBuscada.getNombre());
		assertEquals(persona.getApellido(), personaBuscada.getApellido());
		assertEquals(persona.getDni(), personaBuscada.getDni());
		assertEquals(persona.getDireccion(), personaBuscada.getDireccion());
	}
	
	@Test
	public void testLicenciasExpiradas() {
		GestorBaseDeDatos gestorDB = new GestorBaseDeDatos();
		
		UsuarioAdministrador user = new UsuarioAdministrador();
		user.setUsuario("aleeevola");
		
		Licencia licencia = new Licencia();
		Date fechaEmision = new GregorianCalendar(2014,5,19).getTime();
		Date fechaVencimiento = new GregorianCalendar(2019,5,18).getTime();
		licencia.setEmitidoPor(user);
		licencia.setClase(Clase.B);
		licencia.setNumero_de_copias(0);
		licencia.setId(1);
		licencia.setFecha_de_emision(fechaEmision);
		licencia.setFecha_de_vencimiento(fechaVencimiento);
		List<Licencia> licencias = new ArrayList<Licencia>();
		licencias.add(licencia);
		gestorDB.guardarLicencia(licencia);
		
		List<LicenciaExpirada> expiradasRetornadas = gestorDB.getLicenciasExpiradas();
		LicenciaExpirada licenciaExpirada = expiradasRetornadas.get(0);
		assertEquals(licencia.getClase(), licenciaExpirada.getClase());
		assertEquals(licencia.getFecha_de_vencimiento().getYear(), licenciaExpirada.getFecha_de_vencimiento().getYear());
		assertEquals(licencia.getFecha_de_vencimiento().getMonth(), licenciaExpirada.getFecha_de_vencimiento().getMonth());
		assertEquals(licencia.getFecha_de_vencimiento().getDay(), licenciaExpirada.getFecha_de_vencimiento().getDay());
	}

}
