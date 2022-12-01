<%
if(session.getAttribute("usuario") != null){
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Servicios Generales</title>
        <link href="css/bootstrap.css" rel="stylesheet" type="text/css"/>
        <script src="../js/jquery-3.6.1.min.js" type="text/javascript"></script>
    </head>
    <body>
        <div id="contenedor_evidencia" align="center"></div>
        
        <script src="js.main/evidencia.js" type="text/javascript"></script>
    </body>
</html>
<%
}else{
    response.sendRedirect("/serviciosgenerales/index.jsp");
}
%>