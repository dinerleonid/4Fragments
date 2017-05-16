package com.example.leonwork.moveit3.layout;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.leonwork.moveit3.Order;
import com.example.leonwork.moveit3.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentDetailedOrder extends Fragment implements View.OnClickListener{

    private TextView  detailCustomerName, detailAddress, detailCustomerPhoneNumber, detailType, detailOrderAmount, detailOrderNumber;
    private EditText detailDeliverAmount;
    private SharedPreferences sp;
    private String deliveredAmount;
    private Button saveData;

    public FragmentDetailedOrder() {
        // Required empty public constructor
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_fragment_detailed_order, container, false);

        detailCustomerName = (TextView) v.findViewById(R.id.detailCustomerName);
        detailAddress = (TextView) v.findViewById(R.id.detailCustomerAddress);
        detailCustomerPhoneNumber = (TextView) v.findViewById(R.id.detailCustomerPhoneNumber);
        detailType = (TextView) v.findViewById(R.id.detailDeliveryType);
        detailOrderAmount = (TextView) v.findViewById(R.id.detailOrderAmount);
        detailOrderNumber = (TextView) v.findViewById(R.id.detailOrderNumber);
        detailDeliverAmount = (EditText) v.findViewById(R.id.detailDeliveredAmount);
        saveData = (Button) v.findViewById(R.id.btnSave);
        saveData.setOnClickListener(this);
        // Inflate the layout for this fragment
        setParams();

        return v;

    }
//public void setDetails(Order order){
    public void setParams(){
    sp = PreferenceManager.getDefaultSharedPreferences(getContext());
//        sp.edit().putInt("amount", order.getAmount()).commit();
//        sp.edit().putLong("ordernumber", order.getOrderNumber()).commit();
//        sp.edit().putString("type", order.getType()).commit();

        detailCustomerName.setText(sp.getString("name", "name"));
        detailAddress.setText(sp.getString("address", "address"));
        detailCustomerPhoneNumber.setText(sp.getString("phonenumber", "number"));
        detailOrderAmount.setText(String.valueOf(sp.getInt("amount", 111)));
        detailOrderNumber.setText(String.valueOf(sp.getLong("ordernumber", 0)));
        detailType.setText(sp.getString("type", "type"));

    }

    @Override
    public void onClick(View v) {
        deliveredAmount = detailDeliverAmount.getText().toString();
        Toast.makeText(getContext(), "The actual delivery is - " + deliveredAmount, Toast.LENGTH_SHORT).show();
        sp.edit().putBoolean("changecolor", true);

    }

    public static void onBackpressed(){


    }
}
