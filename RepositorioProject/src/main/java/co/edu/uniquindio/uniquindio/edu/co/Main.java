package uniquindio.edu.co;

import uniquindio.edu.co.model.*;

import javax.swing.JOptionPane;

public class Main {

    public static void main(String[] args) {

        Cine cine = MainRecord.crearCine();
        ServiciosReportes reportes = new ServiciosReportes(cine);

        String opcion = "";

        while (!opcion.equals("10")) {

            opcion = JOptionPane.showInputDialog(
                    "CINEMAUQ - CINE UNIVERSITARIO"
                            + "\n\n1. Consultar películas"
                            + "\n2. Registrar película"
                            + "\n3. Registrar cliente"
                            + "\n4. Programar función"
                            + "\n5. Consultar funciones"
                            + "\n6. Vender entrada"
                            + "\n7. Cancelar venta"
                            + "\n8. Consultar disponibilidad"
                            + "\n9. Consultar reportes"
                            + "\n10. Salir"
                            + "\n\nSeleccione una opción:"
            );

            if (opcion == null) {
                opcion = "10";
            } else if (opcion.equals("1")) {
                JOptionPane.showMessageDialog(
                        null, cine.consultarPeliculas());

            } else if (opcion.equals("2")) {
                registrarPelicula(cine);

            } else if (opcion.equals("3")) {
                registrarCliente(cine);

            } else if (opcion.equals("4")) {
                programarFuncion(cine);

            } else if (opcion.equals("5")) {
                JOptionPane.showMessageDialog(
                        null, cine.consultarFunciones());

            } else if (opcion.equals("6")) {
                venderEntrada(cine);

            } else if (opcion.equals("7")) {
                cancelarVenta(cine);

            } else if (opcion.equals("8")) {
                consultarDisponibilidad(cine, reportes);

            } else if (opcion.equals("9")) {
                consultarReportes(reportes);

            } else if (!opcion.equals("10")) {
                JOptionPane.showMessageDialog(
                        null, "Seleccione una opción válida.");
            }
        }

        JOptionPane.showMessageDialog(
                null, "Gracias por utilizar CINEMAUQ.");
    }

