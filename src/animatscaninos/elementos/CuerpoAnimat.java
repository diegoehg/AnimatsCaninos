package animatscaninos.elementos;

import animatscaninos.agentes.EstadoEmocional;
import animatscaninos.interfaz.Interfase;

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
	private double PosX, PosY, LastPosX, LastPosY; // Posicion del Animat
	public final static double alturaAnimat = 35;
	public final static double anchoAnimat = 50;
	private final static Color ColorAnimat = new Color(173,107,30); // Color cafe obscuro en RGB
	final static Color ColorAnimatLadrando = new Color(193,11,11);  // Color Rojo Sangre
	final static Color ColorAnimatHuyendo = new Color(170,31,210); // Color morado
	public final static Color ColorBordeAnimat = Color.black;
	public final static BasicStroke BordeAnimat = new BasicStroke(2.0f);

	private boolean desplazandose;

	private RoundRectangle2D contorno, posicionAnterior;

	private Color color;
	
	private Mundo mundo;

	private Interfase interfaz;
	
	private double VelocidadDesplazamiento;

	public boolean isDesplazandose() {
		return desplazandose;
	}

	public RoundRectangle2D getContorno() {
		return contorno;
	}

	public RoundRectangle2D getPosicionAnterior() {
		return posicionAnterior;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public void setEstadoEmocional(EstadoEmocional estado) {
		// TODO Esto tiene que ser invocado desde el cambio de comportamiento
		switch (estado) {
			// TODO Si el comportamiento pasa a ladrar o atacar
			case IRA:
				color = ColorAnimatLadrando; break;
			case MIEDO: // TODO Si el comportamiento pasa a huir
				color = ColorAnimatHuyendo; break;
			default: // TODO Si el comportamiento pasa a caminar y buscar comida
				color = ColorAnimat;
		}
	}

	public double getX(){ return PosX; } // Regresan X y Y respectivamente
	public double getY(){ return PosY; }
	
	// Setea las nuevas coordenadas del Animat
	public void setPosicion(double x, double y){
		contorno.setRoundRect(x, y, anchoAnimat, alturaAnimat, 4, 4);
		PosX = x;
		PosY = y;
	}

	public void setMundo(Mundo mundo) {
		this.mundo = mundo;
	}

	public void setInterfaz(Interfase interfaz) {
		this.interfaz = interfaz;
	}

	// Calcula el desplazamiento del Animat
	private void moverse(double Angulo, boolean Direccion) {
		double despX, despY;
		
		// Guardar posición actual
		posicionAnterior.setRoundRect(PosX - 4.0, PosY - 4.0, anchoAnimat + 8, alturaAnimat + 8, 4.0, 4.0);
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
		desplazandose = true;
	}

	public boolean contains(double x, double y) {
		return contorno.contains(x, y);
	}
	
	/**
	 *  Son las direcciones al plato de comida o de agua o al Animat más cercanos
	 */
	double DireccionComida, DireccionAgua, DireccionAnimat, DistanciaComida, DistanciaAgua, 
		DistanciaAnimat;
	boolean SentidoComida, SentidoAgua, SentidoAnimat;
	Ellipse2D PlatoComido, PlatoBebido;
}
