package uniquindio.edu.co.model;

import java.util.ArrayList;
import java.util.List;

public class Sala implements Gestionable {

    private String codigo;
    private String nombre;
    private int capacidad;
    private EstadoSala estado;
    private List<Asiento> listaAsientos;

    public Sala(String codigo, String nombre) {

        if (codigo == null || codigo.trim().isEmpty()) {
            throw new IllegalArgumentException("El código de la sala es obligatorio.");
        }

        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre de la sala es obligatorio.");
        }

        this.codigo = codigo;
        this.nombre = nombre;
        this.capacidad = 9;
        this.estado = EstadoSala.DISPONIBLE;
        this.listaAsientos = new ArrayList<>();

        crearAsientos();
    }

    private void crearAsientos() {

        String[] filas = {"A", "B", "C"};

        for (int i = 0; i < filas.length; i++) {

            for (int j = 1; j <= 3; j++) {

                String codigoAsiento = filas[i] + j;
                String fila = filas[i];

                Asiento asiento = new Asiento(
                        codigoAsiento,
                        fila,
                        j
                );

                listaAsientos.add(asiento);
            }
        }
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public EstadoSala getEstado() {
        return estado;
    }

    public List<Asiento> getListaAsientos() {
        return listaAsientos;
    }

    public Asiento buscarAsiento(String codigo) {

        Asiento asientoEncontrado = null;

        for (Asiento asiento : listaAsientos) {

            if (asiento.getCodigo().equalsIgnoreCase(codigo)) {
                asientoEncontrado = asiento;
            }
        }

        return asientoEncontrado;
    }

    public int contarAsientosDisponibles() {

        int disponibles = 0;

        for (Asiento asiento : listaAsientos) {

            if (asiento.estaDisponible()) {
                disponibles++;
            }
        }

        return disponibles;
    }

    public int contarAsientosOcupados() {

        int ocupados = 0;

        for (Asiento asiento : listaAsientos) {

            if (!asiento.estaDisponible()) {
                ocupados++;
            }
        }

        return ocupados;
    }

    public boolean estaDisponible() {

        boolean disponible = false;

        if (estado == EstadoSala.DISPONIBLE) {
            disponible = true;
        }

        return disponible;
    }

    @Override
    public void modificar() {

        if (estado == EstadoSala.DISPONIBLE) {
            estado = EstadoSala.FUERA_DE_SERVICIO;
        } else {
            estado = EstadoSala.DISPONIBLE;
        }
    }

    @Override
    public void desactivar() {
        estado = EstadoSala.FUERA_DE_SERVICIO;
    }

    @Override
    public String consultarInformacion() {

        String informacion = "Código: " + codigo
                + "\nNombre: " + nombre
                + "\nCapacidad: " + capacidad
                + "\nAsientos disponibles: " + contarAsientosDisponibles()
                + "\nAsientos ocupados: " + contarAsientosOcupados()
                + "\nEstado: " + estado;

        return informacion;
    }
}