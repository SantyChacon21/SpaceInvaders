package gui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class VentanaDificultad extends JFrame {
	private String dificultadSeleccionada = "Cadete"; // por defecto

    public VentanaDificultad() {
        setTitle("Seleccionar Dificultad");
        setSize(300, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JLabel titulo = new JLabel("Selecciona la dificultad", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 18));
        add(titulo, BorderLayout.NORTH);

        String[] opciones = {"Cadete", "Guerrero", "Master"};
        JComboBox<String> combo = new JComboBox<>(opciones);
        combo.setSelectedIndex(0);
        combo.addActionListener(e -> dificultadSeleccionada = (String) combo.getSelectedItem());
        add(combo, BorderLayout.CENTER);

        JButton btnAceptar = new JButton("Aceptar");
        btnAceptar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                iniciarJuego();
            }
        });
        add(btnAceptar, BorderLayout.SOUTH);
    }

    private void iniciarJuego() {
        dispose(); // cierra esta ventana
        VentanaJuego juego = new VentanaJuego(dificultadSeleccionada);
        juego.setVisible(true);
    }

}
