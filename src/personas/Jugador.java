package personas;

import java.util.Random;

public class Jugador extends Personas {
    final String[] Posiciones = {"POR", "DEF", "MIG", "DAV"};

    private String posicion; // POR, DEF, MIG i DAV
    private int dorsal;
    private int qualidad;

    public Jugador() {
    }

    public Jugador(String nombre, String apellido, String fechaNacimiento, int nivelMotivacion, int sueldoAnual, int dorsal, String posicion, int qualidadTeorica) {
        super(nombre, apellido, fechaNacimiento, nivelMotivacion, sueldoAnual);
        this.dorsal = dorsal;
        this.posicion = posicion;
        this.qualidad = qualidadTeorica;
    }

    public int getDorsal() {
        return dorsal;
    }

    public void setDorsal(int dorsal) {
        this.dorsal = dorsal;
    }

    public String getPosicion() {
        return posicion;
    }

    public void setPosicion(String posicion) {
        this.posicion = posicion;
    }

    public int getQualidad() {
        return qualidad;
    }

    public void setQualidad(int qualidad) {
        this.qualidad = qualidad;
    }

    public void canviDePosicion(String nuevaPosicion) {
        Random random = new Random();
        int numRandom = random.nextInt(100) + 1;

        int fallo = 0;
        for (int i = 0; i < Posiciones.length; i++) {
            if (!Posiciones[i].equals(nuevaPosicion.toUpperCase())) {
                fallo++;
            }
        }
            if (fallo != Posiciones.length) {
                if (numRandom <= 5) {
                    System.out.print("Has cambiado de posición al jugador " + this.nombre + " de " + this.posicion + " a ");
                    this.posicion = nuevaPosicion.toUpperCase();
                    System.out.print(nuevaPosicion.toUpperCase());
                    qualidad++;
                } else {
                    System.out.println("El jugador " + this.nombre + " no ha cambiado de posicion, sigue siendo " + this.posicion);
                }
            } else {
                System.out.println("Has introducido una posición incorrecta, prueba con: ");
                for (int j = 0; j < Posiciones.length; j++) {
                    System.out.println(Posiciones[j]);
                }
            }

    }

    @Override
    public String toString() {
        return super.toString() + "Jugador{" +
                "dorsal=" + dorsal +
                ", posicion='" + posicion + '\'' +
                ", qualidadTeorica=" + qualidad +
                '}';
    }
}

