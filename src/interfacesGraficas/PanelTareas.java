package interfacesGraficas;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Panel;
import java.awt.Rectangle;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;



public class PanelTareas extends JPanel {
	
	private JLabel lblUsuario;
	private JLabel lblLogo;
	private JButton btnNuevaLicencia;
	private JButton btnRenovarLicencia;
	private JButton btnEmitirCopiaLicencia;
	private JButton btnBuscar;
	private JButton btnSalir;
	
	private JPanel panelGobierno;
	private JPanel panelTareas;
	private JPanel panelUsuario;
	
	public PanelTareas() {
		this.construirBanner();
		this.contruirPanelUsuario();
		this.construirPanelTareas();
		this.crearGrilla();
	}

	private void crearGrilla() {
		this.setLayout(new BorderLayout());
		this.setBackground(Color.WHITE);
		this.add(panelGobierno, BorderLayout.NORTH);
		this.add(panelTareas,BorderLayout.WEST);
		this.add(panelUsuario,BorderLayout.EAST);
		
		Border b =BorderFactory.createLineBorder(Color.WHITE, 10);
		panelTareas.setBorder(b);
		//setBorder(BorderFactory.createEmptyBorder(top,left,bottom,right));
		}


	private void construirBanner() {
		panelGobierno = new JPanel(new GridBagLayout());
		panelGobierno.setBackground(new Color(255, 168, 0));
		panelGobierno.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		
		GridBagConstraints gridConst =  new GridBagConstraints();
		
		JLabel lblNombre = new JLabel("Sistema de Gestión de Licencias de Conducir");
		lblNombre.setFont(new Font(Font.DIALOG, Font.PLAIN, 20));
		
		lblLogo = new JLabel();
		lblLogo.setIcon(new ImageIcon("imagenes/LogoGobierno.png"));
		
		lblLogo.setHorizontalAlignment(SwingConstants.LEFT);
		lblNombre.setHorizontalAlignment(JLabel.RIGHT);
		
		gridConst.weighty = 1.0;
		gridConst.weightx = 1.0;
		gridConst.gridx = 0;
		gridConst.gridy = 0;
		panelGobierno.add(lblLogo,gridConst);
		gridConst.gridx = 1;
		gridConst.gridy = 0;
		gridConst.gridwidth = 1;
		gridConst.gridheight = 4;
		panelGobierno.add(lblNombre,gridConst);
	
	}

	private void contruirPanelUsuario() {
		panelUsuario=new JPanel(new GridBagLayout());
		panelUsuario.setBackground(Color.WHITE);
		GridBagConstraints gridConst =  new GridBagConstraints();
		
		lblUsuario = new JLabel();
		lblUsuario.setIcon(new ImageIcon("imagenes/lblUsuario.png"));
		gridConst.weighty = 1.0;
		gridConst.weightx = 1.0;
		gridConst.gridx = 0;
		gridConst.gridy = 0;
		panelUsuario.add(lblUsuario,gridConst);
		
		btnSalir = new JButton();
		btnSalir.setIcon(new ImageIcon("imagenes/btnSalir.png"));
		btnSalir.setRolloverIcon(new ImageIcon("imagenes/btnSalirPressed.png"));
		btnSalir.setBorderPainted(false);
		btnSalir.setOpaque(false);
		btnSalir.setContentAreaFilled(false);
		btnSalir.addActionListener(e->System.exit(99));
		gridConst.gridx = 0;
		gridConst.gridy = 1;
		panelUsuario.add(btnSalir,gridConst);
		
	}

