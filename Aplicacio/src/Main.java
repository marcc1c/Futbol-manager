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
        leerEquipos(listaEquipos, listaPersonas);
        char tipoUsuario = login(opcionesAdmin, opcionesGestorEquipos, opcionesUsuarioActual);


        boolean bucleMenuMain = true;
        while (bucleMenuMain) {
            String input = mostrarMenu(tipoUsuario, opcionesAdmin, opcionesGestorEquipos, opciones);
            bucleMenuMain = escogerOpcion(input, tipoUsuario, listaEquipos, listaPersonas);
        }
    }

    private static void leerJugadores(ArrayList<Personas> listaPersonas) {

        try (BufferedReader br = new BufferedReader(new FileReader("Aplicacio/src/archivosGuardado/mercat_fitxatges.txt"))) {
            String linea;

            while ((linea = br.readLine()) != null) {

                String[] dades = linea.split(";");

                String JoE = dades[0];

                String nombre = dades[1];
                String apellido = dades[2];
                String fechaNacimiento = dades[3];
                int nivelMotivacion = Integer.parseInt(dades[4]);
                double salario = Double.parseDouble(dades[5]);
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

    public static void leerEquipos(ArrayList<Equipos> listaEquipos, ArrayList<Personas> listaPersonas) {

        try (BufferedReader br = new BufferedReader(new FileReader("Aplicacio/src/archivosGuardado/guardarEquipos.txt"))) {
            String linea;

            while ((linea = br.readLine()) != null) {
                if (linea.isEmpty()) continue;

                String[] dades = linea.split(";");
                if (dades.length < 6) continue;

                String nombre = dades[0];
                int añoFundacion = Integer.parseInt(dades[1]);
                String ciudad = dades[2];
                String nombreEstadio = dades[3];
                String nombrePresidente = dades[4];
                String entrenadorNombre = dades[5];

                Entrenador objetoEntrenador = null;
                for (Personas persona : listaPersonas) {
                    if (persona instanceof Entrenador) {
                        if (entrenadorNombre.equals(persona.getNombre())) {
                            objetoEntrenador = (Entrenador) persona;
                        }
                    }
                }
                if (objetoEntrenador == null) objetoEntrenador = new Entrenador();

                Equipos e1 = new Equipos(nombre, añoFundacion, ciudad, nombreEstadio, nombrePresidente, objetoEntrenador);
                listaEquipos.add(e1);
            }

        } catch (IOException e) {
            System.out.println("Error al leer guardarEquipos.txt: " + e.getMessage());
            return;
        }

        for (Equipos equipos : listaEquipos) {
            try (BufferedReader brr = new BufferedReader(
                    new FileReader("Aplicacio/src/archivosGuardado/equipos/" + equipos.getNombre() + ".txt"))) {

                String lineaa;

                while ((lineaa = brr.readLine()) != null) {
                    if (lineaa.isEmpty()) continue;

                    String[] datos = lineaa.split(";");
                    if (datos.length < 2) continue;

                    String tipo = datos[0];

                    if (tipo.equals("E")) {
                        if (datos.length < 8) continue;

                        String nombreEntrenador = datos[1];
                        String apellidoEntrenador = datos[2];
                        String fechaNacimientoEntrenador = datos[3];
                        int nivelMotivacionEntrenador = Integer.parseInt(datos[4]);
                        double salarioEntrenador = Double.parseDouble(datos[5]);
                        int numTorneosEntrenador = Integer.parseInt(datos[6]);
                        boolean seleccionadorNacional = Boolean.parseBoolean(datos[7]);

                        Entrenador ent = new Entrenador(
                                nombreEntrenador,
                                apellidoEntrenador,
                                fechaNacimientoEntrenador,
                                nivelMotivacionEntrenador,
                                salarioEntrenador,
                                numTorneosEntrenador,
                                seleccionadorNacional
                        );

                        equipos.setEntrenador(ent);

                    } else if (tipo.equals("J")) {
                        if (datos.length < 9) continue;

                        String nombreJugador = datos[1];
                        String apellidoJugador = datos[2];
                        String fechaNacimiento = datos[3];
                        int nivelMotivacion = Integer.parseInt(datos[4]);
                        double sueldoAnualJugador = Double.parseDouble(datos[5]);
                        int dorsal = Integer.parseInt(datos[6]);
                        String posicion = datos[7];
                        int calidad = Integer.parseInt(datos[8]);

                        Jugador j1 = new Jugador(
                                nombreJugador,
                                apellidoJugador,
                                fechaNacimiento,
                                nivelMotivacion,
                                sueldoAnualJugador,
                                dorsal,
                                posicion,
                                calidad
                        );

                        equipos.getJugadores().add(j1);
    }
                }

            } catch (IOException e) {
                System.out.println("Error al leer el archivo del equipo " + equipos.getNombre() + ": " + e.getMessage());
            }
        }
    }

    public static void altaEquipo(ArrayList<Equipos> listaEquipos, ArrayList<Personas> listaPersonas) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Que equipo quieres dar de alta?");
        Entrenador objetoEntrenador = new Entrenador();

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

        boolean entrenadorCorrecto = false;
        while (!entrenadorCorrecto) {
            for (Personas personas : listaPersonas) {
                if (personas instanceof Entrenador)
                    System.out.println(personas + "\n");
            }
            System.out.println("Introduce el nombre del entrenador");
            String nombreEntrenador = scanner.nextLine();

            for (Personas personas : listaPersonas) {
                if (personas instanceof Entrenador && personas.getNombre().equalsIgnoreCase(nombreEntrenador)) {
                    objetoEntrenador = (Entrenador) personas;
                    entrenadorCorrecto = true;
                }
            }
            if (!entrenadorCorrecto) {
                System.out.println("Entrenador incorrecto");
            }
        }

        System.out.println("(Opcional) Introduce el nombre del presidente");
        String nombrePresidente = scanner.nextLine();
        System.out.println("(Opcional) Introduce el nombre del estadio del equipo");
        String nombreEstadio = scanner.nextLine();

        if (nombrePresidente != null && nombreEstadio != null) {
            Equipos e1 = new Equipos(nombre, añoFundacion, ciudad, nombreEstadio, nombrePresidente, objetoEntrenador);
            listaEquipos.add(e1);
        } else if (nombrePresidente != null && nombreEstadio.isEmpty()) {
            Equipos e1 = new Equipos(nombre, añoFundacion, ciudad, nombrePresidente, objetoEntrenador);
            listaEquipos.add(e1);
        } else if (nombrePresidente.isEmpty() && nombreEstadio != null) {
            Equipos e1 = new Equipos(nombre, añoFundacion, ciudad, nombreEstadio, objetoEntrenador);
            listaEquipos.add(e1);
        } else {
            Equipos e1 = new Equipos(nombre, añoFundacion, ciudad, objetoEntrenador);
            listaEquipos.add(e1);
        }
        listaPersonas.remove(objetoEntrenador);
        actualizarMercadoFichaje(listaPersonas);
        guardarEquipos(listaEquipos);
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
        double salarioAnual = scanner.nextInt();

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

    public static String mostrarMenu(char tipoUsuario, String[] opcionesAdmin, String[] opcionesGestorEquipos,
                                     HashMap<String, String> opcionesTotales) {
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

    public static boolean escogerOpcion(String input, char tipoUsuario, ArrayList<
            Equipos> listaEquipos, ArrayList<Personas> listaPersonas) {
        boolean salir = true;
        switch (input.toUpperCase()) {
            case "S":
                System.out.println("Saliendo del futbol manager");
                salir = false;
                break;
            case "VC":
                break;
            case "AE":
                altaEquipo(listaEquipos, listaPersonas);
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
                actualizarMercadoFichaje(listaPersonas);
                guardarEquipos(listaEquipos);
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
                        bajaEquipo(listaEquipos, equipo);
                        break;
                    case 2:
                        modificarPresidente(listaEquipos, equipo);
                        break;
                    case 3:
                        destituirEntrenador(listaEquipos, equipo);
                        break;
                    case 4:
                        ficharJugadorEntrenador(listaEquipos, listaPersonas, equipo);
                        break;
                }
            } else {
                System.out.println("Equipo no encontrado");
            }
        }

    }

    public static void ficharJugadorEntrenador(ArrayList<Equipos> listaEquipos, ArrayList<Personas> listaPersonas, Equipos equipo) {
        Scanner scanner = new Scanner(System.in);

        boolean fichado = false;
        String JoE = "";

        System.out.println("Quieres fichar un jugador (j) o un entrandor (e)");
        String entradaJoE = Validador.numero2("j", "e");

        if (entradaJoE.equalsIgnoreCase("j")) {
            for (Personas persona : listaPersonas) {
                if (persona instanceof Jugador) {
                    System.out.println(persona);
                    JoE = "Jugador";
                }
            }
        } else {
            for (Personas persona : listaPersonas) {
                if (persona instanceof Entrenador) {
                    System.out.println(persona);
                    JoE = "Entrenador";
                }
            }
        }

        System.out.println("A que " + JoE + " quieres fichar");
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Apellido: ");
        String apellido = scanner.nextLine();

        Personas personaABorrar = null;

        if (entradaJoE.equalsIgnoreCase("j")) {
            for (Personas personas : listaPersonas) {
                if (personas instanceof Jugador) {
                    if (nombre.equalsIgnoreCase(personas.getNombre()) && apellido.equalsIgnoreCase(personas.getApellido())) {
                        equipo.setJugadores((Jugador) personas);
                        personaABorrar = personas;
                        System.out.println("El jugador " + nombre + " ha sido fichado en " + equipo.getNombre());
                        fichado = true;
                    }
                }
            }
        } else {
            for (Personas personas : listaPersonas) {
                if (personas instanceof Entrenador) {
                    if (nombre.equalsIgnoreCase(personas.getNombre()) && apellido.equalsIgnoreCase(personas.getApellido())) {
                        listaPersonas.add(equipo.getEntrenador());
                        equipo.setEntrenador((Entrenador) personas);
                        personaABorrar = personas;
                        System.out.println("El entrenador " + nombre + " ahora es entrenador de " + equipo.getNombre());
                        fichado = true;
                    }
                }
            }
        }

        if (personaABorrar != null) {
            listaPersonas.remove(personaABorrar);
        }

        if (!fichado) {
            System.out.println("No se ha fichado a este fichaje, datos incorrectos");
        }
        actualizarMercadoFichaje(listaPersonas);
        guardarEquipos(listaEquipos);
    }

    public static void modificarPresidente(ArrayList<Equipos> listaEquipos, Equipos equipo) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Indica el nuevo presidente");
        String nuevoPresidente = scanner.nextLine();

        if (equipo.getNombrePresidente().equalsIgnoreCase(nuevoPresidente)) {
            System.out.println("Estas introduciendo el mismo presidente, no ha habido cambios");
        } else if (equipo.getNombrePresidente().isBlank()) {
            System.out.println("No existia presidente, el nuevo presidente serà " + nuevoPresidente);
        } else {
            System.out.println("Se ha cambiado el presidente de " + equipo.getNombrePresidente() + " a " + nuevoPresidente);

            equipo.setNombrePresidente(nuevoPresidente);
        }
    }

    public static void destituirEntrenador(ArrayList<Equipos> listaEquipos, Equipos equipo) {
        System.out.println("¿Seguro que quieres que tu entrenador deje de ser entrandor de " + equipo.getNombre() + "?" + "Si (s), No (n)");

        String entrada = Validador.numero2("s", "n");

        if (entrada.equalsIgnoreCase("s")) {

        }


    }

    public static void bajaEquipo(ArrayList<Equipos> listaEquipos, Equipos equipo) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("¿Estás seguro que quieres dar de baja al equipo actual? (s) sí, (n) no");
        String input = Validador.numero2("s", "n");

        if (input.equalsIgnoreCase("s")) {

            listaEquipos.remove(equipo);
            System.out.println("Equipo eliminado, guarda los cambios en el menú principal");
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

    public static void guardarEquipos(List<Equipos> listaEquipos) {

        String rutaCarpeta = "Aplicacio/src/archivosGuardado/guardarEquipos.txt";
        String rutaArchivoEquipos = "Aplicacio/src/archivosGuardado/equipos/";

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(rutaCarpeta))) {

            for (Equipos equipo : listaEquipos) {
                bw.write(equipo.getNombre() + ";" +
                        equipo.getAñoFundacion() + ";" +
                        equipo.getCiudad() + ";" +
                        equipo.getNombreEstadio() + ";" +
                        equipo.getNombrePresidente() + ";" +
                        equipo.getEntrenador().getNombre());
                bw.newLine();

                try (BufferedWriter bwJugadores = new BufferedWriter(new FileWriter(rutaArchivoEquipos + equipo.getNombre() + ".txt"))) {

                    bwJugadores.write(
                            "E;" +
                                    equipo.getEntrenador().getNombre() + ";" +
                                    equipo.getEntrenador().getApellido() + ";" +
                                    equipo.getEntrenador().getFechaNacimiento() + ";" +
                                    equipo.getEntrenador().getNivelMotivacion() + ";" +
                                    equipo.getEntrenador().getSueldoAnual() + ";" +
                                    equipo.getEntrenador().getNumTorneosGanados() + ";" +
                                    equipo.getEntrenador().isSeleccionadorNacional()
                    );
                    bwJugadores.newLine();

                    for (Jugador jugador : equipo.getJugadores()) {
                        bwJugadores.write(
                                "J;" +
                                        jugador.getNombre() + ";" +
                                        jugador.getApellido() + ";" +
                                        jugador.getFechaNacimiento() + ";" +
                                        jugador.getNivelMotivacion() + ";" +
                                        jugador.getSueldoAnual() + ";" +
                                        jugador.getDorsal() + ";" +
                                        jugador.getPosicion() + ";" +
                                        jugador.getQualidad()
                        );
                        bwJugadores.newLine();
                    }

                } catch (IOException e) {
                    System.out.println("Error al escribir el archivo del equipo " + equipo.getNombre() + ": " + e.getMessage());
                }
            }

        } catch (IOException e) {
            System.out.println("Error al escribir guardarEquipos.txt: " + e.getMessage());
        }
    }

    public static void actualizarMercadoFichaje(ArrayList<Personas> listaPersonas) {
        // Primero, limpia el archivo de datos antiguos, ya que lo sobrescribimos completamente
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("Aplicacio/src/archivosGuardado/mercat_fitxatges.txt"))) {

            // Escribimos los datos de todas las personas (jugadores y entrenadores)
            for (Personas persona : listaPersonas) {
                // Solo escribimos si el objeto no es nulo y si pertenece a Jugador o Entrenador
                if (persona instanceof Entrenador) {
                    bw.write(
                            "E;" +
                                    persona.getNombre() + ";" +
                                    persona.getApellido() + ";" +
                                    persona.getFechaNacimiento() + ";" +
                                    persona.getNivelMotivacion() + ";" +
                                    persona.getSueldoAnual() + ";" +
                                    ((Entrenador) persona).getNumTorneosGanados() + ";" +
                                    ((Entrenador) persona).isSeleccionadorNacional()
                    );
                    bw.newLine();
                } else if (persona instanceof Jugador) {
                    bw.write(
                            "J;" +
                                    persona.getNombre() + ";" +
                                    persona.getApellido() + ";" +
                                    persona.getFechaNacimiento() + ";" +
                                    persona.getNivelMotivacion() + ";" +
                                    persona.getSueldoAnual() + ";" +
                                    ((Jugador) persona).getDorsal() + ";" +
                                    ((Jugador) persona).getPosicion() + ";" +
                                    ((Jugador) persona).getQualidad()
                    );
                    bw.newLine();
                }
            }

            System.out.println("Archivo de mercado de fichajes actualizado correctamente.");
        } catch (IOException e) {
            System.out.println("Error al actualizar el archivo de mercado de fichajes: " + e.getMessage());
        }
    }
}