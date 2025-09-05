package com.mpt.journal.model;

// Модель виниловой пластинки
public class VinylRecordModel {
    private int id;
    private String title; // Название пластинки
    private String artist; // Исполнитель
    private String genre; // Жанр
    private int year; // Год выпуска
    private String vinylCondition; // Состояние винила (NM, M, VG, B, P)
    private String coverCondition; // Состояние конверта (NM, M, VG, B, P)
    private boolean isDeleted; // Флаг логического удаления

    public VinylRecordModel() {
        this.isDeleted = false;
    }

    public VinylRecordModel(int id, String title, String artist, String genre, int year, String vinylCondition, String coverCondition) {
        this.id = id;
        this.title = title;
        this.artist = artist;
        this.genre = genre;
        this.year = year;
        this.vinylCondition = vinylCondition;
        this.coverCondition = coverCondition;
        this.isDeleted = false;
    }

    // Геттеры и сеттеры
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getVinylCondition() {
        return vinylCondition;
    }

    public void setVinylCondition(String vinylCondition) {
        this.vinylCondition = vinylCondition;
    }

    public String getCoverCondition() {
        return coverCondition;
    }

    public void setCoverCondition(String coverCondition) {
        this.coverCondition = coverCondition;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }
}