/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.egcc.ventasweb.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import pe.egcc.ventasweb.model.Detalle;
import pe.egcc.ventasweb.model.Empleado;
import pe.egcc.ventasweb.model.Producto;
import pe.egcc.ventasweb.model.Venta;
import pe.egcc.ventasweb.service.espec.ProductoServiceEspec;
import pe.egcc.ventasweb.service.espec.VentaServiceEspec;
import pe.egcc.ventasweb.service.impl.ProductoServiceImpl;
import pe.egcc.ventasweb.service.impl.VentaServiceImpl;

/**
 *
 * @author firefox_usr
 */
@WebServlet(name = "VentaController",
        urlPatterns = {"/VentaGetForm", "/VentaPostForm", "/ConsultaVenta"})
public class VentaController extends HttpServlet {

    private ProductoServiceEspec productoService;
    private VentaServiceEspec ventaService;

    @Override
    public void init() throws ServletException {
        productoService = new ProductoServiceImpl();
        ventaService = new VentaServiceImpl();
    }

    @Override
    protected void service(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        switch (request.getServletPath()) {
            case "/VentaGetForm":
                getForm(request, response);
                break;
            case "/VentaPostForm":
                if (request.getParameter("btnGrabarVenta") != null) {
                    grabarVenta(request, response);
                } else if (request.getParameter("btnAddItem") != null) {
                    addItem(request, response);
                } else if (request.getParameter("btnResetItem") != null) {
                    resetitem(request, response);
                }
                break;
            case "/ConsultaVenta":
                consultaVenta(request, response);
                break;
        }
    } // Fin de service

    private void getForm(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        try {
            // Productos para el combo
            request.setAttribute("productos", productoService.leerTodos());
            // Objeto venta en session
            Object o = UtilController.getValue(request, "venta");
            if (o == null) {
                Venta venta = new Venta();
                UtilController.setValue(request, "venta", venta);
            }
        } catch (Exception e) {
        }
        // Forward
        UtilController.forward(request, response, "venta.jsp");
    }

    private void grabarVenta(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Productos para el combo
            request.setAttribute("productos", productoService.leerTodos());
            // Cliente
            String cliente = request.getParameter("cliente");
            // El objeto venta
            Venta venta;
            Object o = UtilController.getValue(request, "venta");
            if (o == null) {
                venta = new Venta();
                UtilController.setValue(request, "venta", venta);
            } else {
                venta = (Venta) o;
            }
            venta.setCliente(cliente);
            if (venta.getItems().size() == 0) {
                throw new Exception("No tiene productos esta venta.");
            }
            // El empleado
            Empleado bean = (Empleado) UtilController.getValue(request, "usuario");
            venta.setIdemp(bean.getIdemp());
            // Grabar venta
            venta.setFecha(Calendar.getInstance().getTime());
            ventaService.grabar(venta);
            request.setAttribute("mensaje", "Proceso ok. IdVenta=" + venta.getIdventa() + ".");
            // Reset de venta
            venta.reset();
        } catch (Exception e) {
            request.setAttribute("error", e.getMessage());
        }
        // Forward
        UtilController.forward(request, response, "venta.jsp");
    }

    private void addItem(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        try {
            // Productos para el combo
            request.setAttribute("productos", productoService.leerTodos());
            // Datos
            String cliente = request.getParameter("cliente");
            int idprod = Integer.parseInt(request.getParameter("idprod"));
            int cant = Integer.parseInt(request.getParameter("cant"));
            // Verificar producto
            if (idprod == 0) {
                throw new Exception("Debe seleccionar un producto.");
            }
            // Construyendo el detalle
            Producto prod = productoService.leer(idprod);
            Detalle detalle = new Detalle();
            detalle.setIdprod(idprod);
            detalle.setNombre(prod.getNombre());
            detalle.setPrecatalogo(prod.getPrecio());
            detalle.setPreventa(prod.getPrecio());
            detalle.setCant(cant);
            detalle.setSubtotal(cant * detalle.getPreventa());
            // El objeto venta
            Venta venta;
            Object o = UtilController.getValue(request, "venta");
            if (o == null) {
                venta = new Venta();
                UtilController.setValue(request, "venta", venta);
            } else {
                venta = (Venta) o;
            }
            // Agregar el item
            venta.setCliente(cliente);
            venta.addItem(detalle);
        } catch (Exception e) {
            request.setAttribute("error", e.getMessage());
        }
        // Forward
        UtilController.forward(request, response, "venta.jsp");
    } // Fin de addItem

    private void resetitem(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        try {
            // Productos para el combo
            request.setAttribute("productos", productoService.leerTodos());
            // Reset de venta
            Venta venta;
            Object o = UtilController.getValue(request, "venta");
            if (o == null) {
                venta = new Venta();
                UtilController.setValue(request, "venta", venta);
            } else {
                venta = (Venta) o;
            }
            venta.borraitems();
        } catch (Exception e) {
            request.setAttribute("error", e.getMessage());
        }
        // Forward
        UtilController.forward(request, response, "venta.jsp");
    }

    private void consultaVenta(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       // Proceso
        
        List<Venta> lista = ventaService.getVentas();
        // Forward
        request.setAttribute("lista", lista);
        UtilController.forward(request, response, "consultaVenta.jsp");   
    }

} // Fin de VentaController
