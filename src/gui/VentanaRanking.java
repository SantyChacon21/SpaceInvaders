package gui;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import modelo.GestionarRanking;
import modelo.Jugador;

public class VentanaRanking extends JDialog {
	private JTable tablaRanking;
	private JButton btnCerrar; 
	
	public VentanaRanking (JFrame parent, GestionarRanking gestorRanking) {
		super (parent, "Ranking de jugadores", true);
		setSize(400, 300);
		setLocationRelativeTo(parent); 
		setLayout(new BorderLayout());
		
		DefaultTableModel modelo = new DefaultTableModel(); 
		modelo.addColumn("Jugador");
		modelo.addColumn("Puntaje");
		
		List<Jugador> lista = gestorRanking.getRanking();
		for (Jugador j : lista) {
			modelo.addRow(new Object[] {j.getNombre(), j.getPuntaje()});
		}
		
		tablaRanking = new JTable(modelo);
		JScrollPane scroll = new JScrollPane (tablaRanking);
		
		btnCerrar = new JButton("Cerrar");
		btnCerrar.addActionListener(e -> dispose());
		
		add(scroll, BorderLayout.CENTER);
		add(btnCerrar, BorderLayout.SOUTH);
	}
}
