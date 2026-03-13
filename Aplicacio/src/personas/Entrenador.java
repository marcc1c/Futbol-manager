package personas;

public class Entrenador extends Personas {
    private int numTorneosGanados;
    private boolean seleccionadorNacional;

    public Entrenador() {
    }

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
