package modelo;

public class Jugador {
	private String nombre;
    private int puntaje;

    public Jugador(String nombre, int puntaje) {
        this.nombre = nombre;
        this.puntaje = puntaje;
    }

    public String getNombre() { 
    	return nombre; 
    }
    public int getPuntaje() { 
    	return puntaje;
    }

    public void setPuntaje(int nuevoPuntaje) {
        this.puntaje = nuevoPuntaje;
    }
}
