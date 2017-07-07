package pe.egcc.ventasweb.prueba;

import pe.egcc.ventasweb.service.espec.EmpleadoServiceEspec;
import pe.egcc.ventasweb.service.impl.EmpleadoServiceImpl;


public class Prueba05 {
  
  public static void main(String[] args) {
    EmpleadoServiceEspec service;
    service = new EmpleadoServiceImpl();
    for(int i = 1; i <= 4; i++){
      System.out.println("Rol: " + i 
              + "  ==>  Permiso: " 
              + service.getEstado(1003, i));
    }
  }
}