    public static void registrarPelicula(Cine cine) {
        try {
            String codigo = JOptionPane.showInputDialog(
                    "Código de la película:");

            if (codigo == null) {
                return;
            }

            String nombre = JOptionPane.showInputDialog(
                    "Nombre de la película:");

            if (nombre == null) {
                return;
            }

            String generoTexto = JOptionPane.showInputDialog(
                    "Género:"
                            + "\n1. Acción"
                            + "\n2. Comedia"
                            + "\n3. Drama"
                            + "\n4. Terror"
                            + "\n5. Animación");

            if (generoTexto == null) {
                return;
            }

            GeneroPelicula genero;

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
            } else {
                throw new IllegalArgumentException(
                        "Género no válido.");
            }

            String duracionTexto = JOptionPane.showInputDialog(
                    "Duración en minutos:");

            if (duracionTexto == null) {
                return;
            }

            int duracion = Integer.parseInt(duracionTexto);

            String clasificacionTexto = JOptionPane.showInputDialog(
                    "Clasificación:"
                            + "\n1. Todos"
                            + "\n2. Mayores de 7"
                            + "\n3. Mayores de 12"
                            + "\n4. Mayores de 15"
                            + "\n5. Mayores de 18");

            if (clasificacionTexto == null) {
                return;
            }

            ClasificacionEdad clasificacion;

            if (clasificacionTexto.equals("1")) {
                clasificacion = ClasificacionEdad.TODOS;
            } else if (clasificacionTexto.equals("2")) {
                clasificacion = ClasificacionEdad.MAYORES_7;
            } else if (clasificacionTexto.equals("3")) {
                clasificacion = ClasificacionEdad.MAYORES_12;
            } else if (clasificacionTexto.equals("4")) {
                clasificacion = ClasificacionEdad.MAYORES_15;
            } else if (clasificacionTexto.equals("5")) {
                clasificacion = ClasificacionEdad.MAYORES_18;
            } else {
                throw new IllegalArgumentException(
                        "Clasificación no válida.");
            }

            Pelicula pelicula = new Pelicula(
                    codigo, nombre, genero, duracion, clasificacion);

            cine.registrarPelicula(pelicula);

            JOptionPane.showMessageDialog(
                    null, "Película registrada correctamente.");

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(
                    null, "La duración debe ser un número entero.");
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public static void registrarCliente(Cine cine) {
        try {
            String identificacion = JOptionPane.showInputDialog(
                    "Identificación del cliente:");

            if (identificacion == null) {
                return;
            }

            String nombre = JOptionPane.showInputDialog(
                    "Nombre del cliente:");

            if (nombre == null) {
                return;
            }

            String tipoTexto = JOptionPane.showInputDialog(
                    "Tipo de cliente:"
                            + "\n1. General"
                            + "\n2. Estudiante"
                            + "\n3. Niño"
                            + "\n4. Adulto mayor");

            if (tipoTexto == null) {
                return;
            }

            TipoCliente tipo;

            if (tipoTexto.equals("1")) {
                tipo = TipoCliente.GENERAL;
            } else if (tipoTexto.equals("2")) {
                tipo = TipoCliente.ESTUDIANTE;
            } else if (tipoTexto.equals("3")) {
                tipo = TipoCliente.NINO;
            } else if (tipoTexto.equals("4")) {
                tipo = TipoCliente.ADULTO_MAYOR;
            } else {
                throw new IllegalArgumentException(
                        "Tipo de cliente no válido.");
            }

            Cliente cliente = new Cliente(
                    identificacion, nombre, tipo);

            cine.registrarCliente(cliente);

            JOptionPane.showMessageDialog(
                    null, "Cliente registrado correctamente.");

        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public static void programarFuncion(Cine cine) {
        try {
            String codigo = JOptionPane.showInputDialog(
                    "Código de la función:");

            if (codigo == null) {
                return;
            }

            String codigoPelicula = JOptionPane.showInputDialog(
                    cine.consultarPeliculas()
                            + "\nCódigo de la película:");

            if (codigoPelicula == null) {
                return;
            }

            Pelicula pelicula = cine.buscarPelicula(codigoPelicula);

            if (pelicula == null) {
                throw new IllegalArgumentException(
                        "No existe esa película.");
            }

            String codigoSala = JOptionPane.showInputDialog(
                    cine.consultarSalas()
                            + "\nCódigo de la sala:");

            if (codigoSala == null) {
                return;
            }

            Sala sala = cine.buscarSala(codigoSala);

            if (sala == null) {
                throw new IllegalArgumentException(
                        "No existe esa sala.");
            }

            String fecha = JOptionPane.showInputDialog(
                    "Fecha (DD/MM/AAAA):");

            if (fecha == null) {
                return;
            }

            String hora = JOptionPane.showInputDialog(
                    "Hora (HH:MM):");

            if (hora == null) {
                return;
            }

            String valorTexto = JOptionPane.showInputDialog(
                    "Valor base de la función:");

            if (valorTexto == null) {
                return;
            }

            double valorBase = Double.parseDouble(valorTexto);

            Funcion funcion = new Funcion(
                    codigo, pelicula, sala, fecha, hora, valorBase);

            cine.registrarFuncion(funcion);

            JOptionPane.showMessageDialog(
                    null, "Función programada correctamente.");

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(
                    null, "El valor base debe ser numérico.");
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public static void venderEntrada(Cine cine) {
        try {
            String codigoFuncion = JOptionPane.showInputDialog(
                    cine.consultarFunciones()
                            + "\nCódigo de la función:");

            if (codigoFuncion == null) {
                return;
            }

            Funcion funcion = cine.buscarFuncion(codigoFuncion);

            if (funcion == null || !funcion.estaProgramada()) {
                throw new IllegalArgumentException(
                        "La función no existe o no está programada.");
            }

            JOptionPane.showMessageDialog(
                    null,
                    funcion.consultarAsientos(cine.getServicioVentas()));

            String codigoAsiento = JOptionPane.showInputDialog(
                    "Asiento que desea comprar (ejemplo A1):");

            if (codigoAsiento == null) {
                return;
            }

            Asiento asiento = funcion.buscarAsiento(codigoAsiento);

            if (asiento == null) {
                throw new IllegalArgumentException(
                        "El asiento no existe.");
            }

            if (!cine.getServicioVentas().estaDisponible(funcion, asiento)) {
                throw new IllegalArgumentException(
                        "El asiento ya está ocupado en esta función.");
            }

            String identificacion = JOptionPane.showInputDialog(
                    "Identificación del cliente:");

            if (identificacion == null) {
                return;
            }

            Cliente cliente = cine.buscarCliente(identificacion);

            if (cliente == null) {
                String nombre = JOptionPane.showInputDialog(
                        "Cliente nuevo. Escriba su nombre:");

                if (nombre == null) {
                    return;
                }

                String tipoTexto = JOptionPane.showInputDialog(
                        "Tipo de cliente:"
                                + "\n1. General"
                                + "\n2. Estudiante"
                                + "\n3. Niño"
                                + "\n4. Adulto mayor");

                if (tipoTexto == null) {
                    return;
                }

                TipoCliente tipo;

                if (tipoTexto.equals("1")) {
                    tipo = TipoCliente.GENERAL;
                } else if (tipoTexto.equals("2")) {
                    tipo = TipoCliente.ESTUDIANTE;
                } else if (tipoTexto.equals("3")) {
                    tipo = TipoCliente.NINO;
                } else if (tipoTexto.equals("4")) {
                    tipo = TipoCliente.ADULTO_MAYOR;
                } else {
                    throw new IllegalArgumentException(
                            "Tipo de cliente no válido.");
                }

                cliente = new Cliente(identificacion, nombre, tipo);
                cine.registrarCliente(cliente);
            }

            String tipoEntradaTexto = JOptionPane.showInputDialog(
                    "Tipo de entrada:"
                            + "\n1. General"
                            + "\n2. Estudiante"
                            + "\n3. Niño"
                            + "\n4. Adulto mayor");

            if (tipoEntradaTexto == null) {
                return;
            }

            TipoEntrada tipoEntrada;

            if (tipoEntradaTexto.equals("1")) {
                tipoEntrada = TipoEntrada.GENERAL;
            } else if (tipoEntradaTexto.equals("2")) {
                tipoEntrada = TipoEntrada.ESTUDIANTE;
            } else if (tipoEntradaTexto.equals("3")) {
                tipoEntrada = TipoEntrada.NINO;
            } else if (tipoEntradaTexto.equals("4")) {
                tipoEntrada = TipoEntrada.ADULTO_MAYOR;
            } else {
                throw new IllegalArgumentException(
                        "Tipo de entrada no válido.");
            }

            String codigoVenta = JOptionPane.showInputDialog(
                    "Código de la venta:");

            if (codigoVenta == null) {
                return;
            }

            Venta venta = new Venta(
                    codigoVenta, cliente, funcion, asiento, tipoEntrada);

            cine.getServicioVentas().registrarVenta(venta);

            JOptionPane.showMessageDialog(
                    null, venta.generarInformacionVenta());

        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public static void cancelarVenta(Cine cine) {
        try {
            String codigo = JOptionPane.showInputDialog(
                    cine.getServicioVentas().consultarVentasActivas()
                            + "\nCódigo de la venta que desea cancelar:");

            if (codigo == null) {
                return;
            }

            cine.getServicioVentas().cancelarVenta(codigo);

            JOptionPane.showMessageDialog(
                    null, "Venta cancelada correctamente.");

        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public static void consultarDisponibilidad(
            Cine cine, ServiciosReportes reportes) {

        String codigo = JOptionPane.showInputDialog(
                cine.consultarFunciones()
                        + "\nCódigo de la función:");

        if (codigo == null) {
            return;
        }

        JOptionPane.showMessageDialog(
                null, reportes.consultarDisponibilidadFuncion(codigo));
    }

    public static void consultarReportes(ServiciosReportes reportes) {
        String opcion = JOptionPane.showInputDialog(
                "REPORTES CINEMAUQ"
                        + "\n1. Ocupación"
                        + "\n2. Ingresos"
                        + "\n3. Todas las ventas"
                        + "\n4. Ventas activas");

        if (opcion == null) {
            return;
        }

        if (opcion.equals("1")) {
            JOptionPane.showMessageDialog(
                    null, reportes.consultarOcupacion());
        } else if (opcion.equals("2")) {
            JOptionPane.showMessageDialog(
                    null, reportes.consultarIngresos());
        } else if (opcion.equals("3")) {
            JOptionPane.showMessageDialog(
                    null, reportes.consultarVentas());
        } else if (opcion.equals("4")) {
            JOptionPane.showMessageDialog(
                    null, reportes.consultarVentasActivas());
        } else {
            JOptionPane.showMessageDialog(
                    null, "Seleccione una opción válida.");
        }
    }
}