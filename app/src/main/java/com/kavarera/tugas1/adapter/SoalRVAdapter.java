package com.kavarera.tugas1.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.kavarera.tugas1.R;
import com.kavarera.tugas1.model.Soal;

import java.util.List;

public class SoalRVAdapter extends RecyclerView.Adapter<SoalRVAdapter.ViewHolder> {
    private List<Soal> soalList;

    private OnItemClickListener onItemClickListener;
    public interface OnItemClickListener {
        void onItemClick(Soal soal);
    }

    public SoalRVAdapter(List<Soal> soalList,OnItemClickListener onItemClickListener) {
        this.soalList = soalList;
        this.onItemClickListener=onItemClickListener;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_card_image, parent, false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Soal soal = soalList.get(position);
        Glide.with(holder.itemView.getContext())
                .load(soal.getGambarUrl())
                .into(holder.gambar);

        holder.itemView.setOnClickListener(v -> onItemClickListener.onItemClick(soal));
    }
    @Override
    public int getItemCount() {
        return soalList.size();
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView gambar;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            gambar = itemView.findViewById(R.id.image);
        }
    }
}
