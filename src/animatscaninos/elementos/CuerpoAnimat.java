package animatscaninos.elementos;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.geom.Ellipse2D;
import java.awt.geom.RoundRectangle2D;

/**
 * Corresponde a la parte gráfica del Animat. Contiene las características
 * físicas de este.
 * @author Diego Enrique Hernández González.
 *
 */
public class CuerpoAnimat implements Elemento {
	// Características físicas
	private RoundRectangle2D Contorno, PosicionAnterior;
	private double PosX, PosY, LastPosX, LastPosY; // Posicion del Animat
	private final static double alturaAnimat = 35;
	private final static double anchoAnimat = 50;
	private final static Color ColorAnimat = new Color(173,107,30); // Color cafe obscuro en RGB
	final static Color ColorAnimatLadrando = new Color(193,11,11);  // Color Rojo Sangre
	final static Color ColorAnimatHuyendo = new Color(170,31,210); // Color morado
	final static Color ColorBordeAnimat = Color.black;
	final static BasicStroke BordeAnimat = new BasicStroke(2.0f);
	
	Mundo Mnd; // Apuntador al objeto Mundo que contiene a los Animats
	
	private double VelocidadDesplazamiento;
	
	public double dameX(){ return PosX; } // Regresan X y Y respectivamente
	public double dameY(){ return PosY; }
	
	// Setea las nuevas coordenadas del Animat
	public void setPosicion(double x, double y){
		Contorno.setRoundRect(x, y, anchoAnimat, alturaAnimat, 4, 4);
		PosX = x;
		PosY = y;
	}
	
	public void setMostrarVentana(boolean V) {
		bMostrarVentana = V;
	}
	
	// Calcula el desplazamiento del Animat
	private void moverse(double Angulo, boolean Direccion) {
		double despX, despY;
		
		// Guardar posiciÃ³n actual
		PosicionAnterior.setRoundRect(PosX - 4.0, PosY - 4.0, anchoAnimat + 8, alturaAnimat + 8, 4.0, 4.0);
		LastPosX = PosX;  // Guardando variables
		LastPosY = PosY;
		
		// Calcular desplazamiento
		despX = VelocidadDesplazamiento * Math.cos(Angulo);
		despY = VelocidadDesplazamiento * Math.sin(Angulo);
		if(!Direccion) {
			despX = -despX;		// La funciÃ³n arcotangente de Java solo soporta angulos
			despY = -despY;		// entre PI/2 y -PI/2, de 90Â° a -90Â°
		}
		setPosicion(PosX + despX, PosY + despY);
		bDesplazandose = true;		
	}
	
	
	/**
	 *  Son las direcciones al plato de comida o de agua o al Animat mÃ¡s cercanos
	 */
	double DireccionComida, DireccionAgua, DireccionAnimat, DistanciaComida, DistanciaAgua, 
		DistanciaAnimat;
	boolean SentidoComida, SentidoAgua, SentidoAnimat;
	Ellipse2D PlatoComido, PlatoBebido;
}
