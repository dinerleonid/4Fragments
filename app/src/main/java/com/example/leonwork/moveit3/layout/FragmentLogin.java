package com.example.leonwork.moveit3.layout;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.leonwork.moveit3.LoginVerify;
import com.example.leonwork.moveit3.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentLogin extends Fragment implements View.OnClickListener{

    private Button btnLogin;
    private TextView truckNumner, truckPassword;
    private EditText loginNumber, loginPassword;
    private OnOrderListEnter listener;

    public FragmentLogin() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        listener = (OnOrderListEnter) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_fragment_login, container, false);

        btnLogin = (Button) v.findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(this);

        truckNumner = (TextView) v.findViewById(R.id.textView_truck_num);
        loginNumber = (EditText) v.findViewById(R.id.editText_truck_num);
        loginNumber.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                v.setFocusable(true);
                v.setFocusableInTouchMode(true);
                return false;
            }
        });

        truckPassword = (TextView) v.findViewById(R.id.textView_password);
        loginPassword = (EditText) v.findViewById(R.id.editText_password);
        loginPassword.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                v.setFocusable(true);
                v.setFocusableInTouchMode(true);
                return false;
            }
        });

        return v;
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(getContext(), LoginVerify.class);
        intent.putExtra("truck_number", loginNumber.getText().toString());
        intent.putExtra("password", loginPassword.getText().toString());
        getContext().startService(intent);


        listener.OrderListEntered();

    }

    public interface OnOrderListEnter {
        void OrderListEntered();

    }

    public static void onBackPressed(){

    }






}
