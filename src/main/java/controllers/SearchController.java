package controllers;

import ViewModels.UserViewModel;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.filter.LoggingFilter;


import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by johan on 10/11/15.
 */
@ManagedBean(name="search")
@SessionScoped
public class SearchController {

    private static final String API_URL = "http://130.237.84.58:8080/api.facelight/";

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

        Client client = ClientBuilder.newClient(new ClientConfig().register( LoggingFilter.class ));
        WebTarget target = client.target(API_URL).path("users").queryParam("name", query);

        Invocation.Builder invocationBuilder =  target.request(MediaType.APPLICATION_JSON);
        users = invocationBuilder.get(new GenericType<ArrayList<UserViewModel>>(){});

        users.forEach(item -> System.out.println(item.getFirstName()));

        return "search";
    }
}