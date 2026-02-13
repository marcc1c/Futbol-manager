package personas;

import java.util.ArrayList;

public class Equipos {
    private String nombre, añoFundacion, ciudad, nombreEstadio, nombrePresidente, entrenador;
    private ArrayList<Jugador> jugadores = new ArrayList<Jugador>();

    public Equipos() {
    }


    public Equipos(String nombre, String añoFundacion, String ciudad, String entrenador, ArrayList<Jugador> jugadores) {
        this.nombre = nombre;
        this.añoFundacion = añoFundacion;
        this.ciudad = ciudad;
        this.entrenador = entrenador;
        this.jugadores = jugadores;
    }

    public Equipos(String nombre, String añoFundacion, String ciudad, String nombreEstadio, String entrenador, ArrayList<Jugador> jugadores) {
        this.nombre = nombre;
        this.añoFundacion = añoFundacion;
        this.ciudad = ciudad;
        this.nombreEstadio = nombreEstadio;
        this.entrenador = entrenador;
        this.jugadores = jugadores;
    }

    public Equipos(String nombre, String añoFundacion, String ciudad, String entrenador, ArrayList<Jugador> jugadores, String nombreEstadio) {
        this.nombre = nombre;
        this.añoFundacion = añoFundacion;
        this.ciudad = ciudad;
        this.entrenador = entrenador;
        this.jugadores = jugadores;
        this.nombreEstadio = nombreEstadio;
    }

    public Equipos(String nombre, String añoFundacion, String ciudad, String nombreEstadio, String nombrePresidente, String entrenador, ArrayList<Jugador> jugadores) {
        this.nombre = nombre;
        this.añoFundacion = añoFundacion;
        this.ciudad = ciudad;
        this.nombreEstadio = nombreEstadio;
        this.nombrePresidente = nombrePresidente;
        this.entrenador = entrenador;
        this.jugadores = jugadores;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAñoFundacion() {
        return añoFundacion;
    }

    public void setAñoFundacion(String añoFundacion) {
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

    public String getEntrenador() {
        return entrenador;
    }

    public void setEntrenador(String entrenador) {
        this.entrenador = entrenador;
    }

    public ArrayList<Jugador> getJugadores() {
        return jugadores;
    }

    public void setJugadores(ArrayList<Jugador> jugadores) {
        this.jugadores = jugadores;
    }

    public double calcularMediana() {
        double mediaQualidadJugadores = 0;

        for (Jugador j : jugadores) {
            mediaQualidadJugadores += j.getQualidad();
        }

        return mediaQualidadJugadores / jugadores.size();
    }
}
