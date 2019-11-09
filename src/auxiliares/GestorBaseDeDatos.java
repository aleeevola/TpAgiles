package auxiliares;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

import com.mysql.cj.SimpleQuery;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import clases.Licencia;
import clases.Persona;
import clases.Titular;
import clases.UsuarioAdministrador;

public class GestorBaseDeDatos {

	public GestorBaseDeDatos() {

	}


	public UsuarioAdministrador buscarUsuarioAdministrador(int idusuario) {
		// crear factory

		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(UsuarioAdministrador.class)
				.buildSessionFactory();

		// crear sesión

		Session session = factory.getCurrentSession();
		// usar el objeto session
		session.beginTransaction();
		UsuarioAdministrador u = session.get(UsuarioAdministrador.class, idusuario);
		session.getTransaction().commit();
		session.close();
		factory.close();
		
		return u;
	}


	public int guardarLicencia(Licencia l) {
		// crear factory

		SessionFactory sf = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
		// crear sesión
		Session session = sf.openSession();

		// usar el objeto session

		session.beginTransaction();

		int idt = (int) session.save(l);

		session.getTransaction().commit();
		session.close();
		sf.close();

		return idt;
	}
	
	public int guardarTitular(Titular t) {
		// crear factory

		SessionFactory sf = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Titular.class).buildSessionFactory();
		// crear sesión
		Session session = sf.openSession();

		// usar el objeto session

		session.beginTransaction();

		int idt = (int) session.save(t);

		session.getTransaction().commit();
		session.close();
		sf.close();

		return idt;
	}
	
	public int guardarUsuarioAdministrador(UsuarioAdministrador t) {
		// crear factory

		SessionFactory sf = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(UsuarioAdministrador.class).buildSessionFactory();
		// crear sesión
		Session session = sf.openSession();

		// usar el objeto session

		session.beginTransaction();

		session.save(t);

		session.getTransaction().commit();
		session.close();
		sf.close();

		return 1;
	}
	
	public void guardarPersona(Persona p) {
		// crear factory

		SessionFactory sf = new Configuration().configure("hibernate2.cfg.xml").addAnnotatedClass(Persona.class).buildSessionFactory();
		// crear sesión
		Session session = sf.openSession();

		// usar el objeto session

		session.beginTransaction();

		session.save(p);

		session.getTransaction().commit();
		session.close();
		sf.close();
	}
	
	public ArrayList<Persona> getPersonas(int dni) {

		// crear objeto factory
		SessionFactory factory = new Configuration().configure("hibernate2.cfg.xml").addAnnotatedClass(Persona.class)
				.buildSessionFactory();

		// crear sesión

		Session session = factory.getCurrentSession();

		// usar el objeto session
		session.beginTransaction();
		
		Query q = session.createQuery("select p from Persona p where p.dni = :dni");
		q.setParameter("dni", dni);
		List<Persona> personas = q.list();
		
		session.getTransaction().commit();
		session.close();

		factory.close();
		return (ArrayList<Persona>) personas;
		
		}
}
