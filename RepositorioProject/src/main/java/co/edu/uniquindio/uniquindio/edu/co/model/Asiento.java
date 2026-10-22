package uniquindio.edu.co.model;

public class Asiento {

    private String codigo;
    private String fila;
    private int numero;
    private EstadoAsiento estado;

    public Asiento(String codigo, String fila, int numero) {

        if (codigo == null || codigo.trim().isEmpty()) {
            throw new IllegalArgumentException("El código del asiento es obligatorio.");
        }

        if (fila == null || fila.trim().isEmpty()) {
            throw new IllegalArgumentException("La fila es obligatoria.");
        }

        if (numero <= 0) {
            throw new IllegalArgumentException("El número debe ser mayor que cero.");
        }

        this.codigo = codigo;
        this.fila = fila;
        this.numero = numero;
        this.estado = EstadoAsiento.DISPONIBLE;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getFila() {
        return fila;
    }

    public int getNumero() {
        return numero;
    }

    public EstadoAsiento getEstado() {
        return estado;
    }

    public boolean estaDisponible() {

        boolean disponible = false;

        if (estado == EstadoAsiento.DISPONIBLE) {
            disponible = true;
        }

        return disponible;
    }

    public void ocupar() {

        if (estado == EstadoAsiento.DISPONIBLE) {
            estado = EstadoAsiento.OCUPADO;
        } else {
            throw new IllegalArgumentException("El asiento ya está ocupado.");
        }
    }

    public void liberar() {

        if (estado == EstadoAsiento.OCUPADO) {
            estado = EstadoAsiento.DISPONIBLE;
        }
    }

    public String consultarEstado() {

        String resultado = "";

        if (estado == EstadoAsiento.DISPONIBLE) {
            resultado = "Disponible";
        } else {
            resultado = "Ocupado";
        }

        return resultado;
    }

    public String consultarInformacion() {

        String informacion = "Código: " + codigo
                + "\nFila: " + fila
                + "\nNúmero: " + numero
                + "\nEstado: " + consultarEstado();

        return informacion;
    }
}