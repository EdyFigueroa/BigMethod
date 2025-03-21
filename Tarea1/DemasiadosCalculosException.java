public class DemasiadosCalculosException extends RuntimeException {
    public DemasiadosCalculosException(int calculos) {
        super("No se encontró la raíz en " + calculos + " cálculos.");
    }
}
