package Controller;

import Model.Bienes;
import Model.GetBienes;
import Model.GetBienes_Id;
import Model.GetMantenimiento_Id;
import Model.GetMantenimientos;
import Model.GetMedidor;
import Model.GetRecibo;
import Model.isNumeric;
import com.google.gson.Gson;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.poi.util.StringUtil;

@WebServlet(name = "ControllerTablas", urlPatterns = {"/ControllerTablas"})
public class ControllerTablas extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
        String modulo = request.getParameter("Modulo");
        String tabla = request.getParameter("tabla");

        if (!StringUtil.isBlank(modulo)) {
            switch (modulo) {
                case "Bienes":
                    if (!StringUtil.isBlank(tabla)) {
                        TablaBienes(response, tabla);
                    } else {
                        request.setAttribute("error", "Error El Parametro Tabla es NULO");
                        request.getRequestDispatcher("pages/Error.jsp").forward(request, response);
                    }
                    break;
                case "Mantenimiento":
                    if (!StringUtil.isBlank(tabla)) {
                        TablaMantenimiento(response, tabla);

                    } else {
                        request.setAttribute("error", "Error El Parametro Tabla es NULO");
                        request.getRequestDispatcher("pages/Error.jsp").forward(request, response);
                    }
                    break;
                case "Medidor":
                    TablaMedidor(response);
                    break;
                case "Recibo":
                    TablaRecibo(request, response);
                    break;
                case "ReciboID":
                    TablaReciboID(request, response);
                    break;
                case "DetalleMedidor":
                    TablaDetalleMedidor(request, response);
                    break;
                case "DetalleBienes":
                    TablaDetalleBienes(request, response);
                    break;
                case "DetalleBienes1":
                    TablaDetalleBienes1(request, response);
                    break;
                default:
                    request.setAttribute("error", "Error no se reconoce el Parametro Modulo");
                    request.getRequestDispatcher("pages/Error.jsp").forward(request, response);
            }
        } else {
            request.setAttribute("error", "Error el Parametro Modulo es NULO = ");
            request.getRequestDispatcher("pages/Error.jsp").forward(request, response);
        }

    }

    GetBienes bienes = new GetBienes();

    /*Metodo para los datos de la tabla en json*/
    private void TablaBienes(HttpServletResponse response, String tabla) {
        String data = bienes.getBienes(tabla);
        PrintWriter out;
        try {
            out = response.getWriter();
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            out.print(data);
            out.flush();
        } catch (IOException ex) {
            System.err.println("ERROR EN ControllerTablas Bienes: " + ex);
        }
    }

    private void TablaMantenimiento(HttpServletResponse response, String tabla) {
        GetMantenimientos mentenimientos = new GetMantenimientos();
        String data = mentenimientos.mantenimientos(tabla);
        PrintWriter out;
        try {
            out = response.getWriter();
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            out.print(data);
            out.flush();
        } catch (IOException ex) {
            System.err.println("ERROR EN ControllerTablas Bienes: " + ex);
        }
    }

    private void TablaMedidor(HttpServletResponse response) {
        GetMedidor medidor = new GetMedidor();
        String data = medidor.getMedidor();
        PrintWriter out;
        try {
            out = response.getWriter();
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            out.print(data);
            out.flush();
        } catch (IOException ex) {
            System.err.println("ERROR EN ControllerTablas Bienes: " + ex);
        }

    }

    private void TablaRecibo(HttpServletRequest request, HttpServletResponse response) {
        String tabla = request.getParameter("Tipo");

        GetRecibo recibo = new GetRecibo();
        String data = "";
        if (!StringUtil.isBlank(tabla)) {
            switch (tabla) {
                case "todos":
                    data = recibo.getRecibo();
                    break;
                case "recientes":
                    data = recibo.getReciboOrdenMasReciente();
                    break;
                default:
            try {
                    request.setAttribute("error", "Error no se reconoce el Parametro Para la Tabla");
                    request.getRequestDispatcher("pages/Error.jsp").forward(request, response);
                } catch (ServletException | IOException ex) {
                    System.err.println("Error en TablaRecibo : " + ex);
                }
            }
        } else {
            try {
                request.setAttribute("error", "Error no se reconoce el Parametro Para la Tabla");
                request.getRequestDispatcher("pages/Error.jsp").forward(request, response);
            } catch (ServletException | IOException ex) {
                System.err.println("Error en TablaRecibo : " + ex);
            }
        }

        PrintWriter out;
        try {
            out = response.getWriter();
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            out.print(data);
            out.flush();
        } catch (IOException ex) {
            System.err.println("ERROR EN ControllerTablas Bienes: " + ex);
        }

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

    private void TablaReciboID(HttpServletRequest request, HttpServletResponse response) {
        String cve_recibo = request.getParameter("cve_recibo");
        GetRecibo medidor = new GetRecibo();
        String data = "[" + medidor.getReciboId(Integer.parseInt(cve_recibo)) + "]";

        PrintWriter out;
        try {
            out = response.getWriter();
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            out.print(data);
            out.flush();
        } catch (IOException ex) {
            System.err.println("ERROR EN ControllerTablas TablaReciboID: " + ex);
        }
    }

    private void TablaDetalleMedidor(HttpServletRequest request, HttpServletResponse response) {

        String cve_medidor = request.getParameter("cve_medidor");
        String tablaRecibos = request.getParameter("tablaDetalleMedidorRecibos");
        GetMedidor medidor = new GetMedidor();
        PrintWriter out;
        String data = medidor.getMedidorDetalles(Integer.parseInt(cve_medidor), 1);
        int cont = 0;
        if (data == null) {
            data = medidor.getMedidorDetalles(Integer.parseInt(cve_medidor), 2);
            cont++;
        }

        try {
            out = response.getWriter();
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");

            if (!StringUtil.isBlank(tablaRecibos) && cont > 0) {
                data = null;
                out.print(data);
            } else {
                out.print(data);
            }

            out.flush();
        } catch (IOException ex) {
            System.err.println("ERROR EN ControllerTablas TablaDetalleMedidor: " + ex);
        }

    }

    private void TablaDetalleBienes(HttpServletRequest request, HttpServletResponse response) {
        String cve_bien = request.getParameter("cve_bienes");
        isNumeric ID = new isNumeric();
        PrintWriter out;
        String data = "";
        GetMantenimiento_Id mante = new GetMantenimiento_Id();
        boolean numero = ID.num(cve_bien);
        try {
            out = response.getWriter();
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            if (!StringUtil.isBlank(cve_bien) && numero == true && Integer.parseInt(cve_bien) > 0) {

                data = mante.GetDetalleBien_MantenimientoID(Integer.parseInt(cve_bien));

                if (data != null) {
                    out.print(data);
                } else {
                    data = null;
                    out.print(data);
                }

            } else {
                data = null;
                out.print(data);
            }
            out.flush();
        } catch (IOException ex) {
            System.err.println("ERROR EN TablaDetalleBienes : " + ex);
        }

    }

    private void TablaDetalleBienes1(HttpServletRequest request, HttpServletResponse response) {

        String cve_bien = request.getParameter("cve_bienes");
        String tipo = request.getParameter("tipo");
        isNumeric ID = new isNumeric();
        PrintWriter out;
        Bienes Bien = new Bienes();
        Gson gson = new Gson();
        String data = "";
        GetBienes_Id bien = new GetBienes_Id();
        boolean numero = ID.num(cve_bien);
        try {
            out = response.getWriter();
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            if (!StringUtil.isBlank(cve_bien) && numero == true && Integer.parseInt(cve_bien) > 0) {

                Bien = bien.buscarBien(Integer.parseInt(cve_bien), tipo);
                data = gson.toJson(Bien);
                if (data == null) {
                    out.print(data);
                } else {
                    out.print("[" + data + "]");
                }

                out.flush();
            }
        } catch (IOException ex) {
            System.err.println("ERROR EN TablaDetalleBienes : " + ex);
        }

    }
}
