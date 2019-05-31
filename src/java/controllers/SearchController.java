/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import entities.Property;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
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
    private ArrayList<Property> properties;
    private final Gson gson;
    private String selectedPropertyAdress;
    
    /**
     * Creates a new instance of SearchController
     */
    public SearchController() {
        this.gson = new GsonBuilder().create();
        this.propertyProxy = new PropertyProxy();
        this.properties = new ArrayList<>();
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

    public String getSelectedPropertyAdress() {
        return selectedPropertyAdress;
    }

    public void setSelectedPropertyAdress(String selectedPropertyAdress) {
        this.selectedPropertyAdress = selectedPropertyAdress;
    }
    
    public String searchProperty() {
        String res = this.propertyProxy.getSearchByCedula(idClient);
        this.properties = this.gson.fromJson(res, new TypeToken<List<Property>>(){}.getType());
        return "search"; // TODO - CHANGE
    }
    
    // ID NoCuar Direc Costo
    
}
