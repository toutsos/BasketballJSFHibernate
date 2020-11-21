/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cdi;

import java.io.Serializable;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.SessionScoped;

/**
 *
 * @author atoutsios
 */
@Named(value = "currentUser")
@SessionScoped
public class CurrentUser implements Serializable{
    
    private String currentTheme="eggplant";
    /**
     * Creates a new instance of CurrentUser
     */
    public CurrentUser() {
    }

    public String getCurrentTheme() {
        return currentTheme;
    }

    public void setCurrentTheme(String currentTheme) {
        this.currentTheme = currentTheme;
    }
    
    
    
}
