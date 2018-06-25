package com.projectplan.demo.dao;


import org.springframework.data.repository.CrudRepository;

import com.projectplan.demo.dao.entity.Task;

public interface TaskDAO extends CrudRepository<Task, Long>
{
    Task findByTaskId(Long taskId);

    //Long updateTask(Task lTask);
}
