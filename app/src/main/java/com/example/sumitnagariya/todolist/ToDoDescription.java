package com.example.sumitnagariya.todolist;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class ToDoDescription extends Fragment {

      TextView title;
    TextView desc;
    public ToDoDescription() {
        // Required empty public constructor
    }

    public static ToDoDescription newToDoFrame(){
        ToDoDescription frame = new ToDoDescription();
        return  frame;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View mainView =  inflater.inflate(R.layout.fragment_to_do_description, container, false);
        Bundle bundle = getArguments();
        title = (TextView) mainView.findViewById(R.id.textView2);
        desc = (TextView) mainView.findViewById(R.id.textView3);
        title.setText(bundle.getString("TITLE"));
        desc.setText(bundle.getString("DESCRIPTION"));
        return  mainView;
    }

}
