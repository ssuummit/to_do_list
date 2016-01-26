package com.example.sumitnagariya.todolist;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                switchToAddTask();
//            }
//        });

        FragmentManager manager = getSupportFragmentManager();
        Fragment frag = manager.findFragmentByTag("TLF");
        if (frag ==null) {
            loadTaskListFragment();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main,menu);
        return true;
    }

    private void loadTaskListFragment(){
        Fragment tlf = TaskListFragment.newTaskListFragment();
        FragmentManager manager =getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.mainLayout, tlf,"TLF");
        transaction.commit();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


//    private void switchToAddTask() {
//        Fragment atf = AddTaskFragment.newAddTaskFragment();
//        FragmentManager manager =getSupportFragmentManager();
//        Fragment frag = manager.findFragmentByTag("TLF");
//
//        if (frag != null) {
//            FragmentTransaction transaction = manager.beginTransaction();
//            transaction.remove(frag);
//            atf.setTargetFragment(frag, 101);
//            transaction.add(R.id.mainLayout, atf, "ATF");
//            transaction.addToBackStack(null);
//            transaction.commit();
//
//        }
//    }
}
