<%
if(session.getAttribute("usuario") != null){
%>
<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"pageEncoding="ISO-8859-1"%>
<html>
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Servicios Generales</title>
        <!-- Bootstrap CSS -->
        <link href="pages/css/bootstrap.css" rel="stylesheet" type="text/css"/>
        <link href="pages/css/piede.pagina.css" rel="stylesheet" type="text/css"/>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-u1OknCvxWvY5kfmNBILK2hRnQC3Pr17a+RTT6rIHI7NnikvbZlHgTPOOmMi466C8" crossorigin="anonymous"></script>
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <script src="js/jquery-3.6.1.min.js" type="text/javascript"></script>
        <link href="pages/css/gif.css" rel="stylesheet" type="text/css"/>
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
                        <h5 class="text-white">Editar Mantenimiento</h5>
                    </div>  
                    ${mensaje}
                    <div class="card-body">
                        <form action="ControllerEditar?Editar=Mantenimiento" method="post" enctype="multipart/form-data" onsubmit="actualizar.disabled = true; return true;">
                            <div class="form-row" >
                                <div class="form-group col-md-3">
                                    <label>Tipo de Bien</label>
                                    <select onchange="changeFunc(this.value);"  name='tipoBien' id='tipoBien' class='form-control' required="required">
                                        <option value=''>Seleccionar...</option>
                                        <option value='1'>Clima</option>
                                        <option value='2' >Extintor</option>
                                        <option value='3' >Impresora</option>
                                    </select>                         
                                </div> 
                                <div class="form-group col-md-2" id="cve">
                                    <label>Numero de Bien</label>
                                    <input class="form-control" type="text" name="cve_bienes" id="cve_bienes" required="required">
                                </div> 
                                <div class="form-group col-md-2" id="cve">
                                    <label>No.Mantenimiento</label>
                                    <input class="form-control" type="text" name="num_mantenimiento" id="num_mantenimiento" required="required">
                                    <input type="hidden" name="num_mantenimiento" id="num_mantenimiento" >
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
                                <div class="form-group col-md-2" id="Cambio" style="display:none">
                                    <label id="CambiarET"></label>
                                    <select name='seCambio' id='seCambio' class='form-control'>
                                        <option value=''>Seleccionar...</option>
                                        <option value='1' >Si</option>
                                        <option value='2' >No</option>
                                    </select>                            
                                </div> 
                                <div class="form-group col-md-2" id="tipoManteni" style="display:none">
                                    <label>Mantenimiento</label>
                                    <select name='tipoMantenimiento' id='tipoMantenimiento' class='form-control'>
                                        <option value=''>Seleccionar...</option>
                                        <option value='1' >Predictivo</option>
                                        <option value='2' >Preventivo</option>
                                        <option value='3' >Correctivo</option>
                                    </select>                            
                                </div> 
                                <div class="form-group col-md-2" id="fallaComun" style="display:none">
                                    <label>Falla comun</label>
                                    <select name='fallas' id='fallas' class='form-control'>
                                        <option value='' >Seleccionar...</option>
                                        <option value='1' >No enfria</option>
                                        <option value='2' >Falta de Gas</option>
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
                                        <label for="evidencia" class="custom-file-label" id="archivo">Archivo</label>
                                        <input type="text" class="form-control" name="evidenciaEliminar" id="evidenciaEliminar" oncopy="return false;">
                                    </div>
                                </div>
                                <div class="form-group col-md-3" id="evidenciaVista2" style="display:none">
                                    <label>Reporte de Evidencia</label>
                                    <div class="custom-file">
                                        <input type="file" class="form-control-file custom-file-input" name="evidenciaReporte" id="evidenciaReporte" lang="es">
                                        <label for="evidenciaReporte" class="custom-file-label" id="archivoReporte">Archivo</label>
                                        <input type="text" class="form-control" name="evidenciaReporteEliminar" id="evidenciaReporteEliminar" oncopy="return false;">
                                    </div>
                                </div>
                            </div>
                            <div class="form-group" align="center">
                                <input type="button" name="Regresar" value="Regresar"  class="btn btn-dark" onClick="location.href = 'ControllerBienes?selecciono=Mantenimientos&Tipo=todos'">
                                <button id="actualizar" name="actualizar" class="btn btn-primary">Actualizar</button>
                            </div>
                        </form>  
                    </div>
                </div>
            </div>
        </div>
    <BODY onload="sinVueltaAtras();" onpageshow="if (event.persisted) sinVueltaAtras();" onunload="">

        <script>
            var datos = (${M});
            var data = JSON.parse('${data}');
        </script>
        <script src="pages/js.main/editarMantenimiento.js" type="text/javascript"></script>
    </body>
</html>
<%
}else{
    response.sendRedirect("/serviciosgenerales/index.jsp");
}
%>