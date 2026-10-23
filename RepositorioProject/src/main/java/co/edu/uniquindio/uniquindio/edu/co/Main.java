package uniquindio.edu.co;

import uniquindio.edu.co.model.*;
import javax.swing.JOptionPane;

public class Main {

    public static void main(String[] args) {

        Cine cine = MainRecord.crearCine();
        ServiciosReportes reportes = new ServiciosReportes(cine);

        int opcion = 0;

        while (opcion != 12) {

            opcion = Integer.parseInt(JOptionPane.showInputDialog(
                    "CINEMAUQ\n\n"
                            + "1. Consultar películas\n"
                            + "2. Registrar película\n"
                            + "3. Registrar cliente\n"
                            + "4. Programar función\n"
                            + "5. Consultar funciones\n"
                            + "6. Vender entrada\n"
                            + "7. Cancelar venta\n"
                            + "8. Consultar disponibilidad\n"
                            + "9. Consultar reportes\n"
                            + "10. Modificar fecha y hora de función\n"
                            + "11. Cancelar función\n"
                            + "12. Salir\n\n"
                            + "Seleccione una opción:"
            ));

            switch (opcion) {
                case 1:
                    consultarPeliculas(cine);
                    break;

                case 2:
                    registrarPelicula(cine);
                    break;

                case 3:
                    registrarCliente(cine);
                    break;

                case 4:
                    programarFuncion(cine);
                    break;

                case 5:
                    consultarFunciones(cine);
                    break;

                case 6:
                    venderEntrada(cine);
                    break;

                case 7:
                    cancelarVenta(cine);
                    break;

                case 8:
                    consultarDisponibilidad(cine, reportes);
                    break;

                case 9:
                    consultarReportes(reportes);
                    break;

                case 10:
                    modificarFuncion(cine);
                    break;

                case 11:
                    cancelarFuncion(cine);
                    break;

                case 12:
                    JOptionPane.showMessageDialog(null,
                            "Gracias por utilizar CINEMAUQ.");
                    break;

                default:
                    JOptionPane.showMessageDialog(null,
                            "Opción no válida.");
            }
        }
    }

    public static void consultarPeliculas(Cine cine) {

        JOptionPane.showMessageDialog(null,
                cine.consultarPeliculas());
    }

    public static void registrarPelicula(Cine cine) {

        String codigo = JOptionPane.showInputDialog(
                "Ingrese el código de la película:");

        if (codigo == null || codigo.equals("")) {
            return;
        }

        if (cine.buscarPelicula(codigo) != null) {
            JOptionPane.showMessageDialog(null,
                    "Ya existe una película con ese código.");
            return;
        }

        String nombre = JOptionPane.showInputDialog(
                "Ingrese el nombre de la película:");

        if (nombre == null || nombre.equals("")) {
            return;
        }

        String generoTexto = JOptionPane.showInputDialog(
                "Seleccione el género:\n"
                        + "1. Acción\n"
                        + "2. Comedia\n"
                        + "3. Drama\n"
                        + "4. Terror\n"
                        + "5. Animación");

        if (generoTexto == null) {
            return;
        }

        GeneroPelicula genero = null;

        if (generoTexto.equals("1")) {
            genero = GeneroPelicula.ACCION;
        } else if (generoTexto.equals("2")) {
            genero = GeneroPelicula.COMEDIA;
        } else if (generoTexto.equals("3")) {
            genero = GeneroPelicula.DRAMA;
        } else if (generoTexto.equals("4")) {
            genero = GeneroPelicula.TERROR;
        } else if (generoTexto.equals("5")) {
            genero = GeneroPelicula.ANIMACION;
        }

        if (genero == null) {
            JOptionPane.showMessageDialog(null,
                    "El género seleccionado no es válido.");
            return;
        }

        String duracionTexto = JOptionPane.showInputDialog(
                "Ingrese la duración en minutos:");

        if (duracionTexto == null || duracionTexto.equals("")) {
            return;
        }

        int duracion = Integer.parseInt(duracionTexto);

        if (duracion <= 0) {
            JOptionPane.showMessageDialog(null,
                    "La duración debe ser mayor que cero.");
            return;
        }

        String clasificacionTexto = JOptionPane.showInputDialog(
                "Seleccione la clasificación:\n"
                        + "1. Todos\n"
                        + "2. Mayores de 12 años\n"
                        + "3. Mayores de 15 años\n"
                        + "4. Mayores de 18 años");

        if (clasificacionTexto == null) {
            return;
        }

        ClasificacionEdad clasificacion = null;

        if (clasificacionTexto.equals("1")) {
            clasificacion = ClasificacionEdad.TODOS;
        } else if (clasificacionTexto.equals("2")) {
            clasificacion = ClasificacionEdad.MAYORES_12;
        } else if (clasificacionTexto.equals("3")) {
            clasificacion = ClasificacionEdad.MAYORES_15;
        } else if (clasificacionTexto.equals("4")) {
            clasificacion = ClasificacionEdad.MAYORES_18;
        }

        if (clasificacion == null) {
            JOptionPane.showMessageDialog(null,
                    "La clasificación no es válida.");
            return;
        }

        Pelicula pelicula = new Pelicula(
                codigo, nombre, genero, duracion, clasificacion);

        cine.registrarPelicula(pelicula);

        if (cine.buscarPelicula(codigo) != null) {
            JOptionPane.showMessageDialog(null,
                    "Película registrada correctamente.");
        } else {
            JOptionPane.showMessageDialog(null,
                    "La película no quedó registrada.");
        }
    }

