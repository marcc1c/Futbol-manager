import personas.Entrenador;
import personas.Equipos;
import personas.Jugador;
import personas.Personas;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {


        HashMap<String, String> opciones = new HashMap<>();

        opciones.put("VC", "Veure classificació lliga actual 🏆");
        opciones.put("AE", "Donar d'alta equip");
        opciones.put("AJ", "Donar d'alta jugador/a o entrenador/a");
        opciones.put("DA", "Consultar dades equip");
        opciones.put("DJ", "Consultar dades jugador/a equip");
        opciones.put("DV", "Disputar nova lliga");
        opciones.put("SE", "Realitzar sessió entrenament (del mercat fitxatges)");
        opciones.put("DD", "Desar dades equips");
        opciones.put("S", "Sortir");
        opciones.put("VA", "Veure classificació lliga actual 🏆");
        opciones.put("GE", "Gestionar el meu equip ⚽");


        ArrayList<Personas> listaPersonasDisponibles = new ArrayList<>();
        ArrayList<Equipos> listaEquipos = new ArrayList<>();

        String[] opcionesAdmin = new String[]{
                "VC", "AE", "AJ", "DA", "DJ", "DV", "SE", "DD", "S"
        };
        String[] opcionesGestorEquipos = new String[]{
                "VA", "GE", "DA", "DJ", "TJ", "DD", "S"
        };

        ArrayList<String> opcionesUsuarioActual = new ArrayList<>();


        leerJugadores(listaPersonasDisponibles);
        leerEquipos(listaEquipos);
        char tipoUsuario = login(opcionesAdmin, opcionesGestorEquipos, opcionesUsuarioActual);


        boolean bucleMenuMain = true;
        while (bucleMenuMain) {
            String input = mostrarMenu(tipoUsuario, opcionesAdmin, opcionesGestorEquipos, opciones);
            bucleMenuMain = escogerOpcion(input, tipoUsuario, listaEquipos, listaPersonasDisponibles);
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
                for (int i = 0; i < listaEquipos.size(); i++) {
                }


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

    public static String mostrarMenu(char tipoUsuario, String[] opcionesAdmin, String[] opcionesGestorEquipos, HashMap<String, String> opcionesTotales) {
        String[] tipoMenu = {};

        if (tipoUsuario == 'a') {
            tipoMenu = opcionesAdmin;
        } else if (tipoUsuario == 'g') {
            tipoMenu = opcionesGestorEquipos;
        }

        for (String linea : tipoMenu) {
            if (opcionesTotales.containsKey(linea)) {
                System.out.println(linea + " " + opcionesTotales.get(linea));
            }
        }
        return Validador.array(tipoMenu);
    }

    public static boolean escogerOpcion(String input, char tipoUsuario, ArrayList<Equipos> listaEquipos, ArrayList<Personas> listaPersonas) {
        boolean salir = true;
        switch (input.toUpperCase()) {
            case "S":
                System.out.println("Saliendo del futbol manager");
                salir = false;
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
                gestionarMiEquipo(listaEquipos, listaPersonas);
                break;
            case "TJ":
                break;
            default:
                System.out.println("Valor incorrecto");
                break;
        }
        return salir;
    }

    public static void gestionarMiEquipo(ArrayList<Equipos> listaEquipos, ArrayList<Personas> listaPersonas) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> nuevaListaEquipos = new ArrayList<>();

        System.out.println("Que equipo queres gestionar");
        String nombreEquipoGestionar = scanner.nextLine();

        for (Equipos equipo : listaEquipos) {
            if (nombreEquipoGestionar.equalsIgnoreCase(equipo.getNombre())) {

                System.out.println("Team Manager:");
                System.out.println("1- Donar de baixa l'equip");
                System.out.println("2- Modificar president/a");
                System.out.println("3- Destituir entrenador/a");
                System.out.println("4- Fitxar jugador/a o entrenador/a");
                System.out.println("0- Sortir");

                int input = Validador.numerosInicioFinal(0, 4);

                switch (input) {
                    case 1:
                        bajaEquipo(listaEquipos, nombreEquipoGestionar);
                        break;
                    case 2:
                        modificarPresidente(listaEquipos, nombreEquipoGestionar);
                        break;
                    case 3:
                        break;
                    case 4:
                        ficharJugadorEntrenador(listaEquipos, listaPersonas, nombreEquipoGestionar);
                        break;
                }
            } else {
                System.out.println("Equipo no encontrado");
            }
        }

    }

    public static void ficharJugadorEntrenador(ArrayList<Equipos> listaEquipos, ArrayList<Personas> listaPersonas, String nombreEquipoGestionar) {
        Scanner scanner = new Scanner(System.in);

        for (Equipos equipo : listaEquipos) {
            if (equipo.getNombre().equalsIgnoreCase(nombreEquipoGestionar)) {
                System.out.println("Quieres fichar un jugador (j) o un entrandor (e)");
                String JoE = Validador.numero2("j", "e");
                if (JoE.equalsIgnoreCase("j")) {
                    for (Personas persona : listaPersonas) {
                        if (persona instanceof Jugador) {
                            System.out.println(persona);
                        } else {
                            System.out.println(persona);
                        }
                    }
                }

            }
        }

    }

    public static void modificarPresidente(ArrayList<Equipos> listaEquipos, String nombreEquipoGestionar) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Indica el nuevo presidente");
        String nuevoPresidente = scanner.nextLine();

        for (Equipos equipo : listaEquipos) {
            if (equipo.getNombre().equalsIgnoreCase(nombreEquipoGestionar)) {
                if (equipo.getNombrePresidente().equalsIgnoreCase(nuevoPresidente)) {
                    System.out.println("Estas introduciendo el mismo presidente, no ha habido cambios");
                } else if (equipo.getNombrePresidente().isBlank()) {
                    System.out.println("No existia presidente, el nuevo presidente serà " + nuevoPresidente);
                } else {
                    System.out.println("Se ha cambiado el presidente de " + equipo.getNombrePresidente() + " a " + nuevoPresidente);
                }
                equipo.setNombrePresidente(nuevoPresidente);
            }
        }
    }

    public static void bajaEquipo(ArrayList<Equipos> listaEquipos, String nombreEquipoGestionar) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("¿Estás seguro que quieres dar de baja al equipo actual? (s) sí, (n) no");
        String input = Validador.numero2("s", "n");

        if (input.equalsIgnoreCase("s")) {
            boolean encontrado = false;

            for (int i = 0; i < listaEquipos.size(); i++) {
                Equipos equipo = listaEquipos.get(i);
                if (nombreEquipoGestionar.equalsIgnoreCase(equipo.getNombre()) && !encontrado) {
                    listaEquipos.remove(i);
                    System.out.println("Equipo eliminado, guarda los cambios en el menú principal");
                    encontrado = true;
                }
            }

            if (!encontrado) {
                System.out.println("Equipo no encontrado");
            }
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
                for (Jugador jugador : equipo.getJugadores()) {
                    bw.write(jugador.getNombre() + ";" + jugador.getDorsal());
                }

                bw.write(System.lineSeparator());
            }
            bw.close();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}