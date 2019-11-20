package interfacesGraficas;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class PanelInicio extends JPanel {

	private JLabel lblNombre;
	private JLabel lblTitulo;
	private JLabel lblUsuario;
	private JLabel lblPass;
	private JTextField txtUsuario;
	private JPasswordField txtPass;
	private JButton btnIniciar;
	private JButton btnSalir;
	
	public PanelInicio() {
		this.setLayout(new GridBagLayout());
		this.construir();
	}
	
	public void construir() {
		
		GridBagConstraints gridConst = new GridBagConstraints();
		
		gridConst.anchor = GridBagConstraints.CENTER;
		
		lblNombre = new JLabel("Sistema de Gestión de Licencias de Conducir");
		lblNombre.setFont(new Font(Font.DIALOG, Font.BOLD, 20));
		gridConst.gridx = 1;
		gridConst.gridy = 1;
		gridConst.insets = new Insets(15, 5, 20, 5);
		this.add(lblNombre, gridConst);
		
		lblTitulo = new JLabel("Inicio de Sesión");
		gridConst.gridy = 2;
		gridConst.insets = new Insets(0, 5, 15, 5);
		this.add(lblTitulo, gridConst);
		
		lblUsuario = new JLabel("Usuario");
		gridConst.gridy = 3;
		this.add(lblUsuario, gridConst);
		
		txtUsuario = new JTextField("");
		txtUsuario.setColumns(10);
		gridConst.gridy = 4;
		this.add(txtUsuario, gridConst);
		
		lblPass = new JLabel("Contraseña");
		gridConst.gridy = 5;
		this.add(lblPass, gridConst);
		
		txtPass = new JPasswordField("");
		txtPass.setColumns(10);
		gridConst.gridy = 6;
		this.add(txtPass, gridConst);
		
		btnIniciar = new JButton("Iniciar");
		btnIniciar.setPreferredSize(new Dimension(70, 25));
		btnIniciar.addActionListener(e -> {
			iniciarSesion();
		});
		gridConst.gridy = 7;
		this.add(btnIniciar, gridConst);
		
		txtUsuario.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                	iniciarSesion();
                    e.consume();
                }
            }
        });
		
		txtPass.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                	iniciarSesion();
                    e.consume();
                }
            }
        });
		
		btnIniciar.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                	iniciarSesion();
                    e.consume();
                }
            }
        });
		
		btnSalir = new JButton("Salir");
		btnSalir.setPreferredSize(new Dimension(70, 25));
		gridConst.gridy = 8;
		btnSalir.addActionListener(e->System.exit(99));
		this.add(btnSalir, gridConst);
		
	}
	
	public void iniciarSesion() {
		
		JFrame frame = (JFrame) SwingUtilities.getAncestorOfClass(JFrame.class, this);
		this.setVisible(false);
		
		String usuario = String.valueOf(txtUsuario.getText());
		String pass = String.valueOf(txtPass.getPassword());
		
		PanelTareas tareas = new PanelTareas();
		frame.setContentPane(tareas);
		
		frame.pack();
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(dim.width/2- frame.getSize().width/2, dim.height/2- frame.getSize().height/2);
		
	}
}
