package com.example.scotiabankplus;

import lombok.Getter;
import lombok.Setter;

public class SodiacSign {

    @Getter
    @Setter
    private String nombre;

    @Getter
    @Setter
    private String fechaSigno;

    @Getter
    @Setter
    private String amor;

    @Getter
    @Setter
    private String dinero;

    @Getter
    @Setter
    private String salud;

    @Getter
    @Setter
    private String color;

    @Getter
    @Setter
    private String numero;


    @Override
    public String toString() {
        return nombre;
    }
}
