package com.ceva.jfx.ch02;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class H_JavaFXPropertiesExample extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Text rotatingText = new Text("JavaFX Properties");
        Label valueLabel = new Label();
        Text changeListenerText = new Text();

        Slider slider = new Slider(0,300,0);

        // 1 Property Binding con formato (Unidirectional Binding)
        valueLabel.textProperty().bind(slider.valueProperty().asString("Slider value: %.0f"));

        // 2 Property Binding between nodes. La rotacion de texto depende del valor del Slider
        rotatingText.rotateProperty().bind(slider.valueProperty());

        // 3 ChangeListener (oldValue + newValue).
        slider.valueProperty().addListener((observable, oldValue,newValue) ->{
            changeListenerText.setText("Was " + oldValue.intValue() + ", Now " + newValue.intValue());
        });

        // 4 InvalidationListener (no old/new values)
        slider.valueProperty().addListener(observable -> {
            System.out.println("Slider value changed (InvalidationListener)");
        });

        VBox root = new VBox(10, rotatingText, slider, valueLabel, changeListenerText);
        root.setAlignment(Pos.CENTER);

        stage.setScene(new Scene(root, 400, 300));
        stage.setTitle("JavaFX Properties Demo");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
