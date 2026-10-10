/**
 * CC2008 - Ejercicio 5: UVG Card Battle
 * Subclase que representa un Curso universitario.
 */
public class CartaCurso extends Carta {
    private int creditos;
    private int dificultad;

    public CartaCurso(String id, String nombre, int costoEnergia, String descripcion, int creditos, int dificultad) {
        super(id, nombre, costoEnergia, descripcion);
        if (creditos <= 0 || dificultad <= 0) {
            throw new IllegalArgumentException("Los créditos y la dificultad deben ser enteros positivos.");
        }
        this.creditos = creditos;
        this.dificultad = dificultad;
    }

    @Override
    public String jugarCarta(Jugador jugador) {
        if (!jugador.reducirEnergia(costoEnergia)) {
            return "No tienes suficiente energía para aprobar el curso " + nombre + ".";
        }
        jugador.agregarCreditos(creditos);
        return String.format("¡Has completado exitosamente el curso '%s'! Dificultad: %d/10. Sumaste %d créditos académicos.",
                nombre, dificultad, creditos);
    }

    @Override
    public String jugarCarta(Jugador jugador, Tablero tablero) {
        return jugarCarta(jugador); // Comportamiento directo sobre el jugador
    }

    public int getCreditos() { return creditos; }
    public int getDificultad() { return dificultad; }

    @Override
    public String toString() {
        return String.format("[Curso] %s | Créditos: %d | Dificultad: %d/10",
                super.toString(), creditos, dificultad);
    }
}