package interfacesGraficas;

import java.awt.CardLayout;

import javax.swing.JPanel;

// Esta clase se utiliza como un gestor de paneles. Con este tipo de Layout resulta mucho mas simple el manejo de pantallas.

public class EmitirLicencia extends JPanel{
	
	public final static String EMITIRPANEL = "Card con Panel Emitir";
	public final static String ALTAPANEL = "Card con Panel Dar de Alta";
	//	cards
	PanelEmitirLicencia cardEmitir;
	PanelDarAltaTitular cardAlta;
	CardLayout layout;

	public EmitirLicencia() {
		layout = new CardLayout();
		this.setLayout(layout);
		cardEmitir = new PanelEmitirLicencia();
		cardAlta = new PanelDarAltaTitular();
		this.add(cardAlta, ALTAPANEL);
		this.add(cardEmitir, EMITIRPANEL);
		layout.show(this, EMITIRPANEL);
	}
	
	public JPanel construirCardEmitir() {
		return null;
	}
}
