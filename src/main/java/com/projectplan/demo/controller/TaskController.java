package com.projectplan.demo.controller;

import org.json.JSONException;
import org.json.simple.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.projectplan.demo.service.TaskService;

@RestController
@RequestMapping(path = "task", produces = MediaType.APPLICATION_JSON_VALUE)
public class TaskController
{

    @Autowired
    TaskService mTaskService;

    @ResponseBody
    @GetMapping(path = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONArray getTasks() throws JSONException
    {
        return mTaskService.getAll();

    }

    @ResponseBody
    @PostMapping(path = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
    public String createTask(@RequestBody String aBody)
    {
        Long id = mTaskService.createTask(aBody);

        return String.valueOf(id);
    }

    @ResponseBody
    @DeleteMapping(path = "delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String deleteTask(@PathVariable("id") String aId) throws Exception
    {
        mTaskService.deleteTask(aId);

        return "Success";
    }

    @ResponseBody
    @PutMapping(path = "/update", produces = MediaType.APPLICATION_JSON_VALUE)
    public String updateTask(@RequestBody String aBody)
    {
        mTaskService.updateTask(aBody);

        return "Success";
    }
    
    @ResponseBody
    @PutMapping(path = "/updateStatus", produces = MediaType.APPLICATION_JSON_VALUE)
    public String updateTaskStart(@RequestBody String aBody) throws Exception
    {
        mTaskService.updateTaskStatus(aBody);

        return "Success";
    }

}
