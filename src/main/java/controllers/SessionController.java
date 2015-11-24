package controllers;

import ViewModels.UserViewModel;
import forms.LoginForm;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.filter.LoggingFilter;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.ws.rs.client.*;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dario on 2015-11-12.
 */
@ManagedBean
@SessionScoped
public class SessionController {

    @ManagedProperty(value="#{loginForm}")
    private LoginForm loginForm;
    private UserViewModel currentUser;
    private UserViewModel remoteUser;

    public SessionController(){
        currentUser = new UserViewModel();
        remoteUser = new UserViewModel();
    }

    //Getter & Setter
    public UserViewModel getRemoteUser() {
        return remoteUser;
    }
    public void setRemoteUser(UserViewModel remoteUser) {
        this.remoteUser = remoteUser;
    }
    public LoginForm getLoginForm() {
        return loginForm;
    }

    public void setLoginForm(LoginForm loginForm) {
        this.loginForm = loginForm;
    }

    public UserViewModel getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(UserViewModel currentUser) {
        this.currentUser = currentUser;
    }

    //Methods
    public String doLogin(){
        /*POST*/
        Client client = ClientBuilder.newClient(new ClientConfig().register( LoggingFilter.class ));
        WebTarget target = client.target("http://localhost:8080/api.facelight/").path("login");

        Invocation.Builder invocationBuilder =  target.request(MediaType.APPLICATION_JSON);
        Response response = invocationBuilder.post(Entity.entity(loginForm, MediaType.APPLICATION_JSON));

        currentUser = response.readEntity(UserViewModel.class);
        if(currentUser != null){
            return "index";
        }
        return "register";
    }


    public String doLogout(){
        currentUser = null;
        remoteUser = null;
        return "login";
    }
}