    public static void registrarCliente(Cine cine) {

        String identificacion = JOptionPane.showInputDialog(
                "Ingrese la identificación del cliente:");

        if (identificacion == null || identificacion.equals("")) {
            return;
        }

        if (cine.buscarCliente(identificacion) != null) {
            JOptionPane.showMessageDialog(null,
                    "Ya existe un cliente con esa identificación.");
            return;
        }

        String nombre = JOptionPane.showInputDialog(
                "Ingrese el nombre del cliente:");

        if (nombre == null || nombre.equals("")) {
            return;
        }

        String tipoTexto = JOptionPane.showInputDialog(
                "Seleccione el tipo de cliente:\n"
                        + "1. General\n"
                        + "2. Estudiante\n"
                        + "3. Niño\n"
                        + "4. Adulto mayor");

        if (tipoTexto == null) {
            return;
        }

        TipoCliente tipoCliente = null;

        if (tipoTexto.equals("1")) {
            tipoCliente = TipoCliente.GENERAL;
        } else if (tipoTexto.equals("2")) {
            tipoCliente = TipoCliente.ESTUDIANTE;
        } else if (tipoTexto.equals("3")) {
            tipoCliente = TipoCliente.NINO;
        } else if (tipoTexto.equals("4")) {
            tipoCliente = TipoCliente.ADULTO_MAYOR;
        }

        if (tipoCliente == null) {
            JOptionPane.showMessageDialog(null,
                    "El tipo de cliente no es válido.");
            return;
        }

        Cliente cliente = new Cliente(
                identificacion, nombre, tipoCliente);

        cine.registrarCliente(cliente);

        if (cine.buscarCliente(identificacion) != null) {
            JOptionPane.showMessageDialog(null,
                    "Cliente registrado correctamente.\n\n"
                            + cliente.consultarInformacion());
        } else {
            JOptionPane.showMessageDialog(null,
                    "El cliente no quedó registrado.");
        }
    }

