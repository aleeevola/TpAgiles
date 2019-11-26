package auxiliares;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import clases.Licencia;

public class TablaLicencias extends AbstractTableModel{

//	Clase utilizada para representar el formato de tabla que visualiza las clases.
//	Las licencias provienen de unca clase titular "licencias".
	private static SimpleDateFormat formatFecha = new SimpleDateFormat("dd/MM/yyyy");
	
	private List<Licencia> licencias=new ArrayList();
	private String[] columnas = {"Clase", "Fecha de emision", "Fecha de vencimiento", "Numero de copias"};
	
//	Getters y Setters
	
	public List<Licencia> getLicencias() {
		return licencias;
	}

	public void setLicencias(List<Licencia> licencias) {
		this.licencias = licencias;
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
		return this.licencias.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		
		Object valor = null;
		
		switch(columnIndex) {
		case 0:
			valor = this.licencias.get(rowIndex).getClase();
			break;
		case 1:
			String fechaEmision = formatFecha.format(this.licencias.get(rowIndex).getFecha_de_emision());
			valor = fechaEmision;
			break;
		case 2:
			String fechaVencimiento = formatFecha.format(this.licencias.get(rowIndex).getFecha_de_vencimiento());
			valor = fechaVencimiento;
			break;
		case 3:
			valor = this.licencias.get(rowIndex).getNumero_de_copias();
			break;
		default:
			break;
		}
		return valor;
	}

}
