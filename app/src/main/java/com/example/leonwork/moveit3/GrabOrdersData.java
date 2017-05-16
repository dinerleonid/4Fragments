package com.example.leonwork.moveit3;

import android.app.IntentService;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.widget.ProgressBar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;


public class GrabOrdersData extends IntentService {
    private static final String KEY = "&key=AIzaSyBWR3S7bcVnysNY49SXQBBapuFsPD_jALk";
    private static final String BASE_URL = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=";
    private static final String DATA_URL = "https://";
    private ProgressBar progressBar;
    private ArrayList<Order> orders = new ArrayList<>();
    private long idInternal, orderNumber;
    private String customerName, address, customerPhoneNumber, deliveryDate, deliveryTime, type, productStatus;
    private int amount;

//long , long , int amount, String customerName, String address, String customerPhoneNumber, String deliveryDate, String deliveryTime, String type

    public GrabOrdersData() {
        super("");
    }

//https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=32.1746364,34.8546449&radius=2&key=AIzaSyBWR3S7bcVnysNY49SXQBBapuFsPD_jALk
    @Override
    protected void onHandleIntent(Intent intent) {

        String url_builder;
        URL url_item;
        HttpURLConnection connection_item;
        BufferedReader reader_item;
        StringBuilder builder_item = new StringBuilder();
        JSONObject location;


        try {
            // create a URL object
            url_builder = BASE_URL + "32.1746364," + "34.8546449" + "&radius=2000" + "&keyword=קפה" + KEY;
            //url_builder = DATA_URL + "";
            url_item = new URL(url_builder);
            // open connection to the server
            connection_item = (HttpURLConnection) url_item.openConnection();
            // check if server response is valid and ok
            if (connection_item.getResponseCode() != HttpURLConnection.HTTP_OK) {
            }
            // create reader objects to read data from server. InputStreamReader can read bytes, BufferedReader can read Strings!
            reader_item = new BufferedReader(new InputStreamReader(connection_item.getInputStream()));
            // read first line from stream
            String line_item = reader_item.readLine();
            // loop while there is data in the string (successfully read a line of text)
            while (line_item != null) {
                builder_item.append(line_item);
                // try to read next line
                line_item = reader_item.readLine();
            }

            // starting to parse JSON
            JSONObject found_list = new JSONObject(builder_item.toString());
            JSONArray found_locations = found_list.getJSONArray("results");
            for (int j = 0; j < found_locations.length(); j++) {
                location = found_locations.getJSONObject(j);
                //update the list with all the items on page

                if (String.valueOf(location.getJSONObject("geometry").getJSONObject("location").getInt("lat")) == "") {
                    amount=132;
                }
                orderNumber = location.getJSONObject("geometry").getJSONObject("location").getLong("lng");
                customerName = location.getString("icon");
                customerPhoneNumber = location.getString("name");
                deliveryDate = location.getString("place_id");

                // getting photo reference from an internal array

                if (location.has("photos")) {
                    JSONArray found_reference = location.getJSONArray("photos");
                    JSONObject loc_photo_reference = found_reference.getJSONObject(0);
                    deliveryTime = loc_photo_reference.getString("photo_reference");
                } else {
                    deliveryTime = "";
                }

                address = location.getString("vicinity");
                type="fuel";
                productStatus = "0";

//                if (location.has("opening_hours")) {
//                    if (String.valueOf(location.getJSONObject("opening_hours").getBoolean("open_now")).equals("true")) {
//                        type = true;
//                    } else if (String.valueOf(location.getJSONObject("opening_hours").getBoolean("open_now")).equals("false")) {
//                        type = false;
//                    } else {
//                        type = false;
//                    }
//                }

                // getting place type from an internal array
//                JSONArray found_type = location.getJSONArray("types");
//                String place_type, place_type1 = null;

//                for (int i = 0 ; i < found_type.length() ; i++){
//                    place_type = found_type.getString(i);
//                    place_type1 = place_type1 + ", " + place_type;
//                }
                orders.add(new Order(idInternal, orderNumber, amount, customerName, address, customerPhoneNumber, deliveryDate, deliveryTime, type, productStatus));
            }

            OrdersDBHelper helper = new OrdersDBHelper(this);
            helper.deleteAllOrders();
            helper.insertOrdersList(orders);

            Intent finishedInsert = new Intent("finished");
            LocalBroadcastManager.getInstance(this).sendBroadcast(finishedInsert);

        }catch(MalformedURLException e){
            e.printStackTrace();

        }catch(IOException e){
            Intent noInternet = new Intent("noconnection");
            LocalBroadcastManager.getInstance(this).sendBroadcast(noInternet);

        }catch(JSONException e){
            throw new RuntimeException(e);

        }


    }
}
