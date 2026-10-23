package uniquindio.edu.co.model;

public class Asiento {

    private String codigo;
    private String fila;
    private int numero;
    private EstadoAsiento estado;

    public Asiento(String codigo, String fila, int numero) {
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

    public void setEstado(EstadoAsiento estado) {
        this.estado = estado;
    }

    public boolean estaDisponible() {
        boolean resultado = false;

        if (estado == EstadoAsiento.DISPONIBLE) {
            resultado = true;
        }

        return resultado;
    }

    public void ocupar() {
        if (estado == EstadoAsiento.DISPONIBLE) {
            estado = EstadoAsiento.OCUPADO;
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