package personas;

public class Entrenador extends Personas {
    private int numTorneosGanados;
    private boolean seleccionadorNacional;

    public Entrenador() {
    }

    public Entrenador(String nombre, String apellido, String fechaNacimiento, int nivelMotivacion, int sueldoAnual, int numTorneosGanados, boolean seleccionadorNacional) {
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

    public void incrementarSou () {
        System.out.print("El sueldo a incrementado de " + this.sueldoAnual + " a ");
        this.sueldoAnual = this.sueldoAnual + this.sueldoAnual / 200;
        System.out.println(this.sueldoAnual + " (0,5%)");
    }

    @Override
    public String toString() {
        return super.toString() + "Entrenador{" +
                "numTorneosGanados='" + numTorneosGanados + '\'' +
                ", seleccionadorNacional=" + seleccionadorNacional +
                '}';
    }
}
