package com.example.leonwork.moveit3;

import android.os.Parcel;
import android.os.Parcelable;

public class Order implements Parcelable{

    private int amount ;
    private String customerName, address, customerPhoneNumber, deliveryDate, deliveryTime, type, productStatus;
    private long orderNumber, idInternal;


    public Order(long idInternal, long orderNumber, int amount, String customerName, String address, String customerPhoneNumber, String deliveryDate, String deliveryTime, String type, String productStatus) {
        this.idInternal = idInternal;
        this.orderNumber = orderNumber;
        this.amount = amount;
        this.customerName = customerName;
        this.address = address;
        this.customerPhoneNumber = customerPhoneNumber;
        this.deliveryDate = deliveryDate;
        this.deliveryTime = deliveryTime;
        this.type = type;
        this.productStatus = productStatus;
    }

    public long getIdInternal() { return idInternal; }

    public void setIdInternal(long idInternal) { this.idInternal = idInternal; }

    public long getOrderNumber() { return orderNumber; }

    public void setOrderNumber(int orderNumber) { this.orderNumber = orderNumber; }

    public int getAmount() { return amount; }

    public void setAmount(int amount) { this.amount = amount; }

    public String getCustomerName() { return customerName; }

    public void setCustomerName(String customerName) { this.customerName = customerName; }

    public String getAddress() { return address; }

    public void setAddress(String address) { this.address = address; }

    public String getCustomerPhoneNumber() { return customerPhoneNumber; }

    public void setCustomerPhoneNumber(String customerPhoneNumber) { this.customerPhoneNumber = customerPhoneNumber; }

    public String getDeliveryDate() { return deliveryDate; }

    public void setDeliveryDate(String deliveryDate) { this.deliveryDate = deliveryDate; }

    public String getDeliveryTime() { return deliveryTime; }

    public void setDeliveryTime(String deliveryTime) { this.deliveryTime = deliveryTime; }

    public String getType() { return type; }

    public void setType(String type) { this.type = type; }

    public String getProductStatus() { return productStatus; }

    public void setProductStatus(String productStatus) { this.productStatus = productStatus; }

    //public static Creator<Order> getCREATOR() { return CREATOR; }

    protected Order(Parcel in) {
        idInternal = in.readLong();
        orderNumber = in.readLong();
        amount = in.readInt();
        customerName = in.readString();
        address = in.readString();
        customerPhoneNumber = in.readString();
        deliveryDate = in.readString();
        deliveryTime = in.readString();
        type = in.readString();
        productStatus = in.readString();
    }

    public static final Creator<Order> CREATOR = new Creator<Order>() {
        @Override
        public Order createFromParcel(Parcel in) {
            return new Order(in);
        }

        @Override
        public Order[] newArray(int size) {
            return new Order[size];
        }
    };

    @Override
    public int describeContents() { return 0; }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(idInternal);
        dest.writeLong(orderNumber);
        dest.writeInt(amount);
        dest.writeString(customerName);
        dest.writeString(address);
        dest.writeString(customerPhoneNumber);
        dest.writeString(deliveryDate);
        dest.writeString(deliveryTime);
        dest.writeString(type);
        dest.writeString(productStatus);
    }
}
