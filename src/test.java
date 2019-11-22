import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.imageio.ImageIO;

import org.hibernate.engine.jdbc.BlobProxy;


import auxiliares.GestorBaseDeDatos;
import auxiliares.LicenciaExpirada;
import clases.Clase;
import clases.Grupo_sanguineo;
import clases.Licencia;
import clases.Titular;
import clases.UsuarioAdministrador;

public class test {

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub

		GestorBaseDeDatos bd=new GestorBaseDeDatos();
		
		Titular t=new Titular();
		t.setApellido("Pedro");
		t.setNombre("Alfonso");
		t.setDireccion("Sur");
		t.setDni(40450769);
		t.setSangre(Grupo_sanguineo.CERO_NEGATIVO);
		t.setDonante_de_organos(true);
		
		//save image into database
    	File file = new File("C:\\Users\\Ale\\Desktop\\prueba.jpg");
    	try {
            BufferedImage bufferedImage=ImageIO.read(file);
            ByteArrayOutputStream byteOutStream=new ByteArrayOutputStream();
            ImageIO.write(bufferedImage, "jpg", byteOutStream);
            Blob fotito= (Blob) BlobProxy.generateProxy(byteOutStream.toByteArray());
            t._setFoto(fotito);
         } catch (IOException e) {
            e.printStackTrace();
         }
		
		Date fn = new GregorianCalendar(1997, Calendar.FEBRUARY, 3).getTime();	
		t.setFecha_de_nacimiento(fn);
		
		UsuarioAdministrador u=new UsuarioAdministrador();
		u.setUsuario("aleeevola");
		
		Licencia l1=new Licencia();
		l1.setClase(Clase.A);
		l1.setEmitidoPor(u);
		l1.setFecha_de_emision(fn);
		Date ayer = new GregorianCalendar(2018, Calendar.FEBRUARY, 3).getTime();
		l1.setFecha_de_vencimiento(ayer);
		l1.setNumero_de_copias(0);
		t.addLicencia(l1);
		
		Licencia l2=new Licencia();
		l2.setClase(Clase.H);
		l2.setEmitidoPor(u);
		l2.setFecha_de_emision(fn);
		Date asd = new GregorianCalendar(2018, Calendar.FEBRUARY, 3).getTime();
		l2.setFecha_de_vencimiento(asd);
		l2.setNumero_de_copias(0);
		t.addLicencia(l2);
		//ATENCION    ahre
		//Si el usuario no esta cargado en la BD antes hay que agregarlo con el comando de abajo
		//int oo=bd.guardarUsuarioAdministrador(u);
		
		//GUARDO TITULAR	
		//int o=bd.guardarTitular(t);
		//OBTENGO VENCIDAS
		ArrayList vencidas=(ArrayList) bd.getLicenciasExpiradas();
		System.out.println(vencidas.toString());
		
		
      
	}

}
