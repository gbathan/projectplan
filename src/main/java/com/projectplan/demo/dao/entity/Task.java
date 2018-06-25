package com.projectplan.demo.dao.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "PROJECT_TASK")
public class Task
{
    private Long taskId;

    private String taskName;

    private String desc;

    private Integer durationHours;

    private Date start;

    private Date end;

    private Long parentTaskId;

    private Task parentTask;

    private List<Task> children;

    private Long projectId;

    private Project project;

    private String status;

    private String childDepends;

    /**
     * @return the projectId
     */
    @Column(name = "PROJECT_ID")
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
     * @return the project
     */
    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(insertable = false, name = "PROJECT_ID", updatable = false)
    public Project getProject()
    {
        return project;
    }

    /**
     * @param project the project to set
     */
    public void setProject(Project project)
    {
        this.project = project;
    }

    /**
     * @return the taskId
     */
    @Id
    @SequenceGenerator(name = "PROJECT_TASK_ID", sequenceName = "PROJECT_TASK_ID", allocationSize = 1)
    @GeneratedValue(generator = "PROJECT_TASK_ID")
    @Column(name = "ID")
    public Long getTaskId()
    {
        return taskId;
    }

    /**
     * @param taskId the taskId to set
     */
    public void setTaskId(Long taskId)
    {
        this.taskId = taskId;
    }

    /**
     * @return the taskName
     */
    @Column(name = "NAME")
    public String getTaskName()
    {
        return taskName;
    }

    /**
     * @param taskName the taskName to set
     */
    public void setTaskName(String taskName)
    {
        this.taskName = taskName;
    }

    /**
     * @return the desc
     */
    @Column(name = "DESCRIPTION")
    public String getDesc()
    {
        return desc;
    }

    /**
     * @param desc the desc to set
     */
    @Transient
    public void setDesc(String desc)
    {
        this.desc = desc;
    }

    /**
     * @return the durationHours
     */
    @Column(name = "DURATION_HOURS")
    public Integer getDurationHours()
    {
        return durationHours;
    }

    /**
     * @param durationHours the durationHours to set
     */
    public void setDurationHours(Integer durationHours)
    {
        this.durationHours = durationHours;
    }

    /**
     * @return the start
     */
    @Column(name = "START_DATE")
    public Date getStart()
    {
        return start;
    }

    /**
     * @param start the start to set
     */
    public void setStart(Date start)
    {
        this.start = start;
    }

    /**
     * @return the end
     */
    @Column(name = "END_DATE")
    public Date getEnd()
    {
        return end;
    }

    /**
     * @param end the end to set
     */
    public void setEnd(Date end)
    {
        this.end = end;
    }

    /**
     * @return the parentTaskId
     */
    @Column(name = "PARENT_PROJECT_TASK_ID")
    public Long getParentTaskId()
    {
        return parentTaskId;
    }

    /**
     * @param parentTaskId the parentTaskId to set
     */
    public void setParentTaskId(Long parentTaskId)
    {
        this.parentTaskId = parentTaskId;
    }

    /**
     * @return the status
     */
    @Column(name = "STATUS")
    public String getStatus()
    {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status)
    {
        this.status = status;
    }

    /**
     * @return the parentTask
     */
    //    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    //    @JoinColumn(insertable = false, name = "PROJECT_ID", updatable = false)
    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(insertable = false, name = "PARENT_PROJECT_TASK_ID", updatable = false)
    public Task getParentTask()
    {
        return parentTask;
    }

    /**
     * @param parentTask the parentTask to set
     */
    public void setParentTask(Task parentTask)
    {
        this.parentTask = parentTask;
    }

    /**
     * @return the children
     */
    @OneToMany(mappedBy = "parentTask", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    public List<Task> getChildren()
    {
        return children;
    }

    /**
     * @param children the children to set
     */
    public void setChildren(List<Task> children)
    {
        this.children = children;
    }

    /**
     * @return the childDepends
     */
    @Column(name = "DEPENDS")
    public String getChildDepends()
    {
        return childDepends;
    }

 
    /**
     * @param childDepends the childDepends to set
     */
    public void setChildDepends(String childDepends)
    {
        this.childDepends = childDepends;
    }

}
