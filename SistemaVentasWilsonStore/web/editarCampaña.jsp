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

                <form method="post" action="CampanaGrabar">

                    <h1>${requestScope.accion} CAMPAÑA</h1>

                    <c:if test="${requestScope.error != null}">
                        <p class="error">${requestScope.error}</p>
                    </c:if>

                    <input type="hidden" name="accion" value="${requestScope.accion}" />
                    <input type="hidden" name="idcamp" value="${requestScope.bean.idcamp}" />

                    <table>
                        <tr>
                            <td width="120px">ID</td>
                            <td>${requestScope.bean.idcamp}</td>
                        </tr>
                        <tr>
                            <td>NOMBRE</td>
                            <td>
                                <c:if test='${requestScope.accion ne "ELIMINAR"}'>
                                    <input type="text" name="nombre" value="${requestScope.bean.nombre}"/>
                                </c:if>
                                <c:if test='${requestScope.accion eq "ELIMINAR"}'>
                                    ${requestScope.bean.nombre}
                                </c:if>
                            </td>
                        </tr>
                        <tr>
                            <td>DESCRIPCION</td>
                            <td>
                                <c:if test='${requestScope.accion ne "ELIMINAR"}'>
                                    <input type="text" name="descripcion" value="${requestScope.bean.descripcion}"/>
                                </c:if>
                                <c:if test='${requestScope.accion eq "ELIMINAR"}'>
                                    ${requestScope.bean.descripcion}
                                </c:if>
                            </td>
                        </tr>
                        <tr>
                            <td>IDCAT</td>
                            <td>
                                <c:if test='${requestScope.accion ne "ELIMINAR"}'>
                                    <input type="text" name="idcat" value="${requestScope.bean.idcat}"/>
                                </c:if>
                                <c:if test='${requestScope.accion eq "ELIMINAR"}'>
                                    ${requestScope.bean.idcat}
                                </c:if>
                            </td>
                        </tr>
                        <tr>
                            <td>FECHA DE INICIO</td>
                            <td>
                                <c:if test='${requestScope.accion ne "ELIMINAR"}'>
                                    <input type="date" name="fecInicio" step="1" min="2015-01-01" max="2018-12-31"value="${requestScope.bean.fecInicio}"/>
                                </c:if>
                                <c:if test='${requestScope.accion eq "ELIMINAR"}'>
                                    ${requestScope.bean.fecInicio}
                                </c:if>
                            </td>
                        </tr>
                        <tr>
                            <td>FECHA DE FIN</td>
                            <td>
                                <c:if test='${requestScope.accion ne "ELIMINAR"}'>
                                    <input type="date" name="fecFin" step="1" min="2015-01-01" max="2018-12-31"value="${requestScope.bean.fecFin}"/>
                                </c:if>
                                <c:if test='${requestScope.accion eq "ELIMINAR"}'>
                                    ${requestScope.bean.fecFin}
                                </c:if>
                            </td>
                        </tr>
                        <tr>
                            <td>PORC. DE DSCTO</td>
                            <td>
                                <c:if test='${requestScope.accion ne "ELIMINAR"}'>
                                    <input type="text" name="porcDcto" value="${requestScope.bean.porcDcto}"/>
                                </c:if>
                                <c:if test='${requestScope.accion eq "ELIMINAR"}'>
                                    ${requestScope.bean.porcDcto}
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
                    <a href="mantCampaña.jsp" title="Volver "><img src="img/Flecha-Volver.png" width='30'/></a>
                </form>
            </section>
            <footer class="pie">
                <jsp:include page="pie.jsp" />
            </footer>
        </div>
    </body>
</html>
