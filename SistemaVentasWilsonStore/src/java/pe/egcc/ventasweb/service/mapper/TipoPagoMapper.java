/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.egcc.ventasweb.service.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import pe.egcc.ventasweb.model.TipoPago;
import pe.egcc.ventasweb.service.espec.RowMapper;



/**
 *
 * @author Carlos
 */
public class TipoPagoMapper 
 implements RowMapper<TipoPago>{

  @Override
  public TipoPago mapRow(ResultSet rs) throws SQLException {
    TipoPago bean = new TipoPago();
    bean.setIdtipo(rs.getInt("idtipo"));
    bean.setNombre(rs.getString("nombre"));
    return bean;
       
    }
    
}
