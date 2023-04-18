package animatscaninos.comportamientos;

import animatscaninos.agentes.Proposicion;

import java.util.List;

/**
 * Clase Comportamiento.
 * 
 * Encapsula la funcionalidad general de un módulo de comportamiento. Las
 * acciones particulares ejecutadas por los comportamientos son implementadas
 * parte.
 * 
 * @author Diego Enrique Hernández González.
 */
abstract public class Comportamiento {
	private double nivelActivacion = 0;
	
	/** Precondiciones para que este comportamiento sea activado. */
	private List<Proposicion> precondiciones = null;
	
	/** Metas activadas por este comportamiento. */
	private List<Proposicion> metas = null;
	
	/** Metas desactivadas por este comportamiento. */
	private List<Proposicion> antimetas = null;
  
	public Comportamiento () { };
  
	/**
	 * Establece el nivel de activación de este comportamiento.
	 * @param newVar Nuevo nivel de activación.
	 */
	private void setNivelActivacion ( double newVar ) {
		nivelActivacion = newVar;
	}

	/**
	 * @return Nivel de activación de este comportamiento.
	 */
	public double getNivelActivacion() {
		return nivelActivacion;
	}

	/**
	 * Establece el conjunto de precondiciones de este comportamiento.
	 * @param newVar Arreglo de proposiciones.
	 */
	private void setPrecondiciones (List<Proposicion> newVar) {
		precondiciones = newVar;
	}

	/**
	 * Obtiene el conjunto de precondiciones de este comportamiento.
	 * @return Arreglo de proposiciones.
	 */
	public List<Proposicion> getPrecondiciones() {
		return precondiciones;
	}

	/**
	 * Introduce las metas a las cuales facilita este comportamiento.
	 * @param newVar Metas de este comportamiento.
	 */
	private void setMetas ( List<Proposicion> newVar ) {
		metas = newVar;
	}

	/**
	 * @return Arreglo de las metas que activa este comportamiento.
	 */
	public List<Proposicion> getMetas ( ) {
		return metas;
	}

	/**
	 * Introduce las metas las cuales son evitadas por este comportamiento.
	 * @param newVar Arreglo de metas.
	 */
	private void setAntimetas ( List<Proposicion> newVar ) {
		antimetas = newVar;
	}

	/**
	 * Obtiene las metas que son inhibidas al ejecutarse este comportamiento.
	 * @return Arreglo de metas inhibidas. 
	 */
	public List<Proposicion> getAntimetas() {
		return antimetas;
	}

	/**
	 * Corre una estimación del nivel de activación de este comportamiento.
	 * TODO Verificar si es necesario.
	 */
	abstract public void calcularNivelActivacion(  );


	/**
	 * Verifica si este comportamiento está activo.
	 * TODO Implementar.
	 * @return Resultado de la verificación.
	 */
	public boolean isActivo() {
		return false;
	}


	/**
	 * Verifica si este comportamiento está en estado ejecutable.
	 * TODO Implementar.
	 * @return Resultado de la verificación.
	 */
	public boolean isEjecutable() {
		return false;
	}
}
