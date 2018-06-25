package com.projectplan.demo.stub;

import java.util.ArrayList;
import java.util.List;

import com.projectplan.demo.dao.entity.Project;

public class ProjectPlanStub
{
    public static Project getProject(Long projId)
    {

        Project project = new Project();
        project.setProjectId(projId);
        project.setDescription("Test project");
        project.setName("Project");
        return project;

    }

    public List<Project> getProjects()
    {
        List<Project> projs = new ArrayList();
        Project proj1 = getProject(1L);
        Project proj2 = getProject(2L);

        projs.add(proj1);
        projs.add(proj2);
        return projs;
    }
}
