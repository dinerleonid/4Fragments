package com.example.leonwork.moveit3.layout;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.leonwork.moveit3.GrabOrdersData;
import com.example.leonwork.moveit3.OrdersAdapter;
import com.example.leonwork.moveit3.OrdersDBHelper;
import com.example.leonwork.moveit3.R;

import static android.R.attr.type;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentOrdersList extends Fragment implements View.OnClickListener{
    private TextView stam;
    private Button btnGoToorderDetails;


    private OrdersDBHelper helper;
    private OrdersAdapter adapter;
    private RecyclerView list = null;
    private OnOrderDetails listener;
    private ProgressBar progressBar;
    private TextView  customerName, address, customerPhoneNumber, deliveryDate, deliveryTime, type, amount, idInternal, orderNumber;
    private SharedPreferences sp;
    private TableLayout tblRow;
    //private long idInternal, orderNumber;
   // private int amount;


    public FragmentOrdersList() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        listener = (FragmentOrdersList.OnOrderDetails) context;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_fragment_orders_list, container, false);
        list = (RecyclerView) v.findViewById(R.id.orders_list);


        customerName = (TextView) v.findViewById(R.id.customerName);
        address = (TextView) v.findViewById(R.id.customerAddress);
        customerPhoneNumber = (TextView) v.findViewById(R.id.customerPhoneNumber);
        //deliveryDate = (TextView) v.findViewById(R.id.deliveryDate);
        deliveryTime = (TextView) v.findViewById(R.id.deliveryTime);
        type = (TextView) v.findViewById(R.id.deliveryType);
        amount = (TextView) v.findViewById(R.id.orderAmount);
        //orderNumber = (TextView) v.findViewById(R.id.orderNumber);

        list.setLayoutManager(new LinearLayoutManager(getContext()));
        helper = new OrdersDBHelper(getContext());
        adapter = new OrdersAdapter(getContext(), helper.getAllOrders());
        list.setAdapter(adapter);

        tblRow = (TableLayout) v.findViewById(R.id.table_row);
        Intent intent = new Intent(getContext(), GrabOrdersData.class);
        getContext().startService(intent);

        LocalBroadcastManager.getInstance(getActivity()).registerReceiver(new FinishedInsert(), new IntentFilter("finished"));
        LocalBroadcastManager.getInstance(getActivity()).registerReceiver(new NoInetConnection(), new IntentFilter("noconnection"));



       // stam = (TextView) v.findViewById(R.id.stam);
//        btnGoToorderDetails = (Button) v.findViewById(R.id.btnToDetails);
//        btnGoToorderDetails.setOnClickListener(this);
        // Inflate the layout for this fragment
      //  System.out.println("====================  i come from login");

        return v;
    }

    @Override
    public void onClick(View v) {
        tblRow.setBackgroundColor(Color.parseColor("#ff0000"));

        //listener.OrderDetailsEntered();
    }


    public interface OnOrderDetails {

        void OrderDetailsEntered();
    }


    public class FinishedInsert extends BroadcastReceiver {

        // receiving results from search
        @Override
        public void onReceive(Context context, Intent intent) {
            adapter.clearList();
            adapter.addToList(helper.getAllOrders());
            if (helper.getAllOrders().size() == 0){
                Toast.makeText(context, "No orders found", Toast.LENGTH_SHORT).show();
            }
  //          progressBar.setVisibility(View.INVISIBLE);
        }
    }

    public class NoInetConnection extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
          //  progressBar.setVisibility(View.INVISIBLE);

            Toast.makeText(context, "No Internet Connection", Toast.LENGTH_SHORT).show();

        }
    }


}
