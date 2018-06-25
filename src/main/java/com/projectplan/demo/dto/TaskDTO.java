package com.projectplan.demo.dto;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "task")
@XmlType(name = "task", propOrder =
{ "id", "taskName", "desc", "durationHours", "start", "end", "parentTaskId", "projectId", "status",
        "childDepends" })
public class TaskDTO implements Serializable
{
    private String id;

    private String taskName;

    private String desc;

    private Integer durationHours;

    private Date start;

    private Date end;

    private String parentTaskId;

    private String projectId;

    private String status;

    private String childDepends;

    /**
     * @return the taskName
     */
    public String getTaskName()
    {
        return taskName;
    }

    /**
     * @param taskName the taskName to set
     */
    @XmlElement(name = "taskName")
    public void setTaskName(String taskName)
    {
        this.taskName = taskName;
    }

    /**
     * @return the desc
     */
    @XmlElement(name = "desc")
    public String getDesc()
    {
        return desc;
    }

    /**
     * @param desc the desc to set
     */
    public void setDesc(String desc)
    {
        this.desc = desc;
    }

    /**
     * @return the durationHours
     */
    @XmlElement(name = "durationHours")
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
    @XmlElement(name = "start")
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
    @XmlElement(name = "end")
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
    @XmlElement(name = "parentTaskId")
    public String getParentTaskId()
    {
        return parentTaskId;
    }

    /**
     * @param parentTaskId the parentTaskId to set
     */
    public void setParentTaskId(String parentTaskId)
    {
        this.parentTaskId = parentTaskId;
    }

    /**
     * @return the projectId
     */
    @XmlElement(name = "projectId")
    public String getProjectId()
    {
        return projectId;
    }

    /**
     * @param projectId the projectId to set
     */
    public void setProjectId(String projectId)
    {
        this.projectId = projectId;
    }

    /**
     * @return the status
     */
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
     * @return the childDepends
     */
    @XmlElement(name = "childDepends")
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

    /**
     * @return the taskId
     */
    @XmlAttribute(name = "id")
    public String getId()
    {
        return id;
    }

    /**
     * @param taskId the taskId to set
     */
    public void setId(String id)
    {
        this.id = id;
    }

}
