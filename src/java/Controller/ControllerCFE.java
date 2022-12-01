package Controller;

import Model.GetMedidor;
import Model.GetRecibo;
import Model.Medidor;
import Model.Recibo;
import Model.RegistrarMedidor;
import Model.RegistrarRecibo;
import Model.isNumeric;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.util.StringUtil;

@MultipartConfig
@WebServlet(urlPatterns = {"/ControllerCFE"})
public class ControllerCFE extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String pagina = request.getParameter("pagina");

        if (!StringUtil.isBlank(pagina)) {

            switch (pagina) {
                case "Medidor":
                    paginaMedidor(request, response);
                    break;
                case "Recibo":
                    paginaRecibo(request, response);
                    break;
                case "nuevoMedidor":
                    paginaNuevoMedidor(request, response);
                    break;
                case "nuevoRecibo":
                    paginaNuevoRecibo(request, response);
                    break;
                case "detalleMedidor":
                    paginaDellateMedidor(request, response);
                    break;
                case "detalleRecibo":
                    paginaDellateRecibo(request, response);
                    break;
                default:
                    request.setAttribute("error", "Error: El valor pagina es Desconocido, ControllerCFE");
                    request.getRequestDispatcher("pages/Error.jsp").forward(request, response);
            }

        } else {
            request.setAttribute("error", "Error: El valor pagina es NULO, ControllerCFE");
            request.getRequestDispatcher("pages/Error.jsp").forward(request, response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
           request.setCharacterEncoding("UTF-8");
        String guardar = request.getParameter("guardar");

        if (!StringUtil.isBlank(guardar)) {
            switch (guardar) {
                case "Medidor":
                    guardarMedidor(request, response);
                    break;
                case "Recibo":
                    guardarRecibo(request, response);
                    break;
                default:
                    throw new AssertionError();
            }
        } else {
            request.setAttribute("error", "Error: El valor guardar es NULO, ControllerCFE");
            request.getRequestDispatcher("pages/Error.jsp").forward(request, response);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private void paginaMedidor(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.getRequestDispatcher("pages/Medidor.jsp").forward(request, response);
        } catch (ServletException | IOException ex) {
            System.err.println("ERROR paginaMedidor : " + ex);
        }
    }

    private void paginaNuevoMedidor(HttpServletRequest request, HttpServletResponse response) {
        String mensaje = request.getParameter("mensaje");
        try {
            if (!StringUtil.isBlank(mensaje)) {
                request.setAttribute("mensaje", mensaje);
            } else {
                request.setAttribute("mensaje", "");
            }
            request.getRequestDispatcher("pages/nuevoMedidor.jsp").forward(request, response);
        } catch (ServletException | IOException ex) {
            System.err.println("ERROR paginaNuevoMedidor : " + ex);
        }
    }

    private void guardarMedidor(HttpServletRequest request, HttpServletResponse response) {
        Medidor medidor = new Medidor();
        //Obtiene los datos del formulario
        medidor = medidor.GetMedidor(request);

        RegistrarMedidor registrar = new RegistrarMedidor();

        //Se registra el Medidor
        String mensaje = registrar.RegistrarMedidor(medidor);
        try {
            //Se envia la Respuesta
            response.sendRedirect("ControllerCFE?pagina=nuevoMedidor&mensaje=" + mensaje);
        } catch (IOException ex) {
            System.err.println("ERROR guardarMedidor : " + ex);
        }

    }

    private void paginaRecibo(HttpServletRequest request, HttpServletResponse response) {
        String tabla = request.getParameter("Tipo");
        try {
            request.setAttribute("Tipo", tabla);
            request.getRequestDispatcher("pages/Recibo.jsp").forward(request, response);
        } catch (ServletException | IOException ex) {
            System.err.println("ERROR paginaRecibo : " + ex);
        }
    }

    private void paginaNuevoRecibo(HttpServletRequest request, HttpServletResponse response) {
        String mensaje = request.getParameter("mensaje");
        GetMedidor medidor = new GetMedidor();
        String data = medidor.getNumMedidores();
        try {

            if (!StringUtil.isBlank(mensaje)) {
                request.setAttribute("mensaje", mensaje);
            } else {
                request.setAttribute("mensaje", "");
            }

            request.setAttribute("data", data);
            request.getRequestDispatcher("pages/nuevoRecibo.jsp").forward(request, response);
        } catch (ServletException | IOException ex) {
            System.err.println("ERROR paginaNuevoRecibo : " + ex);
        }
    }

    private void guardarRecibo(HttpServletRequest request, HttpServletResponse response) {
        Recibo recibo = new Recibo();
        //Se obtiene el objeto ya mandamos que no es para actualizar
        recibo = recibo.getRecibo(request, false);

        if (recibo == null) {
            try {
                String mensaje = """
                          <div class="alert alert-danger" role="alert">
                          EL Campo No.Medidor Es Incorrecto """ + "\n" + "</div>";

                response.sendRedirect("ControllerCFE?pagina=nuevoRecibo&mensaje=" + mensaje);
            } catch (IOException ex) {
                System.err.println("ERROR paginaNuevoRecibo : " + ex);
            }
        } else {
            try {
                RegistrarRecibo registrar = new RegistrarRecibo();
                String mensaje = registrar.registrarRecibo(recibo);
                response.sendRedirect("ControllerCFE?pagina=nuevoRecibo&mensaje=" + mensaje);
            } catch (IOException ex) {
                System.err.println("ERROR paginaNuevoRecibo : " + ex);
            }
        }
    }

    private void paginaDellateRecibo(HttpServletRequest request, HttpServletResponse response) {
        String cve_recibo = request.getParameter("cve_recibo");
        isNumeric numero = new isNumeric();
        boolean isNum = numero.num(cve_recibo);

        //Se valida el ID del recibo
        if (isNum == true) {
            try {
                GetRecibo recibo = new GetRecibo();
                String json = recibo.getReciboId(Integer.parseInt(cve_recibo));
                request.setAttribute("recibo", json);
                request.getRequestDispatcher("pages/detallesRecibo.jsp").forward(request, response);
            } catch (ServletException | IOException ex) {
                System.err.println("ERROR paginaDellateRecibo : " + ex);
            }
        } else {

            try {
                request.setAttribute("error", "Error: El Numero de Recibo es incorrecto");
                request.getRequestDispatcher("pages/Error.jsp").forward(request, response);
            } catch (ServletException | IOException ex) {
                System.err.println("error en paginaDellateRecibo no ID incorrecto");
            }

        }

    }

    private void paginaDellateMedidor(HttpServletRequest request, HttpServletResponse response) {
        String cve_medidor = request.getParameter("cve_medidor");
        isNumeric numero = new isNumeric();
        boolean isNum = numero.num(cve_medidor);

        //Se valida el ID del recibo
        if (isNum == true) {
            try {
                request.setAttribute("cve_medidor", cve_medidor);
                request.getRequestDispatcher("pages/detallesMedidor.jsp").forward(request, response);
            } catch (ServletException | IOException ex) {
                System.err.println("ERROR paginaDellateMedidor : " + ex);
            }
        } else {

            try {
                request.setAttribute("error", "Error: El Numero de Medidor es incorrecto");
                request.getRequestDispatcher("pages/Error.jsp").forward(request, response);
            } catch (ServletException | IOException ex) {
                System.err.println("error en paginaDellateRecibo no ID incorrecto");
            }

        }

    }
}
