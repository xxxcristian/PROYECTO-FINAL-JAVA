package pe.egcc.ventasweb.controller;


import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



public final class UtilController {

  private UtilController() {
  }
  
  // Contantes del CRUD
  public static final String CRUD_NUEVO = "NUEVO";
  public static final String CRUD_EDITAR = "EDITAR";
  public static final String CRUD_ELIMINAR = "ELIMINAR";

  public static void forward(
          HttpServletRequest request,
          HttpServletResponse response,
          String destino)
          throws ServletException, IOException {
    RequestDispatcher rd;
    rd = request.getRequestDispatcher(destino);
    rd.forward(request, response);
  }
  
  /**
   * Registra un dato en session.
   * 
   * @param request
   * @param key
   * @param value 
   */
  public static void setValue(
          HttpServletRequest request,
          String key, Object value) {
    request.getSession(true).setAttribute(key, value);
  }

  /**
   * Lee un dato de la session.
   * 
   * @param request
   * @param key
   * @return 
   */
  public static Object getValue(
  HttpServletRequest request,
          String key){
    return request.getSession(true).getAttribute(key);
  }
  
}
