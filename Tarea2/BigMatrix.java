import java.text.DecimalFormat;
import java.util.Scanner;

public class BigMatrix {
    static Scanner sc = new Scanner(System.in);

    // Aquí almacenaremos los datos del problema
    static String pregunta;
    static int orden = 0;
    static String concepto;

    public static void main(String [] args) {
        pantallaDeInicio(); // Imprimir pantalla de inicio

        // SECCIÓN 1: PEDIR DATOS ---------------------------------------------

        // Pedir la pregunta
        /*System.out.print("- Pregunta: > ");
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
        concepto = sc.next();*/

        orden = 4;

        // SECCIÓN 2: PEDIR TODA LA MATRIZ ------------------------------------

        System.out.println("=================================================================");

        // Variable matriz de tamaño orden
        double [][] matriz = {
            {20, 10, 9, 9, 125*100},
            {8, 18, 6, 14, 136*100},
            {8, 8, 16, 12, 144*100},
            {8, 8, 9, 24, 133*100}
        };

        String [] nombresVariables = {
            "Peces hacha", "Peces gato", "Peces mono", "Peces lagarto"
        };

        // Pedir todas las variables
        /*double [][] matriz = new double[orden][orden+1];
        for (int i = 0; i <= orden-1; i++) {
            System.out.println("ECUACIÓN #" + (i + 1));

            for (int ii = 0; ii <= orden; ii++) {
                if (ii == orden) {
                    System.out.print("  > Resultado = ");
                } else {
                    System.out.print("  > x" + (ii + 1) + " = ");
                }
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

        do { // Comienza ciclo del menú
            opcion = sc.nextInt();

            switch (opcion) {
                case 1:
                    // GAUSS-JORDAN ---------------------------------------------------------------
                    System.out.println("=================================================================");
                    System.out.println("                          GAUSS JORDAN");

                    // Imprimir matriz original
                    System.out.println("1. Matriz de datos:");
                    imprimirMatriz(matriz, nombresVariables);

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

                    // Imprimir la matriz con ceros abajo de la diagonal
                    System.out.println("2. Matriz con ceros abajo de la diagonal:");
                    imprimirMatriz(matriz, nombresVariables);

                    // Hacer ceros arriba de la diagonal
                    for (int k = orden-1; k >= 0; k--) {
                        double pivote = matriz[k][k];
                        for (int f = k-1; f >= 0; f--) {
                            double factor = matriz[f][k] / pivote;
                            for (int c = 0; c < orden+1; c++) {
                                matriz[f][c] = matriz[f][c] - (factor * matriz[k][c]);
                            }
                        }
                    }

                    // Imprimir la matriz con ceros arriba de la diagonal
                    System.out.println("3. Matriz con ceros abajo y arriba de la diagonal:");
                    imprimirMatriz(matriz, nombresVariables);

                    // Hacer 1 la diagonal
                    for (int k = 0; k < orden; k++) {
                        double pivote = matriz[k][k];
                        for (int c = 0; c < orden+1; c++) {
                            matriz[k][c] = matriz[k][c] / pivote;
                        }
                    }

                    // Imprimir la matriz resultante
                    System.out.println("4. Matriz identidad:");
                    imprimirMatriz(matriz, nombresVariables);

                    // Imprimir la solución
                    System.out.println("La solución es:");
                    for (int i = 0; i < orden; i++) {
                        System.out.println("x" + (i + 1) + " = " + matriz[i][orden] + " " + nombresVariables[i]);
                    }

                    // Esperar a que el usuario presione enter para continuar
                    System.out.print("\n- Presione [ENTER] para continuar... ");
                    sc.nextLine(); sc.nextLine();

                    // Imprimir el menú
                    System.out.println("=================================================================");
                    imprimirMenu();
                    break;

                case 2:
                    // GAUSS-SEIDEL ---------------------------------------------------------------
                    System.out.println("=================================================================");
                    System.out.println("                          GAUSS SEIDEL");

                    // Pedir datos iniciales
                    /*double [] x = new double[orden];
                    for (int i = 0; i < orden; i++) {
                        System.out.print(" > x" + (i + 1) + " = ");
                        x[i] = sc.nextDouble();
                    }

                    // Pedir el error
                    System.out.print(" > Error = ");
                    double error = sc.nextDouble();

                    // Pedir el número de iteraciones
                    System.out.print(" > Número de cálculos = ");
                    int calculos = sc.nextInt();*/

                    double [] vAnt = {50, 50, 50, 50};
                    double [] vAct = {0, 0, 0, 0};
                    double errorTotal = 0;
                    double errorPermitido = 1;
                    int calculosPermitidos = 50;

                    // Imprimir cabecera de la tabla
                    String str = "┌──┬";
                    for (int i = 0; i < orden+1; i++) {
                        for (int ii = 0; ii < 15; ii++) {
                            str += "─"; // Imprimir tantos guiones como letras tenga la variable
                        }

                        // Imprimir el separador de columnas, dependiendo si es la última columna o no
                        if (i != orden) str += "┬";
                        else str += "┐\n";
                    }

                    // Imprimir el nombre de todas las variables
                    str += "│N°";
                    for (int i = 0; i < orden; i++) {
                        str += String.format("│%15s", nombresVariables[i]);
                    }
                    str += "│    Error Total│\n";

                    // Imprimir el separador de columnas
                    str += "├──┼";
                    for (int i = 0; i < orden+1; i++) {
                        for (int ii = 0; ii < 15; ii++) {
                            str += "─"; // Imprimir tantos guiones como letras tenga la variable
                        }

                        // Imprimir el separador de columnas, dependiendo si es la última columna o no
                        if (i != orden) str += "┼";
                        else str += "┤\n";
                    }

                    // Imprimir los valores iniciales
                    str += "│ 0";
                    for (int i = 0; i < orden; i++) {
                        str += String.format("│%15s", formatNumber(vAnt[i], 15)); 
                    }
                    double _suma = 0;
                    for (int i = 0; i < orden; i++) {
                        _suma += vAnt[i];
                    }
                    str += String.format("│%15s", formatNumber(_suma, 15));
                    str += "│\n";

                    // AHORA SÍ COMENZAR LAS ITERACIONES --------------------------------------------------------------
                    int calculosHechos = 0;
                    do {

                        calculosHechos++;

                        // 1. Imprimir el número de iteración
                        str += String.format("│%2d", calculosHechos);

                        for (int f = 0; f < orden; f++) {
                            // Valores iniciales antes de las iteraciones
                            double suma = 0;
                            double coef = matriz[f][f];
                            suma += matriz[f][orden];

                            for (int c = 0; c < orden; c++) {
                                if (c != f) {
                                    if (c < f) {
                                       suma += (matriz[f][c] * -1.0) * vAct[c]; 
                                    } else {
                                        suma += (matriz[f][c] * -1.0) * vAnt[c];
                                    }
                                } 
                            }

                            suma = suma / coef;
                            vAct[f] = suma;
                        }

                        // Calculamos ahora el error total
                        errorTotal = 0;
                        for (int p = 0; p < orden; p++) {
                            errorTotal += Math.abs(Math.abs(vAct[p]) - Math.abs(vAnt[p]));
                        }

                        // Una vez que se han hecho todos los cálculos, imprimimos la tabla
                        for (int p = 0; p < orden; p++) {
                            str += String.format("│%15s", formatNumber(vAct[p], 15));
                            vAnt[p] = vAct[p];
                        }
                        str += String.format("│%15s", formatNumber(errorTotal, 15));
                        str += "│\n";
                    } while (calculosHechos <= calculosPermitidos && errorTotal > errorPermitido);

                    // Imprimir el pie de la tabla
                    str += "└──┴";
                    for (int i = 0; i < orden+1; i++) {
                        for (int ii = 0; ii < 15; ii++) {
                            str += "─"; // Imprimir tantos guiones como letras tenga la variable
                        }  

                        // Imprimir el separador de columnas, dependiendo si es la última columna o no
                        if (i!= orden) str += "┴";
                        else str += "┘\n";
                    }

                    // IMPRIMIMOS TODA LA TABLA
                    System.out.println(str);

                    

                    // Imprimimos un mensaje si se alcanzó el máximo de iteraciones
                    if (calculosHechos > calculosPermitidos) {
                            System.out.println("Se alcanzó el máximo de cálculos permitidos.");
                            System.out.println("La solución a la que mejor se aproximó es:");
                    } else {
                        System.out.println("La solución es:"); 
                    }

                    // Imprimimos la solución
                    for (int i = 0; i < orden; i++) {
                        System.out.println("x" + (i + 1) + " = " + vAct[i] + " " + nombresVariables[i]);
                    }

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

    public static void imprimirMatriz(double [][] matriz, String [] conceptos) {
        int variables = matriz[0].length;
        int trunque = 15;
        
        // Añadimos un concepto más a conceptos[]: Resultados
        String [] conceptos2 = new String[variables];
        for (int i = 0; i < conceptos.length; i++) {
            conceptos2[i] = conceptos[i];
        }
        conceptos2[variables-1] = "Resultado";
    
        // Imprimir primero el encabezado que se acomode al número de variables
        String str = "┌";
        for (int i = 0; i < variables; i++) {
            for (int ii = 0; ii < Math.max(conceptos2[i].length(), trunque); ii++) {
                str += "─"; // Imprimir tantos guiones como letras tenga la variable
            }
    
            // Imprimir el separador de columnas, dependiendo si es la última columna o no
            if (i != variables-1) str += "┬";
            else str += "┐\n";
        }
    
        // Imprimir el nombre de todas las variables
        for (int i = 0; i < variables; i++) {
            str += String.format("│%15s", conceptos2[i]);
        }
        str += "│\n";
    
        // Imprimir el separador de columnas
        str += "├";
        for (int i = 0; i < variables; i++) {
            for (int ii = 0; ii < Math.max(conceptos2[i].length(), trunque); ii++) {
                str += "─"; // Imprimir tantos guiones como letras tenga la variable
            }
    
            // Imprimir el separador de columnas, dependiendo si es la última columna o no
            if (i != variables-1) str += "┼";
            else str += "┤\n";
        }
    
        // AHORA SÍ, IMPRIMIR EL CONTENIDO DE LA MATRIZ
        for (int i = 0; i < matriz.length; i++) {
            for (int ii = 0; ii < variables; ii++) {
                String format = "│%" + Math.max(conceptos2[ii].length(), trunque) + "s";
                str += String.format(format, formatNumber(matriz[i][ii], trunque));
            }
            str += "│\n";
        }
    
        // Imprimir el pie de la tabla
        str += "└";
        for (int i = 0; i < variables; i++) {
            for (int ii = 0; ii < Math.max(conceptos2[i].length(), trunque); ii++) {
                str += "─"; // Imprimir tantos guiones como letras tenga la variable
            }
    
            // Imprimir el separador de columnas, dependiendo si es la última columna o no
            if (i != variables-1) str += "┴";
            else str += "┘\n";
        }
    
        System.out.println(str);
    }

    public static String formatNumber(double numero, int caracteres) {
        if (numero == 0.0) {
            numero = 0.0; // Evita que por alguna razón se escriba -0 en vez de 0 XD
        }
        DecimalFormat df = new DecimalFormat("0.#########"); // Evita notación científica
        String s = df.format(numero); // Convierte el número a string

        if (s.length() < caracteres) {
            s = String.format("%" + caracteres + "s", s); // Rellena con espacios a la izquierda
        } else if (s.length() > caracteres) {
            s = s.substring(0, caracteres); // Trunca si es demasiado largo
        }

        return s; 
    }
}