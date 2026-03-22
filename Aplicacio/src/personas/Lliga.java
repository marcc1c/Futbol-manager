package personas;

import java.util.ArrayList;
import java.util.Random;


/**
 * Modela una Liga de fútbol donde varios equipos disputan partidos entre sí.
 * Mantiene la clasificación y gestiona la generación de partidos.
 */
public class Lliga {

    private String nombre;

    private int numEquipos;

    private ArrayList<Equipos> equipos;

    private ArrayList<int[]> estadisticas;


    /**
     * Constructor de la Liga.
     * @param nombre Nombre de la liga.
     * @param numEquipos Cantidad de equipos que la disputan.
     */
    public Lliga(String nombre, int numEquipos) {
        this.nombre = nombre;
        this.numEquipos = numEquipos;
        this.equipos = new ArrayList<>();
        this.estadisticas = new ArrayList<>();
    }

    /**
     * @return El nombre de la liga.
     */
    public String getNombre() {
        return nombre;
    }


    /**
     * @return El número de equipos registrados que disputarán la liga.
     */
    public int getNumEquipos() {
        return numEquipos;
    }


    /**
     * @return Lista de equipos participantes.
     */
    public ArrayList<Equipos> getEquipos() {
        return equipos;
    }


    /**
     * Añade un nuevo equipo a la liga y le inicializa las estadísticas.
     * @param equipo El equipo a agregar.
     */
    public void afegirEquip(Equipos equipo) {
        equipos.add(equipo);
        estadisticas.add(new int[]{0, 0, 0, 0});
    }

    /**
     * Simula y disputa todos los partidos posibles entre los equipos de la liga.
     * Genera los resultados basándose en la calidad, motivación y un componente de aleatoriedad,
     * y actualiza las estadísticas (puntos, partidos jugados, goles, etc.).
     */
    public void disputarTodosLosPartidos() {
        Random random = new Random();

        System.out.println("\n" + "=".repeat(40));
        System.out.println("  DISPUTANT LA LLIGA: " + nombre);
        System.out.println("=".repeat(40));

        for (int i = 0; i < equipos.size(); i++) {
            for (int j = 0; j < equipos.size(); j++) {
                if (i == j) {
                    continue;
                }

                Equipos equip1 = equipos.get(i);
                Equipos equip2 = equipos.get(j);

                double qualitatEquip1 = calcularQualitat(equip1);
                double qualitatEquip2 = calcularQualitat(equip2);

                double motivacioEquip1 = calcularMotivacio(equip1);
                double motivacioEquipo2 = calcularMotivacio(equip2);

                double forcaEquip1 = calcularForcaEquip(qualitatEquip1, motivacioEquip1);
                double forcaEquip2 = calcularForcaEquip(qualitatEquip2, motivacioEquipo2);

                int golsEquip1 = generarGols( forcaEquip1, forcaEquip2);
                int golsEquip2 = generarGols( forcaEquip2, forcaEquip1);

                System.out.println(equip1.getNombre() + " " + golsEquip1 + " - " + golsEquip2 + " " + equip2.getNombre());

                int[] statsEquip1 = estadisticas.get(i);
                statsEquip1[1]++;
                statsEquip1[2] += golsEquip1;
                statsEquip1[3] += golsEquip2;

                int[] statsEquip2 = estadisticas.get(j);
                statsEquip2[1]++;
                statsEquip2[2] += golsEquip2;
                statsEquip2[3] += golsEquip1;

                if (golsEquip1 > golsEquip2) {
                    statsEquip1[0] += 3;
                } else if (golsEquip1 < golsEquip2) {
                    statsEquip2[0] += 3;
                } else {
                    statsEquip1[0] += 1;
                    statsEquip2[0] += 1;
                }
            }
        }

        System.out.println("=".repeat(40));
        System.out.println("  Tots els partits han estat disputats!");
        System.out.println("=".repeat(40) + "\n");
        
        guardarResultadosLiga();
    }


    /**
     * Calcula la media de calidad de los jugadores de un equipo.
     * @param equipo Equipo a evaluar.
     * @return Calidad media.
     */
    private double calcularQualitat(Equipos equipo) {
        if (equipo.getJugadores().isEmpty()) {
            return 50.0;
        }
        double suma = 0;
        for (Jugador j : equipo.getJugadores()) {
            suma += j.getQualidad();
        }
        return suma / equipo.getJugadores().size();
    }

