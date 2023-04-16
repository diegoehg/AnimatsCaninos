package animatscaninos.agentes;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.geom.RectangularShape;

import animatscaninos.elementos.CuerpoAnimat;
import animatscaninos.elementos.Plato;
import animatscaninos.interfaz.Interfase;

/**
 * 	Clase Animat: Subclase de Component para el soporte de los MouseListener, e implementa
 *  Runnable para el soporte de Treahds
 *
 *  @author Diego Hdez.
 */
public class Animat implements Runnable {

	public final static double ALTURA_ANIMAT = 35;

	public final static double ANCHO_ANIMAT = 50;

	private final static double RANGO_ALCANCE = 70;

	public final static Color COLOR_ANIMAT = new Color(173,107,30); // Color cafe obscuro en RGB

	public final static Color COLOR_ANIMAT_LADRANDO = new Color(193,11,11);  // Color Rojo Sangre

	public final static Color COLOR_ANIMAT_HUYENDO = new Color(170,31,210); // Color morado

	public final static Color COLOR_BORDE_ANIMAT = Color.black;

	public final static BasicStroke BORDE_ANIMAT = new BasicStroke(2.0f);

	private final CuerpoAnimat cuerpo;

	private final CuerpoAnimat cuerpoPosicionAnterior;

	private double velocidadDesplazamiento;

	private boolean alive = false;

	private Mundo mundo;

	private Interfase ventana;

	private boolean desplazandose, imprimirDatos;

	// Constructor. Se le pasan las coordenadas del nuevo Animat y la referencia al
	// objeto Mundo que lo contiene. Además, se setea la velocidad inicial y
	// el objeto Posicion Anterior.
	public Animat(double x, double y, Mundo mundo, Interfase ventana){
		this.cuerpo = new CuerpoAnimat(x, y);
		this.cuerpoPosicionAnterior = new CuerpoAnimat(0, 0);
		this.mundo = mundo;
		this.ventana = ventana;
		velocidadDesplazamiento = 0.25;
		ComportamientoElegido = 1;
		desplazandose = false;
		DireccionAnimat = 0;
		DistanciaAnimat = 0;    // cercanos.
		SentidoAnimat = false;
		Comiendo = false;
		Bebiendo = false;
		Ladrando = false;
		Peleando = false;
		Huyendo = false;
		CiclosdeEspera = 0;
		CiclosdeEsperaSeleccion = 0;
		UmbralIrPorComida = 120;
		UmbralComer = 100;
		UmbralIrPorAgua = 120;
		UmbralBeber = 100;
		UmbralLadrar = 50;
		UmbralPelear = 100;
		UmbralHuir = 100;
	}

	public RectangularShape getContorno() {
		return cuerpo.getContorno();
	}

	public RectangularShape getPosicionAnterior() {
		return cuerpoPosicionAnterior.getContorno();
	}

	public void start() {
		Thread vida = new Thread(this);
		vida.start();
		alive = true;
	}

	// TODO Falta especificar cuales son los metodos que se invocaran durante el
	// Thread
	public void run() {
		while(alive) {
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {

			}
			escanear();
			EjecutarComportamiento();
			CalculoNivelActivacion();
			CiclosdeEsperaSeleccion++;
		}
	}

	public void stop() {
		alive = false;
	}

	/*
	 * / FUNCIONES MISCELANEAS
	 */
	public double getX() {
		return cuerpo.getContorno().getX();
	}

	public double getY() {
		return cuerpo.getContorno().getY();
	}

	public boolean dameLadrando(){ return Ladrando; } // Regresa estado interno

	public boolean damePeleando(){ return Peleando; }

	public boolean dameHuyendo(){ return Huyendo; }

	// Setea las nuevas coordenadas del Animat
	public void setPosicion(double x, double y){
		cuerpo.setPosicion(x, y);
	}

	public boolean isDesplazandose() {
		return desplazandose;
	}

