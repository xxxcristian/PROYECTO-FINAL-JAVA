package pe.egcc.ventasweb.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import pe.egcc.ventasweb.db.AccesoDB;
import pe.egcc.ventasweb.model.Detalle;
import pe.egcc.ventasweb.model.Venta;
import pe.egcc.ventasweb.service.espec.VentaServiceEspec;
import pe.egcc.ventasweb.service.mapper.VentaMapper;

/**
 *
 * @author Eric Gustavo Coronel Castillo
 * @blog www.desarrollasoftware.com
 * @email egcc.usil@gmail.com
 */
public class VentaServiceImpl implements VentaServiceEspec{

  @Override
  public void grabar(Venta venta) {
    int idventa = 0;
    Connection cn = null;
    try {
      // La conexión
      cn = AccesoDB.getConnection();
      // Iniciamos la Tx
      cn.setAutoCommit(false);
      // Grabra Venta
      String sql = "insert into venta(idemp,cliente,"
              + "fecha,importe,impuesto,total) "
              + "values(?,?,?,?,?,?)";
      PreparedStatement pstm = cn.prepareStatement(sql);
      pstm.setInt(1, venta.getIdemp());
      pstm.setString(2, venta.getCliente());
      pstm.setDate(3, 
           JdbcUtil.utilDateToSqlDate(venta.getFecha()));
      pstm.setDouble(4, venta.getImporte());
      pstm.setDouble(5, venta.getImpuesto());
      pstm.setDouble(6, venta.getTotal());
      pstm.executeUpdate();
      pstm.close();
      // Leer el idventa
      sql = "select last_insert_id() id;";
      pstm = cn.prepareStatement(sql);
      ResultSet rs = pstm.executeQuery();
      rs.next();
      idventa = rs.getInt("id");
      rs.close();
      pstm.close();
      // Grabar Detalles
      sql = "insert into detalle(idventa,idprod,cant,"
              + "precatalogo,preventa,subtotal) "
              + "values(?,?,?,?,?,?)";
      pstm = cn.prepareStatement(sql);
      for (Detalle i: venta.getItems()) {
        pstm.setInt(1, idventa);
        pstm.setInt(2, i.getIdprod());
        pstm.setInt(3, i.getCant());
        pstm.setDouble(4, i.getPrecatalogo());
        pstm.setDouble(5, i.getPreventa());
        pstm.setDouble(6, i.getSubtotal());
        pstm.executeUpdate();
      }
      pstm.close();
      // Grabar Pagos

      
      // Cancelar Tx
      cn.commit();
      venta.setIdventa(idventa);
    } catch (Exception e) {
      try {
        cn.rollback();
      } catch (Exception e1) {
      }
      throw new RuntimeException(e.getMessage());
    } finally{
      try {
        cn.close();
      } catch (Exception e) {
      }
    }
  }

    @Override
    public List<Venta> getVentas() {
         List<Venta> lista = new ArrayList<>();
        Connection cn = null;
        try {
            cn = AccesoDB.getConnection();
            String sql =  "select idventa, idemp, cliente, fecha, importe, impuesto, total from venta";
            PreparedStatement pstm;
            pstm = cn.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            VentaMapper mapper = new VentaMapper();
            while (rs.next()) {
                Venta o = mapper.mapRow(rs);
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
