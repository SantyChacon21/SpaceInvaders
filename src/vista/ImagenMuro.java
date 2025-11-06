package vista;

import java.awt.Image;

import javax.swing.*;

public class ImagenMuro extends ImagenObjetoJuego{
	
	private int ancho = 100;
	private int alto = 50; 
	
	public ImagenMuro(int ancho, int alto) {
		//setIcon(new ImageIcon(getClass().getResource("muros.png")));
		
		super(ancho, alto);
		ImageIcon iconoOriginal = new ImageIcon("muros.png");
		Image image = iconoOriginal.getImage();
		
		Image imagenAEscala= image.getScaledInstance(getAncho(), getAlto(), Image.SCALE_SMOOTH);
		ImageIcon iconoEscalado = new ImageIcon(imagenAEscala);
		setIcon(iconoEscalado);
		
		setSize(ancho, alto);
		setVisible(true);
	}
	
	public void mover(int x, int y) {
		setLocation(x, y);
	}
	
	public int getAncho() {
		return ancho;
	}
	
	public int getAlto() {
		return alto;
	}

	@Override
	public void crearProyectil(int x, int y) {
		// No implementado
	}

	@Override
	public void eliminar() {
		// Los muros se hacen invisibles, pero no se eliminan con este método
        setVisible(false);
        if (getParent() != null) {
        	getParent().remove(this);
        	getParent().repaint();
        }
	}
	public void actualizarColor(double integridad) {
		setOpaque(true);
	    if (integridad > 60) setBackground(new java.awt.Color(0, 200, 0)); // verde
	    else if (integridad > 30) setBackground(new java.awt.Color(255, 165, 0)); // naranja
	    else setBackground(new java.awt.Color(255, 0, 0)); // rojo
	}
}
