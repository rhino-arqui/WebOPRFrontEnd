/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entities.Client;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.ws.rs.core.Response;
import proxies.ClientsProxy;

/**
 *
 * @author JuanPablo
 */
@ManagedBean(name="loginController")
@ApplicationScoped
public class LoginController {
    
    private Client client;
    private final ClientsProxy clientsProxy;
    private final Gson gson;
    /**
     * Creates a new instance of LoginController
     */
    public LoginController() {
        this.gson = new GsonBuilder().create();
        this.client = new Client(null, "", "");
        this.clientsProxy = new ClientsProxy();
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
    
    /**
     * Sends the request to the server to login. Only if it returns 202 
     * the user is redirected to search.
     */
    public String login() {
        String request = this.gson.toJson(this.client);
        Response res = this.clientsProxy.login(request);
        if(res.getStatus() == 202) {
            String jsonResponse = res.readEntity(String.class);
            this.client = this.gson.fromJson(jsonResponse, Client.class);
            return "search";
        }
        return "index";
    }
    
}
