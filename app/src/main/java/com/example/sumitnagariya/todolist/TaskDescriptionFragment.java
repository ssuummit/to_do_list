package com.example.sumitnagariya.todolist;


import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class TaskDescriptionFragment extends Fragment {

    Cursor cursor;
    DBHelper dbHelper;
    TextView title;
    TextView description;
    TextView dueDate;
    TextView priority;
    Button deleteButton;

    public TaskDescriptionFragment() {
        // Required empty public constructor
    }

    public static TaskDescriptionFragment newTaskDescriptionFragment(){
        TaskDescriptionFragment tdf = new TaskDescriptionFragment();
        return tdf;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        final View mainView =  inflater.inflate(R.layout.fragment_task_description, container, false);
        Bundle bundle = getArguments();
        final long item = bundle.getLong("ITEM");
        dbHelper = new DBHelper(getContext());
        cursor = dbHelper.getTaskDetails(item);

        String task_title = cursor.getString(cursor.getColumnIndex("title"));
        String task_desc = cursor.getString(cursor.getColumnIndex("description"));
        String task_due_date = cursor.getString(cursor.getColumnIndex("due_date"));
        String task_priority = cursor.getString(cursor.getColumnIndex("priority"));


        title = (TextView) mainView.findViewById(R.id.titleTV);
        description = (TextView) mainView.findViewById(R.id.descEditText);
        dueDate = (TextView) mainView.findViewById(R.id.displayDueDate);
        priority = (TextView) mainView.findViewById(R.id.priorityDesc);

        title.setText(task_title);
        description.setText(task_desc);
        dueDate.setText(task_due_date);
        priority.setText(task_priority);

        priority.setKeyListener(null);
        title.setKeyListener(null);
        description.setKeyListener(null);
        dueDate.setKeyListener(null);

        deleteButton = (Button) mainView.findViewById(R.id.deleteButton);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHelper.deleteTaskTemp(item);
                Toast.makeText(getContext(), "Task deleted successfully", Toast.LENGTH_SHORT).show();

                getFragmentManager().popBackStack();
            }
        });

        return mainView;
    }

}
