public class Secante {
    // Atributos
    double x1; // Límite inferior de la busqueda
    double x2; // Límite superior de la busqueda
    static double error; // Error tolerado para el cálculo
    int calculos; // Número de cálculos límite
    static int calculoActual = 0; // Cálculo en el que se encuentra actualmente
    static double calculoError = error + 1;
    
        // Constructores
        public Secante(double x1, double x2, double error, int calculos) {
            this.x1 = x1;
            this.x2 = x2;
            this.error = error;
            this.calculos = calculos;
        }
    
        // Métodos de la clase
    
        /**
         * Esta función calcula una nueva iteración para el método de la secante.
         * @return Regresa un arreglo con toda la información necesaria para imprimir una línea de la tabla.
         */
        public double[] nextCalc() {
            // Creamos el arreglo que regresará este método
            // Deberá regresar: [N, x1, f(x1), x2, f(x2), x3, f(x3)]
            double[] arreglo = new double[7];
    
            // 1. N: Número del cálculo
            calculoActual++; // Sumar 1 al cálculo
            arreglo[0] = calculoActual;
    
            // 2. x1: Límite inferior
            arreglo[1] = x1;
    
            // 3. f(x1): Función evaluada en el límite inferior
            double u = ((10*x1)/9) - 1;
            double fx1 = Math.asin(u) + u*(Math.sqrt(1 - Math.pow(u, 2))) - 1.100144;
            arreglo[2] = fx1;
    
            // 4. x2: Límite superior
            arreglo[3] = x2;
    
            // 5. f(x2): Función evaluada en el límite superior
            u = ((10*x2)/9) - 1;
            double fx2 = Math.asin(u) + u*(Math.sqrt(1 - Math.pow(u, 2))) - 1.100144;
            arreglo[4] = fx2;
    
            // 6. x3: Nuevo límite
            double x3 = x1 - ((x1 - x2)*fx1)/(fx1-fx2);
            arreglo[5] = x3;
    
            // 7. f(x3): Función evaluada en el nuevo límite
            u = ((10*x3)/9) - 1;
            double fx3 = Math.asin(u) + u*(Math.sqrt(1 - Math.pow(u, 2))) - 1.100144;
            arreglo[6] = fx3;

            // Configurar todo para la siguiente iteración
            x1 = x2;
            x2 = x3;
            calculoError = fx3;
    
            // Regresar arreglo
            return arreglo;
        }
    
        public void printLine(double[] r) {
            String x1 = truncar(r[1], 6);
            String fx1 = truncar(r[2], 9);
            String x2 = truncar(r[3], 9);
            String fx2 = truncar(r[4], 9);
            String x3 = truncar(r[5], 9);
            String fx3 = truncar(r[6], 9);

            String linea = String.format("│%2d│%10s│%10s│%10s│%10s│%10s│%10e│",
                calculoActual, x1, fx1, x2, fx2, x3, r[6]);
            System.out.println(linea);
    }

    public static String truncar(double valor, int limite) {
        System.out.println(calculoError);
        String res = String.valueOf(valor);
        return res.substring(0, Math.min(limite, res.length()));
    }

    public boolean calcularError() {
        return Math.abs(calculoError) >= error;
    }
}
