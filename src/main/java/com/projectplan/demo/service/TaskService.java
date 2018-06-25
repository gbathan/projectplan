package com.projectplan.demo.service;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.JSONException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.projectplan.demo.dao.TaskDAO;
import com.projectplan.demo.dao.entity.Project;
import com.projectplan.demo.dao.entity.Task;
import com.projectplan.demo.dto.TaskDTO;
import com.projectplan.demo.util.JSONUtilities;
import com.projectplan.demo.util.TaskUtil;

@Service
public class TaskService
{
    @Autowired
    TaskDAO mTaskDAO;

    public Long updateTask(String aBody)
    {
        TaskDTO lTaskDTO = new TaskDTO();
        try
        {
            lTaskDTO = TaskUtil.buildTaskUpdateDTO(aBody);
        }
        catch (Exception e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Task lTask = mTaskDAO.findByTaskId(Long.valueOf(lTaskDTO.getId()));

        //        // if children dependent 
        //        boolean isCanUpdate = true;
        //        if (lTask.isChildDepends())
        //        {
        //            isCanUpdate = validateCanUpdate(lTask, isCanUpdate);
        //        }
        // update
        //        if (isCanUpdate)
        //        {
        TaskUtil.buildUpdateTaskEntity(lTaskDTO, lTask);
        lTask = mTaskDAO.save(lTask);
        //}
        return lTask.getTaskId();
    }

    public Long updateTaskStatus(String aBody) throws Exception
    {
        TaskDTO lTaskDTO = new TaskDTO();
        try
        {
            lTaskDTO = TaskUtil.buildTaskUpdateDTOStatus(aBody);
        }
        catch (Exception e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Task lTask = mTaskDAO.findByTaskId(Long.valueOf(lTaskDTO.getId()));

        //        // if children dependent 
        boolean isCanUpdate = true;
        if ("Y".equals(lTask.getChildDepends()))
        {
            isCanUpdate = validateCanUpdate(lTask, isCanUpdate);
        }
        // update
        if (isCanUpdate)
        {
            TaskUtil.buildUpdateStatusTaskEntity(lTaskDTO, lTask);
            lTask = mTaskDAO.save(lTask);
        }
        else
        {
            throw new Exception("Cannot update task. Need to complete dependent task first");
        }
        return lTask.getTaskId();
    }

    private boolean validateCanUpdate(Task lTask, boolean isCanUpdate)
    {
        if (lTask.getChildren() != null || !lTask.getChildren().isEmpty())
        {
            isCanUpdate = isCanUpdate(lTask, isCanUpdate);
        }
        return isCanUpdate;
    }

    private boolean isCanUpdate(Task lTask, boolean isCanUpdate)
    {
        for (Task childTask : lTask.getChildren())
        {

            if (childTask.getTaskId() != lTask.getTaskId() && !"complete".equals(childTask.getStatus()))
            {
                return isCanUpdate = false;

            }

            if (childTask.getTaskId() != lTask.getTaskId() && childTask.getChildren() != null
                    && !childTask.getChildren().isEmpty())
            {
                isCanUpdate(childTask, isCanUpdate);
            }
        }
        return isCanUpdate;
    }

    public Long createTask(String aTaskDTO)
    {
        TaskDTO lTaskDTO = new TaskDTO();
        try
        {
            lTaskDTO = TaskUtil.buildTaskDTO(aTaskDTO);
        }
        catch (Exception e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        Task lTask = TaskUtil.buildTaskEntity(lTaskDTO);
        lTask = mTaskDAO.save(lTask);
        return lTask.getTaskId();
    }

    public JSONArray getAll() throws JSONException
    {
        Iterable<Task> tasks = mTaskDAO.findAll();

        JSONArray taskArray = new JSONArray();
        for (Task task : tasks)
        {
            TaskUtil.buildTaskJSONArray(taskArray, task);
        }
        return taskArray;
    }

    public void deleteTask(String aId) throws Exception
    {
        Task lTask = mTaskDAO.findByTaskId(Long.valueOf(aId));
        if (lTask == null)
        {
            System.out.println("task not found");
            throw new Exception();
        }
        mTaskDAO.delete(lTask);
    }

    /**
     * @return the mTaskDAO
     */
    public TaskDAO getTaskDAO()
    {
        return mTaskDAO;
    }

    /**
     * @param aTaskDAO the mTaskDAO to set
     */
    public void setTaskDAO(TaskDAO aTaskDAO)
    {
        this.mTaskDAO = aTaskDAO;
    }

}
