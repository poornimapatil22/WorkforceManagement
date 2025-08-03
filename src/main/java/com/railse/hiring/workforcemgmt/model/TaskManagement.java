package com.railse.hiring.workforcemgmt.model;


import com.railse.hiring.workforcemgmt.common.ActivityHistory;
import com.railse.hiring.workforcemgmt.common.Comment;
import com.railse.hiring.workforcemgmt.model.enums.Priority;
import com.railse.hiring.workforcemgmt.common.model.enums.ReferenceType;
import com.railse.hiring.workforcemgmt.model.enums.Task;
import com.railse.hiring.workforcemgmt.model.enums.TaskStatus;
import lombok.Data;

import java.util.Date;
import java.util.List;


@Data
    public class TaskManagement {
        private Date startDate;
    private List<ActivityHistory> activityHistory;
    private List<Comment> comments;
        private Date endDate;
        private Long id;
        private Long referenceId;
        private ReferenceType referenceType;
        private Task task;
        private String description;
        private TaskStatus status;
        private Long assigneeId; // Simplified from Entity for this assignment
        private Long taskDeadlineTime;
        private Priority priority;
    }


