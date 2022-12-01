<%
if(session.getAttribute("usuario") == null){
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Servicios Generales</title>
        <link rel="stylesheet" type="text/css" href="pages/css/bootstrap.css">
        <link rel="stylesheet" type="text/css" href="pages/css/piede.pagina.css">
        <script src="js/jquery-3.6.1.min.js" type="text/javascript"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-u1OknCvxWvY5kfmNBILK2hRnQC3Pr17a+RTT6rIHI7NnikvbZlHgTPOOmMi466C8" crossorigin="anonymous"></script>
        <link href="pages/css/sweetalert2.min.css" rel="stylesheet" type="text/css"/>
        <script src="js/sweetalert2.min.js" type="text/javascript"></script>
        <link href="pages/css/gif.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
            <a class="navbar-brand" href="index.jsp"></a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
                    aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>

            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav mr-auto">
                    <li class="nav-item">
                        <a class="nav-link" href="home.jsp">CGMAIG <span class="sr-only">(current)</span></a>
                    </li>
                </ul>
            </div>
        </nav>
        <div class="container">
            <br>
            <div class="row">
                <div class="col-md-4">
                </div>
                <div class="col-md-4">
                    <div class="card">
                        <div align="center" class="card-body">
                            <h1 class="card-title">Iniciar sesion</h1>

                            <img src="imagen/escudo_de_armas.png" />               

                            <form action="Validar?accion=ingresar" method="POST" onsubmit="accion.disabled = true; return true;">
                                <div class="form-group">
                                    <input onkeyup="validarEmail(this, 'resultadoIniciar', 'iniciar')" type="email" class="form-control" placeholder="Correo" name="correo" required id="correIniciar">
                                    <a style="color: red" id="resultadoIniciar"></a>
                                </div>
                                <div class="form-group" align="center">
                                    <input type="password" class="form-control" placeholder="Contraseña" name="contra" required>
                                    <a href="?page=recuperar" class="btn">Recuperar contraseña</a>
                                </div>
                                <div class="form-group" align="center">
                                    <button name="accion"  class="btn btn-primary" id="iniciar">Ingresar</button>
                                    <div>
                                        <a class="btn" style="color:#007bff" data-bs-toggle="modal" data-bs-target="#exampleModal">Crear cuenta</a>
                                    </div>
                                </div>
                            </form>
                            ${msje} 
                        </div>
                    </div>
                </div>
            </div>
            <br>
        </div>
        <div>
            <section class="conten">
            </section>
        </div>

        <!-- Modal -->
        <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">Registrar Usuario</h5>
                    </div>
                    <div class="modal-body">
                        <form>
                            <div class="form-group">
                                <input type="text" class="form-control" placeholder="Nombre Completo" name="nombre" id="nombre" required="required">
                            </div>
                            <div class="form-group">
                                <input onkeyup="validarEmail(this, 'invalido_registrar', 'registrar')" type="email" class="form-control" placeholder="Correo" name="correo" id="correo">
                                <a id="invalido_registrar" style="color: red"></a>
                            </div>
                            <div class="form-group" align="center">
                                <input type="password" class="form-control" placeholder="Contraseña" name="contra" id="contra">

                            </div>

                        </form>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-dark" data-bs-dismiss="modal" id="cancelar">Cancelar</button>
                            <button class="btn btn-primary" onclick="Guardar()" id="registrar">Guardar</button>
                        </div>
                    </div>

                </div>
            </div>
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
                        <strong>Coordinaci&oacute;n General de Modernizaci&oacute;n Administrativa e Innovaci&oacute;n Gubernamental</strong>
                    </div>
                </div>
            </div>
        </footer>
        <script src="pages/js.main/index.js" type="text/javascript"></script>
    </body>
</html>
<%
}else{  
 response.sendRedirect("/serviciosgenerales/principal.jsp");
}
%>
