/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agenda.controller;

import com.agenda.ejb.MenuFacadeLocal;
import com.agenda.model.Menu;
import com.agenda.model.Usuario;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import org.primefaces.event.RowEditEvent;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;

/**
 *
 * @author bsf_o
 */
@Named
@SessionScoped
public class MenuController implements Serializable {

    private  Menu menu;
    @EJB
    private MenuFacadeLocal menuEJB;

    private MenuModel model;

    List<Menu> listaMenu;
    List<Menu> lstPermisosMenu;

    @PostConstruct
    public void init() {

        this.listarMenus();
        model = new DefaultMenuModel();
        this.establecerPermisos();
        lstPermisosMenu = menuEJB.findAll();

    }

    public void listarMenus() {
        try {
            listaMenu = menuEJB.findAll();

        } catch (Exception e) {
        }

    }
    
    public void modificarPermisos(){
       
        menuEJB.edit(menu);
    }

    public void establecerPermisos() {

        Usuario us = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");

        for (Menu m : listaMenu) {
            if (m.getTipo().equals("SubMenu") && m.getTipoUsario().equals(us.getTipo())) {
                DefaultSubMenu primerSubmenu = new DefaultSubMenu(m.getNombre());
                for (Menu i : listaMenu) {
                    Menu submenu = i.getSubmenu();
                    if (submenu != null) {
                        if (submenu.getCodigo_menu() == m.getCodigo_menu()) {
                            DefaultMenuItem item = new DefaultMenuItem(i.getNombre());
                            item.setUrl(i.getUrl());
                            primerSubmenu.addElement(item);
                        }
                    }
                }
                model.addElement(primerSubmenu);

            } else {
                if (m.getSubmenu() == null && m.getTipoUsario().equals(us.getTipo())) {
                    DefaultMenuItem item = new DefaultMenuItem(m.getNombre());
                    item.setUrl(m.getUrl());
                    model.addElement(item);
                }

            }
        }
    }



    public void cerrarSesion() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
    }

    public MenuModel getModel() {
        return model;
    }

    public void setModel(MenuModel model) {
        this.model = model;
    }

    public List<Menu> getLstPermisosMenu() {
        return lstPermisosMenu;
    }

    public void setLstPermisosMenu(List<Menu> lstPermisosMenu) {
        this.lstPermisosMenu = lstPermisosMenu;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    
}
