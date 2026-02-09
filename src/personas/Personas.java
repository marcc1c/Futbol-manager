package personas;

public class Personas {
    protected String nombre, apellido, fechaNacimiento;
    protected double nivelMotivacion, sueldoAnual;
    public Personas() {}

    public Personas(String nombre, String apellido, String fechaNacimiento, int nivelMotivacion, int sueldoAnual) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaNacimiento = fechaNacimiento;
        this.nivelMotivacion = nivelMotivacion;
        this.sueldoAnual = sueldoAnual;
    }
        public double getNivelMotivacion() {
            return nivelMotivacion;
        }

        public void setNivelMotivacion(double nivelMotivacion) {
            this.nivelMotivacion = nivelMotivacion;
        }

        public double getSueldoAnual() {
            return sueldoAnual;
        }

        public void setSueldoAnual(double sueldoAnual) {
            this.sueldoAnual = sueldoAnual;
        }

    @Override
    public String toString() {
        return "Personas{" +
                "nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", fechaNacimiento='" + fechaNacimiento + '\'' +
                ", nivelMotivacion='" + nivelMotivacion + '\'' +
                ", sueldoAnual='" + sueldoAnual + '\'' +
                '}';
    }
}
