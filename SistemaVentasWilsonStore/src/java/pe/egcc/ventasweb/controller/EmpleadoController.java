package pe.egcc.ventasweb.controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import pe.egcc.ventasweb.model.Empleado;
import pe.egcc.ventasweb.service.espec.EmpleadoServiceEspec;
import pe.egcc.ventasweb.service.impl.EmpleadoServiceImpl;

@WebServlet(name = "EmpleadoController",
        urlPatterns = {"/EmpleadoConsultar",
            "/EmpleadoEditar", "/EmpleadoEliminar",
            "/EmpleadoGrabar", "/EditarPassEmp"})
public class EmpleadoController extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        switch (request.getServletPath()) {
            case "/EmpleadoConsultar":
                if (request.getParameter("btnConsultar") != null) {
                    consultar(request, response);
                } else if (request.getParameter("btnNuevo") != null) {
                    nuevo(request, response);
                }
                break;
            case "/EmpleadoEditar":
                editar(request, response);
                break;
            case "/EmpleadoEliminar":
                eliminar(request, response);
                break;
            case "/EmpleadoGrabar":
                grabar(request, response);
                break;
            case "/EditarPassEmp":
                editarPass(request, response);
        }
    } // Fin de service

    private void consultar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Datos
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        // Proceso
        Empleado bean = new Empleado();
        bean.setNombre(nombre);
        bean.setApellido(apellido);
        EmpleadoServiceEspec service = new EmpleadoServiceImpl();
        List<Empleado> lista = service.leer(bean);
        // Forward
        request.setAttribute("lista", lista);
        UtilController.forward(request, response, "mantEmpleados.jsp");
    }

    private void nuevo(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        // Variables
        Empleado bean = new Empleado();
        // Datos para la página
        request.setAttribute("accion", UtilController.CRUD_NUEVO);
        request.setAttribute("bean", bean);
        UtilController.forward(request, response, "editarEmpleado.jsp");
    } // Fin de nuevo

    private void editar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Parámetro
        int id = Integer.parseInt(request.getParameter("id"));
        // Recuperar datos del empleado
        EmpleadoServiceEspec service;
        service = new EmpleadoServiceImpl();
        Empleado bean = service.leer(id);
        // Datos de la página
        request.setAttribute("bean", bean);
        request.setAttribute("accion", UtilController.CRUD_EDITAR);
        UtilController.forward(request, response, "editarEmpleado.jsp");
    }

    private void eliminar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Parámetro
        int id = Integer.parseInt(request.getParameter("id"));
        // Recuperar datos del empleado
        EmpleadoServiceEspec service;
        service = new EmpleadoServiceImpl();
        Empleado bean = service.leer(id);
        // Datos de la página
        request.setAttribute("bean", bean);
        request.setAttribute("accion", UtilController.CRUD_ELIMINAR);
        UtilController.forward(request, response, "editarEmpleado.jsp");
    }

    private void grabar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Empleado bean = new Empleado();
        String destino;
        String accion = "";
        try {
            // Datos
            accion = request.getParameter("accion");
            bean.setIdemp(Integer.parseInt(request.getParameter("idemp")));
            bean.setApellido(request.getParameter("apellido"));
            bean.setNombre(request.getParameter("nombre"));
            bean.setTelefono(request.getParameter("telefono"));
            bean.setEmail(request.getParameter("email"));
            bean.setDni(request.getParameter("dni"));
            bean.setDireccion(request.getParameter("direccion"));

            // Proceso
            EmpleadoServiceEspec service;
            service = new EmpleadoServiceImpl();
            String mensaje = "";
            switch (accion) {
                case UtilController.CRUD_NUEVO:
                    service.crear(bean);
                    mensaje = "Empleado se ha registrado correctamente (id=" + bean.getIdemp() + ").";
                    break;
                case UtilController.CRUD_EDITAR:
                    service.modificar(bean);
                    mensaje = "Empleado se ha actualizado correctamente.";
                    break;
                case UtilController.CRUD_ELIMINAR:
                    service.eliminar(bean.getIdemp());
                    mensaje = "Empleado se ha eliminado correctamente.";
                    break;
            }
            request.setAttribute("mensaje", mensaje);
            destino = "mantEmpleados.jsp";
        } catch (Exception e) {
            request.setAttribute("error", e.getMessage());
            request.setAttribute("bean", bean);
            request.setAttribute("accion", accion);
            destino = "editarEmpleado.jsp";
        }
        // Forward
        UtilController.forward(request, response, destino);
    } // Fin de grabar

    private void editarPass(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String destino = null;
        String mensaje="";
        try {
            // Datos
            String claveact = request.getParameter("claveActual");
            String clavenew = request.getParameter("claveNueva");
            String claveconf = request.getParameter("claveConfirma");
            if (claveconf.equals(clavenew)) {
                // Proceso
                EmpleadoServiceEspec service;
                service = new EmpleadoServiceImpl();
                service.cambiarPass(claveact, clavenew);
                destino = "cambiarPass.jsp";
                mensaje = "Contraseña cambiada correctamente";
                
            } else {
                destino = "cambiarPass.jsp";
                mensaje="Las contraseñas no coinciden";
            }
            request.setAttribute("mensaje", mensaje);

        } catch (Exception e) {
            request.setAttribute("mensaje", mensaje);
            request.setAttribute("error", e.getMessage());
            destino = "cambiarPass.jsp";

        }
    // Forward

    UtilController.forward (request, response, destino);
    }



} // Fin de EmpleadoController
