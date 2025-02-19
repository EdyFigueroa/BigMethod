/**
 * CLASE MAIN
 */

 import java.util.Scanner;

public class BigMethod {
    static Scanner sc = new Scanner(System.in);

    public static void main(String [] args) {
        printHojaPresentacion();

        System.out.println("\nPregunta del problema: ___");
    }

    public static void printHojaPresentacion() {
        // Datos de la escuela
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
    }
}