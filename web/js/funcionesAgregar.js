$(document).ready(function () {
    mostrarCantidadArticulos();
   
});

window.onload = mostrarCantidadArticulos();

function enviarIdControlador(idProducto) {
    var url = "Controlador?accion=AgregarCarrito";
    $.ajax({
        type: 'POST',
        url: url,
        data: "id=" + idProducto,
        success: function (result) {
            console.log(result);
            $("#contadorItems").html("<i class=\"fas fa-cart-plus\">(<label style=\"color: orange\">" + result + "</label>)</i>Carrito");
        }, error: function (msg) {
            alert("Todo salio mal" + JSON.stringify(msg));
        }
    });
}

//Para mostrar la cantidad de articulos en el Carrito de HOME
function mostrarCantidadArticulos() {
    var url = "Controlador?accion=Listar";
    $.ajax({
        type: 'POST',
        url: url,
        success: function (result) {
            console.log(result);
            if (result > 0) {
                $("#contadorItems").html("<i class=\"fas fa-cart-plus\">(<label style=\"color: orange\">" + result + "</label>)</i> Carrito");
            }
        }, error: function (msg) {
            alert("Todo salio mal en mostrar cantidad articulos " + JSON.stringify(msg));
        }
    });
}

function getDescuento(valor) {
    var subtotal = $("#subtotalCarrito").val();
    subtotal = subtotal.substr(1);
    var descuento = (parseFloat(subtotal) * valor) / 100;
    var totalPagar = parseFloat(subtotal) - descuento;
    $("#totalCarrito").val("$" + totalPagar.toFixed(2).replace('.', ','));
}


