package com.example.scotiabankplus.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Unique;

import lombok.Getter;
import lombok.Setter;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class SodiacSign {

    @Id
    private Long id;

    @Getter
    @Setter
    @Unique
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


    @Generated(hash = 1881423007)
    public SodiacSign(Long id, String nombre, String fechaSigno, String amor,
            String dinero, String salud, String color, String numero) {
        this.id = id;
        this.nombre = nombre;
        this.fechaSigno = fechaSigno;
        this.amor = amor;
        this.dinero = dinero;
        this.salud = salud;
        this.color = color;
        this.numero = numero;
    }


    @Generated(hash = 1094800272)
    public SodiacSign() {
    }


    @Override
    public String toString() {
        return nombre;
    }


    public Long getId() {
        return this.id;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public String getNombre() {
        return this.nombre;
    }


    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    public String getFechaSigno() {
        return this.fechaSigno;
    }


    public void setFechaSigno(String fechaSigno) {
        this.fechaSigno = fechaSigno;
    }


    public String getAmor() {
        return this.amor;
    }


    public void setAmor(String amor) {
        this.amor = amor;
    }


    public String getDinero() {
        return this.dinero;
    }


    public void setDinero(String dinero) {
        this.dinero = dinero;
    }


    public String getSalud() {
        return this.salud;
    }


    public void setSalud(String salud) {
        this.salud = salud;
    }


    public String getColor() {
        return this.color;
    }


    public void setColor(String color) {
        this.color = color;
    }


    public String getNumero() {
        return this.numero;
    }


    public void setNumero(String numero) {
        this.numero = numero;
    }
}
