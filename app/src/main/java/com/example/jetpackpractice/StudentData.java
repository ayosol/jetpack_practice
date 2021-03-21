package com.example.jetpackpractice;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

// Define Table Name
@Entity(tableName = "table_name")
public class StudentData implements Serializable {

    // Create primaryKey using the id column
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "first_name")
    private String first_name;

    @ColumnInfo(name = "second_name")
    private String second_name;

    @ColumnInfo(name = "email")
    private String email;

    @ColumnInfo(name = "phone_num")
    private String phone_num;

    @ColumnInfo(name = "matric_no")
    private Long matric_no;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getSecond_name() {
        return second_name;
    }

    public void setSecond_name(String second_name) {
        this.second_name = second_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone_num() {
        return phone_num;
    }

    public void setPhone_num(String phone_num) {
        this.phone_num = phone_num;
    }

    public Long getMatric_no() {
        return matric_no;
    }

    public void setMatric_no(Long matric_no) {
        this.matric_no = matric_no;
    }
}
