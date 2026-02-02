module com.ceva.jfx.fxbasics {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;

    opens com.ceva.jfx.ch01 to javafx.fxml;
    exports com.ceva.jfx.ch01;
    exports com.ceva.jfx.ch02;
    exports com.ceva.jfx.ch02.properties_listeners;
}