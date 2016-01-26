package com.example.sumitnagariya.todolist;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

/**
 * Created by sumit.nagariya on 26/01/16.
 */
public class TodoCursorAdapter extends CursorAdapter {

    public TodoCursorAdapter(Context context, Cursor c, int flags) {
        super(context, c, flags);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.row, parent, false);

    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        TextView tvtitle = (TextView) view.findViewById(R.id.rowTextView);
        TextView tvPriority = (TextView) view.findViewById(R.id.rowTextView2);
        TextView tvduedate = (TextView) view.findViewById(R.id.rowTextView3);

        // Extract properties from cursor
        String title = cursor.getString(cursor.getColumnIndexOrThrow("title"));
        String priority = cursor.getString(cursor.getColumnIndexOrThrow("priority"));
        String dueDate = cursor.getString(cursor.getColumnIndexOrThrow("due_date"));
        // Populate fields with extracted properties
        tvtitle.setText(title);
        tvPriority.setText(priority);
        tvduedate.setText(dueDate);



    }

    @Override
    public Object getItem(int position) {
        return super.getItem(position);

    }
}
