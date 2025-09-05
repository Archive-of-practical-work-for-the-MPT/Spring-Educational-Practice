package com.mpt.journal.entity;

import com.mpt.journal.model.VinylRecordModel;

public class VinylRecordEntity extends VinylRecordModel {
    
    public VinylRecordEntity() {
        super();
    }
    
    public VinylRecordEntity(int id, String title, String artist, String genre, int year) {
        super(id, title, artist, genre, year, "VG", "VG");
    }
    
    public VinylRecordEntity(int id, String title, String artist, String genre, int year, String vinylCondition, String coverCondition) {
        super(id, title, artist, genre, year, vinylCondition, coverCondition);
    }
}