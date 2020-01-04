package com.example.searchviewrecycle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.SearchView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView recyclerView = findViewById(R.id.recycle);

        ArrayList<MyData> data = new ArrayList<>();
        data.add(new MyData("Monkey", "This is a monkey"));
        data.add(new MyData("Chicken", "This is a chicken"));
        data.add(new MyData("Dog", "This is a dog"));
        data.add(new MyData("Banana", "This is a banana"));
        data.add(new MyData("Bom", "This is a bom"));
        data.add(new MyData("Cow", "This is a cow"));
        data.add(new MyData("Bear", "This is a bear"));
        data.add(new MyData("Country", "This is a country"));
        data.add(new MyData("Gun", "This is a gun"));
        data.add(new MyData("Goat", "This is a goat"));
        data.add(new MyData("Ghost", "This is a ghost"));
        myAdapter = new MyAdapter(data);
        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        MenuItem item = menu.findItem(R.id.searchView);
        SearchView searchView = (SearchView) item.getActionView(); // lay searchview ra (da them vao o file xml menu)
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                myAdapter.getFilter().filter(newText);
                return false;
            }
        });

        return true;
    }
    /*   do su dung nut search view action cua he thong, nen khong can khai bao them su kien cho nut
    khong can cai de ham nay
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return super.onOptionsItemSelected(item);
    } */
}



