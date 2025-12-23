package com.ceva.jfx.ch02;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * Grid Pane: Coloca los nodos hijos en grilla de dos dimensiones de tamano flexible. Es la clase que mas se parece a GridBagLayout de Java Swing
 * Este layout container es ideal para colocar componentes en formulario
 */
public class D_GridPaneLayout extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        GridPane gridPane = new GridPane();
        // position -> column 0 / row 0
        gridPane.add(new Label("Label 1"), 0, 0);
        // position -> column 1 / row 0 and span 2 columns
        gridPane.add(new Label("Label 2 is very long"), 1, 0, 2, 1);

        Scene scene = new Scene(gridPane, 350, 230, Color.LIGHTGREEN);
        stage.setTitle("Layout container control: AnchorPane");
        stage.setScene(scene);
        stage.show();
    }
}
