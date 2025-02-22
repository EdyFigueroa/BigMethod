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
        int opcion = 0;
        while (opcion != 10) {
            imprimirMenu();
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
}