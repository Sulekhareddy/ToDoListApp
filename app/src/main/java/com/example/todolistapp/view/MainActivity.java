package com.example.todolistapp.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.todolistapp.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText userNameEditText;
    private EditText passwordEditText;
    private Button submitButton;
    private Button resetButton;
    private SharedPreferences sharedPreferences;
    public static final String APP_SHARED_PREF = "APP_PREF";
    public static final String PREF_KEY_USERNAME = "USER_NAME";
    public static final String PREF_KEY_PWD = "PASSWORD";
    public static final String PREF_KEY_USER_LOGGED_IN = "LOGGED_IN";

    private String prefUserName;
    private String prefPwd;
    private boolean prefIsAlreadyLoggedIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userNameEditText = findViewById(R.id.user_name_edit_text);
        passwordEditText = findViewById(R.id.password_edit_text);
        submitButton = findViewById(R.id.submit_button);
        resetButton = findViewById(R.id.reset_button);

        submitButton.setOnClickListener(this);
        resetButton.setOnClickListener(this);

        sharedPreferences = getSharedPreferences(APP_SHARED_PREF, Context.MODE_PRIVATE);
        prefUserName = sharedPreferences.getString(PREF_KEY_USERNAME, "");
        prefPwd = sharedPreferences.getString(PREF_KEY_PWD, "");
        prefIsAlreadyLoggedIn = sharedPreferences.getBoolean(PREF_KEY_USER_LOGGED_IN, false);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.submit_button:

                String userName = userNameEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                if (!userName.isEmpty() && !password.isEmpty()) {
                    if (prefIsAlreadyLoggedIn) {
                        if (userName.equals(prefUserName) && password.equals(prefPwd)) {
                            startToDoListActivity(userName, password);
                        } else {
                            Toast.makeText(this, "Incorrect username or password", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        saveUserName(userName, password);
                        startToDoListActivity(userName, password);
                    }
                } else {
                    Toast.makeText(this, "Please enter the login details", Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.reset_button:

                userNameEditText.setText("");
                passwordEditText.setText("");
                break;
        }

    }

    private void saveUserName(String userName, String password) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(PREF_KEY_USERNAME, userName);
        editor.putString(PREF_KEY_PWD, password);
        editor.putBoolean(PREF_KEY_USER_LOGGED_IN, true);
        editor.apply();
    }

    private void startToDoListActivity(String userName, String password) {
        Intent intent = new Intent(this, ToDoListActivity.class);
        startActivity(intent);

    }

}
