package animatscaninos.agentes;

import animatscaninos.comportamientos.Comportamiento;
import animatscaninos.elementos.Mundo;

import java.util.*;


/**
 * Clase Proposicion.
 * 
 * Representa un enunciado acerca del estado del mundo, el cual se puede cumplir
 * o no. Los hechos acerca del mundo sirven como metas o precondiciones de los
 * comportamientos de los Animats.
 * 
 * @author Diego Enrique Hernández González.
 */
abstract public class Proposicion {
	private Mundo mundo;
	
	/** Los comportamientos para los cuales esta proposición es una 
	 *  precondición. */
	private Comportamiento[] sucesores = null;
	
	/** Los comportamientos que activan esta preposición. */
	private Comportamiento[] predecesores = null;
	
	/** Los comportamientos que inhiben esta preposición. */
	private Comportamiento[] inhibidores = null;
  
	public Proposicion () { };
  
	/**
	 * Introduce la instancia del mundo sobre el cual se verificará esta 
	 * proposición.
	 * @param newVar Instancia del mundo.
	 */
	private void setMundo (Mundo newVar) {
		mundo = newVar;
	}

	/**
	 * Obtiene la instancia del mundo sobre la cual se verifica esta 
	 * proposición.
	 * @return Instancia del mundo.
	 */
	private Mundo getMundo() {
		return mundo;
	}

	/**
	 * Introduce los comportamientos para los cuales ésta proposición es una
	 * precondición.
	 * @param newVar Arreglo de comportamientos.
	 */
	private void setSucesores(Comportamiento[] newVar) {
		sucesores = newVar;
	}

	/**
	 * Obtiene los comportamientos sucesores de esta proposición.
	 * @return Arreglo de comportamientos sucesores.
	 */
	public Comportamiento[] getSucesores() {
		return sucesores;
	}

	/**
	 * Introduce los comportamientos predecesores de este enunciado.
	 * @param newVar Arreglo de comportamientos.
	 */
	private void setPredecesores ( Comportamiento[] newVar ) {
		predecesores = newVar;
	}

	/**
	 * Obtiene los comportamientos predecesores de este enunciado.
	 * @return Arreglo de los comportamientos predecesores.
	 */
	public Comportamiento[] getPredecesores ( ) {
		return predecesores;
	}

	/**
	 * Introduce los comportamientos inhibidores de esta proposición.
	 * @param newVar Arreglo de comportamientos.
	 */
	private void setInhibidores ( Comportamiento[] newVar ) {
		inhibidores = newVar;
	}

	/**
	 * Obtiene los comportamientos inhibidores de esta proposición.
	 * @return Arreglo de los comportamientos inhibidores.
	 */
	public Comportamiento[] getInhibidores ( ) {
		return inhibidores;
	}

	/**
	 * Verifica si esta proposición se cumple en el mundo.
	 * @return Resultado de la verificación.
	 */
	abstract public boolean seCumple();
}
