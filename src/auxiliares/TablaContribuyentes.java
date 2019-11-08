package auxiliares;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import clases.Persona;

public class TablaContribuyentes extends AbstractTableModel{

//	Clase utilizada para representar el formato de tabla que visualiza todos los contribuyentes.
//	Los contribuyentes provienen de la base de datos externa al sistema y se representan como "Personas".
	
	
	private List<Persona> contribuyentes;
	private String[] columnas = {"DNI", "Nombre", "Apellido", "Fecha de Nacimiento", "Domicilio"};
	
//	Getters y Setters
	
	public List<Persona> getContribuyentes() {
		return contribuyentes;
	}
	
	public void setContribuyentes(List<Persona> personas) {
		this.contribuyentes = personas;
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
		return this.contribuyentes.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		
		Object valor = null;
		
		switch(columnIndex) {
		case 0:
			valor = this.contribuyentes.get(rowIndex).getDni();
			break;
		case 1:
			valor = this.contribuyentes.get(rowIndex).getNombre();
			break;
		case 2:
			valor = this.contribuyentes.get(rowIndex).getApellido();
			break;
		case 3:
			valor = this.contribuyentes.get(rowIndex).getFecha_de_nacimiento();
			break;
		case 4:
			valor = this.contribuyentes.get(rowIndex).getDireccion();
			break;
		default:
			break;
		}
		return valor;
	}

}
