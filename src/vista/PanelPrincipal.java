package vista;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import controlador.JuegoController;
import modelo.Observador;

public class PanelPrincipal extends JPanel {
	private int ancho;
	private int alto;
	private ImagenNave imagenNave;
	private JuegoController juegoController;
	private JLabel lblPuntaje;
	private JLabel lblVidas;
	private JLabel lblNivel;
	private JLabel lblDificultad;
	private boolean puedeDisparar= false; //seguro del arma
	
	public PanelPrincipal(String dificultad) {
		ancho = 1270;
		alto = 720;
		setLayout(null); //le quitamos el layout ya q queremos posicionar cada elemento en una posicion especifica
		setBackground(Color.BLACK);
		setPreferredSize(new Dimension(ancho,alto));
		
		imagenNave = new ImagenNave();
		add(imagenNave);
		imagenNave.mover(600,600);
		
		ArrayList<Observador> obsNavesInvasoras = new ArrayList<>();

		int filas = 3;
		int columnas = 5;
		int inicioX = 100;
		int inicioY = 50;
		int espacioX = 60;
		int espacioY = 50;

		for (int fila = 0; fila < filas; fila++) {
		    for (int col = 0; col < columnas; col++) {
		        int x = inicioX + col * espacioX;
		        int y = inicioY + fila * espacioY;

		        ImagenNaveInvasora imagenNaveInvasora = new ImagenNaveInvasora();
		        imagenNaveInvasora.setBounds(x, y, 40, 30); // ancho y alto de la imagen
		        add(imagenNaveInvasora);
		        obsNavesInvasoras.add(imagenNaveInvasora);
		    }
		}
		
		
		int posicionNaveJugadorX= 100;
		int posicionNaveJugadorY = 600;
		juegoController = new JuegoController(ancho,alto,posicionNaveJugadorX, posicionNaveJugadorY, imagenNave, obsNavesInvasoras);
		juegoController.configurarDificultad(dificultad);
		
		Observador[] observadoresMuro = new Observador[3];
		for (int i = 0; i < observadoresMuro.length; i++) {
		    observadoresMuro[i] = new ImagenMuro(40, 20);
		    add((JComponent) observadoresMuro[i]);
		}
		juegoController.crearMuros(observadoresMuro);
		
		lblPuntaje = new JLabel("Puntaje: 0");
		lblPuntaje.setForeground(Color.WHITE);
		lblPuntaje.setFont(new Font("Arial", Font.BOLD, 14));
		lblPuntaje.setBounds(20, 10, 150, 20);
		add(lblPuntaje);

		lblVidas = new JLabel("Vidas: 3");
		lblVidas.setForeground(Color.WHITE);
		lblVidas.setFont(new Font("Arial", Font.BOLD, 14));
		lblVidas.setBounds(200, 10, 100, 20);
		add(lblVidas);

		lblNivel = new JLabel("Nivel: 1");
		lblNivel.setForeground(Color.WHITE);
		lblNivel.setFont(new Font("Arial", Font.BOLD, 14));
		lblNivel.setBounds(320, 10, 100, 20);
		add(lblNivel);
		
		lblDificultad = new JLabel("Dificultad: " + juegoController.getDificultad());
		lblDificultad.setForeground(Color.WHITE);
		lblDificultad.setFont(new Font("Arial", Font.BOLD, 14));
		lblDificultad.setBounds(420, 10, 150, 20);
		add(lblDificultad);

		
		setFocusable(true);
		
		addMouseMotionListener (new MouseMotionAdapter() {
			public void mouseMoved(MouseEvent e) {
				juegoController.moverNaveJugador(e.getX());
			}
		});
		
		
		interceptarMouse();
		
		interceptarTecla();
		
		simularMovimientos();
		
		revalidate();
		repaint();
	}
	
