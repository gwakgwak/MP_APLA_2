package com.example.gwak.mp_apla_2;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Gwak on 2017-11-19.
 */

public class RegisterRequest extends StringRequest{

    final static private String URL="http://wlstjd8445.cafe24.com/Register.php";
    private Map<String, String> parameters;

    public RegisterRequest(String userID, String userPassword, String userName, int userAge, Response.Listener<String> listener){
        super(Method.POST, URL, listener, null);

        parameters = new HashMap<>();
        parameters.put("userID", userID);
        parameters.put("userPassword", userPassword);
        parameters.put("userName", userName);
        parameters.put("userAge", userAge+"");
       // Log.i("register req ---",userID + " / "+ userName +" / ");
    }
    @Override
    public Map<String, String> getParams(){
        return parameters;
    }
}
