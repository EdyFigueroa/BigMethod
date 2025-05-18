package Tarea4;

import java.util.InputMismatchException;
import java.util.Scanner;

public class BigSimpson {
    // Creamos el Scanner.
    static Scanner sc = new Scanner(System.in);
    
    // Atributos de la clase.
    static String pregunta; // Almacenamos la pregunta del problema
    static String unidades; // Unidades en las que se mide el problema
    static double Vreal; // Valor real de la integral
    static double a; // Límite inferior de la integral
    static double b; // Límite superior de la integral
    static double error; // Error permitido en el cálculo

    ////////////
    /// MAIN ///
    ////////////
    /// El main se encarga de pedir datos y de hacer el menú y solo de eso. Cada caso está en su propia función.
    /// Las funciones del método empiezan después del main.

    public static void main(String[] args) {
        pantallaDeInicio();

        // Preguntar por los datos
        /*System.out.print("- Pregunta: > "); pregunta = sc.nextLine();
        System.out.print("- Unidades: > "); unidades = sc.nextLine();
        System.out.print("- Valor real: > "); Vreal = sc.nextDouble();
        System.out.print("- Error: > "); error = sc.nextDouble();
        System.out.print("- Límite inferior: > "); a = sc.nextInt();
        System.out.print("- Límite superior: > "); b = sc.nextInt();*/

        // TODO: Al terminar borrar esto y quitar el comentario de arriba
        pregunta = "¿Cuál es la longitud de una pista?";
        unidades = "m";
        Vreal = 2835;
        a = 40;
        b = 70;
        error = 0.005;
        
        System.out.println("=================================================================");

        // Loop del menú
        imprimirMenu();
        int n = -1;

        do {
            // Pedir la opción al usuario hasta que dé una opción valida
            System.out.print("- Cuál es el número de divisiones: > ");
            n = pedirInt(); sc.nextLine();

            
            System.out.println("=================================================================");

            // Switch con todos los casos
            if (n == 2) {
                System.out.println("                 SIMSPON DE 1/3 (FÓRMULA SIMPLE)                 ");
                simple_1_3(n);

                System.out.print("\n- Pulse [ENTER] para salir..."); sc.nextLine();
                
                System.out.println("=================================================================");
                imprimirMenu();
            
            } else if (n == 3) {
                System.out.println("                 SIMSPON DE 3/8 (FÓRMULA SIMPLE)                 ");
                simple_3_8(n);

                System.out.print("\n- Pulse [ENTER] para salir..."); sc.nextLine();
                
                System.out.println("=================================================================");
                imprimirMenu();

            } else if (n > 2 && n%2==0) {
                System.out.println("                SIMSPON DE 1/3 (FÓRMULA COMPLEJA)                ");
                compleja_1_3(n);

                System.out.print("\n- Pulse [ENTER] para salir..."); sc.nextLine();
                
                System.out.println("=================================================================");
                imprimirMenu();

            } else if (n > 3 && n%2!=0 && n%3==0) {
                System.out.println("                SIMSPON DE 3/8 (FÓRMULA COMPLEJA)                ");
                compleja_3_8(n);

                System.out.print("\n- Pulse [ENTER] para salir..."); sc.nextLine();
                
                System.out.println("=================================================================");
                imprimirMenu();

            } else if (n == 0) {
                // Terminar el programa
                System.out.println("Adiós papure :\"v");
            }

        } while (n != 0);

    }

    ///////////////////////////////
    /// MÉTODOS PARA CADA REGLA ///
    ///////////////////////////////
    /// A partir de aquí están todos los métodos para cada regla de Simpson.
    /// Aquí está toda la lógica, así que aquí es la revisión real.
    
