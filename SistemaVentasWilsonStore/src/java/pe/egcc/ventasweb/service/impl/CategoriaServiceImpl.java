package pe.egcc.ventasweb.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import pe.egcc.ventasweb.db.AccesoDB;
import pe.egcc.ventasweb.model.Categoria;
import pe.egcc.ventasweb.service.espec.CategoriaServiceEspec;
import pe.egcc.ventasweb.service.mapper.CategoriaMapper;

public class CategoriaServiceImpl
        implements CategoriaServiceEspec {

    private final String SQL_SELECT = "select "
            + "idcat, nombre, descripcion "
            + "from categoria ";
    private final String SQL_INSERT = "insert into categoria "
            + "(nombre, descripcion) "
            + "values( ?, ?) ";
    private final String SQL_UPDATE = "UPDATE categoria "
            + "SET nombre=?, descripcion=?"
            + "WHERE idcat=?";
    private final String SQL_DELETE = "DELETE FROM categoria "
            + "WHERE idcat=?";

    @Override
    public void crear(Categoria bean) {
        Connection cn = null;
        try {
            cn = AccesoDB.getConnection();
            cn.setAutoCommit(false);
            // Insertar Registro
            PreparedStatement pstm;
            pstm = cn.prepareStatement(SQL_INSERT);
            pstm.setString(1, bean.getNombre());
            pstm.setString(2, bean.getDescri());
            pstm.executeUpdate();
            pstm.close();
            // Recuperar id
            String sql = "select LAST_INSERT_ID() id";
            pstm = cn.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            rs.next();
            int id = rs.getInt("id");
            bean.setIdcat(id);
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
    public void modificar(Categoria bean) {
        Connection cn = null;
        try {
            cn = AccesoDB.getConnection();
            cn.setAutoCommit(false);

            // Verificar si esta alineado a un producto
            String sql = "select count(*) cont "
                    + "from producto where idcat=?";
            PreparedStatement pstm = cn.prepareStatement(sql);
            pstm.setInt(1, bean.getIdcat());
            ResultSet rs = pstm.executeQuery();
            rs.next();
            int cont = rs.getInt("cont");
            rs.close();
            pstm.close();
            if (cont > 0) {
                throw new RuntimeException("No se puede modificar categoria, asociado a un producto.");
            }
            //Modificar categoria
            pstm = cn.prepareStatement(SQL_UPDATE);

            pstm.setString(1, bean.getNombre());
            pstm.setString(2, bean.getDescri());
            pstm.setInt(3, bean.getIdcat());
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
            // Verificar si esta alineado a un producto
            String sql = "select count(*) cont "
                    + "from producto where idcat=?";
            PreparedStatement pstm = cn.prepareStatement(sql);
            pstm.setInt(1, id);
            ResultSet rs = pstm.executeQuery();
            rs.next();
            int cont = rs.getInt("cont");
            rs.close();
            pstm.close();
            if (cont > 0) {
                throw new RuntimeException("No se puede eliminar categoria, asociado a un producto.");
            }
            // Eliminar categoria
            pstm = cn.prepareStatement(SQL_DELETE);
            pstm.setInt(1, id);
            int filas = pstm.executeUpdate();
            pstm.close();
            if (filas == 0) {
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
    public Categoria leer(int id) {
    Categoria bean = null;
    Connection cn = null;
    try {
      cn = AccesoDB.getConnection();
      String sql = SQL_SELECT + "where idcat = ? ";
      PreparedStatement pstm;
      pstm = cn.prepareStatement(sql);
      pstm.setInt(1, id);
      ResultSet rs = pstm.executeQuery();
      if(rs.next()){
        CategoriaMapper mapper = new CategoriaMapper();
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
    public List<Categoria> leer() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Categoria> leer(Categoria bean) {
    List<Categoria> lista = new ArrayList<>();
    Connection cn = null;
    try {
      cn = AccesoDB.getConnection();
      String sql = SQL_SELECT + "where nombre like concat(?,'%') ";
      PreparedStatement pstm;
      pstm = cn.prepareStatement(sql);
      pstm.setString(1, bean.getNombre());
      
      ResultSet rs = pstm.executeQuery();
      CategoriaMapper mapper = new CategoriaMapper();
      while (rs.next()) {
        Categoria o = mapper.mapRow(rs);
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
    public List<Categoria> leerTodos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
