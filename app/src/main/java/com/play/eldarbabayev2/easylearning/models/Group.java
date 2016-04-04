package com.play.eldarbabayev2.easylearning.models;

public class Group {

    private String groupName;
    private String groupTeacher;
    private String groupDescription;
    private int groupSize;

    public Group() {
    }

    public Group(String groupName, String groupTeacher, String groupDescription) {
        this.groupName = groupName;
        this.groupTeacher = groupTeacher;
        this.groupDescription = groupDescription;
    }

    public Group(String groupName, String groupTeacher, String groupDescription, int groupSize) {
        this.groupName = groupName;
        this.groupTeacher = groupTeacher;
        this.groupDescription = groupDescription;
    }


    public String getGroupName() {
        return groupName;
    }

    public String getGroupTeacher() {
        return groupTeacher;
    }

    public String getGroupDescription() {
        return groupDescription;
    }

    public int getGroupSize() {
        return groupSize;
    }

    public void setGroupSize(int groupSize) {
        this.groupSize = groupSize;
    }
}