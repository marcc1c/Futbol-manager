import personas.Entrenador;
import personas.Equipos;
import personas.Jugador;
import personas.Lliga;
import personas.Personas;

import java.io.*;
import java.util.*;


/**
 * Clase principal que gestiona el flujo del programa Football Manager.
 * Permite realizar login, mostrar menús y ejecutar las diversas opciones de gestión de equipos y jugadores.
 */
public class Main {

    /**
     * Punto de entrada principal de la aplicación.
     * @param args Argumentos de la línea de comandos (no utilizados).
     */
    public static void main(String[] args) {

        ArrayList<Personas> listaPersonas = new ArrayList<>();
        ArrayList<Equipos> listaEquipos = new ArrayList<>();
        Lliga lligaActual = null;

        leerMercadoFichajes(listaPersonas);
        leerEquipos(listaEquipos, listaPersonas);

        char tipoUsuario = login();

        boolean bucleMenuMain = true;
        while (bucleMenuMain) {
            mostrarMenu(tipoUsuario);
            String input = leerOpcionMenu(tipoUsuario);
            bucleMenuMain = escogerOpcion(input, tipoUsuario, listaEquipos, listaPersonas);

            if (input.equalsIgnoreCase("DV") || input.equalsIgnoreCase("6")) {
            }
        }
    }


    /**
     * Gestiona el proceso de login para determinar si el usuario es administrador o gestor.
     * @return El carácter que identifica el tipo de usuario ('a' para admin, 'g' para gestor).
     */
    public static char login() {
        System.out.println("-".repeat(15) + " Iniciant login " + "-".repeat(15));
        System.out.println("Ets admin (a) o ets un gestor d'equip (g)?");
        String input = Validador.numero2("a", "g");
        return input.charAt(0);
    }

    /**
     * Muestra el menú principal de la aplicación según el tipo de usuario.
     * @param tipoUsuario El tipo de usuario logueado ('a' o 'g').
     */
    public static void mostrarMenu(char tipoUsuario) {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("  Welcome to Politècnics Football Manager");
        System.out.println("=".repeat(50));

        if (tipoUsuario == 'a') {
            System.out.println("  1- Veure classificació lliga actual");
            System.out.println("  2- Donar d'alta equip");
            System.out.println("  3- Donar d'alta jugador/a o entrenador/a");
            System.out.println("  4- Consultar dades equip");
            System.out.println("  5- Consultar dades jugador/a equip");
            System.out.println("  6- Disputar nova lliga");
            System.out.println("  7- Realitzar sessió entrenament");
            System.out.println("  8- Desar dades equips");
            System.out.println("  0- Sortir");
        } else {
            System.out.println("  1- Veure classificació lliga actual");
            System.out.println("  2- Gestionar el meu equip");
            System.out.println("  3- Consultar dades equip");
            System.out.println("  4- Consultar dades jugador/a equip");
            System.out.println("  5- Transferir jugador/a");
            System.out.println("  6- Desar dades equips");
            System.out.println("  0- Sortir");
        }
        System.out.println("=".repeat(50));
        System.out.print("Escull una opció: ");
    }


    /**
     * Lee y valida la opción seleccionada por el usuario en el menú.
     * @param tipoUsuario El tipo de usuario logueado.
     * @return La opción elegida como String validada.
     */
    public static String leerOpcionMenu(char tipoUsuario) {
        Scanner scanner = new Scanner(System.in);
        String[] opcionesAdmin = {"0", "1", "2", "3", "4", "5", "6", "7", "8"};
        String[] opcionesGestor = {"0", "1", "2", "3", "4", "5", "6"};

        if (tipoUsuario == 'a') {
            return Validador.array(opcionesAdmin);
        } else {
            return Validador.array(opcionesGestor);
        }
    }


