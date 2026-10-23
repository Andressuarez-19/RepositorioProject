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
    private List<Operario> listaOperarios;

    private ServicioVentas servicioVentas;

    public Cine(String nombre, String nit, String direccion) {
        this.nombre = nombre;
        this.nit = nit;
        this.direccion = direccion;

        listaPeliculas = new ArrayList<Pelicula>();
        listaSalas = new ArrayList<Sala>();
        listaFunciones = new ArrayList<Funcion>();
        listaClientes = new ArrayList<Cliente>();
        listaOperarios = new ArrayList<Operario>();

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

    public List<Operario> getListaOperarios() {
        return listaOperarios;
    }

    public ServicioVentas getServicioVentas() {
        return servicioVentas;
    }

    public boolean registrarPelicula(Pelicula pelicula) {
        boolean existe = false;

        if (pelicula != null) {
            for (Pelicula peliculaRegistrada : listaPeliculas) {
                if (peliculaRegistrada.getCodigo()
                        .equalsIgnoreCase(pelicula.getCodigo())) {
                    existe = true;
                }
            }

            if (!existe) {
                listaPeliculas.add(pelicula);
            }
        }
        return existe;
    }

    public void registrarSala(Sala sala) {
        boolean existe = false;

        if (sala != null) {
            for (Sala salaRegistrada : listaSalas) {
                if (salaRegistrada.getCodigo()
                        .equalsIgnoreCase(sala.getCodigo())) {
                    existe = true;
                }
            }

            if (!existe) {
                listaSalas.add(sala);
            }
        }
    }

    public boolean registrarFuncion(Funcion funcion) {
        boolean existe = false;
        boolean peliculaRegistrada = false;
        boolean salaRegistrada = false;

        if (funcion != null) {
            for (Funcion funcionRegistrada : listaFunciones) {
                if (funcionRegistrada.getCodigo()
                        .equalsIgnoreCase(funcion.getCodigo())) {
                    existe = true;
                }
            }

            for (Pelicula pelicula : listaPeliculas) {
                if (pelicula == funcion.getPelicula()
                        && pelicula.estaDisponible()) {
                    peliculaRegistrada = true;
                }
            }

            for (Sala sala : listaSalas) {
                if (sala == funcion.getSala() && sala.estaDisponible()) {
                    salaRegistrada = true;
                }
            }

            if (!existe && peliculaRegistrada && salaRegistrada) {
                listaFunciones.add(funcion);
            }
        }
        return existe;
    }

    public boolean registrarCliente(Cliente cliente) {
        boolean existe = false;

        if (cliente != null) {
            for (Cliente clienteRegistrado : listaClientes) {
                if (clienteRegistrado.getIdentificacion()
                        .equalsIgnoreCase(cliente.getIdentificacion())) {
                    existe = true;
                }
            }

            if (!existe) {
                listaClientes.add(cliente);
            }
        }
        return existe;
    }

    public void registrarOperario(Operario operario) {
        boolean existe = false;

        if (operario != null) {
            for (Operario operarioRegistrado : listaOperarios) {
                if (operarioRegistrado.getCodigo()
                        .equalsIgnoreCase(operario.getCodigo())) {
                    existe = true;
                }
            }

            if (!existe) {
                listaOperarios.add(operario);
            }
        }
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
                if (cliente.getIdentificacion()
                        .equalsIgnoreCase(identificacion)) {
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
            informacion = informacion + pelicula.consultarInformacion()
                    + "\n-------------------------\n";
        }

        if (listaPeliculas.size() == 0) {
            informacion = "No hay películas registradas.";
        }

        return informacion;
    }

    public String consultarSalas() {
        String informacion = "";

        for (Sala sala : listaSalas) {
            informacion = informacion + sala.consultarInformacion()
                    + "\n-------------------------\n";
        }

        if (listaSalas.size() == 0) {
            informacion = "No hay salas registradas.";
        }

        return informacion;
    }

    public String consultarFunciones() {
        String informacion = "";

        for (Funcion funcion : listaFunciones) {
            informacion = informacion + funcion.consultarInformacion()
                    + "\n-------------------------\n";
        }

        if (listaFunciones.size() == 0) {
            informacion = "No hay funciones programadas.";
        }

        return informacion;
    }

    public String consultarClientes() {
        String informacion = "";

        for (Cliente cliente : listaClientes) {
            informacion = informacion + cliente.consultarInformacion()
                    + "\n-------------------------\n";
        }

        if (listaClientes.size() == 0) {
            informacion = "No hay clientes registrados.";
        }

        return informacion;
    }

    public String consultarInformacion() {
        String informacion = "CINE: " + nombre
                + "\nNIT: " + nit
                + "\nDirección: " + direccion
                + "\nPelículas registradas: " + listaPeliculas.size()
                + "\nSalas registradas: " + listaSalas.size()
                + "\nFunciones programadas: " + listaFunciones.size()
                + "\nClientes registrados: " + listaClientes.size();

        return informacion;
    }
}