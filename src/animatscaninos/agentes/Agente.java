package animatscaninos.agentes;

import java.util.Vector;

import animatscaninos.comportamientos.Comportamiento;



/**
 * Clase que encapsula cómo elige un comportamiento un agente, de acuerdo a
 * el esquema propuesto por Pattie Maes.
 * 
 * @author Diego Hdez.
 *
 */
public class Agente {

  //
  // Fields
  //

  private double mediaActivacion = 0;
  private double umbralActivacion = 0;
  private double activacionPorPrecondicion = 0;
  private double activacionPorMeta = 0;
  private Comportamiento comportamientoActivo = null;
  private Proposicion[] proposiciones = null;
  private double inhibicionPorMetaProtegida = 0;
  private Comportamiento[] comportamientos = null;
  private Proposicion[] metasProtegidas = null;
  
  //
  // Constructors
  //
  public Agente () { };
  
  //
  // Methods
  //


  //
  // Accessor methods
  //

  /**
   * Set the value of mediaActivacion
   * @param newVar the new value of mediaActivacion
   */
  private void setMediaActivacion ( double newVar ) {
    mediaActivacion = newVar;
  }

  /**
   * Get the value of mediaActivacion
   * @return the value of mediaActivacion
   */
  private double getMediaActivacion ( ) {
    return mediaActivacion;
  }

  /**
   * Set the value of umbralActivacion
   * @param newVar the new value of umbralActivacion
   */
  private void setUmbralActivacion ( double newVar ) {
    umbralActivacion = newVar;
  }

  /**
   * Get the value of umbralActivacion
   * @return the value of umbralActivacion
   */
  private double getUmbralActivacion ( ) {
    return umbralActivacion;
  }

  /**
   * Set the value of activacionPorPrecondicion
   * @param newVar the new value of activacionPorPrecondicion
   */
  private void setActivacionPorPrecondicion ( double newVar ) {
    activacionPorPrecondicion = newVar;
  }

  /**
   * Get the value of activacionPorPrecondicion
   * @return the value of activacionPorPrecondicion
   */
  private double getActivacionPorPrecondicion ( ) {
    return activacionPorPrecondicion;
  }

  /**
   * Set the value of activacionPorMeta
   * @param newVar the new value of activacionPorMeta
   */
  private void setActivacionPorMeta ( double newVar ) {
    activacionPorMeta = newVar;
  }

  /**
   * Get the value of activacionPorMeta
   * @return the value of activacionPorMeta
   */
  private double getActivacionPorMeta ( ) {
    return activacionPorMeta;
  }

  /**
   * Set the value of comportamientoActivo
   * @param newVar the new value of comportamientoActivo
   */
  private void setComportamientoActivo ( Comportamiento newVar ) {
    comportamientoActivo = newVar;
  }

  /**
   * Get the value of comportamientoActivo
   * @return the value of comportamientoActivo
   */
  private Comportamiento getComportamientoActivo ( ) {
    return comportamientoActivo;
  }

  /**
   * Set the value of proposiciones
   * @param newVar the new value of proposiciones
   */
  private void setProposiciones ( Proposicion[] newVar ) {
    proposiciones = newVar;
  }

  /**
   * Get the value of proposiciones
   * @return the value of proposiciones
   */
  private Proposicion[] getProposiciones ( ) {
    return proposiciones;
  }

  /**
   * Set the value of inhibicionPorMetaProtegida
   * @param newVar the new value of inhibicionPorMetaProtegida
   */
  private void setInhibicionPorMetaProtegida ( double newVar ) {
    inhibicionPorMetaProtegida = newVar;
  }

  /**
   * Get the value of inhibicionPorMetaProtegida
   * @return the value of inhibicionPorMetaProtegida
   */
  private double getInhibicionPorMetaProtegida ( ) {
    return inhibicionPorMetaProtegida;
  }

  /**
   * Set the value of comportamientos
   * @param newVar the new value of comportamientos
   */
  private void setComportamientos ( Comportamiento[] newVar ) {
    comportamientos = newVar;
  }

  /**
   * Get the value of comportamientos
   * @return the value of comportamientos
   */
  private Comportamiento[] getComportamientos ( ) {
    return comportamientos;
  }

  /**
   * Set the value of metasProtegidas
   * @param newVar the new value of metasProtegidas
   */
  private void setMetasProtegidas ( Proposicion[] newVar ) {
    metasProtegidas = newVar;
  }

  /**
   * Get the value of metasProtegidas
   * @return the value of metasProtegidas
   */
  private Proposicion[] getMetasProtegidas ( ) {
    return metasProtegidas;
  }

  //
  // Other methods
  //

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
   */
  public Vector<Proposicion> getMetasActivas() {
	  Vector<Proposicion> activas = new Vector<Proposicion>();
	  return activas;
  }


  /**
   * @return       Proposicion[]
   */
  public Proposicion[] getMetasProtegidas(  )
  {
  }


  /**
   * Ejecuta el algorito de selección del comportamiento.
   */
  private void decidirComportamiento() {
	  
  }


  /**
   */
  private static void verificarCondiciones(  )
  {
  }


}
