package pe.egcc.ventasweb.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import pe.egcc.ventasweb.db.AccesoDB;
import pe.egcc.ventasweb.model.Empleado;
import pe.egcc.ventasweb.service.espec.EmpleadoServiceEspec;
import pe.egcc.ventasweb.service.mapper.EmpleadoMapper;


public class EmpleadoServiceImpl
        implements EmpleadoServiceEspec {

  private final String SQL_SELECT = "select "
          + "idemp, nombre, apellido, telefono, email, dni, direccion "
          + "from empleado ";
  private final String SQL_INSERT = "insert into empleado "
          + "(nombre, apellido, email, telefono, dni, direccion) "
          + "values(?, ?, ?, ?, ?, ? ) ";
  private final String SQL_UPDATE = "UPDATE empleado "
          + "SET nombre=?, apellido=?, email=?, telefono=? , dni=?, direccion=? "
          + "WHERE idemp=?";
  private final String SQL_DELETE = "DELETE FROM empleado "
          + "WHERE idemp=?";

  @Override
  public Empleado validar(String usuario, String clave) {
    Empleado bean = null;
    Connection cn = null;
    try {
      cn = AccesoDB.getConnection();
      // Obtener ID del empleado
      String sql = "select idemp from usuario "
              + "where usuario = ?  and "
              + "clave = SHA(?)  and estado = 1";
      PreparedStatement pstm;
      pstm = cn.prepareStatement(sql);
      pstm.setString(1, usuario);
      pstm.setString(2, clave);
      ResultSet rs = pstm.executeQuery();
      int idemp = -1;
      if (rs.next()) {
        idemp = rs.getInt("idemp");
      }
      rs.close();
      pstm.close();
      if (idemp == -1) {
        throw new Exception("Datos incorrectos.");
      }
      bean = leer(idemp);
      bean.setUsuario(usuario);
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
  public void crear(Empleado bean) {
    Connection cn = null;
    try {
      cn = AccesoDB.getConnection();
      cn.setAutoCommit(false);
      // Insertar Registro
      PreparedStatement pstm;
      pstm = cn.prepareStatement(SQL_INSERT);
      pstm.setString(1, bean.getNombre());
      pstm.setString(2, bean.getApellido());
      pstm.setString(3, bean.getEmail());
      pstm.setString(4, bean.getTelefono());
      pstm.setString(5, bean.getDni());
      pstm.setString(6, bean.getDireccion());
      pstm.executeUpdate();
      pstm.close();
      // Recuperar id
      String sql = "select LAST_INSERT_ID() id";
      pstm = cn.prepareStatement(sql);
      ResultSet rs = pstm.executeQuery();
      rs.next();
      int id = rs.getInt("id");
      bean.setIdemp(id);
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
  public void modificar(Empleado bean) {
    Connection cn = null;
    try {
      cn = AccesoDB.getConnection();
      cn.setAutoCommit(false);
      PreparedStatement pstm;
      pstm = cn.prepareStatement(SQL_UPDATE);
      pstm.setString(1, bean.getNombre());
      pstm.setString(2, bean.getApellido());
      pstm.setString(3, bean.getEmail());
      pstm.setString(4, bean.getTelefono());
      pstm.setString(5, bean.getDni());
      pstm.setString(6, bean.getDireccion());
      pstm.setInt(7, bean.getIdemp());
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
      // Verificar si existe usuario
      String sql = "select count(*) cont "
              + "from usuario where idemp=?";
      PreparedStatement pstm = cn.prepareStatement(sql);
      pstm.setInt(1, id);
      ResultSet rs = pstm.executeQuery();
      rs.next();
      int cont = rs.getInt("cont");
      rs.close();
      pstm.close();
      if(cont > 0){
        throw new RuntimeException("No se puede eliminar el empleado.");
      }
      // Eliminar empleado
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
      String texto = "Error en el proceso Eliminar Empleado. ";
      texto += e.getMessage();
      throw new RuntimeException(texto);
    } finally {
      try {
        cn.close();
      } catch (Exception e) {
      }
    }
  } // Fin de Eliminar

  @Override
  public Empleado leer(int id) {
    Empleado bean = null;
    Connection cn = null;
    try {
      cn = AccesoDB.getConnection();
      String sql = SQL_SELECT + "where idemp = ?";
      PreparedStatement pstm;
      pstm = cn.prepareStatement(sql);
      pstm.setInt(1, id);
      ResultSet rs = pstm.executeQuery();
      EmpleadoMapper mapper = new EmpleadoMapper();
      if (rs.next()) {
        bean = mapper.mapRow(rs);
      }
      rs.close();
      pstm.close();
      if (bean == null) {
        throw new Exception("Datos incorrectos.");
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
  public List<Empleado> leer() {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  /**
   * Consulta datos de los empleados en función a su nombre y apellido.
   *
   * @param bean
   * @return
   */
  @Override
  public List<Empleado> leer(Empleado bean) {
    List<Empleado> lista = new ArrayList<>();
    Connection cn = null;
    try {
      cn = AccesoDB.getConnection();
      String sql = SQL_SELECT
              + "where nombre like concat(?,'%') "
              + "and apellido like concat(?,'%') ";
      PreparedStatement pstm;
      pstm = cn.prepareStatement(sql);
      pstm.setString(1, bean.getNombre());
      pstm.setString(2, bean.getApellido());
      ResultSet rs = pstm.executeQuery();
      EmpleadoMapper mapper = new EmpleadoMapper();
      while (rs.next()) {
        Empleado o = mapper.mapRow(rs);
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
  public int getEstado(int idemp, int idrol) {
    int estado = 0;
    
    Connection cn = null;
    try {
      cn = AccesoDB.getConnection();
      String sql = "select estado from permiso where idemp= ? and idrol= ?";
      PreparedStatement pstm = cn.prepareStatement(sql);
      pstm.setInt(1, idemp);
      pstm.setInt(2, idrol);
      ResultSet rs = pstm.executeQuery();
      if(rs.next()){
        estado = rs.getInt("estado");
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
    return estado;
  }

    @Override
    public void cambiarPass(String claveact, String clavenew) {
         Connection cn = null;
    try {
      cn = AccesoDB.getConnection();
      cn.setAutoCommit(false);
      String sql = "update usuario set clave=SHA(?) where clave=SHA(?)";
      PreparedStatement pstm;
      pstm = cn.prepareStatement(sql);
      pstm.setString(1, clavenew);
      pstm.setString(2, claveact);
      int filas = pstm.executeUpdate();
      pstm.close();
      cn.commit();
   if (filas != 1) {
        throw new Exception("Error, clave incorrecta.");
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
    public List<Empleado> leerTodos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}