	private void interceptarMouse() {
		addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseMoved(MouseEvent e) {
				juegoController.moverNaveJugador(e.getX());
			}
		});
	}
	
	private void interceptarTecla() {
		setFocusable(true);
		addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent evento) {
				int tecla=evento.getKeyCode();
				if (tecla==KeyEvent.VK_RIGHT) {
					juegoController.moverNaveJugadorDerecha();
				}else if(tecla==KeyEvent.VK_LEFT){
					juegoController.moverNaveIzquierda();
				}
				 else if (tecla == KeyEvent.VK_SPACE) {
					if(puedeDisparar){
					ImagenRayo imagenRayo = new ImagenRayo();
					add(imagenRayo);
					juegoController.disparar(imagenRayo);

					puedeDisparar= false;
					}
				}
			}

			public void keyReleased(KeyEvent evento){
				int tecla= evento.getKeyCode();

				if(tecla == KeyEvent.VK_SPACE){

					puedeDisparar= true;
				}
			}
		});
	}
	/*
	private void simularMovimientos() {
	    Timer gameLoop = new Timer(20, new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	            // 1️⃣ Actualizar posiciones de todas las naves y rayos
	            juegoController.actualizarPosiciones(PanelPrincipal.this);

	            // 2️⃣ Las naves invasoras disparan aleatoriamente
	            juegoController.getEspacio().getOleada().dispararAleatorio(PanelPrincipal.this);

	            // 3️⃣ Actualizar posiciones de los rayos enemigos
	            juegoController.getEspacio().getOleada().actualizarRayos();

	            // 4️⃣ Detectar colisiones de los rayos del jugador con las naves invasoras
	            ArrayList<Rayo> rayosJugador = juegoController.getEspacio().getRayos();
	            for (Rayo rayo : new ArrayList<>(rayosJugador)) {
	                for (NaveInvasora nave : new ArrayList<>(juegoController.getEspacio().getOleada().getNaves())) {
	                    if (rayo.colisionaCon(nave)) {
	                        juegoController.getEspacio().getOleada().eliminarNave(nave);
	                        juegoController.getEspacio().eliminar(rayo);
	                        break; // un rayo destruye una nave
	                    }
	                }
	            }

	            // 5️⃣ Detectar colisiones de los rayos enemigos con el jugador
	            ArrayList<RayoEnemigo> rayosEnemigos = juegoController.getEspacio().getOleada().getRayosEnemigos();
	            NaveJugador jugador = juegoController.getEspacio().getNaveJugador();
	            for (RayoEnemigo rayoE : new ArrayList<>(rayosEnemigos)) {
	            	if (rayoE.colisionaCon(jugador)) {
	            	    System.out.println("¡Jugador alcanzado!");
	            	    jugador.recibirImpacto();
	            	    rayosEnemigos.remove(rayoE);
	            	    rayoE.getObservador().eliminar();
	            	}
	            }

	            // 6️⃣ Agregar los rayos enemigos al panel si aún no están
	            for (RayoEnemigo rayoEnemigo : rayosEnemigos) {
	                Observador obs = rayoEnemigo.getObservador();
	                if (obs instanceof ImagenRayoEnemigo) {
	                    ImagenRayoEnemigo img = (ImagenRayoEnemigo) obs;
	                    if (img.getParent() == null) {
	                        add(img); // agregar al panel
	                    }
	                }
	            }

	            // 7️⃣ Refrescar la pantalla
	            repaint();
	        }
	    });*/
	
	private void simularMovimientos() {
		Timer gameLoop = new Timer(20, new ActionListener() {
			public void actionPerformed (ActionEvent e) {
				juegoController.actualizarPosiciones(PanelPrincipal.this);
				actualizarHUD();
				
				for (modelo.NaveInvasora n : juegoController.getEspacio().getOleada().getNaves()) {
		            if (n.getObservador() instanceof JComponent && n.getObservador().getParent() == null) {
		                add((JComponent) n.getObservador());
		            }
		        }

		        for (modelo.Muro m : juegoController.getEspacio().getListaMuros()) {
					if (m.getObservador() instanceof JComponent) {
		        		JComponent comp = (JComponent) m.getObservador();
		        		if (comp.getParent() == null) {
		        			add(comp);
		        		}
		        	}
		            if (m.getObservador() instanceof JComponent && m.getObservador().getParent() == null) {
		                add((JComponent) m.getObservador());
		            }
		        }

				
				repaint();
			}

			private void actualizarHUD() {
				lblPuntaje.setText("Puntaje: " + juegoController.getPuntuacion());
			    lblVidas.setText("Vidas: " + juegoController.getVidas());
			    lblNivel.setText("Nivel: " + juegoController.getNivel());
			    lblDificultad.setText("Dificultad: " + juegoController.getDificultad());

			}
		});
	    gameLoop.start();
	}
}
	