package org.example.model;

public class Bug {
    private int bugId;
    private String title;
    private String priority;
    private String status;

    public Bug() {}

    public Bug(int bugId, String title, String priority, String status) {
        this.bugId = bugId;
        this.title = title;
        this.priority = priority;
        this.status = status;
    }

    public int getBugId() {
        return bugId;
    }

    public void setBugId(int bugId) {
        this.bugId = bugId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
