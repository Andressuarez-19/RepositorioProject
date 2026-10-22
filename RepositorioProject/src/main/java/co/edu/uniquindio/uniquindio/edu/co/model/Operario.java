package uniquindio.edu.co.model;

public class Operario {

    private String identificacion;
    private String nombre;
    private String codigo;

    public Operario(String identificacion, String nombre, String codigo) {

        if (identificacion == null || identificacion.trim().isEmpty()) {
            throw new IllegalArgumentException("La identificación es obligatoria.");
        }

        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre es obligatorio.");
        }

        if (codigo == null || codigo.trim().isEmpty()) {
            throw new IllegalArgumentException("El código del operario es obligatorio.");
        }

        this.identificacion = identificacion;
        this.nombre = nombre;
        this.codigo = codigo;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre es obligatorio.");
        }

        this.nombre = nombre;
    }

    public String consultarInformacion() {
        String informacion = "Código: " + codigo
                + "\nIdentificación: " + identificacion
                + "\nNombre: " + nombre;

        return informacion;
    }
}