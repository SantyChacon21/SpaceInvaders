package modelo;

public class GestionarCreditos {
	private int creditos;

    public void cargarCreditos(int cantidad) {
        this.creditos += cantidad;
    }

    public boolean consumirCredito() {
        if (creditos > 0) {
            creditos--;
            return true;
        }
        return false;
    }

    public void reintegrarCreditos(int cantidad) {
        this.creditos += cantidad;
    }

    public int getCreditos() {
        return creditos;
    }
}
