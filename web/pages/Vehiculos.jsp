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
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-u1OknCvxWvY5kfmNBILK2hRnQC3Pr17a+RTT6rIHI7NnikvbZlHgTPOOmMi466C8" crossorigin="anonymous"></script>
        <link href="pages/css/piede.pagina.css" rel="stylesheet" type="text/css"/>

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
                            <h5>Lista de Vehiculos</h5>
                            <div>
                                <a href='ControllerVehiculos?pagina=nuevoVehiculo' class="btn btn-sm btn-primary text-right">Nuevo</a>
                            </div>
                        </div>
                    </div>   
                </div>
                <div align="center">
                    <table id="vehiculos" class="table table-bordered display nowrap" width="100%">
                        <thead>
                            <tr>                 
                                <th>ID</th>
                                <th>Vehiculo</th>
                                <th>Tipo</th>
                                <th>No.Serie</th>
                                <th>Inventario</th>
                                <th>Placa Anterior</th>
                                <th>Placa Actual</th>
                                <th>Responsable</th>
                                <th>Departamento</th>
                                <th>Acciones</th>
                            </tr>
                        </thead>
                    </table>
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

        <!-- Alerta jquery-->
        <link href="pages/css/sweetalert2.min.css" rel="stylesheet" type="text/css"/>
        <script src="js/sweetalert2.min.js" type="text/javascript"></script>

        <!-- dataTable -->
        <link rel="stylesheet" type="text/css" href="js/DataTables/datatables.min.css"/>
        <script type="text/javascript" src="js/DataTables/datatables.min.js"></script>

        <!-- Editar Excel dataTable -->
        <script src="js/datatables-buttons-excel-styles.js" type="text/javascript"></script>
        <script src="js/datatables-buttons-excel-styles.templates.js" type="text/javascript"></script>

    </body>
</html>
<%
}else{
    response.sendRedirect("/serviciosgenerales/index.jsp");
}
%>