    public static void programarFuncion(Cine cine) {

        String codigo = JOptionPane.showInputDialog(
                "Ingrese el código de la función:");

        if (codigo == null || codigo.equals("")) {
            return;
        }

        String codigoPelicula = JOptionPane.showInputDialog(
                cine.consultarPeliculas()
                        + "\nIngrese el código de la película:");

        if (codigoPelicula == null) {
            return;
        }

        Pelicula pelicula = cine.buscarPelicula(codigoPelicula);

        if (pelicula == null || !pelicula.estaDisponible()) {
            JOptionPane.showMessageDialog(null,
                    "La película no existe o no está disponible.");
            return;
        }

        String codigoSala = JOptionPane.showInputDialog(
                cine.consultarSalas()
                        + "\nIngrese el código de la sala:");

        if (codigoSala == null) {
            return;
        }

        Sala sala = cine.buscarSala(codigoSala);

        if (sala == null || !sala.estaDisponible()) {
            JOptionPane.showMessageDialog(null,
                    "La sala no existe o no está disponible.");
            return;
        }

        String fecha = JOptionPane.showInputDialog(
                "Ingrese la fecha (DD/MM/AAAA):");

        if (fecha == null || fecha.equals("")) {
            return;
        }

        String hora = JOptionPane.showInputDialog(
                "Ingrese la hora (HH:MM):");

        if (hora == null || hora.equals("")) {
            return;
        }

        Funcion funcion = new Funcion(
                codigo, pelicula, sala, fecha, hora);

        boolean registrada = cine.registrarFuncion(funcion);

        if (registrada) {
            JOptionPane.showMessageDialog(null,
                    "Función programada correctamente.\n\n"
                            + funcion.consultarInformacion());
        } else {
            JOptionPane.showMessageDialog(null,
                    "No fue posible programar la función.");
        }
    }

    public static void consultarFunciones(Cine cine) {

        JOptionPane.showMessageDialog(null,
                cine.consultarFunciones());
    }

    public static void venderEntrada(Cine cine) {

        String codigoFuncion = JOptionPane.showInputDialog(
                cine.consultarFunciones()
                        + "\nIngrese el código de la función:");

        if (codigoFuncion == null) {
            return;
        }

        Funcion funcion = cine.buscarFuncion(codigoFuncion);

        if (funcion == null || !funcion.estaProgramada()) {
            JOptionPane.showMessageDialog(null,
                    "La función no existe o no está programada.");
            return;
        }

        if (funcion.contarAsientosDisponibles(
                cine.getServicioVentas()) == 0) {
            JOptionPane.showMessageDialog(null,
                    "La función no tiene asientos disponibles.");
            return;
        }

        String identificacion = JOptionPane.showInputDialog(
                "Ingrese la identificación del cliente:");

        if (identificacion == null) {
            return;
        }

        Cliente cliente = cine.buscarCliente(identificacion);

        if (cliente == null) {
            JOptionPane.showMessageDialog(null,
                    "El cliente no está registrado.");
            return;
        }

        JOptionPane.showMessageDialog(null,
                "Asientos de la función:\n\n"
                        + funcion.consultarAsientos(
                        cine.getServicioVentas()));

        String codigoAsiento = JOptionPane.showInputDialog(
                "Ingrese el código del asiento:");

        if (codigoAsiento == null) {
            return;
        }

        Asiento asiento = funcion.buscarAsiento(codigoAsiento);

        if (asiento == null) {
            JOptionPane.showMessageDialog(null,
                    "El asiento no existe en esta función.");
            return;
        }

        if (!cine.getServicioVentas().estaDisponible(
                funcion, asiento)) {
            JOptionPane.showMessageDialog(null,
                    "El asiento ya está ocupado para esta función.");
            return;
        }

        String codigoVenta = JOptionPane.showInputDialog(
                "Ingrese el código de la venta:");

        if (codigoVenta == null || codigoVenta.equals("")) {
            return;
        }

        Venta venta = new Venta(
                codigoVenta, cliente, funcion, asiento);

        int cantidadAnterior = cine.getServicioVentas()
                .getListaVentas().size();

        cine.getServicioVentas().registrarVenta(venta);

        int cantidadActual = cine.getServicioVentas()
                .getListaVentas().size();

        if (cantidadActual > cantidadAnterior) {
            JOptionPane.showMessageDialog(null,
                    "Venta registrada correctamente.\n\n"
                            + venta.generarInformacionVenta());
        } else {
            JOptionPane.showMessageDialog(null,
                    "No fue posible registrar la venta.");
        }
    }

