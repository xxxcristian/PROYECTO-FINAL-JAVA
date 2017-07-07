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
import pe.egcc.ventasweb.model.Pago;
import pe.egcc.ventasweb.service.espec.PagoServiceEspec;
import pe.egcc.ventasweb.service.mapper.PagoMapper;

/**
 *
 * @author Carlos
 */
public class PagoServiceImpl
        implements PagoServiceEspec {

    @Override
    public void grabar(Pago bean) {

        Connection cn = null;
        try {
            // La conexión
            cn = AccesoDB.getConnection();
            // Iniciamos la Tx
            cn.setAutoCommit(false);
            // Grabra Venta
            String sql = "insert into pago(idventa,"
                    + "idtipo,detalle,importe,obs) "
                    + "values(?,?,?,(select total from venta where idventa=?),?)";
            PreparedStatement pstm = cn.prepareStatement(sql);
            pstm.setInt(1, bean.getIdventa());
            pstm.setInt(2, bean.getIdtipo());
            pstm.setString(3, bean.getDetalle());
            pstm.setDouble(4, bean.getIdventa());
            pstm.setString(5, bean.getObs());
            pstm.executeUpdate();
            pstm.close();
            // Recuperar id
            sql = "select LAST_INSERT_ID() id";
            pstm = cn.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            rs.next();
            int id = rs.getInt("id");
            bean.setIdpago(id);
            rs.close();
            pstm.close();
            cn.commit();
        } catch (Exception e) {
            try {
                cn.rollback();
            } catch (Exception e1) {
            }
            throw new RuntimeException("Error en el proceso. " + e.getMessage());
        } finally {
            try {
                cn.close();
            } catch (Exception e) {
            }
        }
    }

    @Override
    public List<Pago> getPagos() {
      List<Pago> lista = new ArrayList<>();
        Connection cn = null;
        try {
            cn = AccesoDB.getConnection();
            String sql =  "select idpago, idventa, tipopago, detalle, importe, obs "
                        + "from v_pago";
            PreparedStatement pstm;
            pstm = cn.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            PagoMapper mapper = new PagoMapper();
            while (rs.next()) {
                Pago o = mapper.mapRow(rs);
                lista.add(o);
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
