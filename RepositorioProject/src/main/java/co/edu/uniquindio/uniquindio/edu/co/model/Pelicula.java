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

        if (codigo == null || codigo.trim().isEmpty()) {
            throw new IllegalArgumentException("El código de la película es obligatorio.");
        }

        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre de la película es obligatorio.");
        }

        if (genero == null) {
            throw new IllegalArgumentException("Debe seleccionar un género.");
        }

        if (duracion <= 0) {
            throw new IllegalArgumentException("La duración debe ser mayor que cero.");
        }

        if (clasificacion == null) {
            throw new IllegalArgumentException("Debe seleccionar una clasificación.");
        }

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
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre de la película es obligatorio.");
        }

        this.nombre = nombre;
    }

    public void setGenero(GeneroPelicula genero) {
        if (genero == null) {
            throw new IllegalArgumentException("Debe seleccionar un género.");
        }

        this.genero = genero;
    }

    public void setDuracion(int duracion) {
        if (duracion <= 0) {
            throw new IllegalArgumentException("La duración debe ser mayor que cero.");
        }

        this.duracion = duracion;
    }

    public void setClasificacion(ClasificacionEdad clasificacion) {
        if (clasificacion == null) {
            throw new IllegalArgumentException("Debe seleccionar una clasificación.");
        }

        this.clasificacion = clasificacion;
    }

    @Override
    public void modificar() {
        estado = EstadoPelicula.DISPONIBLE;
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