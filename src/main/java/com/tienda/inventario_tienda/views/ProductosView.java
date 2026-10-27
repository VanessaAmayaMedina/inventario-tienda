package com.tienda.inventario_tienda.views;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.router.Route;

@Route ("productos")
public class ProductosView extends VerticalLayout {


    public ProductosView() {

        add(new H1("Inventario de productos"));
        
        TextField codigo = new TextField("Código");
        TextField nombre = new TextField("Nombre");
        TextField categoria = new TextField("Categoría");
        
        NumberField precio = new NumberField("Precio");
        NumberField cantidad = new NumberField("Cantidad");
        NumberField stockMinimo = new NumberField("Stock mínimo");

        add( codigo, nombre, categoria, precio, cantidad, stockMinimo);

    }


}
