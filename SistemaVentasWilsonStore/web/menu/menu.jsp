<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="menu">
    <ul>

        <li><a href="main.jsp" >Inicio</a></li>

        <c:if test="${sessionScope.rolVentas == 1}">
            <li><a href="#">Ventas</a>
                <ul>
                    <li><a href="VentaGetForm">Registrar Venta</a></li>
                    <li><a href="PagoGetForm">Registrar Pago</a></li>
                </ul>
            </li>
        </c:if>

        <c:if test="${sessionScope.rolMant == 1}">
            <li><a href="#">Mantenimiento</a>
                <ul>
                    <li><a href="mantEmpleados.jsp">Empleados</a></li>
                    <li><a href="mantProductos.jsp">Productos</a></li>
                    <li><a href="mantPromocion.jsp">Promociones</a></li>
                    <li><a href="mantCategoria.jsp">Categoria</a></li>
                    <li><a href="mantCampaña.jsp">Campañas</a></li>
                </ul>
            </li>
        </c:if>

        <c:if test="${sessionScope.rolConsultas == 1}">
            <li><a href="#">Consultas</a>
                <ul>
                    <li><a href="consultaVenta.jsp">Ventas</a></li>
                    <li><a href="consultaPago.jsp">Pagos</a></li>
                </ul>
            </li>
        </c:if>

        <c:if test="${sessionScope.rolReportes == 1}">
            <li><a href="#">Reportes</a>
                <ul>
                    <li><a href="mantEmpleados.jsp">Empleados</a></li>
                    <li><a href="consultaVenta.jsp">Ventas</a></li>
                    <li><a href="mantProductos.jsp">Inventario</a></li>
                </ul>
            </li>
        </c:if>

        <c:if test="${sessionScope.rolSeguridad == 1}">
            <li><a href="#">Seguridad</a>
                <ul>
                    <li><a href="mantEmpleados.jsp">Empleados</a></li>
                    <li><a href="cambiarPass.jsp">Cambio de Contraseña</a></li>
                </ul>
            </li>
        </c:if>
    </ul>

</div>
