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

import javax.imageio.ImageIO;

import org.hibernate.engine.jdbc.BlobProxy;


import auxiliares.GestorBaseDeDatos;
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
		t.setApellido("Vola");
		t.setNombre("Alejandro");
		t.setDireccion("Norte");
		t.setDni(40450769);
		t.setSangre(Grupo_sanguineo.A_NEGATIVO);
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
		//ATENCION    ahre
		//Si el usuario no esta cargado en la BD antes hay que agregarlo con el comando de abajo
		//int oo=bd.guardarUsuarioAdministrador(u);
		
				
		//int o=bd.guardarTitular(t);
		ArrayList<Titular> asd=bd.getTitular(40450769, "Vola", "Alejandro");
		Titular t2=asd.get(0);
		
		
		
		File file2 = new File("output.jpg");
	      try(FileOutputStream outputStream = new FileOutputStream(file2)) {
	         BufferedImage bufferedImage = ImageIO.read(t2._getFoto().getBinaryStream());
	         ImageIO.write(bufferedImage, "jpg", outputStream);
	         System.out.println("Image file location: "+file2.getCanonicalPath());
	      } catch (IOException e) {
	         e.printStackTrace();
	      }
      
	}

}
