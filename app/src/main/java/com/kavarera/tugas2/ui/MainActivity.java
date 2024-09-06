// MainActivity.java
package com.kavarera.tugas2.ui;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.kavarera.tugas2.R;
import com.kavarera.tugas2.data.local.database.ToDoDatabase;
import com.kavarera.tugas2.data.local.entity.ToDo;
import com.kavarera.tugas2.data.repositories.ToDoRepositoryImpl;
import com.kavarera.tugas2.databinding.ActivityMainBinding;
import com.kavarera.tugas2.databinding.LayoutAddNewTaskDialogBinding;
import com.kavarera.tugas2.adapter.ToDoAdapter;
import com.kavarera.tugas2.mapper.ToDoMapper;
import com.kavarera.tugas2.models.ToDoModel;
import com.kavarera.tugas2.viewmodel.MainActivityViewModel;
import com.kavarera.tugas2.viewmodel.MainActivityViewModelFactory;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private MainActivityViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        binding.toolbar.setOverflowIcon(getDrawable(R.drawable.baseline_more_vert_24));
        ViewCompat.setOnApplyWindowInsetsListener(binding.getRoot(), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        setSupportActionBar(binding.toolbar);

        // Set up ViewModel
        ToDoDatabase database = ToDoDatabase.getDatabase(this);
        ToDoRepositoryImpl repository = new ToDoRepositoryImpl(database.toDoDao());
        MainActivityViewModelFactory factory = new MainActivityViewModelFactory(repository);
        viewModel = new ViewModelProvider(this, factory).get(MainActivityViewModel.class);

        viewModel.getAllTasks().observe(this, toDoList -> {
            List<ToDoModel> modelList = ToDoMapper.toModelList(toDoList);
            ToDoAdapter adapter = new ToDoAdapter(modelList);
            adapter.setOnDeleteClickListener(todomodel -> {
                ToDo tdEntity = new ToDo(todomodel.getTitle());
                tdEntity.setId(todomodel.getId());
                viewModel.delete(tdEntity);
            });
            binding.recyclerView.setAdapter(adapter);
        });

        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        setContentView(binding.getRoot());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.optionmenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            showAlertDialog();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void showAlertDialog() {
        LayoutAddNewTaskDialogBinding dialogBinding = LayoutAddNewTaskDialogBinding
                .inflate(getLayoutInflater());
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Add Task");
        builder.setView(dialogBinding.getRoot());
        builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (!dialogBinding.etTaskName.getText().toString().isEmpty()) {
                    viewModel.insert(new ToDo(dialogBinding.etTaskName.getText().toString()));
                }
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.create().show();
    }
}