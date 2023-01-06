package com.example.umpoolride;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String Wallet = "Wallet.db";
    public static final String Transactiontable = "transaction_table";
    public static final String T1Col1 = "ID";
    public static final String T1Col2 = "username";
    public static final String T1Col3 = "TypeofTransaction";
    public static final String T1Col4 = "Amount";
    public static final String T1Col5 = "DateTime";

    public DatabaseHelper(MainActivity context) {
        super(context, Wallet, null, 1);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + Transactiontable + " (ID INTEGER PRIMARY KEY AUTOINCREMENT,username TEXT,TypeofTransaction TEXT,Amount REAL,DateTime TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+Wallet);
        onCreate(db);
    }

    public boolean insertData(String username,String type,double amount,String datetime){
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

    public Cursor SumAmount(String username){

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT SUM(Amount) FROM "+Transactiontable+
                " WHERE username = \""+ username+"\"", null);
        return res;
    }

    public Cursor getHist(String username){

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM "+Transactiontable+
                " WHERE username = \""+ username+"\"", null);
        return res;
    }

}
