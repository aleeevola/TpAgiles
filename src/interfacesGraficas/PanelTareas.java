package interfacesGraficas;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelTareas extends JPanel {
	
	private JLabel lblUsuario;
	private JButton btnNuevaLicencia;
	private JButton btnRenovarLicencia;
	private JButton btnEmitirCopiaLicencia;
	private JButton btnBuscar;
	private JButton btnSalir;
	
	public PanelTareas() {
		this.setLayout(new GridBagLayout());
		this.construir();
	}

	private void construir() {
		
		GridBagConstraints gridConst =  new GridBagConstraints();
		
		gridConst.anchor = GridBagConstraints.CENTER;
		
		lblUsuario = new JLabel();
		lblUsuario.setIcon(new ImageIcon("imagenes/lblUsuario.png"));
		gridConst.gridx = 0;
		gridConst.gridy = 0;
		gridConst.insets = new Insets(10, 15, 15, 15);
		this.add(lblUsuario, gridConst);
		
		btnNuevaLicencia = new JButton();
		btnNuevaLicencia.setIcon(new ImageIcon("imagenes/btnEmitirLicencia.png"));
		btnNuevaLicencia.setPressedIcon(new ImageIcon("imagenes/btnEmitirLicenciaPressed.png"));
		btnNuevaLicencia.setBorderPainted(false);
		btnNuevaLicencia.setOpaque(false);
		btnNuevaLicencia.setContentAreaFilled(false);
		gridConst.gridy = 1;
		gridConst.insets = new Insets(0, 5, 15, 0);
		this.add(btnNuevaLicencia, gridConst);
		
		btnRenovarLicencia = new JButton();
		btnRenovarLicencia.setIcon(new ImageIcon("imagenes/btnRenovarLicencia.png"));
		btnRenovarLicencia.setPressedIcon(new ImageIcon("imagenes/btnRenovarLicenciaPressed.png"));
		btnRenovarLicencia.setBorderPainted(false);
		btnRenovarLicencia.setOpaque(false);
		btnRenovarLicencia.setContentAreaFilled(false);
		gridConst.gridy = 2;
		this.add(btnRenovarLicencia, gridConst);
		
		btnEmitirCopiaLicencia = new JButton();
		btnEmitirCopiaLicencia.setIcon(new ImageIcon("imagenes/btnEmitirCopia.png"));
		btnEmitirCopiaLicencia.setPressedIcon(new ImageIcon("imagenes/btnEmitirCopiaPressed.png"));
		btnEmitirCopiaLicencia.setBorderPainted(false);
		btnEmitirCopiaLicencia.setOpaque(false);
		btnEmitirCopiaLicencia.setContentAreaFilled(false);
		gridConst.gridy = 3;
		this.add(btnEmitirCopiaLicencia, gridConst);
		
		btnBuscar = new JButton();
		btnBuscar.setIcon(new ImageIcon("imagenes/btnBuscarLicencias.png"));
		btnBuscar.setPressedIcon(new ImageIcon("imagenes/btnBuscarLicenciasPressed.png"));
		btnBuscar.setBorderPainted(false);
		btnBuscar.setOpaque(false);
		btnBuscar.setContentAreaFilled(false);
		gridConst.gridy = 4;
		this.add(btnBuscar, gridConst);
		
		btnSalir = new JButton();
		btnSalir.setIcon(new ImageIcon("imagenes/btnSalir.png"));
		btnSalir.setPressedIcon(new ImageIcon("imagenes/btnSalirPressed.png"));
		btnSalir.setBorderPainted(false);
		btnSalir.setOpaque(false);
		btnSalir.setContentAreaFilled(false);
		gridConst.gridy = 5;
		btnSalir.addActionListener(e->System.exit(99));
		this.add(btnSalir, gridConst);
		
	}
}
