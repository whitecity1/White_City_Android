package com.example.white_city.Modelo;

public class RegisterRequest {

    private String nombres;
    private String email;
    private String passsword;
    private String passsword_confirmation;

    public String getPasssword_confirmation() {
        return passsword_confirmation;
    }

    public void setPasssword_confirmation(String passsword_confirmation) {
        this.passsword_confirmation = passsword_confirmation;
    }



    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }



    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasssword() {
        return passsword;
    }

    public void setPasssword(String passsword) {
        this.passsword = passsword;
    }
}
