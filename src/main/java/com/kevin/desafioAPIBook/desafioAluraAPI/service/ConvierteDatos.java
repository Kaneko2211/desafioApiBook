package com.kevin.desafioAPIBook.desafioAluraAPI.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ConvierteDatos implements IConvierteDatos {

    private ObjectMapper objetoMapper = new ObjectMapper();

    @Override
    public <T> T obtenerDatosI(String json, Class<T> clase) {

        try {
            return objetoMapper.readValue(json, clase);
        }catch (JsonProcessingException e){
            throw new RuntimeException(e);
        }

    }
}
