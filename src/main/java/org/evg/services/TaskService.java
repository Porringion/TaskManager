package org.evg.services;

import org.evg.entities.Task;
import org.evg.entities.TaskExecutor;
import org.evg.repos.TaskExecutorRepo;
import org.evg.repos.TaskRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    TaskRepo taskRepo;

    @Autowired
    TaskExecutorRepo taskExecutorRepo;

    private final String STRING_CANT_CREATE = "Can`t create";
    private final String STRING_CREATED = "Task created";

    public String createTask(Task task){

        if( task.getTaskStage() == null || task.getTaskStage().equals(""))
            return STRING_CANT_CREATE;

        if(task.getName().equals("") ||
                task.getDescription().equals("") ||
                task.getDeadline().equals("") ||
                task.getTaskStage().equals(""))
            return STRING_CANT_CREATE;

        try {
            Task taskFromDB = taskRepo.findByNameAndDescriptionAndDeadlineAndTaskStage(
                    task.getName(),
                    task.getDescription(),
                    task.getDeadline(),
                    task.getTaskStage());

            if(taskFromDB != null)
                return STRING_CANT_CREATE;

        }catch (Exception e){
            return STRING_CANT_CREATE;
        }


        task.setActive(true);

        taskRepo.save(task);

        return STRING_CREATED;

    }

    public Iterable<Task> getTasksByStage(String taskStage){

        Iterable<Task> taskList = taskRepo.findAllByTaskStageAndIsActive(taskStage, true);

        return taskList;
    }

    public String updateTask(Task task, Integer id){
        task.setId(id);
        task.setActive(true);
        taskRepo.save(task);
        return "task updated";

    }

    public void deleteTaskById(Integer id){
        taskRepo.deleteById(id);
    }

    public Task getTaskByID(Integer id){
        return  taskRepo.findById(id).get();
    }

    public TaskExecutor addTaskExecutor(TaskExecutor taskExecutor){

        taskExecutor.setId(null);

        TaskExecutor taskExecutorFromDB = taskExecutorRepo.findByName(taskExecutor.getName());

        if(taskExecutorFromDB != null)
            return taskExecutorFromDB;

        taskExecutorRepo.save(taskExecutor);

        return taskExecutor;
    }

    public Iterable<TaskExecutor> getAllTaskExecutor(){
        return taskExecutorRepo.findAll();
    }

}
