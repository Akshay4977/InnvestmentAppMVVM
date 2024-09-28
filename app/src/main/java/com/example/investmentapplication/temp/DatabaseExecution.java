package com.example.investmentapplication.temp;


import android.content.Context;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class DatabaseExecution {
    public static AppDatabase appDatabase;
    private final ExecutorService executorService = Executors.newSingleThreadExecutor();
    private UserDao userDao;

    public void execute(Context context) {
        appDatabase = AppDatabase.getDatabase(context);
        userDao = appDatabase.userDao();
    }

    public void insertTestUser(String name, String contact, String path) {
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                User testUser = new User();
                testUser.setFullName(name);
                testUser.setContact(contact);
                testUser.setPath(path);
                userDao.insert(testUser);
            }
        });
    }

    public List<User> getUserList() {
        return userDao.getAllUsers();
    }

    public void insertTestUser1(String name, String contact, String path) {
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                User testUser = new User();
                testUser.setFullName("admsi");
                testUser.setContact("984326433");
                testUser.setPath("path");
                userDao.insert(testUser);
            }
        });
    }
}
