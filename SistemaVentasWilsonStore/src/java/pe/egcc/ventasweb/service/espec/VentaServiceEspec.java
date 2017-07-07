package pe.egcc.ventasweb.service.espec;

import java.util.List;
import pe.egcc.ventasweb.model.Venta;

/**
 *
 * @author firefox_usr
 */
public interface VentaServiceEspec {
  
  void grabar(Venta venta);
  
    List<Venta> getVentas();

}
