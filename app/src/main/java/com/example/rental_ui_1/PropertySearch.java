package com.example.rental_ui_1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class PropertySearch extends AppCompatActivity {
    private String name;
    private PropertyAdapter propertyAdapter;
    private String refno;
    Button search, delete,update;
    TextView userName,refNo;
    RecyclerView recyclerView;
    ArrayList<PropertyModel> PropertySearchArrayList =new ArrayList<>();

    ImageView home,add,logout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property_search);

        Intent intent=getIntent();
        name=intent.getStringExtra("USER_NAME");

        home=findViewById(R.id.home);
        add=findViewById(R.id.add);
        logout=findViewById(R.id.idLogout);

        search=findViewById(R.id.idSearch);
        delete=findViewById(R.id.idBtnDelete);
        userName=findViewById(R.id.idUserName);
        refNo=findViewById(R.id.idReferenceNo);
        recyclerView=findViewById(R.id.recyclerView2);
        update=findViewById(R.id.idUpdate);
        userName.setText(name.toString());

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                refno=refNo.getText().toString();
                if(refno.equals("")){
                    Toast.makeText(getApplicationContext(), "Please Enter Reference Number", Toast.LENGTH_SHORT).show();
                }else {
                    Intent intent = new Intent(PropertySearch.this, PropertyUpdate.class);
                    intent.putExtra("USER_NAME", name);
                    intent.putExtra("REF_ID", refno);
                    startActivity(intent);
                }
            }
        });

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                refno=refNo.getText().toString();
                if(refno.equals("")){
                    Toast.makeText(getApplicationContext(), "Please Enter Reference Number", Toast.LENGTH_SHORT).show();
                }else {
                    DBHelper dbHelper = new DBHelper(getApplicationContext());
                    PropertySearchArrayList = dbHelper.searchProperty(name, refno);
                    if(PropertySearchArrayList.isEmpty()){
                        Toast.makeText(getApplicationContext(), "No Properties Found", Toast.LENGTH_SHORT).show();
                    }else {
                        RecyclerView recyclerView = findViewById(R.id.recyclerView2);
                        propertyAdapter = new PropertyAdapter(PropertySearchArrayList);
                        recyclerView.setLayoutManager(new LinearLayoutManager(PropertySearch.this, LinearLayoutManager.HORIZONTAL, false));
                        recyclerView.setAdapter(propertyAdapter);
                        propertyAdapter.notifyDataSetChanged();
                    }
                }
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                refno=refNo.getText().toString();
                if(!TextUtils.isEmpty(refno)) {
                    DBHelper dbHelper = new DBHelper(getApplicationContext());
                    dbHelper.deleteProperty(name, refno);
                    Toast.makeText(getApplicationContext(), "Delete Successfully", Toast.LENGTH_SHORT).show();
                    refNo.setText("");
                    PropertySearchArrayList.clear();
                    propertyAdapter.notifyDataSetChanged();
                }else Toast.makeText(getApplicationContext(), "Please Enter Reference Number", Toast.LENGTH_SHORT).show();
            }
        });

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(PropertySearch.this,PropertyView.class);
                intent.putExtra("USER_NAME",name);
                startActivity(intent);
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(PropertySearch.this,AddProperty.class);
                intent.putExtra("USER_NAME",name);
                startActivity(intent);
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(PropertySearch.this,LoginPage.class);
                startActivity(intent);
            }
        });
    }
}