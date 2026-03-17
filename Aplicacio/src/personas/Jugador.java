package personas;

import java.util.Random;

public class Jugador extends Personas {
    /*VARIABLES*/
    final static String[] Posiciones = {"POR", "DEF", "MIG", "DAV"};

    private String posicion; // POR, DEF, MIG i DAV
    private int dorsal, qualidad;

    public Jugador() {
    }


    public Jugador(String nombre, String apellido, String fechaNacimiento, int nivelMotivacion, double sueldoAnual, int dorsal, String posicion, int qualidadTeorica) {
        super(nombre, apellido, fechaNacimiento, nivelMotivacion, sueldoAnual);
        this.dorsal = dorsal;
        this.posicion = posicion;
        this.qualidad = qualidadTeorica;
    }
     /*GETTERS Y SETTERS*/
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

    public String toString() {
        return String.format("========== Jugador ==========\n" +
                        "%s" +                          // Información de la clase Persona
                        "Dorsal:               %-20d\n" + // Alineado a la izquierda con 20 caracteres
                        "Posición:             %-20s\n" + // Alineado a la izquierda con 20 caracteres
                        "Calidad Teórica:      %-20d\n",   // Alineado a la izquierda con 20 caracteres
                super.toString(),
                dorsal,
                posicion,
                qualidad);
    }
    /**
     * Intenta canviar la posició del jugador/a a una posició específica.
     * Hi ha un 5% de probabilitats que el canvi es produeixi.
     * Si es produeix el canvi, la qualitat del jugador augmenta en 1 punt.
     *
     * @param nuevaPosicion Nova posició a intentar assignar
     * @return true si el canvi s'ha produït, false en cas contrari
     */
    public boolean canviDePosicion(String nuevaPosicion) {
        Random random = new Random();
        int numRandom = random.nextInt(100) + 1;
        boolean haCambiado = false;

        int fallo = 0;
        for (String posicione : Posiciones) {
            if (!posicione.equals(nuevaPosicion.toUpperCase())) {
                fallo++;
            }
        }
        if (fallo != Posiciones.length) {
            if (numRandom <= 5) {
                System.out.println("El jugador " + this.nombre + " ha canviat de posició de " + this.posicion + " a " + nuevaPosicion.toUpperCase() + "!");
                this.posicion = nuevaPosicion.toUpperCase();
                qualidad++;
                haCambiado = true;
            }
        } else {
            System.out.println("Has introduït una posició incorrecta, prova amb: POR, DEF, MIG, DAV");
        }
        return haCambiado;
    }

    /**
     * Realitza una sessió d'entrenament per al jugador/a del mercat de fitxatges.
     * Augmenta el nivell de motivació en 1 si no ha arribat al màxim (10).
     * A més, intenta un canvi de posició aleatori amb el mètode canviDePosicio.
     */
    public void entrenament() {
        if (this.nivelMotivacion < 10) {
            this.nivelMotivacion++;
        }
        // Escollim una posició aleatòria diferent de l'actual
        Random random = new Random();
        String novaPos = this.posicion;
        while (novaPos.equals(this.posicion)) {
            novaPos = Posiciones[random.nextInt(Posiciones.length)];
        }
        canviDePosicion(novaPos);
    }
}

