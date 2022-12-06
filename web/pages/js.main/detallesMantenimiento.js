

        var datosTabla;
        if (bien.tipo_bien === "Climas") {
datosTabla = [bien.cve_bienes, bien.cve_mantenimiento, bien.tipo_bien, bien.num_inventario, bien.fallas, bien.tipoMantenimiento, bien.mant_descripcion, bien.fecha_manteni,
        bien.proximo_mantenimiento, bien.dias_restantes];
        nombre = ["Numero de Bien", "Numero de Mantenimiento", "Tipo de Bien", "Inventario", "Fallas", "Tipo de Mantenimiento", "Descripción de Mantenimiento", "Fecha de Mantenimiento",
                "Proximo Mantenimiento", "Dias restantes"];
        }

if (bien.tipo_bien === "Extintores") {
datosTabla = [bien.cve_bienes, bien.cve_mantenimiento, bien.tipo_bien, bien.num_inventario, bien.serie, bien.tipoMantenimiento, bien.mant_descripcion, bien.fecha_manteni,
        bien.proximo_mantenimiento, bien.dias_restantes];
        nombre = ["Numero de Bien", "Numero de Mantenimiento", "Tipo de Bien", "Inventario", "Serie", "Tipo de Mantenimiento", "Descripción de Mantenimiento", "Fecha de Mantenimiento",
                "Proximo Mantenimiento", "Dias restantes"];
        }

if (bien.tipo_bien === "Impresoras") {
datosTabla = [bien.cve_bienes, bien.cve_mantenimiento, bien.tipo_bien, bien.num_inventario, bien.cambioET, bien.tonnersCambiados,
        bien.tipoMantenimiento, bien.mant_descripcion, bien.fecha_manteni,
        bien.proximo_mantenimiento, bien.dias_restantes];
        nombre = ["Numero de Bien", "Numero de Mantenimiento", "Tipo de Bien", "Inventario", "Se cambio Tornners", "Torners Cambiados",
                "Tipo de Mantenimiento", "Descripción de Mantenimiento", "Fecha de Mantenimiento",
                "Proximo Mantenimiento", "Dias restantes"];
        }


var content = " <table id='infoBienes' class='table table-bordered table-responsive'>";
        var cont = 0;
        var numero = esPar(datosTabla.length);
        function esPar(numero)
                {
                return ((numero % 2) === 0) ? true : false;
                        }

        var control = (datosTabla.length / 2) * 2;
                for (var i = 0; i < (datosTabla.length / 2); i++) {

        content += "<tr><th scope='row'>" + nombre[cont] + "</th>" +
                "<td>" + datosTabla[cont] + "</td>";
                cont++;
                if (numero === true) {
        content += "<th scope='row'>" + nombre[cont] + "</th>" +
                "<td>" + datosTabla[cont] + "</td>" + "</tr>";
                cont++;
        } else if (numero === false) {
        if (control === cont) {
        content += "<th scope='row'>" + " " + "</th>" +
                "<td>" + " " + "</td>" + "</tr>";
        } else {
        content += "<th scope='row'>" + nombre[cont] + "</th>" +
                "<td>" + datosTabla[cont] + "</td>" + "</tr>";
                cont++;
        }
        }

        }
        content += "</table>";
                //document.getElementById('generaTabla').innerHTML = content;