	public void setImprimirDatos(boolean imprimirDatos) {
		this.imprimirDatos = imprimirDatos;
	}

	private void desplazamiento(double angulo, boolean direccion) {
		// Para los casos donde se ocupa la versión de arcotangente que soporta
		// ángulos entre PI/2 y -PI/2
		if(!direccion)
			angulo += Math.PI;

		desplazamiento(angulo);
	}

	private void desplazamiento(double angulo) {
		double despX, despY;

		// Guardar posición actual
		cuerpoPosicionAnterior.setPosicion(getX(), getY());

		// Calcular desplazamiento
		despX = velocidadDesplazamiento * Math.cos(angulo);
		despY = velocidadDesplazamiento * Math.sin(angulo);
		cuerpo.setPosicion(getX() + despX, getY() + despY);
		desplazandose = true;

		// Incremento de las motivaciones
		Hambre += 0.05;
		Sed += 0.05;
	}


	/** IMPLEMENTACIÓN DEL COMPORTAMIENTO DEL ANIMAT
	 *
	 *  Condiciones de activación:
	 *  Sirven para saber cuales comportamientos se han de elegir. Para nuestro Animat
	 *  hemos elegido las siguientes:
	 *  Alimento al alcance: Cuando hay un plato de comida a menos de 70 pixeles de distancia
	 *  Agua al alcance: Cuando hay un plato de agua a la misma distancia
	 *  Lugar Acogedor: Cuando encuentra un lugar cómodo para dormir
	 *  Aroma de otro perro: Cuando un poste ha sido marcado por otro Animat
	 *  Perro al alcance: Cuando otro Animat se encuentra a la vista
	 *  Agresion: Cuando hay agresión de parte de otro Animat, ladrar ya se considera una
	 *            agresión.  */
	boolean isComidaAlcanzable, isAguaAlcanzable, LugarAcogedor, AromaOtroAnimat,
			AnimatAlAlcance, Agresion;

	/** Motivaciones:
	 *  Las motivaciones muestran el estado interno del Animat y sirven en el proceso de
	 *  elección del comportamiento a ser elegido. Las motivaciones que sufren los
	 *  Animats son hambre, sed, curiosidad, necesidad de expansión de su territorio,
	 *  incontinencia, cansancio, ira y miedo. */
	double Hambre, Sed, Curiosidad, Expansion, Incontinencia, Cansancio, Ira, Miedo;

	/** Niveles de activación:
	 *  Por cada comportamiento implementado se mantiene un valor llamado el nivel de
	 *  activación, el cual es la magnitud que tiene cada comportamiento para emerger
	 *  en determinado instante. Los comportamientos se enlistarán más adelante cuando
	 *  se les implemente.  */
	double ActivacionIrPorComida, ActivacionComer, ActivacionIrPorAgua, ActivacionBeber,
			ActivacionHusmear, ActivacionMarcarTerritorio, ActivacionEvacuar,
			ActivacionIrADormir, ActivacionDormir, ActivacionLadrar, ActivacionPelear,
			ActivacionHuir;

	/** Umbrales de activación:
	 *  De la misma manera, a cada comportamiento esta asignado un valor de umbral el
	 *  cual sirve para determinar en que instante se escogerá un comportamiento para
	 *  ser ejecutado, siendo los elegibles los comportamientos cuyo nivel de
	 *  activacion supere a su umbral. */
	double UmbralIrPorComida, UmbralComer, UmbralIrPorAgua, UmbralBeber, UmbralHusmear,
			UmbralMarcarTerritorio, UmbralEvacuar, UmbralIrADormir, UmbralDormir,
			UmbralLadrar, UmbralPelear, UmbralHuir;

	/** Comportamiento elegido:
	 *  Esta variable indicará con un número entero cual es el comportamiento que estará
	 *  ejecutando el Animat.
	 */
	int ComportamientoElegido;

	/**
	 * 	Este indicará cuando el Animat está ladrando, comiendo o bebiendo
	 */
	private boolean Ladrando, Comiendo, Bebiendo, Peleando, Huyendo;

