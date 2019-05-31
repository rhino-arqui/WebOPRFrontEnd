/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entities.Property;
import java.math.BigDecimal;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.ws.rs.core.Response;
import proxies.PropertyProxy;

/**
 *
 * @author JuanPablo
 */
@ManagedBean(name = "addPropertyController")
@RequestScoped
public class AddPropertyController {

    @ManagedProperty(value="#{loginController}")
    private LoginController loginController; // +setter
    private Property property;
    private final Gson gson;
    private final PropertyProxy propertyProxy;
    private String confirmationMessage;
    
    /**
     * Creates a new instance of AddPropertyController
     */
    public AddPropertyController() {
        this.gson = new GsonBuilder().create();
        this.propertyProxy = new PropertyProxy();
        this.property = new Property();
        this.confirmationMessage = "";
               
    }

    public String getConfirmationMessage() {
        return confirmationMessage;
    }

    public void setConfirmationMessage(String confirmationMessage) {
        this.confirmationMessage = confirmationMessage;
    }
    
    public Property getProperty() {
        return property;
    }

    public void setProperty(Property property) {
        this.property = property;
    }

    public LoginController getLoginController() {
        return loginController;
    }

    public void setLoginController(LoginController loginController) {
        this.loginController = loginController;
    }
    
    public void addProperty() {
        this.property.setClientId(this.loginController.getClient());
        String jsonProperty = this.gson.toJson(this.property, Property.class);
        System.out.println(jsonProperty);
        Response response = this.propertyProxy.postJson(jsonProperty);
        if(response.getStatus() == 201) {
            this.confirmationMessage = "¡Propiedad Agregada!";
        } else {
            this.confirmationMessage = "Error al Agregar Propiedad";
        }
        System.out.println(response.getStatus());
    } 
    
}
