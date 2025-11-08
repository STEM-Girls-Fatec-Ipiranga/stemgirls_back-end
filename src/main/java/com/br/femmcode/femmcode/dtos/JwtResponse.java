// src/main/java/com/br/femmcode/femmcode/dtos/JwtResponse.java
package com.br.femmcode.femmcode.dtos;

public class JwtResponse<T> {
    private String token;
    private T user;

    public JwtResponse(String token, T user) {
        this.token = token;
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public T getUser() {
        return user;
    }
}
