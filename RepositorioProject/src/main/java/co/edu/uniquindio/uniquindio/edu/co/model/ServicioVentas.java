package uniquindio.edu.co.model;

import java.util.ArrayList;
import java.util.List;

public class ServicioVentas {

    private List<Venta> listaVentas;

    public ServicioVentas() {
        listaVentas = new ArrayList<>();
    }

    public List<Venta> getListaVentas() {
        return listaVentas;
    }

    public boolean estaDisponible(Funcion funcion, Asiento asiento) {
        boolean disponible = true;

        for (Venta venta : listaVentas) {
            if (venta.estaActiva()
                    && venta.getFuncion().getCodigo().equalsIgnoreCase(funcion.getCodigo())
                    && venta.getAsiento().getCodigo().equalsIgnoreCase(asiento.getCodigo())) {
                disponible = false;
            }
        }

        return disponible;
    }

    public void registrarVenta(Venta venta) {
        if (venta == null) {
            throw new IllegalArgumentException("La venta no puede ser nula.");
        }

        if (!venta.getFuncion().estaProgramada()) {
            throw new IllegalArgumentException("La función no está disponible para la venta.");
        }

        if (!estaDisponible(venta.getFuncion(), venta.getAsiento())) {
            throw new IllegalArgumentException("El asiento ya está ocupado para esta función.");
        }

        for (Venta ventaRegistrada : listaVentas) {
            if (ventaRegistrada.getCodigo().equalsIgnoreCase(venta.getCodigo())) {
                throw new IllegalArgumentException("Ya existe una venta con ese código.");
            }
        }

        listaVentas.add(venta);
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

        if (venta == null) {
            throw new IllegalArgumentException("No existe una venta con ese código.");
        }

        if (!venta.estaActiva()) {
            throw new IllegalArgumentException("La venta ya está cancelada.");
        }

        venta.cancelar();
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
            informacion = informacion + venta.generarInformacionVenta() + "\n\n";
        }

        if (listaVentas.isEmpty()) {
            informacion = "No hay ventas registradas.";
        }

        return informacion;
    }

    public String consultarVentasActivas() {
        String informacion = "";

        for (Venta venta : listaVentas) {
            if (venta.estaActiva()) {
                informacion = informacion + venta.generarInformacionVenta() + "\n\n";
            }
        }

        if (informacion.isEmpty()) {
            informacion = "No hay ventas activas.";
        }

        return informacion;
    }
}