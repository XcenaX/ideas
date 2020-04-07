package kz.ideas.entity;

public class RatedIdea extends Entity {
    private Integer ideaId;
    private Integer userId;
    private boolean isLike;

    public RatedIdea(Integer ideaId, Integer userId, boolean isLike) {
        this.ideaId = ideaId;
        this.userId = userId;
        this.isLike = isLike;
    }

    public RatedIdea() {}

    public Integer getIdeaId() {
        return ideaId;
    }

    public void setIdeaId(Integer ideaId) {
        this.ideaId = ideaId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public boolean isLike() {
        return isLike;
    }

    public void setLike(boolean like) {
        isLike = like;
    }
}