    // Simpson de 1/3 formula simple
    public static void simple_1_3(int n) {
        pantallaDeInicio();

        double h = (b-a)/n; // Qué tan separados están los puntos el uno al otro
        double fa = 0.03 * Math.pow(a, 2) + 1.5; // Función evaluada en el límite inferior
        double x1 = a+h; // Primer punto
        double fx1 = 0.03 * Math.pow(x1, 2) + 1.5; // Función evaluada en el primer punto
        double fb = 0.03 * Math.pow(b, 2) + 1.5; // Función evaluada en el límite superior
        double Vcalc = ((1*h)/3)*( fa+(4*fx1)+fb ); // Valor que se calculo en la integral
        double errorCalc = Math.abs(Vreal - Vcalc); // Error que se calculó

        // Imprimir los resultados
        System.out.println(pregunta + "\n");
        System.out.println("┌───────────┬───────────┬───────────┬───────────┬───────────┐");
        System.out.println("│    Pxy    │     x     │    f(x)   │  Factor   │   Área    │");
        System.out.println("├───────────┼───────────┼───────────┼───────────┼───────────┤");
        System.out.printf("│%11d│%11s│%11s│%11s│%11s│\n", 1, a, fa, 1, 1*fa);
        System.out.printf("│%11d│%11s│%11s│%11s│%11s│\n", 2, x1, fx1, 4, 4*fx1);
        System.out.printf("│%11d│%11s│%11s│%11s│%11s│\n", 3, b, fb, 1, 1*fb);
        System.out.println("└───────────┴───────────┴───────────┴───────────┴───────────┘");

        System.out.println("Número de divisiones: " + n);
        System.out.println("Valor real de la integral: " + Vreal + " " + unidades);
        System.out.println("Valor por el método: " + Vreal + " " + unidades);
        System.out.println("Error del método: " + errorCalc + " " + unidades);
    }

    
    // Simpson de 3/8 formula simple
    public static void simple_3_8(int n) {
        pantallaDeInicio();

        double h = (b-a)/n; // Qué tan separados están los puntos el uno al otro
        double fa = 0.03 * Math.pow(a, 2) + 1.5; // Función evaluada en el límite inferior
        double x1 = a+(1*h); // Primer punto
        double x2 = a+(2*h); // Segundo punto
        double fx1 = 0.03 * Math.pow(x1, 2) + 1.5; // Función evaluada en el primer punto
        double fx2 = 0.03 * Math.pow(x2, 2) + 1.5; // Función evaluada en el segundo punto
        double fb = 0.03 * Math.pow(b, 2) + 1.5; // Función evaluada en el límite superior
        double Vcalc = ((3*h)/8)*( fa+(3*fx1)+(3*fx2)+fb ); // Valor que se calculo en la integral
        double errorCalc = Math.abs(Vreal - Vcalc); // Error que se calculó

        // Imprimir los resultados
        System.out.println(pregunta + "\n");
        System.out.println("┌───────────┬───────────┬───────────┬───────────┬───────────┐");
        System.out.println("│    Pxy    │     x     │    f(x)   │  Factor   │   Área    │");
        System.out.println("├───────────┼───────────┼───────────┼───────────┼───────────┤");
        System.out.printf("│%11d│%11s│%11s│%11s│%11s│\n", 1, a, fa, 1, 1*fa);
        System.out.printf("│%11d│%11s│%11s│%11s│%11s│\n", 2, x1, fx1, 3, 3*fx1);
        System.out.printf("│%11d│%11s│%11s│%11s│%11s│\n", 3, x2, fx2, 3, 3*fx2);
        System.out.printf("│%11d│%11s│%11s│%11s│%11s│\n", 4, b, fb, 1, 1*fb);
        System.out.println("└───────────┴───────────┴───────────┴───────────┴───────────┘");

        System.out.println("Número de divisiones: " + n);
        System.out.println("Valor real de la integral: " + Vreal + " " + unidades);
        System.out.println("Valor por el método: " + Vreal + " " + unidades);
        System.out.println("Error del método: " + errorCalc + " " + unidades);
    }

    // Simpson de 1/3 formula compleja
    public static void compleja_1_3(int n) {
        pantallaDeInicio();

        // Cabecera de la tabla
        
        System.out.println(pregunta + "\n");
        System.out.println("┌───────────┬───────────┬───────────┬───────────┬───────────┐");
        System.out.println("│    Pxy    │     x     │    f(x)   │  Factor   │   Área    │");
        System.out.println("├───────────┼───────────┼───────────┼───────────┼───────────┤");

        double Vcalc = 0; // Inicializamos la variable del valor real de la integral
        double h = (b-a)/n; // Saltos entre puntos
        double ca = a; // Límite inferior (irá cambiando)
        double cb = b; // Límite superior (irá cambiando)

        for (int x = 1; x <= n+1; x++) {
            if (x == 1) {
                int factor = 1;
                double pto = ca;
                double fpto = 0.03 * Math.pow(pto, 2) + 1.5;
                double valor = factor * fpto;
                Vcalc += valor;
                System.out.printf("│%11d│%11s│%11s│%11s│%11s│\n", x, pto, fpto, factor, valor);
            }

            if (x == n+1) {
                int factor = 1;
                double pto = cb;
                double fpto = 0.03 * Math.pow(pto, 2) + 1.5;
                double valor = factor * fpto;
                Vcalc += valor;
                System.out.printf("│%11d│%11s│%11s│%11s│%11s│\n", x, pto, fpto, factor, valor);
            }

            if (x > 1 && x < n+1) {
                double pos = x-1;
                double res = x % 2;

                if (res == 0) {
                    double factor = 4;
                    double pto = ca + (pos*h);
                    double fpto = 0.03 * Math.pow(pto, 2) + 1.5;
                    double valor = factor * fpto;
                    Vcalc += valor;
                    System.out.printf("│%11d│%11s│%11s│%11s│%11s│\n", x, pto, fpto, factor, valor);

                } else {
                    double factor = 2;
                    double pto = ca + (pos*h);
                    double fpto = 0.03 * Math.pow(pto, 2) + 1.5;
                    double valor = factor * fpto;
                    Vcalc += valor;
                    System.out.printf("│%11d│%11s│%11s│%11s│%11s│\n", x, pto, fpto, factor, valor);
                }
            }
        }

        Vcalc = (1*h/3) * Vcalc;
        double errorCalc = Math.abs(Vreal - Vcalc);

        System.out.println("└───────────┴───────────┴───────────┴───────────┴───────────┘");
        System.out.println("Número de divisiones: " + n);
        System.out.println("Valor real de la integral: " + Vreal + " " + unidades);
        System.out.println("Valor por el método: " + Vreal + " " + unidades);
        System.out.println("Error del método: " + errorCalc + " " + unidades);
    }