	/**
	 *  Este indicara cuantos ciclos debemos esperar para que un comportamiento
	 *  deje de ejecutarse
	 */
	private int CiclosdeEspera;
	private long CiclosdeEsperaSeleccion;


	/**
	 *  Son las direcciones al plato de comida o de agua o al Animat más cercanos
	 */
	double DireccionAnimat, DistanciaAnimat;
	boolean SentidoAnimat;

	/** Sensor del Animat:
	 *  Como sus contrapartes del mundo natural, los Animats pueden sensar el mundo
	 *  a su alrededor. En nuestro caso, leemos las estructuras gráficas instanciadas
	 *  en la clase Mundo. */
	private void escanear() {
		int i = 0;
		// Coordenadas X y Y y radios de distancia a los elementos más cercanos
		double deltaX, deltaY, distancia, DirAnimat = 0,
				DistAnimat = 1.234e21;

		boolean CondAnimCerc = false;
		boolean CondAgresion = false;
		boolean SentAnimat = false;

		// TODO Implementar deteccion de sensado de aroma de otro animat, de lugar acogedor y
		// de agresion de parte de otro Animat
		//boolean CondAntAcogedor = false;
		//boolean CondAntAroma = false;
		//boolean CondAntAnimat = false;
		//boolean CondAntAgresion = false;


		// Sensado de Animats cercanos
		for(i = 0; i < mundo.iNumeroAnimats; i++) {
			if (mundo.Perro[i] != this) { // Esta condicion evita que el Animat se sense a sí mismo
				deltaX = mundo.Perro[i].getX() - getX();
				deltaY = mundo.Perro[i].getY() - getY();
				distancia = Math.sqrt(Math.pow(deltaX, 2.0) + Math.pow(deltaY, 2.0));
				CondAgresion = mundo.Perro[i].Ladrando || CondAgresion;

				// Determinacion de la distancia al Animat mas cercano
				if (distancia < DistAnimat) {
					DistAnimat = distancia;
					if (deltaX == 0) {
						DirAnimat = Math.PI/2;
						SentAnimat = deltaY >= 0;
					} else {
						DirAnimat = Math.atan(deltaY/deltaX);
						SentAnimat = deltaX > 0;
					}
				}

				// Determinacion de la condicion de cercanía del Animat
				CondAnimCerc = (distancia < 70) || CondAnimCerc;
			}
		}

		isComidaAlcanzable = mundo.isPlatoComidaWithinRange(
				cuerpo.getContorno().getCenterX(), cuerpo.getContorno().getCenterY(), RANGO_ALCANCE);
		isAguaAlcanzable = mundo.isPlatoAguaWithinRange(
				cuerpo.getContorno().getCenterX(), cuerpo.getContorno().getCenterY(), RANGO_ALCANCE);
		AnimatAlAlcance = CondAnimCerc;
		Agresion = CondAgresion;

		DireccionAnimat = DirAnimat;
		DistanciaAnimat = DistAnimat;    // cercanos.
		SentidoAnimat = SentAnimat;

		double distanciaComida = Double.MAX_VALUE;
		if(mundo.getNumeroPlatosComida() > 0) {
			Plato plato = mundo.getPlatoComidaMasCercano(
					cuerpo.getContorno().getCenterX(), cuerpo.getContorno().getCenterY());
			distanciaComida = plato.getDistancia(
					cuerpo.getContorno().getCenterX(), cuerpo.getContorno().getCenterY());
		}

		Hambre += 0.025/distanciaComida;

		double distanciaAgua = Double.MAX_VALUE;
		if(mundo.getNumeroPlatosAgua() > 0) {
			Plato plato = mundo.getPlatoAguaMasCercano(
					cuerpo.getContorno().getCenterX(), cuerpo.getContorno().getCenterY());
			distanciaAgua = plato.getDistancia(
					cuerpo.getContorno().getCenterX(), cuerpo.getContorno().getCenterY());
		}

		Sed += 0.025/distanciaAgua;

		Ira += 0.025/DistAnimat;
		if (Sed > 150) Sed = 150;
		if (Hambre > 150) Hambre = 150;
		if (Ira > 150) Ira = 150;
	}

