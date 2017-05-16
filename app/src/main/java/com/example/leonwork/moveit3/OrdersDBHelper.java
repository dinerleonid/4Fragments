package com.example.leonwork.moveit3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;


public class OrdersDBHelper extends SQLiteOpenHelper{

  //  (int orderNumber, int amount, String customerName, String address, String customerPhoneNumber, String deliveryDate, String deliveryTime, String type

    private static final String ORDERS_TABLE_TITLE = "ordersTable";
    private static final String ORDERS_ID = "idInternal";
    private static final String ORDERS_NUMBER = "orderNumber";
    private static final String ORDERS_AMOUNT = "orderAmount";
    private static final String ORDERS_CUSTOMER_NAME = "customerName";
    private static final String ORDERS_CUSTOMER_ADDRESS = "customerAddress";
    private static final String ORDERS_CUSTOMER_PHONE_NUMBER = "customerPhoneNumber";
    private static final String ORDERS_DELIVERY_DATE = "deliveryDate";
    private static final String ORDERS_DELIVERY_TIME = "deliveryTime";
    private static final String ORDERS_PRODUCT_TYPE = "productType";
    private static final String ORDERS_PRODUCT_STATUS = "productStatus";

    private static final String TRUCKS_TABLE_TITLE = "trucksTable";
    private static final String TRUCK_ID = "idTruck";
    private static final String TRUCK_NUMBER = "truckNumber";


    public OrdersDBHelper(Context context) { super(context, "importedOrdersFromServer.db", null, 1); }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String sql = String.format(
                "CREATE TABLE %s " +
                "( %s INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "%s TEXT, " +
                "%s TEXT, " +
                "%s TEXT, " +
                "%s TEXT, " +
                "%s TEXT, " +
                "%s TEXT, " +
                "%s TEXT, " +
                "%s TEXT, " +
                "%s TEXT)",
                ORDERS_TABLE_TITLE,
                ORDERS_ID,
                ORDERS_NUMBER,
                ORDERS_AMOUNT,
                ORDERS_CUSTOMER_NAME,
                ORDERS_CUSTOMER_ADDRESS,
                ORDERS_CUSTOMER_PHONE_NUMBER,
                ORDERS_DELIVERY_DATE,
                ORDERS_DELIVERY_TIME,
                ORDERS_PRODUCT_TYPE,
                ORDERS_PRODUCT_STATUS);
        sqLiteDatabase.execSQL(sql);

