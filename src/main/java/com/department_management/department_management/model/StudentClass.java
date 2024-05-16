package com.department_management.department_management.model;


public enum StudentClass {
    FIRST("1st"),
    SECOND("2nd"),
    THIRD("3rd"),
    FOURTH("4th"),
    FIFTH("5th");

    private final String displayName;

    StudentClass(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}

