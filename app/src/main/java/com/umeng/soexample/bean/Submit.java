package com.umeng.soexample.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by W on 2019/1/15 20:31.
 */

public class Submit implements Parcelable {


    private int commodityId;
    private int amount;
    private String commodityName;
    private int count;
    private String pic;
    private int price;

    public Submit(int commodityId, int amount, String commodityName, int count, String pic, int price) {
        this.commodityId = commodityId;
        this.amount = amount;
        this.commodityName = commodityName;
        this.count = count;
        this.pic = pic;
        this.price = price;
    }

    public int getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(int commodityId) {
        this.commodityId = commodityId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getCommodityName() {
        return commodityName;
    }

    public void setCommodityName(String commodityName) {
        this.commodityName = commodityName;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public static Creator<Submit> getCREATOR() {
        return CREATOR;
    }

    protected Submit(Parcel in) {
        commodityId = in.readInt();
        amount = in.readInt();
        commodityName = in.readString();
        count = in.readInt();
        pic = in.readString();
        price = in.readInt();
    }

    public static final Creator<Submit> CREATOR = new Creator<Submit>() {
        @Override
        public Submit createFromParcel(Parcel in) {
            return new Submit(in);
        }

        @Override
        public Submit[] newArray(int size) {
            return new Submit[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }


    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(commodityId);
        dest.writeInt(amount);
        dest.writeString(commodityName);
        dest.writeInt(count);
        dest.writeString(pic);
        dest.writeInt(price);
    }
    @Override
    public String toString() {
        return "{" +
                "commodityId"+":"+ commodityId +
                ", amount"+":" + amount +
                '}';
    }
}
