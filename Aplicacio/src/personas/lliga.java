package personas;

import java.util.ArrayList;
import java.util.Random;


public class lliga {

    private String nombre;

    private int numEquipos;

    private ArrayList<Equipos> equipos;

    private ArrayList<int[]> estadisticas;


    public lliga(String nombre, int numEquipos) {
        this.nombre = nombre;
        this.numEquipos = numEquipos;
        this.equipos = new ArrayList<>();
        this.estadisticas = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }


    public int getNumEquipos() {
        return numEquipos;
    }


    public ArrayList<Equipos> getEquipos() {
        return equipos;
    }


    public void afegirEquip(Equipos equipo) {
        equipos.add(equipo);
        estadisticas.add(new int[]{0, 0, 0, 0});
    }

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

                double qualLocal = calcularQualitat(local);
                double qualVisitant = calcularQualitat(visitant);

                int golesLocal = generarGols(random, qualLocal);
                int golesVisitant = generarGols(random, qualVisitant);

                System.out.println(local.getNombre() + " " + golesLocal + " - " + golesVisitant + " " + visitant.getNombre());

                int[] statsLocal = estadisticas.get(i);
                statsLocal[1]++;
                statsLocal[2] += golesLocal;
                statsLocal[3] += golesVisitant;

                int[] statsVisitant = estadisticas.get(j);
                statsVisitant[1]++;
                statsVisitant[2] += golesVisitant;
                statsVisitant[3] += golesLocal;

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


    private int generarGols(Random random, double qualitat) {
        int base = random.nextInt(5);
        int bonus = (qualitat > 70) ? random.nextInt(2) : 0;
        return base + bonus;
    }

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