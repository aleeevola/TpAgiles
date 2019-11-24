package interfacesGraficas;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.text.SimpleDateFormat;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;

import clases.Licencia;
import clases.Titular;

public class PanelImprimirLicencia extends JPanel {
	
	private SimpleDateFormat formatFecha = new SimpleDateFormat("dd/MM/yyyy");
	
	JLabel lblTitulo;
	BufferedImage imagenLicencia;
	
	public PanelImprimirLicencia() {
		this.construir();
	}

	private void construir() {
		
		try {
			imagenLicencia = ImageIO.read(new File("imagenes/template-licencia.jpg"));
			imagenLicencia = this.resize(imagenLicencia, 731, 455);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(imagenLicencia, 0, 0, this);
		g.dispose();
	}
	
	public void cargarImagen(Licencia licencia, Titular titular) throws Exception{
		
		Graphics g = imagenLicencia.getGraphics();
		
	    g.setFont(new Font(Font.DIALOG, Font.BOLD, 20));
	    g.setColor(Color.BLACK);
	    
	    g.drawString(String.valueOf(titular.getDni()), 368, 120);
	    g.drawString(titular.getApellido(), 368, 148);
	    g.drawString(titular.getNombre(), 368, 170);
	    
	    String fechaFormato = formatFecha.format(titular.getFecha_de_nacimiento());
	    g.drawString(fechaFormato, 368, 198);
	    
	    g.drawString(titular.getDireccion(), 368, 222);
	    
	    fechaFormato = formatFecha.format(licencia.getFecha_de_emision());
	    g.drawString(fechaFormato, 368, 291);
	    
	    int nroCopias = licencia.getNumero_de_copias();
	    String otorgamiento = "";
	    if(nroCopias == 0) {
	    	otorgamiento = "ORIGINAL";
	    } else {
	    	otorgamiento = "RENOVADA ("+nroCopias+")";
	    }
	    g.drawString(otorgamiento, 368, 319);
	    
	    g.drawString(licencia.getClase().toString(), 368, 343);
	    
	    fechaFormato = formatFecha.format(licencia.getFecha_de_vencimiento());
	    g.drawString(fechaFormato, 586, 330);
	    
	    
	    Graphics2D g2d = imagenLicencia.createGraphics();
	    
	    g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.4f));
	    BufferedImage resized = this.resize(titular.getBufferedFoto(), 158, 182);
	    g2d.drawImage(resized, 34, 98, null);
	    
	    paintComponent(g);
		
	}
	
	public BufferedImage resize(BufferedImage img, int newW, int newH) { 
		
	    Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
	    BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);

	    Graphics2D g2d = dimg.createGraphics();
	    g2d.drawImage(tmp, 0, 0, null);
	    g2d.dispose();

	    return dimg;
	} 
	
}
