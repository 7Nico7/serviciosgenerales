<%
if(session.getAttribute("usuario") != null){
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- Bootstrap CSS -->

    <link rel="stylesheet" type="text/css" href="pages/css/bootstrap.css">
    <link href="pages/css/piede.pagina.css" rel="stylesheet" type="text/css"/>
    <!-- JavaScript Bundle with Popper -->
    <script src="js/jquery-3.6.1.min.js" type="text/javascript"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-u1OknCvxWvY5kfmNBILK2hRnQC3Pr17a+RTT6rIHI7NnikvbZlHgTPOOmMi466C8" crossorigin="anonymous"></script>
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
                    <h5 class="text-white">Registro de Bienes</h5>
                </div>   
                ${mensaje}
                <div class="card-body">
                    <form id="nuevoBien" action="ControllerBienes?guardar=bien" method="post" onsubmit="guardar.disabled = true; return true;">
                        <div class="form-row" >
                            <div class="form-group col-md-3">
                                <label>Seleccione el tipo de Bien</label>
                                <select onclick="cambiarForm()" name='tipoBien' id='tipoBien' class='form-control' required="required">
                                    <option value=''>Seleccionar...</option>
                                    <option value='1'>Clima</option>
                                    <option value='2'>Extintor</option>
                                    <option value='3'>Impresora</option>
                                </select>                            
                            </div> 
                            <div class="form-group col-md-3" id="Odescripcion" style="display:none" >
                                <label>Descripción</label>
                                <input class="form-control" type="text" name='objetoDescripcion' id="objetoDescripcion"/>
                            </div>
                            <div class="form-group col-md-3" style="display:none" id="marc">
                                <label>Marca</label>
                                <input class="form-control" type="text" name='marca' id='marca'/>
                            </div>
                            <div class="form-group col-md-3" style="display:none" id="model">
                                <label>Modelo</label>
                                <input class="form-control" type="text" id='modelo'name='modelo'/>
                            </div>
                            <div class="form-group col-md-3" style="display:none" id="numSerie">
                                <label>Numero de serie</label>
                                <input class="form-control" type="text" name='serie' id="serie"/>
                            </div>
                            <div class="form-group col-md-3" style="display:none" id="numInventario">
                                <label>Numero de inventario</label>
                                <input class="form-control" type="text" name='inventario' id="inventario"/>
                            </div>
                            <div class="form-group col-md-3" style="display:none" id="fechaF">
                                <label>Fecha de Factura</label>
                                <input class="form-control" type="date" name='fechaFactura' id="fechaFactura"/>
                            </div>     
                            <div class="form-group col-md-3" style="display:none" id="fechaI">
                                <label>Fecha de instalación</label>
                                <input class="form-control" type="date" name='fechaAltaSistema' id="fechaAltaSistema"/>
                            </div>               
                            <div class="form-group col-md-3" style="display:none" id="ubica">
                                <label>Ubicación</label>
                                <input class="form-control" type="text" name='ubicacionTxt' id="ubicacionTxt"/>
                            </div>
                            <div class="form-group col-md-3" style="display:none" id="respon">
                                <label>Responsable</label>
                                <input class="form-control" type="text" name='responsable' id="responsable"/>
                            </div>
                            <div class="form-group col-md-3" style="display:none" id="toner">
                                <label>Tonners</label>
                                <textarea class="form-control" id="tonner" name="tonner"></textarea>
                            </div>
                            <div class="form-group col-md-3" style="display:none" id="depend">
                                <label>Dependencia</label>
                                <input class="form-control" type="text" required="required" id='dependencia' name='dependencia'/>
                            </div>
                            <div class="form-group col-md-3" style="display:none" id="departame">
                                <label>Departemento</label>
                                <input class="form-control" type="text" name='departamento' id="departamento"/>
                            </div>
                            <div class="form-group col-md-2" style="display:none" id="areaO">
                                <label>Área</label>
                                <input class="form-control" type="text" name='area' id="area"/>
                            </div>
                            <div class="form-group col-md-4" style="display:none" id="ubicObser">
                                <label>Observaciones de Ubicación</label>
                                <textarea class="form-control" id="ubicacionObservacion" name="ubicacionObservacion"></textarea>
                            </div>
                            <div class="form-group col-md-2" style="display:none" id="estado">
                                <label>Status</label>
                                <select align="center" name='status' id="status" class="form-control">
                                    <option value=''>Seleccionar...</option>
                                    <option value="1">Activo</option>
                                    <option value="2" >Inactivo</option>                            
                                </select>
                            </div>
                        </div>
                        <div class="form-group" align="center">
                            <input type="button" name="Regresar" value="Regresar"  class="btn btn-dark" onClick="location.href = 'ControllerBienes?selecciono=Bienes&Tipo=todos'">
                            <button disabled="true" id="guardar" name="guardar"  class="btn btn-primary" required="required">Guardar</button>
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
                    <strong>Coordinación General de Modernización Administrativa e Innovación Gubernamental</strong>
                </div>

            </div>

        </div>
    </footer>

    <script src="pages/js.main/nuevoBien.js"></script>
</body>
</html>
<%
}else{
    response.sendRedirect("/serviciosgenerales/index.jsp");
}
%>