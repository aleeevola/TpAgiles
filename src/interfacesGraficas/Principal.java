package interfacesGraficas;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class Principal {

	public static void main(String[] args) {

		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();
			}
		});
	}

	private static void createAndShowGUI() {

		JFrame frame = new JFrame();

		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setSize(800, 800);

		PanelInicio inicio = new PanelInicio();
		//PanelTareas tareas = new PanelTareas();
		//PanelEmitirLicencia nueva = new PanelEmitirLicencia();
		PanelDarAltaTitular altaTitular = new PanelDarAltaTitular();
		frame.setContentPane(altaTitular);

		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(dim.width/2- frame.getSize().width/2, dim.height/2- frame.getSize().height/2);
        
	}
}