	/** Algoritmo de eleccion de comportamiento del Animat:
	 *  En base a sus motivaciones internas y a las condiciones presentes, el Animat
	 *  hacen el calculo de sus niveles de activacion internos y en base a ellos decidiran
	 *  cual es el comportamiento que eligiran para su ejecucion
	 */

	private void CalculoNivelActivacion() {
		double ActIrCom = 0, ActCom = 0, ActIrAgua = 0, ActBeb = 0, ActIrDorm = 0, // Incrementos niveles de activacion
				ActDorm = 0, ActLadr = 0, ActPelea = 0, ActHuir = 0, ActMax = 0, ActRandom = 0, ActRandomMax = 0,
				UmbrIrCom = UmbralIrPorComida, UmbrCom = UmbralComer,
				UmbrIrAgua = UmbralIrPorAgua, UmbrBeb = UmbralBeber,     // Copias locales de los umbrales de activacion
				UmbrLadr = UmbralLadrar, UmbrPelea = UmbralPelear,
				UmbrHuir = UmbralHuir;
		boolean EjecIrCom = false, EjecCom = false, EjecIrAgua = false, EjecBeb = false,
				EjecLadr = false, EjecPelea = false, EjecHuir = false, SalirCond = true, Condi = false, Condi2 = false;// Sirven para discriminar los comportamientos ejecutables
		int CompEjec = 0, CompEleg = 0, i = 0, j = 0, aux = 0; // Numero de comportamientos ejecutables
		double[] Activaciones = new double[7];
		int[] Indices = new int[7];

		// Computo de la activacion debido a las condiciones presentes
		if (isComidaAlcanzable) ActCom += 0.05;
		if (isAguaAlcanzable) ActBeb += 0.05;
		if (AnimatAlAlcance) {
			ActLadr += 0.005; ActPelea += 0.005; }
		if (Agresion) {
			ActPelea -= 0.001; ActHuir += 0.005; }

		// Computo de la activacion debido a las motivaciones internas
		ActCom += Hambre * 0.001; ActBeb += Sed * 0.001; ActLadr += Ira * 0.0005;
		ActPelea += Ira * 0.0005; ActHuir += Miedo * 0.01;

		// Activacion de los sucesores de los comportamientos ejecutables
		// Ir por comida -> Comer
		if (!isComidaAlcanzable && !Agresion) ActCom += ActivacionIrPorComida * 0.0001;
		// Ir por agua -> Beber agua
		if (!isAguaAlcanzable && !Agresion) ActBeb += ActivacionBeber * 0.0001;
		// Huir -> Ir por comida, Ir por agua, Comer, Beber
		if (Agresion) {
			ActIrCom += ActivacionHuir * 0.005; ActCom += ActivacionHuir * 0.005; }

		// Activacion de los predecesores de los comportamientos no ejecutables
		// Comer <- Ir por comida
		if (!isComidaAlcanzable || Agresion) ActIrCom += ActivacionComer * 0.001;
		// Beber <- Ir por agua
		if (!isAguaAlcanzable || Agresion) ActIrAgua += ActivacionBeber * 0.001;
		// Ir por agua <- Huir
		if (isAguaAlcanzable || Agresion) ActHuir += ActivacionIrPorAgua * 0.001;
		// Ir por comida <- Huir
		if (isComidaAlcanzable || Agresion) ActHuir += ActivacionIrPorComida * 0.001;

		// Inhibicion de los comportamientos conflictivos
		ActHuir -= ActivacionLadrar * 0.00005 + ActivacionPelear * 0.00005;

		// Actualizar niveles de activación
		ActivacionIrPorComida += ActIrCom; ActivacionComer += ActCom;
		ActivacionIrPorAgua += ActIrAgua; ActivacionBeber += ActBeb;
		ActivacionLadrar += ActLadr; ActivacionPelear += ActPelea; ActivacionHuir += ActHuir;
		if (ActivacionHuir < 0) ActivacionHuir = 0;
		if (ActivacionIrPorComida < 0) ActivacionIrPorComida = 0;
		if (ActivacionComer < 0) ActivacionComer = 0;
		if (ActivacionIrPorAgua < 0) ActivacionIrPorAgua = 0;
		if (ActivacionBeber < 0) ActivacionBeber = 0;
		if (ActivacionLadrar < 0) ActivacionLadrar = 0;
		if (ActivacionPelear < 0) ActivacionPelear = 0;
		if (ActivacionHuir > 150) ActivacionHuir = 150;
		if (ActivacionIrPorComida > 150) ActivacionIrPorComida = 150;
		if (ActivacionComer > 150) ActivacionComer = 150;
		if (ActivacionIrPorAgua > 150) ActivacionIrPorAgua = 150;
		if (ActivacionBeber > 150) ActivacionBeber = 150;
		if (ActivacionLadrar > 150) ActivacionLadrar = 150;
		if (ActivacionPelear > 150) ActivacionPelear = 150;

		if(imprimirDatos) {
			ventana.EscribeActivacionBeber(ActivacionBeber);
			ventana.EscribeActivacionComer(ActivacionComer);
			ventana.EscribeActivacionHuir(ActivacionHuir);
			ventana.EscribeActivacionIrPorAgua(ActivacionIrPorAgua);
			ventana.EscribeActivacionIrPorComida(ActivacionIrPorComida);
			ventana.EscribeActivacionLadrar(ActivacionLadrar);
			ventana.EscribeActivacionPelear(ActivacionPelear);
			ventana.EscribeMotivacionHambre(Hambre);
			ventana.EscribeMotivacionIra(Ira);
			ventana.EscribeMotivacionMiedo(Miedo);
			ventana.EscribeMotivacionSed(Sed);
			ventana.EscribeSensoresAgresion(Agresion);
			ventana.EscribeSensoresAguaAlAlcance(isAguaAlcanzable);
			ventana.EscribeSensoresComidaAlAlcance(isComidaAlcanzable);
			ventana.EscribeSensoresPerro(AnimatAlAlcance);
		}


		// Seleccion de comportamientos
		// Casi todos los comportamientos dependen de que haya agresion o no, así que por ello
		// los englobaremos en un if que tenga esta condicion

		if (CiclosdeEsperaSeleccion >= 600) {
			do {
				if (Agresion) {
					if (!AnimatAlAlcance && (ActivacionPelear > UmbrPelea)) {
						EjecPelea = true; CompEjec++; }
					if (AnimatAlAlcance && (ActivacionHuir > UmbrHuir)) {
						EjecHuir = true; CompEjec++; }
				} else {
					if (isComidaAlcanzable && (ActivacionComer > UmbrCom)) {
						EjecCom = true; CompEjec++; } else if (!isComidaAlcanzable && (ActivacionIrPorComida > UmbrIrCom)) {
						EjecCom = true; CompEjec++; }
					if (isAguaAlcanzable && (ActivacionBeber > UmbrBeb)) {
						EjecBeb = true; CompEjec++; } else if (!isAguaAlcanzable && (ActivacionIrPorAgua > UmbrIrAgua)) {
						EjecBeb = true; CompEjec++; }
					if (AnimatAlAlcance && (ActivacionLadrar > UmbrLadr)) {
						EjecLadr = true; CompEjec++; }
				}

				if(CompEjec == 0) {
					// Disminuye los umbrales
					UmbrIrCom -= 15; UmbrCom -= 15; UmbrIrAgua -= 15; UmbrBeb -= 15;
					UmbrLadr -= 15; UmbrPelea -= 15; UmbrHuir -= 15;
					// Se queda en el ciclo
					SalirCond = false; } else SalirCond = true; // Sale del loop

				CompEjec = 0; // Reinicializa el conteo de comportamientos ejecutable

			} while(!SalirCond);

			if(EjecHuir && (ActivacionHuir > ActMax)) {
				CompEleg = 6;
			} else if(EjecHuir && (ActivacionHuir == ActMax)) {
				ActRandom = Math.random();
				if (ActRandom > ActRandomMax) { CompEleg = 6; ActRandomMax = ActRandom; }
			}

			if(EjecCom && (ActivacionComer > ActMax)) {
				CompEleg = 1;
			} else if(EjecCom && (ActivacionComer == ActMax)) {
				ActRandom = Math.random();
				if (ActRandom > ActRandomMax) { CompEleg = 1; ActRandomMax = ActRandom; }
			}
			if(EjecIrAgua && (ActivacionIrPorAgua > ActMax)) {
				CompEleg = 2;
			} else if(EjecIrAgua && (ActivacionIrPorAgua == ActMax)) {
				ActRandom = Math.random();
				if (ActRandom > ActRandomMax) { CompEleg = 2; ActRandomMax = ActRandom; }
			}
			if(EjecBeb && (ActivacionBeber > ActMax)) {
				CompEleg = 3;
			} else if(EjecBeb && (ActivacionBeber == ActMax)) {
				ActRandom = Math.random();
				if (ActRandom > ActRandomMax) { CompEleg = 3; ActRandomMax = ActRandom; }
			}
			if(EjecLadr && (ActivacionLadrar > ActMax)) {
				CompEleg = 4;
			} else if(EjecLadr && (ActivacionLadrar == ActMax)) {
				ActRandom = Math.random();
				if (ActRandom > ActRandomMax) { CompEleg = 4; ActRandomMax = ActRandom; }
			}
			if(EjecPelea && (ActivacionPelear > ActMax)) {
				CompEleg = 5;
			} else if(EjecPelea && (ActivacionPelear == ActMax)) {
				ActRandom = Math.random();
				if (ActRandom > ActRandomMax) { CompEleg = 5; ActRandomMax = ActRandom; }
			}

			if(EjecIrCom && (ActivacionIrPorComida > ActMax)) {
				CompEleg = 0;
			} else if(EjecIrCom && (ActivacionIrPorComida == ActMax)) {
				ActRandom = Math.random();
				if (ActRandom > ActRandomMax) { CompEleg = 0; ActRandomMax = ActRandom; }
			}

			ComportamientoElegido = CompEleg; // Setea el comportamiento elegido

			switch (ComportamientoElegido) {
				case 0: ActivacionIrPorComida = 0; break;
				case 1: ActivacionComer = 0; break;
				case 2: ActivacionIrPorAgua = 0; break;
				case 3: ActivacionBeber = 0; break;
				case 4: ActivacionLadrar = 0; break;
				case 5: ActivacionPelear = 0; break;
				case 6: ActivacionHuir = 0; break;
			}

			CiclosdeEsperaSeleccion = 0;
		}
	}

