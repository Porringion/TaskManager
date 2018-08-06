package org.evg.controllers;

import org.evg.entities.Task;
import org.evg.entities.TaskExecutor;
import org.evg.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/tasks")
public class TasksController {

    @Autowired
    TaskService taskService;

    private String errorMessage = "";

    @GetMapping()
    public String showTasks(Map<String, Object> model){


        model.put("tasksTODO", taskService.getTasksByStage("1"));
        model.put("tasksProcess", taskService.getTasksByStage("2"));
        model.put("tasksFinished", taskService.getTasksByStage("3"));

        model.put("errorString", errorMessage);

        errorMessage = "";

        return "tasks";
    }

    @PostMapping("/add_task")
    public String addTask(Task taskFromForm){

        errorMessage = taskService.createTask(taskFromForm);

        return "redirect:/tasks";
    }

    @GetMapping("/{id}/edit_task")
    public String showEditTask(@PathVariable("id") Integer id, HashMap<String, Object> model){

        model.put("taskForUpdate", taskService.getTaskByID(id));

        return "edit_task";
    }

    @PostMapping("/{id}/edit_task")
    public String UpdateTask(@PathVariable("id") Integer id, Task taskForUpdate){

        taskService.updateTask(taskForUpdate, id);
        return "redirect:/tasks";
    }

    @GetMapping("/{id}/detail")
    public String showTaskDetail(@PathVariable("id") Integer id, HashMap<String, Object> model){

        Task task = taskService.getTaskByID(id);

        model.put("taskDetail", task);
        model.put("taskExecutor", task.getTaskExecutor());

        return "taskDetail";
    }

    @PostMapping("/{id}/detail/add_taskExecutor")
    public String addTaskExecutor(@PathVariable("id") Integer taskId, TaskExecutor taskExecutor){

        Task task = taskService.getTaskByID(taskId);
        task.setTaskExecutor(taskService.addTaskExecutor(taskExecutor));

        taskService.updateTask(task, taskId);

        return "redirect:/tasks/"+ String.valueOf(taskId) +"/detail";
    }

    @PostMapping("/{id}/del_task")
    public String deleteTask(@PathVariable("id") Integer id){
        taskService.deleteTaskById(id);
        return "redirect:/tasks";
    }
}
