package auxiliares;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import clases.Persona;

public class TablaLicenciasExpiradas extends AbstractTableModel{

//	Clase utilizada para representar el formato de tabla que visualiza todos los contribuyentes.
//	Los contribuyentes provienen de la base de datos externa al sistema y se representan como "Personas".
	
	private static SimpleDateFormat formatFecha = new SimpleDateFormat("dd/MM/yyyy");
	
	private List<LicenciaExpirada> licenciasExpiradas=new ArrayList();
	private String[] columnas = {"DNI", "Nombre", "Apellido", "Clase", "Fecha de vencimiento"};
	
//	Getters y Setters
	
	public List<LicenciaExpirada> getLicenciasExpiradas() {
		return licenciasExpiradas;
	}

	public void setLicenciasExpiradas(List<LicenciaExpirada> licenciasExpiradas) {
		this.licenciasExpiradas = licenciasExpiradas;
	}
	
	public String getColumnName(int indice) {
		return this.columnas[indice];
	}
	
	@Override 
	public int getColumnCount() {
		return this.columnas.length;
	}

	@Override
	public int getRowCount() {
		return this.licenciasExpiradas.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		
		Object valor = null;
		
		switch(columnIndex) {
		case 0:
			valor = this.licenciasExpiradas.get(rowIndex).getDni();
			break;
		case 1:
			valor = this.licenciasExpiradas.get(rowIndex).getNombre();
			break;
		case 2:
			valor = this.licenciasExpiradas.get(rowIndex).getApellido();
			break;
		case 3:
			valor = this.licenciasExpiradas.get(rowIndex).getClase();
			break;
		case 4:
			String fechaVencimiento = formatFecha.format(this.licenciasExpiradas.get(rowIndex).getFecha_de_vencimiento());
			valor = fechaVencimiento;
			break;
		default:
			break;
		}
		return valor;
	}

}
