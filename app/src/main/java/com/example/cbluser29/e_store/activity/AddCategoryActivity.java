package com.example.cbluser29.e_store.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.cbluser29.e_store.R;
import com.example.cbluser29.e_store.database.MyDatabaseCategoryHelper;
import com.example.cbluser29.e_store.models.DBCategoryModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cbluser29 on 15/2/17.
 */


public class AddCategoryActivity extends AppCompatActivity{


    EditText etAddCategoryTitle,etAddCategoryDescription;
    Spinner  spinnerAddCategorySelectCategory;
    Button btnAddCategorySUbmit;
    List<Object> listCategory=new ArrayList<>();
    int pid;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_category);

        initViews();
        adapterSettingsWithSpinner();
        btnListeners();



    }

    private void btnListeners() {
        btnAddCategorySUbmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String title=etAddCategoryTitle.getText().toString().trim();
                String description = etAddCategoryDescription.getText().toString().trim();
                MyDatabaseCategoryHelper helper=new MyDatabaseCategoryHelper(AddCategoryActivity.this);

                long id1=helper.insertRow(title,pid,description);
                if (id1>0)
                {
                    Toast.makeText(AddCategoryActivity.this,"Succesfuly Added ",Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private void adapterSettingsWithSpinner() {

        MyDatabaseCategoryHelper helper=new MyDatabaseCategoryHelper(this);
        listCategory=helper.getObjectCategorylList(0);
        listCategory.add(0,(Object) "-------select a category-------");
        ArrayAdapter<Object> arrayAdapter=new ArrayAdapter<Object>(this,
                R.layout.support_simple_spinner_dropdown_item,listCategory);
        spinnerAddCategorySelectCategory.setAdapter(arrayAdapter);
        spinnerAddCategorySelectCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (parent.getSelectedItem() instanceof String )
                {
                    pid=0;
                }
                else if (parent.getSelectedItem() instanceof DBCategoryModel)
                {
                    pid=((DBCategoryModel) parent.getSelectedItem())._id;

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    private void initViews() {
        etAddCategoryTitle=(EditText)findViewById(R.id.et_addcategoryname);
        etAddCategoryDescription=(EditText)findViewById(R.id.et_addcategorydescription);
        btnAddCategorySUbmit=(Button)findViewById(R.id.btn_addcategory);
        spinnerAddCategorySelectCategory=(Spinner)findViewById(R.id.spinnner_addcategoryselectcategory);

    }
}
