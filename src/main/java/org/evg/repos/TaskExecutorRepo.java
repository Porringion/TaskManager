package org.evg.repos;

import org.evg.entities.TaskExecutor;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TaskExecutorRepo extends CrudRepository<TaskExecutor, Integer> {

    TaskExecutor findByName(String name);
}
