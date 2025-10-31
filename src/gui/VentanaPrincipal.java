package gui;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import modelo.GestionarCreditos;
import modelo.GestionarRanking;

public class VentanaPrincipal extends JFrame {
	private JButton btnCreditos; 
	private JButton btnRanking; 
	private JButton btnJugar;
	
	private GestionarRanking gestorRanking; 
	private GestionarCreditos gestorCreditos;
	
	public VentanaPrincipal () {
		setTitle("Menu del juego");
		setSize(820,640);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		setLayout(new FlowLayout());
		setLocationRelativeTo(null);
		
		gestorRanking = new GestionarRanking();
		gestorCreditos = new GestionarCreditos();
		
		btnJugar = new JButton ("Jugar Space Invaders");
		
		btnJugar.addActionListener(e -> {
			VentanaDificultad vd = new VentanaDificultad();
			vd.setVisible(true);
		});
		
		btnCreditos = new JButton("Creditos");
		
		btnCreditos.addActionListener(e -> {
			VentanaCreditos vc = new gui.VentanaCreditos (this, gestorCreditos);
			vc.setVisible(true);
		});
		
		btnRanking = new JButton("Ver Ranking");
		
		btnRanking.addActionListener(e -> {
			VentanaRanking vr = new gui.VentanaRanking (this, gestorRanking);
			vr.setVisible(true);
		});
		
		/* 
		JFrame frame = new JFrame("Naves invasoras");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(new PanelPrincipal(null));
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		*/
		add(btnJugar);
		add(btnCreditos);
		add(btnRanking);
	}

	
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new VentanaPrincipal().setVisible(true);
        });
    }
}
