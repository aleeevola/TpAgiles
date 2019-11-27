package interfacesGraficas;

import java.awt.CardLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

// Esta clase se utiliza como un gestor de paneles. Con este tipo de Layout resulta mucho mas simple el manejo de pantallas.

public class EmitirLicencia extends JPanel{
	/*
	public final static String EMITIRPANEL = "Card con Panel Emitir";
	public final static String ALTAPANEL = "Card con Panel Dar de Alta";
	//	cards
	PanelEmitirLicencia cardEmitir;
	PanelDarAltaTitular cardAlta;
	CardLayout layout;
	//JPanel principal;
	Container c;

	public EmitirLicencia() {
		c=this;
		layout = new CardLayout();
		this.setLayout(layout);
		//this.setLayout(layout);
		cardEmitir = new PanelEmitirLicencia();
		cardAlta = new PanelDarAltaTitular();
		cardEmitir.setLayout(layout);
		cardEmitir.setC(this);
		
		this.add(cardEmitir, EMITIRPANEL);
		this.add(cardAlta, ALTAPANEL);
		layout.show(this, EMITIRPANEL);
		cardAlta.getBtnVolver().addActionListener(e -> {
			this.layout.previous(this);
		});
		
		cardEmitir.getBtnSiguiente().addActionListener(e -> {
			cardEmitir.emitirLicencia();
			this.layout.next(c);
			System.out.println("q hace2");
		});
	}*/
	
	public JPanel construirCardEmitir() {
		return null;
	}

}
