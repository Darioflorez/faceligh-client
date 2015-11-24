package controllers;

import ViewModels.UserViewModel;
import forms.LoginForm;
import org.glassfish.jersey.client.ClientResponse;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;

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
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://localhost:8080/api.facelight").path("login");

        Form form = new Form();
        form.param("email", "dario@email.com");
        form.param("password", "123");

        this.currentUser =
                target.request(MediaType.APPLICATION_JSON_TYPE)
                        .post(Entity.entity(form,MediaType.APPLICATION_FORM_URLENCODED_TYPE),
                                UserViewModel.class);

        if(currentUser != null){
            return "index";
        }
        return "register";
    }
}
