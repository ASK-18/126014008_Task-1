package com.example.demo.Controller;

import com.example.demo.model.Task;
import com.example.demo.Service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    // GET /tasks -> return all tasks
    // GET /tasks?id=123 -> return task by id
    @GetMapping
    public ResponseEntity<?> getTasks(@RequestParam(required = false) String id) {
        if (id != null) {
            Optional<Task> task = taskService.getTaskById(id);
            return task.map(ResponseEntity::ok)
                       .orElseGet(() -> ResponseEntity.notFound().build());
        }
        return ResponseEntity.ok(taskService.getAllTasks());
    }

    // PUT /tasks -> create or update a task
    @PutMapping
    public ResponseEntity<?> createOrUpdateTask(@RequestBody Task task) {
        try {
            Task saved = taskService.saveTask(task);
            return ResponseEntity.ok(saved);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // DELETE /tasks/{id} -> delete a task
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTask(@PathVariable String id) {
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }

    // GET /tasks/search?name=hello -> find tasks by name
    @GetMapping("/search")
    public ResponseEntity<?> searchTasks(@RequestParam String name) {
        List<Task> tasks = taskService.findTasksByName(name);
        if (tasks.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(tasks);
    }

    // PUT /tasks/{id}/execute -> run a task command
    @PutMapping("/{id}/execute")
    public ResponseEntity<?> executeTask(@PathVariable String id) {
        try {
            Task updated = taskService.executeTask(id);
            return ResponseEntity.ok(updated);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Execution failed: " + e.getMessage());
        }
    }
}

