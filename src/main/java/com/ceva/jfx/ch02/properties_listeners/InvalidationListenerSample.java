package com.ceva.jfx.ch02.properties_listeners;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * CUando el usuario mueve el slider , un Invalidation Listener detecta que la propiedad cambio y actualiza el texto del label.
 * el change listener sigue la politica: Algo cambio, fijate
 */
public class InvalidationListenerSample extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Slider slider = new Slider(0, 100, 50);
        Label label =new Label("Valor actual: 50");


        // slider.valueProperty() es un observable
        slider.valueProperty()
                // Registro del Invalidation Listener. Cuando el valor ya no es valido, se ejecuta el siguiente codigo
                .addListener((observable) ->
                {
                    // el valor puede haber cambiado, lo volvemos a consultar
                    label.setText("Valor actual: " + (int)slider.getValue());
        });

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
