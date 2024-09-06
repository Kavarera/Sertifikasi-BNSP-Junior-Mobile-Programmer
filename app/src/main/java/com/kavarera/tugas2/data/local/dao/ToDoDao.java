// ToDoDao.java
package com.kavarera.tugas2.data.local.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.kavarera.tugas2.data.local.entity.ToDo;

import java.util.List;

@Dao
public interface ToDoDao {
    @Insert
    void insert(ToDo toDo);

    @Query("SELECT * FROM todo_table")
    LiveData<List<ToDo>> getAllTasks();

    @Delete
    void delete(ToDo toDo);
}