    /**
     * Ejecuta la lógica correspondiente a la opción elegida por el usuario.
     * @param input La opción introducida por el usuario.
     * @param tipoUsuario El tipo de usuario logueado.
     * @param listaEquipos Lista global de equipos.
     * @param listaPersonas Lista global de personas (mercado de fichajes).
     * @return true si el programa debe continuar, false para salir.
     */
    public static boolean escogerOpcion(String input, char tipoUsuario,
                                        ArrayList<Equipos> listaEquipos,
                                        ArrayList<Personas> listaPersonas) {
        if (tipoUsuario == 'a') {
            switch (input) {
                case "0":
                    System.out.println("Sortint del Football Manager. Fins aviat!");
                    return false;
                case "1":
                    Lliga lligaCarregada = Lliga.cargarLliga(listaEquipos);
                    if (lligaCarregada != null) {
                        lligaCarregada.mostrarClasificacion();
                    }
                    break;
                case "2":
                    altaEquipo(listaEquipos, listaPersonas);
                    break;
                case "3":
                    altaJugadorEntrenador(listaPersonas);
                    break;
                case "4":
                    consultarDatosEquipo(listaEquipos);
                    break;
                case "5":
                    consultarJugadorEquipo(listaEquipos);
                    break;
                case "6":
                    disputarNovaLliga(listaEquipos);
                    break;
                case "7":
                    sesionEntrenamiento(listaPersonas);
                    break;
                case "8":
                    actualizarMercadoFichaje(listaPersonas);
                    guardarEquipos(listaEquipos);
                    System.out.println("Dades desades correctament.");
                    break;
                default:
                    System.out.println("Opció incorrecta.");
                    break;
            }
        } else {
            switch (input) {
                case "0":
                    System.out.println("Sortint del Football Manager. Fins aviat!");
                    return false;
                case "1":
                    Lliga lligaCarregadaG = Lliga.cargarLliga(listaEquipos);
                    if (lligaCarregadaG != null) {
                        lligaCarregadaG.mostrarClasificacion();
                    }
                    break;
                case "2":
                    gestionarMiEquipo(listaEquipos, listaPersonas);
                    break;
                case "3":
                    consultarDatosEquipo(listaEquipos);
                    break;
                case "4":
                    consultarJugadorEquipo(listaEquipos);
                    break;
                case "5":
                    transferirJugador(listaEquipos);
                    break;
                case "6":
                    actualizarMercadoFichaje(listaPersonas);
                    guardarEquipos(listaEquipos);
                    System.out.println("Dades desades correctament.");
                    break;
                default:
                    System.out.println("Opció incorrecta.");
                    break;
            }
        }
        return true;
    }

    /**
     * Inicia el proceso para disputar una nueva liga.
     * @param listaEquipos Lista de todos los equipos disponibles.
     * @return El objeto Lliga creado y disputado.
     */
    public static Lliga disputarNovaLliga(ArrayList<Equipos> listaEquipos) {
        Scanner scanner = new Scanner(System.in);

        if (listaEquipos.size() < 2) {
            System.out.println("Calen almenys 2 equips per disputar una lliga.");
            return null;
        }

        System.out.println("Introdueix el nom de la nova lliga:");
        String nomLliga = scanner.nextLine();

        System.out.println("Quants equips hi participaran? (màxim " + listaEquipos.size() + ")");
        int numEquipos = Validador.numerosInicioFinal(2, listaEquipos.size());

        Lliga novaLliga = new Lliga(nomLliga, numEquipos);

        System.out.println("\nEquips disponibles:");
        for (Equipos equipo : listaEquipos) {
            System.out.println("  - " + equipo.getNombre());
        }

        ArrayList<String> nomesAfegits = new ArrayList<>();

        for (int i = 0; i < numEquipos; i++) {
            System.out.println("Introdueix el nom de l'equip " + (i + 1) + ":");
            boolean trobat = false;

            while (!trobat) {
                String nomEquip = scanner.nextLine();

                Equipos equipTrobat = null;
                for (Equipos equip : listaEquipos) {
                    if (equip.getNombre().equalsIgnoreCase(nomEquip)) {
                        equipTrobat = equip;
                    }
                }

                if (equipTrobat == null) {
                    System.out.println("Equip no trobat. Torna a introduir un nom vàlid:");
                } else {
                    boolean jaAfegit = false;
                    for (String nom : nomesAfegits) {
                        if (nom.equalsIgnoreCase(nomEquip)) {
                            jaAfegit = true;
                        }
                    }

                    if (jaAfegit) {
                        System.out.println("Aquest equip ja ha estat afegit a la lliga. Tria un altre:");
                    } else {
                        novaLliga.afegirEquip(equipTrobat);
                        nomesAfegits.add(equipTrobat.getNombre());
                        System.out.println("Equip " + equipTrobat.getNombre() + " afegit a la lliga.");
                        trobat = true;
                    }
                }
            }
        }

        novaLliga.disputarTodosLosPartidos();
        novaLliga.mostrarClasificacion();

        Equipos maxGoleador = novaLliga.equipoMasGoleador();
        Equipos mesGolesContra = novaLliga.equipoMenosGoleador();
        if (maxGoleador != null) {
            System.out.println("Equip més golejador: " + maxGoleador.getNombre());
        }
        if (mesGolesContra != null) {
            System.out.println("Equip amb més gols en contra: " + mesGolesContra.getNombre());
        }

        return novaLliga;
    }


