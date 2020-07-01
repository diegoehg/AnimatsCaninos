package animatscaninos.elementos;

public class PlatosFactory {
    public static Plato getPlatoComida(double x, double y) {
        return new Plato(x, y, Plato.COLOR_COMIDA);
    }

    public static Plato getPlatoAgua(double x, double y) {
        return new Plato(x, y, Plato.COLOR_AGUA);
    }
}
