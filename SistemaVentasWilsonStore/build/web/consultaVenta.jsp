<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="menu/menu.css" rel="stylesheet" type="text/css"/>
        <link href="css/estilos.css" rel="stylesheet" type="text/css"/>
        <link href="css/tabla.css" rel="stylesheet" type="text/css"/>
        <title>.:: VENTAS APP ::.</title>
    </head>
    <body>
        <div class="page">
            <header class="header">
                <jsp:include page="header.jsp" />
            </header>
            <section class="menu">
                <jsp:include page="menu/menu.jsp" />
            </section>
            <section class="content">

                <h1>CONSULTA DE VENTAS</h1>

                <c:if test="${requestScope.mensaje != null}">
                    <p class="egcc_mensaje">${requestScope.mensaje}</p>
                </c:if>

                <form method="post" action="ConsultaVenta">
                    <tr>
                        <td>
                            <input type="submit" value="Consultar Ventas" name="btnConsultar"/>
                        </td>
                    </tr>

                </form>
                <h2>LISTADO</h2>
                <table class="tftable">
                    <thead>
                        <tr>                
                            <th>IDVENTA</th>
                            <th>IDEMP</th>
                            <th>CLIENTE</th>
                            <th>FECHA</th>
                            <th>IMPORTE</th>
                            <th>IMPUESTO</th>
                            <th>TOTAL</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${requestScope.lista}" var="r">
                            <tr>
                                <td>${r.idventa}</td>
                                <td>${r.idemp}</td>
                                <td>${r.cliente}</td>
                                <td>${r.fecha}</td>
                                <td>${r.importe}</td>
                                <td>${r.impuesto}</td>
                                <td>${r.total}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </section>
            <footer class="pie">
                <jsp:include page="pie.jsp" />
            </footer>
        </div>
    </body>
</html>

