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
                <h1>REGISTRAR PAGO</h1>

                <c:if test="${requestScope.mensaje != null}">
                    <p class="egcc_mensaje">${requestScope.mensaje}</p>
                </c:if>

                <c:if test="${requestScope.error != null}">
                    <p class="error">${requestScope.error}</p>
                </c:if>

                <form method="post" action="PagoPostForm">
                    <table>
                        <tr>
                            <td>IDVENTA</td>
                            <td>
                                <input type="text" name="idventa" value="${requestScope.bean.idventa}"/>
                            </td>
                        </tr>
                        <tr>
                            <td>IDTIPO</td>
                            <td>
                                <select name="idtipo">
                                    <option value="0">Seleccione producto</option>
                                    <c:if test="${tipopago != null}">
                                        <c:forEach items="${requestScope.tipopago}" var="r">
                                            <option value="${r.idtipo}">${r.nombre})</option>
                                        </c:forEach>
                                    </c:if>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td>DETALLE</td>
                            <td>
                                <input type="text" name="detalle"value="${requestScope.bean.detalle}"/>
                            </td>
                        </tr>
                        <tr>
                            <td>OBS</td>
                            <td>
                                <input type="text" name="obs"value="${requestScope.bean.obs}"/>
                            </td>
                        </tr>

                    </table>
                    <input type="submit" value="Procesar" />
                </form>

            </section>
            <footer class="pie">
                <jsp:include page="pie.jsp" />
            </footer>
        </div>
    </body>
</html>
