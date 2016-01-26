package com.example.sumitnagariya.todolist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by sumit.nagariya on 22/01/16.
 */
public class DBHelper extends SQLiteOpenHelper{

    private static final String TAG = "DBHelper";

    public DBHelper(Context context){
        super(context,"todo.db",null,1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String query1 = "create table tasks(_id integer primary key," +
                        " title text not null, " +
                        "priority text not null," +
                        "description text not null, " +
                        "due_date date not null, " +
                        "is_recycled INTEGER DEFAULT 0 )";

        db.execSQL(query1);

//        String query2 = "create table recycle_tasks(_id integer primary key," +
//                " title text not null, " +
//                "priority text not null," +
//                "description text not null, " +
//                "due_date date not null)";
//
//        db.execSQL(query2);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public Cursor getAllTasks(){

        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.query("tasks", null, "is_recycled=0", null, null, null, null);
        return cursor;
    }

    public Cursor getAllRecycleBinTasks(){

        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.query("tasks", null, "is_recycled=1", null, null, null, null);
        return cursor;
    }

    public Cursor getTaskDetails(long task_id){

        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.query("tasks", null, "_id="+task_id, null, null, null, null);
        cursor.moveToFirst();
        return cursor;
    }


    public void addNewTask(String message, String desc, String priority, String dueDate){

        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues row = new ContentValues();
        row.put("title", message);
        row.put("description", desc);
        row.put("priority", priority);
        row.put("due_date", dueDate);
        database.insert("tasks", null, row);
    }

    public void deleteTaskTemp(long task_id){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put("is_recycled", 1);
        database.update("tasks",cv, "_id="+task_id,null);
//        Cursor cursor = getTaskDetails(task_id);
//
//        String title = cursor.getString(cursor.getColumnIndex("title"));
//        String desc = cursor.getString(cursor.getColumnIndexOrThrow("description"));
//        long _id = cursor.getLong(cursor.getColumnIndexOrThrow("_id"));
//        String priority = cursor.getString(cursor.getColumnIndexOrThrow("priority"));
//        String dueDate = cursor.getString(cursor.getColumnIndexOrThrow("due_date"));
//        database.delete("tasks", "_id=" + task_id, null);
//        String id = String.valueOf(_id);
//
//        ContentValues values = new ContentValues();
//        values.put("_id", _id);
//        values.put("title", title);
//        values.put("priority", priority);
//        values.put("description", desc);
//        values.put("due_date", dueDate);
//        database.insert("recycle_tasks", null, values);


    }

    public void deleteTaskFromRecycleBin(long task_id){
        SQLiteDatabase database = this.getWritableDatabase();
        database.delete("tasks", "_id=" + task_id, null);

    }

    public void restoreTask(long task_id){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put("is_recycled", 0);
        database.update("tasks",cv, "_id="+task_id,null);

    }

}
