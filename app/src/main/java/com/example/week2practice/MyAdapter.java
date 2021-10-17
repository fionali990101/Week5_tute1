package com.example.week2practice;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyHolder> {
    ArrayList<Country> countries; //= Country.getCountries();
    ArrayList<Country> filterCountries;


    //这个一定要加的，这样的话就可以有不同的公文包来传值
    public MyAdapter(ArrayList<Country> countries) {
        this.countries = countries;
        this.filterCountries = countries;

    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item1, parent, false);
        MyHolder myHolder = new MyHolder(v); //把这个item1放进myHolder里面
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = myHolder.getAdapterPosition();
                Country country = filterCountries.get(position);
                Intent intent = new Intent(v.getContext(), CountryActivity.class);
                intent.putExtra("CountryName", country.getCountry());
                v.getContext().startActivity(intent);
            }
        });
        return myHolder;//注意这个不是return null， 是return myholder
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        holder.countryName.setText(filterCountries.get(position).getCountry());
        holder.new_Cases.setText(String.valueOf(filterCountries.get(position).getNewConfirmed()));
        holder.total_Cases.setText(String.valueOf(filterCountries.get(position).getTotalConfirmed()));

    }

    @Override
    public int getItemCount() {
        return filterCountries.size();
    }

    public Filter getFilter(){
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()){
                    filterCountries = countries;
                } else {
                    ArrayList<Country> filteredList = new ArrayList<>();
                    for (Country c: countries){
                        if (c.getCountry().toLowerCase().contains(charString.toLowerCase()))
                            filteredList.add(c);
                    }
                    filterCountries = filteredList;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = filterCountries;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                filterCountries = (ArrayList<Country>) filterResults.values; //////
                notifyDataSetChanged();//
            }
        };
    }


    public class MyHolder extends RecyclerView.ViewHolder {
        //每一个item里面有什么attribute
        ImageView imageView;
        TextView new_Cases;
        TextView total_Cases;
        TextView countryName;

        public MyHolder(@NonNull View v) {
            super(v);
            //把item1里面的attribute赋予值到这个MyHolder里面去
            imageView = v.findViewById(R.id.imageView3);
            new_Cases = v.findViewById(R.id.new_Cases);
            total_Cases = v.findViewById(R.id.total_Cases);
            countryName = v.findViewById(R.id.countryName);
        }
    }
}

