package com.example.cbluser29.e_store.models;

import java.io.Serializable;

/**
 * Created by pushkar on 13/2/17.
 */

public class DBCategoryModel implements Serializable{

    public String _name;
    public String _description;
    public int _id;
    public int _pid;


    public DBCategoryModel(String _name, String _description, int _id, int _pid) {
        this._name = _name;
        this._description = _description;
        this._id = _id;
        this._pid = _pid;
    }

    @Override
    public String toString() {
        return _name;
    }
}