    /**
     * Realiza una sesión de entrenamiento para todas las personas en el mercado de fichajes.
     * @param listaPersonas Lista de personas del mercado de fichajes.
     */
    public static void sesionEntrenamiento(ArrayList<Personas> listaPersonas) {
        if (listaPersonas.isEmpty()) {
            System.out.println("No hi ha cap jugador ni entrenador al mercat de fitxatges.");
            return;
        }

        System.out.println("\n--- Iniciant sessió d'entrenament del mercat de fitxatges ---");


        for (Personas persona : listaPersonas) {
            persona.entrenament();
        }
        System.out.println("--- Sessió d'entrenament finalitzada ---\n");
    }


    /**
     * Gestiona la transferencia de un jugador entre dos equipos.
     * @param listaEquipos Lista global de equipos.
     */
    public static void transferirJugador(ArrayList<Equipos> listaEquipos) {
        Scanner scanner = new Scanner(System.in);

        if (listaEquipos.size() < 2) {
            System.out.println("Calen almenys 2 equips per fer una transferència.");
            return;
        }

        System.out.println("\nEquips disponibles:");
        for (Equipos equip : listaEquipos) {
            System.out.println("  - " + equip.getNombre());
        }

        System.out.println("Introdueix el nom de l'equip d'ORIGEN:");
        Equipos equipOrigen = null;
        while (equipOrigen == null) {
            String nomOrigen = scanner.nextLine();
            for (Equipos equip : listaEquipos) {
                if (equip.getNombre().equalsIgnoreCase(nomOrigen)) {
                    equipOrigen = equip;
                }
            }
            if (equipOrigen == null) {
                System.out.println("Equip no trobat. Torna a introduir el nom:");
            }
        }

        if (equipOrigen.getJugadores().isEmpty()) {
            System.out.println("L'equip " + equipOrigen.getNombre() + " no té cap jugador.");
            return;
        }

        System.out.println("Introdueix el nom de l'equip de DESTÍ:");
        Equipos equipDesti = null;
        while (equipDesti == null) {
            String nomDesti = scanner.nextLine();
            if (nomDesti.equalsIgnoreCase(equipOrigen.getNombre())) {
                System.out.println("L'equip de destí no pot ser el mateix que el d'origen. Torna a introduir:");
                continue;
            }
            for (Equipos equip : listaEquipos) {
                if (equip.getNombre().equalsIgnoreCase(nomDesti)) {
                    equipDesti = equip;
                }
            }
            if (equipDesti == null) {
                System.out.println("Equip no trobat. Torna a introduir el nom:");
            }
        }

        System.out.println("\nJugadors de " + equipOrigen.getNombre() + ":");
        for (Jugador j : equipOrigen.getJugadores()) {
            System.out.println("  Nom: " + j.getNombre() + " " + j.getApellido() + " | Dorsal: " + j.getDorsal());
        }

        System.out.print("Nom del jugador a transferir: ");
        String nomJugador = scanner.nextLine();
        System.out.print("Dorsal del jugador a transferir: ");
        int dorsalJugador = Validador.numerosInicioFinal(0, 99);

        Jugador jugadorATransferir = null;
        for (Jugador j : equipOrigen.getJugadores()) {
            if (j.getNombre().equalsIgnoreCase(nomJugador) && j.getDorsal() == dorsalJugador) {
                jugadorATransferir = j;
            }
        }

        if (jugadorATransferir == null) {
            System.out.println("No s'ha trobat cap jugador amb aquest nom i dorsal a " + equipOrigen.getNombre() + ".");
            return;
        }

        boolean dorsalOcupat = true;
        int nouDorsal = jugadorATransferir.getDorsal();

        while (dorsalOcupat) {
            dorsalOcupat = false;
            for (Jugador j : equipDesti.getJugadores()) {
                if (j.getDorsal() == nouDorsal) {
                    dorsalOcupat = true;
                }
            }
            if (dorsalOcupat) {
                System.out.println("El dorsal " + nouDorsal + " ja està ocupat a " + equipDesti.getNombre() + ". Introdueix un nou dorsal:");
                nouDorsal = Validador.numerosInicioFinal(0, 99);
            }
        }

        jugadorATransferir.setDorsal(nouDorsal);
        equipOrigen.getJugadores().remove(jugadorATransferir);
        equipDesti.setJugadores(jugadorATransferir);

        System.out.println("Transferència completada! " + jugadorATransferir.getNombre() + " " +
                jugadorATransferir.getApellido() + " ara juga a " + equipDesti.getNombre() +
                " amb el dorsal " + nouDorsal + ".");
    }

