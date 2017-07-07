/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.egcc.ventasweb.service.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import pe.egcc.ventasweb.model.Pago;

/**
 *
 * @author firefox_usr
 */
public class PagoMapper {
     public Pago mapRow(ResultSet rs) throws SQLException {
    Pago bean = new Pago();
    bean.setIdpago(rs.getInt("idpago"));
    bean.setIdventa(rs.getInt("idventa"));
    bean.setTipopago(rs.getString("tipopago"));
    bean.setDetalle(rs.getString("detalle"));
    bean.setImporte(rs.getDouble("importe"));
    bean.setObs(rs.getString("obs"));
    return bean;
}
}
