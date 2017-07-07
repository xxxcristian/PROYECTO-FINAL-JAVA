/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.egcc.ventasweb.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import pe.egcc.ventasweb.db.AccesoDB;
import pe.egcc.ventasweb.model.TipoPago;
import pe.egcc.ventasweb.service.espec.TipoPagoEspec;
import pe.egcc.ventasweb.service.mapper.TipoPagoMapper;

/**
 *
 * @author Carlos
 */
public class TipoPagoServiceImpl 
implements TipoPagoEspec{

    @Override
    public List<TipoPago> leerTodos() {
          List<TipoPago> lista = new ArrayList<>();
    Connection cn = null;
    try {
      cn = AccesoDB.getConnection();
      String sql = "select idtipo, nombre from tipo_pago";
      PreparedStatement pstm;
      pstm = cn.prepareStatement(sql);
      ResultSet rs = pstm.executeQuery();
      TipoPagoMapper mapper = new TipoPagoMapper();
      while(rs.next()){
        TipoPago bean = mapper.mapRow(rs);
        lista.add(bean);
      }
      rs.close();
      pstm.close();
    } catch (Exception e) {
      String texto = "Error en el proceso. ";
      texto += e.getMessage();
      throw new RuntimeException(texto);
    } finally {
      try {
        cn.close();
      } catch (Exception e) {
      }
    }
    return lista;
    }
    
}
