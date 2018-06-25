package com.projectplan.demo.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.JSONException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.util.StringUtils;

import com.projectplan.demo.dao.entity.Task;
import com.projectplan.demo.dto.TaskDTO;

public class TaskUtil
{
    public static void buildTaskJSONArray(JSONArray taskArray, Task task) throws JSONException
    {
        JSONObject taskJSON = new JSONObject();
        JSONObject taskRoot = new JSONObject();
        taskJSON.put("id", task.getTaskId());
        taskJSON.put("description", task.getDesc());
        taskJSON.put("durationHours", task.getDurationHours());
        taskJSON.put("end", task.getEnd());
        taskJSON.put("parentTaskId", task.getParentTaskId());
        taskJSON.put("projectId", task.getProjectId());
        taskJSON.put("start", task.getStart());
        taskJSON.put("status", task.getStatus());
        taskJSON.put("taskName", task.getTaskName());
        taskRoot.put("task", taskJSON);
        taskArray.add(taskRoot);

        includeChildren(task, taskJSON);
    }

    private static void includeChildren(Task parentTask, JSONObject parentJSON) throws JSONException
    {
        if (parentTask != null && parentTask.getChildren() != null && !parentTask.getChildren().isEmpty())
        {
            JSONArray childrenJSON = new JSONArray();
            for (Task task : parentTask.getChildren())
            {
                buildTaskJSONArray(childrenJSON, task);

            }
            parentJSON.put("children", childrenJSON);
        }
    }
    
    public static TaskDTO buildTaskDTO(String aTaskDTO) throws Exception
    {

        JSONParser parser = new JSONParser();

        JSONObject json = (JSONObject)parser.parse(aTaskDTO);
        JSONObject task = JSONUtilities.getJSONObjectValue(json, "task",true);
        TaskDTO dto = new TaskDTO();
        dto.setChildDepends(JSONUtilities.getStringValue(task, "childDepends",true));
        dto.setDesc(JSONUtilities.getStringValue(task, "desc"));
        String duration = JSONUtilities.getStringValue(task, "durationHours",true);
        dto.setDurationHours(Integer.parseInt(duration));
        String endDateString = JSONUtilities.getStringValue(task, "end");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        Date endDate = format.parse(endDateString);
        dto.setEnd(endDate);
        dto.setParentTaskId(JSONUtilities.getStringValue(task, "parentTaskId"));
        dto.setProjectId(JSONUtilities.getStringValue(task, "projectId",true));
        String startDateString = JSONUtilities.getStringValue(task, "start");
        Date startDate = format.parse(startDateString);
        dto.setStart(startDate);
        dto.setStatus(JSONUtilities.getStringValue(task, "status",true));
        dto.setTaskName(JSONUtilities.getStringValue(task, "taskName",true));
        // TaskDTO taskDTO = (TaskDTO)JSONUtilities.getObject(aTaskDTO, TaskDTO.class);
        // JSONUtilities.getJSONObjectValue(aTaskDTO, "task");
        return dto;
    }
    

    public static TaskDTO buildTaskUpdateDTO(String aTaskDTO) throws Exception
    {

        JSONParser parser = new JSONParser();

        JSONObject json = (JSONObject)parser.parse(aTaskDTO);
        JSONObject task = JSONUtilities.getJSONObjectValue(json, "task");
        TaskDTO dto = new TaskDTO();
        
        dto.setId(JSONUtilities.getStringValue(task, "id", true));
        dto.setChildDepends(JSONUtilities.getStringValue(task, "childDepends", false));
        dto.setDesc(JSONUtilities.getStringValue(task, "desc", false));

        dto.setTaskName(JSONUtilities.getStringValue(task, "taskName", false));

        return dto;
    }
    
    public static TaskDTO buildTaskUpdateDTOStatus(String aTaskDTO) throws Exception
    {

        JSONParser parser = new JSONParser();

        JSONObject json = (JSONObject)parser.parse(aTaskDTO);
        JSONObject task = JSONUtilities.getJSONObjectValue(json, "task");
        TaskDTO dto = new TaskDTO();
        
        dto.setId(JSONUtilities.getStringValue(task, "id", true));

        String duration = JSONUtilities.getStringValue(task, "durationHours", false);
        dto.setDurationHours(Integer.parseInt(duration));
        String endDateString = JSONUtilities.getStringValue(task, "end");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        Date endDate = format.parse(endDateString);
        dto.setEnd(endDate);
        dto.setParentTaskId(JSONUtilities.getStringValue(task, "parentTaskId", false));
        dto.setProjectId(JSONUtilities.getStringValue(task, "projectId", false));
        String startDateString = JSONUtilities.getStringValue(task, "start", false);
        Date startDate = format.parse(startDateString);
        dto.setStart(startDate);
        dto.setStatus(JSONUtilities.getStringValue(task, "status", false));


        return dto;
    }
    
    
    public static Task buildTaskEntity(TaskDTO aTask)
    {
        Task lTask = new Task();
        //lTask.setTaskId(Long.valueOf(aTask.getId()));
        String childDepends ="N";
        if(Boolean.valueOf(aTask.getChildDepends())) {
            childDepends="Y";
        }
        lTask.setChildDepends(childDepends);
        lTask.setDesc(aTask.getDesc());
        lTask.setDurationHours(aTask.getDurationHours());
        lTask.setEnd(aTask.getEnd());

        if (!StringUtils.isEmpty(aTask.getParentTaskId()))
        {
            lTask.setParentTaskId(Long.valueOf(aTask.getParentTaskId()));
        }
        lTask.setProjectId(Long.valueOf(aTask.getProjectId()));
        lTask.setStart(aTask.getStart());
        lTask.setStatus(aTask.getStatus());
        lTask.setTaskName(aTask.getTaskName());
        return lTask;
    }

    public static void buildUpdateTaskEntity(TaskDTO aTaskDto, Task aTask)
    {

        aTask.setTaskId(Long.valueOf(aTaskDto.getId()));
        String childDepends ="N";
        if(Boolean.valueOf(aTask.getChildDepends())) {
            childDepends = "Y";
        }
        aTask.setChildDepends(childDepends);
        aTask.setDesc(aTaskDto.getDesc());
        aTask.setDurationHours(aTaskDto.getDurationHours());
        aTask.setEnd(aTaskDto.getEnd());

        if (!StringUtils.isEmpty(aTaskDto.getParentTaskId()))
        {
            aTask.setParentTaskId(Long.valueOf(aTaskDto.getParentTaskId()));
        }
        aTask.setProjectId(Long.valueOf(aTaskDto.getProjectId()));
        aTask.setStart(aTaskDto.getStart());
        aTask.setStatus(aTaskDto.getStatus());
        aTask.setTaskName(aTaskDto.getTaskName());

    }
    public static void buildUpdateStatusTaskEntity(TaskDTO aTaskDto, Task aTask)
    {

        aTask.setTaskId(Long.valueOf(aTaskDto.getId()));


        aTask.setDurationHours(aTaskDto.getDurationHours());
        aTask.setEnd(aTaskDto.getEnd());


        aTask.setStart(aTaskDto.getStart());
        aTask.setStatus(aTaskDto.getStatus());


    }
}
