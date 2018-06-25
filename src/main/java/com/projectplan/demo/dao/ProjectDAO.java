package com.projectplan.demo.dao;

import org.springframework.data.repository.CrudRepository;

import com.projectplan.demo.dao.entity.Project;

public interface ProjectDAO extends CrudRepository<Project, Long>
{
    Project findByProjectId(Long taskId);
}
