package animatscaninos.comportamientos;

import java.util.*;


/**
 * Class Comportamiento
 */
abstract public class Comportamiento {

  //
  // Fields
  //

  private double nivelActivacion = 0;
  private Proposicion[] precondiciones = null;
  private Proposicion[] metas = null;
  private Proposicion[] antimetas = null;
  
  //
  // Constructors
  //
  public Comportamiento () { };
  
  //
  // Methods
  //


  //
  // Accessor methods
  //

  /**
   * Set the value of nivelActivacion
   * @param newVar the new value of nivelActivacion
   */
  private void setNivelActivacion ( double newVar ) {
    nivelActivacion = newVar;
  }

  /**
   * Get the value of nivelActivacion
   * @return the value of nivelActivacion
   */
  private double getNivelActivacion ( ) {
    return nivelActivacion;
  }

  /**
   * Set the value of precondiciones
   * @param newVar the new value of precondiciones
   */
  private void setPrecondiciones ( Proposicion[] newVar ) {
    precondiciones = newVar;
  }

  /**
   * Get the value of precondiciones
   * @return the value of precondiciones
   */
  private Proposicion[] getPrecondiciones ( ) {
    return precondiciones;
  }

  /**
   * Set the value of metas
   * @param newVar the new value of metas
   */
  private void setMetas ( Proposicion[] newVar ) {
    metas = newVar;
  }

  /**
   * Get the value of metas
   * @return the value of metas
   */
  private Proposicion[] getMetas ( ) {
    return metas;
  }

  /**
   * Set the value of antimetas
   * @param newVar the new value of antimetas
   */
  private void setAntimetas ( Proposicion[] newVar ) {
    antimetas = newVar;
  }

  /**
   * Get the value of antimetas
   * @return the value of antimetas
   */
  private Proposicion[] getAntimetas ( ) {
    return antimetas;
  }

  //
  // Other methods
  //

  /**
   */
  abstract public void calcularNivelActivacion(  );


  /**
   * @return       boolean
   */
  public boolean isActivo(  )
  {
  }


  /**
   * @return       boolean
   */
  public boolean isEjecutable(  )
  {
  }


  /**
   * @return       double
   */
  public double getNivelActivacion(  )
  {
  }


}
