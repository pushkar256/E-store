package com.example.cbluser29.e_store.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import com.example.cbluser29.e_store.models.DBCategoryModel;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by pushkar on 13/2/17.
 */

/*
public String _name;
public String _description;
public int _id;
public int _pid;*/
public class MyDatabaseCategoryHelper extends SQLiteOpenHelper{

    private static final int DATABASE_VERSION=1;
    private static final String DATABASE_NAME="categorydatabase.db";
    private static final String TABLE_NAME="categorytable";
    private static final String UID="_id";
    private static final String NAME="Name";
    private static final String DESCRIPTION="Description";
    private static final String PARENT_ID="Pid";

    public static final String CREATE_TABLE="CREATE TABLE " + TABLE_NAME + "(" +
            UID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            NAME + " VARCHAR(255) , " +
            DESCRIPTION+ " VARCHAR(255) ,"      +
            PARENT_ID + " INTEGER );";

    public static final String DROP_TABLE="DROP TABLE IF"+TABLE_NAME;
    Context context;

    public MyDatabaseCategoryHelper(Context context) {
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

    public long insertRow(String name, int parentId,String description)
    {
        SQLiteDatabase dbWrite=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(PARENT_ID,parentId);
        contentValues.put(DESCRIPTION,description);
        contentValues.put(NAME,name);
        long id =dbWrite.insert(TABLE_NAME,null,contentValues);
        return id;

    }

    public List<DBCategoryModel> getCategorylList(int id1)
    {
        SQLiteDatabase dbRead=this.getReadableDatabase();
        final String SELECT_QUERY="SELECT * FROM "+TABLE_NAME+" WHERE "+PARENT_ID +"= "+id1;
        List<DBCategoryModel> modelList= new ArrayList<>();
        Cursor cursor=dbRead.rawQuery(SELECT_QUERY,null);
        while (cursor.moveToNext())
        {
            String name=cursor.getString(cursor.getColumnIndex(NAME));
            int id=cursor.getInt(cursor.getColumnIndex(UID));
            int pid=cursor.getInt(cursor.getColumnIndex(PARENT_ID));
            String description=cursor.getString(cursor.getColumnIndex(DESCRIPTION));
            DBCategoryModel dbCategoryModel =new DBCategoryModel(name,description,id,pid);
            modelList.add(dbCategoryModel);
        }
        cursor.close();
        return modelList;
    }

    public List<Object> getObjectCategorylList(int id1)
    {
        SQLiteDatabase dbRead=this.getReadableDatabase();
        final String SELECT_QUERY="SELECT * FROM "+TABLE_NAME+" WHERE "+PARENT_ID +"= "+id1;
        List<Object> modelList= new ArrayList<>();
        Cursor cursor=dbRead.rawQuery(SELECT_QUERY,null);
        while (cursor.moveToNext())
        {
            String name=cursor.getString(cursor.getColumnIndex(NAME));
            int id=cursor.getInt(cursor.getColumnIndex(UID));
            int pid=cursor.getInt(cursor.getColumnIndex(PARENT_ID));
            String description=cursor.getString(cursor.getColumnIndex(DESCRIPTION));
            Object dbCategoryModel =(Object) new DBCategoryModel(name,description,id,pid);
            modelList.add(dbCategoryModel);
        }
        cursor.close();
        return modelList;
    }
}
