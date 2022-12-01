<%
if(session.getAttribute("usuario") != null){
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta name="viewport" contentType="text/html; charset=UTF-8" content="width=device-width, initial-scale=1">
        <!-- boostrap -->
        <link rel="stylesheet" type="text/css" href="pages/css/bootstrap.css">
        <link href="pages/css/piede.pagina.css" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" type="text/css" href="js/DataTables/datatables.min.css"/>
        <link href="pages/css/sweetalert2.min.css" rel="stylesheet" type="text/css"/>
        <!-- Iconos -->
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    </head>
    <body>
        <nav class="navbar navbar-expand-lg navbar-dark bg-dark" id="navbarSupportedContent">
            <div class="container-fluid">
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarNavDropdown">
                    <a class="navbar-brand" href="#">CGMAIG</a>
                    <ul class="navbar-nav mr-auto">
                        <li class="nav-item">
                            <a class="nav-link active" aria-current="page" href="principal.jsp">Inicio</a>
                        </li>
                        <li class="nav-item dropdown">
                            <a class="nav-link active dropdown-toggle" href="#" data-bs-toggle="dropdown" aria-expanded="false">
                                Bienes
                            </a>
                            <ul class="dropdown-menu">
                                <li><a class="dropdown-item" href="ControllerBienes?selecciono=Bienes&Tipo=todos">Todos</a></li>
                                <li><a class="dropdown-item" href="ControllerBienes?selecciono=Bienes&Tipo=climas">Climas</a></li>
                                <li><a class="dropdown-item" href="ControllerBienes?selecciono=Bienes&Tipo=extintores">Extintores</a></li>
                                <li><a class="dropdown-item" href="ControllerBienes?selecciono=Bienes&Tipo=impresoras">Impresoras</a></li>
                            </ul>
                        </li>
                        <li class="nav-item dropdown">
                            <a class="nav-link active dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                                CFE
                            </a>
                            <ul class="dropdown-menu">
                                <li><a class="dropdown-item" href="ControllerCFE?pagina=Medidor">Medidor</a></li>
                                <li><a class="dropdown-item" href="ControllerCFE?pagina=Recibo&Tipo=todos">Recibo</a></li>
                            </ul>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link active" href="ControllerBienes?selecciono=Mantenimientos&Tipo=todos">Mantenimiento</a>
                        </li>
                        <li class="nav-item dropdown">
                            <a class="nav-link active dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                                Vehiculos
                            </a>
                            <ul class="dropdown-menu">
                                <li><a class="dropdown-item" href="ControllerVehiculos?pagina=vehiculos">Vehiculos</a></li>
                                <li><a class="dropdown-item" href="ControllerVehiculos?pagina=gasolina">Carga de Gasolina</a></li>
                                <li><a class="dropdown-item" href="ControllerVehiculos?pagina=servicios">Servicios</a></li>
                                <li><a class="dropdown-item" href="ControllerVehiculos?pagina=salidas">Salidas</a></li>
                            </ul>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link active" href="ControllerInventario?pagina=inventario">Inventario</a>
                        </li>
                    </ul>
                    <div align="right">
                        <div class="container-fluid" >
                            <div class="collapse navbar-collapse" id="navbarNavDropdown">
                                <ul class="navbar-nav mr-auto">
                                    <li class="nav-item dropdown" > 
                                        <a class="nav-link active dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                                            <span class="material-icons">account_circle</span>
                                        </a>
                                        <ul class="dropdown-menu dropdown-menu-right">
                                            <li><a class="dropdown-item" href="#">${usuario.nombre}</a></li>
                                            <div class="dropdown-divider"></div>
                                            <li><a class="dropdown-item" href="ControllerLogin?pagina=gasolina">Perfil</a></li>
                                            <li><a class="dropdown-item" href="ControllerLogin?pagina=vehiculos">Usuarios</a></li>
                                            <li>
                                            <li>
                                                <a class="dropdown-item" href="Validar?accion=salir">
                                                    <span class="material-icons">
                                                        logout</span>
                                                </a>
                                            </li>
                                    </li>                             
                                </ul>
                                </li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </nav>
        <br>
        <div class="container">
            <div class="col-md-16">
                <div class="card">
                    <div class="card-header text-white">
                        <div class="titulo">
                            <h5>Inventario</h5>
                            <div>
                                <button class="btn btn-sm btn-primary text-right" data-bs-toggle="modal"  data-bs-target="#productoModal">Nuevo Producto</button>
                                <button class="btn btn-sm btn-primary text-right" id="entrada" data-bs-toggle="modal"  data-bs-target="#exampleModal">Entrada</button>
                                <button class="btn btn-sm btn-primary text-right" id="salida" data-bs-toggle="modal"  data-bs-target="#exampleModal">Salida</button>

                            </div>
                        </div>
                    </div>   
                </div>
                <div align="center">
                    <table id="Combustible" class="table table-bordered display nowrap" width="100%">
                        <thead>
                            <tr>                 
                                <th>ID</th>
                                <th>Codigo</th>
                                <th>Descripción</th>
                                <th>Proyección Anual</th>
                                <th>Entrada</th>
                                <th>Salida</th>
                                <th>Pendiente Anual</th>
                                <th>Acciones</th>
                            </tr>
                        </thead>
                    </table>
                </div>
            </div>
        </div>

        <!-- Modal -->
        <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="modalTitulo">Entrada</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <form id="formInventario">
                            <div id="mensaje">
                            </div>
                            <div class="form-group">
                                <label>Folio</label>
                                <input type="text" class="form-control" placeholder="Folio" name="folio" id="folio">
                                <label>Codigo</label>
                                <select name='codigo' id='codigo' class='form-control' required="required">
                                    <option value="">Codigo...</option>
                                </select>  
                                <label>Descripción</label>
                                <select name='descripcion' id='descripcion' class='form-control' required="required">
                                    <option value="">Descripción...</option>
                                </select>   
                                <label>Fecha</label>
                                <input type="date" class="form-control" name="fecha" id="fecha" required="requiered">
                                <label>Cantidad</label>
                                <input type="number" class="form-control" step="1" min="1" placeholder="0" name="cantidad" id="cantidad" required="requiered">
                                <label id="solicitanteLabel">Solicitante</label>
                                <input type="text" class="form-control" placeholder="nombre" name="solicitante" id="solicitante" required="requiered">
                            </div>

                        </form>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-dark" data-bs-dismiss="modal" id="cancelar">Cancelar</button>
                            <button class="btn btn-primary" id="botonEntrada">Guardar</button>
                            <button class="btn btn-primary" id="botonSalida">Guardar</button>
                        </div>
                    </div>

                </div>
            </div>
        </div>

        <!-- Modal producto-->
        <div class="modal fade" id="productoModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="modalTitulo">Nuevo Producto</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <form id="formProducto">
                            <div id="mensaje">
                            </div>
                            <div class="form-group">
                                <label>Codigo</label>
                                <input type="text" class="form-control" name="codigoP" id="codigoP" required="requiered">
                            </div>  


                            <div class="form-group">
                                <label>Descripción</label>
                                <input type="text" class="form-control" name="descripcionP" id="descripcionP" required="requiered">
                            </div>  
                            <div>
                                <label>Proyección Anual</label>
                                <input type="text" class="form-control" name="proyeccion_anual" id="proyeccion_anual" required="requiered">
                            </div>

                        </form>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-dark" data-bs-dismiss="modal" id="cancelar">Cancelar</button>
                        <button class="btn btn-primary" id="productoGuardar">Guardar</button>
                    </div>
                </div>

            </div>
        </div>
    </div>

    <br>
    <footer class="site-footer">
        <div class="container" align="center">
            <div class="row pt-2 mt-2 text-center">
                <div class="col-md-12">
                    <img src="imagen/firma_blanco.png" alt="firma blanco.png" height="173" width="100"/>
                </div>
                <div class="col-md-12"><br>
                    <p id="blanco"> Copyright &copy;2022</p>
                </div>
                <div class="col-md-12">
                    <strong>Coordinaci&oacute;n General de Modernizaci&oacute;n Administrativa e Innovaci&oacute;n Gubernamental</strong>
                </div>
            </div>
        </div>
    </footer>
    <!-- jquery -->
    <script src="js/jquery-3.6.1.min.js" type="text/javascript"></script>


    <!-- bootstrap -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-u1OknCvxWvY5kfmNBILK2hRnQC3Pr17a+RTT6rIHI7NnikvbZlHgTPOOmMi466C8" crossorigin="anonymous"></script>

    <!-- Alerta jquery-->
    <script src="js/sweetalert2.min.js" type="text/javascript"></script>

    <!-- dataTable -->

    <script type="text/javascript" src="js/DataTables/datatables.min.js"></script>

    <!-- Editar Excel dataTable -->
    <script src="js/datatables-buttons-excel-styles.js" type="text/javascript"></script>
    <script src="js/datatables-buttons-excel-styles.templates.js" type="text/javascript"></script>
    <script src="pages/inventario/js/inventario.js" type="text/javascript"></script>
    <script>
        var data = JSON.parse('${data}');

        var $select = $('#codigo');
        var $selectDescripcion = $('#descripcion');
        $.each(data, function (i, val) {
            $select.append($('<option />', {value: val.cve_producto, text: val.codigo})
                    );

            $selectDescripcion.append($('<option />', {value: val.cve_producto, text: val.descripcion})
                    );
        });


        $("#codigo").change(function () {
            var selec = $('#codigo option:selected').val();

            document.getElementById("descripcion").value = selec;
        });


        $("#descrip").change(function () {
            var selec = $('#codigo option:selected').val();

            document.getElementById("descripcion").value = selec;
        });



        var activar = 'block';
        var desactivar = 'none';
        $("#salida").click(function () {
            document.getElementById("codigo").value = "";
            document.getElementById("modalTitulo").innerHTML = "Salida";
            document.getElementById("botonEntrada").style.display = desactivar;
            document.getElementById("botonSalida").style.display = activar;
            document.getElementById("solicitante").style.display = activar;
            document.getElementById("solicitanteLabel").style.display = activar;
            $("#botonSalida").click(function () {
                Guardar("salida", "formInventario");
            });
        });

        $("#entrada").click(function () {
            document.getElementById("codigo").value = "";
            document.getElementById("modalTitulo").innerHTML = "Entrada";
            document.getElementById("botonEntrada").style.display = activar;
            document.getElementById("botonSalida").style.display = desactivar;
            document.getElementById("solicitante").style.display = desactivar;
            document.getElementById("solicitanteLabel").style.display = desactivar;
            $("#botonEntrada").click(function () {
                Guardar("entrada", "formInventario");
            });
        });


        $("#productoGuardar").click(function () {

            Guardar("producto", "formProducto");
        });


        function Guardar(guardar, form) {
            var datos = $("#" + form).serialize();
            Enviar(datos, guardar);
            Limpiar();
        }


        function Enviar(datos, guardar) {
            $.ajax({
                url: "ControllerInventario?guardar=" + guardar, type: 'POST', data: datos,
                success: function (data) {
                    swal('Mensaje del sistema', data.trim(), 'success');
                }, error: function (xml, data) {
                    swal('Error!', 'ah ocurrido un error en la base de datos', 'error');
                }
            });
        }
        function Limpiar() {
            $('#folio').val("");
            $('#codigo').val("");
            $('#descripcion').val("");
            $('#fecha').val("");
            $('#cantidad').val("");

            $('#codigoP').val("");
            $('#descripcionP').val("");
            $('#proyeccion_anual').val("");
        }

    </script>
</body>
</html>
<%
}else{
    response.sendRedirect("/serviciosgenerales/index.jsp");
}
%>