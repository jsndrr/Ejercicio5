import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * CC2008 - Ejercicio 5: UVG Card Battle
 * Componente Vista (MVC) para despliegue en consola.
 */
public class VistaJuego {
    private Scanner scanner;

    public VistaJuego() {
        this.scanner = new Scanner(System.in);
    }

    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }

    public void mostrarMenuPrincipal() {
        System.out.println("\n=== UVG CARD BATTLE - MENÚ PRINCIPAL ===");
        System.out.println("1. Listar todas las cartas del mazo/catálogo");
        System.out.println("2. Buscar carta (por ID o por Nombre)");
        System.out.println("3. Ordenar mazo por costo de energía");
        System.out.println("4. Iniciar / Continuar partida por turnos");
        System.out.println("5. Salir");
    }

    public void mostrarMenuTurno() {
        System.out.println("\n--- ACCIONES DE TURNO ---");
        System.out.println("1. Jugar una carta de tu mano");
        System.out.println("2. Tomar una carta del catálogo al azar");
        System.out.println("3. Pasar turno (Recupera +3 de energía)");
        System.out.println("4. Ver tu mano de cartas");
        System.out.println("5. Volver al menú principal");
    }

    public void mostrarCartas(ArrayList<Carta> cartas) {
        if (cartas == null || cartas.isEmpty()) {
            System.out.println("No hay cartas para mostrar.");
            return;
        }
        System.out.println("----------------------------------------------------------------------------------");
        for (int i = 0; i < cartas.size(); i++) {
            System.out.printf("[%d] %s\n", i + 1, cartas.get(i).toString());
        }
        System.out.println("----------------------------------------------------------------------------------");
    }

    public void mostrarEstadoJugador(Jugador j, Tablero t) {
        System.out.println("\n========================================================");
        System.out.println(" ESTADO DEL JUGADOR: " + j.getNombre());
        System.out.println(" Energía Disponible: " + j.getEnergia());
        System.out.println(" Créditos Académicos: " + j.getCreditosAcumulados());
        System.out.println(" Cartas en Mano: " + j.getMano().size());
        System.out.println(" Estado Global del Tablero: " + t.getEventoActivo());
        System.out.println("========================================================");
    }

    public String pedirTexto(String mensaje) {
        System.out.print(mensaje);
        return scanner.nextLine().trim();
    }

    public int pedirEntero(String mensaje) {
        while (true) {
            try {
                System.out.print(mensaje);
                int valor = scanner.nextInt();
                scanner.nextLine(); // Limpieza de búfer
                return valor;
            } catch (InputMismatchException e) {
                System.out.println("Error: Ingrese un entero válido.");
                scanner.nextLine();
            }
        }
    }
}