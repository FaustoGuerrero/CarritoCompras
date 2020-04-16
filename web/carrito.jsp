<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="es_EC"/>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Carrito</title>
        <script src="https://kit.fontawesome.com/5788627617.js" crossorigin="anonymous"></script>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    </head>
    <body>
        <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
            <a class="navbar-brand" href="#">Tienda de productos</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav mr-auto">
                    <li class="nav-item active">
                        <a class="nav-link" href="${pageContext.request.contextPath}/Controlador?accion=home">Home <span class="sr-only">(current)</span></a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">Ofertas</a>
                    </li>                        
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/Controlador?accion=home">Seguir comprando</a>
                    </li>
                </ul>
                <form class="form-inline my-2 my-lg-0">
                    <input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search">
                    <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
                </form>
                <ul class="navbar-nav">
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            Iniciar sesión
                        </a>
                        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                            <a class="dropdown-item" href="#">Action</a>
                            <a class="dropdown-item" href="#">Another action</a>
                            <div class="dropdown-divider"></div>
                            <a class="dropdown-item" href="#">Something else here</a>
                        </div>
                    </li>
                </ul>
            </div>
        </nav>

        <div class="container mt-4">
            <h3>Carrito de compras</h3>
            <br/>
            <div class="row">
                <div class="col-sm-8">
                    <table class="table table-hover">
                        <thead>
                            <tr>
                                <th>ITEM</th>
                                <th>NOMBRES</th>
                                <th>DESCRIPCION</th>
                                <th>CANTIDAD</th>
                                <th>SUBTOTAL</th>
                                <th>ACCION</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="car" items="${listaCarrito}">
                                <tr>
                                    <td>${car.item}</td>
                                    <td>${car.nombres}</td>
                                    <td>${car.descripcion}
                                        <img src="ControladorImagenes?id=${car.idProducto}" width="100">
                                    </td>
                                    <td>${car.cantidad}</td>
                                    <td><fmt:formatNumber value="${car.subTotal}" type="currency"/></td>
                                    <td>
                                        <a class="btn btn-info btn-block" href="#">Editar</a>
                                        <a class="btn btn-danger btn-block" href="#">Eliminar</a>
                                    </td>
                                </tr>    
                            </c:forEach>                            
                        </tbody>
                    </table>
                </div>
                <div class="col-sm-4">
                    <div class="card">
                        <div class="card-header">
                            <h3>Generar compra</h3>
                        </div>
                        <div class="card-body">
                            <label>Subtotal: </label>
                            <input type="text" readonly="" class="form-control" value="<fmt:formatNumber value="${totalPagar}" type="currency"/>">
                            <label>Descuento: </label>
                            <input type="text" readonly="" class="form-control" value="$ 0.00">
                            <label>Total a pagar: </label>
                            <input type="text" readonly="" class="form-control" value="<fmt:formatNumber value="${totalPagar}" type="currency"/>">
                        </div>
                        <div class="card-footer">
                            <a class="btn btn-info btn-block" href="#">Realizar pago</a>
                            <a class="btn btn-danger btn-block" href="#">Generar compra</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>                    
    </body>
</html>
