package uniquindio.edu.co;

import uniquindio.edu.co.model.*;

public class MainRecord {

    public static Cine crearCine() {

        Cine cine = new Cine(
                "CINEMAUQ",
                "890000001",
                "Universidad del Quindío, Armenia"
        );

        Pelicula pelicula1 = new Pelicula(
                "1",
                "Intensamente 2",
                GeneroPelicula.ANIMACION,
                96,
                ClasificacionEdad.TODOS
        );

        Pelicula pelicula2 = new Pelicula(
                "2",
                "Interestelar",
                GeneroPelicula.DRAMA,
                169,
                ClasificacionEdad.MAYORES_12
        );

        Sala sala1 = new Sala(
                "1",
                "Sala principal"
        );

        cine.registrarPelicula(pelicula1);
        cine.registrarPelicula(pelicula2);
        cine.registrarSala(sala1);

        return cine;
    }
}