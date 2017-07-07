/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.egcc.ventasweb.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import pe.egcc.ventasweb.model.Categoria;
import pe.egcc.ventasweb.service.espec.CategoriaServiceEspec;
import pe.egcc.ventasweb.service.impl.CategoriaServiceImpl;

/**
 *
 * @author firefox_usr
 */
@WebServlet(name = "CategoriaController", 
        urlPatterns = {"/CategoriaConsultar", "/CategoriaEditar", "/CategoriaEliminar", "/CategoriaGabrar"})
public class CategoriaController extends HttpServlet {
    protected void service(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        switch (request.getServletPath()) {
            case "/CategoriaConsultar":
                if (request.getParameter("btnConsultar") != null) {
                    traerUno(request, response);
                } else if (request.getParameter("btnNuevo") != null) {
                    nuevo(request, response);
                }
                break;
            case "/CategoriaEditar":
                editar(request, response);
                break;
            case "/CategoriaEliminar":
                eliminar(request, response);
                break;
            case "/CategoriaGabrar":
                grabar(request, response);
                break;
        }
}

    private void traerUno(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Datos
        String nombre = request.getParameter("nombre");
        // Proceso
        Categoria bean = new Categoria();
        bean.setNombre(nombre);
        CategoriaServiceEspec service = new CategoriaServiceImpl();
        List<Categoria> lista = service.leer(bean);
        // Forward
        request.setAttribute("lista", lista);
        UtilController.forward(request, response, "mantCategoria.jsp");
    }

    private void nuevo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
           // Variables
        Categoria bean = new Categoria();
        // Datos para la página
        request.setAttribute("accion", UtilController.CRUD_NUEVO);
        request.setAttribute("bean", bean);
        UtilController.forward(request, response, "editarCategoria.jsp");
    }

    private void editar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         // Parámetro
        int id = Integer.parseInt(request.getParameter("id"));
        // Recuperar datos de la Categoria
        CategoriaServiceEspec service;
        service = new CategoriaServiceImpl();
        Categoria bean = service.leer(id);
        // Datos de la página
        request.setAttribute("bean", bean);
        request.setAttribute("accion", UtilController.CRUD_EDITAR);
        UtilController.forward(request, response, "editarCategoria.jsp");
    }

    private void eliminar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         // Parámetro
        int id = Integer.parseInt(request.getParameter("id"));
        // Recuperar datos de la Categoria
        CategoriaServiceEspec service;
        service = new CategoriaServiceImpl();
        Categoria bean = service.leer(id);
        // Datos de la página
        request.setAttribute("bean", bean);
        request.setAttribute("accion", UtilController.CRUD_ELIMINAR);
        UtilController.forward(request, response, "editarCategoria.jsp");
    }

    private void grabar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Categoria bean = new Categoria();
        String destino;
        String accion = "";
        try {
            // Datos
            accion = request.getParameter("accion");

            bean.setIdcat(Integer.parseInt(request.getParameter("idcat")));
                            
            
            if(!accion.equals(UtilController.CRUD_ELIMINAR)){
                bean.setIdcat(Integer.parseInt(request.getParameter("idcat")));
                bean.setNombre(request.getParameter("nombre"));
                bean.setDescri(request.getParameter("descripcion"));
            }

            // Proceso
            CategoriaServiceEspec service;
            service = new CategoriaServiceImpl();
            String mensaje = "";
            switch (accion) {
                case UtilController.CRUD_NUEVO:
                    service.crear(bean);
                    mensaje = "Categoria registrado correctamente (id=" + bean.getIdcat()+ ").";
                    break;
                case UtilController.CRUD_EDITAR:
                    service.modificar(bean);
                    mensaje = "Categoria actualizado correctamente.";
                    break;
                case UtilController.CRUD_ELIMINAR:
                    service.eliminar(bean.getIdcat());
                    mensaje = "Categoria eliminado correctamente.";
                    break;
            }
            request.setAttribute("mensaje", mensaje);
            destino = "mantCategoria.jsp";
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", e.getMessage());
            request.setAttribute("bean", bean);
            request.setAttribute("accion", accion);
            destino = "editarCategoria.jsp";
        }
        // Forward
        UtilController.forward(request, response, destino);
    }
}
