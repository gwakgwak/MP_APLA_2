package com.example.gwak.mp_apla_2;
//github
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText idText=(EditText)findViewById(R.id.id_Text);
        final EditText passwdText=(EditText)findViewById(R.id.passwd_Text);
        final Button loginButton= (Button)findViewById(R.id.login_Button);
        final TextView registerButton=(TextView)findViewById(R.id.register_Button);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registerIntent = new Intent(LoginActivity.this, RegisterActivity.class);
                LoginActivity.this.startActivity(registerIntent);
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String userID=idText.getText().toString();
                final String userPassword=passwdText.getText().toString();

                Response.Listener<String> responseListener=new Response.Listener<String>(){

                    @Override
                    public void onResponse(String response) {
                        try{
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            if(success) {
                                String userID= jsonResponse.getString("userID");
                                String userPassword= jsonResponse.getString("userPassword");
                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                intent.putExtra("userID", userID);
                                intent.putExtra("userPassword", userPassword);
                                LoginActivity.this.startActivity(intent);
                            }else{
                                AlertDialog.Builder builder= new AlertDialog.Builder(LoginActivity.this);
                                builder.setMessage("Login Failed")
                                        .setNegativeButton("retry", null)
                                        .create().show();
                            }
                        }catch(Exception e){
                            e.printStackTrace();
                        }
                    }
                };

                LoginRequest loginRequest = new LoginRequest(userID, userPassword, responseListener);
                RequestQueue queue=Volley.newRequestQueue(LoginActivity.this);
                queue.add(loginRequest);

            }
        });

    }
}
