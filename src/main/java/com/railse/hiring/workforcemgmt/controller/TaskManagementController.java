package com.railse.hiring.workforcemgmt.controller;

import com.railse.hiring.workforcemgmt.common.ActivityHistory;
import com.railse.hiring.workforcemgmt.common.Comment;
import com.railse.hiring.workforcemgmt.common.model.response.Response;
import com.railse.hiring.workforcemgmt.dto.*;
import com.railse.hiring.workforcemgmt.model.TaskManagement;
import com.railse.hiring.workforcemgmt.model.enums.Priority;
import com.railse.hiring.workforcemgmt.repository.TaskRepository;
import com.railse.hiring.workforcemgmt.service.ActivityHistoryService;
import com.railse.hiring.workforcemgmt.service.CommentService;
import com.railse.hiring.workforcemgmt.service.TaskManagementService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/task-mgmt")
public class TaskManagementController {
    private final TaskManagementService taskManagementService;
    private final TaskRepository taskRepository;
    private final ActivityHistoryService activityHistoryService;

    private final CommentService commentService;

    public TaskManagementController(TaskManagementService taskManagementService, TaskRepository taskRepository, ActivityHistoryService activityHistoryService, CommentService commentService) {
        this.taskManagementService = taskManagementService;
        this.taskRepository = taskRepository;
        this.activityHistoryService = activityHistoryService;
        this.commentService = commentService;
    }

    @GetMapping("/{id}")
    public Response<TaskManagementDto> getTaskById(@PathVariable Long id) {
        return new Response<>(taskManagementService.findTaskById(id));
    }

    @PostMapping("/create")
    public Response<List<TaskManagementDto>> createTasks(@RequestBody TaskCreateRequest request) {
        return new Response<>(taskManagementService.createTasks(request));
    }

    @PostMapping("/update")
    public Response<List<TaskManagementDto>> updateTasks(@RequestBody UpdateTaskRequest request) {
        return new Response<>(taskManagementService.updateTasks(request));
    }

    @PostMapping("/assign-by-ref")
    public Response<String> assignByReference(@RequestBody AssignByReferenceRequest request) {
        return new Response<>(taskManagementService.assignByReference(request));
    }

    @PostMapping("/fetch-by-date/v2")
    public Response<List<TaskManagementDto>> fetchByDate(@RequestBody TaskFetchByDateRequest request) {
        return new Response<>(taskManagementService.fetchTasksByDate(request));
    }

    @GetMapping("/test")
    public Response<String> test(){
        return  new Response<>("Test success");
    }

    @GetMapping("/tasks/daily-view")
    public Response<List<TaskManagement>> getTasksForDailyView(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate, @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate) {
        List<TaskManagement> tasks = taskRepository.findTasksForDailyView(startDate, endDate);
        return new Response<>(tasks);
    }

    @PutMapping("/tasks/{id}/{priority}")
    public Response<TaskManagement> updateTaskPriority(@PathVariable Long id, @PathVariable Priority priority) {
        TaskManagement task = taskRepository.findById(id).orElseThrow();
        task.setPriority(priority);
        taskRepository.save(task);
        activityHistoryService.logActivity(id, "Priority updated to " + priority);
        return new Response<>(task);
    }

    @GetMapping("/tasks/priority/{priority}")
    public Response<List<TaskManagement>> getTasksByPriority(@PathVariable Priority priority) {
        List<TaskManagement> tasks = taskRepository.findByPriority(priority);
        return new Response<>(tasks);
    }

    @PostMapping("/tasks/{id}/comments")
    public Response<Void> addComment(@PathVariable Long id, @RequestBody Comment comment) {
        commentService.addComment(id, comment.getComment());
        return new Response<>(null);
    }

    @GetMapping("/tasks/{id}/comments")
    public Response<List<Comment>> getComments(@PathVariable Long id) {
        List<Comment> comments = commentService.getComments(id);
        return new Response<>(comments);
    }

    @GetMapping("/tasks/assignee/{assigneeId}")
    public Response<List<TaskManagement>> getTasksByAssigneeId(@PathVariable Long assigneeId) {
        List<TaskManagement> tasks = taskRepository.findByAssigneeId(assigneeId);
        return new Response<>(tasks);
    }

}

//http://localhost:8080/task-mgmt/tasks/{taskId}/priority