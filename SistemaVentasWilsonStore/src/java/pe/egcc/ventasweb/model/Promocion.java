package pe.egcc.ventasweb.model;

import java.util.Date;

/**
 *
 * @author Eric Gustavo Coronel Castillo
 * @blog www.desarrollasoftware.com
 * @email egcc.usil@gmail.com
 */
public class Promocion {

  private int idprom;
  private Date fecinicio;
  private Date fecfin;
  private int idprod;
  private String nombre;
  private double pactual;
  private double precio;
  private double oferta;
  private int estado;

  public Promocion() {
  }

  public int getIdprom() {
    return idprom;
  }

  public void setIdprom(int idprom) {
    this.idprom = idprom;
  }

  public Date getFecinicio() {
    return fecinicio;
  }

  public void setFecinicio(Date fecinicio) {
    this.fecinicio = fecinicio;
  }

  public Date getFecfin() {
    return fecfin;
  }

  public void setFecfin(Date fecfin) {
    this.fecfin = fecfin;
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

  public double getPactual() {
    return pactual;
  }

  public void setPactual(double pactual) {
    this.pactual = pactual;
  }

  public double getPrecio() {
    return precio;
  }

  public void setPrecio(double precio) {
    this.precio = precio;
  }

  public double getOferta() {
    return oferta;
  }

  public void setOferta(double oferta) {
    this.oferta = oferta;
  }

  public int getEstado() {
    return estado;
  }

  public void setEstado(int estado) {
    this.estado = estado;
  }

}
