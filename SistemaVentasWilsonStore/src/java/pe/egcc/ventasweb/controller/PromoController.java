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
import pe.egcc.ventasweb.model.Promocion;
import pe.egcc.ventasweb.service.espec.PromocionServiceEspec;
import pe.egcc.ventasweb.service.impl.JdbcUtil;
import pe.egcc.ventasweb.service.impl.PromocionServiceImpl;

/**
 *
 * @author Carlos
 */
@WebServlet(name = "PromoController",
        urlPatterns = {"/PromoConsultar", "/PromoEditar", "/PromoEliminar", "/PromoGrabar"})
public class PromoController extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        switch (request.getServletPath()) {
            case "/PromoConsultar":
                if (request.getParameter("btnConsultar") != null) {
                    promocionConsultar(request, response);
                } else if (request.getParameter("btnNuevo") != null) {
                    nuevo(request, response);
                }
                break;
            case "/PromoEditar":
                editar(request, response);
                break;
            case "/PromoEliminar":
                eliminar(request, response);
                break;
            case "/PromoGrabar":
                grabar(request, response);
                break;

        }

    }

    private void promocionConsultar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Proceso
        PromocionServiceEspec service = new PromocionServiceImpl();
        List<Promocion> lista = service.getPromociones();
        // Forward
        request.setAttribute("lista", lista);
        UtilController.forward(request, response, "mantPromocion.jsp");
    }

    private void nuevo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Variables
        Promocion bean = new Promocion();
        // Datos para la página
        request.setAttribute("accion", UtilController.CRUD_NUEVO);
        request.setAttribute("bean", bean);
        UtilController.forward(request, response, "editarPromocion.jsp");
    }

    private void editar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Parámetro
        int id = Integer.parseInt(request.getParameter("id"));
        // Recuperar datos del producto
        PromocionServiceEspec service;
        service = new PromocionServiceImpl();
        Promocion bean = service.leer(id);
        // Datos de la página
        request.setAttribute("bean", bean);
        request.setAttribute("accion", UtilController.CRUD_EDITAR);
        UtilController.forward(request, response, "editarPromocion.jsp");
    }

    private void eliminar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Parámetro
        int id = Integer.parseInt(request.getParameter("id"));
        // Recuperar datos del producto
        PromocionServiceEspec service;
        service = new PromocionServiceImpl();
        Promocion bean = service.leer(id);
        // Datos de la página
        request.setAttribute("bean", bean);
        request.setAttribute("accion", UtilController.CRUD_ELIMINAR);
        UtilController.forward(request, response, "editarPromocion.jsp");
    }

    private void grabar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Promocion bean = new Promocion();
        String destino;
        String accion = "";
        try {
            // Datos
            accion = request.getParameter("accion");

            bean.setIdprom(Integer.parseInt(request.getParameter("idprom")));

            if (!accion.equals(UtilController.CRUD_ELIMINAR)) {
                bean.setIdprom(Integer.parseInt(request.getParameter("idprom")));
                bean.setFecinicio(JdbcUtil.utilStringDateToSqlDate(request.getParameter("fecInicio")));
                bean.setFecfin(JdbcUtil.utilStringDateToSqlDate(request.getParameter("fecFin")));
                bean.setPrecio(Double.parseDouble(request.getParameter("precio")));
                bean.setOferta(Double.parseDouble(request.getParameter("oferta")));
                bean.setIdprod(Integer.parseInt(request.getParameter("idprod")));
                bean.setEstado(Integer.parseInt(request.getParameter("estado")));
            }

            // Proceso
            PromocionServiceEspec service;
            service = new PromocionServiceImpl();
            String mensaje = "";
            switch (accion) {
                case UtilController.CRUD_NUEVO:
                    service.crear(bean);
                    mensaje = "Promocion registrado correctamente (id=" + bean.getIdprom() + ").";
                    break;
                case UtilController.CRUD_EDITAR:
                    service.modificar(bean);
                    mensaje = "Promocion actualizado correctamente.";
                    break;
                case UtilController.CRUD_ELIMINAR:
                    service.eliminar(bean.getIdprom());
                    mensaje = "Promocion eliminado correctamente.";
                    break;
            }
            request.setAttribute("mensaje", mensaje);
            destino = "mantPromocion.jsp";
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", e.getMessage());
            request.setAttribute("bean", bean);
            request.setAttribute("accion", accion);
            destino = "editarPromocion.jsp";
        }
        // Forward
        UtilController.forward(request, response, destino);
    }

}
