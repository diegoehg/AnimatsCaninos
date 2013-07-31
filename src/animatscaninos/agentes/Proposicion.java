package animatscaninos.agentes;

import animatscaninos.comportamientos.Comportamiento;
import animatscaninos.elementos.Mundo;

import java.util.*;


/**
 * Class Proposicion
 * Representa un enunciado acerca del estado del mundo, el cual puede ser cierto o
 * no.
 */
abstract public class Proposicion {

  //
  // Fields
  //

  private Mundo mundo;
  private Comportamiento[] sucesores = null;
  private Comportamiento[] predecesores = null;
  private Comportamiento[] inhibidores = null;
  
  //
  // Constructors
  //
  public Proposicion () { };
  
  //
  // Methods
  //


  //
  // Accessor methods
  //

  /**
   * Set the value of mundo
   * @param newVar the new value of mundo
   */
  private void setMundo ( Mundo newVar ) {
    mundo = newVar;
  }

  /**
   * Get the value of mundo
   * @return the value of mundo
   */
  private Mundo getMundo ( ) {
    return mundo;
  }

  /**
   * Set the value of sucesores
   * @param newVar the new value of sucesores
   */
  private void setSucesores ( Comportamiento[] newVar ) {
    sucesores = newVar;
  }

  /**
   * Get the value of sucesores
   * @return the value of sucesores
   */
  private Comportamiento[] getSucesores ( ) {
    return sucesores;
  }

  /**
   * Set the value of predecesores
   * @param newVar the new value of predecesores
   */
  private void setPredecesores ( Comportamiento[] newVar ) {
    predecesores = newVar;
  }

  /**
   * Get the value of predecesores
   * @return the value of predecesores
   */
  private Comportamiento[] getPredecesores ( ) {
    return predecesores;
  }

  /**
   * Set the value of inhibidores
   * @param newVar the new value of inhibidores
   */
  private void setInhibidores ( Comportamiento[] newVar ) {
    inhibidores = newVar;
  }

  /**
   * Get the value of inhibidores
   * @return the value of inhibidores
   */
  private Comportamiento[] getInhibidores ( ) {
    return inhibidores;
  }

  //
  // Other methods
  //

  /**
   * @return       boolean
   */
  abstract public boolean seCumple(  );


  /**
   * @return       Comportamiento[]
   */
  public Comportamiento[] getPrecesores(  )
  {
  }


  /**
   * @return       Comportamiento[]
   */
  public Comportamiento[] getSucesores(  )
  {
  }


  /**
   * @return       Comportamiento[]
   */
  public Comportamiento[] getInhibidores(  )
  {
  }


  /**
   * @param        p
   */
  public void setPredecesores( Comportamiento[] p )
  {
  }


  /**
   * @param        s
   */
  public void setSucesores( Comportamiento[] s )
  {
  }


  /**
   * @param        i
   */
  public void setInhibidores( Comportamiento[] i )
  {
  }


}
