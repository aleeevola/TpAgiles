package clases;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.imageio.ImageIO;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.engine.jdbc.BlobProxy;



@Entity
public class Titular {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	@Column(name = "dni")
	private int dni;
	@Column(name = "apellido")
	private String apellido;
	@Column(name = "nombre")
	private String nombre;
	@Column(name = "fecha_de_nacimiento")
	private Date fecha_de_nacimiento;
	@Column(name = "direccion")
	private String direccion;
	@Column(name = "donante_de_organos")
	private Boolean donante_de_organos;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "sangre")
	private Grupo_sanguineo sangre;
	
	
	@OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	@Fetch(value = FetchMode.SUBSELECT)
	@JoinColumn(name = "titular_id")
	private List<Licencia> licencias=new ArrayList<>();

	@Column(name = "foto")
	private Blob foto;
	//private byte[] foto;

	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public int getDni() {
		return dni;
	}


	public void setDni(int dni) {
		this.dni = dni;
	}


	public String getApellido() {
		return apellido;
	}


	public void setApellido(String apellido) {
		this.apellido = apellido;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public Date getFecha_de_nacimiento() {
		return fecha_de_nacimiento;
	}


	public void setFecha_de_nacimiento(Date fecha_de_nacimiento) {
		this.fecha_de_nacimiento = fecha_de_nacimiento;
	}


	public String getDireccion() {
		return direccion;
	}


	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}


	public Boolean getDonante_de_organos() {
		return donante_de_organos;
	}


	public void setDonante_de_organos(Boolean dondante_de_organos) {
		this.donante_de_organos = dondante_de_organos;
	}


	public Grupo_sanguineo getSangre() {
		return sangre;
	}


	public void setSangre(Grupo_sanguineo sangre) {
		this.sangre = sangre;
	}


	public List<Licencia> getLicencias() {
		return licencias;
	}


	public void setLicencias(List<Licencia> licencias) {
		this.licencias = licencias;
	}
	
	public void addLicencia(Licencia licencia) {
		this.licencias.add(licencia);
		System.out.println("agregada");
	}


	public Blob _getFoto(){
		return foto;
	}
	
	public void _setFoto(Blob foto) {
		this.foto = foto;
	}
	
	public File getFoto() throws SQLException {
		File file2 = new File("fotoLicencia.jpg");
	      try(FileOutputStream outputStream = new FileOutputStream(file2)) {
	         BufferedImage bufferedImage = ImageIO.read(foto.getBinaryStream());
	         ImageIO.write(bufferedImage, "jpg", outputStream);
	         System.out.println("Image file location: "+file2.getCanonicalPath());
	         return file2;
	      } catch (IOException e) {
	         e.printStackTrace();
	      }
		return null;
	}
	
	public BufferedImage getBufferedFoto() throws SQLException {
		File file2 = new File("fotoLicencia.jpg");
	      try(FileOutputStream outputStream = new FileOutputStream(file2)) {
	         BufferedImage bufferedImage = ImageIO.read(foto.getBinaryStream());
	         ImageIO.write(bufferedImage, "jpg", outputStream);
	         return bufferedImage;
	      } catch (IOException e) {
	         e.printStackTrace();
	      }
		return null;
	}
	
	public void setFoto(File file) {
		try {
            BufferedImage bufferedImage=ImageIO.read(file);
            ByteArrayOutputStream byteOutStream=new ByteArrayOutputStream();
            ImageIO.write(bufferedImage, "jpg", byteOutStream);
            Blob fotito= (Blob) BlobProxy.generateProxy(byteOutStream.toByteArray());
            _setFoto(fotito);
         } catch (IOException e) {
            e.printStackTrace();
         }
	}
	
	
}
