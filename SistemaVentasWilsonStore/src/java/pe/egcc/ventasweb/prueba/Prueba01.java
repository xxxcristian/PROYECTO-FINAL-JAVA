package pe.egcc.ventasweb.prueba;

import java.sql.Connection;
import pe.egcc.ventasweb.db.AccesoDB;


public class Prueba01 {
  
  public static void main(String[] args) {
    try {
      Connection cn = AccesoDB.getConnection();
      System.out.println("Conexión ok.");
      Thread.currentThread().sleep(10000);
      cn.close();
    } catch (Exception e) {
      System.err.println(e.getMessage());
    }
  }
  
}
