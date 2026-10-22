package uniquindio.edu.co.model;

public class Venta implements Vendible {

    private String codigo;
    private Cliente cliente;
    private Funcion funcion;
    private Asiento asiento;
    private TipoEntrada tipoEntrada;
    private double valor;
    private EstadoVenta estado;

    public Venta(String codigo, Cliente cliente, Funcion funcion,
                 Asiento asiento, TipoEntrada tipoEntrada) {

        if (codigo == null || codigo.trim().isEmpty()) {
            throw new IllegalArgumentException("El código de la venta es obligatorio.");
        }

        if (cliente == null) {
            throw new IllegalArgumentException("El cliente es obligatorio.");
        }

        if (funcion == null) {
            throw new IllegalArgumentException("La función es obligatoria.");
        }

        if (asiento == null) {
            throw new IllegalArgumentException("El asiento es obligatorio.");
        }

        if (tipoEntrada == null) {
            throw new IllegalArgumentException("El tipo de entrada es obligatorio.");
        }

        this.codigo = codigo;
        this.cliente = cliente;
        this.funcion = funcion;
        this.asiento = asiento;
        this.tipoEntrada = tipoEntrada;
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

    public TipoEntrada getTipoEntrada() {
        return tipoEntrada;
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
        boolean resultado = estado == EstadoVenta.ACTIVA;
        return resultado;
    }

    @Override
    public double calcularValor() {
        double resultado = funcion.getValorBase();

        if (tipoEntrada == TipoEntrada.GENERAL) {
            resultado = resultado + Tarifas.GENERAL.getValor();
        } else if (tipoEntrada == TipoEntrada.ESTUDIANTE) {
            resultado = resultado + Tarifas.ESTUDIANTE.getValor();
        } else if (tipoEntrada == TipoEntrada.NINO) {
            resultado = resultado + Tarifas.NINO.getValor();
        } else if (tipoEntrada == TipoEntrada.ADULTO_MAYOR) {
            resultado = resultado + Tarifas.ADULTO_MAYOR.getValor();
        }

        return resultado;
    }

    @Override
    public String generarInformacionVenta() {
        String informacion = "Código de venta: " + codigo
                + "\nCliente: " + cliente.getNombre()
                + "\nPelícula: " + funcion.getPelicula().getNombre()
                + "\nFunción: " + funcion.getCodigo()
                + "\nFecha: " + funcion.getFecha()
                + "\nHora: " + funcion.getHora()
                + "\nAsiento: " + asiento.getCodigo()
                + "\nTipo de entrada: " + tipoEntrada
                + "\nValor: $" + valor
                + "\nEstado: " + estado;

        return informacion;
    }

    public String consultarInformacion() {
        String informacion = generarInformacionVenta();
        return informacion;
    }
}