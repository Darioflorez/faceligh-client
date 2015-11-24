package ViewModels;

/**
 * Created by dario on 2015-11-10.
 */
public class MessageViewModel {

    private MessageType type;
    private String subject;
    private String content;
    private String createdAt;

    private UserViewModel receiver;
    private UserViewModel sender;


    public UserViewModel getSender() {
        return sender;
    }

    public void setSender(UserViewModel sender) {
        this.sender = sender;
    }

    public UserViewModel getReceiver() {
        return receiver;
    }

    public void setReceiver(UserViewModel receiver) {
        this.receiver = receiver;
    }

    public MessageType getType() {
        return type;
    }

    public void setType(MessageType type) {
        this.type = type;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}
