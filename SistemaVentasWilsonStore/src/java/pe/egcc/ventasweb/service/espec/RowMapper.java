package pe.egcc.ventasweb.service.espec;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Este tecnica se ha tomado de Spring Framework
 * 
 * @param <T> 
 */
public interface RowMapper<T> {
  
  
  T mapRow(ResultSet rs) throws SQLException;
  
}
