package com.me.harris.sqlapp;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.me.harris.sqlapp.bean.Student;

import java.sql.Timestamp;

public class DBManager {

    public static final String TAG = DBManager.class.getSimpleName();

    public static final String TABLE_STUDENT = "table_one";
    public static final String COLUMN_NAME = "s_name";
    public static final String COLUMN_NICK = "s_nick";
    public static final String COLUMN_DATA = "s_data"; //二进制数据
    public static final String COLUMN_VCHAR ="s_vchar";//尝试不同的数据类型
    public static final String COLUMN_DOUBLE = "s_double"; //尝试double
    public static final String COLUMN_DATE_TIME = "s_date_time"; //DATE_TIME




    private DBOpenHelper mDbHelper;

    private static volatile DBManager INSTANCE;

    private SQLiteDatabase mDb;


    private DBManager(){
        init();
    }

    private void init() {
        mDbHelper = new DBOpenHelper(App.getApplicationContxt());
    }

    public static DBManager getInstance() {
        if (INSTANCE == null) {
            synchronized (DBManager.class) {
                if (INSTANCE == null) {
                    INSTANCE = new DBManager();
                }
            }
        }
        return INSTANCE;
    }

    private SQLiteDatabase getWriteAbleDatabaseInner() {
        mDb = mDbHelper.getWritableDatabase();
        return mDb;
    }

    public  void createTable(SQLiteDatabase db) {
        String create = "CREATE TABLE IF NOT EXISTS " + TABLE_STUDENT +
                " ( _id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_NAME + " TEXT,"
                + COLUMN_NICK + " TEXT,"
                +COLUMN_DATA+" BLOB,"
                +COLUMN_VCHAR+ " VCHAR,"
                +COLUMN_DOUBLE+" DOUBLE,"
                +COLUMN_DATE_TIME+ " DATETIME)";
//        create = "create table if not exists " + TABLE_STUDENT + " (Id integer primary key autoincrement, CustomName text, OrderPrice integer, Country text)";
        db.execSQL(create);
        Log.e(TAG, create);
    }

    public  void addStudent(String name, String nick) {
        SQLiteDatabase db = getWriteAbleDatabaseInner();
//        db.beginTransaction();
        byte[] blobData = new byte[4096];
        for (int i = 0; i < 4096; i++) {
            blobData[i] = 100;
        }
        double mDouble = 1976.5;
        String myChar = "hello char";



        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

//        db.execSQL("insert into " + TABLE_STUDENT + " (Id, CustomName, OrderPrice, Country) values (1, 'Arc', 100, 'China')");
        String insert = "INSERT OR IGNORE INTO "+TABLE_STUDENT+
                " ("+COLUMN_NAME+","+COLUMN_NICK+","+COLUMN_DATA+","+COLUMN_VCHAR+","+COLUMN_DOUBLE+","+COLUMN_DATE_TIME+") " +
                "VALUES (?,?,?,?,?,?)";
        Log.e(TAG, insert);
        db.execSQL(insert,new Object[]{name,nick,blobData,myChar,mDouble,timestamp});

//        db.setTransactionSuccessful();
    }

    public Student getStudent() {
        Student student = null;
        String sql = "SELECT * FROM " + TABLE_STUDENT + " ORDER BY _id asc";
        Cursor cursor = null;
        SQLiteDatabase db = getWriteAbleDatabaseInner();
        cursor = db.rawQuery(sql, null);
        while (cursor.moveToNext()) {
            String name = cursor.getString(cursor.getColumnIndex(COLUMN_NAME));
            String nick = cursor.getString(cursor.getColumnIndex(COLUMN_NICK));
            student = new Student();
            student.name = name;
            student.nick = nick;
        }
        cursor.close();
        return student;
    }

}
