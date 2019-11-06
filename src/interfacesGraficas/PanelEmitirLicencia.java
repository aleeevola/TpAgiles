package interfacesGraficas;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PanelEmitirLicencia extends JPanel {

	private JLabel lblNombre;
	private JLabel lblTitulo;
	private JLabel lblDNI;
	private JTextField txtDNI;
	private JButton btnBuscar;
	
	public PanelEmitirLicencia() {
		this.setLayout(new GridBagLayout());
		this.construir();
	}

	private void construir() {
		
		GridBagConstraints gridConst =  new GridBagConstraints();
		
		gridConst.anchor = GridBagConstraints.LINE_START;
		
		lblNombre = new JLabel("Sistema de Gestión de Licencias de Conducir");
		lblNombre.setFont(new Font(Font.DIALOG, Font.BOLD, 20));
		gridConst.gridx = 1;
		gridConst.gridy = 1;
		gridConst.gridwidth = 3;
		gridConst.insets = new Insets(15, 5, 20, 5);
		this.add(lblNombre, gridConst);
		
		lblTitulo = new JLabel("Emitir Licencia");
		gridConst.gridy = 2;
		gridConst.gridwidth = 1;
		gridConst.insets = new Insets(0, 5, 15, 5);
		this.add(lblTitulo, gridConst);
		
		lblDNI = new JLabel("DNI:");
		gridConst.gridy = 3;
		gridConst.insets = new Insets(0, 5, 15, 5);
		this.add(lblDNI, gridConst);
		
		txtDNI = new JTextField(9);
		gridConst.gridx = 2;
		gridConst.insets = new Insets(0, 5, 15, 5);
		this.add(txtDNI, gridConst);
		
		btnBuscar = new JButton("Buscar");
		gridConst.gridx = 3;
		gridConst.insets = new Insets(0, 5, 15, 5);
		this.add(btnBuscar, gridConst);
		
	}
}
