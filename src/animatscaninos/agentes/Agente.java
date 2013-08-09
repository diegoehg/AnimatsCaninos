package animatscaninos.agentes;

import java.util.Collections;
import java.util.Vector;

import animatscaninos.comportamientos.Comportamiento;



/**
 * Clase que encapsula cómo elige un comportamiento un agente, de acuerdo a
 * el esquema propuesto por Pattie Maes [1].
 * 
 * [1] Maes, P. "How to do the right thing". Connection Science 1(3):291-323.
 * 
 * @author Diego Hdez.
 *
 */
public class Agente {
	/** Nivel promedio de activación de todos los agentes. */
	private static double mediaActivacion = 0;
	
	/** Umbral de activación por agente. */
	private static double umbralActivacion = 0;
	
	/** Monto de energía inyectada por el estado del mundo por cada proposición 
	 *  verdadera. */
	private static double activacionPorPrecondicion = 0;
	
	/** Monto de energía inyectada por las metas predecesoras. */
	private static double activacionPorMeta = 0;
	
	/** Comportamiento activo en el sistema. */
	private Comportamiento comportamientoActivo = null;
	
	/** Conjunto de proposiciones realizadas sobre el ambiente de los Animats */
	private Proposicion[] proposiciones = null;
	
	/** Monto de energía que se toma de este comportamiento para proteger una
	 *  meta ya cumplida. */
	private static double inhibicionPorMetaProtegida = 0;
	
	/** Conjunto de los comportamientos del agente. */
	private Comportamiento[] comportamientos = null;
	
	/** Metas que ya alcanzó el agente y deben ser protegidas. */
	private Vector<Proposicion> metasProtegidas = null;
  
	/** Constructor. */
	public Agente () { };
  
	/**
	 * Introduce el valor de la energía media de activación.
	 * @param newVar Monto de energía establecida.
	 */
	public static void setMediaActivacion ( double newVar ) {
		mediaActivacion = newVar;
	}

	/**
	 * Obtiene el valor de la media de activación de los agentes.
	 * @return Media de activación.
	 */
	public static double getMediaActivacion ( ) {
		return mediaActivacion;
	}

	/**
	 * Introduce el valor del umbral de activación.
	 * @param newVar Nuevo valor del umbral de activación.
	 */
	public static void setUmbralActivacion ( double newVar ) {
		umbralActivacion = newVar;
	}

	/**
	 * Obtiene el valor del umbral de activación.
	 * @return Monto del umbral de activación.
	 */
	public static double getUmbralActivacion ( ) {
		return umbralActivacion;
	}

	/**
	 * Introduce el valor de activación por precondición.
	 * @param newVar Nuevo monto de activación por precondición.
	 */
	public static void setActivacionPorPrecondicion ( double newVar ) {
		activacionPorPrecondicion = newVar;
	}

	/**
	 * Obtiene el valor de activación por precondición.
	 * @return Monto de activación por precondición.
	 */
	public static double getActivacionPorPrecondicion ( ) {
		return activacionPorPrecondicion;
	}

	/**
	 * Establece el valor de activación por meta.
	 * @param newVar Nuevo monto de activación por meta.
	 */
	public static void setActivacionPorMeta ( double newVar ) {
		activacionPorMeta = newVar;
	}

	/**
	 * Devuelve el valor de activación por meta.
	 * @return Monto de activación por meta.
	 */
	public static double getActivacionPorMeta ( ) {
		return activacionPorMeta;
	}

	/**
	 * Establece el comportamiento activo.
	 * @param newVar the new value of comportamientoActivo
	 */
	private void setComportamientoActivo ( Comportamiento newVar ) {
		comportamientoActivo = newVar;
	}

	/**
	 * Devuelve el comportamiento activo.
	 * @return the value of comportamientoActivo
	 */
	private Comportamiento getComportamientoActivo ( ) {
		return comportamientoActivo;
	}

	/**
	 * Establece el conjunto de proposiciones sobre el mundo.
	 * @param newVar the new value of proposiciones
	 */
	private void setProposiciones ( Proposicion[] newVar ) {
		proposiciones = newVar;
	}

	/**
	 * Obtiene el conjunto de proposiciones sobre el mundo.
	 * @return the value of proposiciones
	 */
	private Proposicion[] getProposiciones ( ) {
		return proposiciones;
	}

	/**
	 * Establece el monto de energía extraído por meta protegida.
	 * @param newVar Nuevo monto de energía por meta protegida.
	 */
	public static void setInhibicionPorMetaProtegida ( double newVar ) {
		inhibicionPorMetaProtegida = newVar;
	}

	/**
	 * Obtiene el monto de energía que extrae cada meta protegida.
	 * @return Monto de energía por meta protegida.
	 */
	public static double getInhibicionPorMetaProtegida ( ) {
		return inhibicionPorMetaProtegida;
	}

	/**
	 * Introduce el conjunto de comportamientos del Animat.
	 * @param newVar the new value of comportamientos
	 */
	private void setComportamientos ( Comportamiento[] newVar ) {
		comportamientos = newVar;
	}

	/**
	 * Obtiene los comportamientos del Animat.
	 * @return the value of comportamientos
	 */
	private Comportamiento[] getComportamientos ( ) {
		return comportamientos;
	}

