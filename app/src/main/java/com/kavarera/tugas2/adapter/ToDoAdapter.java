// ToDoAdapter.java
package com.kavarera.tugas2.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kavarera.tugas2.R;
import com.kavarera.tugas2.models.ToDoModel;

import java.util.List;

public class ToDoAdapter extends RecyclerView.Adapter<ToDoAdapter.ToDoViewHolder> {
    private List<ToDoModel> todoList;
    private OnDeleteClickListener onDeleteClickListener;

    public interface OnDeleteClickListener {
        void onDeleteClick(ToDoModel todo);
    }

    public ToDoAdapter(List<ToDoModel> toDoModelList) {
        this.todoList = toDoModelList;
    }

    public static class ToDoViewHolder extends RecyclerView.ViewHolder {
        TextView tv;
        Button btn;

        public ToDoViewHolder(@NonNull View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.tv_todo_title);
            btn = itemView.findViewById(R.id.btn_delete);
        }
    }

    @NonNull
    @Override
    public ToDoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_todo_item, parent, false);
        return new ToDoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ToDoViewHolder holder, int position) {
        ToDoModel todo = todoList.get(position);
        holder.tv.setText(todo.getTitle());
        holder.btn.setOnClickListener(v -> {
            if (onDeleteClickListener != null) {
                onDeleteClickListener.onDeleteClick(todo);
            }
        });
    }

    @Override
    public int getItemCount() {
        return todoList.size();
    }

    public void setOnDeleteClickListener(OnDeleteClickListener onDeleteClickListener) {
        this.onDeleteClickListener = onDeleteClickListener;
    }
}

//package com.kavarera.tugas2.adapter;
//
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Button;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.kavarera.tugas2.R;
//import com.kavarera.tugas2.models.ToDoModel;
//
//import java.util.List;
//
//public class ToDoAdapter extends RecyclerView.Adapter<ToDoAdapter.ToDoViewHolder> {
//    private List<ToDoModel> todoList;
//    private OnDeleteClickListener onDeleteClickListener;
//
//    public interface  OnDeleteClickListener{
//        void onDeleteClick(ToDoModel todo);
//    }
//    public ToDoAdapter(List<ToDoModel> toDoModelList) {
//        this.todoList = toDoModelList;
//    }
//
//    public static class ToDoViewHolder extends RecyclerView.ViewHolder {
//        TextView tv;
//        Button btn;
//        public ToDoViewHolder(@NonNull View itemView) {
//            super(itemView);
//            tv = itemView.findViewById(R.id.tv_todo_title);
//            btn = itemView.findViewById(R.id.btn_delete);
//        }
//    }
//
//    @NonNull
//    @Override
//    public ToDoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_todo_item, parent, false);
//        return new ToDoViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull ToDoViewHolder holder, int position) {
//        ToDoModel todo = todoList.get(position);
//        holder.tv.setText(todo.getTitle());
//        holder.btn.setOnClickListener(v -> {
//            if(onDeleteClickListener != null){
//                onDeleteClickListener.onDeleteClick(todo);
//            }
//        });
//
//    }
//    @Override
//    public int getItemCount() {
//        return todoList.size();
//    }
//
//    public void setOnDeleteClickListener(OnDeleteClickListener onDeleteClickListener) {
//        this.onDeleteClickListener = onDeleteClickListener;
//    }
//}
