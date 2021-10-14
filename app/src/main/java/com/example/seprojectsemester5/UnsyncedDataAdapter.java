package com.example.seprojectsemester5;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class UnsyncedDataAdapter extends RecyclerView.Adapter<UnsyncedDataAdapter.UnsyncedDataViewHolder> {

    String[] aadharNumber;
    String[] name;
    String[] age;
    String[] gender;
    String[] pin;
    String[] phone;

    public UnsyncedDataAdapter(String[] aadharNumber, String[] name, String[] age, String[] gender, String[] pin, String[] phone){
        this.aadharNumber = aadharNumber;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.pin = pin;
        this.phone = phone;
    }

    @NonNull
    @Override
    public UnsyncedDataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.survey_card, parent, false);
        return new UnsyncedDataViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UnsyncedDataViewHolder holder, int position) {
        holder.aadharNumber.setText(aadharNumber[position]);
        holder.name.setText(name[position]);
        holder.age.setText(age[position]);
        holder.pin.setText(pin[position]);
        holder.gender.setText(gender[position]);
        holder.phone.setText(phone[position]);
    }

    @Override
    public int getItemCount() {
        return aadharNumber.length;
    }

    public class UnsyncedDataViewHolder extends RecyclerView.ViewHolder{
        TextView aadharNumber;
        TextView name;
        TextView age;
        TextView pin;
        TextView gender;
        TextView phone;
       public UnsyncedDataViewHolder(@NonNull View itemView) {
           super(itemView);
           aadharNumber = (TextView) itemView.findViewById(R.id.id);
           name = (TextView) itemView.findViewById(R.id.name);
           age = (TextView) itemView.findViewById(R.id.age);
           pin = (TextView) itemView.findViewById(R.id.pin);
           gender = (TextView) itemView.findViewById(R.id.gender);
           phone = (TextView) itemView.findViewById(R.id.phone);
       }
   }
}
