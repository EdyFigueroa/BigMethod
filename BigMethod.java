/**
 * CLASE MAIN
 */

 import java.util.InputMismatchException;
import java.util.Scanner;

public class BigMethod {
    static Scanner sc = new Scanner(System.in);
    static String pregunta;
    static String concepto;
    static String unidad;

    public static void main(String [] args) {
        printHojaPresentacion();

        // Preguntar por los parámetros del problema
        System.out.print("- Pregunta del problema: > ");
        pregunta = sc.nextLine();
        System.out.print("- Concepto: > ");
        concepto = sc.nextLine();
        System.out.print("- Unidad de medida: > ");
        unidad = sc.nextLine();

        // Mostrar el menú
        System.out.println("=================================================================");
        imprimirMenu();
        System.out.print("- Teclee su opción: > ");

        int opcion = 0;        
        while (opcion != 10) {
            try {
                opcion = sc.nextInt();
            } catch (InputMismatchException e) {
                // Si el usuario escribe algo que no sea correcto
                opcion = 0;
            }
            sc.nextLine();

            switch (opcion) {
                case 1:
                    // Método de la secante
                    System.out.println("=================================================================");
                    System.out.println("                      MÉTODO DE LA SECANTE");
                    metodoSecante();
                    System.out.println("=================================================================");
                    imprimirMenu();
                    System.out.print("- Teclee su opción: > ");
                    break;

                case 2:
                    // Método de Newton Raphson
                    System.out.println("=================================================================");
                    System.out.println("                    MÉTODO DE NEWTON RAPHSON");
                    metodoNewtonRaphson();
                    System.out.println("=================================================================");
                    imprimirMenu();
                    System.out.print("- Teclee su opción: > ");
                    break;

                case 10:
                    // Caso de salir
                    System.out.println("matate pues cffff");
                    break;
            
                default:
                    // Caso en el que la opción no esté en el menú
                    System.out.print("- /!\\ Teclee una opción valida: > ");
                    break;
            }
        }
    }

    /**
     * Este método imprime la hoja de presentación
     */
    public static void printHojaPresentacion() {
        // Datos de la escuela
        System.out.println("=================================================================");
        System.out.println("              Instituto Tecnológico de Culiacán");
        System.out.println("               Ing en Sistemas Computacionales");

        // Datos del alumno
        System.out.println("\nFigueroa Lizárraga Edy Jared");
        System.out.println("Solución de ecuaciones");
        System.out.println("De 9:00 a 10:00 horas");

        // Descripción de la tarea
        System.out.println("\n         Este programa ejecuta la solución de problemas\r\n" + //
                        "     utilizando los procesos lógicos de cálculo de Raíces de\r\n" + //
                        "          una ecuación con diversos métodos numéricos.");
        System.out.println("=================================================================");
    }

    public static void imprimirMenu() {
        System.out.println("[1] Método de la Secante");
        System.out.println("[2] Método de Newton Raphson");
        System.out.println("[10] F I N\n");
    }

    public static void metodoSecante() {
        // Escribir el problema
        System.out.printf("Pregunta: %s\n\n", pregunta);

        // Imprimir cabecera de la tabla
        System.out.println("┌──┬──────────┬──────────┬──────────┬──────────┬──────────┬──────────┐");
        System.out.println("│N°│    x1    │   f(x1)  │    x2    │   f(x2)  │    x3    │   f(x3)  │");
        System.out.println("├──┼──────────┼──────────┼──────────┼──────────┼──────────┼──────────┤");

        Secante secante = new Secante(0.1, 0.6, 0.00001, 50);

        // Imprimir hasta que el error sea suficiente para finalizar
        try {
            while (secante.calcularError()) {
                double[] resultados = secante.nextCalc();
                secante.printLine(resultados);
            }
        } catch (DemasiadosCalculosException e) {
            System.out.println("└──┴──────────┴──────────┴──────────┴──────────┴──────────┴──────────┘");
            System.out.println(e.getMessage());
            System.out.print("- [ENTER] para continuar... ");
            sc.nextLine();
            return;
        }
        

        System.out.println("└──┴──────────┴──────────┴──────────┴──────────┴──────────┴──────────┘");

        // Imprimir resultado
        System.out.printf("%s = %s %s\n", concepto, secante.raiz(), unidad);
        System.out.print("- [ENTER] para continuar... ");
        sc.nextLine();
    }

    public static void metodoNewtonRaphson() {
        // Escribir el problema
        System.out.printf("Pregunta: %s\n\n", pregunta);

        // Imprimir cabecera de la tabla
        System.out.println("┌──┬──────────┬──────────┬──────────┬──────────┬──────────┐");
        System.out.println("│N°│    x1    │   f(x1)  │  f'(x1)  │    x2    │   f(x2)  │");
        System.out.println("├──┼──────────┼──────────┼──────────┼──────────┼──────────┤");

        NewtonRaphson newtonRaphson = new NewtonRaphson(0.3, 0.00001, 50);

        // Imprimir hasta que el error sea suficiente para finalizar
        try {
            while (newtonRaphson.calcularError()) {
                double[] resultados = newtonRaphson.nextCalc();
                newtonRaphson.printLine(resultados);
            }
        } catch (DemasiadosCalculosException e) {
            System.out.println("└──┴──────────┴──────────┴──────────┴──────────┴──────────┘");
            System.out.println(e.getMessage());
            System.out.print("- [ENTER] para continuar... ");
            sc.nextLine();
            return;
        }

        System.out.println("└──┴──────────┴──────────┴──────────┴──────────┴──────────┘");

        // Imprimir resultado
        System.out.printf("%s = %s %s\n", concepto, newtonRaphson.raiz(), unidad);
        System.out.print("- [ENTER] para continuar... ");
        sc.nextLine();
    }


}