	/** Implementacion de los comportamientos de los Animats.
	 *  Esta sección implementa los métodos que ejecutaran los comportamientos que el Animat
	 *  estará mostrando durante su periodo de vida.
	 */

	private void irPorComida() {
		Peleando = false; Ladrando = false; Huyendo = false;

		if (velocidadDesplazamiento > 0.25) velocidadDesplazamiento = 0.25;

		if(mundo.getNumeroPlatosComida() <= 0)
			return;

		double x = cuerpo.getContorno().getCenterX(), y = cuerpo.getContorno().getCenterY();
		Plato plato = mundo.getPlatoComidaMasCercano(x,y);
		desplazamiento(plato.getAngulo(x, y));
	}

	private void comer() {
		Peleando = false; Ladrando = false; Huyendo = false;

		if(mundo.getNumeroPlatosComida() <= 0)
			return;

		double x = cuerpo.getContorno().getCenterX(), y = cuerpo.getContorno().getCenterY();
		Plato plato = mundo.getPlatoComidaMasCercano(x,y);

		if (plato.getDistancia(x, y) > Plato.DIMENSION_PLATO)
			desplazamiento(plato.getAngulo(x, y));
		else if (CiclosdeEspera < 400) {
			Comiendo = true;
			CiclosdeEspera++;
			desplazandose = false;
			Sed += 0.005;
			if (Sed > 150) Sed = 150;
		} else {
			mundo.removePlatoComida(plato);
			CiclosdeEspera = 0;
			Comiendo = false;
			Sed += 0.005;
			if (Sed > 150) Sed = 150;
		}

		Hambre -= 5;
		if (Hambre < 0) Hambre = 0;
	}

