package Model;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class EliminarArchivo {

    public void eliminarArchivo(String ruta) {
        File file = new File(ruta);

        if (file.exists()) {
            Path path = Paths.get(ruta);
            try {
                boolean result = Files.deleteIfExists(path);
                if (result) {
                    System.out.println("El archivo se elimino!");
                } else {
                    System.out.println("Lo sentimos, no se pudo eliminar el archivo.");
                }
            } catch (IOException e) {
                System.err.println("ERROR EN EliminarArchivo : " + e);
            }
        }

    }
}
