package pe.egcc.ventasweb.prueba;

import pe.egcc.ventasweb.model.Empleado;
import pe.egcc.ventasweb.model.Promocion;
import pe.egcc.ventasweb.service.espec.EmpleadoServiceEspec;
import pe.egcc.ventasweb.service.espec.PromocionServiceEspec;
import pe.egcc.ventasweb.service.impl.EmpleadoServiceImpl;
import pe.egcc.ventasweb.service.impl.PromocionServiceImpl;


public class Prueba02 {
  
  public static void main(String[] args) {
    try {
      // Dato
      int id = 1;
      // Proceso
      PromocionServiceEspec service;
      service = new PromocionServiceImpl();
      Promocion bean = service.leer(id);
      System.out.println(bean.getFecinicio()
              + " | " + bean.getFecfin());
    } catch (Exception e) {
      System.err.println(e.getMessage());
    }
    
  }
}
