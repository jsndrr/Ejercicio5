import java.util.ArrayList;
import java.util.Random;

/**
 * CC2008 - Ejercicio 5: UVG Card Battle
 * Componente Controlador (MVC) que orquesta la lógica del juego y el flujo de turnos.
 */
public class ControladorJuego {
    private Tablero modeloTablero;
    private Jugador modeloJugador;
    private VistaJuego vista;
    private Random random;

    public ControladorJuego(Tablero modeloTablero, Jugador modeloJugador, VistaJuego vista) {
        this.modeloTablero = modeloTablero;
        this.modeloJugador = modeloJugador;
        this.vista = vista;
        this.random = new Random();
    }

    public void iniciarJuego() {
        vista.mostrarMensaje("¡Bienvenido a UVG Card Battle!");
        boolean salir = false;

        while (!salir) {
            vista.mostrarMenuPrincipal();
            int opcion = vista.pedirEntero("Seleccione una opción: ");

            switch (opcion) {
                case 1 -> vista.mostrarCartas(modeloTablero.getMazoCatalogo());
                case 2 -> manejarBusqueda();
                case 3 -> {
                    modeloTablero.ordenarMazoPorEnergia();
                    vista.mostrarMensaje("¡Mazo ordenado exitosamente por costo de energía (menor a mayor)!");
                    vista.mostrarCartas(modeloTablero.getMazoCatalogo());
                }
                case 4 -> manejarTurno();
                case 5 -> {
                    salir = true;
                    vista.mostrarMensaje("¡Gracias por jugar UVG Card Battle!");
                }
                default -> vista.mostrarMensaje("Opción no válida.");
            }
        }
    }

    private void manejarBusqueda() {
        vista.mostrarMensaje("\n--- BÚSQUEDA DE CARTAS ---");
        vista.mostrarMensaje("1. Buscar por ID exacto");
        vista.mostrarMensaje("2. Buscar por Nombre (coincidencia parcial)");
        int subOpcion = vista.pedirEntero("Seleccione método: ");

        if (subOpcion == 1) {
            String id = vista.pedirTexto("Ingrese el ID de la carta: ");
            Carta c = modeloTablero.buscarCarta(id); // Uso de sobrecarga
            if (c != null) {
                vista.mostrarMensaje("\nCarta encontrada:\n" + c.toString());
            } else {
                vista.mostrarMensaje("No se encontró ninguna carta con el ID '" + id + "'.");
            }
        } else if (subOpcion == 2) {
            String nombre = vista.pedirTexto("Ingrese el nombre a buscar: ");
            ArrayList<Carta> res = modeloTablero.buscarCarta(nombre, true); // Uso de sobrecarga
            vista.mostrarCartas(res);
        } else {
            vista.mostrarMensaje("Opción de búsqueda inválida.");
        }
    }

    private void manejarTurno() {
        boolean volver = false;
        while (!volver) {
            vista.mostrarEstadoJugador(modeloJugador, modeloTablero);
            vista.mostrarMenuTurno();
            int opcion = vista.pedirEntero("Seleccione acción de turno: ");

            switch (opcion) {
                case 1 -> jugarCartaTurno();
                case 2 -> tomarCartaCatalogo();
                case 3 -> {
                    modeloJugador.agregarEnergia(3);
                    vista.mostrarMensaje("Has pasado turno. ¡Recuperaste +3 de energía!");
                }
                case 4 -> {
                    vista.mostrarMensaje("\n--- MANO ACTUAL ---");
                    vista.mostrarCartas(modeloJugador.getMano());
                }
                case 5 -> volver = true;
                default -> vista.mostrarMensaje("Opción no válida.");
            }
        }
    }

    private void tomarCartaCatalogo() {
        ArrayList<Carta> catalogo = modeloTablero.getMazoCatalogo();
        if (catalogo.isEmpty()) {
            vista.mostrarMensaje("El catálogo está vacío.");
            return;
        }
        Carta cartaObtenida = catalogo.get(random.nextInt(catalogo.size()));
        modeloJugador.agregarCartaAMano(cartaObtenida);
        vista.mostrarMensaje("¡Has tomado la carta '" + cartaObtenida.getNombre() + "' del catálogo!");
    }

    private void jugarCartaTurno() {
        ArrayList<Carta> mano = modeloJugador.getMano();
        if (mano.isEmpty()) {
            vista.mostrarMensaje("Tu mano está vacía. Debes tomar una carta primero.");
            return;
        }

        vista.mostrarMensaje("\n--- SELECCIONA UNA CARTA DE TU MANO PARA JUGAR ---");
        vista.mostrarCartas(mano);
        int idx = vista.pedirEntero("Ingrese el número de la carta a jugar (0 para cancelar): ");

        if (idx < 1 || idx > mano.size()) {
            vista.mostrarMensaje("Operación cancelada o índice fuera de rango.");
            return;
        }

        Carta seleccionada = mano.get(idx - 1);
        if (modeloJugador.getEnergia() < seleccionada.getCostoEnergia()) {
            vista.mostrarMensaje("Energía insuficiente. Necesitas " + seleccionada.getCostoEnergia() + " de energía.");
            return;
        }

        // Ejecución polimórfica sobrecargada
        String resultado = seleccionada.jugarCarta(modeloJugador, modeloTablero);
        modeloJugador.removerCartaDeMano(seleccionada);
        vista.mostrarMensaje("\nRESULTADO: " + resultado);
    }
}