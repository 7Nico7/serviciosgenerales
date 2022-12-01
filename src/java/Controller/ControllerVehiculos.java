package Controller;

import Model.ActualizarSalidasVehiculo;
import Model.CargarGasolina;
import Model.GetVehiculos;
import Model.RegistrarCargarGasolina;
import Model.RegistrarSalidasVehiculo;
import Model.RegistrarServicio;
import Model.RegistrarVehiculo;
import Model.Servicios;
import Model.Vehiculo;
import Model.VehiculoSalidas;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.util.StringUtil;

@MultipartConfig
@WebServlet(name = "ControllerVehiculos", urlPatterns = {"/ControllerVehiculos"})
public class ControllerVehiculos extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String guardar = request.getParameter("guardar");

        if (!StringUtil.isBlank(guardar)) {

            switch (guardar) {
                case "vehiculo":
                    guardarVehiculo(request, response);
                    break;
                case "gasolina":
                    guardarCargaGasolina(request, response);
                    break;
                case "servicio":
                    guardarServicio(request, response);
                    break;
                case "salida":
                    guardarSalida(request, response);
                    break;
                case "concretarSalida":
                    concretarSalida(request, response);
                    break;
                default:
                    request.setAttribute("error", "Error no se reconoce el Parametro guardar");
                    request.getRequestDispatcher("pages/Error.jsp").forward(request, response);
            }

        } else {
            request.setAttribute("error", "Error El Parametro guardar es NULO");
            request.getRequestDispatcher("pages/Error.jsp").forward(request, response);
        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String pagina = request.getParameter("pagina");

        if (!StringUtil.isBlank(pagina)) {
            switch (pagina) {
                case "vehiculos":
                    paginaVehiculos(request, response);
                    break;
                case "nuevoVehiculo":
                    paginaNuevoVehiculo(request, response);
                    break;
                case "gasolina":
                    paginaGasolina(request, response);
                    break;
                case "paginaCargarCombustible":
                    paginaCargarCombustible(request, response);
                    break;
                case "servicios":
                    paginaServicios(request, response);
                    break;
                case "nuevoServicio":
                    paginaNuevoServicio(request, response);
                    break;
                case "salidas":
                    paginaSalidasVehiculo(request, response);
                    break;
                case "nuevaSalida":
                    paginaNuevaSalidaVehiculo(request, response);
                    break;
                case "concretarSalida":
                    paginaConcretarSalida(request, response);
                    break;
                default:
                    request.setAttribute("error", "Error no se reconoce el Parametro pagina");
                    request.getRequestDispatcher("pages/Error.jsp").forward(request, response);
            }
        } else {
            request.setAttribute("error", "Error no se reconoce el Parametro pagina");
            request.getRequestDispatcher("pages/Error.jsp").forward(request, response);
        }

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private void paginaVehiculos(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.setAttribute("data", " ");
            request.getRequestDispatcher("pages/Vehiculos.jsp").forward(request, response);
        } catch (ServletException | IOException ex) {
            System.err.println("error en paginaVehiculos : " + ex);
        }
    }

    private void paginaGasolina(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.setAttribute("data", " ");
            request.getRequestDispatcher("pages/Combustible.jsp").forward(request, response);
        } catch (ServletException | IOException ex) {
            System.err.println("error en paginaVehiculos : " + ex);
        }
    }

    private void paginaServicios(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.setAttribute("data", " ");
            request.getRequestDispatcher("pages/ServiciosVehiculos.jsp").forward(request, response);
        } catch (ServletException | IOException ex) {
            System.err.println("error en paginaVehiculos : " + ex);
        }
    }

    private void paginaNuevoVehiculo(HttpServletRequest request, HttpServletResponse response) {
        String mensaje = request.getParameter("mensaje");

        try {

            if (!StringUtil.isBlank(mensaje)) {
                request.setAttribute("mensaje", mensaje);
            } else {
                request.setAttribute("mensaje", " ");
            }
            request.getRequestDispatcher("pages/nuevoVehiculo.jsp").forward(request, response);
        } catch (ServletException | IOException ex) {
            System.err.println("error en paginaVehiculos : " + ex);
        }

    }

    private void paginaCargarCombustible(HttpServletRequest request, HttpServletResponse response) {
        String mensaje = request.getParameter("mensaje");
        GetVehiculos data = new GetVehiculos();
        String json = data.getVehiculosIds();
        try {

            request.setAttribute("data", json);

            if (!StringUtil.isBlank(mensaje)) {
                request.setAttribute("mensaje", mensaje);
            }
            request.getRequestDispatcher("pages/cargarCombustible.jsp").forward(request, response);
        } catch (ServletException | IOException ex) {
            System.err.println("error en paginaVehiculos : " + ex);
        }

    }

    private void paginaNuevoServicio(HttpServletRequest request, HttpServletResponse response) {
        String mensaje = request.getParameter("mensaje");
        GetVehiculos data = new GetVehiculos();
        String json = data.getVehiculosIds();
        try {

            if (!StringUtil.isBlank(mensaje)) {
                request.setAttribute("mensaje", mensaje);
            }

            request.setAttribute("data", json);
            request.getRequestDispatcher("pages/nuevoServicio.jsp").forward(request, response);
        } catch (ServletException | IOException ex) {
            System.err.println("error en paginaVehiculos : " + ex);
        }
    }

    private void guardarVehiculo(HttpServletRequest request, HttpServletResponse response) {
        Vehiculo V = new Vehiculo();
        V = V.getVehiculo(request);
        RegistrarVehiculo registrar = new RegistrarVehiculo();
        String mensaje = registrar.RegistrarVehiculo(V);
        try {
            //request.getRequestDispatcher("pages/editarBien.jsp").forward(request, response);
            response.sendRedirect("ControllerVehiculos?pagina=nuevoVehiculo&mensaje=" + mensaje);
        } catch (IOException ex) {
            System.err.println("ERROR en guardarVehiculo : " + ex);
        }
    }

    private void guardarCargaGasolina(HttpServletRequest request, HttpServletResponse response) {

        CargarGasolina G = new CargarGasolina();

        G = G.getCargarGasolina(request);
        RegistrarCargarGasolina registrar = new RegistrarCargarGasolina();

        String mensaje = registrar.RegistrarCargarGasolina(G);

        try {

            //request.getRequestDispatcher("pages/editarBien.jsp").forward(request, response);
            response.sendRedirect("ControllerVehiculos?pagina=paginaCargarCombustible&mensaje=" + mensaje);
        } catch (IOException ex) {
            System.err.println("ERROR en guardarVehiculo : " + ex);
        }

    }

    private void guardarServicio(HttpServletRequest request, HttpServletResponse response) {
        Servicios S = new Servicios();

        S = S.getServicios(request, false);
        RegistrarServicio registrar = new RegistrarServicio();

        String mensaje = registrar.RegistrarServicio(S);

        try {

            //request.getRequestDispatcher("pages/editarBien.jsp").forward(request, response);
            response.sendRedirect("ControllerVehiculos?pagina=nuevoServicio&mensaje=" + mensaje);
        } catch (IOException ex) {
            System.err.println("ERROR en guardarVehiculo : " + ex);
        }

    }

    private void paginaSalidasVehiculo(HttpServletRequest request, HttpServletResponse response) {

        try {
            request.getRequestDispatcher("pages/salidasVehiculo.jsp").forward(request, response);
        } catch (ServletException | IOException ex) {
            System.err.println("Error en paginaSalidaVehiculo : " + ex);
        }

    }

    private void paginaNuevaSalidaVehiculo(HttpServletRequest request, HttpServletResponse response) {
        String mensaje = request.getParameter("mensaje");
        GetVehiculos v = new GetVehiculos();
        String data = v.getVehiculosIds();

        try {

            if (!StringUtil.isBlank(mensaje)) {
                request.setAttribute("mensaje", mensaje);
            }

            request.setAttribute("data", data);
            request.getRequestDispatcher("pages/nuevaSalida.jsp").forward(request, response);
        } catch (ServletException | IOException ex) {
            System.err.println("Error en paginaSalidaVehiculo : " + ex);
        }

    }

    private void guardarSalida(HttpServletRequest request, HttpServletResponse response) {

        VehiculoSalidas S = new VehiculoSalidas();

        S = S.getVehiculoSalidas(request);

        RegistrarSalidasVehiculo registrar = new RegistrarSalidasVehiculo();

        String mensaje = registrar.RegistrarSalida(S);

        try {
            response.sendRedirect("ControllerVehiculos?pagina=nuevaSalida&mensaje=" + mensaje);
        } catch (IOException ex) {
            System.err.println("ERROR en guardarVehiculo : " + ex);
        }

    }

    private void paginaConcretarSalida(HttpServletRequest request, HttpServletResponse response) {
        String mensaje = request.getParameter("mensaje");
        ActualizarSalidasVehiculo s = new ActualizarSalidasVehiculo();
        String data = s.ObtenerSalidasParaConcretar();
        GetVehiculos v = new GetVehiculos();
        String vehiculos = v.getVehiculosIds();
        try {

            if (!StringUtil.isBlank(mensaje)) {
                request.setAttribute("mensaje", mensaje);
            }

            request.setAttribute("data", data);
            request.setAttribute("vehiculos", vehiculos);
            request.getRequestDispatcher("pages/concretarSalida.jsp").forward(request, response);
        } catch (ServletException | IOException ex) {
            System.err.println("Error en paginaSalidaVehiculo : " + ex);
        }
    }

    private void concretarSalida(HttpServletRequest request, HttpServletResponse response) {

        VehiculoSalidas S = new VehiculoSalidas();

        S = S.getVehiculoSalidas(request);

        ActualizarSalidasVehiculo registrar = new ActualizarSalidasVehiculo();

        String mensaje = registrar.ConcretarSalida(S);

        try {
            response.sendRedirect("ControllerVehiculos?pagina=concretarSalida&mensaje=" + mensaje);
        } catch (IOException ex) {
            System.err.println("ERROR en guardarVehiculo : " + ex);
        }
    }

}
