package com.mpt.journal.model;

// Модель коллекционера
public class CollectorModel {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private int vinylCount; // Количество пластинок у коллекционера
    private boolean isDeleted; // Флаг логического удаления

    public CollectorModel() {
        this.isDeleted = false;
        this.vinylCount = 0;
    }

    public CollectorModel(int id, String firstName, String lastName, String email, String phoneNumber, int vinylCount) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.vinylCount = vinylCount;
        this.isDeleted = false;
    }

    // Геттеры и сеттеры
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getVinylCount() {
        return vinylCount;
    }

    public void setVinylCount(int vinylCount) {
        this.vinylCount = vinylCount;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    // Метод для получения полного имени
    public String getFullName() {
        return firstName + " " + lastName;
    }
}