	private void irPorAgua() {
		Peleando = false; Ladrando = false; Huyendo = false;

		if (velocidadDesplazamiento > 0.25) velocidadDesplazamiento = 0.25;

		if(mundo.getNumeroPlatosAgua() <= 0)
			return;

		double x = cuerpo.getContorno().getCenterX(), y = cuerpo.getContorno().getCenterY();
		Plato plato = mundo.getPlatoAguaMasCercano(x, y);
		desplazamiento(plato.getAngulo(x, y));
	}

	private void beber() {
		Peleando = false; Ladrando = false; Huyendo = false;

		if(mundo.getNumeroPlatosAgua() <= 0)
			return;

		double x = cuerpo.getContorno().getCenterX(), y = cuerpo.getContorno().getCenterY();
		Plato plato = mundo.getPlatoAguaMasCercano(x, y);

		if (plato.getDistancia(x, y) > Plato.DIMENSION_PLATO)
			desplazamiento(plato.getAngulo(x, y));
		else if (CiclosdeEspera < 400) {
			Bebiendo = true;
			CiclosdeEspera++;
			desplazandose = false;
			Hambre += 0.005;
			if (Hambre > 150) Hambre = 150;
		} else {
			mundo.removePlatoAgua(plato);
			CiclosdeEspera = 0;
			Bebiendo = false;
			Hambre += 0.005;
			if (Hambre > 150) Hambre = 150;
		}

		Sed -= 5;
		if (Sed < 0) Sed = 0;
	}

