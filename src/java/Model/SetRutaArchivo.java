package Model;

import jakarta.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.apache.poi.util.StringUtil;

public class SetRutaArchivo {
    private final String pathFiles = "C:\\Users\\Nico\\Documents\\NetBeansProjects\\serviciosgenerales\\web\\pages\\evidencias";
    private final File almacen = new File(pathFiles);
    private final String[] extenciones;
    
    public SetRutaArchivo() {
        this.extenciones = new String[]{".ico", ".png", ".jpg", ".jpeg", ".jfif", "mp4", "pdf"};
    }

    public Archivo RutaArchivoGuardado(Part evidencia) {
        Archivo archivo = new Archivo();
        Part part = evidencia;
        boolean hayArchivo = hayPart(part);
        if (hayArchivo == false) {
            System.out.println("No ha seleccionado un archivo");
            archivo.setNombre(" ");
            archivo.setRuta(" ");
            return archivo;
        } else if (isExtension(part.getSubmittedFileName(), extenciones)) {
            //Guarda y Obtiene la ruta
            archivo = GuardarArchivo(part, almacen);
        } else {
            System.out.println("El archivo no es Permitido");
            return null;
        }

        return archivo;

    }

    private Archivo GuardarArchivo(Part part, File almacen) {
        String pathAbsolute = "";
        String name = "";

        try {

            Path path = Paths.get(part.getSubmittedFileName());
            String fileName = path.getFileName().toString();
            InputStream input = part.getInputStream();

            if (input != null) {
                File file = new File(almacen, fileName);
                name = file.getName();
                pathAbsolute = file.getAbsolutePath();
                Files.copy(input, file.toPath());
            }

        } catch (IOException e) {
            System.err.println("ERROR GuardarArchivo : " + e);
        }
        Archivo archivo = new Archivo(name, pathAbsolute);

        return archivo;
    }

    //Valida las extenciones de los archivos
    private boolean isExtension(String fileName, String[] extensions) {
        for (String ex : extensions) {
            if (fileName.toLowerCase().endsWith(ex)) {
                return true;
            }
        }
        return false;
    }
    
        private boolean hayPart(Part evidencia) {
        Part part = evidencia;
        Path path = Paths.get(part.getSubmittedFileName());
        String name = path.getFileName().toString();

        return !StringUtil.isBlank(name);
    }

}
