/**
 * CLASE MAIN
 */

 import java.util.Scanner;

public class BigMethod {
    static Scanner sc = new Scanner(System.in);

    public static void main(String [] args) {
        printHojaPresentacion();

        // Preguntar por los parámetros del problema
        System.out.print("- Pregunta del problema: > ");
        String pregunta = sc.nextLine();
        System.out.print("- Concepto: > ");
        String concepto = sc.nextLine();
        System.out.print("- Unidad de medida: > ");
        String unidad = sc.nextLine();

        // Mostrar el menú
        System.out.println("=================================================================");
        imprimirMenu();
        System.out.print("- Teclee su opción: > ");

        int opcion = 0;        
        while (opcion != 10) {
            opcion = sc.nextInt();

            switch (opcion) {
                case 1:
                    // Método de la secante
                    System.out.println("=================================================================");
                    System.out.println("                      MÉTODO DE LA SECANTE\n");
                    metodoSecante();
                    break;

                case 2:
                    System.out.println("Metodo de Newton rapsody seleccionado. Turbo nice!");
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
        // Imprimir cabecera de la tabla
        System.out.println("┌──┬──────────┬──────────┬──────────┬──────────┬──────────┬──────────┐");
        System.out.println("│N°│    x1    │   f(x1)  │    x2    │   f(x2)  │    x3    │   f(x3)  │");
        System.out.println("├──┼──────────┼──────────┼──────────┼──────────┼──────────┼──────────┤");

        Secante secante = new Secante(0.1, 0.6, 0.00001, 40);
        double[] resultados = secante.nextCalc();

        // Imprimir nueva línea
        secante.printLine(resultados);
    }


}