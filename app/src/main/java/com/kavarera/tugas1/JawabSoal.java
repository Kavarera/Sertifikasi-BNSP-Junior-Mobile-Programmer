package com.kavarera.tugas1;

import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.kavarera.tugas1.databinding.ActivityJawabSoalBinding;
import com.kavarera.tugas1.model.Soal;

public class JawabSoal extends AppCompatActivity {

    private ActivityJawabSoalBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityJawabSoalBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(binding.getRoot(), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Soal soal = getIntent().getParcelableExtra("soal");

        binding.image.setImageResource(soal.getGambarUrl());

        binding.btnSubmit.setOnClickListener(v -> {
            if (binding.etJawaban.getText().toString().equalsIgnoreCase(soal.getJawaban())) {
                Toast.makeText(this, "Jawaban Benar", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Jawaban Salah", Toast.LENGTH_SHORT).show();
            }
        });


    }
}