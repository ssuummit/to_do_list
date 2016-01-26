package com.example.sumitnagariya.todolist;


import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.ActionMode;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class TaskListFragment extends Fragment {

    ListView showNote;
    public static String msg;
    Cursor cursor;
    DBHelper dbHelper;
    TodoCursorAdapter todoAdapter;

    private static final String TAG = "TaskListFragment";

    public TaskListFragment() {

    }

    public static TaskListFragment newTaskListFragment() {
        TaskListFragment tlf = new TaskListFragment();
        return tlf;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View mainView = inflater.inflate(R.layout.fragment_task_list, container, false);
        dbHelper = new DBHelper(getContext());
        cursor = dbHelper.getAllTasks();
        todoAdapter = new TodoCursorAdapter(getContext(), cursor,0);
        showNote = (ListView) mainView.findViewById(R.id.listView);
        showNote.setAdapter(todoAdapter);
        setHasOptionsMenu(true);

        FloatingActionButton fab = (FloatingActionButton) mainView.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchToAddTask();
            }
        });

        registerForContextMenu(showNote);

        showNote.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                long item = todoAdapter.getItemId(position);
                switchToToDoDescription(item);
            }
        });

            return mainView;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        if (v.getId()==R.id.listView) {
            MenuInflater inflater = new MenuInflater(getContext());
            inflater.inflate(R.menu.menu_list, menu);
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int index = info.position;

        if (item.getTitle().equals("edit")) {
            Toast.makeText(getContext(), "edit invoked", Toast.LENGTH_SHORT).show();
        }
        else if (item.getTitle().equals("delete")) {
            dbHelper.deleteTaskTemp(todoAdapter.getItemId(index));
            Toast.makeText(getContext(), "Task deleted successfully", Toast.LENGTH_SHORT).show();
            getActivity().recreate();
        }
        else {
            return false;
        }
        return true;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        inflater.inflate(R.menu.sorting_menu, menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getTitle().equals("SortByDate")) {
            Toast.makeText(getContext(), "SortByDate invoked", Toast.LENGTH_SHORT).show();
        }
        else if (item.getTitle().equals("SortByTitle")) {
            Toast.makeText(getContext(), "SortByTitle invoked", Toast.LENGTH_SHORT).show();
        }
        else if (item.getTitle().equals("SortByPriority")) {

            Toast.makeText(getContext(), "SortByPriority invoked", Toast.LENGTH_SHORT).show();
        }
        else if (item.getTitle().equals("RecycleBin")) {
            switchToRecycleBinFragment();
        }
        else if (item.getTitle().equals("Settings")) {

            Toast.makeText(getContext(), "Settings invoked", Toast.LENGTH_SHORT).show();
        }
        else {
            return false;
        }
        return true;
    }

    private void switchToAddTask() {
        Fragment atf = AddTaskFragment.newAddTaskFragment();
        FragmentManager manager =getFragmentManager();
        Fragment frag = manager.findFragmentByTag("TLF");

        if (frag != null) {
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.remove(frag);
            atf.setTargetFragment(frag, 101);
            transaction.add(R.id.mainLayout, atf, "ATF");
            transaction.addToBackStack(null);
            transaction.commit();

        }
    }

    private void switchToToDoDescription(long item) {
        Fragment tdf = TaskDescriptionFragment.newTaskDescriptionFragment();
        Bundle bundle = new Bundle();
        bundle.putLong("ITEM", item);
        tdf.setArguments(bundle);
        FragmentManager manager = getFragmentManager();
        Fragment frag = manager.findFragmentByTag("TLF");

        if (frag != null) {
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.remove(frag);
            tdf.setTargetFragment(frag, 101);
            transaction.add(R.id.mainLayout, tdf, "TDD");
            transaction.addToBackStack(null);
            transaction.commit();
        }


    }

    private void switchToRecycleBinFragment() {
        Fragment rbf = RecycleBinFragment.newRecycleBinFragment();
        FragmentManager manager =getFragmentManager();
        Fragment frag = manager.findFragmentByTag("TLF");

        if (frag != null) {
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.remove(frag);
            rbf.setTargetFragment(frag, 101);
            transaction.add(R.id.mainLayout, rbf, "RBF");
            transaction.addToBackStack(null);
            transaction.commit();

        }
    }

    private void editTask() {
        Fragment atf = AddTaskFragment.newAddTaskFragment();
        FragmentManager manager =getFragmentManager();
        Fragment frag = manager.findFragmentByTag("TLF");

        if (frag != null) {
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.remove(frag);
            atf.setTargetFragment(frag, 101);
            transaction.add(R.id.mainLayout, atf, "ATF");
            transaction.addToBackStack(null);
            transaction.commit();

        }
    }

}
