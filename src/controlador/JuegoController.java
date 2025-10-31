package controlador;

import java.util.ArrayList;

import modelo.Espacio;
import modelo.Muro;
import modelo.NaveInvasora;
import modelo.NaveJugador;
import modelo.ObjetoJuego;
import modelo.ObjetoJuegoActualizable;
import modelo.Observador;
import modelo.Rayo;
import modelo.RayoEnemigo;
import vista.PanelPrincipal;

public class JuegoController {
	private Espacio espacio;
	private int puntuacion;
	private int vidas;
	private int nivel; 
	private String dificultad;
	private int velocidadBaseNaves; 
	private int vidasExtraConcedidas;
	
	private boolean juegoTerminado;
	
	public JuegoController(int anchoEspacio, int altoEspacio, int posicionNaveJugadorX, int posicionNaveJugadorY, Observador observadorNaveJugador, ArrayList<Observador> obsNavesInvasoras) {
		espacio = new Espacio(anchoEspacio, altoEspacio, posicionNaveJugadorX, posicionNaveJugadorY, observadorNaveJugador, obsNavesInvasoras);
		
		this.puntuacion = 0;
		this.vidas = 3; 
		this.nivel = 1;
		this.vidasExtraConcedidas = 0;
		
		configurarDificultad("Cadete"); //por defecto se establece cadete
	}
	
	public void moverNaveJugadorDerecha() {
		NaveJugador nave = espacio.getNaveJugador();
		if (nave != null) {
			nave.moverDerecha();
		}
	}
	
	public void moverNaveJugador(int x) {
		NaveJugador naveJugador = espacio.getNaveJugador();
		if (naveJugador != null) {
			naveJugador.mover(x);
		}
	}
	
	public void disparar(Observador observador) {
		NaveJugador naveJugador = espacio.getNaveJugador();
		if (naveJugador != null) {
			Rayo disparo = naveJugador.disparar(observador);
			
			if (disparo != null) {
				espacio.agregar(disparo);
			}
		}
	}
	
	public void crearMuros (Observador[] observadoresMuro) {
		int [] posicionesX = {350, 650, 850};
		int naveY = espacio.getNaveJugador().getY();
		
		int posicionY = naveY - 80;

		for (int x : posicionesX) {
		    vista.ImagenMuro imgMuro = new vista.ImagenMuro(40, 20);
		    imgMuro.setBounds(x, posicionY, 100, 50);

		    modelo.Muro muro = new modelo.Muro(x, posicionY, imgMuro, 
		            espacio.getNaveJugador().getAnchoEspacio());
		    espacio.agregarMuro(muro);

		    System.out.println("✅ Muro creado en x=" + x + ", y=" + posicionY);
		}
	}
	
	public void configurarDificultad (String dificultad) {
		if (dificultad == null) dificultad = "Cadete";
		this.dificultad = dificultad; 
		
		switch (dificultad) {
		case "Cadete":
			this.velocidadBaseNaves = 3;
			break;
		case "Guerrero":
			this.velocidadBaseNaves = 5;
			break;
		case "Master":
			this.velocidadBaseNaves = 7; 
			break;
		default: 
			this.velocidadBaseNaves = 3;
		}
		
		if (espacio.getOleada() != null) {
			int velocidadActual = this.velocidadBaseNaves + (this.nivel -1);
			espacio.getOleada().setVelocidadMovimiento(velocidadActual);
		}
	}
	
	public void siguienteNivel () {
		this.nivel ++;
		
		System.out.println("Nivel " + this.nivel + " alcanzado!");
		
		agregarPuntuacion(200);
		
		//restaurarMurosCompletamente();
		
		int nuevaVelocidad = this.velocidadBaseNaves + (this.nivel - 1);
		
		if (espacio.getOleada() != null) {
			espacio.getOleada().setVelocidadMovimiento(nuevaVelocidad);; 
		}
		
		
		crearNuevaOleada();
		
		recrearOleadaYMuros(nuevaVelocidad);
	}
	
	private void recrearOleadaYMuros(int nuevaVelocidad) {
		ArrayList<modelo.Observador> obsNavesInvasoras = new ArrayList<>();

	    int filas = 4;
	    int columnas = 5;
	    int inicioX = 100;
	    int inicioY = 50;
	    int espacioX = 60;
	    int espacioY = 50;

	    for (int fila = 0; fila < filas; fila++) {
	        for (int col = 0; col < columnas; col++) {
	            vista.ImagenNaveInvasora imgNave = new vista.ImagenNaveInvasora();
	            imgNave.setBounds(inicioX + col * espacioX, inicioY + fila * espacioY, 40, 30);
	            obsNavesInvasoras.add(imgNave);
	        }
	    }

	    modelo.Oleada nuevaOleada = new modelo.Oleada(espacio.getNaveJugador().getAnchoEspacio(), obsNavesInvasoras);
	    nuevaOleada.setVelocidadMovimiento(nuevaVelocidad);
	    espacio.getOleada().getNaves().clear();
	    espacio.getOleada().getNaves().addAll(nuevaOleada.getNaves());
	    
	    recrearMuros();
	}

