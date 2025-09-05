package com.mpt.journal.entity;

import com.mpt.journal.model.CollectorModel;

public class CollectorEntity extends CollectorModel {
    
    public CollectorEntity() {
        super();
    }
    
    public CollectorEntity(int id, String firstName, String lastName, String email, String phoneNumber) {
        super(id, firstName, lastName, email, phoneNumber, 0); // Default vinyl count
    }
    
    public CollectorEntity(int id, String firstName, String lastName, String email, String phoneNumber, int vinylCount) {
        super(id, firstName, lastName, email, phoneNumber, vinylCount);
    }
}