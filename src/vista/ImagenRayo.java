package vista;

import java.awt.Color;
import java.awt.Container;

public class ImagenRayo extends ImagenObjetoJuego {
	/*private int ancho;
	private int alto;*/
	
	public ImagenRayo() {
		super(4,20);
		setOpaque(true);
		setBackground(Color.GREEN);
	}
	
	/*public void mover(int x, int y) {
		setBounds(x,y,ancho,alto);
	}*/
	
	public void crearProyectil(int x, int y) {
        // Aquí creás un nuevo Rayo o delegás a tu controlador
        // Por ejemplo, podés dejarlo vacío si el disparo se maneja desde JuegoController
    }
	
	public void eliminar() {
	    Container parent = getParent();  // obtenemos el contenedor
	    if (parent != null) {
	        parent.remove(this);         // removemos del JPanel
	        parent.repaint();            // refrescamos la pantalla
	    }
	    // si parent es null, no hacemos nada
	}
}
