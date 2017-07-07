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

        <h1>MANTENIMIENTO DE CATEGORIAS</h1>

        <c:if test="${requestScope.mensaje != null}">
          <p class="egcc_mensaje">${requestScope.mensaje}</p>
        </c:if>

        <form method="get" action="CategoriaConsultar">
          <fieldset>
            <legend>Datos</legend>
            <table>
              <tr>
                <td>Nombre de la Categoria</td>
                <td></td>
              </tr>
              <tr>
                <td><input type="text" name="nombre" /></td>
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
              <th>IDCATEGORIA</th>
              <th>NOMBRE</th>
              <th>DESCRIPCION</th>
              <c:if test="${sessionScope.rolMant == 1}">
              <th>ACCION</th>
              </c:if>
            </tr>
          </thead>
          <tbody>
            <c:forEach items="${requestScope.lista}" var="r">
              <tr>
                <td>${r.idcat}</td>
                <td>${r.nombre}</td>
                <td>${r.descri}</td>
                <c:if test="${sessionScope.rolMant == 1}">
                <td style="text-align: center;">
                    
                  <a href="CategoriaEditar?id=${r.idcat}" title="Editar ${r.idcat}"><img src="img/editar.png" /></a>
                  <a href="CategoriaEliminar?id=${r.idcat}" title="Eliminar ${r.idcat}"><img src="img/tacho.png" /></a>
                    
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
