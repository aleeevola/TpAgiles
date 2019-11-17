package interfacesGraficas;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.image.ImageFilter;
import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.filechooser.FileNameExtensionFilter;

import auxiliares.GestorBaseDeDatos;
import auxiliares.GestorDeLicencia;
import auxiliares.TablaContribuyentes;
import clases.Clase;
import clases.Grupo_sanguineo;
import clases.Persona;
import clases.Titular;


public class PanelDarAltaTitular extends JPanel{

	private JLabel lblTitulo; 
	private JLabel lblNombre;
	private JLabel lblNombreTitular;
	private JTextField txtNombreTitular;
	private JLabel lblApellido;
	private JTextField txtApellido;
	private JLabel lblDNI;
	private JTextField txtDNI;
	private JLabel lblNacimiento;
	private JLabel lblDireccion;
	private JTextField txtDireccion;
	private JLabel lblGrupoSanguineo;
	private JLabel lblFactorRH;
	private JLabel lblDonante;
	private JLabel lblClaseSolicitada;
	
	private JComboBox cmbClase;
	private JComboBox cmbGrupoSanguineo;
	
	private JButton btnSiguiente;
	private JButton btnCancelar;
	
	private JCheckBox chkDonante;
	
	private GestorBaseDeDatos gestorBD;
	private GestorDeLicencia gestorLicencia=new GestorDeLicencia();
	
	private int dni;
	private Date fdn;
	

	private Dimension medidasPanel=new Dimension(1000,600);
	
	
	private JButton btnAgregarFoto;
	private JLabel LabelFoto;
	private File foto;
	    
	public PanelDarAltaTitular() {
		this.setSize(medidasPanel);
		
		this.setLayout(new GridBagLayout());
		this.construir();
		this.gestorBD = new GestorBaseDeDatos();
	}
	
	public PanelDarAltaTitular(Clase clase, Persona persona) {
		this.setSize(medidasPanel);
		
		dni=persona.getDni();
		Date fecha_de_nacimiento=persona.getFecha_de_nacimiento();
		String nombre=persona.getNombre();
		String apellido=persona.getApellido();
		fdn=fecha_de_nacimiento;
		
		this.setLayout(new GridBagLayout());
		this.construir();
		
		
		this.txtNombreTitular.setText(nombre);
		this.txtNombreTitular.setEnabled(false);
		
		this.txtApellido.setText(apellido);
		this.txtApellido.setEnabled(false);
		
		this.txtDNI.setText(String.valueOf(dni));
		this.txtDNI.setEnabled(false);
		
		//FALTA DARLE FORMATO
		this.lblNacimiento.setText("Fecha de Nacimiento:"+formatoFecha(fecha_de_nacimiento));
		
		
		this.cmbClase.setSelectedItem(clase);
		this.cmbClase.setEditable(false);
		
		txtDireccion.setText(persona.getDireccion());
		txtDireccion.setEditable(false);
		
		btnSiguiente.addActionListener(e -> {
			/*
			 * Cuando apreta el boton siguiente debe cerrarse y devolver el objeto titular
			 * */
			try {
				
				Titular titular = _altaTitular();
				gestorLicencia.darDeAltaNuevoTitular(clase, titular);
				this.setVisible(false);
				String Alerta="Se cargo con exito el titular "+titular.getApellido()+" "+titular.getNombre();
				JOptionPane.showMessageDialog(null, Alerta, "Error", JOptionPane.OK_OPTION);
				}
			
			catch(Exception ex) {
				ex.printStackTrace();
				JOptionPane.showMessageDialog(null, "No se pudo cargar el nuevo titular", "Error", JOptionPane.OK_OPTION);
				}
			
			
		});
		
		
		
	}
	
	private String formatoFecha(Date fecha_de_nacimiento) {
		GregorianCalendar fecha=new GregorianCalendar();
		fecha.setTime(fecha_de_nacimiento);
		String formatoFecha=fecha.get(Calendar.DAY_OF_MONTH)+"/"+(fecha.get(Calendar.MONTH)+1)+"/"+fecha.get(Calendar.YEAR);
		return formatoFecha;
	}

