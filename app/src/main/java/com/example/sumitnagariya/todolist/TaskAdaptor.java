package com.example.sumitnagariya.todolist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by sumit.nagariya on 20/01/16.
 */
public class TaskAdaptor extends BaseAdapter {
    Context context;
    ArrayList<Task> tasks;

    private static class ViewHolder {

        TextView textView;
        TextView priority;
        TextView dueDate;

        public ViewHolder( TextView textView, TextView priority, TextView dueDate) {

            this.textView = textView;
            this.priority = priority;
            this.dueDate = dueDate;
        }
    }

    public TaskAdaptor(Context context, ArrayList<Task> tasks) {
        this.context = context;
        this.tasks = tasks;
    }

    @Override
    public int getCount() {
        return tasks.size();
    }

    @Override
    public Object getItem(int position) {
        return tasks.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View mainView = null;
        mainView = LayoutInflater.from(this.context).inflate(R.layout.row, null);
        TextView tv = (TextView) mainView.findViewById(R.id.rowTextView);
        TextView prty = (TextView) mainView.findViewById(R.id.rowTextView2);
        TextView date = (TextView) mainView.findViewById(R.id.rowTextView3);
        ViewHolder vh = new ViewHolder( tv, prty, date);
        //attach view holder to view
        mainView.setTag(vh);
        Task taskTitle = tasks.get(position);
        String title = taskTitle.title;
        String priority = taskTitle.priority;
        String dueDate = taskTitle.dueDate;
        ViewHolder vh1 = (ViewHolder) mainView.getTag();
        TextView tv1 = vh1.textView;
        tv1.setText(title);
        TextView tv2 = vh1.priority;
        tv2.setText(priority);
        TextView tv3 = vh1.dueDate;
        tv3.setText(dueDate);


        return mainView;
    }
}
