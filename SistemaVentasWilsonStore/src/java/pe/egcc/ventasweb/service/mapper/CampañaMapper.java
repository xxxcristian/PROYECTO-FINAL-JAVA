/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.egcc.ventasweb.service.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import pe.egcc.ventasweb.model.Campaña;
import pe.egcc.ventasweb.service.espec.RowMapper;

/**
 *
 * @author Carlos
 */
public class CampañaMapper
        implements RowMapper<Campaña> {

    @Override
    public Campaña mapRow(ResultSet rs) throws SQLException {

        Campaña bean = new Campaña();
        bean.setIdcamp(rs.getInt("idcamp"));
        bean.setNombre(rs.getString("nombre"));
        bean.setDescripcion(rs.getString("descripcion"));
        bean.setIdcat(rs.getInt("idcat"));
        bean.setFecInicio(rs.getDate("fecInicio"));
        bean.setFecFin(rs.getDate("fecFin"));
        bean.setPorcDcto(rs.getDouble("porcDcto"));
        bean.setEstado(rs.getInt("estado"));
        return bean;
    }

}
