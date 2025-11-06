package gui;

import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import modelo.GestionarCreditos;
import modelo.GestionarRanking;

public class VentanaPrincipal extends JFrame {
	private JButton btnCreditos; 
	private JButton btnRanking; 
	private JButton btnJugar;
	
	private GestionarRanking gestorRanking; 
	private GestionarCreditos gestorCreditos;
	private JLabel lblMostrarCreditos;
	
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
			

			if(gestorCreditos.consumirCredito()){
				VentanaDificultad vd = new VentanaDificultad();
				vd.setVisible(true);
				this.dispose();
			}else{
				JOptionPane.showMessageDialog(this, "No tiene creditos suficientes. Cargue creditos.","Creditos Insuficientes",JOptionPane.ERROR_MESSAGE);
			}
			
		});
		
		btnCreditos = new JButton("Creditos");
		
		btnCreditos.addActionListener(e -> {
			VentanaCreditos vc = new gui.VentanaCreditos (this, gestorCreditos);
			vc.setVisible(true);

			actualizarLabelCreditos();
		});
		
		btnRanking = new JButton("Ver Ranking");
		
		btnRanking.addActionListener(e -> {
			new VentanaRanking(this).setVisible(true);
		});
		
		lblMostrarCreditos = new JLabel("Creditos: 0");
		lblMostrarCreditos.setFont(new Font("Arial",Font.BOLD, 16));

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

	private void actualizarLabelCreditos(){
		lblMostrarCreditos.setText("Creditos: "+ gestorCreditos.getCreditos());
	}

	
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new VentanaPrincipal().setVisible(true);
        });
    }
}
