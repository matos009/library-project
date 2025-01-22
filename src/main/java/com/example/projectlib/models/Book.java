package com.example.projectlib.models;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class Book {
    private int id;

    @NotBlank(message = "Название книги не должно быть пустым")
    @Size(max = 255, message = "Название книги не должно превышать 255 символов")
    private String name;

    @NotBlank(message = "Автор не должен быть пустым")
    @Size(max = 255, message = "Имя автора не должно превышать 255 символов")
    private String author;
    @Min(value = 1950, message = "Год должен быть не раньше 1950")
    @Max(value = 2025, message = "Год должен быть не позже 2025")
    private int year;

    private Human user;

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    private int user_id;

    public Human getUser() {
        return user;
    }

    public void setUser(Human user) {
        this.user = user;
    }

    public Book() {

    }

    public Book(int id, String name, String author, int year) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.year = year;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


}