	private void recrearMuros() {
		espacio.getListaMuros().clear();

	    for (Muro muro : new ArrayList<>(espacio.getListaMuros())) {
	        if (muro.getObservador() instanceof javax.swing.JComponent) {
	            javax.swing.JComponent comp = (javax.swing.JComponent) muro.getObservador();
	            if (comp.getParent() != null) {
	                comp.getParent().remove(comp);
	            }
	        }
	        espacio.eliminarMuro(muro);
	    }

	    int[] posicionesX = {350, 650, 850};
	    int naveY = espacio.getNaveJugador().getY();
	    int posicionY = naveY - 80;

	    for (int x : posicionesX) {
	        vista.ImagenMuro imgMuro = new vista.ImagenMuro(20, 20);
	        imgMuro.setBounds(x, posicionY, 100, 50);

	        modelo.Muro muro = new modelo.Muro(x, posicionY, imgMuro, 
	                espacio.getNaveJugador().getAnchoEspacio());
	        espacio.agregarMuro(muro);

	        System.out.println("✅ Muro creado en x=" + x + ", y=" + posicionY);
	    }
	}

	private void crearNuevaOleada() {
		ArrayList<modelo.Observador> obsNuevas = new ArrayList<>();
		
		int filas = 3;
		int columnas = 5; 
		int inicioX = 100; 
		int inicioY = 50; 
		int espacioX = 60;
		int espacioY = 50;
		
		for (int fila = 0; fila < filas; fila++) {
			for (int col = 0; col < columnas; col++) {
				vista.ImagenNaveInvasora img = new vista.ImagenNaveInvasora();
				img.setBounds(inicioX + col * espacioX, inicioY + fila * espacioY, 40, 30);
				obsNuevas.add(img);
			}
		}
		modelo.Oleada nuevaOleada = new modelo.Oleada(espacio.getNaveJugador().getAnchoEspacio(), obsNuevas);
		espacio.getOleada().getNaves().clear();
		espacio.getOleada().getNaves().addAll(nuevaOleada.getNaves());
	}
	/*
	private void restaurarMurosCompletamente () {
		
		
		for  (Muro muro: espacio.getListaMuros()) {
			boolean esDeJugador = disparo.esDeJugador();
			muro.recibirImpacto(esDeJugador, disparo.getX());
			
			if (muro.getObservador() instanceof javax.swing.JComponent) {
				((javax.swing.JComponent) muro.getObservador()).setVisible(true);
			}
		}
	}*/
	
	public void agregarPuntuacion (int puntos) {
		this.puntuacion += puntos;
		
		int vidasGanadasPorPuntos = this.puntuacion / 500;
		
		if (vidasGanadasPorPuntos > this.vidasExtraConcedidas) {
			this.vidas ++;
			this.vidasExtraConcedidas =  vidasGanadasPorPuntos;
			
			System.out.println("Vida extra obtenida! Vidas restantes: " + this.vidas);
		}
	}
	
	public int getPuntuacion() {
		return puntuacion;
	}
	public int getVidas() {
		return vidas;
	}
	public int getNivel() {
		return nivel;
	}
	
	public void actualizarPosiciones (PanelPrincipal panel) {
		if (espacio.getOleada() != null) {
			espacio.getOleada().actualizarPosiciones();
		}
		espacio.actualizarPosiciones();
		
		if (espacio.getOleada() != null) {
			espacio.getOleada().dispararAleatorio(panel);
			espacio.getOleada().actualizarRayos();
		}
		verificarColisiones();
		
		if (espacio.getOleada() != null && espacio.getOleada().estaVacia()) {
			siguienteNivel();
		}
	}
	
	private void verificarColisiones () {
		verificarColisionesMuros();
		verificarColisionesInvasores();
		verificarColisionesNaves();
	}
	
