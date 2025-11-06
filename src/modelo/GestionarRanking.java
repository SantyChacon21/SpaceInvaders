package modelo;

import java.util.*;

public class GestionarRanking {
	private List<Jugador> ranking = new ArrayList<>();

    public void actualizarRanking(Jugador nuevo) {
        Optional<Jugador> existente = ranking.stream()
            .filter(j -> j.getNombre().equalsIgnoreCase(nuevo.getNombre()))
            .findFirst();

        if (existente.isPresent()) {
            if (nuevo.getPuntaje() > existente.get().getPuntaje()) {
                existente.get().setPuntaje(nuevo.getPuntaje());
            }
        } else {
            ranking.add(nuevo);
        }

        ranking.sort(Comparator.comparingInt(Jugador::getPuntaje).reversed());
    }

    public List<Jugador> getRanking() {
        return ranking;
    }
}
