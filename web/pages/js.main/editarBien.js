$(document).ready(function () {

    var cambiar;
   
    switch (tipo) {
        case 'Climas':
            cambiar = '1';
            break;
        case 'Extintores':
            cambiar = '2';
            break;
        case 'Impresoras':
            cambiar = '3';
            break;
        default:
            cambiar = tipo;
    }

    var status;
    switch (estado) {
        case  'Activo':
            status = 1;
            break;
        case 'Inactivo':
            status = 2;
            break;
        default:
            status = estado;
    }
    document.getElementById("id").disabled = true;
    document.getElementById("id").value = id;

    document.getElementById("tipoBien").value = cambiar;
    //document.getElementById("tipoBien").disabled = "true";
    document.getElementById("objetoDescripcion").value = objetoDescripcion;
    document.getElementById("marca").value = marca;
    document.getElementById("modelo").value = modelo;
    document.getElementById("serie").value = serie;
    document.getElementById("inventario").value = inventario;
    document.getElementById("fechaFactura").value = fechaFactura;
    document.getElementById("fechaAltaSistema").value = fechaAltaSistema;
    document.getElementById("ubicacionTxt").value = ubicacionTxt;
    document.getElementById("responsable").value = responsable;
    document.getElementById("tonner").value = tonner;
    document.getElementById("dependencia").value = dependencia;
    document.getElementById("departamento").value = departamento;
    document.getElementById("area").value = area;
    document.getElementById("ubicacionObservacion").value = ubicacionObservacion;
    document.getElementById("status").value = status;

    var tipoBien = document.getElementById("tipoBien").value;
    var activar = 'block';
    var desactivar = 'none';

    CamposBien(tipoBien, activar, desactivar);




});
function TipoBien() {
    var tipoBien = document.getElementById("tipoBien").value;
    var activar = 'block';
    var desactivar = 'none';
    CamposBien(tipoBien, activar, desactivar);
}

function CamposBien(tipoBien, activar, desactivar) {
    if (tipoBien === '1') {
        document.getElementById("actualizar").disabled = "";
        extintor(desactivar);
        impresoras(desactivar);
        camposDesactivadosPosiblemente();
        climas(activar);
    }
    if (tipoBien === '2') {
        document.getElementById("actualizar").disabled = "";
        climas(desactivar);
        impresoras(desactivar);
        camposDesactivadosPosiblemente();
        extintor(activar);
    }
    if (tipoBien === '3') {
        document.getElementById("actualizar").disabled = "";
        climas(desactivar);
        impresoras(desactivar);
        camposDesactivadosPosiblemente();
        impresoras(activar);
    }

}

function climas(accion) {
    var campos = ['Odescripcion', 'marc', 'model', 'numSerie', 'numInventario', 'fechaF',
        'fechaI', 'ubica', 'respon', 'depend', 'departame', 'ubicObser', 'areaO', 'estado'];
    for (var i = 0; i < campos.length; i++) {
        document.getElementById(campos[i]).style.display = accion;
    }
    document.getElementById("tonner").required = "";
    document.getElementById("fechaFactura").required = "";
}

function impresoras(accion) {
    var campos = ['marc', 'model', 'numSerie', 'numInventario', 'ubica', 'respon', 'estado', 'toner'];
    for (var i = 0; i < campos.length; i++) {
        document.getElementById(campos[i]).style.display = accion;
    }
    var desactivarRequired = ['objetoDescripcion', 'fechaFactura', 'fechaAltaSistema',
        'dependencia', 'departamento', 'ubicacionObservacion', 'area'];
    for (var i = 0; i < desactivarRequired.length; i++) {
        document.getElementById(desactivarRequired[i]).required = "";
    }
}

function extintor(accion) {
    var campos = ['Odescripcion', 'marc', 'model', 'numInventario', 'respon', 'ubica', 'estado'];
    var desactivarRequired = ['serie', 'fechaFactura', 'fechaAltaSistema', 'tonner', 'dependencia', 'departamento',
        'ubicacionObservacion', 'area'];
    for (var i = 0; i < campos.length; i++) {
        document.getElementById(campos[i]).style.display = accion;
    }
    for (var i = 0; i < desactivarRequired.length; i++) {
        document.getElementById(desactivarRequired[i]).required = "";
    }
}


function camposDesactivadosPosiblemente() {
    var camposRe = ["objetoDescripcion", "marca", "modelo", "serie", "area", "inventario", "fechaFactura", "fechaAltaSistema", "ubicacionTxt", "responsable", "dependencia", "departamento", "ubicacionObservacion", "status", "tonner"];
    for (var i = 0; i < camposRe.length; i++) {
        document.getElementById(camposRe[i]).required = "required";
    }
}
