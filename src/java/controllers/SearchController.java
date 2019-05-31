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
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.ws.rs.core.Response;
import proxies.PropertyProxy;
import utils.EndPoints;

/**
 *
 * @author JuanPablo
 */
@ManagedBean(name="searchController")
@ViewScoped
public class SearchController { 

    private String idClient;
    private final PropertyProxy propertyProxy;
    private final RentarProxy rentarProxy;
    private ArrayList<Property> properties;
    private final Gson gson;
    private String selectedPropertyId;
    private int responseStatus;
    @ManagedProperty(value="#{loginController}")
    private LoginController loginController;
    private RentRecord newRentRecord;
    
    /**
     * Creates a new instance of SearchController
     */
    public SearchController() {
        this.gson = new GsonBuilder().create();
        this.propertyProxy = new PropertyProxy();
        this.properties = new ArrayList<>();
        this.rentarProxy = new RentarProxy();
        this.responseStatus = 0;
    }
    
    public String getIdClient() {
        return idClient;
    }

    public void setIdClient(String idClient) {
        this.idClient = idClient;
    }

    public ArrayList<Property> getProperties() {
        if(this.properties.isEmpty()) this.searchProperty();
        return properties;
    }

    public void setProperties(ArrayList<Property> properties) {
        this.properties = properties;
    }

    public String getSelectedPropertyId() {
        return selectedPropertyId;
    }

    public void setSelectedPropertyId(String selectedPropertyId) {
        this.selectedPropertyId = selectedPropertyId;
    }
    
    public void searchProperty() {
        String res = this.propertyProxy.getSearchByCedula(idClient);
        this.properties = this.gson.fromJson(res, new TypeToken<List<Property>>(){}.getType());
        //return "search"; // TODO - CHANGE
    }

    public LoginController getLoginController() {
        return loginController;
    }

    public void setLoginController(LoginController loginController) {
        this.loginController = loginController;
    }

    public int getResponseStatus() {
        return responseStatus;
    }

    public void setResponseStatus(int responseStatus) {
        this.responseStatus = responseStatus;
    }
    
    public void createRentRequest() {
        RentRecord record = new RentRecord();
        Property propertyToRent = new Property(new BigDecimal(this.selectedPropertyId));
        record.setClientId(this.loginController.getClient());
        record.setPropertyId(propertyToRent);
        String requestBody = this.gson.toJson(record, RentRecord.class);
        Response res = this.rentarProxy.postJson(requestBody);
        this.responseStatus = res.getStatus();
        if(this.responseStatus == 201) {
            String jsonResponse = res.readEntity(String.class);
            this.newRentRecord = this.gson.fromJson(jsonResponse, RentRecord.class);
        }
    }
    
    public String getConfirmationLink() {
        String id = (this.newRentRecord == null) ? "" : this.newRentRecord.getId().toString();
        return EndPoints.OprEndPoint + "/confirm/" + id;
    }
    
}
