package interfacesGraficas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.text.SimpleDateFormat;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import clases.Licencia;
import clases.Titular;

public class FrameImprimirComprobante extends JFrame{
	
	private SimpleDateFormat formatFecha = new SimpleDateFormat("dd/MM/yyyy");
	
	private JPanel panelBanner;
	private JPanel panelComprobante;
	
	private JLabel lblLogo;
	
	private JLabel lblTitulo;
	private JLabel lblTitular;
	private JLabel lblTitularDato; //Concatenar el nombre y el apellido para que queden en un solo label
	private JLabel lblDomicilio;
	private JLabel lblDomicilioDato;
	private JLabel lblLicencia;
	private JLabel lblLicenciaDato;
	private JLabel lblClase;
	private JLabel lblClaseDato;
	private JLabel lblFechaEmision;
	private JLabel lblFechaEmisionDato;
	private JLabel lblFechaVencimiento;
	private JLabel lblFechaVencimientoDato;
	private JLabel lblDetalle;
	private JLabel lblPrecio;
	
	private JLabel lblDetalleAdministrativo;
	private JLabel lblCostoAdministrativo;
	private JLabel lblDetalleLicencia;
	private JLabel lblCostoLicencia;	
	
	public FrameImprimirComprobante() {
		
		this.setLayout(new BorderLayout());
		this.setVisible(true);
		this.setSize(650, 650);
		
		this.construirBanner();
		this.construirComprobante();
		
		this.add(panelBanner, BorderLayout.NORTH);
		this.add(panelComprobante, BorderLayout.CENTER);

	}

	private void construirBanner() {
		panelBanner = new JPanel();
		panelBanner.setBackground(new Color(255, 168, 0));
		
		lblLogo = new JLabel();
		lblLogo.setIcon(new ImageIcon("imagenes/LogoGobierno.png"));
		panelBanner.add(lblLogo, BorderLayout.CENTER);
	}
	