	private void construir() {
		
		GridBagConstraints gridConst =  new GridBagConstraints();
		
		gridConst.anchor = GridBagConstraints.CENTER;
		
		lblNombre = new JLabel("Sistema de Gestión de Licencias de Conducir");
		lblNombre.setFont(new Font(Font.DIALOG, Font.BOLD, 20));
		gridConst.gridx = 0;
		gridConst.gridy = 0;
		gridConst.gridwidth = 4;
		gridConst.insets = new Insets(15, 5, 5, 5);
		this.add(lblNombre, gridConst);
		
		gridConst.anchor = GridBagConstraints.LINE_START;
		
		lblTitulo = new JLabel("Dar de Alta un Titular");
		lblTitulo.setFont(new Font(Font.DIALOG, Font.BOLD, 16));
		gridConst.gridy = 1;
		gridConst.gridwidth = 2;
		gridConst.insets = new Insets(15, 5, 20, 5);
		this.add(lblTitulo, gridConst);
		
		lblApellido = new JLabel("Apellido:");
		gridConst.gridwidth = 1;
		gridConst.gridy = 2;		
		gridConst.insets = new Insets(0, 5, 15, 0);
		this.add(lblApellido, gridConst);
		
		txtApellido = new JTextField(20);
		gridConst.gridx = 1;
		gridConst.insets = new Insets(0, 0, 15, 5);
		this.add(txtApellido, gridConst);
		
		lblNombreTitular = new JLabel("Nombre:");
		gridConst.gridx = 2;
		gridConst.insets = new Insets(0, 5, 15, 5);
		this.add(lblNombreTitular, gridConst);
		
		txtNombreTitular = new JTextField(20);
		gridConst.gridx = 3;
		gridConst.insets = new Insets(0, 0, 15, 5);
		this.add(txtNombreTitular, gridConst);
		
		lblDNI = new JLabel("DNI:");
		gridConst.gridy = 3;
		gridConst.gridx = 0;
		gridConst.insets = new Insets(0, 5, 15, 5);
		this.add(lblDNI, gridConst);
		
		txtDNI = new JTextField(9);
		gridConst.gridx = 1;
		gridConst.insets = new Insets(0, 0, 15, 5);
		this.add(txtDNI, gridConst);
		
		lblNacimiento = new JLabel("Fecha de Nacimiento:");
		gridConst.gridx = 2;
		gridConst.insets = new Insets(0, 5, 15, 5);
		this.add(lblNacimiento, gridConst);
		
		//Faltan los campos para ingresar la fecha de nacimiento, no me acuerdo como habíamos decidido hacerlo -- Facu
		
		lblDireccion = new JLabel("Dirección:");
		gridConst.gridy = 4;
		gridConst.gridx = 0;
		gridConst.insets = new Insets(0, 5, 15, 5);
		this.add(lblDireccion, gridConst);
		
		txtDireccion = new JTextField(20);
		gridConst.gridx = 1;
		gridConst.gridwidth = 3;
		gridConst.insets = new Insets(0, 0, 15, 5);
		this.add(txtDireccion, gridConst);
		
		lblGrupoSanguineo = new JLabel("Grupo Sanguíneo:");
		gridConst.gridy = 5;
		gridConst.gridx = 0;
		gridConst.gridwidth = 1;
		gridConst.insets = new Insets(0, 5, 15, 5);
		this.add(lblGrupoSanguineo, gridConst);
		
		cmbGrupoSanguineo = new JComboBox(Grupo_sanguineo.values());
		cmbGrupoSanguineo.setEditable(false);
		cmbGrupoSanguineo.setPreferredSize(new Dimension(140, 20));
		gridConst.gridx = 1;
		this.add(cmbGrupoSanguineo, gridConst);
		
		lblDonante = new JLabel("Donante:");
		gridConst.gridx = 2;
		gridConst.insets = new Insets(0, 5, 15, 5);
		this.add(lblDonante, gridConst);
		
		chkDonante = new JCheckBox();
		gridConst.gridx = 3;
		gridConst.insets = new Insets(0, 5, 15, 5);
		this.add(chkDonante, gridConst);
		
		
		lblClaseSolicitada = new JLabel("ClaseSolicitada:");
		gridConst.gridy = 6;
		gridConst.gridx = 0;
		gridConst.insets = new Insets(0, 5, 15, 5);
		this.add(lblClaseSolicitada, gridConst);
		
		cmbClase = new JComboBox(Clase.values());
		cmbClase.setEditable(false);
		cmbClase.setPreferredSize(new Dimension(100, 20));
		gridConst.gridx = 1;
		gridConst.gridwidth = 2;
		this.add(cmbClase, gridConst);
		
		gridConst.anchor = GridBagConstraints.LINE_END;
		
		btnSiguiente = new JButton("Siguiente");
		gridConst.gridy = 7;
		gridConst.gridx = 3;
		gridConst.gridwidth = 1;
		this.add(btnSiguiente, gridConst);
		
		btnCancelar = new JButton("Cancelar");
		gridConst.gridy = 7;
		gridConst.gridx = 2;
		gridConst.gridwidth = 1;
		btnCancelar.addActionListener(e -> {
			this.setVisible(false);
		});
		this.add(btnCancelar, gridConst);
		
		btnAgregarFoto = new JButton("Agregar Foto");
		gridConst.gridy = 8;
		gridConst.gridx = 1;
		gridConst.gridwidth = 1;
		btnAgregarFoto.addActionListener(e -> {
			this.agregarFoto();
		});
		this.add(btnAgregarFoto, gridConst);
	    
		LabelFoto = new JLabel();
	    LabelFoto.setBounds(10,10,670,250);
	    gridConst.gridy = 9;
		gridConst.gridx = 1;
		gridConst.gridwidth = 1;
	    add(LabelFoto, gridConst);
		
	}
	
