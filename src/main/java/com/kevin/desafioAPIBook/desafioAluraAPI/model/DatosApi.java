package com.kevin.desafioAPIBook.desafioAluraAPI.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosApi(
        @JsonAlias("count")  Integer cantidad,
        @JsonAlias("results")  List<DatosLibros> results
){

}

