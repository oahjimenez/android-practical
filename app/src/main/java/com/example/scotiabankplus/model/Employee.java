package com.example.scotiabankplus.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

import lombok.Builder;

@Builder
@Entity
public class Employee {
    @Id
    private Long id;

    private String names;

    private String fullnames;

    private String email;

    private String charge;

    @Generated(hash = 1341768034)
    public Employee(Long id, String names, String fullnames, String email,
            String charge) {
        this.id = id;
        this.names = names;
        this.fullnames = fullnames;
        this.email = email;
        this.charge = charge;
    }

    @Generated(hash = 202356944)
    public Employee() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNames() {
        return this.names;
    }

    public void setNames(String names) {
        this.names = names;
    }

    public String getFullnames() {
        return this.fullnames;
    }

    public void setFullnames(String fullnames) {
        this.fullnames = fullnames;
    }

    public String getCharge() {
        return this.charge;
    }

    public void setCharge(String charge) {
        this.charge = charge;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
