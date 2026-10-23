package uniquindio.edu.co.model;

public class ServiciosReportes {

    private Cine cine;

    public ServiciosReportes(Cine cine) {
        this.cine = cine;
    }

    public String consultarDisponibilidadFuncion(String codigoFuncion) {
        String informacion = "";
        Funcion funcion = cine.buscarFuncion(codigoFuncion);

        if (funcion != null) {
            informacion = "DISPONIBILIDAD DE ASIENTOS\n"
                    + funcion.consultarInformacion()
                    + "\n\n"
                    + funcion.consultarAsientos(cine.getServicioVentas())
                    + "\nAsientos disponibles: "
                    + funcion.contarAsientosDisponibles(cine.getServicioVentas())
                    + "\nAsientos ocupados: "
                    + funcion.contarAsientosOcupados(cine.getServicioVentas());
        } else {
            informacion = "No se encontró la función.";
        }

        return informacion;
    }

    public String consultarOcupacion() {
        String informacion = "REPORTE DE OCUPACIÓN\n";
        int totalDisponibles = 0;
        int totalOcupados = 0;

        for (Funcion funcion : cine.getListaFunciones()) {
            totalDisponibles = totalDisponibles
                    + funcion.contarAsientosDisponibles(cine.getServicioVentas());

            totalOcupados = totalOcupados
                    + funcion.contarAsientosOcupados(cine.getServicioVentas());

            informacion = informacion
                    + "\nFunción: " + funcion.getCodigo()
                    + "\nPelícula: " + funcion.getPelicula().getNombre()
                    + "\nSala: " + funcion.getSala().getNombre()
                    + "\nAsientos disponibles: "
                    + funcion.contarAsientosDisponibles(cine.getServicioVentas())
                    + "\nAsientos ocupados: "
                    + funcion.contarAsientosOcupados(cine.getServicioVentas())
                    + "\n-------------------------\n";
        }

        informacion = informacion
                + "\nTotal de asientos disponibles: " + totalDisponibles
                + "\nTotal de asientos ocupados: " + totalOcupados;

        return informacion;
    }

    public String consultarIngresos() {
        String informacion = "REPORTE DE INGRESOS\n"
                + "Ingresos por ventas activas: $"
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