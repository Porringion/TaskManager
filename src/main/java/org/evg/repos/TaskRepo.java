package org.evg.repos;

import org.evg.entities.Task;
import org.springframework.data.repository.CrudRepository;

public interface TaskRepo extends CrudRepository<Task, Integer> {

    Task findByNameAndDescriptionAndDeadlineAndTaskStage(
            String name,
            String description,
            String deadline,
            String taskStage);

    Iterable<Task> findAllByTaskStageAndIsActive(String taskStage, Boolean isActive);

}
