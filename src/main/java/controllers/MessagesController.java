package controllers;

import ViewModels.MessageType;
import ViewModels.MessageViewModel;
import ViewModels.UserViewModel;
import forms.MessageForm;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.filter.LoggingFilter;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.ws.rs.client.*;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
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
        Client client = ClientBuilder.newClient(new ClientConfig().register( LoggingFilter.class ));
        WebTarget target = client.target("http://localhost:8080/api.facelight").path("messages").queryParam("userId", currentUser.getId()).queryParam("type", MessageType.PUBLIC);

        Invocation.Builder invocationBuilder =  target.request(MediaType.APPLICATION_JSON);
        ArrayList<MessageViewModel> messages = invocationBuilder.get(new GenericType<ArrayList<MessageViewModel>>(){});

        //get from database
        //System.out.println("------CURRENT USER: "+ currentUser.getFirstName());
        return messages;
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

        Client client = ClientBuilder.newClient(new ClientConfig().register( LoggingFilter.class ));
        WebTarget target = client.target("http://130.229.130.25:8080/api.facelight/").path("messages");


        Invocation.Builder invocationBuilder =  target.request(MediaType.APPLICATION_JSON);
        Response response = invocationBuilder.post(Entity.entity(messageForm, MediaType.APPLICATION_JSON));

        System.out.println(response.readEntity(Boolean.class));

        return "index";
    }

    public ArrayList<UserViewModel> getAllUsers() {

        Client client = ClientBuilder.newClient(new ClientConfig().register( LoggingFilter.class ));
        WebTarget target = client.target("http://localhost:8080/api.facelight").path("users");

        Invocation.Builder invocationBuilder =  target.request(MediaType.APPLICATION_JSON);
        ArrayList<UserViewModel> users = invocationBuilder.get(new GenericType<ArrayList<UserViewModel>>(){});

        return users;
    }
}
