package pe.egcc.ventasweb.service.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import pe.egcc.ventasweb.model.Categoria;
import pe.egcc.ventasweb.service.espec.RowMapper;


public class CategoriaMapper 
  implements RowMapper<Categoria>{

  @Override
  public Categoria mapRow(ResultSet rs) throws SQLException {
      
    Categoria bean = new Categoria();
    bean.setIdcat(rs.getInt("idcat"));
    bean.setNombre(rs.getString("nombre"));
    bean.setDescri(rs.getString("descripcion"));
   return bean;
  }
  
  
}
