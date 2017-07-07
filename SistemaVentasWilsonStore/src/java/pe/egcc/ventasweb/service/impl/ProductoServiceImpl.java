package pe.egcc.ventasweb.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import pe.egcc.ventasweb.db.AccesoDB;
import pe.egcc.ventasweb.model.Producto;
import pe.egcc.ventasweb.service.espec.ProductoServiceEspec;
import pe.egcc.ventasweb.service.mapper.ProductoMapper;


public class ProductoServiceImpl 
  implements ProductoServiceEspec{
    
  private final String SQL_SELECT = "select "
          + "idprod, idcat, nombre, descripcion, precio, stock "
          + "from producto ";
  private final String SQL_INSERT = "insert into producto "
          + "(idcat, nombre, descripcion, precio, stock) "
          + "values(?, ?, ?, ?, ? ) ";
  private final String SQL_UPDATE = "UPDATE producto "
          + "SET idcat=?, nombre=?, descripcion=?, precio=? , stock=? "
          + "WHERE idprod=?";
  private final String SQL_DELETE = "DELETE FROM producto "
          + "WHERE idprod=?";
    private final String SQL_DELETE1 = "DELETE FROM producto "
          + "WHERE idprod=?, idcat=?, nombre=?, descripcion=?, precio=?, stock=? ";

  @Override
  public void crear(Producto bean) {
        Connection cn = null;
    try {
      cn = AccesoDB.getConnection();
      cn.setAutoCommit(false);
      // Insertar Registro
      PreparedStatement pstm;
      pstm = cn.prepareStatement(SQL_INSERT);
      pstm.setInt(1, bean.getIdcat());
      pstm.setString(2, bean.getNombre());
      pstm.setString(3, bean.getDescripcion());
      pstm.setDouble(4, bean.getPrecio());
      pstm.setInt(5, bean.getStock());
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
  public void modificar(Producto bean) {
        Connection cn = null;
    try {
      cn = AccesoDB.getConnection();
      cn.setAutoCommit(false);
      PreparedStatement pstm;
      pstm = cn.prepareStatement(SQL_UPDATE);
      pstm.setInt(1, bean.getIdcat());
      pstm.setString(2, bean.getNombre());
      pstm.setString(3, bean.getDescripcion());
      pstm.setDouble(4, bean.getPrecio());
      pstm.setInt(5, bean.getStock());
      pstm.setInt(6, bean.getIdprod());
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
      // Verificar si esta alineado a una promocion
      String sql = "select count(*) cont "
              + "from promocion where idprod=?";
      PreparedStatement pstm = cn.prepareStatement(sql);
      pstm.setInt(1, id);
      ResultSet rs = pstm.executeQuery();
      rs.next();
      int cont = rs.getInt("cont");
      rs.close();
      pstm.close();
      if(cont > 0){
        throw new RuntimeException("No se puede producto, asociado a una promocion.");
      }
      // Eliminar producto
      pstm = cn.prepareStatement(SQL_DELETE);
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
    }
  }

  @Override
  public Producto leer(int id) {
 Producto bean = null;
    Connection cn = null;
    try {
      cn = AccesoDB.getConnection();
      String sql = SQL_SELECT + "where idprod = ? ";
      PreparedStatement pstm;
      pstm = cn.prepareStatement(sql);
      pstm.setInt(1, id);
      ResultSet rs = pstm.executeQuery();
      if(rs.next()){
        ProductoMapper mapper = new ProductoMapper();
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
  public List<Producto> leer() {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public List<Producto> leer(Producto bean) {
 List<Producto> lista = new ArrayList<>();
    Connection cn = null;
    try {
      cn = AccesoDB.getConnection();
      String sql = SQL_SELECT + "where nombre like concat(?,'%') ";
      PreparedStatement pstm;
      pstm = cn.prepareStatement(sql);
      pstm.setString(1, bean.getNombre());
      
      ResultSet rs = pstm.executeQuery();
      ProductoMapper mapper = new ProductoMapper();
      while (rs.next()) {
        Producto o = mapper.mapRow(rs);
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
    public void eliminarnum(Producto bean) { //Prueba-solucion? error null
Connection cn = null;
    try {
      // Obtener objeto Connection
      cn = AccesoDB.getConnection();
      // Inicio de Tx
      cn.setAutoCommit(false);
      // Verificar si esta alineado a una promocion
      String sql = "select count(*) cont "
              + "from promocion where idprod=?";
      PreparedStatement pstm = cn.prepareStatement(sql);
      pstm.setInt(1, bean.getIdprod());
      ResultSet rs = pstm.executeQuery();
      rs.next();
      int cont = rs.getInt("cont");
      rs.close();
      pstm.close();
      if(cont > 0){
        throw new RuntimeException("No se puede producto, asociado a una promocion.");
      }
      // Eliminar producto
      pstm = cn.prepareStatement(SQL_DELETE1);
      pstm.setInt(1, bean.getIdprod());
      pstm.setInt(2, bean.getIdcat());
      pstm.setString(3, bean.getNombre());
      pstm.setString(4, bean.getDescripcion());
      pstm.setDouble(5, bean.getPrecio());
      pstm.setInt(6, bean.getStock());
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
    }
  
}

    @Override
    public List<Producto> leerTodos() {
        List<Producto> lista = new ArrayList<>();
    Connection cn = null;
    try {
      cn = AccesoDB.getConnection();
      String sql = SQL_SELECT;
      PreparedStatement pstm;
      pstm = cn.prepareStatement(sql);
      ResultSet rs = pstm.executeQuery();
      ProductoMapper mapper = new ProductoMapper();
      while(rs.next()){
        Producto bean = mapper.mapRow(rs);
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