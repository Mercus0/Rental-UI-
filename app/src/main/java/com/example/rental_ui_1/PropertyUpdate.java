package com.example.rental_ui_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class PropertyUpdate extends AppCompatActivity {
    private String name,refId;
    ArrayList<PropertyModel> PropertySearchArrayList =new ArrayList<>();
    EditText protype,bed,pri,loc,fur,rem;

    Button update;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property_update);

        Intent intent=getIntent();
        name=intent.getStringExtra("USER_NAME");
        refId=intent.getStringExtra("REF_ID");

        protype=findViewById(R.id.idPropertyType);
        bed=findViewById(R.id.idBedroom);
        pri=findViewById(R.id.idPrice);
        loc=findViewById(R.id.idLocation);
        fur=findViewById(R.id.idFurnitureType);
        rem=findViewById(R.id.idRemark);
        update=findViewById(R.id.idBtnUpdate);

        DBHelper dbHelper=new DBHelper(getApplicationContext());
        PropertySearchArrayList=dbHelper.searchProperty(name,refId);
        if(!PropertySearchArrayList.isEmpty()){
            PropertyModel model=PropertySearchArrayList.get(0);
            protype.setText(model.getPropertyType());
            bed.setText(model.getBedroom());
            pri.setText(model.getPrice());
            loc.setText(model.getLocation());
            fur.setText(model.getFurnitureType());
            rem.setText(model.getRemark());
        }

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHelper dbHelper1=new DBHelper(getApplicationContext());
                dbHelper1.updateProperty(refId,protype.getText().toString(),bed.getText().toString(),pri.getText().toString(),loc.getText().toString(),fur.getText().toString(),rem.getText().toString());
                Toast.makeText(getApplicationContext(),"Update Successfully",Toast.LENGTH_SHORT).show();
                PropertyAdapter property=new PropertyAdapter(PropertySearchArrayList);
                property.notifyDataSetChanged();
            }
        });
    }
}