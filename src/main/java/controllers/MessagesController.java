package controllers;

import ViewModels.MessageViewModel;
import ViewModels.UserViewModel;
import forms.MessageForm;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import java.util.ArrayList;

/**
 * Created by dario on 2015-11-12.
 */
@ManagedBean
public class MessagesController {

    @ManagedProperty(value="#{messageForm}")
    private MessageForm messageForm;

    private ArrayList<MessageViewModel> messages;

    public MessageForm getMessageForm() {
        return messageForm;
    }

    public void setMessageForm(MessageForm messageForm) {
        this.messageForm = messageForm;
    }

    public ArrayList<MessageViewModel> getPrivateMessages(UserViewModel currentUser){

        return null; //MessageHandler.getAllMessages(currentUser.getId());
    }

    public ArrayList<MessageViewModel> getMessages(UserViewModel currentUser)
    {
        //get from database
        System.out.println("------CURRENT USER: "+ currentUser.getFirstName());
        return null;
    }
    public void setMessages(ArrayList<MessageViewModel> messages) {
        this.messages = messages;
    }

    public String createUserMessage(){
        // takes a messageForm a sends a messageViewModel
        System.out.println("Subject: " + messageForm.getSubject());
        System.out.println("Content: " + messageForm.getContent());
        System.out.println("Type: " + messageForm.getType());
        System.out.println("Sender " + messageForm.getSender());
        System.out.println("receiver " + messageForm.getReceiver());


        //MessageHandler.createMessage(messageForm);

        return "index";
    }

    public ArrayList<UserViewModel> getAllUsers() {
        //ArrayList<User> users = (ArrayList<User>) UserHandler.getAllUsers();
        return null;
    }
}
