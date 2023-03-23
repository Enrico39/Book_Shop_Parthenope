module bookshopparthenope {
    requires java.sql;
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.base;
    requires java.desktop;


    opens bookshopparthenope to javafx.fxml;
    exports bookshopparthenope;
}