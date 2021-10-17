package com.example.week2practice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerV;
    MyAdapter myAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
/*
        Button startbutton = findViewById(R.id.button1);
        startbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(MainActivity.this, CountryActivity.class);
                intent1.putExtra("CountryName","US");
                startActivity(intent1);
            }
        });
*/
        /* 用adpater 放进recyclerView的写法1
        // get a handle to the recyclerV
        RecyclerView recyclerV = findViewById(R.id.receylerV);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerV.setLayoutManager(layoutManager);
        MyAdapter myAdapter = new MyAdapter();
        recyclerV.setAdapter(myAdapter); //要根据myAdapter这个对象要在之前创建一个MyAdapter这个类

        */
        //用adapter的写法2
        // get a handle to the recyclerV
        recyclerV = findViewById(R.id.receylerV);

        //create an adapter and supply the data to be displayed
        MyAdapter myAdapter = new MyAdapter(Country.getCountries());

        //connect the adpater with the recyclerView
        recyclerV.setAdapter(myAdapter);

        //give the recyclerView a default layout manager 就是你想横着放还是竖着放
        recyclerV.setLayoutManager(new LinearLayoutManager(this));
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);

        //find the search bar line 65-79 关于search的
        //MenuItem item = menu.findItem(R.id.action_search);
        SearchView searchBarr = (SearchView) menu.findItem(R.id.action_search).getActionView();
        //SearchView searchBarr = (SearchView) item.getActionView();
        searchBarr.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                myAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                myAdapter.getFilter().filter(newText);
                return false;
            }
        });
        //return super.onCreateOptionsMenu(menu);//// 与line83的区别
        return true; 
    }
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){ //这个getItemId是代码本身不用改的
            case R.id.newCasesItem:
                //create a new arraylist and copy the data from Country.class
                ArrayList<Country> sortByNewCases = new ArrayList<>();
                //copy a new 公文包
                sortByNewCases.addAll(Country.getCountries());
                //in order
                Collections.sort(sortByNewCases, new Comparator<Country>() {
                    @Override
                    public int compare(Country country, Country t1) {
                        if (country.getNewConfirmed() < t1.getNewConfirmed()){
                            return 1;
                        } else {
                            return -1;
                        }
                    }
                });
                //要用那个adpater去传这个新的公文包，而不是用之前在MyAdapter里面的公文包
                MyAdapter myNewAdapter = new MyAdapter(sortByNewCases);

                // a new adapter has been created and we need to put the new one into recyclerview
                recyclerV.setAdapter(myNewAdapter);

                //give the recyclerView a default layout manager 就是你想横着放还是竖着放
                recyclerV.setLayoutManager(new LinearLayoutManager(this));
                return true;

            case R.id.totalCasesItem:
                ArrayList<Country> sortByTotalCases = new ArrayList<>();
                sortByTotalCases.addAll(Country.getCountries());
                Collections.sort(sortByTotalCases, new Comparator<Country>() {
                    @Override
                    public int compare(Country country, Country t1) {
                        if (country.getTotalConfirmed() < t1.getTotalConfirmed()){
                            return 1;
                        } else {
                            return -1;
                        }
                    }
                });
                //要用那个adpater去传这个新的公文包，而不是用之前在MyAdapter里面的公文包
                MyAdapter myNewAdapter2 = new MyAdapter(sortByTotalCases);

                // a new adapter has been created and we need to put the new one into recyclerview
                recyclerV.setAdapter(myNewAdapter2);

                //give the recyclerView a default layout manager 就是你想横着放还是竖着放
                recyclerV.setLayoutManager(new LinearLayoutManager(this));

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}