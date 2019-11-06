package interfacesGraficas;

import java.awt.Color;
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
		
		btnNuevaLicencia = new JButton();
		btnNuevaLicencia.setIcon(new ImageIcon("Imagenes/btnEmitirLicencia.png"));
		gridConst.gridy = 1;
		gridConst.insets = new Insets(0, 5, 15, 0);
		this.add(btnNuevaLicencia, gridConst);
		
		btnRenovarLicencia = new JButton();
		btnRenovarLicencia.setIcon(new ImageIcon("Imagenes/btnRenovarLicencia.png"));
		gridConst.gridy = 2;
		this.add(btnRenovarLicencia, gridConst);
		
		btnEmitirCopiaLicencia = new JButton();
		btnEmitirCopiaLicencia.setIcon(new ImageIcon("Imagenes/btnEmitirCopia.png"));
		gridConst.gridy = 3;
		this.add(btnEmitirCopiaLicencia, gridConst);
		
		btnBuscar = new JButton();
		btnBuscar.setIcon(new ImageIcon("Imagenes/btnBuscarLicencias.png"));
		gridConst.gridy = 4;
		this.add(btnBuscar, gridConst);
		
	}
}
