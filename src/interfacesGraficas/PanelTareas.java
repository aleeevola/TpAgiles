package interfacesGraficas;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelTareas extends JPanel {
	
	private JLabel lblUsuario;
	private JButton btnNuevaLicencia;
	private JButton btnRenovarLicencia;
	private JButton btnEmitirCopiaLicencia;
	private JButton btnBuscar;
	
	public PanelTareas() {
		this.setLayout(new GridBagLayout());
		this.construir();
	}

	private void construir() {
		
		GridBagConstraints gridConst =  new GridBagConstraints();
		
		gridConst.anchor = GridBagConstraints.CENTER;
		
		lblUsuario = new JLabel();
		lblUsuario.setIcon(new ImageIcon("Imagenes/lblUsuario.png"));
		gridConst.gridx = 0;
		gridConst.gridy = 0;
		gridConst.insets = new Insets(10, 15, 15, 15);
		this.add(lblUsuario, gridConst);
		
		btnNuevaLicencia = new JButton("Nueva Licencia");
		gridConst.gridy = 1;
		gridConst.insets = new Insets(0, 5, 10, 0);
		this.add(btnNuevaLicencia, gridConst);
		
		btnRenovarLicencia = new JButton("Renovar Licencia");
		gridConst.gridy = 2;
		this.add(btnRenovarLicencia, gridConst);
		
		btnEmitirCopiaLicencia = new JButton("Emitir Copia");
		gridConst.gridy = 3;
		this.add(btnEmitirCopiaLicencia, gridConst);
		
		btnBuscar = new JButton("Buscar Licencia");
		gridConst.gridy = 4;
		this.add(btnBuscar, gridConst);
		
	}
}
