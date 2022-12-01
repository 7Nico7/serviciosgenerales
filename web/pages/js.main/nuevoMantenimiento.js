


//Nombre archivo al Input
document.getElementById("evidenciaReporte").onchange = function () {
    var name = document.getElementById('evidenciaReporte').files[0].name;
    document.getElementById("archivoReporte").innerHTML = name;
};

//Nombre archivo al Input
document.getElementById("evidencia").onchange = function () {
    var name = document.getElementById('evidencia').files[0].name;
    document.getElementById("archivo").innerHTML = name;
};


//Obtiene los Numeros inventario
var inventario = [];
$.each(data, function (i, val) {
    inventario[i] = val.num_inventario;
});
//obtiene los Numeros de ID
var cve_bienes = [];
$.each(data, function (i, val) {
    cve_bienes[i] = val.cve_bienes;
    //   $select2.append($('<option />', {value: (i + 1), text: val.cve_bienes})
    //);
});

var tipo_bien = [];
$.each(data, function (i, val) {
    tipo_bien[i] = val.tipo_bien;
    //   $select3.append($('<option />', {value: (i + 1), text: val.tipo_bien})
    //);
});


//Seleccionar un Numero de Inventario se asigna el ID de Ese Bien
function  clicInventario() {
    var combo = document.getElementById("numInventario");
    var numInventario = combo.options[combo.selectedIndex].text;


    if (numInventario !== "Seleccionar..." || numInventario !== null) {
        for (var i = 0; i < inventario.length; i++) {
            if (inventario[i] === numInventario) {
                $("#cve_bienes").attr("readonly", true);
                //Se asigna el Id que pertenece el Numero de Inventario
                document.getElementById("cve_bienes").value = cve_bienes[i];
                //Se Muestra el campo Numero de Inventario
                document.getElementById("cve").style.display = 'block';
            }
        }
    }
}

//-------------------------
function climas(accion) {
    var camposRe = ['Cambio', 'tonnerCambiar', 'CambiarET'];
    //Ocultar campos
    for (var i = 0; i < camposRe.length; i++) {
        document.getElementById(camposRe[i]).style.display = accion;
        //$(camposRe[i]).prop('required', true);
    }
    document.getElementById('tonnerCambiados').required = "";
    document.getElementById('seCambio').required = "";

    var vaciar = ['tonnerCambiados', 'seCambio', 'cve_bienes', 'fechaMantenimiento', 'mantenimientObservacion'];
    for (var item in vaciar) {
        document.getElementById(vaciar[item]).value = "";
    }

}


//-------------------------
function extintor(accion) {
    var camposRe = ['fallaComun', 'tonnerCambiar'];

    for (var i = 0; i < camposRe.length; i++) {
        document.getElementById(camposRe[i]).style.display = accion;
        //$(camposRe[i]).prop('required', true);
    }

    document.getElementById("tonnerCambiados").required = "";
    document.getElementById("fallas").required = "";
    document.getElementById("CambiarET").innerHTML = "Se cambio el Extintor";
    var vaciar = ['tonnerCambiados', 'seCambio', 'cve_bienes', 'fechaMantenimiento', 'mantenimientObservacion', 'numInventario'];
    for (var item in vaciar) {
        document.getElementById(vaciar[item]).value = "";
    }
}
//--------------------------
function impresora(accion) {

    document.getElementById('fallaComun').style.display = accion;
    document.getElementById('fallas').required = "";
    var vaciar = ['seCambio', 'cve_bienes', 'fechaMantenimiento', 'mantenimientObservacion', 'numInventario'];
    for (var item in vaciar) {
        document.getElementById(vaciar[item]).value = "";
    }
    //$(camposRe[i]).prop('required', true);
    document.getElementById("CambiarET").innerHTML = "Se cambio el Tonner";
}
//-------------------------
function camposDesactivadosPosiblemente() {
    var camposVisibles = ['fechaMante', 'Cambio', 'tipoManteni', 'fallaComun', 'tonnerCambiar', 'manteObservacion',
        'evidenciaVista','evidenciaVista2', 'tipoManteni', 'CambiarET', 'NumInventario'];

    var camposRequiered = ["#fechaMantenimiento", "#seCambio", "#tipoMantenimiento", "#fallas"];

    for (var i = 0; i < camposVisibles.length; i++) {

        document.getElementById(camposVisibles[i]).style.display = 'block';

    }
    for (var i = 0; i < camposRequiered.length; i++) {
        // document.getElementById(camposRequiered[i]).required = "required";
        $(camposRequiered[i]).prop('required', true);
    }

}
//---------------------------------
function NumeroIventarios(tipo) {
    var $select = $('#numInventario');
    //Se se paran los Numeros de Inventarios
    $.each(data, function (i, val) {
        if (val.tipo_bien === tipo) {
            $select.append($('<option />', {value: (i + 1), text: val.num_inventario})
                    );
        }
    });
}

function cambiarForm() {
    var tipo = document.getElementById("tipoBien").value;
    var desactivar = 'none';

    if (tipo === '1') {
        camposDesactivadosPosiblemente();
        climas(desactivar);
        $("#numInventario").find('option').not(':first').remove();
        NumeroIventarios('Climas');

    } else if (tipo === '2') {
        camposDesactivadosPosiblemente();
        extintor(desactivar);
        $("#numInventario").find('option').not(':first').remove();
        NumeroIventarios('Extintores');

    } else if (tipo === '3') {
        camposDesactivadosPosiblemente();
        impresora(desactivar);
        $("#numInventario").find('option').not(':first').remove();
        NumeroIventarios('Impresoras');
    }

}
