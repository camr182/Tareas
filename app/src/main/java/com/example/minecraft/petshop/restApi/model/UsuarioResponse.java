package com.example.minecraft.petshop.restApi.model;

/**
 * Created by minecraft on 21/05/2017.
 */

public class UsuarioResponse {

    private String idDispositivo;
    private String idUsuarioInstagram;

    public UsuarioResponse() {
    }

    public String getIdDispositivo() {
        return idDispositivo;
    }

    public void setIdDispositivo(String idDispositivo) {
        this.idDispositivo = idDispositivo;
    }

    public String getUsuarioInstagram() {
        return idUsuarioInstagram;
    }

    public void setUsuarioInstagram(String usuarioInstagram) {
        this.idUsuarioInstagram = usuarioInstagram;
    }
}
