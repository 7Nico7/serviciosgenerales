
const valores = window.location.search;
const urlParams = new URLSearchParams(valores);
//Accedemos a los valores
var video = urlParams.get('video');
var pdf = urlParams.get("pdf");
var content;

if (video !== null && video !== "nulo" && video !== "" && video !== " ") {
    content = "<br><div class='container'>" +
            "<div class='card'>" +
            "<div class='card-body'>" +
            "<video width='900' height='500' controls>" +
            "<source id='evidencia' src = 'evidencias/" + video + "' type='video/mp4'>" +
            "Your browser does not support the <code>video</code> tag." +
            "</video>" +
            "</div></div></div>";
} else if (pdf !== null && pdf !== "nulo" && pdf !== " ") {
    content = "<div class='embed-responsive embed-responsive-16by9'>" +
            "<iframe class='embed-responsive-item' src='evidencias/" + pdf + "'></iframe>" +
            "</div>";
} else {
   content = "<h1 class='text-dark'>No hay evidencia</h1>";
}


$('#contenedor_evidencia').append(content);