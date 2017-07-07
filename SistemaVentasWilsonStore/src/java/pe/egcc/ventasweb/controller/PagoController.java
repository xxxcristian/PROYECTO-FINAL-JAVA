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
import pe.egcc.ventasweb.model.Pago;
import pe.egcc.ventasweb.service.espec.PagoServiceEspec;
import pe.egcc.ventasweb.service.espec.TipoPagoEspec;
import pe.egcc.ventasweb.service.impl.PagoServiceImpl;
import pe.egcc.ventasweb.service.impl.TipoPagoServiceImpl;

/**
 *
 * @author Carlos
 */
@WebServlet(name = "PagoController",
        urlPatterns = {"/PagoGetForm", "/PagoPostForm", "/ConsultaPago"})
public class PagoController extends HttpServlet {

    private TipoPagoEspec tipopagoService;
    private PagoServiceEspec pagoService;

    @Override
    public void init() throws ServletException {
        tipopagoService = new TipoPagoServiceImpl();
        pagoService = new PagoServiceImpl();
    }

    @Override
    protected void service(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        switch (request.getServletPath()) {
            case "/PagoGetForm":
                getForm(request, response);
                break;
            case "/PagoPostForm":
                grabarPago(request, response);
                break;
            case "/ConsultaPago":
                consultaPago(request, response);
                break;
        }
    } // Fin de service

    private void getForm(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        try {
            // Combo de Tipos de Pago
            request.setAttribute("tipopago", tipopagoService.leerTodos());
        } catch (Exception e) {
        }
        // Forward
        UtilController.forward(request, response, "pago.jsp");
    }

    private void grabarPago(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Pago bean = new Pago();
        String destino;
        String mensaje = "";
        try {
            //  Combo de Tipos de Pago
            request.setAttribute("tipopago", tipopagoService.leerTodos());
            // Datos
            bean.setIdventa(Integer.parseInt(request.getParameter("idventa")));
            bean.setIdtipo(Integer.parseInt(request.getParameter("idtipo")));
            bean.setDetalle(request.getParameter("detalle"));
            bean.setObs(request.getParameter("obs"));
            //Proceso
            pagoService.grabar(bean);
            mensaje = "Proceso ok. IdPago=" + bean.getIdpago() + ".";
            request.setAttribute("mensaje", mensaje);
            destino = "pago.jsp";
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", e.getMessage());
            request.setAttribute("bean", bean);
            destino = "pago.jsp";
        }
        //Forward
        UtilController.forward(request, response, destino);
    }

    private void consultaPago(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
     // Proceso
        
        List<Pago> lista = pagoService.getPagos();
        // Forward
        request.setAttribute("lista", lista);
        UtilController.forward(request, response, "consultaPago.jsp");   
    }

}
