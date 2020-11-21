/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cdi;

import entities.Theme;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.SessionScoped;

/**
 *
 * @author atoutsios
 */
@Named(value = "themeController")
@SessionScoped


public class ThemeController implements  Serializable{
    List<Theme> themes;
    private Theme selected;

    @PostConstruct
    public void init() {
        themes = new ArrayList<>();
        themes.add(new Theme("afterdark"));
        themes.add(new Theme("afternoon"));
        themes.add(new Theme("aristo"));
        themes.add(new Theme("black-tie"));
        themes.add(new Theme("cupertino"));
        themes.add(new Theme("cruze"));
        themes.add(new Theme("dark-hive"));
        themes.add(new Theme("casablanca"));
     }

    public List<Theme> getThemes() {
        return themes;
    }

    public void setThemes(List<Theme> themes) {
        this.themes = themes;
    }

    public Theme getSelected() {
        return selected;
    }

    public void setSelected(Theme selected) {
        this.selected = selected;
    }
       


}
