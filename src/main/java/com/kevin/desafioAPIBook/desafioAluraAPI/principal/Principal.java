package com.kevin.desafioAPIBook.desafioAluraAPI.principal;

import com.kevin.desafioAPIBook.desafioAluraAPI.model.DatosApi;
import com.kevin.desafioAPIBook.desafioAluraAPI.model.DatosLibros;
import com.kevin.desafioAPIBook.desafioAluraAPI.service.ConsumoAPI;
import com.kevin.desafioAPIBook.desafioAluraAPI.service.ConvierteDatos;

import java.util.*;
import java.util.stream.Collectors;

public class Principal {

    private Scanner entrada = new Scanner(System.in);
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private final String URL_BASE = "https://gutendex.com/books/";
    private ConvierteDatos conversor = new ConvierteDatos();
    public void mostrarMenu() {

        System.out.println("Imprimir todos los datos ");

        var json = consumoAPI.obtenerDatos(URL_BASE);
        System.out.println(json);

        var datosApilibros = conversor.obtenerDatosI(json, DatosApi.class);


        /*List<DatosLibros> libros = datosApilibros.results().stream()
                .map(d -> new DatosLibros(d.titulo(), d.autor(), d.idioma() ,d.descargas()))
                .sorted(Comparator.comparing(DatosLibros::descargas).reversed())
                .collect(Collectors.toList());

        libros.stream().limit(5).forEach(System.out::println);*/

        //Top 10 libros más descargados
        datosApilibros.results().stream()
                        .sorted(Comparator.comparing(DatosLibros::descargas).reversed())
                        .limit(10)
                        .map(k -> k.titulo().toUpperCase())
                        .forEach(System.out::println);

        System.out.println("\n Top 10 libros mas descargados");

        /*libros.stream()
                //.sorted(Comparator.comparing(DatosLibros::descargas).reversed())
                .limit(10)
                .forEach(e -> System.out.println(
                        e.titulo().toUpperCase() + " " + e.descargas()
                ));

        libros.clear();*/

        //Busqueda de libros por nombre
        System.out.println("\n Ingrese el nombre del libro que desea buscar:  ");
        var nombreLibro = entrada.nextLine();

        String buscarLibro = URL_BASE + "?search=" + nombreLibro.replace(" ", "+");

        json = consumoAPI.obtenerDatos(buscarLibro);
        datosApilibros = conversor.obtenerDatosI(json, DatosApi.class);


        /*libros = datosApilibros.results().stream()
                .map(k -> new DatosLibros(k.titulo(), k.autor(),k.idioma(),k.descargas()))
                .collect(Collectors.toList());*/


        Optional<DatosLibros> libroBuscado = datosApilibros.results().stream()
                .filter(e -> e.titulo().toUpperCase().contains(nombreLibro.toUpperCase()))
                .findFirst();


        //Trabajando con estadisticas
        if (libroBuscado.isPresent()) {
            System.out.println("Libro encontrado");
            System.out.println("Titulo: " + libroBuscado);

            DoubleSummaryStatistics est = datosApilibros.results().stream()
                    .filter(e -> e.descargas() > 0.0)
                    .collect(Collectors.summarizingDouble(DatosLibros::descargas));

            System.out.println("Media descargas " + est.getAverage());
            System.out.println("Número mayor de descargas: " + est.getMax());
            System.out.println("Número menor de descargas: " + est.getMin());
            System.out.println("Número de registros contados " + est.getCount());
        } else {
            System.out.println("Libro no encontrado");
        }

    }

}
