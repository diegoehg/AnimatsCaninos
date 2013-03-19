package animatscaninos.comportamientos;


/**
 * Class Comportamiento
 */
abstract public class Comportamiento {

  //
  // Fields
  //

  private double nivelActivacion;
  private double umbralActivacion;
  
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

  //
  // Other methods
  //

  /**
   */
  abstract public void ejecutar(  );


  /**
   */
  abstract public void calculaNivelActivacion(  );


  /**
   * @param        energia
   */
  public void sumarNivelActivacion_( double energia )
  {
  }


}
