/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author JuanPablo
 */
@ManagedBean(name="searchController")
@RequestScoped
public class SearchController {

    private String idClient;
    
    /**
     * Creates a new instance of SearchController
     */
    public SearchController() {
        
    }

    public String getIdClient() {
        return idClient;
    }

    public void setIdClient(String idClient) {
        this.idClient = idClient;
    }
    
    public String searchProperty() {
        return "searchProperty"; // TODO - CHANGE
    }
    
}
