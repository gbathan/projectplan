package com.projectplan.demo.util;

import java.util.List;

import org.json.JSONException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.projectplan.demo.dao.entity.Project;
import com.projectplan.demo.dao.entity.Task;
import com.projectplan.demo.dto.ProjectDTO;

public class ProjectUtil
{
    public static void buildProjectJSONArray(JSONArray projectArray, Project project) throws JSONException
    {
        JSONObject projectJSON = new JSONObject();
        JSONObject projectRoot = new JSONObject();
        projectJSON.put("id", project.getProjectId());
        projectJSON.put("description", project.getDescription());
        projectJSON.put("projectName", project.getName());

        projectRoot.put("project", projectJSON);
        includeTasks(project.getTasks(), projectRoot);

        projectArray.add(projectRoot);
    }

    private static void includeTasks(List<Task> tasks, JSONObject projectRoot) throws JSONException
    {
        if (tasks != null && !tasks.isEmpty())
        {
            JSONArray arr = new JSONArray();
            for (Task task : tasks)
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
                arr.add(taskRoot);

                //projectJSON.add(taskRoot);
            }
            projectRoot.put("tasks", arr);

        }
    }

    public static ProjectDTO buildProjectDTO(String aProjectDto) throws Exception
    {

        JSONParser parser = new JSONParser();

        JSONObject json = (JSONObject)parser.parse(aProjectDto);
        JSONObject project = JSONUtilities.getJSONObjectValue(json, "project", true);
        ProjectDTO dto = new ProjectDTO();
        dto.setDescription(JSONUtilities.getStringValue(project, "description"));
        dto.setName(JSONUtilities.getStringValue(project, "projectName"));

        return dto;
    }

    public static ProjectDTO buildProjectDTOforUpdate(String aProjectDto) throws Exception
    {

        JSONParser parser = new JSONParser();

        JSONObject json = (JSONObject)parser.parse(aProjectDto);
        JSONObject project = JSONUtilities.getJSONObjectValue(json, "project", true);
        ProjectDTO dto = new ProjectDTO();
        dto.setDescription(JSONUtilities.getStringValue(project, "id", true));
        dto.setDescription(JSONUtilities.getStringValue(project, "description"));
        dto.setName(JSONUtilities.getStringValue(project, "projectName"));

        return dto;
    }

    public static Project buildProjectEntity(ProjectDTO aDto)
    {
        Project project = new Project();
        project.setDescription(aDto.getDescription());
        project.setName(aDto.getName());
        return project;

    }
}
