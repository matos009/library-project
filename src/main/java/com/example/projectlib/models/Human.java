package com.example.projectlib.models;

import java.util.ArrayList;
import java.util.List;

public class Human {
    private int id;
    private String fio;
    private int birthYear;

    public Human() {

    }

    public Human(int id, String fio, String name, String surname, int birthYear) {
        this.id = id;
        this.fio = fio;
        this.birthYear = birthYear;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }


    public int getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(int birthYear) {
        this.birthYear = birthYear;
    }

    @Override
    public String toString() {
        return "Human{" +
                "id=" + id +
                ", fio='" + fio + '\'' +
                ", birthYear=" + birthYear +
                '}';
    }
}