	private void agregarFoto() {
		
		/*
		 * Crea el explorador de archivos y solo deja seleccionar .jpg
		 * Asigna el archivo a la variable global foto para pasarlo 
		 * al constructor cuando lo agrega*/
		 JFileChooser file = new JFileChooser();
		 file.setCurrentDirectory(new File(System.getProperty("user.home")));
         //filter the files
		 FileNameExtensionFilter filter = new FileNameExtensionFilter("*.JPG", "jpg");
         file.addChoosableFileFilter(filter);
         file.setAcceptAllFileFilterUsed(false);
         int result = file.showSaveDialog(null);
          //if the user click on save in Jfilechooser
         if(result == JFileChooser.APPROVE_OPTION){
             this.foto = file.getSelectedFile();
             String path = this.foto.getAbsolutePath();
             LabelFoto.setIcon(reescalarImgaen(path));
             
         }
          //if the user click on save in Jfilechooser

         else if(result == JFileChooser.CANCEL_OPTION){
             System.out.println("No File Select");
         }
		
	}

	private Icon reescalarImgaen(String path) {
        ImageIcon MyImage = new ImageIcon(path);
        Image img = MyImage.getImage();
        Image newImg = img.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        ImageIcon image = new ImageIcon(newImg);
        return image;
	}

	public Titular _altaTitular() {
		/*
		 * Crea el objeto titular con los datos de la pantalla
		 * */
		
		Titular titular =new Titular();
		titular.setApellido(txtApellido.getText());
		titular.setNombre(txtNombreTitular.getText());
		titular.setDni(this.dni); 
		titular.setFecha_de_nacimiento(fdn);
		titular.setDireccion(txtDireccion.getText());
		titular.setDonante_de_organos(chkDonante.isSelected());
		titular.setSangre((Grupo_sanguineo) cmbGrupoSanguineo.getSelectedItem());
		titular.setFoto(foto);
		
		return titular;
	}
	

	
}
