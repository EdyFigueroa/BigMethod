package Tarea3;

import java.text.DecimalFormat;
import java.util.Scanner;

public class BigIntegral {
    // Scanner para leer datos del teclado
    static Scanner sc = new Scanner(System.in);

    // Atributos
    static String pregunta; // Se almacena la pregunta
    static String unidades; // TODO: Pedir unidades
    static double limInferior; // Límite inferior
    static double limSuperior; // Límite superior
    static int trapecios; // Trapecios
    static int numCalculos; // Cálculos máximos
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
        pregunta = "hola";
        limInferior = 5;
        limSuperior = 9;
        trapecios = 4;
        unidades = "u^2";

        // Calculamos el salto entre intervalos con la fórmula
        // h = (b-a)/n
        double salto = (limSuperior - limInferior) / trapecios;

        // Imprimir cabecera de la tabla
        System.out.println("=================================================================");
        System.out.println("                     MÉTODO DE LOS TRAPECIOS\n");

        System.out.printf("Usando %d trapecios\n", trapecios);
        System.out.println("┌──────────┬──────────┬──────────┬──────────┬──────────┬──────────┐");
        System.out.println("│ Trapecio │     a    │   a+h    │   f(a)   │  f(a+h)  │   Área   │");
        System.out.println("├──────────┼──────────┼──────────┼──────────┼──────────┼──────────┤");

        double areaTotal = 0;

        for (int i = 1; i <= trapecios; i++) {
            // Este trapecio termina en...
            double trapTermina = limInferior + salto;;

            // Evaluamos la función en los límites
            double fa = 40 + 8 * Math.sqrt(limInferior);
            double fab = 40 + 8 * Math.sqrt(trapTermina);

            // Calculamos el área del trapecio con la fórmula
            // (h/2) * [ f(a) + f(a+b) ]
            double areaTrapecio = (salto/2)*(fa+fab);

            // Lo sumamos al área total
            areaTotal += areaTrapecio;

            // Imprimir columna
            System.out.printf("│#%9d│%10s│%10s│%10s│%10s│%10s│\n",
                i, formatNumber(limInferior, 9),
                formatNumber(trapTermina, 9),
                formatNumber(fa, 9),
                formatNumber(fab, 9),
                formatNumber(areaTrapecio, 9));

            
            // El siguiente trapecio comienza en...
            limInferior = trapTermina;
        }

        // Pie de la tabla
        System.out.println("├──────────┴──────────┴──────────┴──────────┴──────────┼──────────┤");
        System.out.printf("│                                           Área total │%10s│ %s\n",
            formatNumber(areaTotal, 9), unidades);
        System.out.println("└──────────────────────────────────────────────────────┴──────────┘");

    }

    //////////////////////////
    /// MÉTODOS AUXILIARES ///
    //////////////////////////
    
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
