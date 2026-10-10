import java.util.ArrayList;

/**
 * CC2008 - Ejercicio 5: UVG Card Battle
 * Representa al estudiante/jugador.
 */
public class Jugador {
    private String nombre;
    private int energia;
    private int creditosAcumulados;
    private ArrayList<Carta> mano;

    public Jugador(String nombre, int energiaInicial) {
        this.nombre = nombre;
        this.energia = energiaInicial;
        this.creditosAcumulados = 0;
        this.mano = new ArrayList<>();
    }

    public String getNombre() { return nombre; }
    public int getEnergia() { return energia; }
    public void setEnergia(int energia) { this.energia = energia; }

    public void agregarEnergia(int cantidad) {
        if (cantidad > 0) this.energia += cantidad;
    }

    public boolean reducirEnergia(int cantidad) {
        if (energia >= cantidad) {
            energia -= cantidad;
            return true;
        }
        return false;
    }

    public int getCreditosAcumulados() { return creditosAcumulados; }
    public void agregarCreditos(int creditos) {
        if (creditos > 0) this.creditosAcumulados += creditos;
    }

    public ArrayList<Carta> getMano() { return mano; }
    public void agregarCartaAMano(Carta c) {
        if (c != null) mano.add(c);
    }

    public boolean removerCartaDeMano(Carta c) {
        return mano.remove(c);
    }
}