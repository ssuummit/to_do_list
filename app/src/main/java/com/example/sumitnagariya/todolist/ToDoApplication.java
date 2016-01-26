package com.example.sumitnagariya.todolist;

import android.app.Application;

/**
 * Created by sumit.nagariya on 22/01/16.
 */
public class ToDoApplication extends Application {
    DBHelper dbHelper;

    @Override
    public void onCreate() {
        super.onCreate();

        dbHelper = new DBHelper(this);
    }
}
