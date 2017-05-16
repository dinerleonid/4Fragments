package com.example.leonwork.moveit3;

import android.os.Parcel;
import android.os.Parcelable;

public class Truck implements Parcelable{

    private int truckNumber ;
    private long idTruck;

    public Truck(Long idTruck, int truckNumber) {
        this.idTruck = idTruck;
        this.truckNumber = truckNumber;
    }


    public long getIdTruck() { return idTruck; }

    public void setIdTruck(long idTruck) { this.idTruck = idTruck; }

    public int getTruckNumber() { return truckNumber; }

    public void setTruckNumber(int truckNumber) { this.truckNumber = truckNumber; }


    protected Truck(Parcel in) {
        truckNumber = in.readInt();
        idTruck = in.readLong();
    }

    public static final Creator<Truck> CREATOR = new Creator<Truck>() {
        @Override
        public Truck createFromParcel(Parcel in) { return new Truck(in); }

        @Override
        public Truck[] newArray(int size) { return new Truck[size]; }
    };

    @Override
    public int describeContents() { return 0; }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(truckNumber);
        dest.writeLong(idTruck);
    }
}
