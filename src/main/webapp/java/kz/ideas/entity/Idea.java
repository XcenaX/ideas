package kz.ideas.entity;

import java.sql.Date;

public class Idea extends Entity {
    private String title;
    private String description;
    private Integer userId;
    private Date pubDate;
    private boolean isDeleted;

    public Idea(String title, String description, Integer userId, Date pubDate, boolean isDeleted) {
        this.title = title;
        this.description = description;
        this.userId = userId;
        this.pubDate = pubDate;
        this.isDeleted = isDeleted;
    }

    public Idea() { }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getPubDate() {
        return pubDate;
    }

    public void setPubDate(Date pubDate) {
        this.pubDate = pubDate;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }
}
