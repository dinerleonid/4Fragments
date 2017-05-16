package com.example.leonwork.moveit3;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TextView;

import java.util.ArrayList;


public class OrdersAdapter extends RecyclerView.Adapter<OrdersAdapter.OrdersHolder>{
    private Context context;
    private ArrayList<Order> orders = new ArrayList<>();
    private OnOrderSelection listener;
    private SharedPreferences sp;
    private Long itemId;
    private TableLayout tblRow;
    private int selected_position = -1;

    public OrdersAdapter(Context context, ArrayList<Order> orders) {
        this.context = context;
        this.orders = orders;
        this.listener = (OnOrderSelection) context;
    }

    public void addToList(ArrayList<Order>orders){
        this.orders.addAll(orders);
        notifyDataSetChanged();
    }

    public void clearList(){
        orders.clear();
        notifyDataSetChanged();
    }



    @Override
    public OrdersHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.activity_orders_list, parent, false);
        OrdersHolder ordersHolder = new OrdersHolder(v, context, orders);

        return new OrdersHolder(v, context, orders);
    }

    @Override
    public void onBindViewHolder(final OrdersHolder holder, final int position) {
        final Order order = orders.get(position);
        holder.bind(order);
       // Item item = items.get(position);
        if(selected_position == position){
            // Here I am just highlighting the background
            holder.itemView.setBackgroundColor(Color.parseColor("#529999"));
        }else{
            holder.itemView.setBackgroundColor(Color.TRANSPARENT);
            order.setProductStatus("0");
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (order.getProductStatus().equals("0")) {
                    order.setProductStatus("1");
                    // Updating old as well as new positions
                    notifyItemChanged(selected_position);
                    selected_position = position;
                    notifyItemChanged(selected_position);
                }else{
                    if (order.getProductStatus().equals("1")){
                        listener.onOrderSelected(order);
                    }
                }
                // Do your another stuff for your onClick
            }
        });
    }

    @Override
    public int getItemCount() {
        return orders.size();
    }

    public class OrdersHolder extends RecyclerView.ViewHolder{//} implements View.OnClickListener{
        private TextView customerName, customerAdress, customerPhoneNumber, deliveryDate, deliveryTime, orderType, orderAmount, orderNumber;
        private Order order;
        //private Long orderNumber;
        //private int orderAmount;
        ArrayList<Order> orders = new ArrayList<Order>();
        Context context;


        public OrdersHolder(View orderView, Context context, ArrayList<Order> orders) {
            super(orderView);
            this.orders = orders;
            this.context = context;
            customerName = (TextView) orderView.findViewById(R.id.customerName);
            customerAdress = (TextView) orderView.findViewById(R.id.customerAddress);
            customerPhoneNumber = (TextView) orderView.findViewById(R.id.customerPhoneNumber);
            //deliveryDate = (TextView) orderView.findViewById(R.id.deliveryDate);
            deliveryTime = (TextView) orderView.findViewById(R.id.deliveryTime);
            orderType = (TextView) orderView.findViewById(R.id.deliveryType);
            //orderNumber = (TextView) orderView.findViewById(R.id.orderNumber);
            orderAmount = (TextView) orderView.findViewById(R.id.orderAmount);
            tblRow = (TableLayout) orderView.findViewById(R.id.table_row);
           // orderView.setOnClickListener(this);
        }

        public void bind(Order order){
            this.order = order;
            customerName.setText(order.getCustomerName());
            customerAdress.setText(order.getAddress());
            customerPhoneNumber.setText(order.getCustomerPhoneNumber());
//            deliveryDate.setText(order.getDeliveryDate());
            deliveryTime.setText(order.getDeliveryTime());
            orderType.setText(order.getType());
//            orderNumber.setText(String.valueOf(order.getOrderNumber()));
            orderAmount.setText(String.valueOf(order.getAmount()));
        }

//        @Override
//        public void onClick(View v) {
//            sp = PreferenceManager.getDefaultSharedPreferences(context);
//            sp.edit().putString("name", order.getCustomerName()).commit();
//            sp.edit().putString("address", order.getAddress()).commit();
//            sp.edit().putString("phonenumber", order.getCustomerPhoneNumber()).commit();
//        }
    }

    public interface OnOrderSelection{
        void onOrderSelected(Order order);
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}






























