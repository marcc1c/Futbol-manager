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
        return
                "\nNombre:           " + nombre + "\n" +
                "Apellido:         " + apellido + "\n" +
                "Fecha nacimiento: " + fechaNacimiento + "\n" +
                "Motivación:       " + nivelMotivacion + "\n" +
                "Sueldo anual:     " + sueldoAnual;
    }
}
