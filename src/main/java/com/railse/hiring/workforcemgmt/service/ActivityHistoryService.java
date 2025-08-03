package com.railse.hiring.workforcemgmt.service;

import com.railse.hiring.workforcemgmt.common.ActivityHistory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


@Service
    public class ActivityHistoryService {
        private Map<Long, List<ActivityHistory>> activityHistoryStore = new ConcurrentHashMap<>();

        public void logActivity(Long taskId, String activity) {
            ActivityHistory history = new ActivityHistory();
            history.setTaskId(taskId);
            history.setActivity(activity);
            history.setTimestamp(new Date());
            activityHistoryStore.computeIfAbsent(taskId, k -> new ArrayList<>()).add(history);
        }

        public List<ActivityHistory> getActivityHistory(Long taskId) {
            return activityHistoryStore.getOrDefault(taskId, new ArrayList<>());
        }
    }

