import org.junit.jupiter.api.Test;
import personas.Entrenador;
import personas.Equipos;
import personas.Jugador;
import personas.Personas;

import javax.swing.*;
import java.util.ArrayList;
import java.io.ByteArrayInputStream;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @Test
    void testMediaEquipos() {

        Jugador j1 = new Jugador(
                "Pedri",
                "Gonzalez",
                "25/11/2002",
                9,
                2000000,
                8,
                "MIG",
                88
        );

        Jugador j2 = new Jugador(
                "Pepe",
                "Gonzalez",
                "25/11/2002",
                5,
                120000,
                3,
                "DAV",
                71
        );
        Entrenador e1 = new Entrenador(
                "Gonca",
                "Gonca",
                "25/11/2002",
                10,
                200000,
                3,
                true
        );

        Equipos eq1 = new Equipos("Equipo 1", 2020, "Barcelona", e1);
        eq1.setJugadores(j1);
        eq1.setJugadores(j2);

        assertEquals(String.format("%.2f", 79.5), eq1.calcularMediana());

    }

    @Test
    void testLeerMercadoFichajes() {

        ArrayList<Personas> listaPersonas = new ArrayList<>();
        ArrayList<Personas> listaVacia = new ArrayList<>();

        Main.leerMercadoFichajes(listaPersonas);

        assertNotEquals(listaPersonas, listaVacia);
    }

    @Test
    void testEscogerOpcion() {
        String input = "0";
        char tipoUsuario = 'a';
        ArrayList<Equipos> listaEquipos = new ArrayList<>();
        ArrayList<Personas> listaPersonas = new ArrayList<>();


        assertFalse(Main.escogerOpcion(input, tipoUsuario, listaEquipos, listaPersonas));

    }

    @Test
    void testCambiarPosicion() {

        Jugador j1 = new Jugador(
                "Pedri",
                "Gonzalez",
                "25/11/2002",
                9,
                2000000,
                8,
                "MIG",
                88
        );

        boolean loop = true;
        int intentos = 0;

        while (loop) {
            boolean haCamabido = j1.canviDePosicion("POR");
            if (haCamabido) {
                System.out.println("\nHas necesitado " + intentos + " intentos");
                loop = false;
            }
            intentos++;
        }
        assertEquals("POR", j1.getPosicion());

    }
}
