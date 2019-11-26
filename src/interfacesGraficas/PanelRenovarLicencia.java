package interfacesGraficas;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
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
import java.sql.SQLException;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonModel;
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
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import auxiliares.GestorBaseDeDatos;
import auxiliares.GestorDeLicencia;
import auxiliares.TablaContribuyentes;
import auxiliares.TablaLicencias;
import auxiliares.TablaTitulares;
import clases.Clase;
import clases.Licencia;
import clases.Persona;
import clases.Titular;

public class PanelRenovarLicencia extends JPanel {

	private JLabel lblNombre;
	private JLabel lblTitulo;
	private JLabel lblDNI;
	private JTextField txtDNI;
	private JButton btnBuscar;
	private JButton btnCancelar;
	private JTable tablaL;
	private JTable tablaT;
	private TablaLicencias tablaLicencias = new TablaLicencias();
	private TablaTitulares tablaTitulares = new TablaTitulares();
	private JLabel lblClase;
	private JButton btnSiguiente;
	private GestorBaseDeDatos gestorBD;
	private GestorDeLicencia gestorLicencia;
	private int seleccion = -1;
	private int seleccionT = -1;
	
	private Titular titular;

	public PanelRenovarLicencia() {
		this.setLayout(new GridBagLayout());
		this.construir();
		this.gestorBD = new GestorBaseDeDatos();
		this.gestorLicencia = new GestorDeLicencia();
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

		lblTitulo = new JLabel("Renovar Licencia");
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

		txtDNI = new JTextField(10);
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
		
			
		/*
		 * Panel que tiene las tablas titulares y licencias
		 * new BorderLayout()*/
		JPanel tablas = new JPanel();
		BoxLayout boxlayout = new BoxLayout(tablas, BoxLayout.Y_AXIS);
        
        // Set the Boxayout to be Y_AXIS from top to down
        //BoxLayout boxlayout = new BoxLayout(panel, BoxLayout.Y_AXIS);
 
		tablas.setLayout(boxlayout);
		gridConst.gridy = 3;
		gridConst.gridx = 0;
		gridConst.gridwidth = 3;
		
		JLabel lblTitular = new JLabel("Seleccione un titular:");
		tablas.add(lblTitular);
		
		tablaT = new JTable(tablaTitulares);
		tablaT.setFillsViewportHeight(true);
		tablaT.setRowSelectionAllowed(true);
		tablaT.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tablaT.setPreferredScrollableViewportSize(new Dimension (500,50));
		JScrollPane scrollPaneT = new JScrollPane(tablaT);
		
		tablas.add(scrollPaneT);
		
		JLabel lblRenovar = new JLabel("Seleccione la licencia a renovar:");
		tablas.add(lblRenovar);
		
		tablaL = new JTable(tablaLicencias);
		tablaL.setFillsViewportHeight(true);
		tablaL.setRowSelectionAllowed(true);
		tablaL.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tablaL.setPreferredScrollableViewportSize(new Dimension (500,200));
		JScrollPane scrollPane = new JScrollPane(tablaL);
		tablas.add(scrollPane);
		
		this.add(tablas,gridConst);
		

		tablaL.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				int r = tablaL.rowAtPoint(e.getPoint());
				seleccion = r;
			}
		});

		tablaT.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				int r = tablaT.rowAtPoint(e.getPoint());
				seleccionT = r;
				mostrarLicencias();
			}
		});
		

		//gridConst.anchor = GridBagConstraints.LINE_START;

		
		btnSiguiente = new JButton("Renovar");
		gridConst.gridy = 8;
		gridConst.gridx = 2;
		gridConst.gridwidth = 1;
		btnSiguiente.addActionListener(e -> {
			try {
				this.renovarLicencia();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		this.add(btnSiguiente, gridConst);

		btnCancelar = new JButton("Cancelar");
		gridConst.gridy = 8;
		gridConst.gridx = 1;
		gridConst.gridwidth = 1;
		btnCancelar.addActionListener(e -> {
			JFrame frame = (JFrame) SwingUtilities.getAncestorOfClass(JFrame.class, this);
			frame.dispose();
		});
		this.add(btnCancelar, gridConst);

	}


	private void renovarLicencia() throws SQLException {

		Licencia licencia = tablaLicencias.getLicencias().get(seleccion);

		RenovarLicencia panelCards = (RenovarLicencia) SwingUtilities.getAncestorOfClass(JPanel.class, this);
		CardLayout cl = (CardLayout) panelCards.getLayout();

		cl.show(panelCards, EmitirLicencia.ALTAPANEL);

		panelCards.cardAlta.cargarDatosRenovar(titular,licencia);
		
		JFrame frame = (JFrame) SwingUtilities.getAncestorOfClass(JFrame.class, this);
	
		frame.setSize(new Dimension(900, 400));
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(dim.width/2- frame.getSize().width/2, dim.height/2- frame.getSize().height/2);
		
	}

//	Setea el resultado de la busqueda en la tabla Licencias
	public void setResultadoBusqueda(List<Licencia> listaResultado, boolean actualizar) {
		this.tablaLicencias.setLicencias(listaResultado);
		if(actualizar) {
			this.tablaLicencias.fireTableDataChanged();
		}
	}
	
//	Setea el resultado de la busqueda en la tabla Titulares
	public void setResultadoBusquedaTitulares(List<Titular> listaResultado, boolean actualizar) {
		this.tablaTitulares.setTitulares(listaResultado);
		if(actualizar) {
			this.tablaTitulares.fireTableDataChanged();
		}
	}

//	Obtiene el parametro de busqueda, realiza la busqueda a traves del gestor de BD y por ultimo actualiza la tabla
	public void buscarContribuyentes() {

		//Validacion para que el texto ingresado no sea vacio. Lo pongo aca por ahora, creo que hay que moverlo para que no este en la interfaz.

		if (this.txtDNI.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Ingrese algún DNI.", "Error", JOptionPane.OK_OPTION);
			return;
		}
		else try {

			int dni = Integer.valueOf(this.txtDNI.getText());
			List<Titular> resultados = gestorBD.getTitular(dni);
			this.setResultadoBusquedaTitulares(resultados,true);
			
		}
		catch(Exception ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(null, "No se encontraron resultados", "Error", JOptionPane.OK_OPTION);
		}
	}
	
	public void mostrarLicencias() {
		try {
			
			titular = tablaTitulares.getTitulares().get(seleccionT);
			this.setResultadoBusqueda(titular.getLicencias(), true);
		}
		catch(Exception ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(null, "No se encontraron resultados", "Error", JOptionPane.OK_OPTION);
		}
	}
}
