import java.util.ArrayList;
import java.util.Scanner;

/**
 * Clase Validador que proporciona métodos de utilidad para obtener y validar la entrada del usuario de forma segura.
 */
public class Validador {

    /**
     * Solicita al usuario un número dentro de un rango específico, gestionando entradas no válidas.
     * @param numeroInicial El valor mínimo aceptado.
     * @param numeroFinal El valor máximo aceptado.
     * @return La entrada del usuario validada.
     */
    public static int numerosInicioFinal(int numeroInicial, int numeroFinal) {
        Scanner scanner = new Scanner(System.in);
        int input = 0;
        boolean valido = false;

        while (!valido) {
            try {
                String s = scanner.nextLine();
                input = Integer.parseInt(s);
                if (input < numeroInicial || input > numeroFinal) {
                    System.out.println("Valor incorrecto");
                } else {
                    valido = true;
                }
            } catch (NumberFormatException e) {
                System.out.println("Valor incorrecto. Por favor introduce un numero entero valido.");
            }
        }
        return input;
    }

    /**
     * Verifica si la entrada de cadena del usuario existe dentro de la lista proporcionada de cadenas aceptables.
     * @param arrayValoresPosibles Los valores permitidos.
     * @return La entrada de cadena válida.
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
     * Verifica si la entrada entera del usuario existe dentro de la lista proporcionada de enteros aceptables.
     * @param arrayValoresPosibles Los valores permitidos.
     * @return La entrada entera válida.
     */
    public static int arrayListInt(ArrayList<Integer> arrayValoresPosibles) {
        Scanner scanner = new Scanner(System.in);
        int input = 0;
        boolean valido = false;

        while (!valido) {
            try {
                String s = scanner.nextLine();
                input = Integer.parseInt(s);
                for (int palabras : arrayValoresPosibles) {
                    if (palabras == input) {
                        valido = true;
                        break;
                    }
                }
                if (!valido) {
                    System.out.println("Valor incorrecto");
                }
            } catch (NumberFormatException e) {
                System.out.println("Valor incorrecto. Por favor introduce un numero entero valido.");
            }
        }
        return input;
    }

    /**
     * Verifica si la entrada de cadena del usuario existe dentro del array proporcionado de cadenas aceptables.
     * @param arrayValoresPosibles Los valores permitidos como un array.
     * @return La entrada de cadena válida.
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
     * Asegura que la entrada del usuario coincida estrictamente con un valor especificado.
     * @param numero El valor requerido.
     * @return La entrada de cadena válida.
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
     * Asegura que la entrada del usuario coincida con uno de los dos valores especificados.
     * @param numero1 El primer valor permitido.
     * @param numero2 El segundo valor permitido.
     * @return La entrada de cadena válida.
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
     * Asegura que la entrada del usuario coincida con uno de los tres valores especificados.
     * @param numero1 El primer valor permitido.
     * @param numero2 El segundo valor permitido.
     * @param numero3 El tercer valor permitido.
     * @return La entrada de cadena válida.
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

    /**
     * Solicita al usuario un valor decimal (double), gestionando entradas no válidas.
     * @return La entrada decimal válida.
     */
    public static double validarDouble() {
        Scanner scanner = new Scanner(System.in);
        double input = 0;
        boolean valido = false;

        while (!valido) {
            try {
                String s = scanner.nextLine();
                input = Double.parseDouble(s);
                valido = true;
            } catch (NumberFormatException e) {
                System.out.println("Valor incorrecto. Por favor introduce un numero (decimal) valido.");
            }
        }
        return input;
    }

    /**
     * Solicita al usuario cualquier valor entero, gestionando entradas no válidas.
     * @return La entrada entera válida.
     */
    public static int validarInt() {
        Scanner scanner = new Scanner(System.in);
        int input = 0;
        boolean valido = false;

        while (!valido) {
            try {
                String s = scanner.nextLine();
                input = Integer.parseInt(s);
                valido = true;
            } catch (NumberFormatException e) {
                System.out.println("Valor incorrecto. Por favor introduce un numero entero valido.");
            }
        }
        return input;
    }
}
