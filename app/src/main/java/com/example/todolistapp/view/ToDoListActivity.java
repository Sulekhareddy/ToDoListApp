package com.example.todolistapp.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.todolistapp.R;
import com.example.todolistapp.listeners.OnItemClickedListener;
import com.example.todolistapp.model.ToDoItem;
import com.example.todolistapp.view.adapter.ToDoListAdapter;

import java.util.ArrayList;
import java.util.List;

public class ToDoListActivity extends AppCompatActivity implements OnItemClickedListener {

    private EditText toDoList_EditText;
    private Button addButton;
    private Button resetButton;

    private RecyclerView recyclerView;
    private ToDoListAdapter adapter;
    private List<ToDoItem> toDOList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do_list);

        toDoList_EditText = findViewById(R.id.list_editText);
        addButton = findViewById(R.id.add_button);
        resetButton = findViewById(R.id.reset_button);

        recyclerView = findViewById(R.id.recyclerview);
        adapter = new ToDoListAdapter();
        adapter.setAdapterItems(toDOList);
        adapter.setOnItemClickedListener(this);


        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String toDoItemsData = toDoList_EditText.getText().toString();
                if(!toDoItemsData.isEmpty()){
                    int topPos = 0;
                    toDOList.add(topPos, new ToDoItem(toDoItemsData));
                    adapter.notifyItemInserted(topPos);
                    recyclerView.scrollToPosition(topPos);
                }else{
                    Toast.makeText(ToDoListActivity.this, "Please enter ToDo", Toast.LENGTH_SHORT).show();
                }
            }
        });

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toDoList_EditText.setText("");
            }
        });


    }

    @Override
    public void onToDoListItemClicked(ToDoItem toDoItem) {

        Toast.makeText(this,  toDoItem.getToDoItems() + " has clicked", Toast.LENGTH_SHORT).show();
    }
}
