package uniquindio.edu.co.model;

public class Cliente extends Persona {

    private TipoCliente tipoCliente;

    public Cliente(String identificacion, String nombre, TipoCliente tipoCliente) {
        super(identificacion, nombre);
        this.tipoCliente = tipoCliente;
    }

    public TipoCliente getTipoCliente() {
        return tipoCliente;
    }

    public void setTipoCliente(TipoCliente tipoCliente) {
        this.tipoCliente = tipoCliente;
    }

    @Override
    public String consultarInformacion() {
        String informacion = "Identificación: " + getIdentificacion()
                + "\nNombre: " + getNombre()
                + "\nTipo de cliente: " + tipoCliente;

        return informacion;
    }
}