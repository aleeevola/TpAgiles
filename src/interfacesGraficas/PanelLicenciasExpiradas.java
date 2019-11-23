package interfacesGraficas;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import auxiliares.GestorBaseDeDatos;
import auxiliares.GestorDeLicencia;
import auxiliares.LicenciaExpirada;
import auxiliares.TablaContribuyentes;
import auxiliares.TablaLicenciasExpiradas;
import clases.Clase;
import clases.Persona;

public class PanelLicenciasExpiradas extends JPanel {

	private JLabel lblNombre;
	private JLabel lblTitulo;



	private JButton btnCancelar;
	private JTable tabla;
	private TablaLicenciasExpiradas tablaLicenciasExpiradas = new TablaLicenciasExpiradas();

	private GestorBaseDeDatos gestorBD;
	private GestorDeLicencia gestorLicencia;
	private int seleccion = -1;

	public PanelLicenciasExpiradas() {
		this.setLayout(new GridBagLayout());
		this.gestorBD = new GestorBaseDeDatos();
		this.gestorLicencia = new GestorDeLicencia();
		this.construir();
	}

	private void construir() {

		GridBagConstraints gridConst =  new GridBagConstraints();

		gridConst.anchor = GridBagConstraints.CENTER;

		lblNombre = new JLabel("Sistema de Gestión de Licencias de Conducir");
		lblNombre.setFont(new Font(Font.DIALOG, Font.BOLD, 20));
		gridConst.gridx = 0;
		gridConst.gridy = 0;
		gridConst.gridwidth = 3;
		gridConst.insets = new Insets(15, 5, 20, 5);
		this.add(lblNombre, gridConst);

		gridConst.anchor = GridBagConstraints.LINE_START;

		lblTitulo = new JLabel("Licencias Expiradas");
		lblTitulo.setFont(new Font(Font.DIALOG, Font.BOLD, 16));
		gridConst.gridy = 1;
		gridConst.gridwidth = 1;
		gridConst.insets = new Insets(0, 5, 15, 5);
		this.add(lblTitulo, gridConst);

		gridConst.anchor = GridBagConstraints.LINE_END;

		
		tabla = new JTable(tablaLicenciasExpiradas);
		gridConst.gridy = 3;
		gridConst.gridx = 0;
		gridConst.gridwidth = 3;
		tabla.setFillsViewportHeight(true);
		tabla.setRowSelectionAllowed(true);
		tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane scrollPane = new JScrollPane(tabla);

		this.add(scrollPane, gridConst);

		tabla.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				int r = tabla.rowAtPoint(e.getPoint());
				seleccion = r;
			}
		});

		gridConst.anchor = GridBagConstraints.LINE_END;


		btnCancelar = new JButton("Cancelar");
		gridConst.gridy = 5;
		gridConst.gridx = 1;
		gridConst.gridwidth = 1;
		btnCancelar.addActionListener(e -> {
			JFrame frame = (JFrame) SwingUtilities.getAncestorOfClass(JFrame.class, this);
			frame.dispose();
		});
		this.add(btnCancelar, gridConst);

		buscarLicenciasExpiradas();
	}


//	Setea el resultado de la busqueda en la tabla
	public void setResultadoBusqueda(List<LicenciaExpirada> listaResultado, boolean actualizar) {
		this.tablaLicenciasExpiradas.setLicenciasExpiradas(listaResultado);
		if(actualizar) {
			this.tablaLicenciasExpiradas.fireTableDataChanged();
		}
	}


	public void buscarLicenciasExpiradas() {

		try {
			List<LicenciaExpirada> resultados = gestorBD.getLicenciasExpiradas();
			this.setResultadoBusqueda(resultados, true);
		}
		catch(Exception ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(null, "No se encontraron resultados", "Error", JOptionPane.OK_OPTION);
		}
	}
}
