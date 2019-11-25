package auxiliares;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import clases.Licencia;
import clases.Titular;

public class TablaTitulares extends AbstractTableModel{

//	Clase utilizada para representar el formato de tabla que visualiza las clases.
//	Las licencias provienen de unca clase titular "licencias".
	
	
	private List<Titular> titulares=new ArrayList();
	private String[] columnas = {"DNI", "Apellido", "Nombre", "Fecha de nacimiento"};
	
//	Getters y Setters
	
	public List<Titular> getTitulares() {
		return titulares;
	}

	public void setTitulares(List<Titular> licencias) {
		this.titulares = licencias;
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
		return this.titulares.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		
		Object valor = null;
		
		switch(columnIndex) {
		case 0:
			valor = this.titulares.get(rowIndex).getDni();
			break;
		case 1:
			valor = this.titulares.get(rowIndex).getApellido();
			break;
		case 2:
			valor = this.titulares.get(rowIndex).getNombre();
			break;
		case 3:
			valor = this.titulares.get(rowIndex).getFecha_de_nacimiento();
			break;
		default:
			break;
		}
		return valor;
	}

}
