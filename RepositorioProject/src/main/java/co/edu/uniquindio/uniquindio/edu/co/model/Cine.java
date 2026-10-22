package uniquindio.edu.co.model;

import java.util.ArrayList;
import java.util.List;

public class Cine {

    private String nombre;
    private String nit;
    private String direccion;

    private List<Pelicula> listaPeliculas;
    private List<Sala> listaSalas;
    private List<Funcion> listaFunciones;
    private List<Cliente> listaClientes;
    private ServicioVentas servicioVentas;

    public Cine(String nombre, String nit, String direccion) {

        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del cine es obligatorio.");
        }

        if (nit == null || nit.trim().isEmpty()) {
            throw new IllegalArgumentException("El NIT es obligatorio.");
        }

        if (direccion == null || direccion.trim().isEmpty()) {
            throw new IllegalArgumentException("La dirección es obligatoria.");
        }

        this.nombre = nombre;
        this.nit = nit;
        this.direccion = direccion;

        listaPeliculas = new ArrayList<>();
        listaSalas = new ArrayList<>();
        listaFunciones = new ArrayList<>();
        listaClientes = new ArrayList<>();
        servicioVentas = new ServicioVentas();
    }

    public String getNombre() {
        return nombre;
    }

    public String getNit() {
        return nit;
    }

    public String getDireccion() {
        return direccion;
    }

    public List<Pelicula> getListaPeliculas() {
        return listaPeliculas;
    }

    public List<Sala> getListaSalas() {
        return listaSalas;
    }

    public List<Funcion> getListaFunciones() {
        return listaFunciones;
    }

    public List<Cliente> getListaClientes() {
        return listaClientes;
    }

    public ServicioVentas getServicioVentas() {
        return servicioVentas;
    }

    public void registrarPelicula(Pelicula pelicula) {
        if (pelicula == null) {
            throw new IllegalArgumentException("La película no puede ser nula.");
        }

        if (buscarPelicula(pelicula.getCodigo()) != null) {
            throw new IllegalArgumentException("Ya existe una película con ese código.");
        }

        listaPeliculas.add(pelicula);
    }

    public void registrarSala(Sala sala) {
        if (sala == null) {
            throw new IllegalArgumentException("La sala no puede ser nula.");
        }

        if (buscarSala(sala.getCodigo()) != null) {
            throw new IllegalArgumentException("Ya existe una sala con ese código.");
        }

        listaSalas.add(sala);
    }

    public void registrarFuncion(Funcion funcion) {
        if (funcion == null) {
            throw new IllegalArgumentException("La función no puede ser nula.");
        }

        if (buscarFuncion(funcion.getCodigo()) != null) {
            throw new IllegalArgumentException("Ya existe una función con ese código.");
        }

        if (!listaPeliculas.contains(funcion.getPelicula())) {
            throw new IllegalArgumentException("La película no está registrada en el cine.");
        }

        if (!listaSalas.contains(funcion.getSala())) {
            throw new IllegalArgumentException("La sala no está registrada en el cine.");
        }

        if (funcion.getPelicula().getEstado() != EstadoPelicula.DISPONIBLE) {
            throw new IllegalArgumentException("La película no está disponible.");
        }

        if (!funcion.getSala().estaDisponible()) {
            throw new IllegalArgumentException("La sala no está disponible.");
        }

        listaFunciones.add(funcion);
    }

    public void registrarCliente(Cliente cliente) {
        if (cliente == null) {
            throw new IllegalArgumentException("El cliente no puede ser nulo.");
        }

        if (buscarCliente(cliente.getIdentificacion()) != null) {
            throw new IllegalArgumentException("Ya existe un cliente con esa identificación.");
        }

        listaClientes.add(cliente);
    }

    public Pelicula buscarPelicula(String codigo) {
        Pelicula resultado = null;

        if (codigo != null) {
            for (Pelicula pelicula : listaPeliculas) {
                if (pelicula.getCodigo().equalsIgnoreCase(codigo)) {
                    resultado = pelicula;
                }
            }
        }

        return resultado;
    }

    public Sala buscarSala(String codigo) {
        Sala resultado = null;

        if (codigo != null) {
            for (Sala sala : listaSalas) {
                if (sala.getCodigo().equalsIgnoreCase(codigo)) {
                    resultado = sala;
                }
            }
        }

        return resultado;
    }

    public Funcion buscarFuncion(String codigo) {
        Funcion resultado = null;

        if (codigo != null) {
            for (Funcion funcion : listaFunciones) {
                if (funcion.getCodigo().equalsIgnoreCase(codigo)) {
                    resultado = funcion;
                }
            }
        }

        return resultado;
    }

    public Cliente buscarCliente(String identificacion) {
        Cliente resultado = null;

        if (identificacion != null) {
            for (Cliente cliente : listaClientes) {
                if (cliente.getIdentificacion().equalsIgnoreCase(identificacion)) {
                    resultado = cliente;
                }
            }
        }

        return resultado;
    }

    public Venta buscarVenta(String codigo) {
        Venta resultado = servicioVentas.buscarVenta(codigo);
        return resultado;
    }

    public String consultarPeliculas() {
        String informacion = "";

        for (Pelicula pelicula : listaPeliculas) {
            informacion = informacion + pelicula.consultarInformacion() + "\n\n";
        }

        if (listaPeliculas.isEmpty()) {
            informacion = "No hay películas registradas.";
        }

        return informacion;
    }

    public String consultarSalas() {
        String informacion = "";

        for (Sala sala : listaSalas) {
            informacion = informacion + sala.consultarInformacion() + "\n\n";
        }

        if (listaSalas.isEmpty()) {
            informacion = "No hay salas registradas.";
        }

        return informacion;
    }

    public String consultarFunciones() {
        String informacion = "";

        for (Funcion funcion : listaFunciones) {
            if (funcion.estaProgramada()) {
                informacion = informacion + funcion.consultarInformacion() + "\n\n";
            }
        }

        if (informacion.isEmpty()) {
            informacion = "No hay funciones programadas.";
        }

        return informacion;
    }

    public String consultarClientes() {
        String informacion = "";

        for (Cliente cliente : listaClientes) {
            informacion = informacion + cliente.consultarInformacion() + "\n\n";
        }

        if (listaClientes.isEmpty()) {
            informacion = "No hay clientes registrados.";
        }

        return informacion;
    }

    public String consultarInformacion() {
        String informacion = "Cine: " + nombre
                + "\nNIT: " + nit
                + "\nDirección: " + direccion
                + "\nPelículas registradas: " + listaPeliculas.size()
                + "\nSalas registradas: " + listaSalas.size()
                + "\nFunciones registradas: " + listaFunciones.size()
                + "\nClientes registrados: " + listaClientes.size()
                + "\nVentas activas: " + servicioVentas.contarVentasActivas()
                + "\nIngresos: $" + servicioVentas.calcularIngresos();

        return informacion;
    }
}