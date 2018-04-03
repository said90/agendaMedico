/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agenda.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author bsf_o
 */
@Entity
@Table(name = "menu")
public class Menu implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int codigo_menu;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "tipo")
    private String tipo;

    @Column(name = "tipoUsario")
    private String tipoUsario;
    
    @ManyToOne
    @JoinColumn(name = "codigo_submenu")
    private Menu submenu;
    
    @Column(name = "estado")
    private boolean estado= true;
    
    @Column(name = "url")
    private String url;

    public int getCodigo_menu() {
        return codigo_menu;
    }

    public void setCodigo_menu(int codigo_menu) {
        this.codigo_menu = codigo_menu;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

  

    public Menu getSubmenu() {
        return submenu;
    }

    public void setSubmenu(Menu submenu) {
        this.submenu = submenu;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public String getTipoUsario() {
        return tipoUsario;
    }

    public void setTipoUsario(String tipoUsario) {
        this.tipoUsario = tipoUsario;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    
    
    
}
