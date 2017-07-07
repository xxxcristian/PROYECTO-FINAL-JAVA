/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.egcc.ventasweb.service.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import pe.egcc.ventasweb.model.Venta;
import pe.egcc.ventasweb.service.espec.RowMapper;

/**
 *
 * @author firefox_usr
 */
public class VentaMapper implements RowMapper<Venta>{
      @Override
  public Venta mapRow(ResultSet rs) throws SQLException {
    Venta bean = new Venta();
    bean.setIdventa(rs.getInt("idventa"));
    bean.setIdemp(rs.getInt("idemp"));
    bean.setCliente(rs.getString("cliente"));
    bean.setFecha(rs.getDate("fecha"));
    bean.setImporte(rs.getDouble("importe"));
    bean.setImpuesto(rs.getDouble("impuesto"));
    bean.setTotal(rs.getDouble("total"));
    return bean;
}
}
