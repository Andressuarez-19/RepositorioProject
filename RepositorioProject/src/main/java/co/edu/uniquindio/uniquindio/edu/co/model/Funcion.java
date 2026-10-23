package uniquindio.edu.co.model;

import java.util.ArrayList;
import java.util.List;

public class Funcion {

    private String codigo;
    private Pelicula pelicula;
    private Sala sala;
    private String fecha;
    private String hora;
    private EstadoFuncion estado;
    private List<Asiento> listaAsientos;

    public Funcion(String codigo, Pelicula pelicula, Sala sala,
                   String fecha, String hora) {

        this.codigo = codigo;
        this.pelicula = pelicula;
        this.sala = sala;
        this.fecha = fecha;
        this.hora = hora;
        this.estado = EstadoFuncion.PROGRAMADA;
        this.listaAsientos = new ArrayList<Asiento>();

        if (sala != null) {
            for (Asiento asiento : sala.getListaAsientos()) {
                listaAsientos.add(asiento);
            }
        }
    }

    public String getCodigo() {
        return codigo;
    }

    public Pelicula getPelicula() {
        return pelicula;
    }

    public Sala getSala() {
        return sala;
    }

    public String getFecha() {
        return fecha;
    }

    public String getHora() {
        return hora;
    }

    public EstadoFuncion getEstado() {
        return estado;
    }

    public List<Asiento> getListaAsientos() {
        return listaAsientos;
    }

    public void setPelicula(Pelicula pelicula) {
        this.pelicula = pelicula;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
        listaAsientos = new ArrayList<Asiento>();

        if (sala != null) {
            for (Asiento asiento : sala.getListaAsientos()) {
                listaAsientos.add(asiento);
            }
        }
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public void finalizarFuncion() {
        if (estado == EstadoFuncion.PROGRAMADA) {
            estado = EstadoFuncion.FINALIZADA;
        }
    }

    public void cancelarFuncion() {
        if (estado == EstadoFuncion.PROGRAMADA) {
            estado = EstadoFuncion.CANCELADA;
        }
    }

    public boolean estaProgramada() {
        boolean resultado = false;

        if (estado == EstadoFuncion.PROGRAMADA) {
            resultado = true;
        }

        return resultado;
    }

    public Asiento buscarAsiento(String codigo) {
        Asiento resultado = null;

        if (codigo != null) {
            for (Asiento asiento : listaAsientos) {
                if (asiento.getCodigo().equalsIgnoreCase(codigo)) {
                    resultado = asiento;
                }
            }
        }

        return resultado;
    }

    public int contarAsientosDisponibles(ServicioVentas servicioVentas) {
        int cantidad = 0;

        if (servicioVentas != null) {
            for (Asiento asiento : listaAsientos) {
                if (servicioVentas.estaDisponible(this, asiento)) {
                    cantidad++;
                }
            }
        }

        return cantidad;
    }

    public int contarAsientosOcupados(ServicioVentas servicioVentas) {
        int cantidad = 0;

        if (servicioVentas != null) {
            for (Asiento asiento : listaAsientos) {
                if (!servicioVentas.estaDisponible(this, asiento)) {
                    cantidad++;
                }
            }
        }

        return cantidad;
    }

    public String consultarAsientos(ServicioVentas servicioVentas) {
        String informacion = "";

        for (Asiento asiento : listaAsientos) {
            informacion = informacion + asiento.getCodigo();

            if (servicioVentas != null
                    && servicioVentas.estaDisponible(this, asiento)) {
                informacion = informacion + " - Disponible\n";
            } else {
                informacion = informacion + " - Ocupado\n";
            }
        }

        return informacion;
    }

    public String consultarInformacion() {
        String informacion = "Código de función: " + codigo
                + "\nPelícula: " + pelicula.getNombre()
                + "\nSala: " + sala.getNombre()
                + "\nFecha: " + fecha
                + "\nHora: " + hora
                + "\nEstado: " + estado;

        return informacion;
    }
}