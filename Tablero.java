import java.util.ArrayList;

/**
 * CC2008 - Ejercicio 5: UVG Card Battle
 * Modelo que gestiona el tablero, el catálogo polimórfico de cartas y las búsquedas.
 */
public class Tablero {
    private ArrayList<Carta> mazoCatalogo;
    private String eventoActivo;

    public Tablero() {
        this.mazoCatalogo = new ArrayList<>();
        this.eventoActivo = "Ninguno (Normal)";
        inicializarCatalogoAleatorio();
    }

    /**
     * Carga inicial en memoria con al menos 10 cartas aleatorias (mínimo requerido)
     */
    public final void inicializarCatalogoAleatorio() {
        mazoCatalogo.add(new CartaCatedratico("CAT-01", "Dr. Morales", 3, "Experto en Estructura de Datos", "Computación", 2, 45));
        mazoCatalogo.add(new CartaCatedratico("CAT-02", "Inga. Ruiz", 2, "Docente de POO", "Computación", 1, 30));
        mazoCatalogo.add(new CartaCatedratico("CAT-03", "Lic. Girón", 4, "Titular de Cálculo Multivariable", "Matemáticas", 3, 60));

        mazoCatalogo.add(new CartaCurso("CUR-01", "POO", 4, "Programación Orientada a Objetos", 5, 8));
        mazoCatalogo.add(new CartaCurso("CUR-02", "Algoritmos", 3, "Estructuras de Datos Basicas", 4, 7));
        mazoCatalogo.add(new CartaCurso("CUR-03", "Física 1", 5, "Mecánica Clásica", 5, 9));
        mazoCatalogo.add(new CartaCurso("CUR-04", "Química General", 2, "Conceptos fundamentales de materia", 3, 5));

        mazoCatalogo.add(new CartaEvento("EVE-01", "Semana de Parciales", 2, "Aumenta la tensión en el campus", "Académico", 3));
        mazoCatalogo.add(new CartaEvento("EVE-02", "Feria de Clubes", 1, "Oportunidad de recreación", "Social", 2));
        mazoCatalogo.add(new CartaEvento("EVE-03", "Hackathon UVG", 3, "Competencia intensiva de desarrollo", "Competencia", 2));
    }

    public ArrayList<Carta> getMazoCatalogo() { return mazoCatalogo; }

    // --- OVERLOADING DE BÚSQUEDA DE CARTAS ---

    public Carta buscarCarta(String id) {
        for (Carta c : mazoCatalogo) {
            if (c.getId().equalsIgnoreCase(id.trim())) {
                return c;
            }
        }
        return null;
    }

    public ArrayList<Carta> buscarCarta(String criterio, boolean porNombre) {
        ArrayList<Carta> resultados = new ArrayList<>();
        for (Carta c : mazoCatalogo) {
            if (porNombre && c.getNombre().toLowerCase().contains(criterio.toLowerCase().trim())) {
                resultados.add(c);
            }
        }
        return resultados;
    }

    /**
     * Ordena la lista polimórfica por costo de energía de menor a mayor
     */
    public void ordenarMazoPorEnergia() {
        mazoCatalogo.sort((c1, c2) -> Integer.compare(c1.getCostoEnergia(), c2.getCostoEnergia()));
    }

    public String getEventoActivo() { return eventoActivo; }
    public void setEventoActivo(String eventoActivo) { this.eventoActivo = eventoActivo; }
}