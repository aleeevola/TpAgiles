package interfacesGraficas;

import java.awt.CardLayout;
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
import javax.swing.SwingUtilities;
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
	private JTextField txtNacimiento;
	private JLabel lblDireccion;
	private JTextField txtDireccion;
	private JLabel lblGrupoSanguineo;
	private JLabel lblDonante;
	private JLabel lblClaseSolicitada;

	private JComboBox cmbClase;
	private JComboBox cmbGrupoSanguineo;

	private JButton btnSiguiente;
	private JButton btnVolver;

	private JCheckBox chkDonante;

	private GestorDeLicencia gestorLicencia = new GestorDeLicencia();

	private int dni;
	private Date fdn;

	private JButton btnAgregarFoto;
	private JLabel LabelFoto;
	private File foto;

	public PanelDarAltaTitular() {
		this.setLayout(new GridBagLayout());
		this.construir();
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

		lblApellido = new JLabel("Apellido: ");
		gridConst.gridwidth = 1;
		gridConst.gridy = 2;
		gridConst.insets = new Insets(0, 5, 15, 5);
		this.add(lblApellido, gridConst);

		txtApellido = new JTextField();
		txtApellido.setColumns(15);
		gridConst.gridx = 1;
		gridConst.insets = new Insets(0, 0, 15, 5);
		this.add(txtApellido, gridConst);

		lblNombreTitular = new JLabel("Nombre:");
		gridConst.gridx = 2;
		gridConst.insets = new Insets(0, 0, 15, 5);
		this.add(lblNombreTitular, gridConst);

		txtNombreTitular = new JTextField(20);
		txtNombreTitular.setColumns(15);
		gridConst.gridx = 3;
		gridConst.insets = new Insets(0, 0, 15, 5);
		this.add(txtNombreTitular, gridConst);

		lblDNI = new JLabel("DNI:");
		gridConst.gridy = 3;
		gridConst.gridx = 0;
		gridConst.insets = new Insets(0, 5, 15, 5);
		this.add(lblDNI, gridConst);

		txtDNI = new JTextField(9);
		txtDNI.setColumns(15);
		gridConst.gridx = 1;
		gridConst.insets = new Insets(0, 0, 15, 5);
		this.add(txtDNI, gridConst);

		lblNacimiento = new JLabel("Fecha de Nacimiento:");
		gridConst.gridx = 2;
		gridConst.insets = new Insets(0, 0, 15, 5);
		this.add(lblNacimiento, gridConst);

		txtNacimiento = new JTextField(15);
		gridConst.gridx = 3;
		gridConst.insets = new Insets(0, 0, 15, 5);
		this.add(txtNacimiento, gridConst);

		lblDireccion = new JLabel("Domicilio:");
		gridConst.gridy = 4;
		gridConst.gridx = 0;
		gridConst.insets = new Insets(0, 5, 15, 5);
		this.add(lblDireccion, gridConst);

		txtDireccion = new JTextField(25);
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
		gridConst.insets = new Insets(0, 0, 15, 5);
		this.add(cmbGrupoSanguineo, gridConst);

		gridConst.anchor = GridBagConstraints.LINE_END;

		lblDonante = new JLabel("Donante de órganos:");
		gridConst.gridx = 2;
		this.add(lblDonante, gridConst);

		gridConst.anchor = GridBagConstraints.LINE_START;

		chkDonante = new JCheckBox();
		gridConst.gridx = 3;
		gridConst.insets = new Insets(0, 0, 15, 5);
		this.add(chkDonante, gridConst);

		lblClaseSolicitada = new JLabel("Clase Solicitada:");
		gridConst.gridy = 6;
		gridConst.gridx = 0;
		gridConst.insets = new Insets(0, 5, 15, 5);
		this.add(lblClaseSolicitada, gridConst);

		cmbClase = new JComboBox(Clase.values());
		cmbClase.setEditable(false);
		cmbClase.setPreferredSize(new Dimension(100, 20));
		gridConst.gridx = 1;
		gridConst.gridwidth = 2;
		gridConst.insets = new Insets(0, 0, 15, 5);
		this.add(cmbClase, gridConst);

		btnAgregarFoto = new JButton("Agregar Foto");
		gridConst.gridy = 7;
		gridConst.gridx = 1;
		gridConst.gridwidth = 1;
		btnAgregarFoto.addActionListener(e -> {
			this.agregarFoto();
		});
		this.add(btnAgregarFoto, gridConst);

		LabelFoto = new JLabel();
	    LabelFoto.setBounds(10,10,670,250);
	    gridConst.gridy = 8;
		gridConst.gridx = 1;
		gridConst.gridwidth = 1;
	    this.add(LabelFoto, gridConst);
		
		btnSiguiente = new JButton("Dar de alta");
		gridConst.gridy = 9;
		gridConst.gridx = 2;
		gridConst.gridwidth = 1;
		this.add(btnSiguiente, gridConst);

		btnSiguiente.addActionListener(e -> {
			/*
			 * Cuando apreta el boton siguiente debe cerrarse y devolver el objeto titular
			 * */
			try {
				Titular titular = _altaTitular();
				gestorLicencia.darDeAltaNuevoTitular((Clase)cmbClase.getSelectedItem(), titular);

				String Alerta="El titular "+titular.getApellido()+", "+titular.getNombre()+" fue creado con éxito";

				JOptionPane.showMessageDialog(null, Alerta, "Operación Exitosa", JOptionPane.INFORMATION_MESSAGE);

				JOptionPane.showMessageDialog(null, "Licencia asignada con éxito", "Licencia Emitida", JOptionPane.INFORMATION_MESSAGE);

				JFrame frame = (JFrame) SwingUtilities.getAncestorOfClass(JFrame.class, this);
				frame.dispose();

			}

			catch(Exception ex) {
				ex.printStackTrace();
				JOptionPane.showMessageDialog(null, "No se pudo cargar el nuevo titular", "Error", JOptionPane.OK_OPTION);
			}
		});

		gridConst.anchor = GridBagConstraints.LINE_END;

		btnVolver = new JButton("Volver");
		gridConst.gridx = 1;
		btnVolver.addActionListener(e -> {

			EmitirLicencia panelCards = (EmitirLicencia) SwingUtilities.getAncestorOfClass(JPanel.class, this);
			CardLayout cl = (CardLayout) panelCards.getLayout();

			cl.show(panelCards, EmitirLicencia.EMITIRPANEL);

		});
		this.add(btnVolver, gridConst);

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

	public void cargarDatos(Persona contribuyente, Clase clase) {

		Date fecha_de_nacimiento = contribuyente.getFecha_de_nacimiento();
		fdn = fecha_de_nacimiento;
		dni = contribuyente.getDni();

		this.txtNombreTitular.setText(contribuyente.getNombre());
		this.txtNombreTitular.setEditable(false);

		this.txtApellido.setText(contribuyente.getApellido());
		this.txtApellido.setEditable(false);

		this.txtDNI.setText(String.valueOf(contribuyente.getDni()));
		this.txtDNI.setEditable(false);

		//FALTA DARLE FORMATO
		this.txtNacimiento.setText(fecha_de_nacimiento.toString());
		this.txtNacimiento.setEditable(false);

		this.cmbClase.setSelectedItem(clase);
		this.cmbClase.setEditable(false);

		txtDireccion.setText(contribuyente.getDireccion());
		txtDireccion.setEditable(false);

	}
}
