package uniquindio.edu.co.model;

import java.util.ArrayList;
import java.util.List;

public class Funcion {

    private String codigo;
    private Pelicula pelicula;
    private Sala sala;
    private String fecha;
    private String hora;
    private double valorBase;
    private EstadoFuncion estado;
    private List<Asiento> listaAsientos;

    public Funcion(String codigo, Pelicula pelicula, Sala sala,
                   String fecha, String hora, double valorBase) {

        if (codigo == null || codigo.trim().isEmpty()) {
            throw new IllegalArgumentException("El código de la función es obligatorio.");
        }

        if (pelicula == null) {
            throw new IllegalArgumentException("Debe seleccionar una película.");
        }

        if (sala == null) {
            throw new IllegalArgumentException("Debe seleccionar una sala.");
        }

        if (fecha == null || fecha.trim().isEmpty()) {
            throw new IllegalArgumentException("La fecha es obligatoria.");
        }

        if (hora == null || hora.trim().isEmpty()) {
            throw new IllegalArgumentException("La hora es obligatoria.");
        }

        if (valorBase <= 0) {
            throw new IllegalArgumentException("El valor base debe ser mayor que cero.");
        }

        this.codigo = codigo;
        this.pelicula = pelicula;
        this.sala = sala;
        this.fecha = fecha;
        this.hora = hora;
        this.valorBase = valorBase;
        this.estado = EstadoFuncion.PROGRAMADA;
        this.listaAsientos = new ArrayList<>();

        for (Asiento asiento : sala.getListaAsientos()) {
            listaAsientos.add(asiento);
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

    public double getValorBase() {
        return valorBase;
    }

    public EstadoFuncion getEstado() {
        return estado;
    }

    public List<Asiento> getListaAsientos() {
        return listaAsientos;
    }

    public void setPelicula(Pelicula pelicula) {
        if (pelicula == null) {
            throw new IllegalArgumentException("Debe seleccionar una película.");
        }

        this.pelicula = pelicula;
    }

    public void setSala(Sala sala) {
        if (sala == null) {
            throw new IllegalArgumentException("Debe seleccionar una sala.");
        }

        this.sala = sala;
        this.listaAsientos = new ArrayList<>();

        for (Asiento asiento : sala.getListaAsientos()) {
            listaAsientos.add(asiento);
        }
    }

    public void setFecha(String fecha) {
        if (fecha == null || fecha.trim().isEmpty()) {
            throw new IllegalArgumentException("La fecha es obligatoria.");
        }

        this.fecha = fecha;
    }

    public void setHora(String hora) {
        if (hora == null || hora.trim().isEmpty()) {
            throw new IllegalArgumentException("La hora es obligatoria.");
        }

        this.hora = hora;
    }

    public void setValorBase(double valorBase) {
        if (valorBase <= 0) {
            throw new IllegalArgumentException("El valor base debe ser mayor que cero.");
        }

        this.valorBase = valorBase;
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
        boolean programada = false;

        if (estado == EstadoFuncion.PROGRAMADA) {
            programada = true;
        }

        return programada;
    }

    public Asiento buscarAsiento(String codigoAsiento) {
        Asiento asientoEncontrado = null;

        for (Asiento asiento : listaAsientos) {
            if (asiento.getCodigo().equalsIgnoreCase(codigoAsiento)) {
                asientoEncontrado = asiento;
            }
        }

        return asientoEncontrado;
    }

    public int contarAsientosDisponibles(ServicioVentas servicioVentas) {
        int disponibles = 0;

        for (Asiento asiento : listaAsientos) {
            if (servicioVentas.estaDisponible(this, asiento)) {
                disponibles++;
            }
        }

        return disponibles;
    }

    public int contarAsientosOcupados(ServicioVentas servicioVentas) {
        int ocupados = 0;

        for (Asiento asiento : listaAsientos) {
            if (!servicioVentas.estaDisponible(this, asiento)) {
                ocupados++;
            }
        }

        return ocupados;
    }

    public String consultarAsientos(ServicioVentas servicioVentas) {
        String informacion = "ASIENTOS DE LA FUNCIÓN " + codigo + "\n\n";

        for (int i = 0; i < listaAsientos.size(); i++) {
            Asiento asiento = listaAsientos.get(i);

            informacion = informacion + asiento.getCodigo() + " - ";

            if (servicioVentas.estaDisponible(this, asiento)) {
                informacion = informacion + "Disponible";
            } else {
                informacion = informacion + "Ocupado";
            }

            informacion = informacion + "\n";
        }

        return informacion;
    }

    public String consultarInformacion() {
        String informacion = "Código: " + codigo
                + "\nPelícula: " + pelicula.getNombre()
                + "\nSala: " + sala.getNombre()
                + "\nFecha: " + fecha
                + "\nHora: " + hora
                + "\nValor base: $" + valorBase
                + "\nEstado: " + estado;

        return informacion;
    }
}