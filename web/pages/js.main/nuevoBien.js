$(document).ready(function () {
    document.getElementById("nuevoBien").reset();
});

function cambiarForm() {

    var tipoBien = document.getElementById("tipoBien").value;
    var activar = 'block';
    var desactivar = 'none';
    if (tipoBien !== "Seleccionar..." || tipoBien !== null) {
        if (tipoBien === '1') {
            document.getElementById("guardar").disabled = "";
            extintor(desactivar);
            impresoras(desactivar);
            camposDesactivadosPosiblemente();
            climas(activar);
        }
        if (tipoBien === '2') {
            document.getElementById("guardar").disabled = "";
            climas(desactivar);
            impresoras(desactivar);
            camposDesactivadosPosiblemente();
            extintor(activar);
        }
        if (tipoBien === '3') {
            document.getElementById("guardar").disabled = "";
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
}