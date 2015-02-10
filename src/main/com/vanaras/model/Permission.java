package com.vanaras.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="permission")
public enum Permission {
    ADD_BOOK(1),
    BORROW_BOOK(2), RETURN_BOOK(3), REMOVE_BOOK(4);

    @Id
    private final int id;

    Permission(int i) {
        this.id =i;
    }

    public int getId(){
        return id;
    }

    public String getDescription(){
        return this.toString();
    }

}
