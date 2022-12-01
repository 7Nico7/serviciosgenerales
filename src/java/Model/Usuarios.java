package Model;

public class Usuarios {

    private int cve_usuarios;
    private String nombre;
    private String correo;
    private String contra;
    private String tipo_usuario;

    public Usuarios() {
    }

    public int getCve_usuarios() {
        return cve_usuarios;
    }

    public void setCve_usuarios(int cve_usuarios) {
        this.cve_usuarios = cve_usuarios;
    }

    public String getTipo_usuario() {
        return tipo_usuario;
    }

    public void setTipo_usuario(String tipo_usuario) {
        this.tipo_usuario = tipo_usuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContra() {
        return contra;
    }

    public void setContra(String contra) {
        this.contra = contra;
    }

}
