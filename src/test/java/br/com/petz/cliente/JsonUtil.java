package br.com.petz.cliente;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

public class JsonUtil {
    public static String toJson ( Object obj) throws JsonProcessingException {
        return new Gson().toJson(obj);
    }
}
