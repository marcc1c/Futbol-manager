package personas;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;


public class Lliga {

    private String nombre;

    private int numEquipos;

    private ArrayList<Equipos> equipos;

    private ArrayList<int[]> estadisticas;


    public Lliga(String nombre, int numEquipos) {
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

        System.out.println("\n" + "=".repeat(40));
        System.out.println("  DISPUTANT LA LLIGA: " + nombre);
        System.out.println("=".repeat(40));

        for (int i = 0; i < equipos.size(); i++) {
            for (int j = 0; j < equipos.size(); j++) {
                if (i == j) continue;

                Equipos equip1 = equipos.get(i);
                Equipos equip2 = equipos.get(j);

                double quialidadEquip1 = calcularQualitat(equip1);
                double quialidadEquip2 = calcularQualitat(equip2);

                double motEquip1 = calcularMotivacio(equip1);
                double motEquip2 = calcularMotivacio(equip2);

                double forcaEquip1 = calcularForca(quialidadEquip1, motEquip1);
                double forcaEquip2 = calcularForca(quialidadEquip2, motEquip2);

                int golsEquip1 = generarGols(forcaEquip1, forcaEquip2);
                int golsEquip2 = generarGols(forcaEquip2, forcaEquip1);

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
        guardarLliga();
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

    private double calcularForca(double qualitat, double motivacio) {
        return qualitat * 0.8 + motivacio * 0.2;
    }


    private int generarGols(double f1, double f2) {
        Random random = new Random();

        double diff = f1 - f2;

        int base = random.nextInt(3);

        if (diff > 20) {
            base += random.nextInt(3);
        } else if (diff > 10) {
            base += random.nextInt(2);
        } else if (diff < -20) {
            if (random.nextInt(100) < 40) {
                base -= 1;
            }
        } else if (diff < -10) {
            if (random.nextInt(100) < 25) {
                base -= 1;
            }
        }

        if (base < 0) base = 0;

        return base;
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

    public void guardarLliga() {
        try (BufferedWriter writer = new BufferedWriter(
                new FileWriter("Aplicacio/src/archivosGuardado/liga.txt"))) {

            writer.write("Lliga:" + nombre + "\n");
            writer.write("NumEquipos:" + equipos.size() + "\n");

            for (int i = 0; i < equipos.size(); i++) {
                Equipos eq = equipos.get(i);
                int[] stats = estadisticas.get(i);
                // format: nomEquip;punts;partits;golsFavor;golsContra
                writer.write(eq.getNombre() + ";" + stats[0] + ";" + stats[1] + ";" + stats[2] + ";" + stats[3] + "\n");
            }

            System.out.println("Lliga desada correctament a liga.txt");

        } catch (IOException e) {
            System.out.println("Error al guardar la lliga: " + e.getMessage());
        }
    }


    public static Lliga cargarLliga(ArrayList<Equipos> listaEquiposGlobal) {
        try (BufferedReader br = new BufferedReader(
                new FileReader("Aplicacio/src/archivosGuardado/liga.txt"))) {

            String line = br.readLine();
            if (line == null || !line.startsWith("Lliga:")) return null;
            String nomLliga = line.split(":")[1];

            line = br.readLine();
            if (line == null || !line.startsWith("NumEquipos:")) return null;
            int numEquipos = Integer.parseInt(line.split(":")[1]);

            Lliga lliga = new Lliga(nomLliga, numEquipos);

            while ((line = br.readLine()) != null) {
                if (line.isEmpty()) continue;
                String[] dades = line.split(";");
                if (dades.length < 5) continue;

                String nomEquip = dades[0];
                int pts = Integer.parseInt(dades[1]);
                int pj = Integer.parseInt(dades[2]);
                int gf = Integer.parseInt(dades[3]);
                int gc = Integer.parseInt(dades[4]);

                Equipos equipTrobat = null;
                for (Equipos e : listaEquiposGlobal) {
                    if (e.getNombre().equalsIgnoreCase(nomEquip)) {
                        equipTrobat = e;
                        break;
                    }
                }

                if (equipTrobat != null) {
                    lliga.equipos.add(equipTrobat);
                    lliga.estadisticas.add(new int[]{pts, pj, gf, gc});
                }
            }
            return lliga;

        } catch (IOException | NumberFormatException e) {
            System.out.println("No s'ha pogut carregar la lliga o el fitxer no existeix.");
            return null;
        }
    }
}