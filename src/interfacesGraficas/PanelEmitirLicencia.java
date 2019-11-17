package interfacesGraficas;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Rectangle;
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
	private TablaContribuyentes tablaContribuyentes= new TablaContribuyentes();
	private JLabel lblClase;
	private JComboBox cmbClase;
	private JButton btnSiguiente;
	private GestorBaseDeDatos gestorBD;
	private GestorDeLicencia gestorLicencia;
	private int seleccion = -1;

	private JFrame padre;
	private JPanel anterior;
	private Rectangle boundsAnterior;
	JPanel panel=this;
	private Dimension medidasPanel=new Dimension(800,900);
	
	public PanelEmitirLicencia() {
		this.setSize(medidasPanel);
		this.setLayout(new GridBagLayout());
		this.construir();
		this.gestorBD = new GestorBaseDeDatos();
		this.gestorLicencia = new GestorDeLicencia();
		
	}

	private void construir() {
		
		GridBagConstraints gridConst =  new GridBagConstraints();
		
		gridConst.anchor = GridBagConstraints.LINE_START;
		
		lblNombre = new JLabel("Sistema de Gestión de Licencias de Conducir");
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
		
		lblClase = new JLabel("Clase:");
		gridConst.gridy = 4;
		gridConst.gridwidth = 1;
		this.add(lblClase, gridConst);
		
		cmbClase = new JComboBox(Clase.values());
		cmbClase.setEditable(false);
		cmbClase.setPreferredSize(new Dimension(100, 20));
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
//			removerPanel();
		});
		this.add(btnCancelar, gridConst);
		
	}
	
private void emitirLicencia() {
	
	try {	
		Persona persona = tablaContribuyentes.getContribuyentes().get(seleccion);
		int seEmitioLaLicencia = gestorLicencia.emitirLicencia((Clase)cmbClase.getSelectedItem(), persona);
		
		if(seEmitioLaLicencia==0) {
			JOptionPane.showMessageDialog(null, "Licencia asignada", "Licencia Emitida", JOptionPane.OK_OPTION);
		
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

private void _darDeAltaNuevoTitular(GestorDeLicencia gestorLicencia2, Persona persona) {
	//this.setSize(800, 900);
	/*
	PanelDarAltaTitular panelAltaTitular = new PanelDarAltaTitular((Clase)cmbClase.getSelectedItem(), persona);
	panelAltaTitular.setPadre(padre);
	panelAltaTitular.setAnterior(panel);
	panelAltaTitular.setBoundsAnterior(new Rectangle(0, 0, medidasPanel.width, medidasPanel.height));
	padre.setContentPane(panelAltaTitular);
	padre.setBounds(panelAltaTitular.getBounds());
	padre.setLocationRelativeTo(null);	*/
	
	
	PanelDarAltaTitular panelAltaTitular = new PanelDarAltaTitular((Clase)cmbClase.getSelectedItem(), persona);
	
	JFrame newFrame = new JFrame();
	newFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	newFrame.setVisible(true);
	newFrame.setSize(900, 600);
	newFrame.setContentPane(panelAltaTitular);
	
	Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
    newFrame.setLocation(dim.width/2- newFrame.getSize().width/2, dim.height/2- newFrame.getSize().height/2);
	
}

//	Setea el resultado de la búsqueda en la tabla
	public void setResultadoBusqueda(List<Persona> listaResultado, boolean actualizar) {
		this.tablaContribuyentes.setContribuyentes(listaResultado);
		if(actualizar) {
			this.tablaContribuyentes.fireTableDataChanged();
		}
	}
	
//	Obtiene el parámetro de búsqueda, realiza la busqueda a traves del gestor de BD y por último actualiza la tabla
	public void buscarContribuyentes() {

		//Validación para que el texto ingresado no sea vacío. Lo pongo acá por ahora, creo que hay que moverlo para que no esté en la interfaz. -- Facu
		
		if (this.txtDNI.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Ingrese algún DNI.", "Error", JOptionPane.OK_OPTION);
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
	
	public void setPadre(JFrame padre) {
		this.padre = padre;
	}

//	public void removerPanel() {
//		padre.remove(this);
//		padre.setContentPane(anterior);
//		padre.setBounds(boundsAnterior);
//		padre.setLocationRelativeTo(null);
//	}

	public void setAnterior(JPanel anterior) {
		this.anterior = anterior;
	}

	public void setBoundsAnterior(Rectangle bounds) {
		this.boundsAnterior=bounds;	
	}
}