    /**
     * Calcula la motivación promedio de un equipo.
     * @param equipo Equipo a evaluar.
     * @return Nivel de motivación media.
     */
    private double calcularMotivacio(Equipos equipo) {
        if (equipo.getJugadores().isEmpty()) {
            return 50.0;
        }

        double suma = 0;

        for (Jugador j : equipo.getJugadores()) {
            suma += j.getNivelMotivacion();
        }

        return suma / equipo.getJugadores().size();
    }

    /**
     * Determina la fuerza conjunta combinando calidad y motivación.
     * @param qualitat Calidad calculada.
     * @param motivacio Motivación calculada.
     * @return Fuerza global del equipo.
     */
    private double calcularForcaEquip(double qualitat, double motivacio) {
        return qualitat * 0.8 + motivacio * 0.2;
    }

    /**
     * Lógica para generar los goles marcados por un equipo al competir con la fuerza del rival.
     * @param forcaEquip Fuerza del equipo atacante.
     * @param forcaRival Fuerza del equipo defensor.
     * @return Cantidad de goles marcados.
     */
    private int generarGols(double forcaEquip, double forcaRival) {
        Random random = new Random();
        double diferencia = forcaEquip - forcaRival;

        int goles = 0;

        if (diferencia > 20) {
            goles = random.nextInt(4) + 2; // 2 a 5
        } else if (diferencia > 10) {
            goles = random.nextInt(4) + 1; // 1 a 4
        } else if (diferencia >= -10) {
            goles = random.nextInt(4); // 0 a 3
        } else if (diferencia >= -20) {
            goles = random.nextInt(3); // 0 a 2
        } else {
            goles = random.nextInt(2); // 0 a 1
        }

        return goles;
    }

    /**
     * Genera la tabla de clasificación ordenada y la guarda en un archivo de texto persistente.
     * El archivo sobrescribe las ligas jugadas anteriormente.
     */
    public void guardarResultadosLiga() {
        try (java.io.FileWriter fw = new java.io.FileWriter("src/archivosGuardado/liga.txt", false);
             java.io.PrintWriter pw = new java.io.PrintWriter(fw)) {

            pw.println("\n" + "=".repeat(70));
            pw.println("  CLASSIFICACIÓ - " + nombre + "  ");
            pw.println("=".repeat(70));
            pw.printf("%-3s %-20s %6s %4s %6s %6s %6s%n",
                    "Pos", "Equip", "Pts", "PJ", "GF", "GC", "DG");
            pw.println("-".repeat(70));

            ArrayList<int[]> copiaStats = new ArrayList<>();
            ArrayList<Equipos> copiaEquipos = new ArrayList<>();

            for (int i = 0; i < equipos.size(); i++) {
                copiaStats.add(estadisticas.get(i));
                copiaEquipos.add(equipos.get(i));
            }

            for (int i = 0; i < copiaEquipos.size() - 1; i++) {
                for (int j = 0; j < copiaEquipos.size() - 1 - i; j++) {
                    int[] statsA = copiaStats.get(j);
                    int[] statsB = copiaStats.get(j + 1);

                    int difA = statsA[2] - statsA[3];
                    int difB = statsB[2] - statsB[3];

                    boolean haDeCanviar = false;
                    if (statsA[0] < statsB[0]) {
                        haDeCanviar = true;
                    } else if (statsA[0] == statsB[0] && difA < difB) {
                        haDeCanviar = true;
                    }

                    if (haDeCanviar) {
                        copiaStats.set(j, statsB);
                        copiaStats.set(j + 1, statsA);
                        Equipos temp = copiaEquipos.get(j);
                        copiaEquipos.set(j, copiaEquipos.get(j + 1));
                        copiaEquipos.set(j + 1, temp);
                    }
                }
            }

            for (int i = 0; i < copiaEquipos.size(); i++) {
                int[] stats = copiaStats.get(i);
                int difGols = stats[2] - stats[3];
                pw.printf("%-3d %-20s %6d %4d %6d %6d %+6d%n",
                        (i + 1),
                        copiaEquipos.get(i).getNombre(),
                        stats[0],
                        stats[1],
                        stats[2],
                        stats[3],
                        difGols);
            }

            pw.println("=".repeat(70));
            pw.println("(Pts=Punts, PJ=Partits Jugats, GF=Gols Favor, GC=Gols Contra, DG=Dif. Gols)\n");

        } catch (java.io.IOException e) {
            System.out.println("Error en guardar la lliga: " + e.getMessage());
        }
    }

