package fr.cuib.mediathequeweb.controller;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import org.primefaces.component.breadcrumb.BreadCrumb;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.MenuItem;
import org.primefaces.model.menu.MenuModel;

@Named
@RequestScoped
public class MenuBean {
    private MenuModel bar;

    public MenuBean() {
        bar = new DefaultMenuModel();

        DefaultMenuItem item = DefaultMenuItem.builder()
                .value("Accueil")
                .url("/CUIB")
                .icon("pi pi-home")
                .build();

        bar.getElements().add(item);
    }

    public MenuModel getBar() {
        return bar;
    }

    public void setBar(MenuModel bar) {
        this.bar = bar;
    }

    public void addItemToMenuModel(String value, String url, boolean disabled) {
        DefaultMenuItem element = new DefaultMenuItem();
        element.setValue(value);
        element.setUrl(url);
        element.setDisabled(disabled);
        this.bar.getElements().add(element);
    }
}
