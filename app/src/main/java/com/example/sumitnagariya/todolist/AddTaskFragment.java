package com.example.sumitnagariya.todolist;


import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.zip.Inflater;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddTaskFragment extends Fragment {

    Button doneButton;
    Button cancelButton;
    TextView titleTV;
    TextView descEditText;
    Button setDueDate;
    TextView displayDueDate;
    TextView priorityDesc;
    Cursor cursor;
    DBHelper dbHelper;
    SQLiteDatabase database;

    public AddTaskFragment() {
        // Required empty public constructor
    }

    public static AddTaskFragment newAddTaskFragment(){
        AddTaskFragment atf = new AddTaskFragment();
        return  atf;
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View mainView =  inflater.inflate(R.layout.fragment_add_task, container, false);
        dbHelper = new DBHelper(getContext());
        database = dbHelper.getWritableDatabase();

        doneButton = (Button) mainView.findViewById(R.id.doneButton);
        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = titleTV.getText().toString();
                String desc = descEditText.getText().toString();
                String priority = priorityDesc.getText().toString();
                String dueDate = displayDueDate.getText().toString();
                dbHelper.addNewTask(message, desc, priority, dueDate);
//                ContentValues row = new ContentValues();
//                row.put("title", message);
//                row.put("description", desc);
//                row.put("priority", priority);
//                row.put("due_date", dueDate);
//                database.insert("tasks", null,row);

//                Intent intent=new Intent();
//                Task tasks = new Task();
//                tasks.title=message;
//                tasks.description=desc;
//                tasks.priority=priority;
//                tasks.dueDate=dueDate;
//                intent.putExtra("NEW_TASK", tasks);
//                getTargetFragment().onActivityResult(101, Activity.RESULT_OK, intent);

                getFragmentManager().popBackStack();
            }
        });
        cancelButton = (Button) mainView.findViewById(R.id.cancelButton);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStack();
            }
        });
        titleTV = (TextView) mainView.findViewById(R.id.titleTV);
        descEditText = (TextView) mainView.findViewById(R.id.descEditText);


        setDueDate = (Button) mainView.findViewById(R.id.setDueDate);
        displayDueDate = (TextView) mainView.findViewById(R.id.displayDueDate);
        priorityDesc = (TextView) mainView.findViewById(R.id.priorityDesc);
        setDueDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PickerDialogue pickerDialogue = new PickerDialogue(displayDueDate);
                FragmentManager manager = getFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                pickerDialogue.show(transaction,"DatePicker");
            }
        });

        Spinner spinner = (Spinner) mainView.findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
        R.array.priority, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                priorityDesc.setText(parent.getItemAtPosition(position).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        return  mainView;
    }



}
