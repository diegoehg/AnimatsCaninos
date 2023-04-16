package animatscaninos.elementos;

import java.awt.*;
import java.awt.geom.RectangularShape;
import java.awt.geom.RoundRectangle2D;

public class CuerpoAnimat implements Sprite {
    public final static double ALTURA_ANIMAT = 35;
    public final static double ANCHO_ANIMAT = 50;
    private final static double ARC_CONTORNO = 4;
    private double x;
    private double y;
    private final RoundRectangle2D contorno;

    public CuerpoAnimat(double x, double y) {
        this.x = x;
        this.y = y;
        this.contorno = new RoundRectangle2D.Double(x,y, ANCHO_ANIMAT, ALTURA_ANIMAT,ARC_CONTORNO,ARC_CONTORNO);
    }

    @Override
    public RectangularShape getContorno() {
        return contorno;
    }

    @Override
    public Color getColor() {
        return null;
    }

    public void setPosicion(double x, double y){
        this.contorno.setRoundRect(x, y, ANCHO_ANIMAT, ALTURA_ANIMAT, ARC_CONTORNO, ARC_CONTORNO);
        this.x = x;
        this.y = y;
    }
}
