package controllers;

import ViewModels.UserViewModel;
import com.sun.corba.se.impl.corba.CORBAObjectImpl;
import forms.LoginForm;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.ClientResponse;
import org.glassfish.jersey.filter.LoggingFilter;
import org.omg.CORBA.Object;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.ws.rs.client.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

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
        /*GET*/
        Client client = ClientBuilder.newClient(new ClientConfig().register( LoggingFilter.class ));
        WebTarget target = client.target("http://localhost:8080/api.facelight").path("users/1");

        Invocation.Builder invocationBuilder =  target.request(MediaType.APPLICATION_JSON);
        currentUser = invocationBuilder.get(UserViewModel.class);

        System.out.println("UserName: "+ currentUser.getFirstName());

        if(currentUser != null){
            return "index";
        }
        return "register";
    }
}
