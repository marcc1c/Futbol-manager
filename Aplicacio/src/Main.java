import personas.Entrenador;
import personas.Equipos;
import personas.Jugador;
import personas.Personas;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {


        Personas p1 = new Personas("a", "b", "12121", 12, 200);
        Jugador j1 = new Jugador("a", "b", "12121", 12, 200, 12, "DEL", 99);
        Entrenador e1 = new Entrenador("a", "b", "12121", 12, 10110, 2, true);

        ArrayList<Personas> listaPersonas = new ArrayList<>();
        ArrayList<Equipos> listaEquipos = new ArrayList<>();


        String[] opcionesAdmin = new String[]{
                "VC", "AE", "AJ", "DA", "DJ", "DV", "SE", "DD", "S"
        };

        String[] opcionesGestorEquipos = new String[]{
                "VA", "GE", "DA", "DJ", "TJ", "DD", "S"
        };

        ArrayList<String> opcionesUsuarioActual = new ArrayList<>();


        leerJugadores(listaPersonas);
        leerEquipos(listaEquipos);
        char tipoUsuario = login(opcionesAdmin, opcionesGestorEquipos, opcionesUsuarioActual);
        boolean bucleMenuMain = true;
        for (Equipos equipo : listaEquipos) {
            System.out.println(equipo);
        }
        while (bucleMenuMain) {
            String input = mostrarMenu(tipoUsuario, opcionesUsuarioActual);
            escogerOpcion(input, tipoUsuario, listaEquipos, listaPersonas);
        }
    }

    private static void leerJugadores(ArrayList<Personas> listaPersonas) {

        try (BufferedReader br = new BufferedReader(new FileReader("Aplicacio/src/resources/mercat_fitxatges.txt"))) {
            String linea;

            while ((linea = br.readLine()) != null) {

                String[] dades = linea.split(";");

                String JoE = dades[0];

                String nombre = dades[1];
                String apellido = dades[2];
                String fechaNacimiento = dades[3];
                int nivelMotivacion = Integer.parseInt(dades[4]);
                int salario = Integer.parseInt(dades[5]);
                if (JoE.equals("J")) {
                    int dorsal = Integer.parseInt(dades[6]);
                    String posicion = dades[7];
                    int calidadTeorica = Integer.parseInt(dades[8]);
                    Jugador j1 = new Jugador(nombre, apellido, fechaNacimiento, nivelMotivacion, salario,
                            dorsal, posicion, calidadTeorica);
                    listaPersonas.add(j1);
                } else {
                    int torneosGanados = Integer.parseInt(dades[6]);
                    boolean seleccionadoNacional = Boolean.parseBoolean(dades[7]);
                    Entrenador e1 = new Entrenador(nombre, apellido, fechaNacimiento, nivelMotivacion, salario,
                            torneosGanados, seleccionadoNacional);
                    listaPersonas.add(e1);
                }
            }

        } catch (IOException e) {
            System.out.println("Error al leer el fichero: " + e.getMessage());
        }
    }

    private static void leerEquipos(ArrayList<Equipos> listaEquipos) {

        try (BufferedReader br = new BufferedReader(new FileReader("Aplicacio/src/resources/guardarEquipos.txt"))) {
            String linea;

            while ((linea = br.readLine()) != null) {

                String[] dades = linea.split(";");

                String nombre = dades[0];
                int añoFundacion = Integer.parseInt(dades[1]);
                String ciudad = dades[2];
                String nombreEstadio = (dades[3]);
                String nombrePresidente = (dades[4]);
                String entrenador = dades[5];
                Equipos e1 = new Equipos(nombre, añoFundacion, ciudad, nombreEstadio, nombrePresidente, entrenador);
                listaEquipos.add(e1);
            }
        } catch (IOException e) {
            System.out.println("Error al leer el fichero: " + e.getMessage());
        }
    }

    public static void altaEquipo(ArrayList<Equipos> listaEquipos) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Que equipo quieres dar de alta?");

        String input = "";
        boolean nombreRepetido = true;

        while (nombreRepetido) {
            input = scanner.nextLine();
            nombreRepetido = false;
            for (Equipos equipo : listaEquipos) {
                if (input.equalsIgnoreCase(equipo.getNombre())) {
                    System.out.println("Hay un equipo con ese nombre, introduzca otro");
                    nombreRepetido = true;
                }
            }
        }
        String nombre = input;
        System.out.println("Introduce el año de fundación");
        int añoFundacion = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Introduce la ciudad del equipo");
        String ciudad = scanner.nextLine();
        System.out.println("Introduce el nombre del entrenador");
        String nombreEntrenador = scanner.nextLine();
        System.out.println("(Opcional) Introduce el nombre del presidente");
        String nombrePresidente = scanner.nextLine();
        System.out.println("(Opcional) Introduce el nombre del estadio del equipo");
        String nombreEstadio = scanner.nextLine();

        if (nombrePresidente != null && nombreEstadio != null) {
            Equipos e1 = new Equipos(nombre, añoFundacion, ciudad, nombreEstadio, nombrePresidente, nombreEntrenador);
            listaEquipos.add(e1);
        } else if (nombrePresidente != null && nombreEstadio.isEmpty()) {
            Equipos e1 = new Equipos(nombre, añoFundacion, ciudad, nombrePresidente, nombreEntrenador);
            listaEquipos.add(e1);
        } else if (nombrePresidente.isEmpty() && nombreEstadio != null) {
            Equipos e1 = new Equipos(nombre, añoFundacion, ciudad, nombreEstadio, nombreEntrenador);
            listaEquipos.add(e1);
        } else {
            Equipos e1 = new Equipos(nombre, añoFundacion, ciudad, nombreEntrenador);
            listaEquipos.add(e1);
        }
    }

    public static void altaJugadorEntrenador(ArrayList<Personas> listaPersonas) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        System.out.println("Quieres dar de alta un jugador (j) o un entrandor (e)?");
        String JoE = Validador.numero2("j", "e");
        System.out.println("Introduce el nombre");
        String nombre = scanner.nextLine();
        System.out.println("Introduce el apellido");
        String apellido = scanner.nextLine();
        System.out.println("Introduce la fecha nacimiento");
        String fechaNacimiento = scanner.nextLine();
        System.out.println("Introduce la salario anual");
        int salarioAnual = scanner.nextInt();

        if (JoE.equalsIgnoreCase("j"
        )) {
            System.out.println("Introduce el dorsal");
            int dorsal = scanner.nextInt();
            System.out.println("Introduce su posición");
            String posicion = scanner.nextLine();
            Jugador j1 = new Jugador(nombre, apellido, fechaNacimiento, 5,
                    salarioAnual, dorsal, posicion, random.nextInt((99) + 1));
            listaPersonas.add(j1);
        } else {
            System.out.println("Introduce el numero de torneos ganados");
            int torneosGanados = scanner.nextInt();
            System.out.println("Introduce si es seleccionador nacinal");
            boolean seleccionadorNacional = scanner.nextBoolean();
            Entrenador e1 = new Entrenador(nombre, apellido, fechaNacimiento, 5,
                    salarioAnual, torneosGanados, seleccionadorNacional);
            listaPersonas.add(e1);
        }
        for (Personas listaPersona : listaPersonas) {
            System.out.println(listaPersona);
        }


    }

    public static char login(String[] opcionesAdmin, String[]
            opcionesGestorEquipos, ArrayList<String> opcionesUsuarioActual) {

        System.out.println("-".repeat(15) + "Iniciando login" + "-".repeat(15));
        System.out.println("¿Eres admin (a) o eres un gestor de equipo (g)?");
        String input = Validador.numero2("a", "g");

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

    public static void escogerOpcion(String input, char tipoUsuario, ArrayList<Equipos> listaEquipos, ArrayList<Personas> listaPersonas) {
        switch (input.toUpperCase()) {
            case "S":
                break;
            case "VC":
                break;
            case "AE":
                altaEquipo(listaEquipos);
                break;
            case "AJ":
                altaJugadorEntrenador(listaPersonas);
                break;
            case "DA":
                consultarDatosEquipo(listaEquipos);
                break;
            case "DJ":
                consultarJugadorEquipo(listaEquipos);
                break;
            case "DV":
                break;
            case "SE":
                break;
            case "DD":
                guardarDatos(listaEquipos);
                break;
            case "GE":
                gestionarMiEquipo();
                break;
            case "TJ":
                break;
            default:
                System.out.println("Valor incorrecto");
                break;
        }
    }

    public static void gestionarMiEquipo() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Team Manager:");
        System.out.println("1- Donar de baixa l'equip");
        System.out.println("2- Modificar president/a");
        System.out.println("3- Destituir entrenador/a");
        System.out.println("4- Fitxar jugador/a o entrenador/a");
        System.out.println("0- Sortir");

        int input = Validador.numerosInicioFinal(0, 4);

        switch (input) {
            case 1:
                bajaEquipo();
                break;
        }

    }

    public static void bajaEquipo() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Estas seguro que quieres dar de baja al equipo actual?");
        String input = Validador.numero2("s", "n");

        if (input.equalsIgnoreCase("s")) {
        } else {
            System.out.println("No se ha borrado el equipo de la liga");
        }
    }

    public static void consultarDatosEquipo(ArrayList<Equipos> listaEquipos) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Que equipo quieres consultar");

        String input = scanner.nextLine();
        for (Equipos equipo : listaEquipos) {
            if (equipo.getNombre().equalsIgnoreCase(input)) {
                System.out.println(equipo);
            } else {
                System.out.println("Equipo no encontrado");
            }
        }

    }

    public static void consultarJugadorEquipo(ArrayList<Equipos> listaEquipos) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Que equipo quieres consultar");
        String input = scanner.nextLine();

        for (Equipos equipo : listaEquipos) {
            if (equipo.getNombre().equalsIgnoreCase(input)) {

                System.out.println("Cual es el nombre del jugador");
                String nombreJugador = scanner.nextLine();
                System.out.println("Cual es el dorsal del jugador");
                int dorsaljugador = scanner.nextInt();

                for (Jugador jugador : equipo.getJugadores()) {
                    if (jugador.getNombre().equalsIgnoreCase(nombreJugador) && jugador.getDorsal() == dorsaljugador) {
                        System.out.println(jugador);
                    } else {
                        System.out.println("Jugador no encontrado");
                    }
                }
            } else {
                System.out.println("Equipo no encontrado");
            }
        }
    }

    public static void guardarDatos(ArrayList<Equipos> listaEquipos) {

        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("Aplicacio/src/resources/guardarEquipos.txt"));
            for (Equipos equipo : listaEquipos) {

                bw.write(equipo.guardarInfo());
                bw.write(System.lineSeparator());
            }
            bw.close();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
