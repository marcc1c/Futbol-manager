package personas;

public class Personas {
    protected String nombre, apellido, fechaNacimiento;
    protected double sueldoAnual;
    int nivelMotivacion;

    public Personas() {
    }

    public Personas(String nombre, String apellido, String fechaNacimiento, int nivelMotivacion, double sueldoAnual) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaNacimiento = fechaNacimiento;
        this.nivelMotivacion = nivelMotivacion;
        this.sueldoAnual = sueldoAnual;

    }

    /*GETTERS Y SETTER*/
    public int getNivelMotivacion() {
        return nivelMotivacion;
    }

    public void setNivelMotivacion(int nivelMotivacion) {
        this.nivelMotivacion = nivelMotivacion;
    }

    public double getSueldoAnual() {
        return sueldoAnual;
    }

    public void setSueldoAnual(double sueldoAnual) {
        this.sueldoAnual = sueldoAnual;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    @Override
    public String toString() {
        return String.format(
                "Nombre:                %-20s\n" +  // Alineado a la izquierda con un ancho fijo
                        "Apellido:              %-20s\n" +  // Alineado a la izquierda con un ancho fijo
                        "Fecha nacimiento:      %-20s\n" +  // Alineado a la izquierda con un ancho fijo
                        "Motivación:            %-20d\n" +  // Alineado a la izquierda con un ancho fijo
                        "Sueldo anual:          %-20.2f\n",  // Alineado a la izquierda con un ancho fijo y 2 decimales
                nombre, apellido, fechaNacimiento, nivelMotivacion, sueldoAnual);
    }
}
