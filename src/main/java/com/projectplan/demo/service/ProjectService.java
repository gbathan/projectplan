package com.projectplan.demo.service;

import java.util.List;

import org.json.JSONException;
import org.json.simple.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projectplan.demo.dao.ProjectDAO;
import com.projectplan.demo.dao.entity.Project;
import com.projectplan.demo.dao.entity.Task;
import com.projectplan.demo.dto.ProjectDTO;
import com.projectplan.demo.util.ProjectUtil;

@Service
public class ProjectService
{

    @Autowired
    ProjectDAO mProjectDAO;

    public Long createProject(String aBody)
    {
        ProjectDTO lProjectDTO = new ProjectDTO();
        try
        {
            lProjectDTO = ProjectUtil.buildProjectDTO(aBody);
        }
        catch (Exception e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        Project project = ProjectUtil.buildProjectEntity(lProjectDTO);
        project = mProjectDAO.save(project);

        // if children dependent 

        return project.getProjectId();

    }

    public Long updateProject(String aBody)
    {
        ProjectDTO lProjectDTO = new ProjectDTO();
        try
        {
            lProjectDTO = ProjectUtil.buildProjectDTOforUpdate(aBody);
        }
        catch (Exception e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Project project = mProjectDAO.findByProjectId(Long.valueOf(lProjectDTO.getId()));

        return project.getProjectId();
    }

    private boolean validateCanUpdate(Project project, boolean isCanUpdate)
    {
        if (project.getTasks() != null || !project.getTasks().isEmpty())
        {
            isCanUpdate = isCanUpdate(project.getTasks(), isCanUpdate);
        }
        return isCanUpdate;
    }

    private boolean isCanUpdate(List<Task> tasks, boolean isCanUpdate)
    {
        for (Task childTask : tasks)
        {

            if (!"completed".equals(childTask.getStatus()))
            {
                isCanUpdate = false;
                break;
            }

            if (childTask.getChildren() != null || !childTask.getChildren().isEmpty())
            {
                isCanUpdate(childTask.getChildren(), isCanUpdate);
            }
        }
        return isCanUpdate;
    }

    public void deleteProject(String id) throws Exception
    {
        Project lProject = mProjectDAO.findByProjectId(Long.valueOf(id));
        if (lProject == null)
        {
            System.out.println("task not found");
            throw new Exception();
        }
        mProjectDAO.delete(lProject);
    }

    public JSONArray getAllProject() throws JSONException
    {
        Iterable<Project> projects = mProjectDAO.findAll();

        JSONArray projectArray = new JSONArray();
        for (Project project : projects)
        {
            ProjectUtil.buildProjectJSONArray(projectArray, project);
        }
        return projectArray;
    }

}
