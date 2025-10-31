package modelo;

public class Muro extends ObjetoJuego {
	
	private double integridad;
	private boolean [][] troneras;
	
	
	public Muro(int x, int y, Observador observador, int anchoEspacio) {
		super(x, y, 0, observador, anchoEspacio);
		this.integridad = 100.0;
		this.troneras = generarTroneras(4, 2);
	}
	
	public void mover(int x, int y) {
		
	}
	
	public void recibirImpacto(boolean esDeJugador, int impactoX) {
		if (estaEnTronera(impactoX)) {
			System.out.println("Disparo pasó por una tronera, sin daño.");
			
		}
		
		double danio = esDeJugador ? 10.0 : 5.0;
		integridad -= danio;
		
		if (integridad < 0) integridad = 0;
		
		System.out.println("Muro dañado: " + integridad + "% restante");
		
		if (observador instanceof vista.ImagenMuro) {
            ((vista.ImagenMuro) observador).actualizarColor(integridad);
        }
		
		if (integridad <= 0) {
            destruir();
        }
		
	}
	
	private void destruir () {
		if (observador instanceof javax.swing.JComponent) {
            javax.swing.JComponent comp = (javax.swing.JComponent) observador;
        }
        System.out.println("💥 Se destruyó una porción del muro de energía!");
    }

    private boolean[][] generarTroneras(int columnas, int filas) {
        boolean[][] t = new boolean[filas][columnas];

        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                t[i][j] = (j == 1 && i == 0) || (j == 2 && i == 0);
            }
        }
        return t;
	}

    public boolean estaEnTronera(int impactoX) {
        int anchoMuro = getAncho(); // ancho real del muro en pixeles
        int numColumnas = troneras[0].length;

        int col = (impactoX - x) * numColumnas / anchoMuro;

        if (col < 0 || col >= numColumnas);
        return false;
    }
        

    public double getIntegridad() {
        return integridad;
    }
}