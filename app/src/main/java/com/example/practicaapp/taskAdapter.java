package com.example.practicaapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class taskAdapter extends RecyclerView.Adapter<MyViewHolder> implements Filterable {

    private List<tasksModel> tasksList;
    private Context context;

    private List<tasksModel> taskdListFiltrada;

    public taskAdapter(List<tasksModel> tasksList, Context context) {
        this.tasksList = tasksList;
        this.context = context;
        this.taskdListFiltrada = tasksList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_item, parent, false);
       return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {


        holder.taskTextView.setText(tasksList.get(position).getTitulo());
        holder.detailTextView.setText(tasksList.get(position).getDescripcion());

        holder.cardRec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, detailActivity.class);
                intent.putExtra("titulo", tasksList.get(holder.getAdapterPosition()).getTitulo());
                intent.putExtra("descripcion", tasksList.get(holder.getAdapterPosition()).getDescripcion());
                intent.putExtra("fecha", tasksList.get(holder.getAdapterPosition()).getFecha());
                intent.putExtra("key", tasksList.get(holder.getAdapterPosition()).getKey());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return tasksList.size();
    }


    @Override
    public Filter getFilter() {
        return null;
    }

    private class CustomFilter extends Filter{

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();
            List<tasksModel> listaFiltrada = new ArrayList<>();
            if (constraint == null || constraint.length() == 0){
                listaFiltrada.addAll(tasksList);
            } else {
                String filtro = constraint.toString().toLowerCase().trim();
                for (tasksModel item : tasksList){
                    if (item.getTitulo().equalsIgnoreCase(filtro)){
                        listaFiltrada.add(item);
                    }
                }
            }
            results.values = listaFiltrada;
            results.count = listaFiltrada.size();
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            taskdListFiltrada = (ArrayList<tasksModel>) results.values;
            notifyDataSetChanged();
        }
    }
}

class MyViewHolder extends RecyclerView.ViewHolder{

    TextView taskTextView, detailTextView;
    CardView cardRec;

    public MyViewHolder(@NonNull View itemView) {
        super(itemView);

        taskTextView = itemView.findViewById(R.id.taskTextView);
        detailTextView = itemView.findViewById(R.id.detailTextView);
        cardRec = itemView.findViewById(R.id.cardRec);
    }
}
