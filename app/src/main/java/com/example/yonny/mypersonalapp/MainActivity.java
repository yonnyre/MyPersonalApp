package com.example.yonny.mypersonalapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    // SharedPreferences
    private SharedPreferences sharedPreferences;

    private EditText usernameInput;
    private EditText passwordInput;
    private ProgressBar progressBar;
    private View loginPanel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usernameInput = (EditText)findViewById(R.id.username_input);
        passwordInput = (EditText)findViewById(R.id.password_input);
        progressBar = (ProgressBar)findViewById(R.id.progressbar);
        loginPanel = findViewById(R.id.login_panel);



        // init SharedPreferences
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        // username remember
        String username = sharedPreferences.getString("username", null);
        if(username != null){
            usernameInput.setText(username);
            passwordInput.requestFocus();

        }

        // islogged remember
        if(sharedPreferences.getBoolean("islogged", false)){
            // Go to Dashboard
            goDashboard();
        }

    }

    public void callLogin(View view){
        loginPanel.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);

        String username = usernameInput.getText().toString();
        String password = passwordInput.getText().toString();



        // Login logic
        User user = UserRepository.login(username, password);
        user = UserRepository.addUsuario(username, password);



        Toast.makeText(this, "Welcome " + user.getFullname(), Toast.LENGTH_SHORT).show();

        // Save to SharedPreferences
       SharedPreferences.Editor editor = sharedPreferences.edit();
        boolean success = editor
                .putString("username", user.getUsername())
                .putString("fullname", user.getFullname())
                .putBoolean("islogged", true)
                .commit();




        // Go to Dashboard
        goDashboard();
    }

    private void goDashboard(){
        startActivity(new Intent(this, Main2Activity.class));
        finish();
    }


}
