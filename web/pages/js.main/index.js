
// Validar Campos de Email
function validarEmail(elemento, mensaje, boton) {
    var texto = document.getElementById(elemento.id).value;
    // Expresion regular
    var expresion = /^[-\w.%+]{1,64}@(?:[A-Z0-9-]{1,63}\.){1,125}[A-Z]{2,63}$/i;
    //Validar si el Texto no pertenece a la Expresión
    if (!expresion.test(texto)) {
        //Desactiva el voton que se resive
        document.getElementById(boton).disabled = true;
        //Se envia el texto a la Etiqueta
        document.getElementById(mensaje).innerHTML = "Correo invalido";
    } else {
        //Se activa el boton y se quita el Mensaje si El Correo es correcto
        document.getElementById(boton).disabled = false;
        document.getElementById(mensaje).innerHTML = "";
    }
}
//Guarda el Usuario
function Guardar() {
    $("#cancelar").prop('disabled', false);
    $("#registrar").prop('disabled', false);
    //Formulario para el Registro de Usuario
    var nombre = document.getElementById('nombre').value;
    var correo = document.getElementById('correo').value;
    var contra = document.getElementById('contra').value;
    var datos = 'accion=registrar' + '&nombre=' + nombre + '&correo=' + correo + '&contra=' + contra;
//valida los campos
    if (nombre === "") {
        alert("Complete el campo nombre");
    } else if (correo === "") {
        alert("Complete el campo Correo");
    } else if (contra === "") {
        alert("Complete el campo Contraseña");
    } else {
        enviar(datos);
        Limpiar();
    }
}
//Envio de los datos para el Registro del Usuario
function enviar(datos) {
    $.ajax({
        url: "Validar", type: 'POST', data: datos,
        success: function (data) {
            swal('Mensaje del sistema', data.trim(), 'success');
            ("#cancelar").prop('disabled', true);
            ("#registrar").prop('disabled', true);
        }, error: function (xml, data) {
            swal('Error!', 'ah ocurrido un error en la base de datos', 'error');
            ("#cancelar").prop('disabled', true);
            ("#registrar").prop('disabled', true);
        }
    });
}
// Limpia caja de texto del Formulario Para un Nuevo Usuario
function Limpiar() {
    $('#nombre').val("");
    $('#correo').val("");
    $('#contra').val("");
}
