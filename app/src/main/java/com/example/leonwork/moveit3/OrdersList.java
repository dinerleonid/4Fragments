//package com.example.leonwork.moveit3;
//
//
//import android.os.Bundle;
//import android.support.v4.app.Fragment;
//import android.support.v7.widget.LinearLayoutManager;
//import android.support.v7.widget.RecyclerView;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//
//import java.util.ArrayList;
//
//
//public class OrdersList extends Fragment {
//
//
//    public OrdersList() {
//        // Required empty public constructor
//    }
//
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//
//        View v = inflater.inflate(R.layout.fragment_orders_list, container, false);
//
//        ArrayList<Order> orders = new ArrayList<>();
//
//        //int orderNumber, int amount, String customerName, String address, String phoneNumber, String deliveryDate, String deliveryTime, String type
//        // TODO "orders.add(all));
//        // Inflate the layout for this fragment
//        OrdersAdapter adapter = new OrdersAdapter(getActivity(), orders);
//        RecyclerView list = (RecyclerView) v.findViewById(R.id.list);
//        list.setLayoutManager(new LinearLayoutManager(getActivity()));
//        list.setAdapter(adapter);
//        return v;
//    }
//
//}
