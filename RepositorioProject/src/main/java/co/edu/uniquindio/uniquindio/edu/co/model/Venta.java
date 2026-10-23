package uniquindio.edu.co.model;

public class Venta implements Vendible {

    private String codigo;
    private Cliente cliente;
    private Funcion funcion;
    private Asiento asiento;
    private double valor;
    private EstadoVenta estado;

    public Venta(String codigo, Cliente cliente, Funcion funcion,
                 Asiento asiento) {

        this.codigo = codigo;
        this.cliente = cliente;
        this.funcion = funcion;
        this.asiento = asiento;
        this.valor = calcularValor();
        this.estado = EstadoVenta.ACTIVA;
    }

    public String getCodigo() {
        return codigo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Funcion getFuncion() {
        return funcion;
    }

    public Asiento getAsiento() {
        return asiento;
    }

    public double getValor() {
        return valor;
    }

    public EstadoVenta getEstado() {
        return estado;
    }

    public void cancelar() {
        if (estado == EstadoVenta.ACTIVA) {
            estado = EstadoVenta.CANCELADA;
        }
    }

    public boolean estaActiva() {
        boolean resultado = false;

        if (estado == EstadoVenta.ACTIVA) {
            resultado = true;
        }

        return resultado;
    }

    @Override
    public double calcularValor() {
        double resultado = 0;

        if (cliente != null) {
            if (cliente.getTipoCliente() == TipoCliente.GENERAL) {
                resultado = Tarifas.GENERAL.getValor();
            } else if (cliente.getTipoCliente() == TipoCliente.ESTUDIANTE) {
                resultado = Tarifas.ESTUDIANTE.getValor();
            } else if (cliente.getTipoCliente() == TipoCliente.NINO) {
                resultado = Tarifas.NINO.getValor();
            } else if (cliente.getTipoCliente() == TipoCliente.ADULTO_MAYOR) {
                resultado = Tarifas.ADULTO_MAYOR.getValor();
            }
        }

        return resultado;
    }

    @Override
    public String generarInformacionVenta() {
        String informacion = "Código de venta: " + codigo
                + "\nCliente: " + cliente.getNombre()
                + "\nTipo de cliente: " + cliente.getTipoCliente()
                + "\nPelícula: " + funcion.getPelicula().getNombre()
                + "\nFunción: " + funcion.getCodigo()
                + "\nFecha: " + funcion.getFecha()
                + "\nHora: " + funcion.getHora()
                + "\nSala: " + funcion.getSala().getNombre()
                + "\nAsiento: " + asiento.getCodigo()
                + "\nValor de la boleta: $" + valor
                + "\nEstado de la venta: " + estado;

        return informacion;
    }
}