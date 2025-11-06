package modelo;

import java.io.*;
import java.util.*;

import javax.swing.JOptionPane;

public class Ranking {
    private static final String ARCHIVO = "ranking.txt";
    

    public static void guardar(String nombre, int puntaje) {
        try (FileWriter fw = new FileWriter(ARCHIVO, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter pw = new PrintWriter(bw)) {
            pw.println(nombre + "," + puntaje);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<RegistroRanking> cargar() {
        List<RegistroRanking> lista = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(ARCHIVO))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(",");
                if (partes.length == 2) {
                    lista.add(new RegistroRanking(partes[0], Integer.parseInt(partes[1])));
                }
            }
        } catch (IOException e) {
            // Si no existe el archivo, lo ignoramos
        }
        lista.sort((a, b) -> b.getPuntaje() - a.getPuntaje()); // de mayor a menor
        return lista;
    }
    
    public static void mostrarRanking() {
        List<RegistroRanking> ranking = cargar();
        StringBuilder sb = new StringBuilder("🏆 Ranking:\n\n");
        for (int i = 0; i < Math.min(10, ranking.size()); i++) {
            RegistroRanking r = ranking.get(i);
            sb.append((i + 1) + ". " + r.getNombre() + " - " + r.getPuntaje() + "\n");
        }
        JOptionPane.showMessageDialog(null, sb.toString());
    }
}