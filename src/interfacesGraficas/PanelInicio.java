package interfacesGraficas;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class PanelInicio extends JPanel {

	private JLabel lblNombre;
	private JLabel lblTitulo;
	private JLabel lblNro;
	private JLabel lblPass;
	private JTextField txtNro;
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
		
		lblNro = new JLabel("Usuario");
		gridConst.gridy = 3;
		this.add(lblNro, gridConst);
		
		txtNro = new JTextField("");
		txtNro.setColumns(10);
		gridConst.gridy = 4;
		this.add(txtNro, gridConst);
		
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
			
			//Integer nroLegajo = Integer.valueOf(txtNro.getText());
			//String password = String.valueOf(txtPass.getPassword());

			
		});
		gridConst.gridy = 7;
		this.add(btnIniciar, gridConst);
		
		txtNro.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                	
                	//Integer nroLegajo = Integer.valueOf(txtNro.getText());
        			//String password = String.valueOf(txtPass.getPassword());
        			
                    e.consume();
                }
            }
        });
		
		txtPass.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                	
                	//Integer nroLegajo = Integer.valueOf(txtNro.getText());
        			//String password = String.valueOf(txtPass.getPassword());
        			
                    e.consume();
                }
            }
        });
		
		btnIniciar.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                	
                	//Integer nroLegajo = Integer.valueOf(txtNro.getText());
        			//String password = String.valueOf(txtPass.getPassword());

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
}
