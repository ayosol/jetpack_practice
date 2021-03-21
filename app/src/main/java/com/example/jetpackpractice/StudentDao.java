package com.example.jetpackpractice;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface StudentDao {

    //insert query
    @Insert(onConflict = REPLACE)
    void insert(StudentData studentData);

    //delete query
    @Delete
    void delete(StudentData studentData);

    // delete all queries
    @Delete
    void reset(List<StudentData> studentData);

    // update query
    @Query("UPDATE table_name SET first_name = :sFirstName, second_name = :sLastName, email = :sEmail, phone_num = :sPhoneNo, matric_no = :sMatricNo  WHERE ID = :sId")
    void update(int sId, String sFirstName, String sLastName, String sEmail, String sPhoneNo, Long sMatricNo);

    // Get all data queries
    @Query("SELECT * FROM table_name")
    List<StudentData> getAll();


}
