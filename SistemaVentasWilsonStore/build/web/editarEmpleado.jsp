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

                <form method="get" action="EmpleadoGrabar">

                    <h1>${requestScope.accion} EMPLEADO</h1>

                    <c:if test="${requestScope.error != null}">
                        <p class="error">${requestScope.error}</p>
                    </c:if>

                    <input type="hidden" name="accion" value="${requestScope.accion}" />
                    <input type="hidden" name="idemp" value="${requestScope.bean.idemp}" />

                    <table>
                        <tr>
                            <td width="120px">ID</td>
                            <td>${requestScope.bean.idemp}</td>
                        </tr>
                        <tr>
                            <td>NOMBRE</td>
                            <td>
                                <c:if test='${requestScope.accion ne "ELIMINAR"}'>
                                    <input  type="text" name="nombre"value="${requestScope.bean.nombre}"/>
                                </c:if>
                                <c:if test='${requestScope.accion eq "ELIMINAR"}'>
                                    ${requestScope.bean.nombre}
                                </c:if>
                            </td>
                        </tr>
                        <tr>
                            <td>APELLIDO</td>
                            <td>
                                <c:if test='${requestScope.accion ne "ELIMINAR"}'>
                                    <input type="text" name="apellido"value="${requestScope.bean.apellido}"/>
                                </c:if>
                                <c:if test='${requestScope.accion eq "ELIMINAR"}'>
                                    ${requestScope.bean.apellido}
                                </c:if>
                            </td>
                        </tr>
                        <tr>
                            <td>TELEFONO</td>
                            <td>
                                <c:if test='${requestScope.accion ne "ELIMINAR"}'>
                                    <input type="text" name="telefono"value="${requestScope.bean.telefono}"/>
                                </c:if>
                                <c:if test='${requestScope.accion eq "ELIMINAR"}'>
                                    ${requestScope.bean.telefono}
                                </c:if>
                            </td>
                        </tr>
                        <tr>
                            <td>EMAIL</td>
                            <td>
                                <c:if test='${requestScope.accion ne "ELIMINAR"}'>
                                    <input type="text" name="email"value="${requestScope.bean.email}"/>
                                </c:if>
                                <c:if test='${requestScope.accion eq "ELIMINAR"}'>
                                    ${requestScope.bean.email}
                                </c:if>
                            </td>
                        </tr>
                        <tr>
                            <td>DNI</td>
                            <td>
                                <c:if test='${requestScope.accion ne "ELIMINAR"}'>
                                    <input type="text" name="dni"value="${requestScope.bean.dni}"/>
                                </c:if>
                                <c:if test='${requestScope.accion eq "ELIMINAR"}'>
                                    ${requestScope.bean.dni}
                                </c:if>
                            </td>
                        </tr>
                        <tr>
                            <td>DIRECCION</td>
                            <td>
                                <c:if test='${requestScope.accion ne "ELIMINAR"}'>
                                    <input type="text" name="direccion"value="${requestScope.bean.direccion}"/>
                                </c:if>
                                <c:if test='${requestScope.accion eq "ELIMINAR"}'>
                                    ${requestScope.bean.direccion}
                                </c:if>
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
