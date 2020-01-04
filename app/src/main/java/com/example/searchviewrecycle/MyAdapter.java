package com.example.searchviewrecycle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> implements Filterable { // implements them 1 interface de ho tro chuc nang search

    ArrayList<MyData> data; // hien thi ket qua sau khi search
    ArrayList<MyData> dataFull; // du lieu tong de search

    Filter myFilter = new Filter() { // khoi tao doi tuong dinh nghia search (filter)
        @Override
        protected FilterResults performFiltering(CharSequence constraint) { //giai thuat loc ket qua theo chuoi search(constraint)

            ArrayList<MyData> filterList = new ArrayList<>();
            if (constraint == null || constraint.length() == 0) { // neu khong search thi lay het data
                filterList.addAll(dataFull);
            } else {

                String compareString = constraint.toString().toLowerCase().trim(); // cat bat phan chuoi thua truoc va sau chuoi search, cho thanh tat ca chu thuong(lower case)

                for (MyData data : dataFull) {
                    if (data.title.toLowerCase().contains(compareString)) { // them vao ds ket qua khi ton tai ky tu
                        filterList.add(data);
                    }
                }

            }
            FilterResults results = new FilterResults();
            results.values = filterList; // lay ds ket qua gan vao result
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) { // hien thi ket qua sau khi loc
            data.clear();
            data.addAll( (ArrayList<MyData>)results.values);
            notifyDataSetChanged();
        }
    };

    @Override
    public Filter getFilter() { // ham bat buoc cai de cua interface filterable
        return myFilter;
    }


    public MyAdapter(ArrayList<MyData> data) {
        this.data = data;
        dataFull = new ArrayList<>(data);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.title.setText(data.get(position).title);
        holder.content.setText(data.get(position).content);

    }

    @Override
    public int getItemCount() {
        return data.size();
    }



    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView content;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.textView);
            content = itemView.findViewById(R.id.textView2);

        }
    }
}