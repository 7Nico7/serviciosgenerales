package Controller;

import Model.Archivo;
import Model.EliminarBien;
import Model.EliminarMantenimiento;
import Model.EliminarMedidor;
import Model.EliminarRecibo;
import Model.GetArchivos;
import Model.isNumeric;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.lang3.StringUtils;

@WebServlet(name = "ControllerEliminar", urlPatterns = {"/ControllerEliminar"})
public class ControllerEliminar extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out;
        out = response.getWriter();
        String eliminarEditar = request.getParameter("Eliminar");

        if (!StringUtils.isBlank(eliminarEditar)) {
            switch (eliminarEditar) {
                case "eliminarBien":
                    eliminarBien(request, response);
                    break;
                case "mantenimiento":
                    eliminarMantenimiento(request, response);
                    break;
                case "Medidor":
                    eliminarMedidor(request, response);
                    break;
                case "Recibo":
                    eliminarRecibo(request, response);
                    break;
                default:
                    out.println("desconocido");
            }
        } else {
            out.println("nulo");
        }
    }

    private void eliminarBien(HttpServletRequest request, HttpServletResponse response) {
        try {
            String cve_bienes = request.getParameter("cve_bienes");
            EliminarBien bien = new EliminarBien();
            PrintWriter out;
            out = response.getWriter();
            String sql = "select archivo from fecha_mante_camb where cve_bienes = ?;";
            GetArchivos archivos = new GetArchivos();
            List<Archivo> lista = archivos.getArchivos(Integer.parseInt(cve_bienes), sql);
            try {
                String mensaje = null;
                if (!StringUtils.isBlank(cve_bienes)) {
                    if (Integer.parseInt(cve_bienes) > 0) {
                        mensaje = bien.Eliminar(Integer.parseInt(cve_bienes), lista);

                    } else {
                        try {
                            request.setAttribute("error", "Error: El ID no es Correcto");
                            request.getRequestDispatcher("pages/Error.jsp").forward(request, response);
                        } catch (ServletException ex) {
                            Logger.getLogger(ControllerEliminar.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                } else {
                    request.setAttribute("error", "Error: El ID Es NULO ");
                    try {
                        request.getRequestDispatcher("pages/Error.jsp").forward(request, response);
                    } catch (ServletException ex) {
                        Logger.getLogger(ControllerEliminar.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

                if (mensaje != null) {
                    out.print(mensaje);
                }
            } catch (NumberFormatException ex) {
                System.err.println("Error " + ex);
            }
        } catch (IOException ex2) {
            System.err.println("Error " + ex2);
        }
    }

    private void eliminarMantenimiento(HttpServletRequest request, HttpServletResponse response) {
        String cve_mantenimiento = request.getParameter("cve_mantenimiento");
        String archivo = request.getParameter("archivo");
        String reporte = request.getParameter("reporte");
        String sql = "";
        try {

            GetArchivos archivos = new GetArchivos();
            List<Archivo> lista = archivos.getArchivos(Integer.parseInt(cve_mantenimiento), sql);
            EliminarMantenimiento mantenimiento = new EliminarMantenimiento();
            PrintWriter out;
            out = response.getWriter();

            try {
                String mensaje = null;
                if (!StringUtils.isBlank(cve_mantenimiento)) {
                    if (Integer.parseInt(cve_mantenimiento) > 0) {
                        mensaje = mantenimiento.Eliminar(Integer.parseInt(cve_mantenimiento), archivo, reporte);
                    } else {
                        try {
                            request.setAttribute("error", "Error: El ID no es Correcto");
                            request.getRequestDispatcher("pages/Error.jsp").forward(request, response);
                        } catch (ServletException ex) {
                            System.err.println("ERROR EN eliminarMantenimiento : " + ex);
                        }
                    }
                } else {
                    request.setAttribute("error", "Error: El ID Es NULO ");
                    try {
                        request.getRequestDispatcher("pages/Error.jsp").forward(request, response);
                    } catch (ServletException ex) {
                        System.err.println("ERROR EN eliminarMantenimiento " + ex);
                    }
                }

                if (mensaje != null) {
                    out.print(mensaje);
                    System.err.println("MNSAJ : " + mensaje);
                }
            } catch (NumberFormatException ex) {
                System.err.println("Error " + ex);
            }
        } catch (IOException ex2) {
            System.err.println("Error " + ex2);
        }
    }

    private void eliminarRecibo(HttpServletRequest request, HttpServletResponse response) {
        try {
            String cve_recibo = request.getParameter("cve_recibo");
            String archivo = request.getParameter("archivo");
            String reporte = request.getParameter("archivo_reporte");
            isNumeric id = new isNumeric();

            PrintWriter out;
            out = response.getWriter();

            if (!StringUtils.isBlank(cve_recibo)) {
                if (id.num(cve_recibo) == true) {
                    EliminarRecibo recibo = new EliminarRecibo();
                    String mensaje = recibo.Eliminar(Integer.parseInt(cve_recibo), archivo, reporte);
                    out.print(mensaje);
                } else {
                    out.print("incorrecto");
                }
            } else {
                out.print("nulo");

            }
        } catch (IOException ex) {
            System.err.println("ERROR EN eliminarRecibo : " + ex);
        }

    }

    private void eliminarMedidor(HttpServletRequest request, HttpServletResponse response) {
        try {
            String cve_medidor = request.getParameter("cve_medidor");
            isNumeric ID = new isNumeric();

            boolean numero = ID.num(cve_medidor);
            PrintWriter out;
            out = response.getWriter();
            String sql = "select archivo, archivo2 from recibo where cve_medidor = ?;";

            if (numero == true) {

                GetArchivos archivos = new GetArchivos();
                List<Archivo> lista = archivos.getArchivos(Integer.parseInt(cve_medidor), sql);
                System.err.println("LISTA : " + lista);

                EliminarMedidor medidor = new EliminarMedidor();

                String mensaje = medidor.Eliminar(Integer.parseInt(cve_medidor), lista);
                out.print(mensaje);
            } else {
                out.print("desconocido");
            }
        } catch (IOException ex) {
            System.err.println("ERROR en eliminarMedidor : " + ex);

        }

    }

}