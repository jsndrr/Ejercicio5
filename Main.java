/**
 * CC2008 - Ejercicio 5: UVG Card Battle
 * Driver Program de la aplicación.
 */
public class Main {
    public static void main(String[] args) {
        // Inicialización de componentes MVC
        Tablero tablero = new Tablero(); // Carga catálogo con >= 10 cartas
        Jugador jugador = new Jugador("Estudiante UVG", 10); // Inicia con 10 de energía
        VistaJuego vista = new VistaJuego();

        ControladorJuego controlador = new ControladorJuego(tablero, jugador, vista);
        controlador.iniciarJuego();
    }
}