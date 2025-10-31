package modelo;

import java.util.ArrayList;

public class Espacio {
	private int ancho;
	private int alto;
	private NaveJugador naveJugador;
	
	private ArrayList<ObjetoJuego> listaObjetoJuego = new ArrayList<>();
	private ArrayList<Muro> listaMuros = new ArrayList<>();
	
	private Oleada oleada;
	
	
	public Espacio(int ancho, int alto, int posicionNaveJugadorX, int posicionNaveJugadorY, Observador observadorNave, ArrayList<Observador> obsNavesInvasoras) {
		this.ancho=ancho;
		this.alto=alto;
		
		naveJugador=new NaveJugador(posicionNaveJugadorX, posicionNaveJugadorY, observadorNave,ancho);
		
		oleada = new Oleada(ancho, obsNavesInvasoras);
	}
	
	public NaveJugador getNaveJugador() {
		return naveJugador;
	}
	
	public void agregar(ObjetoJuego objetoJuego) {
		listaObjetoJuego.add(objetoJuego);
		if (objetoJuego.getObservador() instanceof vista.ImagenObjetoJuego) {
			
		}
	}
	
	public void actualizarPosiciones() {
		ArrayList<ObjetoJuego> copiaLista = new ArrayList<>(listaObjetoJuego);
		
		for (ObjetoJuego objeto: copiaLista) {
			if (objeto instanceof ObjetoJuegoActualizable) {
				ObjetoJuegoActualizable actualizable = (ObjetoJuegoActualizable) objeto;
				actualizable.actualizarPosicion();
				
				if (objeto.getY() < 0) {
					eliminarObjeto(objeto);
					
					if (objeto.getObservador() != null) {
						objeto.getObservador().eliminar();
					}
				}
			}
		}
	}
	
	public Oleada getOleada() {
		return oleada;
	}
	
	public ArrayList<Muro> getListaMuros(){
		return listaMuros;
	}
	
	public void agregarMuro (Muro muro) {
		listaMuros.add(muro);
	}
	
	public void eliminarMuro (Muro muro) {
		listaMuros.remove(muro);
	}
	
	public ArrayList<ObjetoJuego> getListaObjetoJuego() {
		return listaObjetoJuego;
	}
	
	public void eliminarObjeto(ObjetoJuego obj) {
		listaObjetoJuego.remove(obj);
		
		if (obj.getObservador() != null) {
			obj.getObservador().eliminar();
		}
	}
}

