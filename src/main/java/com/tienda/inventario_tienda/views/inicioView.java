package com.tienda.inventario_tienda.views;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("")
public class inicioView  extends VerticalLayout {

    public inicioView() {
        add(new H1("Inventario de tienda"));
        add(new Paragraph("Esta es la página de inicio de la aplicación de inventario "));
    }
}