        String sqlTrucks = String.format(
                "CREATE TABLE %s " +
                "( %s INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "%s TEXT )",
                TRUCKS_TABLE_TITLE,
                TRUCK_ID,
                TRUCK_NUMBER);
        sqLiteDatabase.execSQL(sqlTrucks);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }


    public long insertTruck(Truck truck){
        ContentValues values = new ContentValues();
        values.put(TRUCK_NUMBER, truck.getTruckNumber());
        SQLiteDatabase db = getWritableDatabase();
        long newID = db.insert(TRUCKS_TABLE_TITLE, null, values);
        db.close();
        return newID;
    }

    public long insertOrder(Order order){
        ContentValues values = new ContentValues();
        //values.put(ORDERS_ID, order.getIdInternal());
        values.put(ORDERS_NUMBER, order.getOrderNumber());
        values.put(ORDERS_AMOUNT, order.getAmount());
        values.put(ORDERS_CUSTOMER_NAME, order.getCustomerName());
        values.put(ORDERS_CUSTOMER_ADDRESS, order.getAddress());
        values.put(ORDERS_CUSTOMER_PHONE_NUMBER, order.getCustomerPhoneNumber());
        values.put(ORDERS_DELIVERY_DATE, order.getDeliveryDate());
        values.put(ORDERS_DELIVERY_TIME, order.getDeliveryTime());
        values.put(ORDERS_PRODUCT_TYPE, order.getType());
        values.put(ORDERS_PRODUCT_STATUS, order.getProductStatus());
        SQLiteDatabase db = getWritableDatabase();
        long newID = db.insert(ORDERS_TABLE_TITLE, null, values);
        db.close();
        return newID;
    }


    public void insertTrucksList(List<Truck> trucks){
        SQLiteDatabase db = getWritableDatabase();
        for (int i = 0; i < trucks.size() ; i++) {
            ContentValues values = new ContentValues();
            values.put(TRUCK_NUMBER, trucks.get(i).getTruckNumber());
            db.insert(TRUCKS_TABLE_TITLE, null, values);
        }
        db.close();
    }



        public void insertOrdersList(List<Order> order){
        SQLiteDatabase db = getWritableDatabase();
        for (int i = 0; i < order.size() ; i++){
            ContentValues values = new ContentValues();
           // values.put(ORDERS_ID, order.get(i).getIdInternal());
            values.put(ORDERS_NUMBER, order.get(i).getOrderNumber());
            values.put(ORDERS_AMOUNT, order.get(i).getAmount());
            values.put(ORDERS_CUSTOMER_NAME, order.get(i).getCustomerName());
            values.put(ORDERS_CUSTOMER_ADDRESS, order.get(i).getAddress());
            values.put(ORDERS_CUSTOMER_PHONE_NUMBER, order.get(i).getCustomerPhoneNumber());
            values.put(ORDERS_DELIVERY_DATE, order.get(i).getDeliveryDate());
            values.put(ORDERS_DELIVERY_TIME, order.get(i).getDeliveryTime());
            values.put(ORDERS_PRODUCT_TYPE, order.get(i).getType());
            values.put(ORDERS_PRODUCT_STATUS, order.get(i).getProductStatus());
            db.insert(ORDERS_TABLE_TITLE, null, values);
        }
        db.close();
    }


    public ArrayList<Truck> getAllTrucks() {
        ArrayList<Truck> trucks = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(TRUCKS_TABLE_TITLE, null, null, null, null, null, TRUCK_NUMBER);
        while (cursor.moveToNext()) {
            Long idTruck = cursor.getLong(cursor.getColumnIndex(TRUCK_ID));
            int truckNumber = cursor.getInt(cursor.getColumnIndex(TRUCK_NUMBER));
            trucks.add(new Truck(idTruck, truckNumber));
        }
        db.close();
        return trucks;
    }
    public ArrayList<Order> getAllOrders(){
        ArrayList<Order> orders = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(ORDERS_TABLE_TITLE, null, null, null, null, null, ORDERS_DELIVERY_TIME);
        while (cursor.moveToNext()){
            Long idInternal = cursor.getLong(cursor.getColumnIndex(ORDERS_ID));
            Long orderNumber = cursor.getLong(cursor.getColumnIndex(ORDERS_NUMBER));
            int orderAmount = cursor.getInt(cursor.getColumnIndex(ORDERS_AMOUNT));
            String customerName = cursor.getString(cursor.getColumnIndex(ORDERS_CUSTOMER_NAME));
            String customerAddress = cursor.getString(cursor.getColumnIndex(ORDERS_CUSTOMER_ADDRESS));
            String customerPhoneNumber = cursor.getString(cursor.getColumnIndex(ORDERS_CUSTOMER_PHONE_NUMBER));
            String deliveryDate = cursor.getString(cursor.getColumnIndex(ORDERS_DELIVERY_DATE));
            String deliveryTime = cursor.getString(cursor.getColumnIndex(ORDERS_DELIVERY_TIME));
            String productType = cursor.getString(cursor.getColumnIndex(ORDERS_PRODUCT_TYPE));
            String productStatus = cursor.getString(cursor.getColumnIndexOrThrow(ORDERS_PRODUCT_STATUS));
            orders.add(new Order(idInternal, orderNumber, orderAmount, customerName, customerAddress, customerPhoneNumber, deliveryDate, deliveryTime, productType, productStatus));
        }
        db.close();
        return orders;
    }

    public void deleteAllOrders(){
        SQLiteDatabase db = getWritableDatabase();
        db.delete(ORDERS_TABLE_TITLE, null, null);
        db.close();
    }
}
