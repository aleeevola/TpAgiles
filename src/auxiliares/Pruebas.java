package auxiliares;
import org.junit.*;

import clases.Clase;
import clases.Licencia;

import static org.junit.Assert.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class Pruebas {
	@Test
	public void testCalcularVigencia01() { //se prueba el método calcularLicencia() con una persona mayor de 18 sin licencias anteriores
		GestorDeLicencia gestorL = new GestorDeLicencia();
		Date fechaNacimiento = new GregorianCalendar(2000,10,21).getTime();
		int cantLicencias = 0;
		Date fechaVencimiento = new GregorianCalendar(2019+1,10,21).getTime();
		Date fechaVencimientoCalculada = gestorL.calcularVigencia(fechaNacimiento, cantLicencias);
		assertEquals(fechaVencimientoCalculada, fechaVencimiento);		
	}
	
	@Test
	public void testCalcularVigencia02() { //se prueba el método calcularLicencia() con una persona mayor de 18 con una licencia anterior
		GestorDeLicencia gestorL = new GestorDeLicencia();
		Date fechaNacimiento = new GregorianCalendar(2000,10,21).getTime();
		int cantLicencias = 1;
		Date fechaVencimiento = new GregorianCalendar(2019+3,10,21).getTime();
		Date fechaVencimientoCalculada = gestorL.calcularVigencia(fechaNacimiento, cantLicencias);
		assertEquals(fechaVencimientoCalculada, fechaVencimiento);		
	}
	
	@Test
	public void testCalcularVigencia03() { //se prueba el método calcularLicencia() con una persona mayor a 21 y menor a 46
		GestorDeLicencia gestorL = new GestorDeLicencia();
		Date fechaNacimiento = new GregorianCalendar(1989,10,21).getTime();
		int cantLicencias = 0;
		Date fechaVencimiento = new GregorianCalendar(2019+5,10,21).getTime();
		Date fechaVencimientoCalculada = gestorL.calcularVigencia(fechaNacimiento, cantLicencias);
		assertEquals(fechaVencimientoCalculada, fechaVencimiento);		
	}
	
	@Test
	public void testCalcularVigencia04() { //se prueba el método calcularLicencia() con una persona mayor a 46 y menor a 60
		GestorDeLicencia gestorL = new GestorDeLicencia();
		Date fechaNacimiento = new GregorianCalendar(1969,10,21).getTime();
		int cantLicencias = 0;
		Date fechaVencimiento = new GregorianCalendar(2019+4,10,21).getTime();
		Date fechaVencimientoCalculada = gestorL.calcularVigencia(fechaNacimiento, cantLicencias);
		assertEquals(fechaVencimientoCalculada, fechaVencimiento);		
	}
	
	@Test
	public void testCalcularVigencia05() { //se prueba el método calcularLicencia() con una persona mayor a 60 y menor a 70
		GestorDeLicencia gestorL = new GestorDeLicencia();
		Date fechaNacimiento = new GregorianCalendar(1954,10,21).getTime();
		int cantLicencias = 0;
		Date fechaVencimiento = new GregorianCalendar(2019+3,10,21).getTime();
		Date fechaVencimientoCalculada = gestorL.calcularVigencia(fechaNacimiento, cantLicencias);
		assertEquals(fechaVencimientoCalculada, fechaVencimiento);		
	}
	
	@Test
	public void testCalcularVigencia06() { //se prueba el método calcularLicencia() con una persona mayor a 70
		GestorDeLicencia gestorL = new GestorDeLicencia();
		Date fechaNacimiento = new GregorianCalendar(1944,10,21).getTime();
		int cantLicencias = 0;
		Date fechaVencimiento = new GregorianCalendar(2019+1,10,21).getTime();
		Date fechaVencimientoCalculada = gestorL.calcularVigencia(fechaNacimiento, cantLicencias);
		assertEquals(fechaVencimientoCalculada, fechaVencimiento);		
	}
	
	@Test
	public void testLicenciaB1Año01() { //se prueba el método _licenciaB1año() con un titular de licencia B
		GestorDeLicencia gestorL = new GestorDeLicencia();
		Licencia licencia = new Licencia();
		licencia.setClase(Clase.B);
		licencia.setFecha_de_emision(new GregorianCalendar(2017,02,13).getTime());
		List<Licencia> licencias = new ArrayList<Licencia>();
		licencias.add(licencia);
		boolean tieneLicenciaCalculada = gestorL._licenciaB1año(licencias);
		assertTrue(tieneLicenciaCalculada);
	}
	
	@Test
	public void testLicenciaB1Año02() { //se prueba el método _licenciaB1año() con un titular que no tiene licencias
		GestorDeLicencia gestorL = new GestorDeLicencia();
		List<Licencia> licencias = new ArrayList<Licencia>();
		boolean tieneLicenciaCalculada = gestorL._licenciaB1año(licencias);
		assertFalse(tieneLicenciaCalculada);
	}
	
	@Test
	public void testCalcularCostoLicencia01() { //se prueba el método _calcularCostoLicencia() con clase A y una vigencia de 5 años
		GestorDeLicencia gestorL = new GestorDeLicencia();
		Licencia licencia = new Licencia();
		Date fechaEmision = new GregorianCalendar(2019,10,29).getTime();
		Date fechaVencimiento = new GregorianCalendar(2024,10,29).getTime();
		licencia.setFecha_de_emision(fechaEmision);
		licencia.setFecha_de_vencimiento(fechaVencimiento);
		licencia.setClase(Clase.A);
		Double costoLicencia = 40.0;
		Double costoLicenciaCalculado = gestorL._calcularCostoLicencia(licencia);
		assertEquals(costoLicencia, costoLicenciaCalculado);
	}
	
	@Test
	public void testCalcularCostoLicencia02() { //se prueba el método _calcularCostoLicencia() con clase G y una vigencia de 1 años
		GestorDeLicencia gestorL = new GestorDeLicencia();
		Licencia licencia = new Licencia();
		Date fechaEmision = new GregorianCalendar(2019,10,29).getTime();
		Date fechaVencimiento = new GregorianCalendar(2020,10,29).getTime();
		licencia.setFecha_de_emision(fechaEmision);
		licencia.setFecha_de_vencimiento(fechaVencimiento);
		licencia.setClase(Clase.G);
		Double costoLicencia = 20.0;
		Double costoLicenciaCalculado = gestorL._calcularCostoLicencia(licencia);
		assertEquals(costoLicencia, costoLicenciaCalculado);
	}
	
	@Test
	public void testCalcularCostoLicencia03() { //se prueba el método _calcularCostoLicencia() con clase E y una vigencia de 4 años
		GestorDeLicencia gestorL = new GestorDeLicencia();
		Licencia licencia = new Licencia();
		Date fechaEmision = new GregorianCalendar(2019,10,29).getTime();
		Date fechaVencimiento = new GregorianCalendar(2023,10,29).getTime();
		licencia.setFecha_de_emision(fechaEmision);
		licencia.setFecha_de_vencimiento(fechaVencimiento);
		licencia.setClase(Clase.E);
		Double costoLicencia = 44.0;
		Double costoLicenciaCalculado = gestorL._calcularCostoLicencia(licencia);
		assertEquals(costoLicencia, costoLicenciaCalculado);
	}

	@Test
	public void testCalcularCostoLicencia04() { //se prueba el método _calcularCostoLicencia() con clase C y una vigencia de 3 años
		GestorDeLicencia gestorL = new GestorDeLicencia();
		Licencia licencia = new Licencia();
		Date fechaEmision = new GregorianCalendar(2019,10,29).getTime();
		Date fechaVencimiento = new GregorianCalendar(2022,10,29).getTime();
		licencia.setFecha_de_emision(fechaEmision);
		licencia.setFecha_de_vencimiento(fechaVencimiento);
		licencia.setClase(Clase.C);
		Double costoLicencia = 30.0;
		Double costoLicenciaCalculado = gestorL._calcularCostoLicencia(licencia);
		assertEquals(costoLicencia, costoLicenciaCalculado);
	}
	
	
	
}
