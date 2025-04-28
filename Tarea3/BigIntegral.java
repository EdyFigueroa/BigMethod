package Tarea3;

import java.text.DecimalFormat;
import java.util.Scanner;

public class BigIntegral {
    // Scanner para leer datos del teclado
    static Scanner sc = new Scanner(System.in);

    // Atributos
    static String pregunta; // Se almacena la pregunta
    static String unidades; // TODO: Pedir unidades
    static double valorReal; // Almacenna el valor real para saber cuándo acabar el método
    static double limInferior; // Límite inferior
    static double limSuperior; // Límite superior
    static int trapecios; // Trapecios
    static int maxCalculos; // Cálculos máximos
    static double error; // Error bruh

    public static void main(String[] args) {
        pantallaDeInicio();

        // Pedir datos ----------------------------------------------------
        /*System.out.print("- Pregunta: > ");
        pregunta = sc.nextLine();

        System.out.print("- Límite inferior: > ");
        limInferior = sc.nextInt();

        System.out.print("- Límite superior: > ");
        limSuperior = sc.nextInt();

        System.out.print("- Número de trapecios: > ");
        trapecios = sc.nextInt();*/

        // TODO: Quitar esto al final
        pregunta = "¿Cuál será el crecimiento poblacional durante el mes de junio al mes de septiembre\r\n" + //
                "del año?";
        valorReal = 244.37;
        limInferior = 5;
        limSuperior = 9;
        trapecios = 3;
        maxCalculos = 20;
        error = 0.001;
        unidades = "u^2";

        // Imprimir cabecera de la tabla
        System.out.println("=================================================================");
        System.out.println("                     MÉTODO DE LOS TRAPECIOS\n");
        System.out.println("Pregunta: " + pregunta);

        System.out.println("┌──────────┬──────────┬──────────┬──────────┬──────────┬──────────┐");
        System.out.println("│ Trapecio │     a    │   a+h    │   f(a)   │  f(a+h)  │   Área   │");
        System.out.println("├──────────┼──────────┼──────────┼──────────┼──────────┼──────────┤");

        double areaTotal = 0; // En esta variable se almacenará el área calculada
        double errorCalculado; // El error de nuestro método
        int calculos = 0; // Para llevar cuenta de cuántos cálculos hemos hecho
        int trapeciosIteracion = trapecios; // Trapecios iniciales
        String[] filas; // Acá guardaremos las filas de cuando encontremos la mejor aproximación

        // Haremos este método mientras
        // 1. No hayamos alcanzado los cálculos máximos
        // 2. El error permitido ha sido cubierto
        do {
            areaTotal = 0; // El área comienza en cero
            double trapEmpieza = limInferior; // El primer trapecio empieza en el límite inferior

            // Calculamos el salto entre trapecios con la fórmula
            // h = (b-a)/n
            double salto = (limSuperior - limInferior) / trapeciosIteracion;

            // Filas que se sacaron en esta iteración, para imprimirlas si resulta ser que esta es la mejor aproximación
            filas = new String[trapeciosIteracion];

            // Comenzamos a calcular el área de cada trapecio
            // Para cada trapecio en esta iteración...
            for (int i = 1; i <= trapeciosIteracion; i++) {
                // Este trapecio termina en...
                double trapTermina = trapEmpieza + salto;
    
                // Evaluamos la función en los límites del trapecio
                double fa = 40 + 8 * Math.sqrt(trapEmpieza);
                double fab = 40 + 8 * Math.sqrt(trapTermina);
    
                // Calculamos el área del trapecio con la fórmula
                // (h/2) * [ f(a) + f(a+b) ]
                double areaTrapecio = (salto/2)*(fa+fab);
    
                // Lo sumamos al área total
                areaTotal += areaTrapecio;
    
                // Añadimos el String de la fila
                filas[i-1] = String.format("│#%9d│%10s│%10s│%10s│%10s│%10s│",
                    i, formatNumber(trapEmpieza, 9),
                    formatNumber(trapTermina, 9),
                    formatNumber(fa, 9),
                    formatNumber(fab, 9),
                    formatNumber(areaTrapecio, 9));
                
                // El siguiente trapecio empieza en...
                trapEmpieza = trapTermina;
            }

            // Calculamos el error que se obtuvo
            errorCalculado = Math.abs(areaTotal - valorReal);

            calculos++; // Sumamos un cálculo
            trapeciosIteracion *= 2; // Doblamos los trapecios para la siguiente iteración

        } while (calculos < maxCalculos && errorCalculado >= error);

        // Imprimir las filas de la tabla
        for (String fila : filas) {
            System.out.println(fila);
        }

        // Pie de la tabla
        System.out.println("├──────────┴──────────┴──────────┴──────────┴──────────┼──────────┤");
        System.out.printf("│                                           Área total │%10s│ %s\n",
            formatNumber(areaTotal, 9), unidades);
        System.out.println("└──────────────────────────────────────────────────────┴──────────┘");

        // Mensaje de por qué se termino el método
        if (calculos >= maxCalculos) {
            System.out.println("Se alcanzó el número máximo de cálculos permitidos.");
        } else {
            System.out.println("Se econtró la mejor aproximación.");
        }

        System.out.println("-------------------------------------------------------");
        System.out.printf("Número inicial de trapecios: %d\n", trapecios);
        System.out.printf("Número final de trapecios: %d\n", trapeciosIteracion);
        System.out.printf("Número de procesos: %d\n", calculos);
        System.out.printf("Valor real de la integral: %f\n", valorReal);
        System.out.printf("Valor calculado por trapecios: %f\n", areaTotal);
        System.out.printf("Error del problema: %f\n", error);
        System.out.printf("Error del método: %f\n", errorCalculado);

    }

    //////////////////////////
    /// MÉTODOS AUXILIARES ///
    //////////////////////////
    
    /*
     * Imprime la pantalla de inicio
     */
    public static void pantallaDeInicio() {
        // Datos de la escuela
        System.out.println("=================================================================");
        System.out.println("              Instituto Tecnológico de Culiacán");
        System.out.println("               Ing en Sistemas Computacionales");

        // Datos del alumno
        System.out.println("\nFigueroa Lizárraga Edy Jared");
        System.out.println("Método de los trapecios");
        System.out.println("De 9:00 a 10:00 horas");
        System.out.println("=================================================================");
    }

    /**
     * Esta función la uso para poder darle un buen formato a un double
     * y que aparte sea exactamente del número de caracteres que lo ocupo.
     * @param numero - {@code double} El número a formatear.
     * @param caracteres - {@code double} La longitud de caracteres en el que se necesita el número.
     * @return
     */
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
