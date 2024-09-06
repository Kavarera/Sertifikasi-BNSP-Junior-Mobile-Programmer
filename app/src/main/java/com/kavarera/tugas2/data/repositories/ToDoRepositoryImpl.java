// ToDoRepositoryImpl.java
package com.kavarera.tugas2.data.repositories;

import androidx.lifecycle.LiveData;
import com.kavarera.tugas2.data.local.dao.ToDoDao;
import com.kavarera.tugas2.data.local.entity.ToDo;

import java.util.List;

public class ToDoRepositoryImpl {
    private final ToDoDao toDoDao;
    private final LiveData<List<ToDo>> allTasks;

    public ToDoRepositoryImpl(ToDoDao toDoDao) {
        this.toDoDao = toDoDao;
        this.allTasks = toDoDao.getAllTasks();
    }

    public LiveData<List<ToDo>> getAllTasks() {
        return allTasks;
    }

    public void insert(ToDo toDo) {
        new Thread(() -> toDoDao.insert(toDo)).start();
    }

    public void delete(ToDo toDo) {
        new Thread(() -> toDoDao.delete(toDo)).start();
    }
}