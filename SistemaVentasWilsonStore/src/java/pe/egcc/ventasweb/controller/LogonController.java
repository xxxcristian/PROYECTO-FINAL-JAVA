package pe.egcc.ventasweb.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import pe.egcc.ventasweb.model.Empleado;
import pe.egcc.ventasweb.service.espec.EmpleadoServiceEspec;
import pe.egcc.ventasweb.service.impl.EmpleadoServiceImpl;


@WebServlet(name = "LogonController", 
        urlPatterns = {"/LogonIngresar","/LogonSalir"})
public class LogonController extends HttpServlet {

  @Override
  protected void service(HttpServletRequest request, 
          HttpServletResponse response) 
          throws ServletException, IOException {
    switch(request.getServletPath()){
      case "/LogonIngresar":
        logonIngresar(request,response);
        break;
      case "/LogonSalir":
        LogonSalir(request,response);
        break;
        
    }
  } // Fin de service

  private void logonIngresar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String destino;
    try {
      // Datos
      String usuario = request.getParameter("usuario");
      String clave = request.getParameter("clave");
      // Proceso
      EmpleadoServiceEspec service;
      service = new EmpleadoServiceImpl();
      Empleado bean = service.validar(usuario, clave);
      UtilController.setValue(request, "usuario", bean);
      
      // Estado Roles
      int estAdm = service.getEstado(bean.getIdemp(), 1);
      int estVen = service.getEstado(bean.getIdemp(), 2);
      int estOpe = service.getEstado(bean.getIdemp(), 3);
      int estUsu = service.getEstado(bean.getIdemp(), 4);
      
      // Permisos
      UtilController.setValue(request, "rolVentas", ((estAdm == 1 || estVen == 1)?1:0));
      UtilController.setValue(request, "rolMant", ((estAdm == 1 || estOpe == 1)?1:0));
      UtilController.setValue(request, "rolConsultas", 1);
      UtilController.setValue(request, "rolReportes", ((estAdm == 1 || estOpe == 1  || estUsu == 1)?1:0));
      UtilController.setValue(request, "rolSeguridad", (estAdm == 1)?1:0);
      
      destino = "main.jsp";
    } catch (Exception e) {
      e.printStackTrace();
      request.setAttribute("error", e.getMessage());
      destino = "index.jsp";
    }
    // Forward
    UtilController.forward(request, response, destino);
  } // Fin de LogonIngresar

  private void LogonSalir(HttpServletRequest request, 
          HttpServletResponse response) 
          throws ServletException, IOException {
    request.getSession().invalidate();
    UtilController.forward(request, response, "index.jsp");
  } // Fin de LogonSalir

  
  
  
  
} // Fin de LogonController
