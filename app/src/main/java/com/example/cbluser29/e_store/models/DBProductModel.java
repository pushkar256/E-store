package com.example.cbluser29.e_store.models;

/**
 * Created by pushkar on 13/2/17.
 */

public class DBProductModel {

    public String _name;
    public int _id;
    public int _pid;
    public int _price;

    public DBProductModel(int _pid, int _id, String _name, int _price) {
        this._pid = _pid;
        this._id = _id;
        this._name = _name;
        this._price = _price;
    }
}
