package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;

import javax.swing.JOptionPane;

public class ManejoCancelar implements ActionListener {
	
	private JDialog ventana;
	
	public ManejoCancelar (JDialog ventanaCredito) {
		this.ventana = ventanaCredito; 
	}
	

	public ManejoCancelar(VentanaCredito ventanaCredito) {
		// TODO Auto-generated constructor stub
	}


	public void actionPerformed (ActionEvent e) {
		int respuesta = JOptionPane.showConfirmDialog(ventana, "Cancelar operacion", "Aceptar", JOptionPane.YES_NO_OPTION);
		
		if (respuesta == JOptionPane.YES_OPTION) {
			ventana.dispose();
		}
	}
}