	private void verificarColisionesMuros() {
	    ArrayList<Muro> muros = new ArrayList<>(espacio.getListaMuros());
	    ArrayList<ObjetoJuegoActualizable> todosLosDisparos = new ArrayList<>();

	    // Agregamos los disparos del jugador
	    for (ObjetoJuego obj : espacio.getListaObjetoJuego()) {
	        if (obj instanceof ObjetoJuegoActualizable) {
	            todosLosDisparos.add((ObjetoJuegoActualizable) obj);
	        }
	    }

	    // Agregamos los disparos enemigos
	    if (espacio.getOleada() != null) {
	        todosLosDisparos.addAll(espacio.getOleada().getRayos());
	    }

	    for (ObjetoJuegoActualizable disparo : new ArrayList<>(todosLosDisparos)) {
	        boolean colisionado = false;

	        for (Muro muro : new ArrayList<>(muros)) {

	            int anchoDisparo = disparo.getAncho();
	            int altoDisparo = disparo.getAlto();
	            int anchoMuro = muro.getAncho();
	            int altoMuro = muro.getAlto();

	            // Hitbox
	            boolean hayColision = disparo.getX() < muro.getX() + anchoMuro &&
	                                  disparo.getX() + anchoDisparo > muro.getX() &&
	                                  disparo.getY() < muro.getY() + altoMuro &&
	                                  disparo.getY() + altoDisparo > muro.getY();

	            if (hayColision) {
	                boolean esDeJugador = disparo.esDeJugador();

	                // Recibimos impacto y determinamos si pasó por tronera
	                if (!muro.estaEnTronera(disparo.getX())) {
	                    // Impacto real
	                    muro.recibirImpacto(esDeJugador, disparo.getX());
	                    if (disparo.esDeJugador()) {
	                        espacio.eliminarObjeto(disparo);
	                    } else if (disparo instanceof RayoEnemigo) {
	                        espacio.getOleada().eliminarRayo((RayoEnemigo) disparo);
	                    }

	                    colisionado = true;
	                } else {
	                    // Pasó por tronera, rayo sigue
	                    System.out.println("Disparo pasó por tronera, sigue avanzando.");
	                }

	                // Eliminamos muro si su integridad llegó a 0
	                if (muro.getIntegridad() <= 0) {
	                    if (muro.getObservador() instanceof javax.swing.JComponent) {
	                        ((javax.swing.JComponent) muro.getObservador()).setVisible(false);
	                    }
	                    espacio.eliminarMuro(muro);
	                }
	            }

	            if (colisionado) break; // Rayo no debe seguir revisando otros muros
	        }
	    }
	}
	
	private void verificarColisionesNaves() {
		if (vidas <= 0) return; // ya terminó el juego

	    NaveJugador jugador = espacio.getNaveJugador();

	    for (NaveInvasora nave : espacio.getOleada().getNaves()) {
	        
	        if (nave.getY() + nave.getAlto() >= jugador.getY() &&
	            nave.getX() < jugador.getX() + jugador.getAncho() &&
	            nave.getX() + nave.getAncho() > jugador.getX()) {
	            vidas = 0;
	            gameOver();
	            return; 
	        }

	        for (Muro muro : espacio.getListaMuros()) {
	            if (nave.getY() + nave.getAlto() >= muro.getY() &&
	                nave.getX() < muro.getX() + muro.getObservador().getAncho() &&
	                nave.getX() + nave.getAncho() > muro.getX()) {
	                vidas = 0;
	                System.out.println("💥 Nave enemiga chocó contra un muro. GAME OVER.");
	                return;
	            }
	        }
	    }
	}
	
	private void verificarColisionesInvasores() {
		ArrayList<ObjetoJuego> disparosJugador = new ArrayList<>(espacio.getListaObjetoJuego());
		
		if (espacio.getOleada() == null) {
			return;
		}
		
		ArrayList<NaveInvasora> invasores = new ArrayList<>(espacio.getOleada().getNaves());
		
		for (ObjetoJuego disparoObj : new ArrayList<>(disparosJugador)) {
			if (!(disparoObj instanceof Rayo)) continue;
			
			Rayo disparo = (Rayo) disparoObj;
			boolean rayoConsumido = false;
			
			for (NaveInvasora invasor: new ArrayList<>(invasores)) {
				if (disparo.colisionaCon(invasor)) {
					espacio.eliminarObjeto(disparo);
					
					if (disparo.getObservador() != null) {
						disparo.getObservador().eliminar();
					}
					
					espacio.getOleada().eliminarNave(invasor);
					
					if (invasor.getObservador() != null) {
						invasor.getObservador().eliminar();
					}

					agregarPuntuacion(10);
					rayoConsumido = true;
					break;
				}
				
				if (rayoConsumido) {
					break;
				}
			}
		}
		
		NaveJugador jugador = espacio.getNaveJugador();
		ArrayList<RayoEnemigo> rayosEnemigos = new ArrayList<>(espacio.getOleada().getRayos());
	    for (RayoEnemigo rayo : new ArrayList<>(rayosEnemigos)) {
	        if (rayo.colisionaCon(jugador)) {
	            vidas--;
	            System.out.println("Jugador alcanzado. Vidas restantes: " + vidas);
	            espacio.getOleada().eliminarRayo(rayo);
	            if (vidas <= 0) {
	                gameOver();
	            }
	        }
	    }
	}
	
	private void gameOver () {
		if (juegoTerminado) {
			return;
		}
		juegoTerminado = true;
		System.out.println("GAME OVER");
		javax.swing.JOptionPane.showMessageDialog(null, "Game Over! Tu puntuacion: " + puntuacion);
		System.exit(0);
	}
	
	public Espacio getEspacio() {
		return espacio;
	}

	public String getDificultad() {
		return dificultad;
	}
}

