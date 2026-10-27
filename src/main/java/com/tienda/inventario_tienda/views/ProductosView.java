package com.tienda.inventario_tienda.views;

import com.tienda.inventario_tienda.model.Producto;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.Route;

import java.util.ArrayList;
import java.util.List;

@Route("productos")
public class ProductosView extends HorizontalLayout {

    //investigacion de grid para organizar los productos en una tabla
    private Grid<Producto> grid = new Grid<>(Producto.class, false);
    private List<Producto> listaProductos = new ArrayList<>();
    private Binder<Producto> binder = new Binder<>(Producto.class);//productos organizados en una tabla

    private VerticalLayout contentArea;

    public ProductosView() {

        setSizeFull();
        getStyle()
            .set("background-color", "#050507")
            .set("font-family", "'Inter', -apple-system, sans-serif")
            .set("color", "#ffffff");

        // barra lateral.
        VerticalLayout sidebar = createSidebar();

        // dashboard.
        contentArea = new VerticalLayout();
        contentArea.setSizeFull();
        contentArea.getStyle()
            .set("padding", "40px")
            .set("background-color", "#08080a")
            .set("overflow-y", "auto");

        
        showDashboardHome();

        add(sidebar, contentArea);
        setFlexGrow(1, contentArea);
    }

    //contenedor vertical del dashboard.
    private VerticalLayout createSidebar() {
        VerticalLayout sidebar = new VerticalLayout();
        sidebar.setWidth("260px");
        sidebar.setHeightFull();
        sidebar.getStyle()
            .set("background-color", "#0b0b0e")
            .set("border-right", "1px solid rgba(255, 255, 255, 0.06)")
            .set("padding", "30px 20px");

        Span brandTitle = new Span("VANESSAMEDINA");
        brandTitle.getStyle()
            .set("font-size", "0.75rem")
            .set("font-weight", "600")
            .set("letter-spacing", "2px")
            .set("color", "#71717a")
            .set("margin-bottom", "30px");

        Button btnDashboard = createMenuButton("📊 Dashboard & Stock", e -> showDashboardHome());
        Button btnRegistro = createMenuButton("➕ Registrar Producto", e -> showRegistroForm());
        Button btnVolver = createMenuButton("← Volver al Inicio", e -> getUI().ifPresent(ui -> ui.navigate("")));
        btnVolver.getStyle().set("margin-top", "auto"); // Empuja el botón al fondo

        sidebar.add(brandTitle, btnDashboard, btnRegistro, btnVolver);
        return sidebar;
    }

    // Estilos de btns
    private Button createMenuButton(String text, ComponentEventListener<ClickEvent<Button>> listener) {        Button btn = new Button(text, listener);
        btn.setWidthFull();
        btn.getStyle()
            .set("background", "transparent")
            .set("color", "#a1a1aa")
            .set("font-size", "0.9rem")
            .set("justify-content", "flex-start")
            .set("padding", "12px 16px")
            .set("border-radius", "8px")
            .set("border", "none")
            .set("cursor", "pointer")
            .set("transition", "all 0.2s ease");
        return btn;
    }

    // VISTA 1: DASHBOARD Y LISTA DE STOCK
    private void showDashboardHome() {
        contentArea.removeAll();

        H2 titulo = new H2("Control de Existencias");
        titulo.getStyle().set("color", "#ffffff").set("font-weight", "400").set("margin-bottom", "20px");


        //evitar que se agreguen columnas duplicadas al codigo.
        if (grid.getColumns().isEmpty()) {
            grid.addColumn(Producto::getNombre).setHeader("Nombre").setFlexGrow(2);
            grid.addColumn(Producto::getPrecio).setHeader("Precio").setFlexGrow(1);
            grid.addColumn(Producto::getCantidad).setHeader("Stock Actual").setFlexGrow(1);
        }

        // Datos de ejemplo iniciales si está vacío
        if (listaProductos.isEmpty()) {
            listaProductos.add(new Producto());
            // Nota: aquí puedes poblar con datos reales de tu base de datos más adelante
        }
        grid.setItems(listaProductos);
        grid.setHeight("400px");
        grid.getStyle()
            .set("background", "transparent")
            .set("border", "1px solid rgba(255, 255, 255, 0.08)")
            .set("border-radius", "12px");

        contentArea.add(titulo, grid);
    }

    // VISTA 2: FORMULARIO DE REGISTRO DE PRODUCTOS
    private void showRegistroForm() {
        contentArea.removeAll();

        H2 titulo = new H2("Registro de Nuevo Producto");
        titulo.getStyle().set("color", "#ffffff").set("font-weight", "400").set("margin-bottom", "20px");

        // Tarjeta contenedora del formulario
        VerticalLayout formCard = new VerticalLayout();
        formCard.setMaxWidth("600px");
        formCard.getStyle()
            .set("background", "rgba(20, 20, 25, 0.75)")
            .set("border", "1px solid rgba(255, 255, 255, 0.08)")
            .set("border-radius", "16px")
            .set("padding", "30px");

        TextField nombreField = new TextField("Nombre del Producto");
        NumberField precioField = new NumberField("Precio Unitario");
        NumberField cantidadField = new NumberField("Cantidad en Stock");

        // Estilizar campos para que combinen con el fondo oscuro
        formatField(nombreField);
        formatField(precioField);
        formatField(cantidadField);

        Button btnGuardar = new Button("Guardar Producto", e -> {
            Producto nuevo = new Producto();
            nuevo.setNombre(nombreField.getValue());
            nuevo.setPrecio(precioField.getValue() != null ? precioField.getValue() : 0.0);
            nuevo.setCantidad(cantidadField.getValue() != null ? cantidadField.getValue().intValue() : 0);

            listaProductos.add(nuevo);
            grid.setItems(listaProductos);

            // Regresar al dashboard de stock para ver el resultado
            showDashboardHome();
        });

        btnGuardar.getStyle()
            .set("background-color", "#ffffff")
            .set("color", "#000000")
            .set("font-weight", "600")
            .set("border-radius", "30px")
            .set("padding", "12px 28px")
            .set("margin-top", "20px");

        formCard.add(nombreField, precioField, cantidadField, btnGuardar);
        contentArea.add(titulo, formCard);
    }

    // Método auxiliar para dar estilo oscuro a los inputs de texto/número
    private void formatField(com.vaadin.flow.component.AbstractField<?, ?> field) {
        field.getElement().getStyle().set("width", "100%");
        field.getStyle().set("--vaise-input-background", "rgba(255,255,255,0.03)");
    }
}