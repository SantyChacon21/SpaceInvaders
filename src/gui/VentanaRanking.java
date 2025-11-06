package gui;

import java.awt.BorderLayout;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import modelo.Ranking;
import modelo.RegistroRanking;

public class VentanaRanking extends JDialog {
    private JTable tablaRanking;
    private JButton btnCerrar;

    public VentanaRanking(JFrame parent) {
        super(parent, "Ranking de jugadores", true);
        setSize(400, 300);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout());

        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("Jugador");
        modelo.addColumn("Puntaje");

        // Cargar ranking desde el archivo
        List<RegistroRanking> lista = Ranking.cargar();

        for (RegistroRanking r : lista) {
            modelo.addRow(new Object[]{r.getNombre(), r.getPuntaje()});
        }

        tablaRanking = new JTable(modelo);
        JScrollPane scroll = new JScrollPane(tablaRanking);

        btnCerrar = new JButton("Cerrar");
        btnCerrar.addActionListener(e -> dispose());

        add(scroll, BorderLayout.CENTER);
        add(btnCerrar, BorderLayout.SOUTH);
    }
}