	private void construirPanelTareas() {
				
		panelTareas=new JPanel(new GridBagLayout());
		GridBagConstraints gridConst =  new GridBagConstraints();
		
		btnNuevaLicencia = new JButton();
		btnNuevaLicencia.setIcon(new ImageIcon("imagenes/btnEmitirLicencia.png"));
		btnNuevaLicencia.setRolloverIcon(new ImageIcon("imagenes/btnEmitirLicenciaPressed.png"));
		btnNuevaLicencia.setBorderPainted(false);
		btnNuevaLicencia.setOpaque(false);
		btnNuevaLicencia.setContentAreaFilled(false);
		gridConst.gridx = 0;
		gridConst.gridy = 0;
		panelTareas.add(btnNuevaLicencia, gridConst);
		
		
		btnNuevaLicencia.addActionListener(e -> {
			
			EmitirLicencia panelCards = new EmitirLicencia();
			
			JFrame newFrame = new JFrame("Emitir Licencia");
			
			newFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			newFrame.setVisible(true);
			newFrame.setSize(1000, 900);

			newFrame.setContentPane(panelCards);
			
			newFrame.pack();
			
			Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
	        newFrame.setLocation(dim.width/2- newFrame.getSize().width/2, dim.height/2- newFrame.getSize().height/2);
			
	        ImageIcon img = new ImageIcon("imagenes/icono.png");
	        newFrame.setIconImage(img.getImage());
		});
		
		btnRenovarLicencia = new JButton();
		btnRenovarLicencia.setIcon(new ImageIcon("imagenes/btnRenovarLicencia.png"));
		btnRenovarLicencia.setRolloverIcon(new ImageIcon("imagenes/btnRenovarLicenciaPressed.png"));
		btnRenovarLicencia.setBorderPainted(false);
		btnRenovarLicencia.setOpaque(false);
		btnRenovarLicencia.setContentAreaFilled(false);
		gridConst.gridy = 1;
		panelTareas.add(btnRenovarLicencia, gridConst);
		
		btnRenovarLicencia.addActionListener(e -> {
			
			RenovarLicencia panelCards = new RenovarLicencia();
			
			JFrame newFrame = new JFrame("Renovar Licencia");
			
			newFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			newFrame.setVisible(true);
			newFrame.setSize(1000, 900);

			newFrame.setContentPane(panelCards);
			
			newFrame.pack();
			
			Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
	        newFrame.setLocation(dim.width/2- newFrame.getSize().width/2, dim.height/2- newFrame.getSize().height/2);
			
	        ImageIcon img = new ImageIcon("imagenes/icono.png");
	        newFrame.setIconImage(img.getImage());
		});
		
		
		btnEmitirCopiaLicencia = new JButton();
		btnEmitirCopiaLicencia.setIcon(new ImageIcon("imagenes/btnEmitirCopia.png"));
		btnEmitirCopiaLicencia.setRolloverIcon(new ImageIcon("imagenes/btnEmitirCopiaPressed.png"));
		btnEmitirCopiaLicencia.setBorderPainted(false);
		btnEmitirCopiaLicencia.setOpaque(false);
		btnEmitirCopiaLicencia.setContentAreaFilled(false);
		gridConst.gridy = 2;
		panelTareas.add(btnEmitirCopiaLicencia, gridConst);
		
		
		
		btnBuscar = new JButton();
		btnBuscar.setIcon(new ImageIcon("imagenes/btnBuscarLicencias.png"));
		btnBuscar.setRolloverIcon(new ImageIcon("imagenes/btnBuscarLicenciasPressed.png"));
		btnBuscar.setBorderPainted(false);
		btnBuscar.setOpaque(false);
		btnBuscar.setContentAreaFilled(false);
		btnBuscar.addActionListener(e -> {
			
			PanelLicenciasExpiradas panelCards = new PanelLicenciasExpiradas();
			
			JFrame newFrame = new JFrame("Licencias expiradas");
			
			newFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			newFrame.setVisible(true);
			newFrame.setSize(1000, 900);

			newFrame.setContentPane(panelCards);
			
			newFrame.pack();
			
			Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
	        newFrame.setLocation(dim.width/2- newFrame.getSize().width/2, dim.height/2- newFrame.getSize().height/2);
			
	        ImageIcon img = new ImageIcon("imagenes/icono.png");
	        newFrame.setIconImage(img.getImage());
		});
		gridConst.gridy = 3;
		panelTareas.add(btnBuscar, gridConst);
	}
	
	public void efectoBotones() {
		/*btnNuevaLicencia.getModel().addChangeListener(new ChangeListener() {
		    public void stateChanged(ChangeEvent e) {
		        ButtonModel model = (ButtonModel) e.getSource();
		        if (model.isRollover()) {
		        	btnNuevaLicencia.setIcon(new ImageIcon("imagenes/btnEmitirLicenciaPressed.png"));
		        } else {
		        	btnNuevaLicencia.setIcon(new ImageIcon("imagenes/btnEmitirLicencia.png"));
		        }
		    }
		});*/
	}
}