    public static void cancelarVenta(Cine cine) {

        String codigo = JOptionPane.showInputDialog(
                "Ingrese el código de la venta que desea cancelar:");

        if (codigo == null) {
            return;
        }

        Venta venta = cine.buscarVenta(codigo);

        if (venta == null) {
            JOptionPane.showMessageDialog(null,
                    "No se encontró la venta.");
            return;
        }

        if (!venta.estaActiva()) {
            JOptionPane.showMessageDialog(null,
                    "La venta ya está cancelada.");
            return;
        }

        cine.getServicioVentas().cancelarVenta(codigo);

        JOptionPane.showMessageDialog(null,
                "Venta cancelada correctamente.");
    }

    public static void consultarDisponibilidad(
            Cine cine, ServiciosReportes reportes) {

        String codigo = JOptionPane.showInputDialog(
                cine.consultarFunciones()
                        + "\nIngrese el código de la función:");

        if (codigo == null) {
            return;
        }

        JOptionPane.showMessageDialog(null,
                reportes.consultarDisponibilidadFuncion(codigo));
    }

    public static void consultarReportes(
            ServiciosReportes reportes) {

        String opcion = JOptionPane.showInputDialog(
                "REPORTES\n\n"
                        + "1. Consultar todas las ventas\n"
                        + "2. Consultar ventas activas\n"
                        + "3. Consultar ocupación\n"
                        + "4. Consultar ingresos");

        if (opcion == null) {
            return;
        }

        String informacion = "";

        if (opcion.equals("1")) {
            informacion = reportes.consultarVentas();
        } else if (opcion.equals("2")) {
            informacion = reportes.consultarVentasActivas();
        } else if (opcion.equals("3")) {
            informacion = reportes.consultarOcupacion();
        } else if (opcion.equals("4")) {
            informacion = reportes.consultarIngresos();
        } else {
            informacion = "Opción no válida.";
        }

        JOptionPane.showMessageDialog(null, informacion);
    }

    public static void modificarFuncion(Cine cine) {

        String codigo = JOptionPane.showInputDialog(
                cine.consultarFunciones()
                        + "\nIngrese el código de la función:");

        if (codigo == null) {
            return;
        }

        Funcion funcion = cine.buscarFuncion(codigo);

        if (funcion == null || !funcion.estaProgramada()) {
            JOptionPane.showMessageDialog(null,
                    "La función no existe o no está programada.");
            return;
        }

        String fecha = JOptionPane.showInputDialog(
                "Fecha actual: " + funcion.getFecha()
                        + "\nIngrese la nueva fecha (DD/MM/AAAA):");

        if (fecha == null || fecha.equals("")) {
            return;
        }

        String hora = JOptionPane.showInputDialog(
                "Hora actual: " + funcion.getHora()
                        + "\nIngrese la nueva hora (HH:MM):");

        if (hora == null || hora.equals("")) {
            return;
        }

        funcion.setFecha(fecha);
        funcion.setHora(hora);

        JOptionPane.showMessageDialog(null,
                "Función modificada.\n\n"
                        + funcion.consultarInformacion());
    }

    public static void cancelarFuncion(Cine cine) {

        String codigo = JOptionPane.showInputDialog(
                cine.consultarFunciones()
                        + "\nIngrese el código de la función que desea cancelar:");

        if (codigo == null) {
            return;
        }

        Funcion funcion = cine.buscarFuncion(codigo);

        if (funcion == null) {
            JOptionPane.showMessageDialog(null,
                    "No se encontró la función.");
            return;
        }

        if (!funcion.estaProgramada()) {
            JOptionPane.showMessageDialog(null,
                    "La función no está programada.");
            return;
        }

        boolean tieneVentasActivas = false;

        for (Venta venta : cine.getServicioVentas().getListaVentas()) {
            if (venta.estaActiva()
                    && venta.getFuncion().getCodigo()
                    .equalsIgnoreCase(funcion.getCodigo())) {
                tieneVentasActivas = true;
            }
        }

        if (tieneVentasActivas) {
            JOptionPane.showMessageDialog(null,
                    "No se puede cancelar la función porque tiene ventas activas.\n"
                            + "Primero debes cancelar esas ventas.");
            return;
        }

        funcion.cancelarFuncion();

        JOptionPane.showMessageDialog(null,
                "Función cancelada correctamente.");
    }
}