<%
if(session.getAttribute("usuario") != null){
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html >
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Servicios Generales</title>
        <link rel="stylesheet" type="text/css" href="pages/css/bootstrap.css">
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <script src="js/jquery-3.6.1.min.js" type="text/javascript"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-u1OknCvxWvY5kfmNBILK2hRnQC3Pr17a+RTT6rIHI7NnikvbZlHgTPOOmMi466C8" crossorigin="anonymous"></script>
        <link rel="stylesheet" type="text/css" href="pages/css/piede.pagina.css">
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
                            </ul>
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
            <div class="col-md-17">
                <div class="card">
                    <div class="card-header ">
                        <h4 class="text-white">Concretar Salida</h4>
                    </div>   
                    ${mensaje}
                    <div class="card-body">
                        <form id="form" action="ControllerVehiculos?guardar=concretarSalida" method="post" onsubmit="guardar.disabled = true; return true;">
                            <div class="form-row">
                                <div class="form-group col-md-4">
                                    <label>Salida</label>
                                    <select name='cve_salidas' id='cve_salidas' class='form-control' required="required">
                                        <option value="">Seleccionar...</option>
                                    </select>         
                                </div>
                                <div class="form-group col-md-4">
                                    <label>Vehiculo</label>
                                    <select name='vehiculo' id='vehiculo' class='form-control' required="required">
                                        <option value="">Seleccionar...</option>
                                    </select>         
                                </div>
                                <div class="form-group col-md-3">
                                    <label>Fecha, Editar Si Es Necesario</label>
                                    <input class="form-control" type="date" name='fecha' id="fecha"  required="required"/>
                                </div>
                                <div class="form-group col-md-3">
                                    <label>Asunto, Editar Si Es Necesario</label>
                                    <textarea id="asunto" class="form-control" name="asunto" rows="2" placeholder="Asuntos" required="required"></textarea>
                                </div>
                                <div class="form-group col-md-2">
                                    <label>Hora de Salida</label>
                                    <input id="hora_salida" class="form-control" type="time" name="hora_salida" required="required">
                                </div>
                                <div class="form-group col-md-2">
                                    <label>Hora de Entrada</label>
                                    <input id="hora_entrada" class="form-control" type="time" name="hora_entrada" placeholder="00:00 hrs" required="required">
                                </div>

                                <div class="form-group col-md-3">
                                    <label>Kilometro Inicial</label>
                                    <input id="k_inicial" class="form-control" type="number" step="0.01" min=".0000" name="k_inicial" placeholder="00.00 km" required="required">
                                </div>
                                <div class="form-group col-md-3">
                                    <label>Kilometro Final</label>
                                    <input id="k_final" class="form-control" name="k_final" type="number" step="0.01" min=".0000" placeholder="00:00 km" required="required">
                                </div>
                                <div class="form-group col-md-3">
                                    <label>Gasolina Inicial</label>
                                    <input id="g_inicial" class="form-control" name="g_inicial" type="number" step="0.01" min=".0000" placeholder="00.00 Litros" required="required">
                                </div>
                                <div class="form-group col-md-3">
                                    <label>Gasolina Final</label>
                                    <input id="g_final" class="form-control" name="g_final" type="number" step="0.01" min="0.0000" placeholder="00.00 Litros" required="required">
                                </div>
                                <div class="form-group col-md-3">
                                    <label>Conductor</label>
                                    <input id="conductor" class="form-control" type="text" name="conductor" placeholder="Nombre" required="required">
                                </div>
                                <div class="form-group col-md-3">
                                    <label>Usuarios</label>
                                    <textarea id="usuarios" class="form-control" name="usuarios" rows="2" placeholder="Usuarios" required="required"></textarea>
                                </div>
                                <div class="form-group col-md-3">
                                    <label>Destino</label>
                                    <textarea id="destino" class="form-control" name="destino" rows="2" placeholder="Destino" required="required"></textarea>
                                </div>
                                <div class="form-group col-md-3">
                                    <label>Observaciones</label>
                                    <textarea id="observaciones" class="form-control" name="observaciones" rows="2" placeholder="Observaciones" required="required"></textarea>
                                </div>
                                <div class="form-group col-md-3">
                                    <label>Estatus</label>
                                    <select name='status' id='status' class='form-control' required="required">
                                        <option value="">Seleccionar...</option>
                                        <option value="1">Sin concretar</option>
                                        <option value="2">Concretado</option>
                                    </select>
                                </div>
                            </div>

                            <div class="form-group" align="center">
                                <input type="button" name="Regresar" value="Regresar"  class="btn btn-dark" onClick="location.href = 'ControllerVehiculos?pagina=salidas'">
                                <button id="guardar" name="guardar"  class="btn btn-primary">Guardar</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <script>

    </script>

    <br>
    <footer class="site-footer">
        <div class="container" align="center">
            <div class="row pt-2 mt-2 text-center">
                <div class="col-md-12">
                    <img alt="firma blanco.png" height="173" src="imagen/firma_blanco.png" width="100">
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

    <script>
        var json = JSON.parse('${data}');
        var data = json;
        console.log(data);
        var jsonV = JSON.parse('${vehiculos}');
        var dataV = jsonV;
    </script>
    <script src="pages/js.main/concretarSalidas.js" type="text/javascript"></script>

</body>
</html>
<%
}else{
    response.sendRedirect("/serviciosgenerales/index.jsp");
}
%>