	private void construirComprobante() {
		
		panelComprobante = new JPanel(new GridBagLayout());
		
		panelComprobante.setBackground(Color.WHITE);

		GridBagConstraints gridConst =  new GridBagConstraints();

		gridConst.anchor = GridBagConstraints.CENTER;


		lblTitulo = new JLabel("Comprobante de pago");
		lblTitulo.setFont(new Font(Font.MONOSPACED, Font.BOLD, 16));
		gridConst.gridy = 0;
		gridConst.gridx = 0;
		gridConst.gridwidth = 3;
		gridConst.insets = new Insets(5, 5, 20, 5);
		panelComprobante.add(lblTitulo, gridConst);
		
		JLabel lblSeparador = new JLabel("-------------------------------------------------");
		gridConst.gridy = 1;
		panelComprobante.add(lblSeparador, gridConst);
		
		gridConst.anchor = GridBagConstraints.LINE_START;

		Font fuenteDetalle = new Font(Font.MONOSPACED, Font.BOLD, 14);
		
		lblTitular = new JLabel("Nombre y Apellido: ");
		lblTitular.setFont(fuenteDetalle);
		gridConst.gridy = 2;
		gridConst.gridwidth = 1;
		gridConst.insets = new Insets(5, 5, 15, 5);
		panelComprobante.add(lblTitular, gridConst);
		
		lblTitularDato = new JLabel("");
		lblTitularDato.setFont(fuenteDetalle);
		gridConst.gridx = 1;
		gridConst.gridwidth = 2;
		gridConst.insets = new Insets(5, 5, 15, 5);
		panelComprobante.add(lblTitularDato, gridConst);
		
		lblDomicilio = new JLabel("Domicilio: ");
		lblDomicilio.setFont(fuenteDetalle);
		gridConst.gridy = 3;
		gridConst.gridx = 0;
		gridConst.gridwidth = 1;
		gridConst.insets = new Insets(5, 5, 15, 5);
		panelComprobante.add(lblDomicilio, gridConst);
		
		lblDomicilioDato = new JLabel();
		lblDomicilioDato.setFont(fuenteDetalle);
		gridConst.gridy = 3;
		gridConst.gridx = 1;
		gridConst.gridwidth = 2;
		gridConst.insets = new Insets(0, 5, 15, 5);
		panelComprobante.add(lblDomicilioDato, gridConst);
		
		lblLicencia = new JLabel("Licencia Nro: ");
		lblLicencia.setFont(fuenteDetalle);
		gridConst.gridy = 4;
		gridConst.gridx = 0;
		gridConst.gridwidth = 1;
		gridConst.insets = new Insets(5, 5, 15, 5);
		panelComprobante.add(lblLicencia, gridConst);
		
		lblLicenciaDato = new JLabel();
		lblLicenciaDato.setFont(fuenteDetalle);
		gridConst.gridy = 4;
		gridConst.gridx = 1;
		gridConst.insets = new Insets(5, 5, 15, 5);
		panelComprobante.add(lblLicenciaDato, gridConst);
		
		lblClase = new JLabel("Clase: ");
		lblClase.setFont(fuenteDetalle);
		gridConst.gridy = 5;
		gridConst.gridx = 0;
		gridConst.insets = new Insets(5, 5, 15, 5);
		panelComprobante.add(lblClase, gridConst);
		
		lblClaseDato = new JLabel();
		lblClaseDato.setFont(fuenteDetalle);
		gridConst.gridx = 1;
		gridConst.insets = new Insets(5, 5, 15, 5);
		panelComprobante.add(lblClaseDato, gridConst);
		
		lblFechaEmision = new JLabel("Fecha de emisión: ");
		lblFechaEmision.setFont(fuenteDetalle);
		gridConst.gridy = 6;
		gridConst.gridx = 0;
		panelComprobante.add(lblFechaEmision, gridConst);

		lblFechaEmisionDato = new JLabel();
		lblFechaEmisionDato.setFont(fuenteDetalle);
		gridConst.gridx = 1;
		panelComprobante.add(lblFechaEmisionDato, gridConst);
		
		lblFechaVencimiento = new JLabel("Fecha de vencimiento: ");
		lblFechaVencimiento.setFont(fuenteDetalle);
		gridConst.gridy = 7;
		gridConst.gridx = 0;
		panelComprobante.add(lblFechaVencimiento, gridConst);
		
		lblFechaVencimientoDato = new JLabel();
		lblFechaVencimientoDato.setFont(fuenteDetalle);
		gridConst.gridx = 1;
		panelComprobante.add(lblFechaVencimientoDato, gridConst);

		gridConst.anchor = GridBagConstraints.CENTER;
		
		JLabel lblSeparador2 = new JLabel("-------------------------------------------------");
		gridConst.gridx = 0;
		gridConst.gridy = 8;
		gridConst.gridwidth = 3;
		panelComprobante.add(lblSeparador2, gridConst);
		
		gridConst.anchor = GridBagConstraints.LINE_START;
		
		lblDetalle = new JLabel("Detalle");
		lblDetalle.setFont(fuenteDetalle);
		gridConst.gridy = 9;
		gridConst.gridwidth = 1;
		gridConst.insets = new Insets(5, 5, 15, 5);
		panelComprobante.add(lblDetalle, gridConst);
		
		lblDetalleAdministrativo = new JLabel("Costos administrativos");
		lblDetalleAdministrativo.setFont(fuenteDetalle);
		gridConst.gridy = 10;
		panelComprobante.add(lblDetalleAdministrativo, gridConst);
		
		lblDetalleLicencia = new JLabel();
		lblDetalleLicencia.setFont(fuenteDetalle);
		gridConst.gridy = 11;
		panelComprobante.add(lblDetalleLicencia, gridConst);
		
		gridConst.anchor = GridBagConstraints.LINE_END;
		
		lblPrecio = new JLabel("Precio");
		lblPrecio.setFont(fuenteDetalle);
		gridConst.gridy = 9;
		gridConst.gridx = 2;
		gridConst.gridwidth = 2;
		gridConst.insets = new Insets(5, 5, 15, 5);
		panelComprobante.add(lblPrecio, gridConst);
		
		lblCostoAdministrativo = new JLabel("$ 8.0");
		lblCostoAdministrativo.setFont(fuenteDetalle);
		gridConst.gridy = 10;
		panelComprobante.add(lblCostoAdministrativo, gridConst);
		
		lblCostoLicencia = new JLabel();
		lblCostoLicencia.setFont(fuenteDetalle);
		gridConst.gridy = 11;
		panelComprobante.add(lblCostoLicencia, gridConst);

		
	}
	
	public void cargarDatos(Titular titular, Licencia licencia, Double costoLicencia) {
		
		lblTitularDato.setText(titular.getNombre()+" "+titular.getApellido());
		lblDomicilioDato.setText(titular.getDireccion());
		lblLicenciaDato.setText(licencia.getId()+"-"+titular.getDni());
		lblClaseDato.setText(licencia.getClase().toString());
		
		String formatoFecha = formatFecha.format(licencia.getFecha_de_emision());
		lblFechaEmisionDato.setText(formatoFecha);
		
		formatoFecha = formatFecha.format(licencia.getFecha_de_vencimiento());
		lblFechaVencimientoDato.setText(formatoFecha);
		
		lblDetalleLicencia.setText("Costo de Licencia "+licencia.getClase().toString());
		lblCostoLicencia.setText("$ "+costoLicencia);
		
		this.pack();

	}
	
}
