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
import pe.egcc.ventasweb.model.Campaña;
import pe.egcc.ventasweb.service.espec.CampañaServiceEspec;
import pe.egcc.ventasweb.service.mapper.CampañaMapper;

/**
 *
 * @author firefox_usr
 */
public class CampañaServiceImpl
implements CampañaServiceEspec{
    
     
    private final String SQL_SELECT = "select "
          + "idcamp, nombre, descripcion, idcat, fecInicio, fecFin, porcDcto, estado "
          + "from campania ";
  private final String SQL_INSERT = "insert into campania "
          + "(nombre, descripcion, idcat, fecInicio, fecFin, porcDcto, estado) "
          + "values(?, ?, ?, ?, ?, ?, ?) ";
  private final String SQL_UPDATE = "UPDATE campania "
          + "SET nombre=?, descripcion=?, idcat=?, fecInicio=?, fecFin=?, porcDcto=?, estado=? "
          + "WHERE idcamp=?";
  private final String SQL_DELETE = "DELETE FROM campania "
          + "WHERE idcamp=?";


   @Override
    public void crear(Campaña bean) {
                Connection cn = null;
    try {
      cn = AccesoDB.getConnection();
      cn.setAutoCommit(false);
      // Insertar Registro
      PreparedStatement pstm;
      pstm = cn.prepareStatement(SQL_INSERT);
      pstm.setString(1, bean.getNombre());
      pstm.setString(2, bean.getDescripcion());
      pstm.setInt(3, bean.getIdcat());
      pstm.setDate(4, JdbcUtil.utilDateToSqlDate(bean.getFecInicio()));
      pstm.setDate(5, JdbcUtil.utilDateToSqlDate(bean.getFecFin()));
      pstm.setDouble(6, bean.getPorcDcto());
      pstm.setInt(7, bean.getEstado());
      pstm.executeUpdate();
      pstm.close();
      // Recuperar id
      String sql = "select LAST_INSERT_ID() id";
      pstm = cn.prepareStatement(sql);
      ResultSet rs = pstm.executeQuery();
      rs.next();
      int id = rs.getInt("id");
      bean.setIdcamp(id);
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
    public void modificar(Campaña bean) {
              Connection cn = null;
    try {
      cn = AccesoDB.getConnection();
      cn.setAutoCommit(false);
      PreparedStatement pstm;
      pstm = cn.prepareStatement(SQL_UPDATE);
      pstm.setString(1, bean.getNombre());
      pstm.setString(2, bean.getDescripcion());
      pstm.setInt(3, bean.getIdcat());
      pstm.setDate(4, JdbcUtil.utilDateToSqlDate(bean.getFecInicio()));
      pstm.setDate(5, JdbcUtil.utilDateToSqlDate(bean.getFecFin()));
      pstm.setDouble(6, bean.getPorcDcto());
      pstm.setInt(7, bean.getEstado());
      pstm.setInt(8, bean.getIdcamp());
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
    public Campaña leer(int id) {
       Campaña bean = null;
    Connection cn = null;
    try {
      cn = AccesoDB.getConnection();
      String sql = SQL_SELECT + "where idprom = ? ";
      PreparedStatement pstm;
      pstm = cn.prepareStatement(sql);
      pstm.setInt(1, id);
      ResultSet rs = pstm.executeQuery();
      if(rs.next()){
        CampañaMapper mapper = new CampañaMapper();
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
    public List<Campaña> leer() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Campaña> leer(Campaña bean) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Campaña> leerTodos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Campaña> getCampañas() {
              List<Campaña> lista = new ArrayList<>();
        Connection cn = null;
        try {
            cn = AccesoDB.getConnection();
            String sql =  SQL_SELECT;
            PreparedStatement pstm;
            pstm = cn.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            CampañaMapper mapper = new CampañaMapper();
            while (rs.next()) {
                Campaña o = mapper.mapRow(rs);
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
