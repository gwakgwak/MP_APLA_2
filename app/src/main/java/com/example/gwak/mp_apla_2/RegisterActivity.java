package com.example.gwak.mp_apla_2;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final EditText idText=(EditText)findViewById(R.id.id_Text);
        final EditText passwdText=(EditText)findViewById(R.id.passwd_Text);
        final EditText nameText=(EditText)findViewById(R.id.name_Text);
        final EditText ageText=(EditText)findViewById(R.id.age_Text);

        Button registerButton= (Button)findViewById(R.id.register_Button);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userID= idText.getText().toString();
                String userPassword = passwdText.getText().toString();
                String userName= nameText.getText().toString();
                int userAge = Integer.parseInt(ageText.getText().toString());
                //Log.i("aaa",userID + " / "+ userName +" / ");

                Response.Listener<String> responseListener = new Response.Listener<String>(){
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            if(success){

                                AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                                builder.setMessage("register successed")
                                        .setPositiveButton("OK", new DialogInterface.OnClickListener(){

                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                                RegisterActivity.this.startActivity(intent);
                                            }
                                        })
                                        .create().show();

                            }
                            else{
                                AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                                builder.setMessage("register failed")
                                        .setNegativeButton("Retry", null)
                                        .create().show();
                            }
                        }catch(JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };
                Toast.makeText(getApplicationContext(), " "+userID+" / "+ userPassword + " / " + userName, Toast.LENGTH_LONG);
               // Log.i("kkkkkkkk---",userID + " / "+ userName +" / ");
                RegisterRequest registerRequest = new RegisterRequest(userID, userPassword, userName, userAge, responseListener);
                RequestQueue queue = Volley.newRequestQueue(RegisterActivity.this);
                queue.add(registerRequest);
            }
        });

    }
}
