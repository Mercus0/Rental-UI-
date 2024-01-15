package com.example.rental_ui_1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.text.ParseException;
import java.util.ArrayList;

public class PropertyView extends AppCompatActivity {
    private String name;
    private PropertyAdapter propertyAdapter;
    private ArrayList<PropertyModel> propertyList;
    ImageView addProperty,delete,logout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property_view);

            Intent intent = getIntent();
            name = intent.getStringExtra("USER_NAME");

        DBHelper dbHelper=new DBHelper(getApplicationContext());
        try {
            propertyList=dbHelper.readProperty();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        RecyclerView recyclerView =findViewById(R.id.idRecycleView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        propertyAdapter=new PropertyAdapter(propertyList);
        recyclerView.setAdapter(propertyAdapter);
        propertyAdapter.notifyDataSetChanged();

        addProperty=findViewById(R.id.add);
        delete=findViewById(R.id.idDelete);
        logout=findViewById(R.id.idLogout);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(PropertyView.this,LoginPage.class);
                startActivity(intent);
            }
        });

        addProperty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "" +name , Toast.LENGTH_LONG).show();
                Intent t1=new Intent(PropertyView.this, AddProperty.class);
                t1.putExtra("USER_NAME",name);
                startActivity(t1);
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent t1=new Intent(PropertyView.this, PropertySearch.class);
                t1.putExtra("USER_NAME",name);
                startActivity(t1);
            }
        });
    }
}