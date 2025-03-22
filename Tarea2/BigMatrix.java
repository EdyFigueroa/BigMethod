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
        concepto = sc.next();

        // SECCIÓN 2: PEDIR TODA LA MATRIZ ------------------------------------

        System.out.println("=================================================================");

        // Variable matriz de tamaño orden
        double [][] matriz = {
            {20, 10, 9, 9, 125*100},
            {8, 18, 6, 14, 136*100},
            {8, 8, 9, 24, 133*100},
            {8, 8, 16, 12, 144*100}
        };

        // Pedir todas las variables
        /*double [][] matriz = new double[orden][orden];
        for (int i = 0; i <= orden-1; i++) {
            System.out.println("ECUACIÓN #" + (i + 1));

            for (int ii = 0; ii <= orden-1; ii++) {
                System.out.print("  > x" + (ii + 1) + " = ");
                double valor = sc.nextDouble();

                // Insertamos el valor dado en la matriz
                matriz[i][ii] = valor;
            }
        }*/

        // SECCIÓN 3: MENÚ ----------------------------------------------------

        System.out.println("=================================================================");

        int opcion;
        imprimirMenu();
        System.out.print("- Teclee su opción: > ");

        do {
            opcion = sc.nextInt();

            switch (opcion) {
                case 1:
                    // GAUSS-JORDAN ---------------------------------------------------------------
                    System.out.println("=================================================================");
                    System.out.println("                          GAUSS JORDAN");

                    // Hacer ceros abajo de la diagonal
                    for (int k = 0; k < orden; k++) {
                        double pivote = matriz[k][k];
                        for (int f = k+1; f < orden; f++) {
                            double ecero = matriz[f][k];
                            for (int c = 0; c < orden+1; c++) {
                                matriz[f][c] = (pivote * matriz[f][c]) - (ecero * matriz[k][c]);
                            }
                        }
                    }

                    // Hacer ceros arriba de la diagonal
                    for (int k = orden-1; k >= 0; k--) {
                        double pivote = matriz[k][k];
                        for (int f = k-1; f >= 0; f--) {
                            double ecero = matriz[f][k];
                            for (int c = 0; c < orden+1; c++) {
                                matriz[f][c] = (pivote * matriz[f][c]) - (ecero * matriz[k][c]);
                            }
                        }
                    }

                    // Hacer 1 la diagonal
                    for (int k = 0; k < orden; k++) {
                        double pivote = matriz[k][k];
                        for (int c = 0; c < orden+1; c++) {
                            matriz[k][c] = matriz[k][c] / pivote;
                        }
                    }

                    // Imprimir la matriz resultante
                    imprimirMatriz(matriz);

                    // Imprimir la solución
                    System.out.println("La solución es:");
                    for (int i = 0; i < orden; i++) {
                        System.out.println("x" + (i + 1) + " = " + matriz[i][orden] + " " + concepto);
                    }

                    // Esperar a que el usuario presione enter para continuar
                    System.out.print("\n- Presione [ENTER] para continuar... ");
                    sc.nextLine(); sc.nextLine();

                    // Imprimir el menú
                    System.out.println("=================================================================");
                    imprimirMenu();
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

    public static void imprimirMatriz(double [][] matriz) {
        for (int i = 0; i < orden; i++) {
            System.out.print("| ");
            for (int j = 0; j < orden + 1; j++) {
                System.out.printf("%10.2f ", matriz[i][j]); // Formato para alinear los números
            }
            System.out.println("|");
        }
        System.out.println();
    }
}