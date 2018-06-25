package com.projectplan.demo.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.projectplan.demo.dao.TaskDAO;
import com.projectplan.demo.dao.entity.Task;
import com.projectplan.demo.dto.TaskDTO;

public class TaskServiceTest
{
    TaskService mProjectPlanService;

    TaskDAO mTaskDAO;

    //    @Before
    //    public void init()
    //    {
    //        mProjectPlanService = new TaskService();
    //        mTaskDAO = Mockito.mock(TaskDAO.class);
    //
    //        mProjectPlanService.setTaskDAO(mTaskDAO);
    //    }
    //
    //    @Test
    //    public void testUpdateTask()
    //    {
    //        TaskDTO taskDTO =  getTaskDTO();
    //        Task parentTask = getTaskDependentYCompleted();
    //        when(mProjectPlanService.getTaskDAO().findByTaskId(parentTask.getTaskId())).thenReturn(parentTask);
    //        boolean isUpdated = mProjectPlanService.updateTask(taskDTO);
    //        assertEquals(true, isUpdated);
    //    }
    //
    //    @Test
    //    public void testUpdateTaskIncompleteTask()
    //    {
    //        TaskDTO taskDTO =  getTaskDTO();
    //        Task parentTask = getTaskDependentYIncompete();
    //        when(mProjectPlanService.getTaskDAO().findByTaskId(parentTask.getTaskId())).thenReturn(parentTask);
    //        boolean isUpdated = mProjectPlanService.updateTask(taskDTO);
    //        assertEquals(false, isUpdated);
    //    }

    private TaskDTO getTaskDTO()
    {
        TaskDTO requestDTO = new TaskDTO();
        requestDTO.setId("1");
        requestDTO.setDesc("Parent Task");
        requestDTO.setDurationHours(24);
        requestDTO.setEnd(new Date());
        requestDTO.setProjectId("1");
        requestDTO.setStart(new Date());
        requestDTO.setStatus("complete");
        requestDTO.setTaskName("Parent Task");


        return requestDTO;
    }

    private Task getTaskDependentYCompleted()
    {
        Task parentTask = new Task();
        parentTask.setTaskId(1L);
        parentTask.setDesc("Parent Task");
        parentTask.setDurationHours(24);
        parentTask.setEnd(new Date());
        parentTask.setProjectId(1L);
        parentTask.setStart(new Date());
        parentTask.setStatus("");
        parentTask.setTaskName("Parent Task");
        parentTask.setChildDepends("Y");
        Task task = new Task();
        task.setTaskId(2L);
        task.setDesc("Child Task");
        task.setDurationHours(24);
        task.setEnd(new Date());
        task.setProjectId(1L);
        task.setStart(new Date());
        task.setStatus("completed");
        task.setTaskName("Child Task");
        task.setParentTaskId(1L);

        List<Task> children = new ArrayList<Task>();
        children.add(task);

        parentTask.setChildren(children);
        return parentTask;
    }

    private Task getTaskDependentYIncompete()
    {
        Task parentTask = new Task();
        parentTask.setTaskId(1L);
        parentTask.setDesc("Parent Task");
        parentTask.setDurationHours(24);
        parentTask.setEnd(new Date());
        parentTask.setProjectId(1L);
        parentTask.setStart(new Date());
        parentTask.setStatus("");
        parentTask.setTaskName("Parent Task");
        parentTask.setChildDepends("Y");
        Task task = new Task();
        task.setTaskId(2L);
        task.setDesc("Child Task");
        task.setDurationHours(24);
        task.setEnd(new Date());
        task.setProjectId(1L);
        task.setStart(new Date());
        task.setStatus("");
        task.setTaskName("Child Task");
        task.setParentTaskId(1L);

        List<Task> children = new ArrayList<Task>();
        children.add(task);

        parentTask.setChildren(children);
        return parentTask;
    }

    private Task getTaskDependentN()
    {
        Task parentTask = new Task();
        parentTask.setTaskId(1L);
        parentTask.setDesc("Parent Task");
        parentTask.setDurationHours(24);
        parentTask.setEnd(new Date());
        parentTask.setProjectId(1L);
        parentTask.setStart(new Date());
        parentTask.setStatus("");
        parentTask.setTaskName("Parent Task");
        parentTask.setChildDepends("Y");
        Task task = new Task();
        task.setTaskId(2L);
        task.setDesc("Child Task");
        task.setDurationHours(24);
        task.setEnd(new Date());
        task.setProjectId(1L);
        task.setStart(new Date());
        task.setStatus("");
        task.setTaskName("Child Task");
        task.setParentTaskId(1L);

        List<Task> children = new ArrayList<Task>();
        children.add(task);

        parentTask.setChildren(children);
        return parentTask;
    }
}
