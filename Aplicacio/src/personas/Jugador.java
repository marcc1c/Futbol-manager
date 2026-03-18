package personas;

import java.util.Random;

public class Jugador extends Personas {

    final static String[] Posiciones = {"POR", "DEF", "MIG", "DAV"};

    private String posicion;
    private int dorsal, qualidad;

    public Jugador() {
    }

    public Jugador(String nombre, String apellido, String fechaNacimiento, int nivelMotivacion, double sueldoAnual, int dorsal, String posicion, int qualidadTeorica) {
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

    public String toString() {
        return String.format("========== Jugador ==========\n" +
                        "%s" +
                        "Dorsal:               %-20d\n" +
                        "Posición:             %-20s\n" +
                        "Calidad Teórica:      %-20d\n",
                super.toString(),
                dorsal,
                posicion,
                qualidad);
    }

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


    public void entrenament() {
        if (this.nivelMotivacion < 10) {
            this.nivelMotivacion++;
        }
        Random random = new Random();
        String novaPos = this.posicion;
        while (novaPos.equals(this.posicion)) {
            novaPos = Posiciones[random.nextInt(Posiciones.length)];
        }
        canviDePosicion(novaPos);
    }
}

