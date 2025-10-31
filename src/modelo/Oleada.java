package modelo;

import java.util.ArrayList;
import java.util.Random;

import vista.ImagenRayoEnemigo;
import vista.PanelPrincipal;

public class Oleada implements Actualizable {
	private ArrayList<NaveInvasora> naves = new ArrayList<>();
    private int anchoEspacio;
    private Random random = new Random();
    private ArrayList<RayoEnemigo> rayos = new ArrayList<>();

    private int filas = 3;
    private int columnas = 5;
    
    private boolean moviendoDerecha = true;
    
    // OKSANA: Esta velocidad ahora será controlada por el JuegoController
    private int velocidad = 2; // Valor inicial, será modificado por OKSANA
    private int saltoY = 20;
    
    public Oleada(int anchoEspacio, ArrayList<Observador> observadores) {
        this.anchoEspacio = anchoEspacio;
        crearOleada(observadores);
    }
    
    private void crearOleada(ArrayList<Observador> observadores) {
    	int espacioX = 60;
        int espacioY = 50;
        int inicioX = 100;
        int inicioY = 50;

        int index = 0;
        for (int fila = 0; fila < filas; fila++) {
            for (int col = 0; col < columnas; col++) {
                if (index >= observadores.size()) break;
                
                int x = inicioX + col * espacioX;
                int y = inicioY + fila * espacioY;

                naves.add(new NaveInvasora(x, y, observadores.get(index), anchoEspacio));
                index++;
            }
        }
    }

    public void actualizarPosiciones() {
        boolean cambiarDireccion = false;
        
        for (NaveInvasora nave : new ArrayList<>(naves)) {
            if (moviendoDerecha) {
                nave.mover(nave.getX() + velocidad, nave.getY());
                if (nave.getX() + nave.getAncho() > anchoEspacio) {
                    cambiarDireccion = true;
                }
            } else {
                nave.mover(nave.getX() - velocidad, nave.getY());
                if (nave.getX() < 0) {
                    cambiarDireccion = true;
                }
            }
        }

        if (cambiarDireccion) {
            moviendoDerecha = !moviendoDerecha;
            for (NaveInvasora nave : naves) {
                nave.mover(nave.getX(), nave.getY() + saltoY);
            }
        }
    }

    public void eliminarNave(NaveInvasora nave) {
        if (naves.contains(nave)) {
            naves.remove(nave); 
            Observador obs = nave.getObservador();
            if (obs != null) {
                obs.eliminar(); 
            }
        }
    }

    public void dispararAleatorio(PanelPrincipal panel) {
        if (naves.isEmpty()) return;

        if (random.nextInt(50) == 0) {
            NaveInvasora nave = naves.get(random.nextInt(naves.size()));

            ImagenRayoEnemigo obs = new ImagenRayoEnemigo();
            int xInicial = nave.getX() + nave.getAncho() / 2 - obs.getAncho() / 2;
            int yInicial = nave.getY() + nave.getAlto(); 
            
            panel.add(obs);

            RayoEnemigo rayo = new RayoEnemigo(xInicial, yInicial, obs, panel.getHeight());
            rayos.add(rayo);
        }
    }

    public void actualizarRayos() {
        ArrayList<RayoEnemigo> copiaRayos = new ArrayList<>(rayos);
        for (RayoEnemigo rayo : copiaRayos) {
            rayo.actualizarPosicion();
            if (rayo.estaFueraDePantalla(rayo.getAltoEspacio())) {
                eliminarRayo(rayo);
            }
        }
    }
    
    public void setVelocidadMovimiento(int velocidad) {
        this.velocidad = velocidad;
    }

    public ArrayList<RayoEnemigo> getRayos() {
        return rayos;
    }
	
	public ArrayList<NaveInvasora> getNaves() {
        return naves;
    }
	
	public boolean estaVacia() {
        return naves.isEmpty();
    }
	
	public void eliminarRayo(RayoEnemigo rayo) {
        if (rayos.contains(rayo)) {
            rayos.remove(rayo);
            if (rayo.getObservador() != null) {
                rayo.getObservador().eliminar();
            }
        }
    }
}