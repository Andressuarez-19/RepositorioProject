package uniquindio.edu.co.model;

public class Operario extends Persona {

    private String codigo;

    public Operario(String identificacion, String nombre, String codigo) {
        super(identificacion, nombre);
        this.codigo = codigo;
    }

    public String getCodigo() {
        return codigo;
    }

    @Override
    public String consultarInformacion() {
        String informacion = "Código de operario: " + codigo
                + "\nIdentificación: " + getIdentificacion()
                + "\nNombre: " + getNombre();

        return informacion;
    }
}