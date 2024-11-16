package FloraWeddingHall.system;

public class Response {
    private boolean success;
    private String message;
    private String suggestion;
    private MessageType type;

    public enum MessageType {
        SUCCESS, WARNING, ERROR
    }

    public Response(boolean success, String message, String suggestion, MessageType type) {
        this.success = success;
        this.message = message;
        this.suggestion = suggestion;
        this.type = type;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public String getSuggestion() {
        return suggestion;
    }

    public MessageType getType() {
        return type;
    }
}
