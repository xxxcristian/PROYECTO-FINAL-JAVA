package pe.egcc.ventasweb.prueba;

import java.util.List;
import pe.egcc.ventasweb.model.Empleado;
import pe.egcc.ventasweb.model.Producto;
import pe.egcc.ventasweb.model.Promocion;
import pe.egcc.ventasweb.service.espec.EmpleadoServiceEspec;
import pe.egcc.ventasweb.service.espec.ProductoServiceEspec;
import pe.egcc.ventasweb.service.espec.PromocionServiceEspec;
import pe.egcc.ventasweb.service.impl.EmpleadoServiceImpl;
import pe.egcc.ventasweb.service.impl.ProductoServiceImpl;
import pe.egcc.ventasweb.service.impl.PromocionServiceImpl;


public class Prueba04 {
 
  public static void main(String[] args) {
    try {
      // Datos
        Producto bean = new Producto();

      bean.setNombre("");
       // Proceso
      ProductoServiceEspec service;
      service = new ProductoServiceImpl();
      List<Producto> lista = service.leer(bean);
      // Reporte
      for (Producto o : lista) {
        System.out.println(o.getIdprod()+ " | " 
                + o.getNombre()+ " | " + o.getIdcat());
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
