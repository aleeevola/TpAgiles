package interfacesGraficas;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

public class FrameImprimirComprobante extends JFrame{
	
	private JLabel lblNombre;
	private JLabel lblTitulo;
	private JLabel lblTitular;
	private JLabel lblTitularDato; //Concatenar el nombre y el apellido para que queden en un solo label
	private JLabel lblDomicilio;
	private JLabel lblDomicilioDato;
	private JLabel lblLicencia;
	private JLabel lblLicenciaDato;
	private JLabel lblClase;
	private JLabel lblClaseDato;
	private JLabel lblDetalle;
	private JLabel lblPrecio;

	
	public FrameImprimirComprobante() {
		this.setLayout(new GridBagLayout());
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		

		this.setVisible(true);
		this.setSize(800, 800);
		this.construir();
	}

	private void construir() {
		
		this.getContentPane().setBackground(Color.WHITE);

		
		GridBagConstraints gridConst =  new GridBagConstraints();

		gridConst.anchor = GridBagConstraints.CENTER;

		lblNombre = new JLabel("Sistema de Gestión de Licencias de Conducir");
		lblNombre.setFont(new Font(Font.DIALOG, Font.BOLD, 20));
		gridConst.gridx = 0;
		gridConst.gridy = 0;
		gridConst.gridwidth = 3;
		gridConst.insets = new Insets(15, 5, 10, 5);
		this.add(lblNombre, gridConst);

		lblTitulo = new JLabel("Comprobante");
		lblTitulo.setFont(new Font(Font.DIALOG, Font.BOLD, 16));
		gridConst.gridy = 1;
		gridConst.gridwidth = 3;
		gridConst.insets = new Insets(0, 5, 20, 5);
		this.add(lblTitulo, gridConst);
		
		gridConst.anchor = GridBagConstraints.LINE_START;

		lblTitular = new JLabel("Apellido: ");
		lblTitular.setFont(new Font(Font.DIALOG, Font.BOLD, 14));
		gridConst.gridy = 2;
		gridConst.gridwidth = 1;
		gridConst.insets = new Insets(0, 5, 15, 5);
		this.add(lblTitular, gridConst);
		
		lblTitularDato = new JLabel("");
		gridConst.gridy = 2;
		gridConst.gridx = 1;
		gridConst.gridwidth = 2;
		gridConst.insets = new Insets(0, 5, 15, 5);
		this.add(lblTitularDato, gridConst);
		
		lblDomicilio = new JLabel("Domicilio: ");
		lblDomicilio.setFont(new Font(Font.DIALOG, Font.BOLD, 14));
		gridConst.gridy = 3;
		gridConst.gridx = 0;
		gridConst.gridwidth = 1;
		gridConst.insets = new Insets(0, 5, 15, 5);
		this.add(lblDomicilio, gridConst);
		
		lblDomicilioDato = new JLabel();
		gridConst.gridy = 3;
		gridConst.gridx = 1;
		gridConst.gridwidth = 2;
		gridConst.insets = new Insets(0, 5, 15, 5);
		this.add(lblDomicilioDato, gridConst);
		
		lblLicencia = new JLabel("Licencia Nro: ");
		lblLicencia.setFont(new Font(Font.DIALOG, Font.BOLD, 14));
		gridConst.gridy = 4;
		gridConst.gridx = 0;
		gridConst.gridwidth = 1;
		gridConst.insets = new Insets(0, 5, 15, 5);
		this.add(lblLicencia, gridConst);
		
		lblLicenciaDato = new JLabel();
		gridConst.gridy = 4;
		gridConst.gridx = 1;
		gridConst.gridwidth = 2;
		gridConst.insets = new Insets(0, 5, 15, 5);
		this.add(lblLicenciaDato, gridConst);
		
		lblClase = new JLabel("Clase: ");
		lblClase.setFont(new Font(Font.DIALOG, Font.BOLD, 14));
		gridConst.gridy = 4;
		gridConst.gridx = 2;
		gridConst.gridwidth = 1;
		gridConst.insets = new Insets(0, 5, 15, 5);
		this.add(lblClase, gridConst);
		
		lblLicenciaDato = new JLabel();
		gridConst.gridy = 4;
		gridConst.gridx = 3;
		gridConst.gridwidth = 2;
		gridConst.insets = new Insets(0, 5, 15, 5);
		this.add(lblLicenciaDato, gridConst);

		lblDetalle = new JLabel("Detalle");
		lblDetalle.setFont(new Font(Font.DIALOG, Font.BOLD, 14));
		gridConst.gridy = 5;
		gridConst.gridx = 0;
		gridConst.gridwidth = 1;
		gridConst.insets = new Insets(0, 5, 15, 5);
		this.add(lblDetalle, gridConst);
		
		gridConst.anchor = GridBagConstraints.LINE_END;
		
		lblPrecio = new JLabel("Precio");
		lblPrecio.setFont(new Font(Font.DIALOG, Font.BOLD, 14));
		gridConst.gridy = 5;
		gridConst.gridx = 2;
		gridConst.gridwidth = 1;
		gridConst.insets = new Insets(0, 5, 15, 5);
		this.add(lblPrecio, gridConst);
		
	}
	
}
