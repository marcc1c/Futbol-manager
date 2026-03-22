package personas;

import java.util.Random;

/**
 * Clase que representa a un entrenador de fútbol.
 * Hereda de Personas y añade información sobre torneos ganados y si es seleccionador nacional.
 */
public class Entrenador extends Personas {
    private int numTorneosGanados;
    private boolean seleccionadorNacional;

    public Entrenador() {
    }

    /**
     * Constructor con todos los campos de Entrenador.
     * @param nombre Nombre del entrenador.
     * @param apellido Apellido del entrenador.
     * @param fechaNacimiento Fecha de nacimiento.
     * @param nivelMotivacion Nivel de motivación inicial.
     * @param sueldoAnual Sueldo anual del entrenador.
     * @param numTorneosGanados Número de torneos ganados hasta la fecha.
     * @param seleccionadorNacional true si es seleccionador nacional, false en caso contrario.
     */
    public Entrenador(String nombre, String apellido, String fechaNacimiento, int nivelMotivacion, double sueldoAnual, int numTorneosGanados, boolean seleccionadorNacional) {
        super(nombre, apellido, fechaNacimiento, nivelMotivacion, sueldoAnual);
        this.numTorneosGanados = numTorneosGanados;
        this.seleccionadorNacional = seleccionadorNacional;
    }

    public int getNumTorneosGanados() {
        return numTorneosGanados;
    }

    public void setNumTorneosGanados(int numTorneosGanados) {
        this.numTorneosGanados = numTorneosGanados;
    }

    public boolean isSeleccionadorNacional() {
        return seleccionadorNacional;
    }

    public void setSeleccionadorNacional(boolean seleccionadorNacional) {
        this.seleccionadorNacional = seleccionadorNacional;
    }


    /**
     * Incrementa el sueldo anual del entrenador en un 0.5%.
     */
    public void incrementarSou() {
        this.sueldoAnual = this.sueldoAnual + (this.sueldoAnual * 0.005);
    }


    /**
     * Realiza un entrenamiento que aumenta la motivación e incrementa el sueldo.
     */
    @Override
    public void entrenament() {
        super.entrenament();
        incrementarSou();
    }

    @Override
    public String toString() {
        return String.format("========== Entrenador ==========\n%s" +
                        "\nTorneos ganados:        %-20d" +
                        "\nSeleccionador nacional: %-20s",
                super.toString(),
                numTorneosGanados,
                seleccionadorNacional ? "Sí" : "No")
                + "\n" + "=".repeat(32) + "\n";
    }
}
