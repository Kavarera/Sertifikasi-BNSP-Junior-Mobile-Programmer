// MainActivityViewModelFactory.java
package com.kavarera.tugas2.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.kavarera.tugas2.data.repositories.ToDoRepositoryImpl;

public class MainActivityViewModelFactory implements ViewModelProvider.Factory {
    private final ToDoRepositoryImpl repository;

    public MainActivityViewModelFactory(ToDoRepositoryImpl repository) {
        this.repository = repository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(MainActivityViewModel.class)) {
            return (T) new MainActivityViewModel(repository);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}