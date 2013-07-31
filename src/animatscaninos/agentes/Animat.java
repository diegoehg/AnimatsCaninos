package animatscaninos.agentes;

/**
 * Esta clase representa al agente Animat. Contiene el modelo de toma de
 * decisiones del Animat y ejecuta los comportamientos.
 * 
 * @author Diego Enrique Hernández González.
 *
 */
public class Animat implements Runnable {
	
	/**
	 * TODO Definir cómo se va a ejecutar este algoritmo.
	 */
	public void run() {
		Sensado();
		decideComportamiento();
	}
	
	/** 
	 *  Es la rutina que detecta cuáles son los elementos del ambiente que están
	 *  alrededor del Animat.
	 *  
	 *  TODO Verificar en donde iría esta rutina.
	 */
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
	
	/**
	 * Algoritmo de decisi�n del comportamiento, basado en el mecanismo de
	 * elecci�n de Pattie Maes.
	 */
	private void decideComportamiento () {
		/* Aqu� va el algoritmo de decisi�n de comportamiento */
	}
	
	
	
} // Fin de la clase Animat
