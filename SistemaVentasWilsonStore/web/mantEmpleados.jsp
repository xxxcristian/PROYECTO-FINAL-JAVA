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

        <h1>MANTENIMIENTO DE EMPLEADOS</h1>

        <c:if test="${requestScope.mensaje != null}">
          <p class="egcc_mensaje">${requestScope.mensaje}</p>
        </c:if>

        <form method="post" action="EmpleadoConsultar">
          <fieldset>
            <legend>Datos</legend>
            <table>
              <tr>
                <td>Nombre</td>
                <td>Apellido</td>
                <td></td>
              </tr>
              <tr>
                <td><input type="text" name="nombre" /></td>
                <td><input type="text" name="apellido" /></td>
                <td>
                  <input type="submit" value="Consultar" name="btnConsultar"/>
                  <c:if test="${sessionScope.rolMant == 1}">
                  <input type="submit" value="Nuevo" name="btnNuevo" />
                  </c:if>
                </td>
              </tr>
            </table>
          </fieldset>
        </form>
        <h2>LISTADO</h2>
        <table class="tftable">
          <thead>
            <tr>
              <th>ID</th>
              <th>NOMBRE</th>
              <th>APELLIDO</th>
              <th>EMAIL</th>
              <th>TELEFONO</th>
              <th>DNI</th>
              <th>DIRECCION</th>
              <c:if test="${sessionScope.rolMant == 1}">
              <th>ACCION</th>
              </c:if>
            </tr>
          </thead>
          <tbody>
            <c:forEach items="${requestScope.lista}" var="r">
              <tr>
                <td>${r.idemp}</td>
                <td>${r.nombre}</td>
                <td>${r.apellido}</td>
                <td>${r.email}</td>
                <td>${r.telefono}</td>
                <td>${r.dni}</td>
                <td>${r.direccion}</td>
                <c:if test="${sessionScope.rolMant == 1}">
                <td style="text-align: center;">
                  <a href="EmpleadoEditar?id=${r.idemp}" title="Editar ${r.idemp}"><img src="img/editar.png" /></a>
                  <a href="EmpleadoEliminar?id=${r.idemp}" title="Eliminar ${r.idemp}"><img src="img/tacho.png" /></a>
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
