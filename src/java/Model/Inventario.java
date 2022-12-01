package Model;

import jakarta.servlet.http.HttpServletRequest;
import org.apache.poi.util.StringUtil;

public class Inventario {
    
    private int cve_producto;
    private String codigo;
    private String descripcion;
    private int proyeccion_anual;
    private int proyeccion_anual_restante;
    private int entrada;
    private int salida;
    private int existencia;

    public int getCve_producto() {
        return cve_producto;
    }

    public void setCve_producto(int cve_producto) {
        this.cve_producto = cve_producto;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getProyeccion_anual() {
        return proyeccion_anual;
    }

    public void setProyeccion_anual(int proyeccion_anual) {
        this.proyeccion_anual = proyeccion_anual;
    }

    public int getProyeccion_anual_restante() {
        return proyeccion_anual_restante;
    }

    public void setProyeccion_anual_restante(int proyeccion_anual_restante) {
        this.proyeccion_anual_restante = proyeccion_anual_restante;
    }

    public int getEntrada() {
        return entrada;
    }

    public void setEntrada(int entrada) {
        this.entrada = entrada;
    }

    public int getSalida() {
        return salida;
    }

    public void setSalida(int salida) {
        this.salida = salida;
    }

    public int getExistencia() {
        return existencia;
    }

    public void setExistencia(int existencia) {
        this.existencia = existencia;
    }

    public Inventario getInventario(HttpServletRequest req) {
        Inventario I = new Inventario();

        isNumeric num = new isNumeric();

        I.setCodigo(req.getParameter("codigoP"));
        I.setDescripcion(req.getParameter("descripcionP"));

        String entrad = req.getParameter("entrada");
        String salid = req.getParameter("salida");
        String proyeccion_anua = req.getParameter("proyeccion_anual");
        String proyeccion_anual_res = req.getParameter("proyeccion_anual_restante");
        String existenci = req.getParameter("existencia");

        if (!StringUtil.isBlank(entrad) && num.num(entrad)) {
            I.setEntrada(Integer.parseInt(req.getParameter("entrada")));
        }

        if (!StringUtil.isBlank(salid) && num.num(salid)) {
            I.setSalida(Integer.parseInt(salid));
        }

        if (!StringUtil.isBlank(existenci) && num.num(existenci)) {
            I.setExistencia(Integer.parseInt(existenci));
        }

        if (!StringUtil.isBlank(proyeccion_anua) && num.num(proyeccion_anua)) {
            I.setProyeccion_anual(Integer.parseInt(proyeccion_anua));
        }
        if (!StringUtil.isBlank(proyeccion_anual_res) && num.num(proyeccion_anual_res)) {
            I.setProyeccion_anual_restante(Integer.parseInt(proyeccion_anual_res));
        }

        return I;
    }

}
