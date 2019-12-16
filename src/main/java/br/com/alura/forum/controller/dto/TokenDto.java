package br.com.alura.forum.controller.dto;

public class TokenDto {

    private final String token;
    private final String tipo;

    public TokenDto(String token, String tipo) {
        this.token = token;
        this.tipo = tipo;
    }

    public String getTipo() {
        return this.tipo;
    }

    public String getToken() {
        return this.token;
    }

}
