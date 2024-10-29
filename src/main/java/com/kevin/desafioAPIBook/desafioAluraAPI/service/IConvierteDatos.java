package com.kevin.desafioAPIBook.desafioAluraAPI.service;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface IConvierteDatos {
    <T> T obtenerDatosI(String json, Class<T> clase) throws JsonProcessingException;
}