//--------------------------

                $(function () {
                $("#Excel").click(function (e) {
                var table = $("#infoBienes");
                        if (table && table.length) {
                $(table).table2excel({
                exclude: ".noExl",
                        name: "Excel Document Name",
                        filename: "${bien.tipo_bien}" + new Date().toISOString().replace(/[\-\:\.]/g, "") + ".xls",
                        fileext: ".xls",
                        exclude_img: true,
                        exclude_links: true,
                        exclude_inputs: true,
                        preserveColors: true
                });
                }
                });
                        });
                $("#crear").click(function () {
        var doc = new jsPDF();
                // var imgData = new Image();
                // imgData.src = "imagen/SAIG.jpeg";
                // doc.addImage(imgData, 20, 40, 100, 100);

                doc.setFontSize(30);
                doc.setTextColor(255, 0, 0);
                doc.text(40, 20, "SMAIG");
                doc.setFontSize(14);
                doc.setTextColor(0, 0, 0);
                doc.text(20, 30, "Bien numero ");
                // doc.addImage(imgData, 'JPEG', 10, 40, 180, 180);
                // imgData.onload = function () {
                doc.save("mipdf.pdf");
                //};
                });
                var titulo = "Mantenimintos del Bien No." + bien.cve_bienes;
                /*----------------------------DataTable----------------------------------*/

                $(document).ready(function () {

//SE genera la Tabla 
        var table = $('#DetallesBienes').DataTable({
        responsive: true,
                order: [[5, 'asc']],
                language: {
                url: "//cdn.datatables.net/plug-ins/1.10.15/i18n/Spanish.json"
                },
                dom: "Bfrtip",
                buttons: {
                dom: {
                button: {
                className: 'btn'
                }
                },
                        buttons: [
                        {
                        //definimos estilos del boton de excel
                        extend: "excel",
                                text: 'Exportar a Excel',
                                className: 'btn btn-sm btn-success',
                                filename: titulo,
                                // configuración insertCells
                                insertCells: [
                                {
                                cells: '1', // Target cell B3
                                        content: titulo // without pushCol or pushRow defined, the cell
                                }
                                ],
                                excelStyles: [
                                {// Add an excelStyles definition
                                cells: "2", // adonde se aplicaran los estilos (fila 2)
                                        style: {// The style block
                                        font: {// Style the font
                                        name: "Arial", // Font name
                                                size: "14", // Font size
                                                color: "FFFFFF", // Font Color

                                                b: true // negrita SI
                                        },
                                                fill: {// Estilo de relleno (background)
                                                pattern: {// tipo de rellero (pattern or gradient)
                                                color: "b38e5d" // color de fondo de la fila
                                                }
                                                }
                                        }
                                },
                                {// Add an excelStyles definition
                                cells: "1", // adonde se aplicaran los estilos (fila 2)
                                        style: {// The style block
                                        font: {// Style the font
                                        name: "Arial", // Font name
                                                size: "14", // Font size
                                                color: "FFFFFF", // Font Color

                                                b: true // negrita SI
                                        },
                                                fill: {// Estilo de relleno (background)
                                                pattern: {// tipo de rellero (pattern or gradient)
                                                color: "9D2449" // color de fondo de la fila
                                                }
                                                },
                                                // Alignment Object
                                                alignment: {
                                                vertical: "center",
                                                        horizontal: "center"
                                                        //wrapText: true
                                                }
                                        }
                                }
                                ],
                                //Columnas que se exportan al archivo
                                exportOptions: {
                                columns: (bien.tipo_bien === "Extintores") ? [0, 1, 2, 4, 5, 6, 7, 9] : (bien.tipo_bien === "Impresoras") ?
                                [0, 1, 2, 4, 5, 6, 7, 8, 9] : [0, 1, 2, 3, 4, 5, 6, 7, 9]
                                }
                        },
                        {
                        //definimos estilos del boton PDF
                        extend: "pdf",
                                text: 'Exportar a pdf',
                                className: 'btn btn-sm btn-danger',
                                filename: titulo,
                                download: 'open',
                                title: function () {
                                var searchString = table.search();
                                        return searchString.length ? "Search: " + searchString : titulo;
                                },
                                exportOptions: {
                                columns: (bien.tipo_bien === "Extintores") ? [0, 1, 2, 4, 5, 6, 7, 9] : (bien.tipo_bien === "Impresoras") ?
                                [0, 1, 2, 4, 5, 6, 7, 8, 9] : [0, 1, 2, 3, 4, 5, 6, 7, 9]
                                }
                        },
                        {
                        //definimos estilos del boton de Imprimir
                        extend: "print",
                                text: 'Imprimir',
                                className: 'btn btn-sm btn-info',
                                filename: titulo,
                                title: function () {
                                var searchString = table.search();
                                        return searchString.length ? "Search: " + searchString : titulo;
                                },
                                exportOptions: {
                                columns: (bien.tipo_bien === "Extintores") ? [0, 1, 2, 4, 5, 6, 7, 9] : (bien.tipo_bien === "Impresoras") ?
                                [0, 1, 2, 4, 5, 6, 7, 8, 9] : [0, 1, 2, 3, 4, 5, 6, 7, 9]
                                }
                        }
                        ]
                },
                data : datos,
                //Datos de la Tabla
                columns: [
                {data: 'cve_bienes'},
                {data: 'cve_mantenimiento'},
                {data: 'tipoMantenimiento'},
                {data: 'fallas'},
                {data: 'fecha_manteni'},
                {data: 'proximo_mantenimiento'},
                {data: 'dias_restantes'},
                {data: 'cambioET'},
                {data: 'tonnersCambiados'},
                {data: 'mant_descripcion'},
                {data: 'nombre_archivo'},
                {data: 'nombre_archivo_reporte'},
                {defaultContent: "<button class='btn btn-sm btn-success btnEvidencia'><span class='material-icons'>smart_display</span></button>\n\
                              <button class='btn btn-sm btn-primary btnEvidencia2'><span class='material-icons'>picture_as_pdf</span></button>"}

                ]
                });
//boton evidencia Video
                        $("#DetallesBienes tbody").on('click', '.btnEvidencia', function () {
                let data = table.row($(this).parents()).data();
                        var archivo = data.nombre_archivo; //capturo el ID 

                        if (archivo === null || archivo === " ") {
                archivo = "nulo";
                }

                window.open('pages/evidencia.jsp?video=' + archivo, '_blank');
                });
                        //boton evidencia Reporte
                        $("#DetallesBienes tbody").on('click', '.btnEvidencia2', function () {
                let data = table.row($(this).parents()).data();
                        var archivo = data.nombre_archivo_reporte; //capturo el ID 

                        if (archivo === null || archivo === " ") {
                archivo = "nulo";
                }

                window.open('pages/evidencia.jsp?pdf=' + archivo, '_blank');
                });
//OCULTAR FILAS
                        const fila_falla = table.column(3);
                        const fila_tonnersCambiados = table.column(8);
                        const num_bien = table.column(0);
                        num_bien.visible(!num_bien.visible());
                        if (bien.tipo_bien === "Extintores") {
                fila_falla.visible(!fila_falla.visible()); // true or false
                        fila_tonnersCambiados.visible(!fila_tonnersCambiados.visible()); // true or false
                }

                if (bien.tipo_bien === "Impresoras") {
                fila_falla.visible(!fila_falla.visible()); // true or false
                }

                if (bien.tipo_bien === "Climas") {
                fila_tonnersCambiados.visible(!fila_tonnersCambiados.visible()); // true or false
                }

                });