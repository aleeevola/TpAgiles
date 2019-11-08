package interfacesGraficas;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import auxiliares.TablaContribuyentes;

public class PanelEmitirLicencia extends JPanel {

	private JLabel lblNombre;
	private JLabel lblTitulo;
	private JLabel lblDNI;
	private JTextField txtDNI;
	private JButton btnBuscar;
	private JTable tabla;
	private TablaContribuyentes tablaContribuyentes;
	private JLabel lblClase;
	private JComboBox cmbClase;
	private JButton btnSiguiente;
	
	public PanelEmitirLicencia() {
		this.setLayout(new GridBagLayout());
		this.construir();
	}

	private void construir() {
		
		GridBagConstraints gridConst =  new GridBagConstraints();
		
		gridConst.anchor = GridBagConstraints.LINE_START;
		
		lblNombre = new JLabel("Sistema de Gesti�n de Licencias de Conducir");
		lblNombre.setFont(new Font(Font.DIALOG, Font.BOLD, 20));
		gridConst.gridx = 0;
		gridConst.gridy = 0;
		gridConst.gridwidth = 3;
		gridConst.insets = new Insets(15, 5, 20, 5);
		this.add(lblNombre, gridConst);
		
		lblTitulo = new JLabel("Emitir Licencia");
		gridConst.gridy = 1;
		gridConst.gridwidth = 1;
		gridConst.insets = new Insets(0, 5, 15, 5);
		this.add(lblTitulo, gridConst);
		
		lblDNI = new JLabel("DNI:");
		gridConst.gridy = 2;
		gridConst.insets = new Insets(0, 5, 15, 5);
		this.add(lblDNI, gridConst);
		
		txtDNI = new JTextField(9);
		gridConst.gridx = 1;
		gridConst.insets = new Insets(0, 5, 15, 5);
		this.add(txtDNI, gridConst);
		
		btnBuscar = new JButton("Buscar");
		gridConst.gridx = 2;
		gridConst.insets = new Insets(0, 5, 15, 5);
		this.add(btnBuscar, gridConst);
		
		tabla = new JTable(tablaContribuyentes);
		gridConst.gridy = 3;
		gridConst.gridx = 0;
		gridConst.gridwidth = 3;
		tabla.setFillsViewportHeight(true);
		tabla.setRowSelectionAllowed(true);
		tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane scrollPane = new JScrollPane(tabla);
		this.add(scrollPane, gridConst);
		
		lblClase = new JLabel("Clase");
		gridConst.gridy = 4;
		gridConst.gridwidth = 1;
		this.add(lblClase, gridConst);
		
		cmbClase = new JComboBox();
		gridConst.gridx = 1;
		gridConst.gridwidth = 2;
		this.add(cmbClase, gridConst);
		
		btnSiguiente = new JButton("Siguiente");
		gridConst.gridy = 5;
		gridConst.gridx = 2;
		gridConst.gridwidth = 1;
		this.add(btnSiguiente, gridConst);
		
		
		
	}
}
