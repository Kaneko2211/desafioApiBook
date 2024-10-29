package com.kevin.desafioAPIBook.desafioAluraAPI.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDate;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatoAutor(
        @JsonAlias("name")  String nombre,

        @JsonAlias("birth_year")  String fechaDeNacimiento){




}
