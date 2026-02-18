import personas.Entrenador;
import personas.Jugador;
import personas.Personas;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Personas p1 = new Personas("a", "b", "12121", 12, 200);
        Jugador j1 = new Jugador("a", "b", "12121", 12, 200, 12, "DEL", 99);
        Entrenador e1 = new Entrenador("a", "b", "12121", 12, 10110, 2, true);

        String[] opcionesAdmin = new String[]{
                "VC", "AE", "AJ", "DA", "DJ", "DV", "SE", "DD", "S"
        };

        String[] opcionesGestorEquipos = new String[]{
                "VA", "GE", "DA", "DJ", "TJ", "DD", "S"
        };

        ArrayList<String> opcionesUsuarioActual = new ArrayList<>();

        char tipoUsuario = login(opcionesAdmin, opcionesGestorEquipos, opcionesUsuarioActual);
        String input = mostrarMenu(tipoUsuario, opcionesUsuarioActual);
        escogerOpcion(input, tipoUsuario);

    }

    public static char login(String[] opcionesAdmin, String[] opcionesGestorEquipos, ArrayList<String> opcionesUsuarioActual) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("-".repeat(15) + "Iniciando login" + "-".repeat(15));
        System.out.println("¿Eres admin (a) o eres un gestor de equipo (g)?");
        scanner.hasNextLine();
        String input = scanner.nextLine();

        char tipousuario = input.charAt(0);
        if (input.equalsIgnoreCase("a")) {
            tipousuario = 'a';
            opcionesUsuarioActual.addAll(Arrays.asList(opcionesAdmin));
        } else if (input.equalsIgnoreCase("g")) {
            tipousuario = 'g';
            opcionesUsuarioActual.addAll(Arrays.asList(opcionesGestorEquipos));
        } else {

        }
        return tipousuario;
    }

    public static String mostrarMenu(char tipoUsuario, ArrayList<String> opcionesUsuarioActual) {
        String[] tipoMenu = {};
        String[] menuAdmin = {
                "Welcome to Politècnics Football Manager:",
                "VC- Veure classificació lliga actual 🏆",
                "AE- Donar d'alta equip",
                "AJ- Donar d'alta jugador/a o entrenador/a",
                "DA- Consultar dades equip",
                "DJ- Consultar dades jugador/a equip",
                "DV- Disputar nova lliga",
                "SE- Realitzar sessió entrenament (del mercat fitxatges)",
                "DD- Desar dades equips",
                "S- Sortir"
        };
        String[] menuGestorEquipos = {
                "Welcome to Politècnics Football Manager:",
                "VA- Veure classificació lliga actual 🏆",
                "GE- Gestionar el meu equip ⚽",
                "DA- Consultar dades equip",
                "DJ- Consultar dades jugador/a equip",
                "TJ- Transferir jugador/a",
                "DD- Desar dades equips",
                "S- Sortir"
        };

        if (tipoUsuario == 'a') {
            tipoMenu = menuAdmin;
        } else if (tipoUsuario == 'g') {
            tipoMenu = menuGestorEquipos;
        }

        for (String linea : tipoMenu) {
            System.out.println(linea);

        }
        return pedirinput(opcionesUsuarioActual);

    }

    public static String pedirinput(ArrayList<String> opcionesUsuario) {
        Scanner scanner = new Scanner(System.in);
        boolean valido = false;
        String input = "";

        while (!valido) {
            input = scanner.nextLine();

            boolean encontrado = false;

                for (String opcion : opcionesUsuario) {
                    if (opcion.equalsIgnoreCase(input)) {
                        encontrado = true;
                    }
            }
            if (encontrado) {
                valido = true;
            } else {
                System.out.println("Opción inválida");
            }
        }
        return input;
    }

    public static void escogerOpcion(String input, char tipoUsuario) {
        switch (input) {
            case "S":
                break;
            case "VC":
                break;
            case "AE":
                break;
            case "AJ":
                break;
            case "DA":
                break;
            case "DJ":
                break;
            case "DV":
                break;
            case "SE":
                break;
            case "DD":
                break;
            case "GE":
                getstionarMiEquipo();
                break;
            case "TJ":
                break;
            default:
                System.out.println("Valor incorrecto");
                break;
        }
    }

    public static void getstionarMiEquipo() {
        System.out.println("Team Manager:");
        System.out.println("1- Donar de baixa l'equip");
        System.out.println("2- Modificar president/a");
        System.out.println("3- Destituir entrenador/a");
        System.out.println("4- Fitxar jugador/a o entrenador/a");
        System.out.println("0- Sortir");

    }

}
