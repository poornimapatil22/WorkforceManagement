package com.railse.hiring.workforcemgmt.model.enums;

import com.railse.hiring.workforcemgmt.common.model.enums.ReferenceType;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public enum Task {
    ASSIGN_CUSTOMER_TO_SALES_PERSON(List.of(ReferenceType.ENTITY), "Assign customer to Sales person", Priority.MEDIUM),
    CREATE_INVOICE(List.of(ReferenceType.ORDER), "Create Invoice", Priority.HIGH),
    ARRANGE_PICKUP(List.of(ReferenceType.ORDER), "Arrange Pickup", Priority.MEDIUM),
    COLLECT_PAYMENT(List.of(ReferenceType.ORDER), "Collect Payment", Priority.HIGH);

    private final List<ReferenceType> applicableReferenceTypes;
    private final String view;
    private final Priority priority;

    Task(List<ReferenceType> applicableReferenceTypes, String view, Priority priority) {
        this.applicableReferenceTypes = applicableReferenceTypes;
        this.view = view;
        this.priority = priority;
    }

    public static List<Task> getTasksByReferenceType(ReferenceType referenceType) {
        return Arrays.stream(Task.values())
                .filter(task -> task.getApplicableReferenceTypes().contains(referenceType))
                .collect(Collectors.toList());
    }

    public enum Priority {
        HIGH, MEDIUM, LOW
    }
}