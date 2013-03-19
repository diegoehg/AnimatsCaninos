package animatscaninos.elementos;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.geom.Ellipse2D;
import java.awt.geom.RoundRectangle2D;

import animatscaninos.Mundo;
import animatscaninos.interfaz.Interfase;

/** Clase Animat: Subclase de Component para el soporte de los MouseListener, e implementa
 *  Runnable para el soporte de Treahds */
public class Animat implements Runnable {
	
	/** Implementación del Animat: 
	 *  Esta la implementación de atributos y métodos esenciales para la clase Animat */
	
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
	private double VelocidadDesplazamiento;
	Thread Vida;
	Mundo Mnd; // Apuntador al objeto Mundo que contiene a los Animats
	Interfase Vent;
	public boolean bDesplazandose, bMostrarVentana;
	
	final static long serialVersionUID = 0; // TODO Investigar que es el serialVersionUID
		
	// Constructor. Se le pasan las coordenadas del nuevo Animat y la referencia al
	// objeto Mundo que lo contiene. Además, se setea la velocidad inicial y
	// el objeto Posicion Anterior.
	public Animat(double x, double y, Mundo m, Interfase v){
		Contorno = new RoundRectangle2D.Double(x,y,anchoAnimat,alturaAnimat,4,4);
		PosX = x;
		PosY = y;
		Mnd = m;
		Vent = v;
		VelocidadDesplazamiento = 0.25;
		PosicionAnterior = new RoundRectangle2D.Double(0,0,anchoAnimat,alturaAnimat,4,4);
		bDesplazandose = false;
		DireccionComida = 0;
		DireccionAgua = 0;
		DireccionAnimat = 0;
		DistanciaComida = 0;	// Se setean direcciones, distancias y sentidos
		DistanciaAgua = 0;		// al plato de comida o agua o Animat mas
		DistanciaAnimat = 0;	// cercanos.
		SentidoComida = false;
		SentidoAgua = false;
		SentidoAnimat = false;
		PlatoComido = new Ellipse2D.Double(0,0,30,30);
		PlatoBebido = new Ellipse2D.Double(100,100,30,30);
	}
	
	
	
	
	/*
	 *  IMPLEMENTACION DEL THREAD
	 */
	public void start() {
		Vida = new Thread(this);
		Vida.start();
	}
	
	// TODO Falta especificar cuales son los metodos que se invocaran durante el
	// Thread
	public void run() {
		Thread me = Thread.currentThread();
		while(me == Vida) {
			try {
				Thread.currentThread().sleep(10);
			}
			catch (InterruptedException e) {
				
			}
			Sensado();
			decideComportamiento();
			
		}
		
	}
	
	public void stop() {
		Vida = null;
		
	}
	
	/*
	 * / FUNCIONES MISCELANEAS
	 */
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
		
		// Guardar posición actual
		PosicionAnterior.setRoundRect(PosX - 4.0, PosY - 4.0, anchoAnimat + 8, alturaAnimat + 8, 4.0, 4.0);
		LastPosX = PosX;  // Guardando variables
		LastPosY = PosY;
		