    /**
     * Muestra la clasificación actual de la liga ordenando los equipos por puntos e imprimiéndolos.
     */
    public void mostrarClasificacion() {
        if (equipos.isEmpty()) {
            System.out.println("No hi ha cap lliga disputada.");
            return;
        }

        System.out.println("\n" + "=".repeat(70));
        System.out.println("  CLASSIFICACIÓ - " + nombre + "  ");
        System.out.println("=".repeat(70));
        System.out.printf("%-3s %-20s %6s %4s %6s %6s %6s%n",
                "Pos", "Equip", "Pts", "PJ", "GF", "GC", "DG");
        System.out.println("-".repeat(70));

        ArrayList<int[]> copiaStats = new ArrayList<>();
        ArrayList<Equipos> copiaEquipos = new ArrayList<>();

        for (int i = 0; i < equipos.size(); i++) {
            copiaStats.add(estadisticas.get(i));
            copiaEquipos.add(equipos.get(i));
        }

        for (int i = 0; i < copiaEquipos.size() - 1; i++) {
            for (int j = 0; j < copiaEquipos.size() - 1 - i; j++) {
                int[] statsA = copiaStats.get(j);
                int[] statsB = copiaStats.get(j + 1);

                int difA = statsA[2] - statsA[3];
                int difB = statsB[2] - statsB[3];

                boolean haDeCanviar = false;
                if (statsA[0] < statsB[0]) {
                    haDeCanviar = true;
                } else if (statsA[0] == statsB[0] && difA < difB) {
                    haDeCanviar = true;
                }

                if (haDeCanviar) {
                    copiaStats.set(j, statsB);
                    copiaStats.set(j + 1, statsA);
                    Equipos temp = copiaEquipos.get(j);
                    copiaEquipos.set(j, copiaEquipos.get(j + 1));
                    copiaEquipos.set(j + 1, temp);
                }
            }
        }

        for (int i = 0; i < copiaEquipos.size(); i++) {
            int[] stats = copiaStats.get(i);
            int difGols = stats[2] - stats[3];
            System.out.printf("%-3d %-20s %6d %4d %6d %6d %+6d%n",
                    (i + 1),
                    copiaEquipos.get(i).getNombre(),
                    stats[0],
                    stats[1],
                    stats[2],
                    stats[3],
                    difGols);
        }

        System.out.println("=".repeat(70));
        System.out.println("(Pts=Punts, PJ=Partits Jugats, GF=Gols Favor, GC=Gols Contra, DG=Dif. Gols)\n");
    }


    /**
     * Busca entre las estadísticas qué equipo ha anotado más goles a favor.
     * @return El equipo con más goles marcados, o null si está vacío.
     */
    public Equipos equipoMasGoleador() {
        if (equipos.isEmpty()) return null;

        Equipos millor = equipos.get(0);
        int maxGols = estadisticas.get(0)[2];

        for (int i = 1; i < equipos.size(); i++) {
            if (estadisticas.get(i)[2] > maxGols) {
                maxGols = estadisticas.get(i)[2];
                millor = equipos.get(i);
            }
        }
        return millor;
    }


    /**
     * Busca entre las estadísticas qué equipo ha recibido más goles en contra.
     * @return El equipo con más goles en contra recibidos, o null si está vacío.
     */
    public Equipos equipoMenosGoleador() {
        if (equipos.isEmpty()) return null;

        Equipos pitjor = equipos.get(0);
        int maxGolsContra = estadisticas.get(0)[3];

        for (int i = 1; i < equipos.size(); i++) {
            if (estadisticas.get(i)[3] > maxGolsContra) {
                maxGolsContra = estadisticas.get(i)[3];
                pitjor = equipos.get(i);
            }
        }
        return pitjor;
    }
}
