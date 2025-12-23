package com.ceva.jfx.ch02;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class C_AnchorPaneLayout extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        AnchorPane anchorPane = new AnchorPane();
        Label label = new Label("My label");
        anchorPane.getChildren().add(label);

        AnchorPane.setLeftAnchor(label, 10.0);
        AnchorPane.setBottomAnchor(label, 10.0);

        Scene scene = new Scene(anchorPane, 350, 230, Color.LIGHTGREEN);
        stage.setTitle("Layout container control: AnchorPane");
        stage.setScene(scene);
        stage.show();

    }
}
