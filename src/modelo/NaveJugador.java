package modelo;

public class NaveJugador extends ObjetoJuego {
private int xMax;
	
	public NaveJugador(int x, int y, Observador observador, int anchoEspacio) {
		super(x,y,10,observador,anchoEspacio);
		this.xMax=anchoEspacio - observador.getAncho();
	}
	
	public void moverDerecha() {
		mover(x+velocidad);
	}
	
	public void mover(int x) {
		mover(x,y);
	}
	
	public void mover(int x, int y) {
		if (x < xMax && x >= 0) {
			this.x=x;
			this.y=y;
			if (observador != null) {
				observador.mover(x, y);
			}
		}
	}
	
	public Rayo disparar(Observador observadorDisparo) {
		int posicionX=getPosicionMediaX();
		Rayo disparo = new Rayo(posicionX,getY(),observadorDisparo,getAnchoEspacio());
		return disparo;
	}
	
	public void recibirImpacto() {
		
	}
}
