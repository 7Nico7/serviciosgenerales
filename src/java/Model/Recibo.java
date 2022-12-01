package Model;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.Part;
import java.io.IOException;
import org.joda.time.DateTime;

public class Recibo extends Medidor {

    private String cve_recibo;
    private String year;
    private String periodoInicio;
    private String periodoFinal;
    private String periodo;
    private String consumo;
    private String saldo;
    private String nombre_archivo;
    private String archivo;
    private Part part;
    private String eliminarArchivo;
    private String nombre_archivoReporte;
    private String archivo_reporte;
    private Part partReporte;
    private String eliminarArchivoReporte;

    public Recibo() {
    }

    public String getCve_recibo() {
        return cve_recibo;
    }

    public void setCve_recibo(String cve_recibo) {
        this.cve_recibo = cve_recibo;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getConsumo() {
        return consumo;
    }

    public void setConsumo(String consumo) {
        this.consumo = consumo;
    }

    public String getSaldo() {
        return saldo;
    }

    public void setSaldo(String saldo) {
        this.saldo = saldo;
    }

    public String getArchivo() {
        return archivo;
    }

    public void setArchivo(String archivo) {
        this.archivo = archivo;
    }

    public String getNombre_archivo() {
        return nombre_archivo;
    }

    public void setNombre_archivo(String nombre_archivo) {
        this.nombre_archivo = nombre_archivo;
    }

    public Part getPart() {
        return part;
    }

    public void setPart(Part part) {
        this.part = part;
    }

    public String getEliminarArchivo() {
        return eliminarArchivo;
    }

    public void setEliminarArchivo(String eliminarArchivo) {
        this.eliminarArchivo = eliminarArchivo;
    }

    public String getPeriodoInicio() {
        return periodoInicio;
    }

    public void setPeriodoInicio(String periodoInicio) {
        this.periodoInicio = periodoInicio;
    }

    public String getPeriodoFinal() {
        return periodoFinal;
    }

    public void setPeriodoFinal(String periodoFinal) {
        this.periodoFinal = periodoFinal;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public String getNombre_archivoReporte() {
        return nombre_archivoReporte;
    }

    public void setNombre_archivoReporte(String nombre_archivoReporte) {
        this.nombre_archivoReporte = nombre_archivoReporte;
    }

    public String getArchivo_reporte() {
        return archivo_reporte;
    }

    public void setArchivo_reporte(String archivo_reporte) {
        this.archivo_reporte = archivo_reporte;
    }

    public Part getPartReporte() {
        return partReporte;
    }

    public void setPartReporte(Part partReporte) {
        this.partReporte = partReporte;
    }

    public String getEliminarArchivoReporte() {
        return eliminarArchivoReporte;
    }

    public void setEliminarArchivoReporte(String eliminarArchivoReporte) {
        this.eliminarArchivoReporte = eliminarArchivoReporte;
    }
    
    

    public Recibo getRecibo(HttpServletRequest request, boolean isActualizar) {
        Recibo R = new Recibo();
        SetRutaArchivo guardar = new SetRutaArchivo();
        Archivo archivo = new Archivo();
        Archivo archivoReporte = new Archivo();
        R.setCve_recibo(request.getParameter("cve_recibo"));
        R.setCve_medidor(Integer.parseInt(request.getParameter("medidor")));
        R.setYear(obtenerYear(request.getParameter("periodoInicio")));

        R.setPeriodoInicio(request.getParameter("periodoInicio"));
        R.setPeriodoFinal(request.getParameter("periodoFinal"));
        String periodo = Periodo(obtenerMes(request.getParameter("periodoInicio"))) + "-" +  Periodo(obtenerMes(request.getParameter("periodoFinal")));
        R.setPeriodo(periodo);
        R.setConsumo(request.getParameter("consumo"));
        R.setSaldo(request.getParameter("saldo"));

        //Se valida si se va actualiza
        if (isActualizar == true) {
            try {
                //Recibo pdf
                R.setPart(request.getPart("archivoCFE"));
                R.setEliminarArchivo(request.getParameter("eliminarArchivoCFE"));
                //Reporte pdf
                R.setPartReporte(request.getPart("archivoReporte"));
                R.setEliminarArchivoReporte(request.getParameter("eliminarArchivoReporte"));
                
            } catch (IOException | ServletException ex) {
                System.err.println("Error en obtener archivoCFE " + ex);
            }
            archivo.setNombre(" ");
            archivo.setRuta(" ");
        } else {
            try {
                //Guardar recibo
                archivo = guardar.RutaArchivoGuardado(request.getPart("archivoCFE"));
                
                //Guardar Reporte
                archivoReporte = guardar.RutaArchivoGuardado(request.getPart("archivoReporte"));

            } catch (IOException | ServletException ex) {
                System.err.println("Error en obtener archivoCFE :" + ex);
            }
        }
        R.setNombre_archivo(archivo.getNombre());
        R.setArchivo(archivo.getRuta());
        R.setNombre_archivoReporte(archivoReporte.getNombre());
        R.setArchivo_reporte(archivoReporte.getRuta());
        return R;

    }

    private String Periodo(String mes) {

        if (mes
                != null) {
            switch (mes) {
                case "1":
                    periodo = "Ene";
                    break;
                case "2":
                    periodo = "Feb";
                    break;
                case "3":
                    periodo = "Mar";
                    break;
                case "4":
                    periodo = "Abr";
                    break;
                case "5":
                    periodo = "May";
                    break;
                case "6":
                    periodo = "Jun";
                    break;
                case "7":
                    periodo = "Jul";
                    break;
                case "8":
                    periodo = "Ago";
                    break;
                case "9":
                    periodo = "Sep";
                    break;
                case "10":
                    periodo = "Oct";
                    break;
                case "11":
                    periodo = "Nov";
                    break;
                case "12":
                    periodo = "Dic";
                    break;
                default:
                    periodo = "0";
            }
        }

        return periodo;
    }

    private String obtenerMes(String fecha) {
        //Obtiene el mes de la Fecha
        DateTime datetime = new DateTime(fecha);
        int month = Integer.parseInt(datetime.toString("MM"));
        String mes = Integer.toString(month);
        return mes;
    }

    private String obtenerYear(String fecha) {
        //Obtiene el mes de la Fecha
        DateTime datetime = new DateTime(fecha);
        int year = Integer.parseInt(datetime.toString("YYYY"));
        System.err.println("La FECHA #### " + year);
        String mes = Integer.toString(year);
        return mes;
    }

}
