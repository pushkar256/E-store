package com.example.cbluser29.e_store.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.cbluser29.e_store.R;
import com.example.cbluser29.e_store.database.MyDatabaseCategoryHelper;
import com.example.cbluser29.e_store.database.MyDatabaseProductHelper;
import com.example.cbluser29.e_store.models.DBCategoryModel;

import java.util.ArrayList;
import java.util.List;

public class AddProductActivity extends AppCompatActivity {


    EditText etName,etPrice;
    Button btnSubmit;
    List<DBCategoryModel> listCategory;
    List<DBCategoryModel> listSubCategory;
    int idCategory,idSubCategory;
    MyDatabaseCategoryHelper helper;
    Spinner spinnerSelectCategory,spinnerSelectSubCategory;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

        initViews();
        adapterSettingsWithSpinner();
        btnListeners();




    }

    private void btnListeners() {

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MyDatabaseProductHelper helper1=new MyDatabaseProductHelper(AddProductActivity.this);
                String name=etName.getText().toString().trim();
                int price=Integer.parseInt(etPrice.getText().toString().trim());

                Toast.makeText(AddProductActivity.this,name+price+idSubCategory,Toast.LENGTH_LONG).show();
                long id=helper1.insertRow(name,idSubCategory,price);
                if (id>0)
                {
                    Toast.makeText(AddProductActivity.this,"Sucesfully inserted",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void adapterSettingsWithSpinner() {
        helper=new MyDatabaseCategoryHelper(this);
        listCategory=helper.getCategorylList(0);

        ArrayAdapter<DBCategoryModel> adapterCategory=new ArrayAdapter<DBCategoryModel>(AddProductActivity.this,
                android.R.layout.simple_spinner_item,listCategory);
        spinnerSelectCategory.setAdapter(adapterCategory);

        spinnerSelectCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                idCategory=((DBCategoryModel)parent.getSelectedItem())._id;

                listSubCategory=helper.getCategorylList(idCategory);

                ArrayAdapter<DBCategoryModel> adapterSubCategory=new ArrayAdapter<DBCategoryModel>(AddProductActivity.this,
                        android.R.layout.simple_spinner_item,listSubCategory);
                spinnerSelectSubCategory.setAdapter(adapterSubCategory);
                spinnerSelectSubCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        idSubCategory=((DBCategoryModel)parent.getSelectedItem())._id;

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {



                    }
                });


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    private void initViews() {

        etName=(EditText)findViewById(R.id.et_addproductname);
        btnSubmit=(Button)findViewById(R.id.btn_addproductsubmit);
        etPrice=(EditText)findViewById(R.id.et_addproductprice);
        spinnerSelectCategory=(Spinner)findViewById(R.id.spin_addproductselectcategory);
        spinnerSelectSubCategory=(Spinner)findViewById(R.id.spin_addproductselectsubcategory);
        listCategory=new ArrayList<>();
        listSubCategory=new ArrayList<>();

    }
}
