package vista;

import java.awt.Image;
import javax.swing.ImageIcon;

public class ImagenNave extends ImagenObjetoJuego{
	
	public ImagenNave() {
		super(50,50);
		Image imagen = new ImageIcon("nave.png").getImage();
		Image imagenAEscala = imagen.getScaledInstance(getAncho(), getAlto(), Image.SCALE_SMOOTH);
		ImageIcon icono = new ImageIcon(imagenAEscala);
		setIcon(icono);
	}
	
	 public void crearProyectil(int x, int y) {
	        
		 }
	 
	 public void eliminar() {
	        if (getParent() != null) {
	            getParent().remove(this);
	            getParent().repaint();
	        }
	    }
}
