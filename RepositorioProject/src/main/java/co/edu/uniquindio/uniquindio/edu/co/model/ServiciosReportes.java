package uniquindio.edu.co.model;

public class ServiciosReportes {

    private Cine cine;

    public ServiciosReportes(Cine cine) {
        if (cine == null) {
            throw new IllegalArgumentException("El cine es obligatorio.");
        }

        this.cine = cine;
    }

    public String consultarDisponibilidadFuncion(String codigoFuncion) {
        String informacion = "";
        Funcion funcion = cine.buscarFuncion(codigoFuncion);

        if (funcion == null) {
            informacion = "No existe una función con ese código.";
        } else {
            informacion = "DISPONIBILIDAD DE LA FUNCIÓN"
                    + "\nPelícula: " + funcion.getPelicula().getNombre()
                    + "\nFunción: " + funcion.getCodigo()
                    + "\nSala: " + funcion.getSala().getNombre()
                    + "\nFecha: " + funcion.getFecha()
                    + "\nHora: " + funcion.getHora()
                    + "\n\n";

            informacion = informacion
                    + funcion.consultarAsientos(cine.getServicioVentas());

            informacion = informacion
                    + "\n\nAsientos disponibles: "
                    + funcion.contarAsientosDisponibles(cine.getServicioVentas());

            informacion = informacion
                    + "\nAsientos ocupados: "
                    + funcion.contarAsientosOcupados(cine.getServicioVentas());
        }

        return informacion;
    }

    public String consultarOcupacion() {
        String informacion = "REPORTE DE OCUPACIÓN\n\n";

        if (cine.getListaFunciones().isEmpty()) {
            informacion = "No hay funciones registradas.";
        } else {
            for (Funcion funcion : cine.getListaFunciones()) {
                int disponibles = funcion.contarAsientosDisponibles(
                        cine.getServicioVentas());

                int ocupados = funcion.contarAsientosOcupados(
                        cine.getServicioVentas());

                informacion = informacion
                        + "Función: " + funcion.getCodigo()
                        + "\nPelícula: " + funcion.getPelicula().getNombre()
                        + "\nSala: " + funcion.getSala().getNombre()
                        + "\nAsientos disponibles: " + disponibles
                        + "\nAsientos ocupados: " + ocupados
                        + "\n\n";
            }
        }

        return informacion;
    }

    public String consultarIngresos() {
        String informacion = "REPORTE DE INGRESOS"
                + "\nVentas activas: "
                + cine.getServicioVentas().contarVentasActivas()
                + "\nIngresos totales: $"
                + cine.getServicioVentas().calcularIngresos();

        return informacion;
    }

    public String consultarVentas() {
        String informacion = cine.getServicioVentas().consultarVentas();
        return informacion;
    }

    public String consultarVentasActivas() {
        String informacion = cine.getServicioVentas().consultarVentasActivas();
        return informacion;
    }
}