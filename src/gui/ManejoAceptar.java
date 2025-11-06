package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import modelo.GestionarCreditos;

public class ManejoAceptar implements ActionListener {
	
	private JTextField txtCreditos;
	private GestionarCreditos gestorCreditos;
	private JDialog ventana; 
	
	public ManejoAceptar (JTextField txtCreditos,JDialog ventana, GestionarCreditos gestorCreditos) {
		this.txtCreditos = txtCreditos;
		this.gestorCreditos = gestorCreditos;
		this.ventana = ventana; 
	}

	public void actionPerformed (ActionEvent e) {
		if (txtCreditos.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Los creditos no puede estar en blanco");
			return;
		}
		
		try {
			int cantidad = Integer.parseInt(txtCreditos.getText());
			
			gestorCreditos.cargarCreditos(cantidad);
			System.out.println("Creditos cargados: " + cantidad);
			
			ventana.dispose();
		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(ventana, "Ingrese un numero valido");
		}
	}

}