    /**
     * Dóna d'alta un nou equip a l'aplicació.
     * Es comprova que el nom no estigui repetit.
     * Es demana tota la informació necessària, incloent els camps opcionals (estadi i president).
     *
     * @param listaEquipos  Llista d'equips de l'aplicació
     * @param listaPersonas Llista del mercat de fitxatges
     */
    public static void altaEquipo(ArrayList<Equipos> listaEquipos, ArrayList<Personas> listaPersonas) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Quin equip vols donar d'alta? Introdueix el nom:");
        String nombre = "";
        boolean nombreRepetido = true;
        while (nombreRepetido) {
            nombre = scanner.nextLine();
            nombreRepetido = false;
            for (Equipos equipo : listaEquipos) {
                if (nombre.equalsIgnoreCase(equipo.getNombre())) {
                    System.out.println("Ja existeix un equip amb aquest nom. Introdueix un altre:");
                    nombreRepetido = true;
                }
            }
        }

        System.out.println("Introdueix l'any de fundació:");
        int añoFundacion = Validador.numerosInicioFinal(-2000, 3000);

        System.out.println("Introdueix la ciutat de l'equip:");
        String ciudad = scanner.nextLine();

        System.out.println("Vols indicar el nom de l'estadi? (s/n)");
        String veuEstadi = Validador.numero2("s", "n");
        String nombreEstadio = "";
        if (veuEstadi.equalsIgnoreCase("s")) {
            System.out.println("Introdueix el nom de l'estadi:");
            nombreEstadio = scanner.nextLine();
        }

        System.out.println("Vols indicar el nom del/la president/a? (s/n)");
        String veuPresident = Validador.numero2("s", "n");
        String nombrePresidente = "";
        if (veuPresident.equalsIgnoreCase("s")) {
            System.out.println("Introdueix el nom del/la president/a:");
            nombrePresidente = scanner.nextLine();
        }

        Entrenador objetoEntrenador = new Entrenador();
        boolean entrenadorCorrecto = false;

        boolean hiHaEntrenadors = false;
        for (Personas personas : listaPersonas) {
            if (personas instanceof Entrenador) hiHaEntrenadors = true;
        }

        if (hiHaEntrenadors) {
            System.out.println("\nEntrenadors disponibles al mercat:");
            for (Personas personas : listaPersonas) {
                if (personas instanceof Entrenador) {
                    System.out.println("  - " + personas.getNombre() + " " + personas.getApellido());
                }
            }
            System.out.println("Introdueix el nom de l'entrenador:");
            while (!entrenadorCorrecto) {
                String nombreEntrenador = scanner.nextLine();
                for (Personas personas : listaPersonas) {
                    if (personas instanceof Entrenador && personas.getNombre().equalsIgnoreCase(nombreEntrenador)) {
                        objetoEntrenador = (Entrenador) personas;
                        entrenadorCorrecto = true;
                    }
                }
                if (!entrenadorCorrecto) {
                    System.out.println("Entrenador no trobat. Torna a introduir el nom:");
                }
            }
        } else {
            System.out.println("No hi ha entrenadors al mercat. L'equip es crearà sense entrenador.");
        }

        Equipos nouEquip = new Equipos(nombre, añoFundacion, ciudad, nombreEstadio, nombrePresidente, objetoEntrenador);
        listaEquipos.add(nouEquip);

        if (entrenadorCorrecto) {
            listaPersonas.remove(objetoEntrenador);
            actualizarMercadoFichaje(listaPersonas);
        }

