// MainActivityViewModel.java
package com.kavarera.tugas2.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.kavarera.tugas2.data.local.entity.ToDo;
import com.kavarera.tugas2.data.repositories.ToDoRepositoryImpl;

import java.util.List;

public class MainActivityViewModel extends ViewModel {
    private final ToDoRepositoryImpl repository;
    private final LiveData<List<ToDo>> allTasks;

    public MainActivityViewModel(ToDoRepositoryImpl repository) {
        this.repository = repository;
        this.allTasks = repository.getAllTasks();
    }

    public LiveData<List<ToDo>> getAllTasks() {
        return allTasks;
    }

    public void insert(ToDo toDo) {
        repository.insert(toDo);
    }

    public void delete(ToDo toDo) {
        repository.delete(toDo);
    }
}