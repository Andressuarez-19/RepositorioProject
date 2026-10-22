package uniquindio.edu.co.model;

public enum Tarifas {

    GENERAL(15000),
    ESTUDIANTE(12000),
    NINO(10000),
    ADULTO_MAYOR(11000);

    private double valor;

    Tarifas(double valor) {
        this.valor = valor;
    }

    public double getValor() {
        return valor;
    }
}