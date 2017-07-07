package pe.egcc.ventasweb.controller;

import java.io.IOException;

import java.util.List;
import javax.servlet.ServletException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pe.egcc.ventasweb.model.Producto;

import pe.egcc.ventasweb.service.espec.ProductoServiceEspec;

import pe.egcc.ventasweb.service.impl.ProductoServiceImpl;

@WebServlet(name = "ProductoController",
        urlPatterns = {"/ProductoTraerUno", "/ProductoEditar", "/ProductoEliminar", "/ProductoGabrar"})
public class ProductoController extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        switch (request.getServletPath()) {
            case "/ProductoTraerUno":
                if (request.getParameter("btnConsultar") != null) {
                    traerUno(request, response);
                } else if (request.getParameter("btnNuevo") != null) {
                    nuevo(request, response);
                }
                break;
            case "/ProductoEditar":
                editar(request, response);
                break;
            case "/ProductoEliminar":
                eliminar(request, response);
                break;
            case "/ProductoGabrar":
                grabar(request, response);
                break;
        }

    }

    private void traerUno(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        // Datos
        String nombre = request.getParameter("nombre");

        // Proceso
        Producto bean = new Producto();
        bean.setNombre(nombre);
        ProductoServiceEspec service = new ProductoServiceImpl();
        List<Producto> lista = service.leer(bean);
        // Forward
        request.setAttribute("lista", lista);
        UtilController.forward(request, response, "mantProductos.jsp");
    }

    private void nuevo(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        // Variables
        Producto bean = new Producto();
        // Datos para la página
        request.setAttribute("accion", UtilController.CRUD_NUEVO);
        request.setAttribute("bean", bean);
        UtilController.forward(request, response, "editarProducto.jsp");
    } // Fin de nuevo

    private void editar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Parámetro
        int id = Integer.parseInt(request.getParameter("id"));
        // Recuperar datos del producto
        ProductoServiceEspec service;
        service = new ProductoServiceImpl();
        Producto bean = service.leer(id);
        // Datos de la página
        request.setAttribute("bean", bean);
        request.setAttribute("accion", UtilController.CRUD_EDITAR);
        UtilController.forward(request, response, "editarProducto.jsp");
    }

    private void eliminar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Parámetro
        int id = Integer.parseInt(request.getParameter("id"));
        // Recuperar datos del producto
        ProductoServiceEspec service;
        service = new ProductoServiceImpl();
        Producto bean = service.leer(id);
        // Datos de la página
        request.setAttribute("bean", bean);
        request.setAttribute("accion", UtilController.CRUD_ELIMINAR);
        UtilController.forward(request, response, "editarProducto.jsp");
    }

    private void grabar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Producto bean = new Producto();
        String destino;
        String accion = "";
        try {
            // Datos
            accion = request.getParameter("accion");

            bean.setIdprod(Integer.parseInt(request.getParameter("idprod")));
            
            if(!accion.equals(UtilController.CRUD_ELIMINAR)){
                bean.setIdprod(Integer.parseInt(request.getParameter("idprod")));
                bean.setIdcat(Integer.parseInt(request.getParameter("idcat")));
                bean.setNombre(request.getParameter("nombre"));
                bean.setDescripcion(request.getParameter("descripcion"));
                bean.setPrecio(Double.parseDouble(request.getParameter("precio")));
                bean.setStock(Integer.parseInt(request.getParameter("stock")));
            }

            // Proceso
            ProductoServiceEspec service;
            service = new ProductoServiceImpl();
            String mensaje = "";
            switch (accion) {
                case UtilController.CRUD_NUEVO:
                    service.crear(bean);
                    mensaje = "Producto registrado correctamente (id=" + bean.getIdprod() + ").";
                    break;
                case UtilController.CRUD_EDITAR:
                    service.modificar(bean);
                    mensaje = "Producto actualizado correctamente.";
                    break;
                case UtilController.CRUD_ELIMINAR:
                    service.eliminar(bean.getIdprod());
                    mensaje = "Producto eliminado correctamente.";
                    break;
            }
            request.setAttribute("mensaje", mensaje);
            destino = "mantProductos.jsp";
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", e.getMessage());
            request.setAttribute("bean", bean);
            request.setAttribute("accion", accion);
            destino = "editarProducto.jsp";
        }
        // Forward
        UtilController.forward(request, response, destino);
    } // Fin de grabar

}
