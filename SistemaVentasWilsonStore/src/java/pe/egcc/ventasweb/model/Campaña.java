/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.egcc.ventasweb.model;

import java.util.Date;

/**
 *
 * @author Carlos
 */
public class Campaña {

    private int idcamp;
    private String nombre;
    private String descripcion;
    private int idcat;
    private Date fecInicio;
    private Date fecFin;
    private double porcDcto;
    private int estado;
    
    
    public Campaña(){
        
    }

    public int getIdcamp() {
        return idcamp;
    }

    public void setIdcamp(int idcamp) {
        this.idcamp = idcamp;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getIdcat() {
        return idcat;
    }

    public void setIdcat(int idcat) {
        this.idcat = idcat;
    }

    public Date getFecInicio() {
        return fecInicio;
    }

    public void setFecInicio(Date fecInicio) {
        this.fecInicio = fecInicio;
    }

    public Date getFecFin() {
        return fecFin;
    }

    public void setFecFin(Date fecFin) {
        this.fecFin = fecFin;
    }

    public double getPorcDcto() {
        return porcDcto;
    }

    public void setPorcDcto(double porcDcto) {
        this.porcDcto = porcDcto;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

        
    
}
