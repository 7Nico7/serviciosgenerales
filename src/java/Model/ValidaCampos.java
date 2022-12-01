package Model;

import org.apache.poi.util.StringUtil;

public class ValidaCampos {

    public String valida(String[] campos, String[] campoNombre) {
        String respuesta = null;

        for (int i = 0; i < campos.length; i++) {
            if ("0".equals(campos[i])) {

                respuesta = """
                            <div class="alert alert-danger alert-dismissible fade show" role="alert">
                              <strong>Error! </strong> """ + campoNombre[i] + ".\n"
                        + "  <button type=\"button\" class=\"btn-close\" data-bs-dismiss=\"alert\" aria-label=\"Close\"></button>\n"
                        + "</div>";
                i = campos.length;

            } else {

                if (!StringUtil.isBlank(campos[i])) {
                    respuesta = "correcto";
                } else {

                    respuesta = """
                            <div class="alert alert-danger alert-dismissible fade show" role="alert">
                              <strong>Error! </strong> """ + campoNombre[i] + ".\n"
                            + "  <button type=\"button\" class=\"btn-close\" data-bs-dismiss=\"alert\" aria-label=\"Close\"></button>\n"
                            + "</div>";
                    i = campos.length;
                }
            }
        }
        return respuesta;
    }
}
