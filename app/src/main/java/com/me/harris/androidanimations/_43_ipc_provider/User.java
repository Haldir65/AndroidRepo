package com.me.harris.androidanimations._43_ipc_provider;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Harris on 2018/3/23.
 */

public class User implements Parcelable{

    public int userId;
    public String userName;
    public boolean isMale;
    public Book book;

    public User() {
    }

    protected User(Parcel in) {
        userId = in.readInt();
        userName = in.readString();
        isMale = in.readByte() != 0;
        book = in.readParcelable(Book.class.getClassLoader());
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(userId);
        dest.writeString(userName);
        dest.writeByte((byte) (isMale ? 1 : 0));
        dest.writeParcelable(book, flags);
    }

    @Override
    public String toString() {
        return String.format(
                "[userId:%s, userName:%s, isMale:%s, book:{%s}]",
                userId, userName, isMale, book);
    }
}
