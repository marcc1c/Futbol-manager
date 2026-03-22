package personas;

import java.util.Random;

/**
 * Clase que representa a un jugador de fútbol.
 * Hereda de la clase Personas e incluye atributos específicos como posición, dorsal y calidad.
 */
public class Jugador extends Personas {

    final static String[] Posiciones = {"POR", "DEF", "MIG", "DAV"};

    private String posicion;
    private int dorsal, qualidad;

    public Jugador() {
    }

    /**
     * Constructor con todos los campos de Jugador.
     * @param nombre Nombre del jugador.
     * @param apellido Apellido del jugador.
     * @param fechaNacimiento Fecha de nacimiento del jugador.
     * @param nivelMotivacion Nivel de motivación inicial.
     * @param sueldoAnual Sueldo anual del jugador.
     * @param dorsal Número del dorsal.
     * @param posicion Posición en la que juega (POR, DEF, MIG, DAV).
     * @param qualidadTeorica Nivel de calidad del jugador.
     */
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

    /**
     * Intenta cambiar la posición del jugador. Existe una probabilidad de éxito del 5%.
     * @param nuevaPosicion La nueva posición propuesta.
     * @return true si el cambio ha sido exitoso, false en caso contrario.
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
     * Realiza un entrenamiento que aumenta la motivación (vía superclase) e intenta cambiar de posición aleatoriamente.
     */
    @Override
    public void entrenament() {
        super.entrenament();
        Random random = new Random();
        String novaPos = this.posicion;
        while (novaPos.equals(this.posicion)) {
            novaPos = Posiciones[random.nextInt(Posiciones.length)];
        }
        canviDePosicion(novaPos);
    }
}

