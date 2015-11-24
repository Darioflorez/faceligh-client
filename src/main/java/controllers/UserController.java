package controllers;

import ViewModels.UserViewModel;
import forms.UserForm;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

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

        /*if(UserHandler.createUser(userForm) != null){
            return "login";
        }*/
        return "register";
    }


    public String userProfile(UserViewModel remoteUser){
        session.setRemoteUser(remoteUser);
        return "user_profile";
    }


}
