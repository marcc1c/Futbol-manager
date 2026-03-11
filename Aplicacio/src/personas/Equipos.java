package personas;

import java.util.ArrayList;

public class Equipos {
    private String nombre, ciudad, nombreEstadio, nombrePresidente;
    private int añoFundacion;
    private ArrayList<Jugador> jugadores = new ArrayList<Jugador>();
    private Entrenador entrenador;

    public Equipos() {
    }

    public Equipos(String nombre, int añoFundacion, String ciudad, Entrenador entrenador) {
        this.nombre = nombre;
        this.añoFundacion = añoFundacion;
        this.ciudad = ciudad;
        this.entrenador = entrenador;
    }

    public Equipos(String nombre, int añoFundacion, String ciudad, String nombreEstadio, Entrenador entrenador) {
        this.nombre = nombre;
        this.añoFundacion = añoFundacion;
        this.ciudad = ciudad;
        this.nombreEstadio = nombreEstadio;
        this.entrenador = entrenador;
    }

    public Equipos(String nombre, int añoFundacion, String ciudad, String nombreEstadio, String nombrePresidente, Entrenador entrenador) {
        this.nombre = nombre;
        this.añoFundacion = añoFundacion;
        this.ciudad = ciudad;
        this.nombreEstadio = nombreEstadio;
        this.nombrePresidente = nombrePresidente;
        this.entrenador = entrenador;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getAñoFundacion() {
        return añoFundacion;
    }

    public void setAñoFundacion(int añoFundacion) {
        this.añoFundacion = añoFundacion;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getNombreEstadio() {
        return nombreEstadio;
    }

    public void setNombreEstadio(String nombreEstadio) {
        this.nombreEstadio = nombreEstadio;
    }

    public String getNombrePresidente() {
        return nombrePresidente;
    }

    public void setNombrePresidente(String nombrePresidente) {
        this.nombrePresidente = nombrePresidente;
    }

    public Entrenador getEntrenador() {
        return entrenador;
    }

    public void setEntrenador(Entrenador entrenador) {
        this.entrenador = entrenador;
    }

    public ArrayList<Jugador> getJugadores() {
        return jugadores;
    }

    public void setJugadores(Jugador jugadores) {
        this.jugadores.add(jugadores);
    }

    public String calcularMediana() {
        double suma = 0;
        String mensaje;

        if (jugadores.isEmpty()) {
            mensaje = "No existen jugadores";
        } else {

            for (Jugador j : jugadores) {
                suma += j.getQualidad();
            }

            double media = suma / jugadores.size();
            mensaje = String.format("%.2f", media);
        }

        return mensaje;
    }

    @Override
    public String toString() {
        return "\n" + "=".repeat(35) +
                "\n" + nombre.toUpperCase() +
                "\n" + "=".repeat(35) +
                "\nAño de fundación : " + añoFundacion +
                "\nCiudad           : " + ciudad +
                "\nEstadio          : " + nombreEstadio +
                "\nPresidente       : " + nombrePresidente +
                "\nNúmero jugadores : " + jugadores.size() +
                "\nMedia calidad    : " + calcularMediana() +
                "\n\n" + entrenador;
    }
}
