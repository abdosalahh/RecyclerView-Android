package com.example.recyclerview;

public class DataClass {
    private String userName;
    private String description;
    private String time;
    private boolean read; // New attribute for read status

    public String getUserName() {
        return userName;
    }

    public String getDescription() {
        return description;
    }

    public String getTime() {
        return time;
    }

    public boolean isRead() {
        return read;
    }

    public void setRead(boolean read) {
        this.read = read;
    }

    public DataClass(String userName, String description, String time) {
        this.userName = userName;
        this.description = description;
        this.time = time;
    }
}
