package clases;

import java.util.Date;

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
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Licencia {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_licencia")
	private int id;
	@Column(name = "fecha_de_vencimiento")
	private Date fecha_de_vencimiento;
	@Column(name = "numero_de_copias")
	private int numero_de_copias;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "clase")
	private Clase clase;
	
	//@OneToOne(cascade = CascadeType.ALL)
	//@JoinColumn(name="emitidoPor")
	//private UsuarioAdministrador emitidoPor;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getFecha_de_vencimiento() {
		return fecha_de_vencimiento;
	}

	public void setFecha_de_vencimiento(Date fecha_de_vencimiento) {
		this.fecha_de_vencimiento = fecha_de_vencimiento;
	}

	public int getNumero_de_copias() {
		return numero_de_copias;
	}

	public void setNumero_de_copias(int numero_de_copias) {
		this.numero_de_copias = numero_de_copias;
	}

	public Clase getClase() {
		return clase;
	}

	public void setClase(Clase clase) {
		this.clase = clase;
	}

	/*public UsuarioAdministrador getEmitidoPor() {
		return emitidoPor;
	}

	public void setEmitidoPor(UsuarioAdministrador emitidoPor) {
		this.emitidoPor = emitidoPor;
	}*/
	
	
}
