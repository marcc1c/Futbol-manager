package personas;

/**
 * Clase abstracta que representa a una persona en el ámbito del club.
 * Sirve como base para Jugador y Entrenador.
 */
public abstract class Personas {
    protected String nombre, apellido, fechaNacimiento;
    protected double sueldoAnual;
    int nivelMotivacion;

    public Personas() {
    }

    /**
     * Constructor con todos los campos base de una persona.
     * @param nombre Nombre de la persona.
     * @param apellido Apellido de la persona.
     * @param fechaNacimiento Fecha de nacimiento.
     * @param nivelMotivacion Nivel de motivación inicial.
     * @param sueldoAnual Sueldo anual inicial.
     */
    public Personas(String nombre, String apellido, String fechaNacimiento, int nivelMotivacion, double sueldoAnual) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaNacimiento = fechaNacimiento;
        this.nivelMotivacion = nivelMotivacion;
        this.sueldoAnual = sueldoAnual;
    }

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
                "Nombre:                %-20s\n" +
                        "Apellido:              %-20s\n" +
                        "Fecha nacimiento:      %-20s\n" +
                        "Motivación:            %-20d\n" +
                        "Sueldo anual:          %-20.2f\n",
                nombre, apellido, fechaNacimiento, nivelMotivacion, sueldoAnual);
    }

    /**
     * Realiza un entrenamiento que incrementa el nivel de motivación si este es inferior a 10.
     */
    public void entrenament() {
        if (this.nivelMotivacion < 10) {
            System.out.print(this.nombre + "ahora tiene una motivación de " + this.nivelMotivacion);
            this.nivelMotivacion++;
            System.out.println(" --> " + this.nivelMotivacion);
        }
    }
}