		// Calcular desplazamiento
		despX = VelocidadDesplazamiento * Math.cos(Angulo);
		despY = VelocidadDesplazamiento * Math.sin(Angulo);
		if(!Direccion) {
			despX = -despX;		// La función arcotangente de Java solo soporta angulos
			despY = -despY;		// entre PI/2 y -PI/2, de 90° a -90°
		}
		setPosicion(PosX + despX, PosY + despY);
		bDesplazandose = true;		
	}
	
	
	/**
	 *  Son las direcciones al plato de comida o de agua o al Animat más cercanos
	 */
	double DireccionComida, DireccionAgua, DireccionAnimat, DistanciaComida, DistanciaAgua, 
		DistanciaAnimat;
	boolean SentidoComida, SentidoAgua, SentidoAnimat;
	Ellipse2D PlatoComido, PlatoBebido;
	
	/** Sensor del Animat:
	 *  Como sus contrapartes del mundo natural, los Animats pueden sensar el mundo
	 *  a su alrededor. En nuestro caso, leemos las estructuras gráficas instanciadas
	 *  en la clase Mundo. */
	private void Sensado() {
		/*
		 * TODO Reimplementar esta parte, ya sea aquí o en otro lado.
		int i = 0;
		// Coordenadas X y Y y radios de distancia a los elementos más cercanos
		double X, Y, rad = 1.234e21, DirComida = 0, DirAgua = 0, DirAnimat = 0, DistComida = 1.234e21,
			DistAgua = 1.234e21, DistAnimat = 1.234e21;
		
		boolean CondAntComida = false; // Estas banderas nos permitiran determinar
		boolean CondAntAgua = false;     // las condiciones que estan activas
		boolean CondAnimCerc = false;
		boolean CondAgresion = false;
		boolean SentComida = false, SentAgua = false, SentAnimat = false;
		Ellipse2D PlatoCom = null, PlatoBeb = null;
		
		//boolean CondAntAcogedor = false;
		//boolean CondAntAroma = false;
		//boolean CondAntAnimat = false;
		//boolean CondAntAgresion = false;
		
		// Calcula el radio de distancia a los alimentos existentes
		for(i = 0; i < Mnd.iNumeroAlimentos; i++) {
			X = (Mnd.AlimentosMundo[i].getX() + (Mnd.AlimentosMundo[i].getWidth()/2)) - (PosX + (anchoAnimat/2));
			Y = (Mnd.AlimentosMundo[i].getY() + (Mnd.AlimentosMundo[i].getHeight()/2)) - (PosY + (alturaAnimat/2));
			rad = Math.sqrt(Math.pow(X, 2.0) + Math.pow(Y, 2.0));
			
			// Determinan la distancia del plato de agua o comida más cercanos
			if ((rad < DistAgua) && (Mnd.ColorAlimentosMundo[i] == Color.blue)) {
				DistAgua = rad;
				PlatoBeb = Mnd.AlimentosMundo[i];
				
				if (X == 0) {  // Evita una division entre 0
					DirAgua = Math.PI/2;
					SentAgua = (Y >= 0 );
				} else {
					DirAgua = Math.atan(Y/X);
					SentAgua = X > 0;
				}
			}
			if ((rad < DistComida) && (Mnd.ColorAlimentosMundo[i] == Color.green)) {
				DistComida = rad;
				PlatoCom = Mnd.AlimentosMundo[i];
				
				if (X == 0) {
					DirComida = Math.PI/2;
					SentComida = (Y >= 0 );
				} else {
					DirComida = Math.atan(Y/X);
					SentComida = X > 0;
				}
			}
			
			// Determina si las condiciones de alimento o agua al alcance estan activas
			if (rad < 70) {
				CondAntAgua = (Mnd.ColorAlimentosMundo[i] == Color.blue) || CondAntAgua;
				CondAntComida = (Mnd.ColorAlimentosMundo[i] == Color.green) || CondAntComida;
			}
		}
		
		// Sensado de Animats cercanos
		for(i = 0; i < Mnd.iNumeroAnimats; i++) {
			if (Mnd.Perro[i] != this) { // Esta condicion evita que el Animat se sense a sí mismo
				X = Mnd.Perro[i].dameX() - PosX;
				Y = Mnd.Perro[i].dameY() - PosY;
				rad = Math.sqrt(Math.pow(X, 2.0) + Math.pow(Y, 2.0));
				CondAgresion = Mnd.Perro[i].Ladrando || CondAgresion;
				
				// Determinacion de la distancia al Animat mas cercano
				if (rad < DistAnimat) {
					DistAnimat = rad;
					if (X == 0) {
						DirAnimat = Math.PI/2;
						SentAnimat = Y >= 0;
					} else {
						DirAnimat = Math.atan(Y/X);
						SentAnimat = X > 0;
					}
				}
				
				// Determinacion de la condicion de cercanía del Animat
				CondAnimCerc = (rad < 70) || CondAnimCerc;
			}
		}
		
		AlimentoAlAlcance = CondAntComida;
		AguaAlAlcance = CondAntAgua;		// Se setean las condiciones afectadas
		AnimatAlAlcance = CondAnimCerc;
		Agresion = CondAgresion;
		
		DireccionComida = DirComida;
		DireccionAgua = DirAgua;
		DireccionAnimat = DirAnimat;
		DistanciaComida = DistComida;	// Se setean direcciones, distancias y sentidos
		DistanciaAgua = DistAgua;		// al plato de comida o agua o Animat mas
		DistanciaAnimat = DistAnimat;	// cercanos.
		SentidoComida = SentComida;
		SentidoAgua = SentAgua;
		SentidoAnimat = SentAnimat;
		PlatoBebido = PlatoBeb;
		PlatoComido = PlatoCom;
		
		Sed += 0.025/DistAgua;
		Hambre += 0.025/DistComida;
		Ira += 0.025/DistAnimat;
		if (Sed > 150) Sed = 150;
		if (Hambre > 150) Hambre = 150;
		if (Ira > 150) Ira = 150;
		*/
	}
	
	private void decideComportamiento () {
		/* Aquí va el algoritmo de decisión de comportamiento */
	}
	
	
	
} // Fin de la clase Animat
