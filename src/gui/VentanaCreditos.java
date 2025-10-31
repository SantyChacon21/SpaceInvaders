package gui;

import java.awt.Container;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import modelo.GestionarCreditos;

public class VentanaCreditos extends JDialog {
	private JTextField txtCreditos;
	private JLabel lblCreditos;
	private JButton btnAceptar, btnCancelar;
	private GestionarCreditos gestorCreditos;
	
	
	public VentanaCreditos (JFrame parent, GestionarCreditos gestorCreditos) {
		super(parent, "Cargar creditos", true);
		this.gestorCreditos = gestorCreditos;
		
		configuracion();
		eventos();
		
		pack();
		setLocationRelativeTo(parent);
	}


	private void configuracion() {
		Container c = this.getContentPane();
		c.setLayout(new GridLayout(2, 2, 5, 5));
		
		lblCreditos = new JLabel("Creditos: ");
		lblCreditos.setHorizontalAlignment(JLabel.RIGHT);
		
		txtCreditos = new JTextField(10);
		
		btnAceptar = new JButton ("Aceptar");
		btnCancelar = new JButton ("Cancelar");
		
		c.add(lblCreditos);
		c.add(txtCreditos);
		c.add(btnCancelar);
		c.add(btnAceptar);
	}
	
	private void eventos () {
		btnAceptar.addActionListener(new ManejoAceptar(txtCreditos, this, gestorCreditos));
		btnCancelar.addActionListener(new ManejoCancelar(this));
	}
}


