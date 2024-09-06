// ToDoMapper.java
package com.kavarera.tugas2.mapper;

import com.kavarera.tugas2.data.local.entity.ToDo;
import com.kavarera.tugas2.models.ToDoModel;

import java.util.List;
import java.util.stream.Collectors;

public class ToDoMapper {
    public static ToDoModel toModel(ToDo entity) {
        ToDoModel model = new ToDoModel(entity.getTitle());
        model.setId(entity.getId());
        return model;
    }

    public static List<ToDoModel> toModelList(List<ToDo> entities) {
        return entities.stream().map(ToDoMapper::toModel).collect(Collectors.toList());
    }
}