package kz.ideas.entity;

public class Role extends Entity {
    private String name;

    public Role(String name){
        this.name = name;
    }
    public Role(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
