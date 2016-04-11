package com.play.eldarbabayev2.easylearning.models;

public class Group {

    private String name;
    private String teacher;
    private String description;
    private long size;
    private long timeOfCreation;
    private String groupId;

    public Group() {
    }

    public Group(String name, String teacher, String description, int size, long timeOfCreation) {
        this.name = name;
        this.teacher = teacher;
        this.description = description;
        this.size = size;
        this.timeOfCreation = timeOfCreation;
    }

    public Group(String name, String teacher, String description, String groupId) {
        this.name = name;
        this.teacher = teacher;
        this.description = description;
        this.groupId = groupId;
    }


    public String getName() {
        return name;
    }

    public String getTeacher() {
        return teacher;
    }

    public String getDescription() {
        return description;
    }

    public long getTimeOfCreation() {
        return timeOfCreation;
    }

    public String getGroupId() { return groupId; }

    public long getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}