    // Simpson de 3/8 formula compleja
    public static void compleja_3_8(int n) {
        pantallaDeInicio();

        // Cabecera de la tabla
        System.out.println(pregunta + "\n");
        System.out.println("┌───────────┬───────────┬───────────┬───────────┬───────────┐");
        System.out.println("│    Pxy    │     x     │    f(x)   │  Factor   │   Área    │");
        System.out.println("├───────────┼───────────┼───────────┼───────────┼───────────┤");

        double Vcalc = 0;
        double h = (b-a)/n;
        double ca = a;
        double cb = b;

        for (int xx = 1; xx <= n+1; xx++) {
            if (xx == 1) {
                double factor = 1;
                double pto = ca;
                double fpto = 0.03 * Math.pow(pto, 2) + 1.5;
                double valor = factor*fpto;
                Vcalc += valor;
                System.out.printf("│%11d│%11.5f│%11.5f│%11.5f│%11.5f│\n", xx, pto, fpto, factor, valor);

            } else if (xx == n+1) {
                double factor = 1;
                double pto = cb;
                double fpto = 0.03 * Math.pow(pto, 2) + 1.5;
                double valor = factor*fpto;
                Vcalc += valor;
                System.out.printf("│%11d│%11.5f│%11.5f│%11.5f│%11.5f│\n", xx, pto, fpto, factor, valor);

            } else if (xx > 1 && xx < n+1) {
                double pos = xx-1;
                double res = ( (xx-1) %3 );

                if (res == 0) {
                    double factor = 2;
                    double pto = ca + (pos*h);
                    double fpto = 0.03 * Math.pow(pto, 2) + 1.5;
                    double valor = factor*fpto;
                    Vcalc += valor;
                    System.out.printf("│%11d│%11.5f│%11.5f│%11.5f│%11.5f│\n", xx, pto, fpto, factor, valor);

                } else {
                    double factor = 3;
                    double pto = ca + (pos*h);
                    double fpto = 0.03 * Math.pow(pto, 2) + 1.5;
                    double valor = factor*fpto;
                    Vcalc += valor;
                    System.out.printf("│%11d│%11.5f│%11.5f│%11.5f│%11.5f│\n", xx, pto, fpto, factor, valor);
                }
            }
        }

        Vcalc = (3*h/8)*Vcalc;
        double errorCalc = Math.abs(Vreal - Vcalc);

        System.out.println("└───────────┴───────────┴───────────┴───────────┴───────────┘");
        System.out.println("Número de divisiones: " + n);
        System.out.println("Valor real de la integral: " + Vreal + " " + unidades);
        System.out.println("Valor por el método: " + Vreal + " " + unidades);
        System.out.println("Error del método: " + errorCalc + " " + unidades);
    }

    //////////////////////////
    /// MÉTODOS AUXILIARES ///
    //////////////////////////
    /// Métodos que no son tan importantes como los otros

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

    public static void imprimirMenu() {
        System.out.println("[1] Regla 1 (n=1)\n" +
            "[2] Regla 2 (n=3)\n" +
            "[3] Regla 3 (n>2 && n=par)\n" +
            "[4] Regla 4 (n>3 && n=impar && n=*=3)\n" +
            "[0] F I N\n");
    }

    public static int pedirInt() {
        while (true) {
            try {
                return sc.nextInt(); // Pedir el número

            } catch (InputMismatchException e) {
                // Si lo que se dio no es un número
                sc.nextLine();
                System.out.print("- /!\\ Teclee una opción válida: > ");
            }
        }
    }
}
