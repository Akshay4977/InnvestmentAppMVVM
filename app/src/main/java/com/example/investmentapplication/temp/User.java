package com.example.investmentapplication.temp;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = DbConfig.USER_TABLE)
public class User {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "full_name")
    public String fullName;

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    @ColumnInfo(name = "contact")
    public String contact;

    @ColumnInfo(name = "path")
    public String path;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}

