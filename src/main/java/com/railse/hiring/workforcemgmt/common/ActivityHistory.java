package com.railse.hiring.workforcemgmt.common;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
@Data
public class ActivityHistory  {
    private Long taskId;
    private String activity;

    private Date timestamp;

}

