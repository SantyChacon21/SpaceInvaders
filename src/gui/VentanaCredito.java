package gui;

import java.awt.Container;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class VentanaCredito extends JFrame {
	private JLabel lblCreditos;
	private JTextField txtCreditos;
	private JButton btnAceptar, btnCancelar;
	
	public VentanaCredito () {
		configuracion();
		eventos();
	}
	
	private void configuracion () {
		Container c = this.getContentPane();
		c.setLayout(new GridLayout(5,2));
		lblCreditos = new JLabel ("Creditos");
		lblCreditos.setHorizontalAlignment(JLabel.CENTER);
		txtCreditos = new JTextField();
		btnAceptar = new JButton("Aceptar");
		btnCancelar = new JButton("Cancelar");
		JPanel pnlBotones = new JPanel();
		pnlBotones.setLayout(new GridLayout(1,2));
		pnlBotones.add(btnAceptar);
		pnlBotones.add(btnCancelar);
		c.add(lblCreditos);
		c.add(txtCreditos);
		c.add(new JLabel());
		c.add(pnlBotones);
	}
	
	private void eventos () {
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		btnCancelar.addActionListener(new ManejoCancelar(this));
		btnAceptar.addActionListener(new ManejoAceptar(txtCreditos, null, null));
	}
}
