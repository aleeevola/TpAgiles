package auxiliares;

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
import javax.persistence.Table;

import clases.Clase;

@Entity
@Table(name = "licencia")
public class LicenciaExpirada {
	/*
	 * Esta clase solo sirve para cargar la lista de licencias expiradas
	 * Es una clase axiliar que solo se usa para mostrar en tablas cuando no queres cargar todos los datos
	 * */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	@Column(name = "fecha_de_vencimiento")
	private Date fecha_de_vencimiento;
	
	
	@Enumerated(EnumType.STRING)
	@Column(name = "clase")
	private Clase clase;
	
	
	@ManyToOne
    @JoinColumn(name = "titular_id")
    private TitularExpirada titular;

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
	


	public Clase getClase() {
		return clase;
	}

	public void setClase(Clase clase) {
		this.clase = clase;
	}


	public TitularExpirada getTitular() {
		return titular;
	}

	public void setTitular(TitularExpirada titular) {
		this.titular = titular;
	}
	

	@Override
	public String toString() {
		return "LicenciaExpirada [id=" + id + ", fecha_de_vencimiento=" + fecha_de_vencimiento + ", clase=" + clase
				+ ", titular=" + titular + "]";
	}


/*
 * Metodos get y set del el titular
 * */
	public int getIdTitular() {
		return titular.getId();
	}


	public int getDni() {
		return titular.getDni();
	}


	public String getApellido() {
		return titular.getApellido();
	}



	public String getNombre() {
		return titular.getNombre();
	}

	
}
