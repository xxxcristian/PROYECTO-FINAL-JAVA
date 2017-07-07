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
import pe.egcc.ventasweb.model.Campaña;
import pe.egcc.ventasweb.service.espec.CampañaServiceEspec;
import pe.egcc.ventasweb.service.impl.CampañaServiceImpl;
import pe.egcc.ventasweb.service.impl.JdbcUtil;

/**
 *
 * @author firefox_usr
 */
@WebServlet(name = "CampanaController", 
        urlPatterns = {"/CampanaConsultar", "/CampanaEditar", "/CampanaEliminar", "/CampanaGrabar"})
public class CampanaController extends HttpServlet {

     @Override
    protected void service(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        switch (request.getServletPath()) {
            case "/CampanaConsultar":
                if (request.getParameter("btnConsultar") != null) {
                    campanaConsultar(request, response);
                } else if (request.getParameter("btnNuevo") != null) {
                    nuevo(request, response);
                }
                break;
            case "/CampanaEditar":
                editar(request, response);
                break;
            case "/CampanaEliminar":
                eliminar(request, response);
                break;
            case "/CampanaGrabar":
                grabar(request, response);
                break;

        }

    }

    private void campanaConsultar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Proceso
        CampañaServiceEspec service = new CampañaServiceImpl();
        List<Campaña> lista = service.getCampañas();
        // Forward
        request.setAttribute("lista", lista);
        UtilController.forward(request, response, "mantCampaña.jsp");
    }

    private void nuevo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Variables
        Campaña bean = new Campaña();
        // Datos para la página
        request.setAttribute("accion", UtilController.CRUD_NUEVO);
        request.setAttribute("bean", bean);
        UtilController.forward(request, response, "editarCampaña.jsp");
    }

    private void editar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Parámetro
        int id = Integer.parseInt(request.getParameter("id"));
        // Recuperar datos del producto
        CampañaServiceEspec service;
        service = new CampañaServiceImpl();
        Campaña bean = service.leer(id);
        // Datos de la página
        request.setAttribute("bean", bean);
        request.setAttribute("accion", UtilController.CRUD_EDITAR);
        UtilController.forward(request, response, "editarCampaña.jsp");
    }

    private void eliminar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Parámetro
        int id = Integer.parseInt(request.getParameter("id"));
        // Recuperar datos del producto
        CampañaServiceEspec service;
        service = new CampañaServiceImpl();
        Campaña bean = service.leer(id);
        // Datos de la página
        request.setAttribute("bean", bean);
        request.setAttribute("accion", UtilController.CRUD_ELIMINAR);
        UtilController.forward(request, response, "editarCampaña.jsp");
    }

    private void grabar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Campaña bean = new Campaña();
        String destino;
        String accion = "";
        try {
            // Datos
            accion = request.getParameter("accion");

            bean.setIdcamp(Integer.parseInt(request.getParameter("idcamp")));

            if (!accion.equals(UtilController.CRUD_ELIMINAR)) {
                bean.setIdcamp(Integer.parseInt(request.getParameter("idcamp")));
                bean.setNombre(request.getParameter("nombre"));
                bean.setDescripcion(request.getParameter("descripcion"));
                bean.setIdcat(Integer.parseInt(request.getParameter("idcat")));
                bean.setFecInicio(JdbcUtil.utilStringDateToSqlDate(request.getParameter("fecInicio")));
                bean.setFecFin(JdbcUtil.utilStringDateToSqlDate(request.getParameter("fecFin")));
                bean.setPorcDcto(Double.parseDouble(request.getParameter("porcDcto")));
                bean.setEstado(Integer.parseInt(request.getParameter("estado")));
            }

            // Proceso
            CampañaServiceEspec service;
            service = new CampañaServiceImpl();
            String mensaje = "";
            switch (accion) {
                case UtilController.CRUD_NUEVO:
                    service.crear(bean);
                    mensaje = "Campaña registrado correctamente (id=" + bean.getIdcamp() + ").";
                    break;
                case UtilController.CRUD_EDITAR:
                    service.modificar(bean);
                    mensaje = "Campaña actualizado correctamente.";
                    break;
                case UtilController.CRUD_ELIMINAR:
                    service.eliminar(bean.getIdcamp());
                    mensaje = "Campaña eliminado correctamente.";
                    break;
            }
            request.setAttribute("mensaje", mensaje);
            destino = "mantCampaña.jsp";
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", e.getMessage());
            request.setAttribute("bean", bean);
            request.setAttribute("accion", accion);
            destino = "editarCampaña.jsp";
        }
        // Forward
        UtilController.forward(request, response, destino);
    }

}
