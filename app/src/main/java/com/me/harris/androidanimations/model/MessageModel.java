package com.me.harris.androidanimations.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Harris on 2017/6/18.
 */

public class MessageModel implements Parcelable{
    long id;
    String content;
    String extra;

    public MessageModel(){}

    protected MessageModel(Parcel in) {
        id = in.readLong();
        content = in.readString();
        extra = in.readString();
    }

    public static final Creator<MessageModel> CREATOR = new Creator<MessageModel>() {
        @Override
        public MessageModel createFromParcel(Parcel in) {
            return new MessageModel(in);
        }

        @Override
        public MessageModel[] newArray(int size) {
            return new MessageModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(id);
        parcel.writeString(content);
        parcel.writeString(extra);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
