package modelo;

public abstract class ObjetoJuego {
	protected int x;
	protected int y;
	protected int velocidad;
	protected Observador observador;
	protected int anchoEspacio;
	
	public ObjetoJuego(int x, int y, int velocidad, Observador observador, int anchoEspacio) {
		this.x=x;
		this.y=y;
		this.velocidad = velocidad;
		this.observador = observador;
		this.anchoEspacio=anchoEspacio;
		
		if (this.observador != null) {
			this.observador.mover(x, y);
		}
	}
	
	public abstract void mover(int x, int y);
	
	public void moverDerecha() {
		mover(x+velocidad, y);
	}

	public void moverIzquierda(){
		mover(x-velocidad, y);
	}
	
	public void moverArriba() {
		mover(x, y-velocidad);
	}
	
	public void mover(int x) {
		mover(x,  y);
	}
	
	public int getPosicionMediaX() {
		return x + observador.getAncho()/2;
	}
	
	public int getY() {
		return y;
	}
	
	public int getX() {
		return x;
	}
	
	public int getAncho() {
		return observador.getAncho();
	}
	
	public int getAlto() {
		return observador.getAlto();
	}
	
	public int getAnchoEspacio() {
		return anchoEspacio + observador.getAncho();
	}
	
	public Observador getObservador() {
		return observador;
	}
}
