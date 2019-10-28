import java.util.ArrayList;

import clases.Persona;

public class testListarPersonas {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//lista las personas por dni ingresado
		
		GestorBaseDeDatos bd= new GestorBaseDeDatos();
		
		ArrayList<Persona> personas= bd.getPersonas(40450769);
		
		
		for(int i=0; i<personas.size(); ++i) {
			System.out.println(personas.get(i).toString());
			
		}
	}

}
