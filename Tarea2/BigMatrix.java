import java.text.DecimalFormat; // Lo uso para formatear los números
import java.util.Scanner; // Lo uso para leer datos del usuario

// CLASE PRINCIPAL -----------------------------------------------------------

/**
 * Tengo que admitir que este programa es muy confuso,
 * trataré de facilitar la revisión marcando los puntos claves del programa con macatextos
 */

public class BigMatrix {
    static Scanner sc = new Scanner(System.in);

    // Aquí almacenaremos los datos del problema
    static String pregunta; // Pregunta del problema
    static int orden = 0; // Orden de la matriz
    static String unidades; // Unidades que maneja el problema
    static String [] conceptos; // Conceptos de las variables

    public static void main(String [] args) {
        pantallaDeInicio(); // Imprimir pantalla de inicio

        // SECCIÓN 1: PEDIR DATOS ---------------------------------------------

        // Pedir la pregunta
        System.out.print("- Pregunta: > ");
        pregunta = sc.nextLine();

        // Pedir el orden + validarlo del 2 al 10
        do {
            System.out.print("- Orden: > ");
            orden = sc.nextInt();
        
            if (orden < 2 || orden > 10) {
                System.out.println("/!\\ Por favor, introduzca un número entre el 2 y el 10.");
            }
        } while (orden < 2 || orden > 10);

        // Una vez ya con el orden, inicializamos el arreglo de conceptos
        conceptos = new String[orden+1];

        // Pedir las unidades
        System.out.print("- Unidades: > ");
        unidades = sc.next();

        // SECCIÓN 2: PEDIR TODA LA MATRIZ ------------------------------------

        System.out.println("=================================================================");

        // Pedir todas las variables
        double [][] matriz = new double[orden][orden+1]; // Creamos la matriz
        for (int i = 0; i < orden; i++) { // Iterar sobre las filas (ecuaciones)
            System.out.println("ECUACIÓN #" + (i + 1));

            for (int ii = 0; ii <= orden; ii++) { // Iterar sobre las columnas
                // Leer el valor de la columna o del resultado dependiendo si es la última columna o no
                System.out.print(ii == orden ? "  > Resultado = " : "  > x" + (ii + 1) + " = ");
                matriz[i][ii] = sc.nextDouble();
            }

            System.out.print("  > Concepto = ");
            conceptos[i] = sc.next(); sc.next();
        }

        // Añadir la columna resultado
        conceptos[orden] = "Resultado";

        // SECCIÓN 3: MENÚ ----------------------------------------------------

        System.out.println("=================================================================");

        int opcion;
        imprimirMenu();
        System.out.print("- Teclee su opción: > ");

        do { // Comienza ciclo del menú
            opcion = sc.nextInt(); // Leer la opción del usuario

            switch (opcion) {
                case 1:
                    // GAUSS-JORDAN ---------------------------------------------------------------
                    System.out.println("=================================================================");
                    System.out.println("GAUSS JORDAN");

                    // Imprimir la pregunta
                    System.out.println(pregunta + "\n");

                    // Creamos una copia de la matriz original para no modificarla en el proceso
                    // y para poder seguirla utilizando en los otros métodos.
                    double[][] _matriz = new double[orden][orden + 1];
                    for (int i = 0; i < orden; i++) {
                        System.arraycopy(matriz[i], 0, _matriz[i], 0, orden + 1);
                    }

                    // Imprimir matriz original
                    System.out.println("1. Matriz de datos:");
                    imprimirMatriz(_matriz, conceptos);

                    // Hacer ceros abajo de la diagonal
                    for (int k = 0; k < orden; k++) { // Iterar sobre las filas
                        double pivote = _matriz[k][k]; // El pivote es el valor de la diagonal
                        for (int f = k+1; f < orden; f++) { // Iterar sobre las filas debajo de la diagonal
                            double ecero = _matriz[f][k]; // El valor de la columna
                            for (int c = 0; c < orden+1; c++) { // Iterar sobre las columnas
                                // Multiplicamos el valor de la columna por el pivote
                                // y le restamos el valor de la columna de la fila actual
                                _matriz[f][c] = (pivote * _matriz[f][c]) - (ecero * _matriz[k][c]);
                            }
                        }
                    }

                    // Imprimir la matriz con ceros abajo de la diagonal
                    System.out.println("2. Matriz con ceros abajo de la diagonal:");
                    imprimirMatriz(_matriz, conceptos);

                    // Hacer ceros arriba de la diagonal
                    for (int k = orden-1; k >= 0; k--) { // Iterar sobre las filas
                        double pivote = _matriz[k][k]; // El pivote es el valor de la diagonal
                        for (int f = k-1; f >= 0; f--) { // Iterar sobre las filas arriba de la diagonal
                            double factor = _matriz[f][k] / pivote; // El factor es el valor de la columna dividido por el pivote
                            for (int c = 0; c < orden+1; c++) {
                                // Restamos el valor de la columna por el valor de la columna de la fila actual
                                _matriz[f][c] -= (factor * _matriz[k][c]);
                            }
                        }
                    }

                    // Imprimir la matriz con ceros arriba de la diagonal
                    System.out.println("3. Matriz con ceros abajo y arriba de la diagonal:");
                    imprimirMatriz(_matriz, conceptos);

                    // Hacer 1 la diagonal
                    for (int k = 0; k < orden; k++) { // Iterar sobre las filas
                        double pivote = _matriz[k][k]; // El pivote es el valor de la diagonal
                        for (int c = 0; c < orden+1; c++) { // Iterar sobre las columnas
                            // Dividimos el valor de la columna por el pivote
                            _matriz[k][c] = _matriz[k][c] / pivote;
                        }
                    }

                    // Imprimir la matriz resultante
                    System.out.println("4. Matriz identidad:");
                    imprimirMatriz(_matriz, conceptos);

                    // Imprimir la solución
                    System.out.println("La solución es:");
                    for (int i = 0; i < orden; i++) {
                        System.out.printf("%15s = %.0f %s\n", conceptos[i], _matriz[i][orden], unidades);
                    }

                    // Esperar a que el usuario presione enter para continuar
                    System.out.print("\n- Presione [ENTER] para continuar... ");
                    sc.nextLine(); sc.nextLine();

                    // Imprimir el menú
                    System.out.println("=================================================================");
                    imprimirMenu();
                    System.out.print("- Teclee su opción: > ");
                    break;

                case 2:
                    // GAUSS-SEIDEL ---------------------------------------------------------------
                    System.out.println("=================================================================");
                    System.out.println("GAUSS SEIDEL");
                    System.out.println(pregunta + "\n");

                    // Pedir datos iniciales
                    double [] vAnt = new double[orden];
                    double [] vAct = new double[orden];
                    for (int i = 0; i < orden; i++) {
                        System.out.print(" > x" + (i + 1) + " = ");
                        vAnt[i] = sc.nextDouble();
                        vAct[i] = 0;
                    }

                    // Pedir el error
                    System.out.print(" > Error = ");
                    double errorPermitido = sc.nextDouble();
                    double errorTotal = 0;

                    // Pedir el número de iteraciones
                    System.out.print(" > Número de cálculos = ");
                    int calculosPermitidos = sc.nextInt();

                    // Imprimir cabecera de la tabla
                    String str = "┌──┬";
                    for (int i = 0; i < orden+1; i++) {
                        str += "───────────────";

                        // Imprimir el separador de columnas, dependiendo si es la última columna o no
                        if (i != orden) str += "┬";
                        else str += "┐\n";
                    }

                    // Imprimir el nombre de todas las variables
                    str += "│N°";
                    for (int i = 0; i < orden; i++) {
                        str += String.format("│%15s", conceptos[i]);
                    }
                    str += "│    Error Total│\n";

                    // Imprimir el separador de columnas
                    str += "├──┼";
                    for (int i = 0; i < orden+1; i++) {
                        str += "───────────────";

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
                        calculosHechos++; // Incrementar el número de iteraciones
                        str += String.format("│%2d", calculosHechos); // Imprimir el número de iteraciones

                        // CALCULAR LOS NUEVOS VALORES
                        for (int f = 0; f < orden; f++) { // Iterar sobre las filas
                            // Valores iniciales antes de las iteraciones
                            double suma = 0;
                            double coef = matriz[f][f];
                            suma += matriz[f][orden];

                            for (int c = 0; c < orden; c++) { // Iterar sobre las columnas
                                if (c != f) { // Si no es la diagonal
                                    if (c < f) { // Si es una columna anterior a la diagonal
                                       // Suma += coeficiente * valor actual
                                       suma += (matriz[f][c] * -1.0) * vAct[c]; 
                                    } else {
                                        // Suma += coeficiente * valor anterior
                                        suma += (matriz[f][c] * -1.0) * vAnt[c];
                                    }
                                } 
                            }

                            suma = suma / coef; // Dividimos la suma por el coeficiente
                            vAct[f] = suma; // Guardamos los valores actuales
                        }

                        // CALCULAR EL ERROR TOTAL
                        errorTotal = 0;
                        for (int p = 0; p < orden; p++) { // Iterar sobre las filas
                            // Error total += valor actual - valor anterior
                            errorTotal += Math.abs(Math.abs(vAct[p]) - Math.abs(vAnt[p]));
                        }

                        // Una vez que se han hecho todos los cálculos, imprimimos la tabla
                        for (int p = 0; p < orden; p++) {
                            str += String.format("│%15s", formatNumber(vAct[p], 15));
                            vAnt[p] = vAct[p]; // Guardamos los valores actuales en los anteriores
                        }
                        str += String.format("│%15s", formatNumber(errorTotal, 15));
                        str += "│\n";
                    
                    // Esto se hará mientras los cálculos sean menores que los permitidos y el error sea mayor al permitido
                    } while (calculosHechos <= calculosPermitidos && errorTotal > errorPermitido);

                    // Imprimir el pie de la tabla
                    str += "└──┴";
                    for (int i = 0; i < orden+1; i++) {
                        str += "───────────────";

                        // Imprimir el separador de columnas, dependiendo si es la última columna o no
                        if (i!= orden) str += "┴";
                        else str += "┘\n";
                    }

                    // IMPRIMIMOS TODA LA TABLA
                    System.out.println(str);

                    // Imprimimos un mensaje si se alcanzó el máximo de iteraciones
                    if (calculosHechos > calculosPermitidos) {
                        System.out.println("Se alcanzó el máximo de cálculos permitidos.");
                    }

                    // Imprimimos la solución
                    System.out.println("La solución aproximada es:");
                    for (int i = 0; i < orden; i++) {
                        System.out.printf("%15s = %.5f %s\n", conceptos[i], vAct[i], unidades);
                    }

                    // Esperar a que el usuario presione enter para continuar
                    System.out.print("\n- Presione [ENTER] para continuar... ");
                    sc.nextLine(); sc.nextLine();

                    // Imprimir el menú
                    System.out.println("=================================================================");
                    imprimirMenu();
                    System.out.print("- Teclee su opción: > ");
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
    
        // Imprimir primero el encabezado que se acomode al número de variables
        String str = "┌";
        for (int i = 0; i < variables; i++) {
            str += "───────────────";
    
            // Imprimir el separador de columnas, dependiendo si es la última columna o no
            if (i != variables-1) str += "┬";
            else str += "┐\n";
        }
    
        // Imprimir el nombre de todas las variables
        for (int i = 0; i < variables; i++) {
            str += String.format("│%15s", conceptos[i]);
        }
        str += "│\n";
    
        // Imprimir el separador de columnas
        str += "├";
        for (int i = 0; i < variables; i++) {
            str += "───────────────";
    
            // Imprimir el separador de columnas, dependiendo si es la última columna o no
            if (i != variables-1) str += "┼";
            else str += "┤\n";
        }
    
        // AHORA SÍ, IMPRIMIR EL CONTENIDO DE LA MATRIZ
        for (int i = 0; i < matriz.length; i++) {
            for (int ii = 0; ii < variables; ii++) {
                String format = "│%" + Math.max(conceptos[ii].length(), trunque) + "s";
                str += String.format(format, formatNumber(matriz[i][ii], trunque));
            }
            str += "│\n";
        }
    
        // Imprimir el pie de la tabla
        str += "└";
        for (int i = 0; i < variables; i++) {
            str += "───────────────";
    
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