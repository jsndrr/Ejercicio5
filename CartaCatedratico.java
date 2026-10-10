/**
 * CC2008 - Ejercicio 5: UVG Card Battle
 * Subclase que representa a un Catedrático universitario.
 */
public class CartaCatedratico extends Carta {
    private String departamento;
    private int llamadasAtencion;
    private int tiempoAtencion;

    public CartaCatedratico(String id, String nombre, int costoEnergia, String descripcion,
                             String departamento, int llamadasAtencion, int tiempoAtencion) {
        super(id, nombre, costoEnergia, descripcion);
        this.departamento = departamento;
        this.llamadasAtencion = llamadasAtencion;
        this.tiempoAtencion = tiempoAtencion;
    }

    @Override
    public String jugarCarta(Jugador jugador) {
        if (!jugador.reducirEnergia(costoEnergia)) {
            return "Energía insuficiente para consultar al catedrático " + nombre + ".";
        }
        // Efecto: A mayor llamadas de atención, más disciplina/energía recupera el jugador posteriormente
        int bonoEnergia = Math.max(1, llamadasAtencion * 2);
        jugador.agregarEnergia(bonoEnergia);
        return String.format("¡Has asistido a la revisión con el Prof. %s (%s)! Gastaste %d de energía y obtuviste un bono de %d de energía por disciplina.",
                nombre, departamento, costoEnergia, bonoEnergia);
    }

    @Override
    public String jugarCarta(Jugador jugador, Tablero tablero) {
        String resultadoBase = jugarCarta(jugador);
        tablero.setEventoActivo("Atención con " + nombre + " (" + departamento + ")");
        return resultadoBase + " El estado del tablero ha cambiado a modo de revisión académica.";
    }

    public String getDepartamento() { return departamento; }
    public int getLlamadasAtencion() { return llamadasAtencion; }
    public int getTiempoAtencion() { return tiempoAtencion; }

    @Override
    public String toString() {
        return String.format("[Catedrático] %s | Depto: %s | Llamadas Atención: %d | Tiempo: %d min",
                super.toString(), departamento, llamadasAtencion, tiempoAtencion);
    }
}