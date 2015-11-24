package controllers;

import ViewModels.UserViewModel;
import forms.UserForm;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.filter.LoggingFilter;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.ws.rs.client.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by dario on 2015-11-11.
 */
@ManagedBean
public class UserController {

    @ManagedProperty(value="#{userForm}")
    private UserForm userForm;
    @ManagedProperty(value ="#{sessionController}")
    private SessionController session;

    public UserController(){
    }


    public SessionController getSession() {
        return session;
    }

    public void setSession(SessionController session) {
        this.session = session;
    }

    public UserForm getUserForm() {
        return userForm;
    }

    public void setUserForm(UserForm userForm) {
        this.userForm = userForm;
    }


    //Methods
    public String registerUser(){

        Client client = ClientBuilder.newClient(new ClientConfig().register( LoggingFilter.class ));
        WebTarget target = client.target("http://130.229.130.25:8080/api.facelight/").path("users");


        Invocation.Builder invocationBuilder =  target.request(MediaType.APPLICATION_JSON);
        Response response = invocationBuilder.post(Entity.entity(userForm, MediaType.APPLICATION_JSON));

        UserViewModel user = response.readEntity(UserViewModel.class);

        System.out.println(user.getFirstName());

        if(user != null){
            return "login";
        }

        return "register";
    }


    public String userProfile(UserViewModel remoteUser){
        session.setRemoteUser(remoteUser);
        return "user_profile";
    }


}
