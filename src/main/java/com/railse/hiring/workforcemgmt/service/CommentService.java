package com.railse.hiring.workforcemgmt.service;

import com.railse.hiring.workforcemgmt.common.Comment;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class CommentService {
    private Map<Long, List<Comment>> commentStore = new ConcurrentHashMap<>();

    public void addComment(Long taskId, String comment) {
        Comment newComment = new Comment();
        newComment.setTaskId(taskId);
        newComment.setComment(comment);
        newComment.setTimestamp(new Date());
        commentStore.computeIfAbsent(taskId, k -> new ArrayList<>()).add(newComment);
    }

    public List<Comment> getComments(Long taskId) {
        return commentStore.getOrDefault(taskId, new ArrayList<>());
    }
}
