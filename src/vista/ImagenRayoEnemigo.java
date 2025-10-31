package vista;

import java.awt.Container;
import java.awt.Color;
import javax.swing.JLabel;
import modelo.Observador;

public class ImagenRayoEnemigo extends JLabel implements Observador {
    public ImagenRayoEnemigo() {
        setOpaque(true);
        setBackground(Color.RED);
        setSize(5, 15); // ancho y alto del proyectil
    }

    @Override
    public void mover(int x, int y) {
        setLocation(x, y);
    }

    @Override
    public void crearProyectil(int x, int y) {
        // no se usa acá
    }

    @Override
    public void eliminar() {
        Container parent = getParent();
        if (parent != null) {
            parent.remove(this);
            parent.repaint();
        }
    }
    
    public int getAncho() {
        return 5; // ancho del proyectil
    }
    
    public int getAlto() {
    	return 15;
    }
}