	/**
	 * Introduce el conjunto de metas protegidas.
	 * @param newVar Vector de las metas protegidas.
	 */
	private void setMetasProtegidas ( Vector<Proposicion> newVar ) {
		metasProtegidas = newVar;
	}

	/**
	 * Obtiene las metas protegidas del sistema.
	 * @return Vector de metas protegidas.
	 */
	private Vector<Proposicion> getMetasProtegidas() {
		return metasProtegidas;
	}

	/**
	 * Evalúa el conjunto de proposiciones relativas a este agente, y elige a
	 * aquellas que son verdaderas y las devuelve.
	 * 
	 * @return Conjunto de proposiciones verdaderas.
	 */
	public Vector<Proposicion> getProposicionesVerdaderas() {
		Vector<Proposicion> verdaderas = new Vector<Proposicion>();
	  
		for(Proposicion p:proposiciones) {
			if(p.seCumple())
				verdaderas.add(p);
		}
	  
		return verdaderas;
	}


	/**
	 * Obtiene el conjunto de metas activas y las devuelve.
	 * @return Vector con las metas activas en un instante del tiempo.
	 * TODO Implementar.
	 */
	private Vector<Proposicion> getMetasActivas() {
		Vector<Proposicion> activas = new Vector<Proposicion>();
		return activas;
	}

	/**
	 * Ejecuta el algoritmo de selección del comportamiento.
	 */
	private void decidirComportamiento() {
		
	}
	
	/**
	 * Obtiene un monto unitario de energía, dividiendo el monto indicado entre
	 * el número de comportamientos y el número de proposiciones. Este cálculo
	 * se repite constatemente en todo el modelo del agente.
	 * 
	 * @param e Monto de energía que se reparte entre comportamientos y 
	 * proposiciones.
	 * @param c Comportamientos.
	 * @param p Proposiciones.
	 * @return Monto de energía unitario.
	 */
	private static double getMontoUnitario(double e, Vector<Comportamiento> c, 
			Vector<Proposicion> p) {
		return e * (1 / c.size()) * (1 / p.size());
	}
	
	/**
	 * Estima la energía de activación obtenida por un comportamiento de acuerdo
	 * al estado actual del mundo. 
	 * 
	 * @param c Comportamiento que recibe la energía de activación.
	 * @return Monto de energía recibido.
	 */
	private double activacionDesdeEstadoActual(Comportamiento c) {
		double energia = 0;
		
		for(Proposicion p: c.getPrecondiciones())
			if(p.seCumple())
				energia += getMontoUnitario(getActivacionPorPrecondicion(), 
						p.getSucesores(), c.getPrecondiciones());
		
		return energia;
	}
	
	/**
	 * Calcula la cantidad de energía de activación obtenida por las metas
	 * activas de un comportamiento.
	 * @param c Comportamiento.
	 * @return Monto de energía recibido.
	 */
	private double activacionDesdeMetas(Comportamiento c) {
		double energia = 0;
		
		for(Proposicion p: c.getMetas())
			if(getMetasActivas().contains(p))
				energia += getMontoUnitario(getActivacionPorMeta(), 
						p.getPredecesores(), c.getMetas());
		
		return energia;
	}
	
	/**
	 * Calcula la cantidad de energía extraída del nivel de activación de un
	 * comportamiento por las metas que ya están protegidas.
	 * @param c Comportamiento.
	 * @return Monto de energía extraído.
	 */
	private double inhibicionDesdeMetasProtegidas(Comportamiento c) {
		double energia = 0;
		
		for(Proposicion p: c.getAntimetas())
			if(getMetasProtegidas().contains(p))
				energia += getMontoUnitario(getInhibicionPorMetaProtegida(), 
						p.getInhibidores(), c.getAntimetas());
		
		return energia;
	}
	
	/**
	 * Calcula la cantidad de energía que un comportamiento distribuye hacia
	 * un comportamiento predecesor.
	 * @param x Comportamiento.
	 * @param y Comportamiento predecesor.
	 * @return Cantidad de energía distribuída.
	 */
	private double distribuyePredecesor(Comportamiento x, Comportamiento y) {
		double energia = 0;
		if(x.isEjecutable())
			return 0;
		
		for(Proposicion p: x.getPrecondiciones())
			if(!p.seCumple() && y.getMetas().contains(p))
				energia += getMontoUnitario(x.getNivelActivacion(),
						p.getPredecesores(),y.getMetas());
		
		return energia;
	}
	
	/**
	 * Calcula la cantidad de energía que un módulo de comportamiento distribuye
	 * hacia un comportamiento sucesor.
	 * @param x Comportamiento.
	 * @param y Comportamiento sucesor.
	 * @return Cantidad de energía distribuida.
	 */
	private double distribuyeSucesor(Comportamiento x, Comportamiento y) {
		double energia = 0;
		
		if(!x.isEjecutable())
			return 0;

		for(Proposicion p: x.getMetas())
			if(!p.seCumple() && y.getPrecondiciones().contains(p))
				energia += getMontoUnitario(x.getNivelActivacion() * 
						(getActivacionPorPrecondicion()/getActivacionPorMeta()), 
						p.getSucesores(), y.getPrecondiciones());
		
		return energia;
	}
}
