package modelo;

public class Rayo extends ObjetoJuegoActualizable {
	public Rayo (int x, int y, Observador observador, int anchoEspacio) {
		super(x,y,10,observador,anchoEspacio);
	}
	
	public void actualizarPosicion() {
		moverArriba();
	}
	
	public void mover(int x, int y) {
		if (x > 0 && x < (anchoEspacio - observador.getAncho())) {
			this.x = x;
		}
		
		this.y = y;
		observador.mover(this.x, this.y);
	}
	
	public boolean esDeJugador() {
		return true; //porque lo dispara el jugador
	}
	
	public boolean colisionaCon (NaveInvasora nave) {
		int rayoX = this.x;
        int rayoY = this.y;
        int rayoAncho = 4;
        int rayoAlto = 20;

        int naveX = nave.getX();
        int naveY = nave.getY();
        int naveAncho = nave.getAncho();
        int naveAlto = nave.getAlto();

        boolean colisionX = rayoX + rayoAncho >= naveX && rayoX <= naveX + naveAncho;
        boolean colisionY = rayoY + rayoAlto >= naveY && rayoY <= naveY + naveAlto;

        return colisionX && colisionY;
	}
}
