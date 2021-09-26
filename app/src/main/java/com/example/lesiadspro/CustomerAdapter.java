package com.example.lesiadspro;

import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import io.grpc.Context;

public class CustomerAdapter extends RecyclerView.Adapter<ViewHolder> {

    ListActivity listActivity;
    List<Model> modelList;
    Context context;

    public CustomerAdapter(ListActivity listActivity, List<Model> modelList) {
        this.listActivity = listActivity;
        this.modelList = modelList;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.model_layout, parent,false);

        ViewHolder viewHolder = new ViewHolder(itemView);

        viewHolder.setOnClickListner(new ViewHolder.ClickListner() {
            @Override
            public void onItemClick(View view, int position) {


                String name = modelList.get(position).getName();
                String email = modelList.get(position).getEmail();
                String feedback = modelList.get(position).getFeedback();
                Toast.makeText(listActivity, name+"\n"+email+"\n"+feedback, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onItemLongClick(View view, int position) {
                AlertDialog.Builder builder = new AlertDialog.Builder(listActivity);

                String[] options = {"Update", "Delete"};
                builder.setItems(options, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                }).create().show();

            }
        });

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.mName.setText(modelList.get(position).getName());
        holder.mEmail.setText(modelList.get(position).getEmail());
        holder.mFeedback.setText(modelList.get(position).getFeedback());

    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }
}
