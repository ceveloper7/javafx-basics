package com.ceva.jfx.ch02;

import javafx.application.Application;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class E_FlowPaneLayout extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        // set orientation to VERTICAL
        FlowPane flowPane = new FlowPane(Orientation.VERTICAL);

        Scene scene = new Scene(flowPane, 350, 230, Color.LIGHTBLUE);
        stage.setScene(scene);
        stage.show();
    }
}
