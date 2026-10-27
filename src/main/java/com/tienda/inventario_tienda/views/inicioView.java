package com.tienda.inventario_tienda.views;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("") // Página principal (http://localhost:8080)
//esta es la ruta 

public class inicioView extends VerticalLayout {

    private boolean showingDetails = false;

    public inicioView() {
        // estilos de la vista de inicio
        setSizeFull();
        setJustifyContentMode(JustifyContentMode.CENTER);
        setAlignItems(Alignment.CENTER);
        getStyle()
            .set("background-color", "#000000")
            .set("font-family", "'Playfair Display', Georgia, serif")
            .set("color", "#ffffff")
            .set("padding", "20px");

        // Contenedor principal para centrar el contenido y limitar el ancho
        VerticalLayout container = new VerticalLayout();
        container.setMaxWidth("420px");
        container.setAlignItems(Alignment.CENTER);
        container.getStyle()
            .set("text-align", "center");

        // creador/ mi nombre vanessa medina, en parte superior.
        Span brand = new Span("VanessaMedina");
        brand.getStyle()
            .set("font-size", "0.85rem")
            .set("letter-spacing", "3px")
            .set("text-transform", "uppercase")
            .set("color", "#a1a1aa")
            .set("margin-bottom", "35px");

        H1 titulo = new H1();
        titulo.getElement().setProperty("innerHTML", "Control<br><i>Inventario</i>");
        titulo.getStyle()
            .set("color", "#ffffff")
            .set("font-size", "3.2rem")
            .set("font-weight", "400")
            .set("line-height", "1.15")
            .set("margin-bottom", "20px");

        Paragraph descripcion = new Paragraph("Bienvenido al sistema.");
        descripcion.getStyle()
            .set("color", "#d4d4d8")
            .set("font-size", "1rem")
            .set("font-weight", "300")
            .set("margin-bottom", "45px")
            .set("line-height", "1.5");

        // Botón que lleva a la vista de descripcion al usuario.
        Button btnAccion = new Button("Siguiente");
        btnAccion.getStyle()
            .set("background", "transparent")
            .set("color", "#ffffff")
            .set("font-family", "'Playfair Display', Georgia, serif")
            .set("font-size", "0.95rem")
            .set("padding", "12px 38px")
            .set("border-radius", "30px")
            .set("border", "1px solid rgba(255, 255, 255, 0.4)")
            .set("cursor", "pointer")
            .set("transition", "all 0.3s ease");

        // despues de hacer click en btn.
        btnAccion.addClickListener(e -> {
            if (!showingDetails) {
                showingDetails = true;

                titulo.getElement().setProperty("innerHTML", "Gestiona <br><i>tu inventario</i>");
                descripcion.setText("Administra tu catálogo de forma eficiente, visualiza el estado actual de tu tienda y optimiza tu inventario en tiempo real.");
                descripcion.getStyle().set("font-size", "0.92rem").set("color", "#a1a1aa");



                // Muestra la información de lo que podrá hacer la app antes de entrar
                descripcion.setText("Al ingresar podrás administrar tu catálogo de productos, controlar existencias y supervisar el stock mínimo.");
                descripcion.getStyle().set("font-size", "0.92rem").set("color", "#a1a1aa");
                btnAccion.setText("Iniciar");
            } else {
                // Pasa a la vista de productos 
                UI.getCurrent().navigate("productos");
            }
        });

        container.add(brand, titulo, descripcion, btnAccion);
        add(container);
    }
}