package com.example.rental_ui_1;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class PropertyAdapter extends RecyclerView.Adapter<PropertyAdapter.PropertyViewHolder> {
    private List<PropertyModel> propertyList;
    public PropertyAdapter(ArrayList<PropertyModel> propertyList) {
        this.propertyList=propertyList;
    }

    @NonNull
    @Override
    public PropertyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.property_item,parent,false);
        PropertyViewHolder holder = new PropertyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull PropertyViewHolder holder, int position) {
        PropertyModel property=propertyList.get(position);
        holder.ref_no.setText(String.valueOf(propertyList.get(position).getRefId()));
        holder.prop_type.setText(String.valueOf(propertyList.get(position).getPropertyType()));
        holder.bed_room.setText(String.valueOf(propertyList.get(position).getBedroom()));
        holder.price.setText(String.valueOf(propertyList.get(position).getPrice()));
        holder.location.setText(String.valueOf(propertyList.get(position).getLocation()));
        holder.Furniture_type.setText(String.valueOf(propertyList.get(position).getFurnitureType()));
        holder.re_mark.setText(String.valueOf(propertyList.get(position).getRemark()));
        holder.reporter_name.setText(String.valueOf(propertyList.get(position).getReporterName()));
        holder.assign_date.setText(String.valueOf(propertyList.get(position).getAssignDate()));
    }

    @Override
    public int getItemCount() {
        return propertyList.size();
    }

    public static class PropertyViewHolder extends RecyclerView.ViewHolder{

        TextView ref_no,prop_type,bed_room,price,location,Furniture_type,re_mark,reporter_name,assign_date;

        public PropertyViewHolder(@NonNull View itemView) {
            super(itemView);
            ref_no=(TextView) itemView.findViewById(R.id.idRefNo);
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
