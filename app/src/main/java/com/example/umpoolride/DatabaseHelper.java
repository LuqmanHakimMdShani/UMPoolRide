package com.example.umpoolride;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String UMPOOL = "UMPool.db";
    public static final String Transactiontable = "transaction_table";
    public static final String T1Col1 = "ID";
    public static final String T1Col2 = "Email";
    public static final String T1Col3 = "TypeofTransaction";
    public static final String T1Col4 = "Amount";
    public static final String T1Col5 = "DateTime";

    public static final String Notificationtable = "notification_table";
    public static final String T2Col1 = "ID";
    public static final String T2Col2 = "Email";
    public static final String T2Col3 = "NotificationTitle";
    public static final String T2Col4 = "NotificationDescription";
    public static final String T2Col5 = "DateTime";

    public static final String Usertable = "user_table";
    public static final String T3Col1 = "Email";
    public static final String T3Col2 = "FullName";
    public static final String T3Col3 = "Password";
    public static final String T3Col4 = "TypeofUser";
    public static final String T3Col5 = "MatricNumber";

    public DatabaseHelper(Context context) {
        super(context, UMPOOL, null, 1);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + Transactiontable + " (ID INTEGER PRIMARY KEY AUTOINCREMENT,Email TEXT,TypeofTransaction TEXT,Amount REAL,DateTime TEXT)");
        db.execSQL("CREATE TABLE " + Notificationtable + " (ID INTEGER PRIMARY KEY AUTOINCREMENT,Email TEXT,NotificationTitle TEXT,NotificationDescription TEXT,DateTime TEXT)");
        db.execSQL("CREATE TABLE " + Usertable + " (Email PRIMARY KEY, FullName TEXT,Password TEXT,TypeofUser TEXT DEFAULT \"User\" NOT NULL,MatricNumber TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+Transactiontable);
        db.execSQL("DROP TABLE IF EXISTS "+Notificationtable);
        db.execSQL("DROP TABLE IF EXISTS "+Usertable);
        onCreate(db);
    }

    public boolean insertUser(String Fullname,String email,String password,String Nomatric){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(T3Col2,Fullname);
        contentValues.put(T3Col1,email);
        contentValues.put(T3Col3,password);
        contentValues.put(T3Col5,Nomatric);
        long result = db.insert(Usertable,null,contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }

    public boolean insertTopup(String username,String type,double amount,String datetime){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(T1Col2,username);
        contentValues.put(T1Col3,type);
        contentValues.put(T1Col4,amount);
        contentValues.put(T1Col5,datetime);
        long result = db.insert(Transactiontable,null,contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }

    public boolean insertNoti(String username,String NotiTitle,String NotiDesc,String DateTime){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(T2Col2,username);
        contentValues.put(T2Col3,NotiTitle);
        contentValues.put(T2Col4,NotiDesc);
        contentValues.put(T2Col5,DateTime);
        long result = db.insert(Notificationtable,null,contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }

    public Boolean checkemailpassword(String email, String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from "+Usertable+" where Email = ? and Password = ?", new String[] {email,password});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }

    public Cursor SumAmount(){

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT SUM(Amount) FROM "+Transactiontable+
                " WHERE Email = \""+ Credentials.USERNAME+"\"", null);
        return res;

    }

    public Cursor getHist(){

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM "+Transactiontable+
                " WHERE Email = \""+ Credentials.USERNAME+"\"", null);
        return res;

    }

}
