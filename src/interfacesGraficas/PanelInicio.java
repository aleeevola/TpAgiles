package interfacesGraficas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
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

	private JPanel panelGobierno;
	private JPanel panelCampos;
	private JPanel panelBotones;
	private JLabel lblLogo;

	public PanelInicio() {
		this.setLayout(new BorderLayout());
		this.setBackground(Color.WHITE);

		this.construirPanelCampos();
		construirBanner();
		construirPanelBotones();
		this.add(panelGobierno, BorderLayout.NORTH);
		this.add(panelCampos,BorderLayout.CENTER);
		this.add(panelBotones,BorderLayout.SOUTH);

	}

	private void construirPanelBotones() {
		panelBotones=new JPanel(new GridBagLayout());
		panelBotones.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		GridBagConstraints gridConst = new GridBagConstraints();
		gridConst.anchor = GridBagConstraints.CENTER;

		btnIniciar = new JButton("Iniciar");
		btnIniciar.setPreferredSize(new Dimension(70, 25));
		btnIniciar.addActionListener(e -> {
			iniciarSesion();
		});
		gridConst.gridy = 0;
		panelBotones.add(btnIniciar, gridConst);

		btnSalir = new JButton("Salir");
		btnSalir.setPreferredSize(new Dimension(70, 25));
		gridConst.gridx = 1;
		btnSalir.addActionListener(e->System.exit(99));
		panelBotones.add(btnSalir, gridConst);

		btnIniciar.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                	iniciarSesion();
                    e.consume();
                }
            }
        });
	}

	private void construirBanner() {
		panelGobierno = new JPanel(new GridBagLayout());
		panelGobierno.setBackground(new Color(255, 168, 0));
		panelGobierno.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

		GridBagConstraints gridConst =  new GridBagConstraints();

		lblNombre = new JLabel("Sistema de Gesti�n de Licencias de Conducir");
		lblNombre.setFont(new Font(Font.DIALOG, Font.BOLD, 15));
		lblNombre.setForeground(Color.WHITE);

		lblLogo = new JLabel();
		lblLogo.setIcon(new ImageIcon("imagenes/LogoGobierno.png"));

		lblLogo.setHorizontalAlignment(SwingConstants.LEFT);
		lblNombre.setHorizontalAlignment(JLabel.RIGHT);

		gridConst.weighty = 1.0;
		gridConst.weightx = 1.0;
		gridConst.gridx = 0;
		gridConst.gridy = 0;
		panelGobierno.add(lblLogo,gridConst);
		gridConst.gridx = 0;
		gridConst.gridy = 1;
		panelGobierno.add(lblNombre,gridConst);

	}

	public void construirPanelCampos() {

		panelCampos=new JPanel(new GridBagLayout());
		panelCampos.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

		GridBagConstraints gridConst = new GridBagConstraints();

		gridConst.anchor = GridBagConstraints.CENTER;

		//lblNombre = new JLabel("Sistema de Gesti�n de Licencias de Conducir");
		//lblNombre.setFont(new Font(Font.DIALOG, Font.BOLD, 20));
		gridConst.gridx = 1;
		gridConst.gridy = 1;
		gridConst.insets = new Insets(15, 5, 20, 5);
		//panelCampos.add(lblNombre, gridConst);

		lblTitulo = new JLabel("Inicio de Sesi�n");
		gridConst.gridy = 2;
		gridConst.insets = new Insets(0, 5, 15, 5);
		panelCampos.add(lblTitulo, gridConst);

		lblUsuario = new JLabel("Usuario");
		gridConst.gridy = 3;
		panelCampos.add(lblUsuario, gridConst);

		txtUsuario = new JTextField("");
		txtUsuario.setColumns(10);
		gridConst.gridy = 4;
		panelCampos.add(txtUsuario, gridConst);

		lblPass = new JLabel("Contrase�a");
		gridConst.gridy = 5;
		panelCampos.add(lblPass, gridConst);

		txtPass = new JPasswordField("");
		txtPass.setColumns(10);
		gridConst.gridy = 6;
		panelCampos.add(txtPass, gridConst);



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
