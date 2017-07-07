
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

                <form name= "formchangepass" method="post" action="EditarPassEmp">
                    <c:if test="${requestScope.error != null}">
                        <p class="error">${requestScope.error}</p>
                    </c:if>
                    <c:if test="${requestScope.mensaje != null}">
                        <p class="egcc_mensaje">${requestScope.mensaje}</p>
                    </c:if>
                    <table>
                        <h1>Cambiar Contraseña</h1>
                        <tr>
                            <td>Contraseña Actual:</td>
                            <td><input type="password" name="claveActual"/></td>
                        </tr>
                        <tr>
                            <td>Nueva Contraseña:</td>
                            <td><input type="password" name="claveNueva"/></td>
                        </tr>
                        <tr>
                            <td>Confirmar Contraseña:</td>
                            <td><input type="password" name="claveConfirma"/></td>
                        </tr>  
                        <tr>
                            <td colspan="3" style="text-align: center;">
                                <input type="submit" name="btnCambiar" onclick="pregunta()" value="Cambiar" />
                            </td>
                        </tr>
                    </table>

                </form>
            </section>
            <footer class="pie">
                <jsp:include page="pie.jsp" />
            </footer>
        </div>
        <script>
            function pregunta() {
                if (confirm('¿Estas seguro de cambiar la contraseña?')) {
                    document.formchangepass.submit();

                } else {
                    alert("Operacion cancelada");
                }
            }

        </script>
    </body>
</html>
