package com.example.cbluser29.e_store.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import com.example.cbluser29.e_store.models.DBProductModel;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by pushkar on 13/2/17.
 */

public class MyDatabaseProductHelper extends SQLiteOpenHelper{

    private static final int DATABASE_VERSION=1;
    private static final String DATABASE_NAME="productdatabase.db";
    private static final String TABLE_NAME="producttable";
    private static final String UID="_id";
    private static final String NAME="Name";
    private static final String PRICE="Price";
    private static final String PARENT_ID="Pid";

    public static final String CREATE_TABLE="CREATE TABLE " + TABLE_NAME + "(" +
            UID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            NAME + " VARCHAR(255) , " +
            PRICE+ " INTEGER ,"      +
            PARENT_ID + " INTEGER );";

    public static final String DROP_TABLE="DROP TABLE IF"+TABLE_NAME;
    Context context;

    public MyDatabaseProductHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context=context;




    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL(CREATE_TABLE);
            Toast.makeText(context,"onCreate",Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            Log.d("TAG pushkar", e.getMessage() + " ");
//            Toast.makeText(this,"onCreate caught",Toast.LENGTH_LONG).show();
        }
    }


    public void onUpgrade(SQLiteDatabase db, int oldVersion,int newVersion) {

        try
        {
            db.execSQL(DROP_TABLE);
            onCreate(db);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public long insertRow(String name, int parentId ,int price)
    {
        SQLiteDatabase dbWrite=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(PARENT_ID,parentId);
        contentValues.put(PRICE,price);
        contentValues.put(NAME,name);
        long id =dbWrite.insert(TABLE_NAME,null,contentValues);
        if (id>0)
        {

        }
        return id;

    }

    public List<DBProductModel> getCategorylList(int id1)
    {
        SQLiteDatabase dbRead=this.getReadableDatabase();
        final String SELECT_QUERY="SELECT * FROM "+TABLE_NAME+" WHERE "+PARENT_ID +"= "+id1;
        List<DBProductModel> modelList= new ArrayList<>();
        Cursor cursor=dbRead.rawQuery(SELECT_QUERY,null);
        while (cursor.moveToNext())
        {
            String name=cursor.getString(cursor.getColumnIndex(NAME));
            int id=cursor.getInt(cursor.getColumnIndex(UID));
            int pid=cursor.getInt(cursor.getColumnIndex(PARENT_ID));
            int price=cursor.getInt(cursor.getColumnIndex(PRICE));
            DBProductModel dbProductModel =new DBProductModel(pid,id,name,price);

            Log.e("Product Details :",name+id+pid+price);
            modelList.add(dbProductModel);
        }
        cursor.close();
        return modelList;
    }
}
