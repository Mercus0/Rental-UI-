package com.example.rental_ui_1;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DeleteAdapter extends RecyclerView.Adapter<DeleteAdapter.ViewHolder> {
    ArrayList<PropertyModel> PropertyModelArrayList =new ArrayList<>();
    @NonNull
    @Override
    public DeleteAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull DeleteAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

     class ViewHolder extends RecyclerView.ViewHolder{
         TextView prop_type,bed_room,price,location,Furniture_type,re_mark,reporter_name,assign_date;
         public ViewHolder(@NonNull View itemView) {
             super(itemView);
             prop_type = (TextView) itemView.findViewById(R.id.idPropertyType);
             bed_room=(TextView) itemView.findViewById(R.id.idBedroomCount);
             price=(TextView) itemView.findViewById(R.id.idPrice);
             location=(TextView) itemView.findViewById(R.id.idLocation);
             Furniture_type=(TextView) itemView.findViewById(R.id.idFurniture);
             re_mark=(TextView) itemView.findViewById(R.id.idRemark);
             reporter_name=(TextView) itemView.findViewById(R.id.idOwner);
             assign_date=(TextView) itemView.findViewById(R.id.idAssignDate);
         }
     }
}
