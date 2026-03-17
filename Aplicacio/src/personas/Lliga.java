package personas;

import java.util.ArrayList;
import java.util.Random;

/**
 * Classe que representa una lliga de futbol.
 * Gestiona els equips participants, disputa els partits
 * i mostra la classificació final.
 */
public class Lliga {

    /** Nom de la lliga */
    private String nombre;

    /** Nombre d'equips que participen a la lliga */
    private int numEquipos;

    /** Llista d'equips participants */
    private ArrayList<Equipos> equipos;

    /** Llista de resultats per a cada equip (punts, partits, gols a favor, gols en contra) */
    private ArrayList<int[]> estadisticas; // [puntos, partidosJugados, golesFavor, golesContra]

    /**
     * Constructor de la Lliga.
     *
     * @param nombre    Nom de la lliga
     * @param numEquipos Nombre d'equips que hi participen
     */
    public Lliga(String nombre, int numEquipos) {
        this.nombre = nombre;
        this.numEquipos = numEquipos;
        this.equipos = new ArrayList<>();
        this.estadisticas = new ArrayList<>();
    }

    /**
     * Retorna el nom de la lliga.
     *
     * @return Nom de la lliga
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Retorna el nombre d'equips que han de participar a la lliga.
     *
     * @return Nombre d'equips
     */
    public int getNumEquipos() {
        return numEquipos;
    }

    /**
     * Retorna la llista d'equips participants.
     *
     * @return ArrayList d'equips
     */
    public ArrayList<Equipos> getEquipos() {
        return equipos;
    }

    /**
     * Afegeix un equip a la lliga inicialitzant les seves estadístiques a zero.
     *
     * @param equipo Equip a afegir
     */
    public void afegirEquip(Equipos equipo) {
        equipos.add(equipo);
        // [puntos, partidosJugados, golesFavor, golesContra]
        estadisticas.add(new int[]{0, 0, 0, 0});
    }

    /**
     * Disputa tots els partits de la lliga (anada i tornada).
     * Cada equip juga contra tots els altres dos cops.
     * Els gols de cada partit es generen aleatoriament.
     */
    public void disputarTodosLosPartidos() {
        Random random = new Random();

        System.out.println("\n" + "=".repeat(40));
        System.out.println("  DISPUTANT LA LLIGA: " + nombre);
        System.out.println("=".repeat(40));

        for (int i = 0; i < equipos.size(); i++) {
            for (int j = 0; j < equipos.size(); j++) {
                if (i == j) continue;

                Equipos local = equipos.get(i);
                Equipos visitant = equipos.get(j);

                // Qualitat mitjana determina un petit avantatge als gols
                double qualLocal = calcularQualitat(local);
                double qualVisitant = calcularQualitat(visitant);

                int golesLocal = generarGols(random, qualLocal);
                int golesVisitant = generarGols(random, qualVisitant);

                System.out.println(local.getNombre() + " " + golesLocal + " - " + golesVisitant + " " + visitant.getNombre());

                // Actualitzem estadísticas del local
                int[] statsLocal = estadisticas.get(i);
                statsLocal[1]++; // partits jugats
                statsLocal[2] += golesLocal; // gols a favor
                statsLocal[3] += golesVisitant; // gols en contra

                // Actualitzem estadísticas del visitant
                int[] statsVisitant = estadisticas.get(j);
                statsVisitant[1]++;
                statsVisitant[2] += golesVisitant;
                statsVisitant[3] += golesLocal;

                // Punts
                if (golesLocal > golesVisitant) {
                    statsLocal[0] += 3;
                } else if (golesLocal < golesVisitant) {
                    statsVisitant[0] += 3;
                } else {
                    statsLocal[0] += 1;
                    statsVisitant[0] += 1;
                }
            }
        }

        System.out.println("=".repeat(40));
        System.out.println("  Tots els partits han estat disputats!");
        System.out.println("=".repeat(40) + "\n");
    }

    /**
     * Calcula la qualitat d'un equip en base a la mitja dels seus jugadors.
     * Si l'equip no té jugadors, retorna 50 per defecte.
     *
     * @param equipo Equip a avaluar
     * @return Qualitat mitja
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
     * Genera un nombre aleatori de gols per a un equip.
     * La qualitat de l'equip afecta lleugerament la probabilitat de marcar.
     *
     * @param random   Objecte Random per generar els gols
     * @param qualitat Qualitat de l'equip
     * @return Nombre de gols generats (entre 0 i 5)
     */
    private int generarGols(Random random, double qualitat) {
        // Base: entre 0 i 4 gols, amb un petit bonus de qualitat
        int base = random.nextInt(5);
        int bonus = (qualitat > 70) ? random.nextInt(2) : 0;
        return base + bonus;
    }

    /**
     * Mostra la classificació actual de la lliga ordenada per punts.
     * En cas d'empat de punts, s'ordena per diferència de gols.
     */
    public void mostrarClasificacion() {
        if (equipos.isEmpty()) {
            System.out.println("No hi ha cap lliga disputada.");
            return;
        }

        System.out.println("\n" + "=".repeat(70));
        System.out.println("  CLASSIFICACIÓ - " + nombre + "  🏆");
        System.out.println("=".repeat(70));
        System.out.printf("%-3s %-20s %6s %4s %6s %6s %6s%n",
                "Pos", "Equip", "Pts", "PJ", "GF", "GC", "DG");
        System.out.println("-".repeat(70));

        // Fem una còpia per ordenar sense modificar l'original
        ArrayList<int[]> copiaStats = new ArrayList<>();
        ArrayList<Equipos> copiaEquipos = new ArrayList<>();

        for (int i = 0; i < equipos.size(); i++) {
            copiaStats.add(estadisticas.get(i));
            copiaEquipos.add(equipos.get(i));
        }

        // Ordenem per punts (i per diferència de gols en cas d'empat) amb un bubble sort
        for (int i = 0; i < copiaEquipos.size() - 1; i++) {
            for (int j = 0; j < copiaEquipos.size() - 1 - i; j++) {
                int[] statsA = copiaStats.get(j);
                int[] statsB = copiaStats.get(j + 1);

                int difA = statsA[2] - statsA[3]; // diferència gols A
                int difB = statsB[2] - statsB[3]; // diferència gols B

                boolean haDeCanviar = false;
                if (statsA[0] < statsB[0]) {
                    haDeCanviar = true;
                } else if (statsA[0] == statsB[0] && difA < difB) {
                    haDeCanviar = true;
                }

                if (haDeCanviar) {
                    // Intercanviem estadísticas
                    copiaStats.set(j, statsB);
                    copiaStats.set(j + 1, statsA);
                    // Intercanviem equips
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
     * Retorna l'equip amb més gols a favor.
     *
     * @return Equip amb més gols a favor, o null si la lliga no té equips
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
     * Retorna l'equip amb més gols en contra.
     *
     * @return Equip amb més gols en contra, o null si la lliga no té equips
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
