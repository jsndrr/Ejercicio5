/**
 * CC2008 - Ejercicio 5: UVG Card Battle
 * Subclase que representa un Evento del Campus.
 */
public class CartaEvento extends Carta {
    private String tipoEvento;
    private int duracionTurnos;

    public CartaEvento(String id, String nombre, int costoEnergia, String descripcion, String tipoEvento, int duracionTurnos) {
        super(id, nombre, costoEnergia, descripcion);
        this.tipoEvento = tipoEvento;
        this.duracionTurnos = duracionTurnos;
    }

    @Override
    public String jugarCarta(Jugador jugador) {
        if (!jugador.reducirEnergia(costoEnergia)) {
            return "No tienes suficiente energía para activar el evento " + nombre + ".";
        }
        return String.format("¡Se ha activado el evento '%s' (%s)! Se siente el ambiente en el campus.",
                nombre, tipoEvento);
    }

    @Override
    public String jugarCarta(Jugador jugador, Tablero tablero) {
        String res = jugarCarta(jugador);
        if (jugador.getEnergia() >= 0) {
            tablero.setEventoActivo(nombre + " (" + tipoEvento + " - " + duracionTurnos + " turnos)");
            return res + " Se actualizó el estado global del tablero.";
        }
        return res;
    }

    public String getTipoEvento() { return tipoEvento; }
    public int getDuracionTurnos() { return duracionTurnos; }

    @Override
    public String toString() {
        return String.format("[Evento Campus] %s | Tipo: %s | Duración: %d turnos",
                super.toString(), tipoEvento, duracionTurnos);
    }
}