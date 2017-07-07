/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.egcc.ventasweb.prueba;

import pe.egcc.ventasweb.model.Promocion;
import pe.egcc.ventasweb.service.espec.PromocionServiceEspec;
import pe.egcc.ventasweb.service.impl.JdbcUtil;
import pe.egcc.ventasweb.service.impl.PromocionServiceImpl;

/**
 *
 * @author Carlos
 */
public class Prueba10 {
    public static void main(String[] args) {
        try {
      // Datos
        Promocion bean = new Promocion();

      bean.setFecinicio(JdbcUtil.utilStringDateToSqlDate("2016-11-22"));
      bean.setFecfin(JdbcUtil.utilStringDateToSqlDate("2016-11-23"));
      bean.setPrecio(140);
      bean.setOferta(120);
      bean.setIdprod(2);
      bean.setEstado(0);
       // Proceso
      PromocionServiceEspec service;
      service = new PromocionServiceImpl();
      service.crear(bean);
      // Reporte
     
            System.out.println("Creado Correctamente");
    } catch (Exception e) {
            System.out.println("Error");
      e.printStackTrace();
    }
    }
}
