<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="es_EC"/>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script src="https://kit.fontawesome.com/5788627617.js" crossorigin="anonymous"></script>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    </head>
    <body>
        <nav class="navbar navbar-expand-lg navbar-dark bg-dark navbar-default navbar-static-top">
            <div class="container">
                <a class="navbar-brand" href="#">Joyer&iacute;a Europea Catalina</a>
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav mr-auto">
                        <li class="nav-item active">
                            <a class="nav-link" href="${pageContext.request.contextPath}/Controlador?accion=home"><i class="fas fa-home"></i> Home <span class="sr-only">(current)</span></a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#"><i class="fas fa-percent"></i> Ofertas</a>
                        </li>                        
                        <li class="nav-item">
                            <a class="nav-link" href="${pageContext.request.contextPath}/Controlador?accion=Carrito"><i class="fas fa-cart-plus">(<label style="color: orange">${contador}</label>)</i> Carrito</a>
                        </li>
                    </ul>                
                    <ul class="navbar-nav">
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                <i class="fas fa-user"></i> Iniciar sesión
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
            </div>
        </nav>
        <div class="container mt-4 rounded-10">
            <div class="row">
                <c:forEach var="productos" items="${listaProductos}">
                    <br/>
                    <div class="col-sm-4">
                        <div class="card">
                            <div class="card-header">
                                <label>${productos.nombres}</label>
                            </div>
                            <div class="card-body">
                                <i><fmt:formatNumber value="${productos.precio}" type="currency"/></i>
                                <img src="ControladorImagenes?id=${productos.id}" width="200" height="180">
                            </div>                        
                            <div class="card-footer text-center">
                                <label>Descripcion del producto</label>
                                <div>
                                    <a href="Controlador?accion=AgregarCarrito&id=${productos.id}" class="btn btn-outline-info">Agregar a Carrito</a>
                                    <a href="Controlador?accion=Comprar&id=${productos.id}" class="btn btn-danger">Comprar</a>
                                </div>
                            </div>
                            <br/>
                        </div>
                    </div>
                </c:forEach>                
            </div>
        </div>
        <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
    </body>
</html>



