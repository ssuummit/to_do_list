package com.example.sumitnagariya.todolist;


import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class RecycleBinFragment extends Fragment {

    ListView showNote;
    Cursor cursor;
    DBHelper dbHelper;
    TodoCursorAdapter todoAdapter;

    public RecycleBinFragment() {
        // Required empty public constructor
    }

    public static RecycleBinFragment newRecycleBinFragment(){
        RecycleBinFragment rbf = new RecycleBinFragment();
        return rbf;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View mainView = inflater.inflate(R.layout.fragment_recycle_list, container, false);
        dbHelper = new DBHelper(getContext());
        cursor = dbHelper.getAllRecycleBinTasks();
        todoAdapter = new TodoCursorAdapter(getContext(), cursor,0);
        showNote = (ListView) mainView.findViewById(R.id.recycleBinLV);
        showNote.setAdapter(todoAdapter);

        registerForContextMenu(showNote);

        return mainView;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        if (v.getId()==R.id.recycleBinLV) {
            MenuInflater inflater = new MenuInflater(getContext());
            inflater.inflate(R.menu.menu_list_recyclebin, menu);
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int index = info.position;

        if (item.getTitle().equals("move to task list")) {
            dbHelper.restoreTask(todoAdapter.getItemId(index));
            Toast.makeText(getContext(), "Task has been restored", Toast.LENGTH_SHORT).show();
            getActivity().recreate();
        }
        else if (item.getTitle().equals("delete")) {
            dbHelper.deleteTaskFromRecycleBin(todoAdapter.getItemId(index));
            Toast.makeText(getContext(), "Task permanently deleted", Toast.LENGTH_SHORT).show();
            getActivity().recreate();
        }
        else {
            return false;
        }
        return true;
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_list_recyclebin, menu);

    }

}