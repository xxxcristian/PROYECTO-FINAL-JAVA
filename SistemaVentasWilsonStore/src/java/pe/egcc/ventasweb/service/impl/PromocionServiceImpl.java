/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.egcc.ventasweb.service.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import pe.egcc.ventasweb.db.AccesoDB;
import pe.egcc.ventasweb.model.Promocion;
import pe.egcc.ventasweb.service.espec.PromocionServiceEspec;
import pe.egcc.ventasweb.service.mapper.CategoriaMapper;
import pe.egcc.ventasweb.service.mapper.PromocionMapper;

/**
 *
 * @author Carlos
 */
public class PromocionServiceImpl
        implements PromocionServiceEspec {

    
    private final String SQL_SELECT = "select "
          + "idprom, fecInicio, fecFin, precio, oferta, idprod, estado "
          + "from promocion ";
  private final String SQL_INSERT = "insert into promocion "
          + "(fecInicio, fecFin, precio, oferta, idprod, estado) "
          + "values(?, ?, ?, ?, ?, ?) ";
  private final String SQL_UPDATE = "UPDATE promocion "
          + "SET fecInicio=?, fecFin=?, precio=?, oferta=?, idprod=?, estado=? "
          + "WHERE idprom=?";
  private final String SQL_DELETE = "DELETE FROM promocion "
          + "WHERE idprom=?";

    
    
    @Override
    public List<Promocion> getPromociones() {
        List<Promocion> lista = new ArrayList<>();
        Connection cn = null;
        try {
            cn = AccesoDB.getConnection();
            String sql =  "select idprom, fecinicio, fecfin, idprod, nombre, pactual, precio, oferta, estado "
                        + "from v_promocion";
            PreparedStatement pstm;
            pstm = cn.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            PromocionMapper mapper = new PromocionMapper();
            while (rs.next()) {
                Promocion o = mapper.mapRow(rs);
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

    @Override
    public void crear(Promocion bean) {
                Connection cn = null;
    try {
      cn = AccesoDB.getConnection();
      cn.setAutoCommit(false);
      // Insertar Registro
      PreparedStatement pstm;
      pstm = cn.prepareStatement(SQL_INSERT);
      pstm.setDate(1, JdbcUtil.utilDateToSqlDate(bean.getFecinicio()));
      pstm.setDate(2, JdbcUtil.utilDateToSqlDate(bean.getFecfin()));
      pstm.setDouble(3, bean.getPrecio());
      pstm.setDouble(4, bean.getOferta());
      pstm.setInt(5, bean.getIdprod());
      pstm.setInt(6, bean.getEstado());
      pstm.executeUpdate();
      pstm.close();
      // Recuperar id
      String sql = "select LAST_INSERT_ID() id";
      pstm = cn.prepareStatement(sql);
      ResultSet rs = pstm.executeQuery();
      rs.next();
      int id = rs.getInt("id");
      bean.setIdprod(id);
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
    public void modificar(Promocion bean) {
              Connection cn = null;
    try {
      cn = AccesoDB.getConnection();
      cn.setAutoCommit(false);
      PreparedStatement pstm;
      pstm = cn.prepareStatement(SQL_UPDATE);
      pstm.setDate(1, JdbcUtil.utilDateToSqlDate(bean.getFecinicio()));
      pstm.setDate(2, JdbcUtil.utilDateToSqlDate(bean.getFecfin()));
      pstm.setDouble(3, bean.getPrecio());
      pstm.setDouble(4, bean.getOferta());
      pstm.setInt(5, bean.getIdprod());
      pstm.setInt(6, bean.getEstado());
      pstm.setInt(7, bean.getIdprom());
      int filas = pstm.executeUpdate();
      pstm.close();
      if (filas != 1) {
        throw new Exception("Error, datos incorrectos.");
      }
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
    public void eliminar(int id) {
            Connection cn = null;
    try {
      // Obtener objeto Connection
      cn = AccesoDB.getConnection();
      // Inicio de Tx
      cn.setAutoCommit(false);
      // Eliminar producto
       PreparedStatement pstm = cn.prepareStatement(SQL_DELETE);
      pstm.setInt(1, id);
      int filas = pstm.executeUpdate();
      pstm.close();
      if(filas == 0){
        throw new RuntimeException("El código no existe.");
      }
      // Confirmar Tx
      cn.commit();
    } catch (Exception e) {
      try {
        cn.rollback();
      } catch (Exception e1) {
      }
      String texto = "Error en el proceso Eliminar Producto. ";
      texto += e.getMessage();
      throw new RuntimeException(texto);
    } finally {
      try {
        cn.close();
      } catch (Exception e) {
      }
    }}

    @Override
    public Promocion leer(int id) {
       Promocion bean = null;
    Connection cn = null;
    try {
      cn = AccesoDB.getConnection();
      String sql = SQL_SELECT + "where idprom = ? ";
      PreparedStatement pstm;
      pstm = cn.prepareStatement(sql);
      pstm.setInt(1, id);
      ResultSet rs = pstm.executeQuery();
      if(rs.next()){
        PromocionMapper mapper = new PromocionMapper();
        bean = mapper.mapRow(rs);
      }
      rs.close();
      pstm.close();
      if (bean == null) {
        throw new Exception("Id no existe.");
      }
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
    return bean; 
    }

    @Override
    public List<Promocion> leer() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Promocion> leer(Promocion bean) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Promocion> leerTodos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
