package modelo;

public class NaveInvasora extends ObjetoJuegoActualizable{
	private int direccion = 1;
	private int velocidad = 5;
	private int limiteDerecho;
	private int limiteIzquierdo;
	private boolean bajar = false;
	
	public NaveInvasora(int x, int y, Observador observador, int anchoEspacio) {
		super(x,y,5, observador, anchoEspacio);
		this.limiteDerecho = anchoEspacio;
		this.limiteIzquierdo = 0;
	}
	
	public Observador getObservador() {
		return observador;
	}
	
	public void actualizarPosicion() {
	    // La posición se actualiza desde la clase Oleada, no acá.
	}
	
	/*
	public void actualizarPosicion() {
		x += direccion * velocidad;
		
		if (x>limiteDerecho || x<limiteIzquierdo) {
			direccion *= -1;
			bajar=true;
		}
		
		if (bajar) {
			y+=20;
			bajar=false;
		}
		
		observador.mover(x, y);
	} */
	
	public void mover(int x, int y) {
	    this.x = x;
	    this.y = y;
	    if (observador != null) {
	        observador.mover(x, y); // actualiza JLabel
	    }
	}
	
	public int getAncho() {
	    return 40; // ancho del JLabel de la nave
	}
	
	public int getAlto() {
	    return 30; // alto del JLabel de la nave
	}
	
	public RayoEnemigo disparar() {
	    int xRayoEnemigo = x + getAncho() / 2;
	    int yRayoEnemigo = y + 30; // sale desde debajo de la nave

	    Observador obs = null; // se asignará desde el controlador o la vista
	    RayoEnemigo rayoEnemigo = new RayoEnemigo(xRayoEnemigo, yRayoEnemigo, obs, anchoEspacio);
	    return rayoEnemigo;
	}
	
	public boolean esDeJugador() {
		return false;
	}
}
