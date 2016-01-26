package com.example.sumitnagariya.todolist;


import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

/**
 * Created by sumit.nagariya on 19/01/16.
 */
public class Task implements Parcelable {

    public String title;
    public String description;
    public  String priority;
    public String dueDate;

    public static Creator<Task> CREATOR = new Creator<Task>() {
        @Override
        public Task createFromParcel(Parcel source) {
            Task task = new Task();
            task.title = source.readString();
            task.description = source.readString();
            task.priority = source.readString();
            task.dueDate = source.readString();
            return task;
        }

        @Override
        public Task[] newArray(int size) {
            return new Task[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(description);
        dest.writeString(priority);
        dest.writeString(dueDate);
    }
}
