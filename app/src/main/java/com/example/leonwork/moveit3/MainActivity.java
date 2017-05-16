package com.example.leonwork.moveit3;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.leonwork.moveit3.layout.FragmentDetailedOrder;
import com.example.leonwork.moveit3.layout.FragmentLogin;
import com.example.leonwork.moveit3.layout.FragmentOrdersList;

public class MainActivity extends AppCompatActivity implements OrdersAdapter.OnOrderSelection, LoginVerify.OnOrderListEnter, FragmentLogin.OnOrderListEnter, FragmentOrdersList.OnOrderDetails{

    private FragmentDetailedOrder fragmentDetailedOrder;
    private FragmentLogin fragmentLogin;
    private FragmentOrdersList fragmentOrdersList;
    private SharedPreferences sp;
    private AlertDialog dialogExit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentLogin = new FragmentLogin();
        if (getResources().getBoolean(R.bool.isTablet)) {
            getSupportFragmentManager().beginTransaction().add(R.id.activity_main_login, fragmentLogin, "FragmentLogin").commit();
        }
    }

    @Override
    public void onOrderSelected(Order order) {
        //String deliveryDate, String deliveryTime, String type)
        sp = PreferenceManager.getDefaultSharedPreferences(this);
        sp.edit().putString("name", order.getCustomerName()).commit();
        sp.edit().putString("address", order.getAddress()).commit();
        sp.edit().putString("phonenumber", order.getCustomerPhoneNumber()).commit();
        sp.edit().putInt("amount", order.getAmount()).commit();
        sp.edit().putLong("ordernumber", order.getOrderNumber()).commit();
        sp.edit().putString("type", order.getType()).commit();

        fragmentDetailedOrder = new FragmentDetailedOrder();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.activity_main_login,fragmentDetailedOrder, "FragmentDetailedOrder");
        transaction.addToBackStack(null);
//        fragmentDetailedOrder.passParams(order);
        // Commit the transaction
        transaction.commit();

    }

    @Override
    public void OrderListEntered() {
        fragmentOrdersList = new FragmentOrdersList();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.activity_main_login,fragmentOrdersList, "FragmentOrderList");
        transaction.addToBackStack(null);
        // Commit the transaction
        transaction.commit();
//        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//        transaction.add(R.id.fragment_orders_list, fragmentOrdersList, "FragmentOrderList");
//        transaction.addToBackStack(null);
//        transaction.commit();
    }

    @Override
    public void OrderDetailsEntered() {
        fragmentDetailedOrder = new FragmentDetailedOrder();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.activity_main_login,fragmentDetailedOrder, "FragmentOrderList");
        transaction.addToBackStack(null);
        transaction.commit();
    }

//    @Override
//    public void onBackPressed() {
//
//        //FragmentManager fm = getSupportFragmentManager();
//       // for (Fragment fragmentLogin : fm.getFragments()) {
//            if (fragmentLogin != null) {
//                super.onBackPressed();
//
//                Toast.makeText(this, "Exit via menu", Toast.LENGTH_SHORT).show();
//
//            }
//
//
//            }
    //}

    @Override
    public void onBackPressed(){
        sp = PreferenceManager.getDefaultSharedPreferences(this);
        FragmentLogin.onBackPressed();
        FragmentDetailedOrder.onBackpressed();
        if (fragmentLogin.isVisible()) {
            //AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
            // Setting Dialog Title
            //alertDialog.setTitle("Signout your app");
           // alertDialog.setCancelable(false);
            //alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
              //  public void onClick(DialogInterface dialog, int which) {
                //    dialog.cancel();
                //}
            //});
            //alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
              //  public void onClick(DialogInterface dialog, int which) {
                //    finish();
                //}
            //});
            //alertDialog.show();
        }else{
            super.onBackPressed();
//        } if (fragmentDetailedOrder.isVisible()){
//            if (sp.getString("delivered", "no").equals("yes")){

       //    }

        }


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.settings_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){

            case R.id.exit_app:
                dialogExit = new AlertDialog.Builder(this).
                        setMessage("Are you sure you want to exit?").
                        setTitle("Exit Application").
                        setPositiveButton("Yes", new DialogInterface.OnClickListener(){
                            public void onClick(DialogInterface dialog, int id){
                                finish();}
                        }).
                        setNegativeButton("No", new DialogInterface.OnClickListener(){
                            public void onClick(DialogInterface dialog, int id){
                                dialog.cancel();}
                        }).
                        setCancelable(false).
                        create();
                dialogExit.show();
                break;

            case R.id.settings:
                Intent in = new Intent(this, SettingsMenu.class);
                startActivity(in);
        }

        return super.onOptionsItemSelected(item);
    }


}
