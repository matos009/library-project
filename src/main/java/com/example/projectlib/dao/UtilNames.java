package com.example.projectlib.dao;

public enum UtilNames {
    URL("jdbc:postgresql://localhost:5432/librarydb"),
    USERNAME("artur"),
    PASSWORD("0905");

    private final String value;

    // Конструктор
    UtilNames(String value) {
        this.value = value;
    }

    // Метод для получения значения
    public String getValue() {
        return value;
    }
}
