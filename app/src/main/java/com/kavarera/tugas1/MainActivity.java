package com.kavarera.tugas1;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kavarera.tugas1.adapter.SoalRVAdapter;
import com.kavarera.tugas1.databinding.ActivityMainBinding;
import com.kavarera.tugas1.model.Soal;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(binding.getRoot(), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        setSupportActionBar(binding.toolbar);



        binding.recyclerView.setLayoutManager(new GridLayoutManager(this,2));

        List<Soal> soalList = new ArrayList<>();

        soalList.add(new Soal("facebook",R.drawable.facebook));
        soalList.add(new Soal("github",R.drawable.github));
        soalList.add(new Soal("google",R.drawable.google));
        soalList.add(new Soal("java",R.drawable.java));
        soalList.add(new Soal("google drive",R.drawable.googledrive));
        soalList.add(new Soal("instagram",R.drawable.instagram));
        soalList.add(new Soal("twitter",R.drawable.twitter));
        soalList.add(new Soal("youtube",R.drawable.youtube));


        SoalRVAdapter adapter = new SoalRVAdapter(soalList, soal->{
            Intent intent = new Intent(this,JawabSoal.class);
            intent.putExtra("soal",soal);
            startActivity(intent);
        });
        binding.recyclerView.setAdapter(adapter);
    }
}