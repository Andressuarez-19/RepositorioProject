package uniquindio.edu.co.model;

import java.util.ArrayList;
import java.util.List;

public class ServicioVentas {

    private List<Venta> listaVentas;

    public ServicioVentas() {
        listaVentas = new ArrayList<Venta>();
    }

    public List<Venta> getListaVentas() {
        return listaVentas;
    }

    public boolean estaDisponible(Funcion funcion, Asiento asiento) {
        boolean disponible = true;

        for (Venta venta : listaVentas) {
            if (venta.estaActiva()) {
                if (venta.getFuncion().getCodigo().equalsIgnoreCase(funcion.getCodigo())
                        && venta.getAsiento().getCodigo().equalsIgnoreCase(asiento.getCodigo())) {
                    disponible = false;
                }
            }
        }

        return disponible;
    }

    public void registrarVenta(Venta venta) {
        boolean existe = false;

        if (venta != null) {
            for (Venta ventaRegistrada : listaVentas) {
                if (ventaRegistrada.getCodigo().equalsIgnoreCase(venta.getCodigo())) {
                    existe = true;
                }
            }

            if (!existe && venta.getFuncion().estaProgramada()
                    && estaDisponible(venta.getFuncion(), venta.getAsiento())) {
                listaVentas.add(venta);
            }
        }
    }

    public Venta buscarVenta(String codigo) {
        Venta resultado = null;

        if (codigo != null) {
            for (Venta venta : listaVentas) {
                if (venta.getCodigo().equalsIgnoreCase(codigo)) {
                    resultado = venta;
                }
            }
        }

        return resultado;
    }

    public void cancelarVenta(String codigo) {
        Venta venta = buscarVenta(codigo);

        if (venta != null) {
            if (venta.estaActiva()) {
                venta.cancelar();
            }
        }
    }

    public int contarVentasActivas() {
        int cantidad = 0;

        for (Venta venta : listaVentas) {
            if (venta.estaActiva()) {
                cantidad++;
            }
        }

        return cantidad;
    }

    public double calcularIngresos() {
        double total = 0;

        for (Venta venta : listaVentas) {
            if (venta.estaActiva()) {
                total = total + venta.getValor();
            }
        }

        return total;
    }

    public String consultarVentas() {
        String informacion = "";

        for (Venta venta : listaVentas) {
            informacion = informacion + venta.generarInformacionVenta()
                    + "\n-------------------------\n";
        }

        if (listaVentas.size() == 0) {
            informacion = "No hay ventas registradas.";
        }

        return informacion;
    }

    public String consultarVentasActivas() {
        String informacion = "";
        int cantidad = 0;

        for (Venta venta : listaVentas) {
            if (venta.estaActiva()) {
                informacion = informacion + venta.generarInformacionVenta()
                        + "\n-------------------------\n";
                cantidad++;
            }
        }

        if (cantidad == 0) {
            informacion = "No hay ventas activas.";
        }

        return informacion;
    }
}