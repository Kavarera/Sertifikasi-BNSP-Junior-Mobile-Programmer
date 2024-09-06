// ToDoDatabase.java
package com.kavarera.tugas2.data.local.database;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.kavarera.tugas2.data.local.dao.ToDoDao;
import com.kavarera.tugas2.data.local.entity.ToDo;

@Database(entities = {ToDo.class}, version = 1)
public abstract class ToDoDatabase extends RoomDatabase {
    public abstract ToDoDao toDoDao();

    private static volatile ToDoDatabase INSTANCE;

    public static ToDoDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (ToDoDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    ToDoDatabase.class, "todo_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}