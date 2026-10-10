/**
 * CC2008 - Ejercicio 5: UVG Card Battle
 * Clase abstracta base que representa una carta del juego.
 */
public abstract class Carta {
    protected String id;
    protected String nombre;
    protected int costoEnergia;
    protected String descripcion;

    public Carta(String id, String nombre, int costoEnergia, String descripcion) {
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("El ID de la carta no puede estar vacío.");
        }
        if (costoEnergia < 0) {
            throw new IllegalArgumentException("El costo de energía no puede ser negativo.");
        }
        this.id = id.trim();
        this.nombre = nombre != null ? nombre.trim() : "";
        this.costoEnergia = costoEnergia;
        this.descripcion = descripcion != null ? descripcion.trim() : "";
    }

    public String getId() { return id; }
    public String getNombre() { return nombre; }
    public int getCostoEnergia() { return costoEnergia; }
    public String getDescripcion() { return descripcion; }

    // Overloading de métodos abstractos para efectos polimórficos de la carta
    public abstract String jugarCarta(Jugador jugador);
    public abstract String jugarCarta(Jugador jugador, Tablero tablero);

    @Override
    public String toString() {
        return String.format("ID: %-6s | Nombre: %-22s | Costo Energía: %-2d | Desc: %s",
                id, nombre, costoEnergia, descripcion);
    }
}