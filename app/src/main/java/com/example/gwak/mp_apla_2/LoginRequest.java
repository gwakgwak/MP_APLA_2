package com.example.gwak.mp_apla_2;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Gwak on 2017-11-19.
 */

public class LoginRequest extends StringRequest{

    final static private String URL="http://wlstjd8445.cafe24.com/Login.php";
    private Map<String, String> parameters;

    public LoginRequest(String userID, String userPassowrd,  Response.Listener<String> listener){
        super(Method.POST, URL, listener, null);
        parameters = new HashMap<>();
        parameters.put("userID", userID);
        parameters.put("userPassword", userPassowrd);

    }
    @Override
    public Map<String, String> getParams(){
        return parameters;
    }
}
