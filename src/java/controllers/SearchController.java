/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import proxies.RentarProxy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import entities.Property;
import entities.RentRecord;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.ws.rs.core.Response;
import proxies.PropertyProxy;

/**
 *
 * @author JuanPablo
 */
@ManagedBean(name="searchController")
@RequestScoped
public class SearchController {

    private String idClient;
    private final PropertyProxy propertyProxy;
    private final RentarProxy rentarProxy;
    private ArrayList<Property> properties;
    private final Gson gson;
    private Property selectedProperty;
    @ManagedProperty(value="#{loginController}")
    private LoginController loginController;
    
    /**
     * Creates a new instance of SearchController
     */
    public SearchController() {
        this.gson = new GsonBuilder().create();
        this.propertyProxy = new PropertyProxy();
        this.properties = new ArrayList<>();
        this.rentarProxy = new RentarProxy();
    }

    public String getIdClient() {
        return idClient;
    }

    public void setIdClient(String idClient) {
        this.idClient = idClient;
    }

    public ArrayList<Property> getProperties() {
        return properties;
    }

    public void setProperties(ArrayList<Property> properties) {
        this.properties = properties;
    }

    public Property getSelectedProperty() {
        return selectedProperty;
    }

    public void setSelectedProperty(Property selectedProperty) {
        this.selectedProperty = selectedProperty;
    }

    public String searchProperty() {
        String res = this.propertyProxy.getSearchByCedula(idClient);
        this.properties = this.gson.fromJson(res, new TypeToken<List<Property>>(){}.getType());
        return "search"; // TODO - CHANGE
    }

    public LoginController getLoginController() {
        return loginController;
    }

    public void setLoginController(LoginController loginController) {
        this.loginController = loginController;
    }
    
    public void createRentRequest() {
        System.out.println("Create");
        RentRecord record = new RentRecord();
        System.out.println(this.selectedProperty == null);
        //record.setClientId(this.loginController.getClient());
        //record.setPropertyId(selectedProperty);
        //String requestBody = this.gson.toJson(record, RentRecord.class);
        //Response res = this.rentarProxy.postJson(requestBody);
        //System.out.println(res.getStatus());
        //return "search";
    }
    
}
