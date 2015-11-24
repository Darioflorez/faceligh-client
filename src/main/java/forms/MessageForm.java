package forms;


import ViewModels.MessageType;

import javax.faces.bean.ManagedBean;

/**
 * Created by dario on 2015-11-12.
 */
@ManagedBean
public class MessageForm {

    private String subject;
    private String content;
    private MessageType type;
    private int sender;
    private int receiver;

    public int getSender() {
        return sender;
    }

    public void setSender(int sender) {
        this.sender = sender;
    }

    public int getReceiver() {
        return receiver;
    }

    public void setReceiver(int receiver) {
        this.receiver = receiver;
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

    public MessageType getType() {
        return type;
    }

    public void setType(MessageType type) {
        this.type = type;
    }

}
