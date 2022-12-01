<%
if(session.getAttribute("usuario") != null){
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<head>
    <!-- Required meta tags -->
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->

    <link rel="stylesheet" type="text/css" href="pages/css/bootstrap.css">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <link href="pages/css/piede.pagina.css" rel="stylesheet" type="text/css"/>
    <!-- JavaScript Bundle with Popper -->
    <script src="js/jquery-3.6.1.min.js" type="text/javascript"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-u1OknCvxWvY5kfmNBILK2hRnQC3Pr17a+RTT6rIHI7NnikvbZlHgTPOOmMi466C8" crossorigin="anonymous"></script>
    <link href="pages/css/sweetalert2.min.css" rel="stylesheet" type="text/css"/>
    <script src="js/sweetalert2.min.js" type="text/javascript"></script>
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
                    <h5 class="text-white">Registro de Mantenimiento</h5>
                </div>   
                ${data[1]}
                <div class="card-body">
                    <form action="ControllerBienes?guardar=mantenimiento" method="post" enctype="multipart/form-data" onsubmit="guardar.disabled = true; return true;">
                        <div class="form-row" >
                            <div class="form-group col-md-2">
                                <label>Tipo de Bien</label>
                                <select onclick="cambiarForm()" name='tipoBien' id='tipoBien' class='form-control' required="required">
                                    <option value=''>Seleccionar...</option>
                                    <option value='1'>Clima</option>
                                    <option value='2'>Extintor</option>
                                    <option value='3'>Impresora</option>
                                </select>                         
                            </div> 
                            <div class="form-group col-md-3" id="cve" style="display:none">
                                <label>Numero de Bien</label>
                                <input class="form-control" type="text" name="cve_bienes" id="cve_bienes" required="required">
                            </div> 
                            <div class="form-group col-md-4" id="NumInventario" style="display:none">
                                <label>Agregar Manteniemiento a Inventario</label>
                                <select onclick="clicInventario()" name='numInventario' id='numInventario' class='form-control' required="required">
                                    <option value="">Seleccionar...</option>
                                </select>                            
                            </div>  
                            <div class="form-group col-md-3" style="display:none" id="fechaMante">
                                <label>Fecha de Mantenimiento</label>
                                <input class="form-control" type="date" name='fechaMantenimiento' id="fechaMantenimiento"/>
                            </div>  
                            <div class="form-group col-md-3" id="Cambio" style="display:none">
                                <label id="CambiarET"></label>
                                <select name='seCambio' id='seCambio' class='form-control'>
                                    <option value="">Seleccionar...</option>
                                    <option value='1' >Si</option>
                                    <option value='2' >No</option>
                                </select>                            
                            </div> 
                            <div class="form-group col-md-2" id="tipoManteni" style="display:none">
                                <label>Mantenimiento</label>
                                <select name='tipoMantenimiento' id='tipoMantenimiento' class='form-control'>
                                    <option value="">Seleccionar...</option>
                                    <option value='1' >Predictivo</option>
                                    <option value='2' >Preventivo</option>
                                    <option value='3' >Correctivo</option>
                                </select>                            
                            </div> 
                            <div class="form-group col-md-3" id="fallaComun" style="display:none">
                                <label>Falla comun</label>
                                <select name='fallas' id='fallas' class='form-control'>
                                    <option value='' >Seleccionar...</option>
                                    <option value='1' >No enfria</option>
                                    <option value='2' >Falta de gas</option>
                                    <option value='3' >Fuga</option>
                                    <option value='4' >No enciende</option>
                                </select>                            
                            </div> 
                            <div class="form-group col-md-4" id="tonnerCambiar" style="display:none">
                                <label>Tornner Cambiados</label>
                                <textarea class="form-control" id="tonnerCambiados" name="tonnerCambiados"></textarea>
                            </div> 
                            <div class="form-group col-md-4" id="manteObservacion" style="display:none">
                                <label>Observaciones de Mantenimiento</label>
                                <textarea class="form-control" id="mantenimientObservacion" name="mantenimientObservacion"></textarea>
                            </div>
                            <div class="form-group col-md-3" id="evidenciaVista" style="display:none">
                                <label>Subir video</label>
                                <div class="custom-file">
                                    <input type="file" class="form-control-file custom-file-input" name="evidencia" id="evidencia" lang="es">
                                    <label for="evidencia" class="custom-file-label" id="archivo">Subir video</label>
                                </div>
                            </div>
                            <div class="form-group col-md-3" id="evidenciaVista2" style="display:none">
                                <label>Reporte de Evidencia</label>
                                <div class="custom-file">
                                    <input type="file" class="form-control-file custom-file-input" name="evidenciaReporte" id="evidenciaReporte" lang="es">
                                    <label for="evidenciaReporte" class="custom-file-label" id="archivoReporte">Subir archivo</label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group" align="center">
                            <input type="button" name="Regresar" value="Regresar"  class="btn btn-dark" onClick="location.href = 'ControllerBienes?selecciono=Mantenimientos&Tipo=todos'">
                            <button id="guardar" name="guardar" class="btn btn-primary">Guardar</button>
                        </div>
                    </form>    
                </div>
            </div>
        </div>
    </div>
    <div>
        <section class="conten">
        </section>
    </div>
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
                    <strong>Coordinación General de Modernización Administrativa e Innovación Gubernamental</strong>
                </div>
            </div>
        </div>
    </footer>


    <script>var json = '${data[0]}';
        var data = JSON.parse(json);
        console.log(data);
    </script>
    <script src="pages/js.main/nuevoMantenimiento.js" type="text/javascript"></script>
</body>
</html>
<%
}else{
    response.sendRedirect("/serviciosgenerales/index.jsp");
}
%>