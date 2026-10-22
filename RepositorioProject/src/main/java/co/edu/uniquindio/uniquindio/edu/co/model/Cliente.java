package uniquindio.edu.co.model;

public class Cliente {

    private String identificacion;
    private String nombre;
    private TipoCliente tipoCliente;

    public Cliente(String identificacion, String nombre, TipoCliente tipoCliente) {
        if (identificacion == null || identificacion.trim().isEmpty()) {
            throw new IllegalArgumentException("La identificación es obligatoria.");
        }

        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre es obligatorio.");
        }

        if (tipoCliente == null) {
            throw new IllegalArgumentException("Debe seleccionar un tipo de cliente.");
        }

        this.identificacion = identificacion;
        this.nombre = nombre;
        this.tipoCliente = tipoCliente;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public String getNombre() {
        return nombre;
    }

    public TipoCliente getTipoCliente() {
        return tipoCliente;
    }

    public void setNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre es obligatorio.");
        }

        this.nombre = nombre;
    }

    public void setTipoCliente(TipoCliente tipoCliente) {
        if (tipoCliente == null) {
            throw new IllegalArgumentException("Debe seleccionar un tipo de cliente.");
        }

        this.tipoCliente = tipoCliente;
    }

    public String consultarInformacion() {
        String informacion = "Identificación: " + identificacion
                + "\nNombre: " + nombre
                + "\nTipo de cliente: " + tipoCliente;

        return informacion;
    }
}