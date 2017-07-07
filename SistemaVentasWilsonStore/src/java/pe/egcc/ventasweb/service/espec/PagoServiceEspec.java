/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.egcc.ventasweb.service.espec;

import java.util.List;
import pe.egcc.ventasweb.model.Pago;

/**
 *
 * @author Carlos
 */
public interface PagoServiceEspec {
    
    void grabar(Pago bean);
    
      List<Pago> getPagos();

    
}
