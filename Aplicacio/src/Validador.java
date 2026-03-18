import java.util.ArrayList;
import java.util.Scanner;

public class Validador {

    public static int numerosInicioFinal(int numeroInicial, int numeroFinal) {
        Scanner scanner = new Scanner(System.in);
        scanner.hasNextInt();

        int input = 0;

        boolean valido = false;
        while (!valido) {
            input = scanner.nextInt();
            if (input < numeroInicial || input > numeroFinal) {
                System.out.println("Valor incorrecto");
            } else {
                valido = true;
            }
        }
        return input;
    }

    public static String arrayListStrings(ArrayList<String> arrayValoresPosibles) {
        Scanner scanner = new Scanner(System.in);
        String input = "";

        boolean valido = false;
        while (!valido) {
            input = scanner.nextLine();
            for (String palabras : arrayValoresPosibles) {
                if (palabras.equalsIgnoreCase(input)) {
                    valido = true;
                }
            }
            if (!valido) {
                System.out.println("Valor incorrecto");
            }
        }
        return input;
    }

    public static int arrayListInt(ArrayList<Integer> arrayValoresPosibles) {
        Scanner scanner = new Scanner(System.in);
        int input = 0;

        boolean valido = false;
        while (!valido) {
            input = scanner.nextInt();
            for (int palabras : arrayValoresPosibles) {
                if (palabras == input) {
                    valido = true;
                }
            }
            if (!valido) {
                System.out.println("Valor incorrecto");
            }
        }
        return input;
    }

    public static String array(String[] arrayValoresPosibles) {
        Scanner scanner = new Scanner(System.in);
        String input = "";

        boolean valido = false;
        while (!valido) {
            input = scanner.nextLine();
            for (String arrayValoresPosible : arrayValoresPosibles) {
                if (arrayValoresPosible.equalsIgnoreCase(input)) {
                    valido = true;
                }
            }
            if (!valido) {
                System.out.println("Valor incorrecto");
            }
        }
        return input;
    }

    public static String numero1(String numero) {
        Scanner scanner = new Scanner(System.in);
        String input = "";
        boolean valido = false;

        while (!valido) {
            input = scanner.nextLine();
            if (input.equals(numero)) {
                valido = true;
            } else  {
                System.out.println("Valor incorrecto");
            }
        }
        return input;
    }

    public static String numero2(String numero1, String numero2) {
        Scanner scanner = new Scanner(System.in);
        String input = "";
        boolean valido = false;

        while (!valido) {
            input = scanner.nextLine();
            if (input.equalsIgnoreCase(numero1) || input.equalsIgnoreCase(numero2)) {
                valido = true;
            } else  {
                System.out.println("Valor incorrecto");
            }
        }
        return input;
    }

    public static String numero3(String numero1, String numero2, String numero3) {
        Scanner scanner = new Scanner(System.in);
        String input = "";
        boolean valido = false;

        while (!valido) {
            input = scanner.nextLine();
            if (input.equalsIgnoreCase(numero1) || input.equalsIgnoreCase(numero2) || input.equalsIgnoreCase(numero3)) {
                valido = true;
            } else  {
                System.out.println("Valor incorrecto");
            }
        }
        return input;
    }

    public static String array(String por, String def, String mig, String dav) {
        Scanner scanner = new Scanner(System.in);
        String input = "";
        boolean valido = false;

        while (!valido) {
            input = scanner.nextLine();
            if (input.equalsIgnoreCase(por) || input.equalsIgnoreCase(def)
                    || input.equalsIgnoreCase(mig) || input.equalsIgnoreCase(dav)) {
                valido = true;
            } else {
                System.out.println("Valor incorrecto. Prova amb: " + por + ", " + def + ", " + mig + ", " + dav);
            }
        }
        return input.toUpperCase();
    }
}

