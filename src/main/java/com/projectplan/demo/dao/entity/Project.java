package com.projectplan.demo.dao.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="PROJECT")
public class Project
{
    private Long projectId;

    private String name;

    private String description;

    private List<Task> tasks;

    /**
     * @return the projectId
     */
    @Id
    @SequenceGenerator(name = "PROJECT_ID", sequenceName = "PROJECT_ID", allocationSize = 1)
    @GeneratedValue(generator = "PROJECT_ID")
    @Column(name = "ID")
    public Long getProjectId()
    {
        return projectId;
    }

    /**
     * @param projectId the projectId to set
     */
    public void setProjectId(Long projectId)
    {
        this.projectId = projectId;
    }

    /**
     * @return the name
     */
    @Column(name="NAME")
    public String getName()
    {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * @return the description
     */
    @Column(name="DESCRIPTION")
    public String getDescription()
    {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description)
    {
        this.description = description;
    }

    /**
     * @return the tasks
     */
    @OneToMany(mappedBy="project", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval=true)
    public List<Task> getTasks()
    {
        return tasks;
    }

    /**
     * @param tasks the tasks to set
     */
    public void setTasks(List<Task> tasks)
    {
        this.tasks = tasks;
    }

}
