package pe.egcc.ventasweb.model;

/**
 *
 * @author Eric Gustavo Coronel Castillo
 * @blog www.desarrollasoftware.com
 * @email egcc.usil@gmail.com
 */
public class Detalle {

  private int iddetalle;
  private int idventa;
  private int idprod;
  private String nombre;
  private int cant;
  private double precatalogo;
  private double preventa;
  private double subtotal;

  public Detalle() {
  }

  public Detalle(int iddetalle, int idventa, int idprod, String nombre, int cant, double precatalogo, double preventa, double subtotal) {
    this.iddetalle = iddetalle;
    this.idventa = idventa;
    this.idprod = idprod;
    this.nombre = nombre;
    this.cant = cant;
    this.precatalogo = precatalogo;
    this.preventa = preventa;
    this.subtotal = subtotal;
  }

  public int getIddetalle() {
    return iddetalle;
  }

  public void setIddetalle(int iddetalle) {
    this.iddetalle = iddetalle;
  }

  public int getIdventa() {
    return idventa;
  }

  public void setIdventa(int idventa) {
    this.idventa = idventa;
  }

  public int getIdprod() {
    return idprod;
  }

  public void setIdprod(int idprod) {
    this.idprod = idprod;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public int getCant() {
    return cant;
  }

  public void setCant(int cant) {
    this.cant = cant;
  }

  public double getPrecatalogo() {
    return precatalogo;
  }

  public void setPrecatalogo(double precatalogo) {
    this.precatalogo = precatalogo;
  }

  public double getPreventa() {
    return preventa;
  }

  public void setPreventa(double preventa) {
    this.preventa = preventa;
  }

  public double getSubtotal() {
    return subtotal;
  }

  public void setSubtotal(double subtotal) {
    this.subtotal = subtotal;
  }

}
