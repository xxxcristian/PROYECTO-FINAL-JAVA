<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="menu/menu.css" rel="stylesheet" type="text/css"/>
        <link href="css/estilos.css" rel="stylesheet" type="text/css"/>
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

                <form method="get" action="PromoGrabar">

                    <h1>${requestScope.accion} PROMOCION</h1>

                    <c:if test="${requestScope.error != null}">
                        <p class="error">${requestScope.error}</p>
                    </c:if>

                    <input type="hidden" name="accion" value="${requestScope.accion}" />
                    <input type="hidden" name="idprom" value="${requestScope.bean.idprom}" />

                    <table>
                        <tr>
                            <td width="120px">ID</td>
                            <td>${requestScope.bean.idprom}</td>
                        </tr>
                        <tr>
                            <td>FECHA DE INICIO</td>
                            <td>
                                <c:if test='${requestScope.accion ne "ELIMINAR"}'>
                                 <input type="date" name="fecInicio" step="1" min="2015-01-01" max="2018-12-31"value="${requestScope.bean.fecinicio}"/>
                                </c:if>
                                <c:if test='${requestScope.accion eq "ELIMINAR"}'>
                                    ${requestScope.bean.fecinicio}
                                </c:if>
                            </td>
                        </tr>
                        <tr>
                            <td>FECHA DE FIN</td>
                            <td>
                                <c:if test='${requestScope.accion ne "ELIMINAR"}'>
                                   <input type="date" name="fecFin" step="1" min="2015-01-01" max="2018-12-31"value="${requestScope.bean.fecfin}"/>
                                </c:if>
                                <c:if test='${requestScope.accion eq "ELIMINAR"}'>
                                    ${requestScope.bean.fecfin}
                                </c:if>
                            </td>
                        </tr>
                        <tr>
                            <td>PRECIO</td>
                            <td>
                                <c:if test='${requestScope.accion ne "ELIMINAR"}'>
                                    <input type="text" name="precio" value="${requestScope.bean.precio}"/>
                                </c:if>
                                <c:if test='${requestScope.accion eq "ELIMINAR"}'>
                                    ${requestScope.bean.precio}
                                </c:if>
                            </td>
                        </tr>
                        <tr>
                            <td>OFERTA</td>
                            <td>
                                <c:if test='${requestScope.accion ne "ELIMINAR"}'>
                                    <input type="text" name="oferta" value="${requestScope.bean.oferta}"/>
                                </c:if>
                                <c:if test='${requestScope.accion eq "ELIMINAR"}'>
                                    ${requestScope.bean.oferta}
                                </c:if>
                            </td>
                        </tr>
                        <tr>
                            <td>IDPROD</td>
                            <td>
                                <c:if test='${requestScope.accion ne "ELIMINAR"}'>
                                    <input type="text" name="idprod" value="${requestScope.bean.idprod}"/>
                                </c:if>
                                <c:if test='${requestScope.accion eq "ELIMINAR"}'>
                                    ${requestScope.bean.idprod}
                                </c:if>
                            </td>
                        </tr>
                        <tr>
                            <td>ESTADO</td>
                            <td>
                                <c:if test='${requestScope.accion ne "ELIMINAR"}'>
                                    <input type="text" name="estado" value="${requestScope.bean.estado}"/>
                                </c:if>
                                <c:if test='${requestScope.accion eq "ELIMINAR"}'>
                                    ${requestScope.bean.estado}
                                </c:if>
                            </td>
                        </tr>
                    </table>
                    <input type="submit" value="Procesar" />
                     <a href="mantPromocion.jsp" title="Volver "><img src="img/Flecha-Volver.png" width='30'/></a>
                </form>
            </section>
            <footer class="pie">
                <jsp:include page="pie.jsp" />
            </footer>
        </div>
    </body>
</html>
