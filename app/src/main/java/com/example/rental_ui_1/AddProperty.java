package com.example.rental_ui_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Toast;

public class AddProperty extends AppCompatActivity {

    private String name;
    RadioGroup propertyGroup;
    EditText bedroom,price,address,furnitureType,remark,repo_name;
    Button addProperty;
    String propertyType,bed,pri,add,fur,rem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_property);
        //nav item
        ImageView home,delete,logout;

        home=findViewById(R.id.home);
        delete=findViewById(R.id.idDelete);
        //variable

        Intent intent=getIntent();
        name=intent.getStringExtra("USER_NAME");

        addProperty=findViewById(R.id.idAddProperty);
        propertyGroup=findViewById(R.id.idPropertyGroup);
        bedroom=findViewById(R.id.idBedroom);
        price=findViewById(R.id.idPrice);
        address=findViewById(R.id.idAddress);
        furnitureType=findViewById(R.id.idFurnitureType);
        remark=findViewById(R.id.idRemark);
        repo_name=findViewById(R.id.idReporterName);
        repo_name.setFocusable(false);
        repo_name.setClickable(false);
        repo_name.setText(name.toString());

        int rdoId=propertyGroup.getCheckedRadioButtonId();
        if (rdoId != -1) {
            if(rdoId==R.id.a){
                propertyType="Apartment";
            }else if(rdoId==R.id.b){
                propertyType="Condos";
            }else if(rdoId==R.id.c){
                propertyType="Bungalow";
            }else if(rdoId==R.id.d){
                propertyType="Tiny House";
            }else if(rdoId==R.id.e){
                propertyType="Cabin";
            }else{
                propertyType="Tudor";
            }
        }

        addProperty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bed=bedroom.getText().toString();
                pri=price.getText().toString();
                add=address.getText().toString();
                fur=furnitureType.getText().toString();
                rem=remark.getText().toString();
                //check validation
                if(TextUtils.isEmpty(bed)||TextUtils.isEmpty(pri)||TextUtils.isEmpty(add)||TextUtils.isEmpty(fur)||TextUtils.isEmpty(rem)){
                    Toast.makeText(getApplicationContext(), "Please fill in all fields"+name, Toast.LENGTH_SHORT).show();
                    return;
                }

                //insert into database
                DBHelper dbhelper=new DBHelper(getApplicationContext());
                dbhelper.addProperty(propertyType,bed,pri,add,fur,rem,name);
                Toast.makeText(getApplicationContext(), "Insert SuccessFully", Toast.LENGTH_SHORT).show();

                //clear Text
                bedroom.setText("");price.setText("");address.setText("");furnitureType.setText("");remark.setText("");
            }
        });

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent t1=new Intent(AddProperty.this, PropertyView.class);
                t1.putExtra("USER_NAME",name);
                startActivity(t1);
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent t1=new Intent(AddProperty.this,PropertySearch.class);
                t1.putExtra("USER_NAME",name);
                startActivity(t1);
            }
        });
    }
}