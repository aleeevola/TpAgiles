package interfacesGraficas;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;



public class PanelTareas extends JPanel {
	
	private JLabel lblUsuario;
	private JButton btnNuevaLicencia;
	private JButton btnRenovarLicencia;
	private JButton btnEmitirCopiaLicencia;
	private JButton btnBuscar;
	private JButton btnSalir;
	
//	private JFrame frame=this;
//	private JPanel contentPane;
//	private Dimension medidasPanel=new Dimension(500,800);
	
//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					PanelTareas frame = new PanelTareas();
//					frame.setVisible(true);
//					// PARA QUE APAREZCA CENTRADO
//					frame.setLocationRelativeTo(null);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}
	
	public PanelTareas() {
//		contentPane = new JPanel();
//		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
//		setContentPane(contentPane);
//		this.setSize(medidasPanel);
		
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
		
		btnNuevaLicencia.addActionListener(e -> {
			
			EmitirLicencia panelCards = new EmitirLicencia();
			
			JFrame newFrame = new JFrame();
			
			newFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			newFrame.setVisible(true);
			newFrame.setSize(800, 900);

			newFrame.setContentPane(panelCards);
			
			Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
	        newFrame.setLocation(dim.width/2- newFrame.getSize().width/2, dim.height/2- newFrame.getSize().height/2);
	        
//			this.setSize(800, 900);
//			PanelEmitirLicencia panelEmitir = new PanelEmitirLicencia();
//			panelEmitir.setSize(800, 900);
//			panelEmitir.setPadre(frame);
//			panelEmitir.setAnterior(contentPane);
//			panelEmitir.setBoundsAnterior(new Rectangle(0, 0, medidasPanel.width, medidasPanel.height));
//			setContentPane(panelEmitir);
//			setLocationRelativeTo(null);
			//Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
			//panelEmitir.setLocation(dim.width/2- panelEmitir.getSize().width/2, dim.height/2- panelEmitir.getSize().height/2);
			
		});
		
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
