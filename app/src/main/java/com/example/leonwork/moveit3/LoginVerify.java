package com.example.leonwork.moveit3;

import android.app.IntentService;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.widget.TextView;

import com.example.leonwork.moveit3.layout.FragmentLogin;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class LoginVerify extends IntentService {

    private String truckNumber, truckPassword;
    private final static String BASE_URL = "https://";
    private FragmentLogin.OnOrderListEnter listener;


    public LoginVerify() {
        super("");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        truckNumber = intent.getStringExtra("truck_number");
        truckPassword = intent.getStringExtra("password");

        HttpURLConnection connectingToVerify;
        URL urlVerify;
        String url_builder="";

        try {

            url_builder = BASE_URL + truckNumber + truckPassword;
            urlVerify = new URL(url_builder);
            connectingToVerify = (HttpURLConnection) urlVerify.openConnection();
            if (connectingToVerify.getResponseCode() != HttpURLConnection.HTTP_OK) {
            }
// TODO go to URL and get response that password and truck numbers match
            listener.OrderListEntered();
            // TODO if (password and truck number verified) open FRAGMENTORDERSLIST fragment




        } catch (MalformedURLException e) {
            e.printStackTrace();

        } catch (IOException e) {
            Intent noInternet = new Intent("noconnection");
            LocalBroadcastManager.getInstance(this).sendBroadcast(noInternet);
        }
    }

    public interface OnOrderListEnter {
        void OrderListEntered();

    }
}