	private void Ladrar() {
		desplazandose = false;
		Ladrando = true;
		Huyendo = false;
		Ira += 0.005;
		Hambre += 0.15;
		Sed += 0.05;
		Miedo += 0.15;
		if (Hambre > 150) Hambre = 150;
		if (Ira > 150) Ira = 150;
		if (Sed > 150) Sed = 150;
		if (Miedo > 150) Miedo = 150;
	}

	private void Pelear() {
		Peleando = true;
		Huyendo = false;
		if (velocidadDesplazamiento != 0.3) velocidadDesplazamiento = 0.3;
		desplazamiento(DireccionAnimat, SentidoAnimat);
		Ira -= 5;
		Miedo += 0.15;
		if (Miedo > 150) Miedo = 150;
		if (Ira < 0) Ira = 0;
	}

	private void Huir () {
		Peleando = false; Ladrando = false;
		Huyendo = true;
		if (velocidadDesplazamiento < 0.45) velocidadDesplazamiento = 0.45;
		desplazamiento(DireccionAnimat, !SentidoAnimat);
		Hambre += 0.05;
		Sed += 0.05;

		Miedo -= 5;
		if (Hambre > 150) Hambre = 150;
		if (Sed > 150) Sed = 150;
		if (Miedo < 0) Miedo = 0;
	}

	private void EjecutarComportamiento () {
		switch (ComportamientoElegido) {
			case 0: irPorComida(); break;
			case 1: comer(); break;
			case 2: irPorAgua(); break;
			case 3: beber(); break;
			case 4: Ladrar(); break;
			case 5: Pelear(); break;
			case 6: Huir(); break;
		}
	}
}
