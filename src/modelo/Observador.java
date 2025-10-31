package modelo;

public interface Observador {
	public void mover(int x, int y);
	public int getAncho();
	public int getAlto();
	void crearProyectil(int x, int y);
	void eliminar();
	public Object getParent();
}
