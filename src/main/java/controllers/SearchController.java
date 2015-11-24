package controllers;

import ViewModels.UserViewModel;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.ArrayList;

/**
 * Created by johan on 10/11/15.
 */
@ManagedBean(name="search")
@SessionScoped
public class SearchController {

    private String query;
    private ArrayList<UserViewModel> users = new ArrayList<UserViewModel>();

    public ArrayList<UserViewModel> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<UserViewModel> users) {
        this.users = users;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String search(){
       // users = UserHandler.getUserByName(query);
        //users.forEach(item -> System.out.println(item.getFirstName()));
        return "search";
    }
}