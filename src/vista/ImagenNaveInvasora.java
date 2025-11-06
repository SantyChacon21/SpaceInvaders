package vista;

import java.awt.Container;
import java.awt.Image;
import javax.swing.ImageIcon;

public class ImagenNaveInvasora extends ImagenObjetoJuego{
	
	public ImagenNaveInvasora() {
		super(50,50);
		Image imagen = new ImageIcon("nave.png").getImage();
		Image imagenAEscala = imagen.getScaledInstance(getAncho(), getAlto(), Image.SCALE_SMOOTH);
		ImageIcon icono = new ImageIcon(imagenAEscala);
		setIcon(icono);
	}
	
	public void mover(int x, int y) {
        setLocation(x, y);
    }

    public void crearProyectil(int x, int y) {
        // implementar si dispara algo
    }

    public void eliminar() {
        Container parent = getParent(); // obtenemos el contenedor
        if (parent != null) {
            parent.remove(this); // removemos del contenedor
            parent.repaint();    // refrescamos la pantalla
        }
        // si parent es null, no hacemos nada
    }
	
}

