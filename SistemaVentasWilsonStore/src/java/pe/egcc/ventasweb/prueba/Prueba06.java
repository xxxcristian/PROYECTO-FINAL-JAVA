/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.egcc.ventasweb.prueba;


import pe.egcc.ventasweb.service.espec.EmpleadoServiceEspec;
import pe.egcc.ventasweb.service.impl.EmpleadoServiceImpl;

/**
 *
 * @author firefox_usr
 */
public class Prueba06 {
    public static void main(String[] args) {
    try {
      // Dato
      String claveact = "suerte";
      String clavenew = "malasuerte";
              
      // Proceso
      EmpleadoServiceEspec service;
      service = new EmpleadoServiceImpl();
      service.cambiarPass(claveact,clavenew);
        System.out.println("Clave cambiada correctamente");
    } catch (Exception e) {
      System.err.println(e.getMessage());
    }
  }
    
}