        guardarEquipos(listaEquipos);
        System.out.println("Equip " + nombre + " donat d'alta correctament!");
    }

    /**
     * Dóna d'alta un nou jugador o entrenador al mercat de fitxatges.
     * La qualitat del jugador es genera aleatòriament (entre 30 i 100).
     * La motivació inicial és sempre 5.
     *
     * @param listaPersonas Llista del mercat de fitxatges
     */
    /**
     * Inicia el proceso para dar de alta un nuevo jugador o entrenador en el mercado de fichajes.
     * @param listaPersonas Lista del mercado de fichajes.
     */
    public static void altaJugadorEntrenador(ArrayList<Personas> listaPersonas) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        System.out.println("Vols donar d'alta un jugador (j) o un entrenador (e)?");
        String JoE = Validador.numero2("j", "e");

        System.out.println("Introdueix el nom:");
        String nombre = scanner.nextLine();
        System.out.println("Introdueix el cognom:");
        String apellido = scanner.nextLine();
        System.out.println("Introdueix la data de naixement (dd/mm/aaaa):");
        String fechaNacimiento = scanner.nextLine();

        System.out.println("Introdueix el salari anual:");
        double salarioAnual = Validador.validarDouble();

        if (JoE.equalsIgnoreCase("j")) {
            System.out.println("Introdueix el dorsal (0-99):");
            int dorsal = Validador.numerosInicioFinal(0, 99);

            System.out.println("Introdueix la posició: Portero (POR), Defensa (DEF), Migcampista (MIG), Davanter (DAV)");
            String posicion = Validador.array("POR", "DEF", "MIG", "DAV");

            int qualitat = 30 + random.nextInt(71);

            Jugador j1 = new Jugador(nombre, apellido, fechaNacimiento, 5, salarioAnual, dorsal, posicion, qualitat);
            listaPersonas.add(j1);
            System.out.println("Jugador " + nombre + " " + apellido + " donat d'alta al mercat de fitxatges!");

        } else {
            System.out.println("Introdueix el nombre de tornejos guanyats:");
            int torneosGanados = Validador.numerosInicioFinal(0, 999);

            System.out.println("És seleccionador nacional? (si/no)");
            String sn = Validador.numero2("si", "no");
            boolean seleccionadorNacional = sn.equalsIgnoreCase("si");

            Entrenador e1 = new Entrenador(nombre, apellido, fechaNacimiento, 5,
                    salarioAnual, torneosGanados, seleccionadorNacional);
            listaPersonas.add(e1);
            System.out.println("Entrenador " + nombre + " " + apellido + " donat d'alta al mercat de fitxatges!");
        }

        actualizarMercadoFichaje(listaPersonas);
    }


    /**
     * Muestra por pantalla los datos de un equipo seleccionado.
     * @param listaEquipos Lista global de equipos.
     */
    public static void consultarDatosEquipo(ArrayList<Equipos> listaEquipos) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Quin equip vols consultar? Equips disponibles:");
        for (Equipos eq : listaEquipos) {
            System.out.println("  - " + eq.getNombre());
        }
        System.out.print("Nom de l'equip: ");
        String input = scanner.nextLine();

        boolean trobat = false;
        for (Equipos equipo : listaEquipos) {
            if (equipo.getNombre().equalsIgnoreCase(input)) {
                System.out.println(equipo);
                if (equipo.getJugadores().isEmpty()) {
                    System.out.println("L'equip no té jugadors.");
                }
                trobat = true;
            }
        }

        if (!trobat) {
            System.out.println("Equip no trobat. Tornant al menú principal.");
        }
    }



    /**
     * Muestra los datos detallados de un jugador específico de un equipo.
     * @param listaEquipos Lista global de equipos.
     */
    public static void consultarJugadorEquipo(ArrayList<Equipos> listaEquipos) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Quin equip vols consultar?");
        for (Equipos eq : listaEquipos) {
            System.out.println("  - " + eq.getNombre());
        }
        System.out.print("Nom de l'equip: ");
        String inputEquip = scanner.nextLine();

        Equipos equipoTrobat = null;
        for (Equipos equipo : listaEquipos) {
            if (equipo.getNombre().equalsIgnoreCase(inputEquip)) {
                equipoTrobat = equipo;
            }
        }

        if (equipoTrobat == null) {
            System.out.println("Equip no trobat. Tornant al menú principal.");
            return;
        }

        if (equipoTrobat.getJugadores().isEmpty()) {
            System.out.println("L'equip " + equipoTrobat.getNombre() + " no té jugadors.");
            return;
        }

        System.out.println("\nJugadors de " + equipoTrobat.getNombre() + ":");
        for (Jugador j : equipoTrobat.getJugadores()) {
            System.out.println("  Nom: " + j.getNombre() + " " + j.getApellido() + " | Dorsal: " + j.getDorsal());
        }

        System.out.print("Nom del jugador: ");
        String nomJugador = scanner.nextLine();
        System.out.print("Dorsal del jugador: ");
        int dorsalJugador = Validador.numerosInicioFinal(0, 99);

        boolean jugadorTrobat = false;
        for (Jugador jugador : equipoTrobat.getJugadores()) {
            if (jugador.getNombre().equalsIgnoreCase(nomJugador) && jugador.getDorsal() == dorsalJugador) {
                System.out.println(jugador);
                jugadorTrobat = true;
            }
        }

        if (!jugadorTrobat) {
            System.out.println("Jugador no trobat. Tornant al menú principal.");
        }
    }

    /**
     * Accede al submenú de gestión de un equipo específico para un gestor.
     * @param listaEquipos Lista global de equipos.
     * @param listaPersonas Lista del mercado de fichajes.
     */
    public static void gestionarMiEquipo(ArrayList<Equipos> listaEquipos, ArrayList<Personas> listaPersonas) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\nQuin equip vols gestionar?");
        for (Equipos eq : listaEquipos) {
            System.out.println("  - " + eq.getNombre());
        }
        System.out.print("Nom de l'equip: ");
        String nomEquip = scanner.nextLine();

        Equipos equipo = null;
        for (Equipos eq : listaEquipos) {
            if (eq.getNombre().equalsIgnoreCase(nomEquip)) {
                equipo = eq;
            }
        }

        if (equipo == null) {
            System.out.println("Equip no trobat. Tornant al menú principal.");
            return;
        }

        System.out.println("\n" + "=".repeat(40));
        System.out.println("  Team Manager - " + equipo.getNombre());
        System.out.println("=".repeat(40));
        System.out.println("  1- Donar de baixa l'equip");
        System.out.println("  2- Modificar president/a");
        System.out.println("  3- Destituir entrenador/a");
        System.out.println("  4- Fitxar jugador/a o entrenador/a");
        System.out.println("  0- Sortir");
        System.out.println("=".repeat(40));
        System.out.print("Escull una opció: ");

        int input = Validador.numerosInicioFinal(0, 4);

        switch (input) {
            case 0:
                System.out.println("Tornant al menú principal.");
                break;
            case 1:
                bajaEquipo(listaEquipos, equipo, listaPersonas);
                break;
            case 2:
                modificarPresidente(equipo);
                break;
            case 3:
                destituirEntrenador(equipo, listaPersonas);
                break;
            case 4:
                ficharJugadorEntrenador(listaEquipos, listaPersonas, equipo);
                break;
        }
    }


    /**
     * Elimina un equipo del sistema, devolviendo sus integrantes al mercado de fichajes.
     * @param listaEquipos Lista global de equipos.
     * @param equipo El equipo a dar de baja.
     * @param listaPersonas Lista del mercado de fichajes.
     */
    public static void bajaEquipo(ArrayList<Equipos> listaEquipos, Equipos equipo, ArrayList<Personas> listaPersonas) {
        System.out.println("Estàs segur que vols donar de baixa l'equip " + equipo.getNombre() + "? (s/n)");
        String input = Validador.numero2("s", "n");

        if (input.equalsIgnoreCase("s")) {
            for (Jugador j : equipo.getJugadores()) {
                listaPersonas.add(j);
            }
            if (equipo.getEntrenador() != null && equipo.getEntrenador().getNombre() != null) {
                listaPersonas.add(equipo.getEntrenador());
            }
            listaEquipos.remove(equipo);
            guardarEquipos(listaEquipos);
            actualizarMercadoFichaje(listaPersonas);
            System.out.println("Equip " + equipo.getNombre() + " eliminat correctament.");
        } else {
            System.out.println("Operació cancel·lada.");
        }
    }


    /**
     * Modifica el nombre del presidente de un equipo.
     * @param equipo El equipo a modificar.
     */
    public static void modificarPresidente(Equipos equipo) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Indica el nom del nou/nova president/a:");
        String nouPresident = scanner.nextLine();

        if (equipo.getNombrePresidente() == null || equipo.getNombrePresidente().isBlank()) {
            System.out.println("L'equip no tenia president/a. El nou president és: " + nouPresident);
            equipo.setNombrePresidente(nouPresident);
        } else if (equipo.getNombrePresidente().equalsIgnoreCase(nouPresident)) {
            System.out.println("Estàs introduint el mateix president/a, no hi ha hagut canvis.");
        } else {
            System.out.println("S'ha canviat el/la president/a de " + equipo.getNombrePresidente() + " a " + nouPresident + ".");
            equipo.setNombrePresidente(nouPresident);
        }
    }


    /**
     * Destituye al entrenador actual de un equipo y lo envía al mercado de fichajes.
     * @param equipo El equipo del cual se destituye al entrenador.
     * @param listaPersonas Lista del mercado de fichajes.
     */
    public static void destituirEntrenador(Equipos equipo, ArrayList<Personas> listaPersonas) {
        if (equipo.getEntrenador() == null || equipo.getEntrenador().getNombre() == null) {
            System.out.println("L'equip " + equipo.getNombre() + " no té cap entrenador assignat.");
            return;
        }

        System.out.println("Segur que vols que " + equipo.getEntrenador().getNombre() + " " +
                equipo.getEntrenador().getApellido() + " deixi de ser entrenador de " +
                equipo.getNombre() + "? (s/n)");
        String entrada = Validador.numero2("s", "n");

        if (entrada.equalsIgnoreCase("s")) {
            listaPersonas.add(equipo.getEntrenador());
            equipo.setEntrenador(new Entrenador());
            actualizarMercadoFichaje(listaPersonas);
            System.out.println("L'entrenador ha estat destituït i ara és al mercat de fitxatges.");
        } else {
            System.out.println("Operació cancel·lada.");
        }
    }


    /**
     * Permite fichar un nuevo integrante (jugador o entrenador) desde el mercado para un equipo.
     * @param listaEquipos Lista global de equipos.
     * @param listaPersonas Lista del mercado de fichajes.
     * @param equipo El equipo que realiza el fichaje.
     */
    public static void ficharJugadorEntrenador(ArrayList<Equipos> listaEquipos,
                                               ArrayList<Personas> listaPersonas, Equipos equipo) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Vols fitxar un jugador (j) o un entrenador (e)?");
        String entradaJoE = Validador.numero2("j", "e");

        if (entradaJoE.equalsIgnoreCase("j")) {
            boolean hiHaJugadors = false;
            System.out.println("\nJugadors disponibles al mercat:");
            for (Personas persona : listaPersonas) {
                if (persona instanceof Jugador) {
                    System.out.println("  - " + persona.getNombre() + " " + persona.getApellido());
                    hiHaJugadors = true;
                }
            }
            if (!hiHaJugadors) {
                System.out.println("No hi ha jugadors disponibles al mercat.");
                return;
            }

            System.out.print("Nom del jugador: ");
            String nombre = scanner.nextLine();
            System.out.print("Cognom del jugador: ");
            String apellido = scanner.nextLine();

            Personas personaAFitxar = null;
            for (Personas personas : listaPersonas) {
                if (personas instanceof Jugador &&
                        personas.getNombre().equalsIgnoreCase(nombre) &&
                        personas.getApellido().equalsIgnoreCase(apellido)) {
                    personaAFitxar = personas;
                }
            }

            if (personaAFitxar != null) {
                equipo.setJugadores((Jugador) personaAFitxar);
                listaPersonas.remove(personaAFitxar);
                System.out.println("El jugador " + nombre + " " + apellido + " ha estat fitxat per " + equipo.getNombre() + "!");
            } else {
                System.out.println("No s'ha trobat el jugador indicat al mercat.");
            }

        } else {
            boolean hiHaEntrenadors = false;
            System.out.println("\nEntrenadors disponibles al mercat:");
            for (Personas persona : listaPersonas) {
                if (persona instanceof Entrenador) {
                    System.out.println("  - " + persona.getNombre() + " " + persona.getApellido());
                    hiHaEntrenadors = true;
                }
            }
            if (!hiHaEntrenadors) {
                System.out.println("No hi ha entrenadors disponibles al mercat.");
                return;
            }

            System.out.print("Nom de l'entrenador: ");
            String nombre = scanner.nextLine();
            System.out.print("Cognom de l'entrenador: ");
            String apellido = scanner.nextLine();

            Personas personaAFitxar = null;
            for (Personas personas : listaPersonas) {
                if (personas instanceof Entrenador &&
                        personas.getNombre().equalsIgnoreCase(nombre) &&
                        personas.getApellido().equalsIgnoreCase(apellido)) {
                    personaAFitxar = personas;
                }
            }

            if (personaAFitxar != null) {
                equipo.setEntrenador((Entrenador) personaAFitxar);
                listaPersonas.remove(personaAFitxar);
                System.out.println("L'entrenador " + nombre + " " + apellido + " ara és entrenador de " + equipo.getNombre() + "!");
            } else {
                System.out.println("No s'ha trobat l'entrenador indicat al mercat.");
            }
        }

        actualizarMercadoFichaje(listaPersonas);
        guardarEquipos(listaEquipos);
    }

    /**
     * Lee los datos de las personas en el mercado de fichajes desde un archivo de texto.
     * @param listaPersonas Lista donde se cargarán los datos leídos.
     */
    public static void leerMercadoFichajes(ArrayList<Personas> listaPersonas) {
        try (BufferedReader br = new BufferedReader(new FileReader("Aplicacio/src/archivosGuardado/mercat_fitxatges.txt"))) {
            String linea;

            while ((linea = br.readLine()) != null) {
                if (linea.isEmpty()) continue;
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
                    Jugador j1 = new Jugador(nombre, apellido, fechaNacimiento, nivelMotivacion, salario, dorsal, posicion, calidadTeorica);
                    listaPersonas.add(j1);
                } else {
                    int torneosGanados = Integer.parseInt(dades[6]);
                    boolean seleccionadoNacional = Boolean.parseBoolean(dades[7]);
                    Entrenador e1 = new Entrenador(nombre, apellido, fechaNacimiento, nivelMotivacion, salario, torneosGanados, seleccionadoNacional);
                    listaPersonas.add(e1);
                }
            }

        } catch (IOException e) {
            System.out.println("Error al llegir el fitxer del mercat: " + e.getMessage());
        }
    }


    /**
     * Lee los datos de los equipos y sus integrantes desde los archivos de persistencia.
     * @param listaEquipos Lista donde se cargarán los equipos.
     * @param listaPersonas Lista del mercado de fichajes para asociar entrenadores.
     */
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
            System.out.println("Error al llegir guardarEquipos.txt: " + e.getMessage());
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

                        Entrenador ent = new Entrenador(
                                datos[1], datos[2], datos[3],
                                Integer.parseInt(datos[4]),
                                Double.parseDouble(datos[5]),
                                Integer.parseInt(datos[6]),
                                Boolean.parseBoolean(datos[7])
                        );
                        equipos.setEntrenador(ent);

                    } else if (tipo.equals("J")) {
                        if (datos.length < 9) continue;

                        Jugador j1 = new Jugador(
                                datos[1], datos[2], datos[3],
                                Integer.parseInt(datos[4]),
                                Double.parseDouble(datos[5]),
                                Integer.parseInt(datos[6]),
                                datos[7],
                                Integer.parseInt(datos[8])
                        );
                        equipos.getJugadores().add(j1);
                    }
                }

            } catch (IOException e) {
                System.out.println("Error al llegir el fitxer de l'equip " + equipos.getNombre() + ": " + e.getMessage());
            }
        }
    }


    /**
     * Guarda la información de todos los equipos y sus jugadores en archivos de texto.
     * @param listaEquipos Lista de equipos a guardar.
     */
    public static void guardarEquipos(List<Equipos> listaEquipos) {

        String rutaCarpeta = "Aplicacio/src/archivosGuardado/guardarEquipos.txt";
        String rutaArchivoEquipos = "Aplicacio/src/archivosGuardado/equipos/";

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(rutaCarpeta))) {

            for (Equipos equipo : listaEquipos) {
                String nomEntrenador = (equipo.getEntrenador() != null && equipo.getEntrenador().getNombre() != null)
                        ? equipo.getEntrenador().getNombre() : "";
                bw.write(equipo.getNombre() + ";" +
                        equipo.getAñoFundacion() + ";" +
                        equipo.getCiudad() + ";" +
                        (equipo.getNombreEstadio() != null ? equipo.getNombreEstadio() : "") + ";" +
                        (equipo.getNombrePresidente() != null ? equipo.getNombrePresidente() : "") + ";" +
                        nomEntrenador);
                bw.newLine();

                try (BufferedWriter bwJugadors = new BufferedWriter(new FileWriter(rutaArchivoEquipos + equipo.getNombre() + ".txt"))) {

                    if (equipo.getEntrenador() != null && equipo.getEntrenador().getNombre() != null) {
                        bwJugadors.write(
                                "E;" +
                                        equipo.getEntrenador().getNombre() + ";" +
                                        equipo.getEntrenador().getApellido() + ";" +
                                        equipo.getEntrenador().getFechaNacimiento() + ";" +
                                        equipo.getEntrenador().getNivelMotivacion() + ";" +
                                        equipo.getEntrenador().getSueldoAnual() + ";" +
                                        equipo.getEntrenador().getNumTorneosGanados() + ";" +
                                        equipo.getEntrenador().isSeleccionadorNacional()
                        );
                        bwJugadors.newLine();
                    }

                    for (Jugador jugador : equipo.getJugadores()) {
                        bwJugadors.write(
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
                        bwJugadors.newLine();
                    }

                } catch (IOException e) {
                    System.out.println("Error al escriure el fitxer de l'equip " + equipo.getNombre() + ": " + e.getMessage());
                }
            }

        } catch (IOException e) {
            System.out.println("Error al escriure guardarEquipos.txt: " + e.getMessage());
        }
    }


    /**
     * Actualiza el archivo del mercado de fichajes con el estado actual de la lista de personas.
     * @param listaPersonas Lista de personas a persistir.
     */
    public static void actualizarMercadoFichaje(ArrayList<Personas> listaPersonas) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("Aplicacio/src/archivosGuardado/mercat_fitxatges.txt"))) {

            for (Personas persona : listaPersonas) {
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

        } catch (IOException e) {
            System.out.println("Error al actualitzar el mercat de fitxatges: " + e.getMessage());
        }
    }
}