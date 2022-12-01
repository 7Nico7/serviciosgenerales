package Controller;

import Model.Bienes;
import Model.GetBienes_Id;
import Model.ActualizarBien;
import Model.ActualizarCFE;
import Model.ActualizarMantenimiento;
import Model.GetMantenimiento_Id;
import Model.GetMedidor;
import Model.GetRecibo;
import Model.Get_Id_Inventarios_Bienes;
import Model.Mantenimientos;
import Model.Medidor;
import Model.Recibo;
import Model.isNumeric;
import com.google.gson.Gson;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.util.StringUtil;

@MultipartConfig
@WebServlet(name = "ControllerEditar", urlPatterns = {"/ControllerEditar"})
public class ControllerEditar extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //response.setContentType("text/html;charset=UTF-8");

    }

    //Redireciona a la pagina Editar con los datos del Elemento
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
     
        //Obtiene lo que se va editar, para redireccionar a la Pagina.
        String editar = request.getParameter("Editar");
        switch (editar) {
            case "Bien":
                paginaEditarBien(request, response);
                break;
            case "Mantenimiento":
                paginaEditarMantenimiento(request, response);
                break;
            case "Medidor":
                paginaEditarMedidor(request, response);
                break;
            case "Recibo":
                paginaEditarRecibo(request, response);
                break;
            default:
                request.setAttribute("error", "Error: Nose reconoce el valor de Editar metodo GET Editar = " + editar);
                request.getRequestDispatcher("pages/Error.jsp").forward(request, response);
        }
    }

    //Metodo POST Recibe los datos para actualizar
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
           request.setCharacterEncoding("UTF-8");
        //Obtiene a lo que se va editar
        String editar = request.getParameter("Editar");
        if (!StringUtil.isBlank(editar)) {
            switch (editar) {
                case "Bien":
                    ActualizarBien(request, response);
                    break;
                case "Mantenimiento":
                    ActualizarMantenimiento(request, response);
                    break;
                case "Medidor":
                    ActualizarMedidor(request, response);
                    break;
                case "Recibo":
                    ActualizarRecibo(request, response);
                    break;
                default:
                    request.setAttribute("error", "Error: Nose Reconose el valor de Editar metodo POST Editar= " + editar);
                    request.getRequestDispatcher("pages/Error.jsp").forward(request, response);
            }
        } else {
            request.setAttribute("error", "Error: El Parametro Editar es NULO");
            request.getRequestDispatcher("pages/Error.jsp").forward(request, response);
        }

    }

    private void ActualizarBien(HttpServletRequest request, HttpServletResponse response) {
        Bienes bien = new Bienes();
        //Se envian los parametros request, donde se obtiene el bien
        bien = bien.Bien(request);

        ActualizarBien actualiza = new ActualizarBien();
        //Actualiza el bien y Retorna un Mensaje
        String mensaje = actualiza.Actualizar(bien);
        //Se envia el mensaje    
        String tipo = null;
        if (null != bien.getTipo_bien()) {
            switch (bien.getTipo_bien()) {
                case "1" ->
                    tipo = "Climas";
                case "2" ->
                    tipo = "Extintores";
                case "3" ->
                    tipo = "Impresoras";
                default -> {
                    request.setAttribute("error", "Error: El valor selecciono = " + bien.getTipo_bien());
                    try {
                        request.getRequestDispatcher("pages/Error.jsp").forward(request, response);
                    } catch (ServletException | IOException ex) {
                        System.err.println("ERROR nose recono tipo_bien ActualizarBien : " + ex);
                    }
                }
            }
        }

        try {
            //request.getRequestDispatcher("pages/editarBien.jsp").forward(request, response);
            response.sendRedirect("ControllerEditar?Editar=Bien&mensaje=" + mensaje + "&cve_bienes="
                    + bien.getCve_bienes() + "&tipo_bien=" + tipo);
        } catch (IOException ex) {
            System.err.println("ERROR en ActualizarBien : " + ex);
        }
    }

    //Redireciona a la Pagina con los datos, para editar el Bien
    private void paginaEditarBien(HttpServletRequest request, HttpServletResponse response) {
        String cve_bienes = request.getParameter("cve_bienes");
        String tipo_bien = request.getParameter("tipo_bien");
        String mensaje = request.getParameter("mensaje");
        GetBienes_Id buscar = new GetBienes_Id();
        Bienes bien = new Bienes();
        try {
            //Se envia el ID y el tipo de Bien para buscar la ubicacion, y caracteristicas del Bien, sin el mantenimiento
            bien = buscar.buscarBien(Integer.parseInt(cve_bienes), tipo_bien);
            request.setAttribute("bien", bien);
            if (!StringUtil.isBlank(mensaje)) {
                request.setAttribute("mensaje", mensaje);
            } else {
                request.setAttribute("mensaje", "");
            }
            request.getRequestDispatcher("pages/editarBien.jsp").forward(request, response);
        } catch (ServletException | IOException ex) {
            System.err.println("ERROR EN paginaEditarBien : " + ex);
        }
    }

    private void paginaEditarMantenimiento(HttpServletRequest request, HttpServletResponse response) {
        String cve_mantenimiento = request.getParameter("cve_mantenimiento");
        String tipo_bien = request.getParameter("tipo_bien");
        String mensaje = request.getParameter("mensaje");
        String num_inventario = request.getParameter("inventario");
        GetMantenimiento_Id buscar = new GetMantenimiento_Id();
        String json = null;
        Gson gson = new Gson();
        Get_Id_Inventarios_Bienes bienes = new Get_Id_Inventarios_Bienes();
        String data = bienes.ObtenerIdInventarios_Bienes();
        try {
            //Se envia el ID y el tipo de Bien para buscar la ubicacion, y caracteristicas del Bien, sin el mantenimiento
            Mantenimientos mant = buscar.GetMantnimientoID(cve_mantenimiento, tipo_bien, num_inventario);
            json = gson.toJson(mant);
            if (!StringUtil.isBlank(mensaje)) {
                request.setAttribute("mensaje", mensaje);
            } else {
                request.setAttribute("mensaje", "");
            }
            if (json != null) {
                request.setAttribute("M", json);
            } else {
                request.setAttribute("M", " ");
            }

            if (data != null) {
                request.setAttribute("data", data);
            } else {
                request.setAttribute("data", " ");
            }

            request.setAttribute("tipo_bien", tipo_bien);
            request.getRequestDispatcher("pages/editarMantenimiento.jsp").forward(request, response);

        } catch (ServletException | IOException ex) {
            System.err.println("ERROR EN paginaEditarBien : " + ex);
        }
    }

    private void ActualizarMantenimiento(HttpServletRequest request, HttpServletResponse response) {
        Mantenimientos M = new Mantenimientos();

        //Se envian los parametros request, donde se obtiene el bien
        M = M.ObtenerDatos(request, true);
        ActualizarMantenimiento actualiza = new ActualizarMantenimiento();

        //Actualiza el Mantenimiento y Retorna un Mensaje
        String mensaje = actualiza.Actualizar(M);
        //Se envia el mensaje    
        String tipo = null;
        if (null != M.getTipo_bien()) {
            switch (M.getTipo_bien()) {
                case "1" ->
                    tipo = "Climas";
                case "2" ->
                    tipo = "Extintores";
                case "3" ->
                    tipo = "Impresoras";
                default -> {
                    request.setAttribute("error", "Error: El valor selecciono = " + M.getTipo_bien());
                    try {
                        request.getRequestDispatcher("pages/Error.jsp").forward(request, response);
                    } catch (ServletException | IOException ex) {
                        System.err.println("ERROR nose recono tipo_bien ActualizarBien : " + ex);
                    }
                }
            }
        }

        try {
            //request.getRequestDispatcher("pages/editarBien.jsp").forward(request, response);
            response.sendRedirect("ControllerEditar?Editar=Mantenimiento&mensaje=" + mensaje + "&cve_bienes="
                    + M.getCve_bienes() + "&tipo_bien=" + tipo + "&inventario=" + M.getNum_inventario()
                    + "&cve_mantenimiento=" + M.getCve_mantenimiento());
        } catch (IOException ex) {
            System.err.println("ERROR en ActualizarBien : " + ex);
        }

    }

    private void paginaEditarRecibo(HttpServletRequest request, HttpServletResponse response) {
        String cve_recibo = request.getParameter("cve_recibo");
        String mensaje = request.getParameter("mensaje");
        GetRecibo recibo = new GetRecibo();
        GetMedidor medidor = new GetMedidor();
        String data = medidor.getNumMedidores();

        try {
            //Se busca el recibo por medio del ID
            String jsonRecibo = recibo.getReciboId(Integer.parseInt(cve_recibo));
            //Se envia el json con el Recibo
            request.setAttribute("recibo", jsonRecibo);
            request.setAttribute("data", data);
            if (!StringUtil.isBlank(mensaje)) {
                request.setAttribute("mensaje", mensaje);
            } else {
                request.setAttribute("mensaje", "");
            }
            request.getRequestDispatcher("pages/editarRecibo.jsp").forward(request, response);
        } catch (ServletException | IOException ex) {
            System.err.println("ERROR EN paginaEditarBien : " + ex);
        }
    }

    private void ActualizarRecibo(HttpServletRequest request, HttpServletResponse response) {

        String cve_recibo = request.getParameter("cve_recibo");
        ActualizarCFE actualizar = new ActualizarCFE();
        Recibo R = new Recibo();
        R = R.getRecibo(request, true);
        String mensaje = actualizar.UpedateRecibo(R);

        try {

            response.sendRedirect("ControllerEditar?Editar=Recibo&mensaje=" + mensaje + "&cve_recibo="
                    + cve_recibo);
        } catch (IOException ex) {
            System.err.println("ERROR en ActualizarBien : " + ex);
        }
    }

    private void paginaEditarMedidor(HttpServletRequest request, HttpServletResponse response) {
        String cve_medidor = request.getParameter("cve_medidor");
        String mensaje = request.getParameter("mensaje");
        isNumeric ID = new isNumeric();
        boolean numero = ID.num(cve_medidor);

        if (numero == true) {
            GetMedidor medidor = new GetMedidor();
            String json = medidor.getMedidorID(Integer.parseInt(cve_medidor));
            try {
                
                if (!StringUtil.isBlank(mensaje)) {
                   request.setAttribute("mensaje", mensaje);
                } else {
                  request.setAttribute("mensaje", " ");  
                }
                
                
                request.setAttribute("json", json);
                request.getRequestDispatcher("pages/editarMedidor.jsp").forward(request, response);
            } catch (ServletException | IOException ex) {
                System.err.println("ERROR EN paginaEditarMedidor : " + ex);
            }
        } else {
            try {
                request.setAttribute("error", "Error: El Numero de Medidor es incorrecto");
                request.getRequestDispatcher("pages/Error.jsp").forward(request, response);
            } catch (ServletException | IOException ex) {
                System.err.println("ERROR EN paginaEditarMedidor : " + ex);
            }
        }
    }

    private void ActualizarMedidor(HttpServletRequest request, HttpServletResponse response) {
        String cve_medidor = request.getParameter("cve_medidor");
        isNumeric ID = new isNumeric();
        boolean numero = ID.num(cve_medidor);
        Medidor M = new Medidor();
        M = M.GetMedidor(request);

        ActualizarCFE medidor = new ActualizarCFE();

        if (numero == true) {
            String mensaje = medidor.UpedateMedidor(M);
            try {

                response.sendRedirect("ControllerEditar?Editar=Medidor&mensaje=" + mensaje + "&cve_medidor="
                        + cve_medidor);
            } catch (IOException ex) {
                System.err.println("ERROR en ActualizarBien : " + ex);
            }
        } else {
            try {
                request.setAttribute("error", "Error: El Numero de Medidor es incorrecto");
                request.getRequestDispatcher("pages/Error.jsp").forward(request, response);
            } catch (ServletException | IOException ex) {
                System.err.println("ERROR EN paginaEditarMedidor : " + ex);
            }
        }
    }
}
