package vista;

import javax.swing.JFrame;

public class Ventana extends JFrame{
	private PanelPrincipal panelPrincipal;
	
	public Ventana(String dificultad) {
		add(new PanelPrincipal(dificultad));
		setTitle("Space Invaders"); // Título añadido
		panelPrincipal = new PanelPrincipal(null);
		setContentPane(panelPrincipal);
		pack();
		setDefaultCloseOperation(DISPOSE_ON_CLOSE); // Cambiado para no cerrar la app
		setLocationRelativeTo(null); // Centrar
		setVisible(false);
		
		// Importante para que las teclas funcionen
		panelPrincipal.requestFocusInWindow(); 
	}
}