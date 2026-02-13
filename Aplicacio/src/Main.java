import personas.Entrenador;
import personas.Jugador;
import personas.Personas;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Personas p1 = new Personas("a", "b", "12121", 12, 200);
        Jugador j1 = new Jugador("a", "b", "12121", 12, 200, 12, "DEL", 99);
        Entrenador e1 = new Entrenador("a", "b", "12121", 12, 10110, 2, true);

        char tipoUsuario = login();
        String input = mostrarMenu(tipoUsuario);
        escogerOpcion(input, tipoUsuario);

    }

    public static char login() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("-".repeat(15) + "Iniciando login" + "-".repeat(15));
        System.out.println("¿Eres admin (a) o eres un gestor de equipo (g)?");
        scanner.hasNextLine();
        String input = scanner.nextLine();
        char tipousuario = input.charAt(0);
        if (input.equalsIgnoreCase("a")) {
            tipousuario = 'a';
        } else if (input.equalsIgnoreCase("g")) {
            tipousuario = 'g';
        } else {

        }
        return tipousuario;
    }

    public static String mostrarMenu(char tipoUsuario) {
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
        return pedirinput(tipoMenu.length - 2);

    }

    public static String pedirinput(int longitud) {
        Scanner scanner = new Scanner(System.in);
        boolean valido = false;
        String input = "";
/**
        while (!valido) {
            input = scanner.nextInt();
            if (input > 0 && input < longitud) {
                valido = true;
            } else {
                System.out.println("Valor incorrecto, intoduce entre 0 y " + longitud);
            }
        }
        **/
        input = scanner.nextLine();

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
        public static void getstionarMiEquipo () {
            System.out.println("Team Manager:");
            System.out.println("1- Donar de baixa l'equip");
            System.out.println("2- Modificar president/a");
            System.out.println("3- Destituir entrenador/a");
            System.out.println("4- Fitxar jugador/a o entrenador/a");
            System.out.println("0- Sortir");

        }

    }
