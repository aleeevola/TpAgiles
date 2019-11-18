package interfacesGraficas;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Toolkit;
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
import javax.swing.WindowConstants;

import auxiliares.GestorBaseDeDatos;
import auxiliares.GestorDeLicencia;
import auxiliares.TablaContribuyentes;
import clases.Clase;
import clases.Persona;

public class PanelEmitirLicencia extends JPanel {

	private JLabel lblNombre;
	private JLabel lblTitulo;
	private JLabel lblDNI;
	private JTextField txtDNI;
	private JButton btnBuscar;
	private JButton btnCancelar;
	private JTable tabla;
	private TablaContribuyentes tablaContribuyentes = new TablaContribuyentes();
	private JLabel lblClase;
	private JComboBox cmbClase;
	private JButton btnSiguiente;
	private GestorBaseDeDatos gestorBD;
	private GestorDeLicencia gestorLicencia;
	private int seleccion = -1;

	public PanelEmitirLicencia() {
		this.setLayout(new GridBagLayout());
		this.construir();
		this.gestorBD = new GestorBaseDeDatos();
		this.gestorLicencia = new GestorDeLicencia();
	}

	private void construir() {

		GridBagConstraints gridConst =  new GridBagConstraints();

		gridConst.anchor = GridBagConstraints.CENTER;

		lblNombre = new JLabel("Sistema de Gesti�n de Licencias de Conducir");
		lblNombre.setFont(new Font(Font.DIALOG, Font.BOLD, 20));
		gridConst.gridx = 0;
		gridConst.gridy = 0;
		gridConst.gridwidth = 3;
		gridConst.insets = new Insets(15, 5, 20, 5);
		this.add(lblNombre, gridConst);

		gridConst.anchor = GridBagConstraints.LINE_START;

		lblTitulo = new JLabel("Emitir Licencia");
		lblTitulo.setFont(new Font(Font.DIALOG, Font.BOLD, 16));
		gridConst.gridy = 1;
		gridConst.gridwidth = 1;
		gridConst.insets = new Insets(0, 5, 15, 5);
		this.add(lblTitulo, gridConst);

		gridConst.anchor = GridBagConstraints.LINE_END;

		lblDNI = new JLabel("DNI:");
		gridConst.gridy = 2;
		gridConst.insets = new Insets(0, 5, 15, 5);
		this.add(lblDNI, gridConst);

		gridConst.anchor = GridBagConstraints.LINE_START;

		txtDNI = new JTextField(9);
		gridConst.gridx = 1;
		gridConst.insets = new Insets(0, 5, 15, 5);
		this.add(txtDNI, gridConst);

		txtDNI.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                	buscarContribuyentes();
                    e.consume();
                }
            }
        });

		btnBuscar = new JButton("Buscar");
		gridConst.gridx = 2;
		gridConst.insets = new Insets(0, 5, 15, 5);
		btnBuscar.addActionListener(e -> {
			this.buscarContribuyentes();
		});
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

		tabla.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				int r = tabla.rowAtPoint(e.getPoint());
				seleccion = r;
			}
		});

		gridConst.anchor = GridBagConstraints.LINE_END;

		lblClase = new JLabel("Clase:");
		gridConst.gridy = 4;
		gridConst.gridwidth = 1;
		this.add(lblClase, gridConst);

		gridConst.anchor = GridBagConstraints.LINE_START;

		cmbClase = new JComboBox(Clase.values());
		cmbClase.setEditable(false);
		cmbClase.setPreferredSize(new Dimension(200, 20));
		gridConst.gridx = 1;
		gridConst.gridwidth = 2;
		this.add(cmbClase, gridConst);

		btnSiguiente = new JButton("Siguiente");
		gridConst.gridy = 5;
		gridConst.gridx = 2;
		gridConst.gridwidth = 1;
		btnSiguiente.addActionListener(e -> {
			this.emitirLicencia();
		});
		this.add(btnSiguiente, gridConst);

		btnCancelar = new JButton("Cancelar");
		gridConst.gridy = 5;
		gridConst.gridx = 1;
		gridConst.gridwidth = 1;
		btnCancelar.addActionListener(e -> {
			JFrame frame = (JFrame) SwingUtilities.getAncestorOfClass(JFrame.class, this);
			frame.dispose();
		});
		this.add(btnCancelar, gridConst);

	}

	private void emitirLicencia() {

		try {
			Persona persona = tablaContribuyentes.getContribuyentes().get(seleccion);
			int seEmitioLaLicencia = gestorLicencia.emitirLicencia((Clase)cmbClase.getSelectedItem(), persona);

			if(seEmitioLaLicencia==0) {

				JOptionPane.showMessageDialog(null, "Licencia asignada con �xito", "Licencia Emitida", JOptionPane.INFORMATION_MESSAGE);

			}else if (seEmitioLaLicencia==-1){

				JOptionPane.showMessageDialog(null, "No se puede emitir una licencia", "Error", JOptionPane.OK_OPTION);

			}else if (seEmitioLaLicencia==1) {

				_darDeAltaNuevoTitular(gestorLicencia,persona);

			}

		}catch(Exception ex){
			ex.printStackTrace();
			JOptionPane.showMessageDialog(null, "No se selecciono ninguna persona", "Error", JOptionPane.OK_OPTION);
		};
	}

	private void _darDeAltaNuevoTitular(GestorDeLicencia gestorLicencia, Persona persona) {

		EmitirLicencia panelCards = (EmitirLicencia) SwingUtilities.getAncestorOfClass(JPanel.class, this);
		CardLayout cl = (CardLayout) panelCards.getLayout();

		cl.show(panelCards, EmitirLicencia.ALTAPANEL);

		panelCards.cardAlta.cargarDatos(persona, (Clase)cmbClase.getSelectedItem());

	}

//	Setea el resultado de la b�squeda en la tabla
	public void setResultadoBusqueda(List<Persona> listaResultado, boolean actualizar) {
		this.tablaContribuyentes.setContribuyentes(listaResultado);
		if(actualizar) {
			this.tablaContribuyentes.fireTableDataChanged();
		}
	}

//	Obtiene el par�metro de b�squeda, realiza la busqueda a traves del gestor de BD y por �ltimo actualiza la tabla
	public void buscarContribuyentes() {

		//Validaci�n para que el texto ingresado no sea vac�o. Lo pongo ac� por ahora, creo que hay que moverlo para que no est� en la interfaz.

		if (this.txtDNI.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Ingrese alg�n DNI.", "Error", JOptionPane.OK_OPTION);
			return;
		}
		else try {

			int dni = Integer.valueOf(this.txtDNI.getText());

			List<Persona> resultados = gestorBD.getPersonas(dni);
			this.setResultadoBusqueda(resultados, true);
		}
		catch(Exception ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(null, "No se encontraron resultados", "Error", JOptionPane.OK_OPTION);
		}
	}
}
