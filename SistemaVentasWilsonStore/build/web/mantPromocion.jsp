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

                <h1>MANTENIMIENTO DE PROMOCIONES</h1>

                <c:if test="${requestScope.mensaje != null}">
                    <p class="egcc_mensaje">${requestScope.mensaje}</p>
                </c:if>

                <form method="post" action="PromoConsultar">
                    <tr>
                        <td>
                            <input type="submit" value="Consultar Promociones" name="btnConsultar"/>
                            <c:if test="${sessionScope.rolMant == 1}">

                                <input type="submit" value="Nuevo" name="btnNuevo" />

                            </c:if>
                        </td>
                    </tr>

                </form>
                <h2>LISTADO</h2>
                <table class="tftable">
                    <thead>
                        <tr>                
                            <th>IDPROMOMOCION</th>
                            <th>FECHA DE INICIO</th>
                            <th>FECHA DE FIN</th>
                            <th>IDPRODUCTO</th>
                            <th>PRECIO</th>
                            <th>OFERTA</th>
                                <c:if test="${sessionScope.rolMant == 1}">
                                <th>ACCION</th>
                                </c:if>


                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${requestScope.lista}" var="r">
                            <tr>
                                <td>${r.idprom}</td>
                                <td>${r.fecinicio}</td>
                                <td>${r.fecfin}</td>
                                <td>${r.idprod}</td>
                                <td>${r.precio}</td>
                                <td>${r.oferta}</td>
                                <c:if test="${sessionScope.rolMant == 1}">
                                    <td style="text-align: center;">

                                        <a href="PromoEditar?id=${r.idprom}" title="Editar ${r.idprom}"><img src="img/editar.png" /></a>
                                        <a href="PromoEliminar?id=${r.idprom}" title="Eliminar ${r.idprom}"><img src="img/tacho.png" /></a>

                                    </td>
                                </c:if>
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
