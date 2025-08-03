package com.railse.hiring.workforcemgmt.common;

import lombok.Data;

import java.util.Date;
@Data
public class Comment {
    private Long taskId;
    private String comment;
    private Date timestamp;

}

