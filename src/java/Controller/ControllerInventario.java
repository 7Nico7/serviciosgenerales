package Controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.util.StringUtil;
import Model.Entrada;
import Model.GetInventario;
import Model.Inventario;
import Model.RegistrarProducto;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "ControllerInventario", urlPatterns = {"/ControllerInventario"})
public class ControllerInventario extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String pagina = request.getParameter("pagina");

        if (!StringUtil.isBlank(pagina)) {

            switch (pagina) {
                case "inventario":
                    paginaInventario(request, response);
                    break;
                default:
                    request.setAttribute("error", "Error ControllerInventario: El valor pagina es desconocido");
                    request.getRequestDispatcher("pages/Error.jsp").forward(request, response);
            }

        } else {
            request.setAttribute("error", "Error ControllerInventario: El valor pagina es nulo");
            request.getRequestDispatcher("pages/Error.jsp").forward(request, response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String guardar = request.getParameter("guardar");

        if (!StringUtil.isBlank(guardar)) {

            switch (guardar) {
                case "entrada":
                    Entrada(request, response);
                    break;
                case "salida":
                    Salida(request, response);
                    break;
                case "producto":
                    Producto(request, response);
                    break;
                default:
                    request.setAttribute("error", "Error El parametro guardar en ControllerInvetario es Desconocido");
                    request.getRequestDispatcher("pages/Error.jsp").forward(request, response);
            }

        } else {
            request.setAttribute("error", "Error El parametro guardar en ControllerInvetario es NULO");
            request.getRequestDispatcher("pages/Error.jsp").forward(request, response);
        }

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private void paginaInventario(HttpServletRequest request, HttpServletResponse response) {
        GetInventario I = new GetInventario();
        String data = I.getProductos();
        try {
            request.setAttribute("data", data);
            request.getRequestDispatcher("pages/inventario/inventario.jsp").forward(request, response);
        } catch (ServletException | IOException ex) {
            System.err.println("error en paginaVehiculos : " + ex);
        }
    }

    private void Entrada(HttpServletRequest request, HttpServletResponse response) {

        Entrada E = new Entrada();

        E = E.getEntrada(request);

    }

    private void Salida(HttpServletRequest request, HttpServletResponse response) {

    }

    private void Producto(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html;charset=UTF-8");
        Inventario I = new Inventario();
        try {

            PrintWriter out;
            out = response.getWriter();

            I = I.getInventario(request);

            RegistrarProducto registrar = new RegistrarProducto();

            String mensaje = registrar.registrarProducto(I);
            out.println(mensaje);
        } catch (IOException ex) {
            System.err.println("Error en Producto : " + ex);
        }

    }

}
