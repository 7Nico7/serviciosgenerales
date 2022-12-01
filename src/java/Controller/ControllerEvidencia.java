package Controller;

import Model.GetRecibo;
import Model.isNumeric;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.util.StringUtil;

@WebServlet(name = "ControllerEvidencia", urlPatterns = {"/ControllerEvidencia"})
public class ControllerEvidencia extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String pagina = request.getParameter("pagina");

        if (!StringUtil.isBlank(pagina)) {
            switch (pagina) {
                case "mantenimiento":
                    evidenciaMantenimiento(request, response);
                    break;
                case "recibo":
                    evidenciaRecibo(request, response);
                    break;
                default:
                    request.setAttribute("error", "Error: El valor de Pagina no es aceptado pagina= " + pagina);
                    request.getRequestDispatcher("pages/Error.jsp").forward(request, response);
            }
        } else {
            request.setAttribute("error", "Error: El valor de pagina es NULO");
            request.getRequestDispatcher("pages/Error.jsp").forward(request, response);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private void evidenciaMantenimiento(HttpServletRequest request, HttpServletResponse response) {
        String video = request.getParameter("video");
        System.err.println(video);
        if (!StringUtil.isBlank(video)) {
            try {

                response.sendRedirect("pages/evidencia.jsp");
            } catch (IOException ex) {
                System.err.println("ERROR en evidenciaMantenimiento : " + ex);
            }
        } else {
            try {
                request.setAttribute("error", "NO HAY EVIDENCIAS");
                request.getRequestDispatcher("pages/Error.jsp").forward(request, response);
            } catch (ServletException | IOException ex) {
                System.err.println("ERROR en evidenciaMantenimiento : " + ex);
            }
        }

    }

    private void evidenciaRecibo(HttpServletRequest request, HttpServletResponse response) {
        String cve_medidor = request.getParameter("cve_medidor");
        try {

            //Envia la respuesta al Metodo ajax
            PrintWriter out;
            out = response.getWriter();

            // Valida que se reciva algo
            if (cve_medidor == null) {
                out.print("nulo");
            } else {
                //valida si el ID es un Numero
                isNumeric ID = new isNumeric();
                boolean numero = ID.num(cve_medidor);
                if (numero == true) {
                    GetRecibo recibo = new GetRecibo();
                    String json = recibo.getReciboId_MasReciente(Integer.parseInt(cve_medidor));
                    out.print(json);
                } else {
                    out.print("invalido");
                }
            }
        } catch (IOException ex) {
            System.err.println("ERROR en evidenciaRecibo : " + ex);
        }
    }
}
