import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Validador class that provides utility methods to safely get and validate user input.
 */
public class Validador {

    /**
     * Prompts the user for a number within a specified range, handling invalid inputs.
     * @param numeroInicial the minimum accepted value
     * @param numeroFinal the maximum accepted value
     * @return the constrained user input
     */
    public static int numerosInicioFinal(int numeroInicial, int numeroFinal) {
        Scanner scanner = new Scanner(System.in);
        int input = 0;
        boolean valido = false;

        while (!valido) {
            try {
                input = scanner.nextInt();
                if (input < numeroInicial || input > numeroFinal) {
                    System.out.println("Valor incorrecto");
                } else {
                    valido = true;
                }
            } catch (InputMismatchException e) {
                System.out.println("Valor incorrecto. Por favor introduce un numero valido.");
                scanner.next(); // Clear the invalid input
            }
        }
        return input;
    }

    /**
     * Checks if the user's string input exists within the provided list of acceptable strings.
     * @param arrayValoresPosibles the allowed values
     * @return the valid string input
     */
    public static String arrayListStrings(ArrayList<String> arrayValoresPosibles) {
        Scanner scanner = new Scanner(System.in);
        String input = "";
        boolean valido = false;

        while (!valido) {
            input = scanner.nextLine();
            for (String palabras : arrayValoresPosibles) {
                if (palabras.equalsIgnoreCase(input)) {
                    valido = true;
                    break;
                }
            }
            if (!valido) {
                System.out.println("Valor incorrecto");
            }
        }
        return input;
    }

    /**
     * Checks if the user's integer input exists within the provided list of acceptable integers.
     * @param arrayValoresPosibles the allowed values
     * @return the valid integer input
     */
    public static int arrayListInt(ArrayList<Integer> arrayValoresPosibles) {
        Scanner scanner = new Scanner(System.in);
        int input = 0;
        boolean valido = false;

        while (!valido) {
            try {
                input = scanner.nextInt();
                for (int palabras : arrayValoresPosibles) {
                    if (palabras == input) {
                        valido = true;
                        break;
                    }
                }
                if (!valido) {
                    System.out.println("Valor incorrecto");
                }
            } catch (InputMismatchException e) {
                System.out.println("Valor incorrecto. Por favor introduce un numero valido.");
                scanner.next(); // Clear the invalid input
            }
        }
        return input;
    }

    /**
     * Checks if the user's string input exists within the provided array of acceptable strings.
     * @param arrayValoresPosibles the allowed values as an array
     * @return the valid string input
     */
    public static String array(String[] arrayValoresPosibles) {
        Scanner scanner = new Scanner(System.in);
        String input = "";
        boolean valido = false;

        while (!valido) {
            input = scanner.nextLine();
            for (String arrayValoresPosible : arrayValoresPosibles) {
                if (arrayValoresPosible.equalsIgnoreCase(input)) {
                    valido = true;
                    break;
                }
            }
            if (!valido) {
                System.out.println("Valor incorrecto");
            }
        }
        return input;
    }

    /**
     * Ensures the user's input strictly matches a specified value.
     * @param numero the required value
     * @return the valid string input
     */
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

    /**
     * Ensures the user's input strictly matches one of two specified values.
     * @param numero1 the first allowed value
     * @param numero2 the second allowed value
     * @return the valid string input
     */
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

    /**
     * Ensures the user's input strictly matches one of three specified values.
     * @param numero1 the first allowed value
     * @param numero2 the second allowed value
     * @param numero3 the third allowed value
     * @return the valid string input
     */
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

    /**
     * Revisa que la entrada del usuario sea exactamente una de las cuatro strings introducidas.
     * @param por first valid string
     * @param def second valid string
     * @param mig third valid string
     * @param dav fourth valid string
     * @return the valid inputted string converted to uppercase
     */
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
