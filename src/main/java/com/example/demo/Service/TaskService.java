package com.example.demo.Service;

import com.example.demo.model.Task;
import com.example.demo.model.TaskExecution;
import com.example.demo.Repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    // Get all tasks
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    // Get a single task by ID
    public Optional<Task> getTaskById(String id) {
        return taskRepository.findById(id);
    }

    // Create or update a task
    public Task saveTask(Task task) {
        // Basic validation to prevent unsafe commands
        if (task.getCommand().toLowerCase().contains("rm") ||
                task.getCommand().toLowerCase().contains("shutdown") ||
                task.getCommand().toLowerCase().contains("reboot")) {
            throw new IllegalArgumentException("Unsafe command detected!");
        }
        return taskRepository.save(task);
    }

    // Delete task by ID
    public void deleteTask(String id) {
        taskRepository.deleteById(id);
    }

    // Find tasks by name substring
    public List<Task> findTasksByName(String name) {
        return taskRepository.findByNameContainingIgnoreCase(name);
    }

    // Execute a task’s command and store execution log
    public Task executeTask(String id) throws Exception {
        Optional<Task> taskOpt = taskRepository.findById(id);
        if (taskOpt.isEmpty()) {
            throw new RuntimeException("Task not found");
        }

        Task task = taskOpt.get();

        Date start = new Date();
        Process process;
        if (System.getProperty("os.name").toLowerCase().contains("win")) {
            // Windows: run via cmd.exe
            process = Runtime.getRuntime().exec(new String[] { "cmd.exe", "/c", task.getCommand() });
        } else {
            // Linux / macOS: run via /bin/sh
            process = Runtime.getRuntime().exec(new String[] { "/bin/sh", "-c", task.getCommand() });
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        StringBuilder output = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            output.append(line).append("\n");
        }
        process.waitFor();

        Date end = new Date();

        TaskExecution exec = new TaskExecution(start, end, output.toString().trim());
        task.addTaskExecution(exec);

        return taskRepository.save(task);
    }
}
