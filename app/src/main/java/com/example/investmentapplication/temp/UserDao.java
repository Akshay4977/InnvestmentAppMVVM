package com.example.investmentapplication.temp;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface UserDao {
    @Insert
    void insert(User user);

    @Update
    void update(User user);

    @Delete
    void delete(User user);

    @Query("SELECT * FROM user_table WHERE id = :id")
    User getUserByLoginId(String id);

    @Query("SELECT * FROM " + DbConfig.USER_TABLE)
    List<User> getAllUsers();

}


