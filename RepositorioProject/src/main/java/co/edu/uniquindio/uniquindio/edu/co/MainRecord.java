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

        Sala sala1 = new Sala("1", "Sala principal");
        Sala sala2 = new Sala("2", "Sala VIP");
        Sala sala3 = new Sala("3", "Sala 3D");

        cine.registrarPelicula(pelicula1);
        cine.registrarPelicula(pelicula2);

        cine.registrarSala(sala1);
        cine.registrarSala(sala2);
        cine.registrarSala(sala3);

        return cine;
    }
}