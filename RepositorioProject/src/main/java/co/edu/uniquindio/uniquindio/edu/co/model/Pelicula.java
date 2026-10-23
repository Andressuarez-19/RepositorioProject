package uniquindio.edu.co.model;

public class Pelicula implements Gestionable {

    private String codigo;
    private String nombre;
    private GeneroPelicula genero;
    private int duracion;
    private ClasificacionEdad clasificacion;
    private EstadoPelicula estado;

    public Pelicula(String codigo, String nombre, GeneroPelicula genero,
                    int duracion, ClasificacionEdad clasificacion) {

        this.codigo = codigo;
        this.nombre = nombre;
        this.genero = genero;
        this.duracion = duracion;
        this.clasificacion = clasificacion;
        this.estado = EstadoPelicula.DISPONIBLE;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public GeneroPelicula getGenero() {
        return genero;
    }

    public int getDuracion() {
        return duracion;
    }

    public ClasificacionEdad getClasificacion() {
        return clasificacion;
    }

    public EstadoPelicula getEstado() {
        return estado;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setGenero(GeneroPelicula genero) {
        this.genero = genero;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public void setClasificacion(ClasificacionEdad clasificacion) {
        this.clasificacion = clasificacion;
    }

    public boolean estaDisponible() {
        boolean resultado = false;

        if (estado == EstadoPelicula.DISPONIBLE) {
            resultado = true;
        }

        return resultado;
    }

    @Override
    public void modificar() {
        // La película conserva su estado actual.
        // Sus datos se pueden cambiar mediante los métodos set.
    }

    @Override
    public void desactivar() {
        estado = EstadoPelicula.NO_DISPONIBLE;
    }

    @Override
    public String consultarInformacion() {
        String informacion = "Código: " + codigo
                + "\nNombre: " + nombre
                + "\nGénero: " + genero
                + "\nDuración: " + duracion + " minutos"
                + "\nClasificación: " + clasificacion
                + "\nEstado: " + estado;

        return informacion;
    }
}