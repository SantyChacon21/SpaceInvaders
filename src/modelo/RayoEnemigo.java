package modelo;

public class RayoEnemigo extends ObjetoJuegoActualizable { 
	private int velocidad = 5; 
	private int altoEspacio;
	
	public RayoEnemigo (int x, int y, Observador obs, int altoEspacio) {
		super(x, y, 5, obs, 0);
		this.velocidad = 5; 
		this.altoEspacio = altoEspacio;
	}
	
	public int getAltoEspacio() {
		return altoEspacio;
	}
	
	public void actualizarPosicion() {
		y += velocidad; 
		if (observador != null) {
			observador.mover(x,y);
		}
	}
	
	public boolean estaFueraDePantalla (int limite ) {
		return y > limite;
	}
	
	public void mover (int x, int y) {
		this.x = x; 
		this.y = y;
		if (observador != null) {
			observador.mover(x, y);
		}
	}
	
	public boolean colisionaCon(NaveJugador jugador) {
		int rX = this.x;
		int rY = this.y;
		int rW = this.getAnchoRayo();
		int rH = this.getAltoRayo();
		
		int nX = jugador.getX();
		int nY = jugador.getY();
		int nW = jugador.getAncho();
		int nH = jugador.getAlto();
		
		return (rX < nX + nW && rX + rW > nX && rY < nY + nH && rY + rH > nY);
	}
	
	private int getAnchoRayo() {
		return (observador != null) ? observador.getAncho() : 4;
	}
	
	private int getAltoRayo() {
		return (observador != null) ? observador.getAlto() : 15;
	}
	
	public boolean esDeJugador() {
		return false; //porque lo dispara una nave invasora
	}
}
