package com.example.gwak.mp_apla_2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView idText=(TextView)findViewById(R.id.id_Text);
        TextView passwdText=(TextView)findViewById(R.id.passwd_Text);
        TextView welcomeMessage=(TextView)findViewById(R.id.welcome_Message);

        Intent intent = getIntent();
        String userID= intent.getStringExtra("userID");
        String userPassword= intent.getStringExtra("userPassword");
        String message = "환영합니다, " + userID + "님!";

        idText.setText(userID);
        passwdText.setText(userPassword);
        welcomeMessage.setText(message);



    }
}
