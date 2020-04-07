package kz.ideas.exception;

public class CheckException extends Exception {
    private String field;

    public CheckException(String message, String field) {
        super(message);
        this.field = field;
    }

    public String getField() {
        return field;
    }
}
