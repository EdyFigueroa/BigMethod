import java.util.Scanner;

public class BigMatrix {
    static Scanner sc = new Scanner(System.in);

    static String pregunta;
    static int orden = 0;
    static String concepto;

    public static void main(String [] args) {
        pantallaDeInicio(); // Imprimir pantalla de inicio

        // SECCIÓN 1: PEDIR DATOS ---------------------------------------------

        // Pedir la pregunta
        System.out.print("- Pregunta: > ");
        pregunta = sc.next();

        // Pedir el orden + validarlo del 2 al 10
        do {
            System.out.print("- Orden: > ");
            orden = sc.nextInt();
        
            if (orden < 2 || orden > 10) {
                System.out.println("/!\\ Por favor, introduzca un número entre el 2 y el 10.");
            }
        } while (orden < 2 || orden > 10);

        // Pedir el concepto
        System.out.print("- Concepto: > ");
        pregunta = sc.next();

        // SECCIÓN 2: PEDIR TODA LA MATRIZ ------------------------------------

        System.out.println("=================================================================");

        // Variable matriz de tamaño orden
        double [][] matriz = new double[orden][orden];

        // Pedir todas las variables
        for (int i = 0; i <= orden-1; i++) {
            System.out.println("ECUACIÓN #" + (i + 1));

            for (int ii = 0; ii <= orden-1; ii++) {
                System.out.print("  > x" + (ii + 1) + " = ");
                double valor = sc.nextDouble();

                // Insertamos el valor dado en la matriz
                matriz[i][ii] = valor;
            }
        }

        // SECCIÓN 3: MENÚ ----------------------------------------------------

        System.out.println("=================================================================");

        int opcion;
        imprimirMenu();
        System.out.print("- Teclee su opción: > ");

        do {
            opcion = sc.nextInt();

            switch (opcion) {
                case 1:
                    // GAUSS-JORDAN -------------------------------------------

                    break;

                case 2:
                    System.out.println("Abajo gauss seibei xddd");
                    break;

                case 10:
                    System.out.println("Adiós papu :\"v");
                    break;
            
                default:
                    // Caso en el que la opción no esté en el menú
                    System.out.print("- /!\\ Teclee una opción valida: > ");
                    break;
            }
        } while (opcion != 10);
    }

    // MÉTODOS AUXILIARES -----------------------------------------------------

    public static void pantallaDeInicio() {
        // Datos de la escuela
        System.out.println("=================================================================");
        System.out.println("              Instituto Tecnológico de Culiacán");
        System.out.println("               Ing en Sistemas Computacionales");

        // Datos del alumno
        System.out.println("\nFigueroa Lizárraga Edy Jared");
        System.out.println("Solución de Sistema de Ecuaciones");
        System.out.println("De 9:00 a 10:00 horas");

        // Descripción de la tarea
        System.out.println("\n          Este programa ejecuta la solución de problemas\r\n" + //
                        "     utilizando los procesos lógicos de Solución de sistemas\r\n" + //
                        "       de Ecuaciones utilizando diversos métodos numéricos.");
        System.out.println("=================================================================");
    }

    public static void imprimirMenu() {
        System.out.println("[1] Método de Gauss Jordan");
        System.out.println("[2] Método de Gauss Seidel");
        System.out.println("[10] F I N\n");
    }
}