package com.ceva.jfx.ch02.properties_listeners;

import javafx.application.Application;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Cada vez que el usuario mueve el Slide, JavaFX:
 * Detecta el cambio
 * Ejecuta el Listener
 * Entrega el oldValue y newValue
 * El ChangeListener sigue la politica, algo cambio: ante valia x ahora vale y
 */
public class ChangeListenerSample extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Slider slider = new Slider(0,100,50);
        Label label = new Label("Valor actual: 50");

        // ChangeListener
        slider.valueProperty()
                // con ChangeListener la firma de addListener cambia. Recibimos el oldValue y newValue
                .addListener(
                        (observable, oldValue, newVallue) ->{
                            label.setText("Antes: " + oldValue.intValue() + " | Ahora: " + newVallue.intValue());
                        }
                );

        VBox root = new VBox(15, slider, label);
        root.setStyle("-fx-padding: 20");

        Scene scene = new Scene(root, 300,150);
        stage.setTitle("Invalidation Listener - JavaFX");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
