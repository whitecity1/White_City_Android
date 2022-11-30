package com.example.white_city.Utils;

public class Apis {

    public static  final String URL_001="http://10.0.2.2:8000/   ";

    public static PersonaService getPersonaService() {
        return  Cliente.getCliente(URL_001).create(PersonaService.class);
    }
}
