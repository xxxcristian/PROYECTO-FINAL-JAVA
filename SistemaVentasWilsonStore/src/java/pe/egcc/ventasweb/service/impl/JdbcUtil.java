package pe.egcc.ventasweb.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Eric Gustavo Coronel Castillo
 * @blog www.desarrollasoftware.com
 * @email egcc.usil@gmail.com
 */
public final class JdbcUtil {


    private JdbcUtil() {
    }
    
        public static Date utilStringDateToSqlDate(String date) {
        java.sql.Date sqlDate = java.sql.Date.valueOf(date);
        return sqlDate;
    }


    public static java.sql.Date
            utilDateToSqlDate(java.util.Date utilDate) {
        java.sql.Date sqlDate;
        sqlDate = new java.sql.Date(utilDate.getTime());
        return sqlDate;
    }



    }

