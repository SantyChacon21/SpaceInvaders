package gui;

import javax.swing.JFrame;

import vista.PanelPrincipal;

public class VentanaJuego extends JFrame{
	private PanelPrincipal panel;

    public VentanaJuego(String dificultad) {
        setTitle("Naves Invasoras - Dificultad: " + dificultad);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(1280, 720);
        setLocationRelativeTo(null);

        panel = new PanelPrincipal(dificultad);
        add(panel);
    }
}
